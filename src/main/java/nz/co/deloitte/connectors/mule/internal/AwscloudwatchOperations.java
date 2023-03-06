package nz.co.deloitte.connectors.mule.internal;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import java.util.ArrayList;
import java.util.List;

import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import software.amazon.awssdk.auth.credentials.SystemPropertyCredentialsProvider;
import software.amazon.awssdk.services.cloudwatchlogs.CloudWatchLogsClient;
import software.amazon.awssdk.services.cloudwatchlogs.model.DescribeLogStreamsRequest;
import software.amazon.awssdk.services.cloudwatchlogs.model.DescribeLogStreamsResponse;
import software.amazon.awssdk.services.cloudwatchlogs.model.InputLogEvent;
import software.amazon.awssdk.services.cloudwatchlogs.model.PutLogEventsRequest;
import software.amazon.awssdk.services.cloudwatchlogs.model.PutLogEventsResponse;

/**
 * This class is a container for operations, every public method in this class
 * will be taken as an extension operation.
 */
public class AwscloudwatchOperations {
	private final Logger logger = LoggerFactory.getLogger(AwscloudwatchOperations.class);

	/**
	 * Example of an operation that uses the configuration to perform a retrieve Info operation.
	 */
	@MediaType(value = ANY, strict = false)
	public String retrieveInfo(@Config final AwscloudwatchConfiguration config) {
		logger.debug("Capturing the configurations...");
		return config.toString();
	}
	
	/**
	 * Operation that receives a list parameter to be processed (JSON based) and doesn't returns a
	 * value (if returning something it would be included into the payload).
	 */
	@Alias("SendLogs")
	@MediaType(value = ANY, strict = false)
	public void sendLogs(@Config final AwscloudwatchConfiguration configs, final List<?> events) {
		final ObjectMapper mapper = new ObjectMapper();
		final CloudWatchLogsClient client = CloudWatchLogsClient.builder()
				.credentialsProvider(SystemPropertyCredentialsProvider.create())
				.build();
		final DescribeLogStreamsRequest logStreamRequest = DescribeLogStreamsRequest
				.builder()
				.logGroupName(configs.getLogGroupName())
				.logStreamNamePrefix(configs.getLogStreamName())
				.build();
		final DescribeLogStreamsResponse logStreamResponse = client.describeLogStreams(logStreamRequest);
		List<InputLogEvent> batch;
		String sequenceToken = logStreamResponse.logStreams().get(0).uploadSequenceToken();
		int j,i = 0;

		logger.debug("Starting to process LOG events... ");
		try {
			while (i < events.size()) {
				batch = new ArrayList<>(configs.getEventBatchSize());
				j = 0;
				while (j < configs.getEventBatchSize() && (i+j) < events.size()) {
					logger.debug("event : " + mapper.writeValueAsString(events.get(i+j)));
					batch.add(InputLogEvent.builder()
							.message(mapper.writeValueAsString(events.get(i+j)))
							.timestamp(System.currentTimeMillis())
							.build());
					j++;
				}
				i += j;
				sequenceToken = logEvent(client, configs.getLogGroupName(), configs.getLogStreamName(), sequenceToken, batch).nextSequenceToken();
				Thread.sleep(configs.getThrottleMili());
			}
		} catch (JsonProcessingException ex) {
			throw new RuntimeException("Error processing JSON from log events.",ex);
		} catch (InterruptedException ex) {
			throw new RuntimeException("Interruption exception when processing logs to AWS CloudWatch.",ex);
		}
		logger.debug("LOG events processed successfuly. ");	
	}

	/**
	 * Private Methods are not exposed as operations
	 */
	private PutLogEventsResponse logEvent(final CloudWatchLogsClient client, final String logGroupName,
			final String logStreamName, final String sequenceToken, final List<InputLogEvent> events) {
		return client.putLogEvents(PutLogEventsRequest.builder()
					.logEvents(events)
					.logGroupName(logGroupName)
					.logStreamName(logStreamName)
					.sequenceToken(sequenceToken)
					.build()
				);
	}
}
