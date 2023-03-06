/*
 * CloudWatchConfiguration.java
 */
package nz.co.deloitte.connectors.mule.internal;

import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.param.Parameter;

/**
 * This class represents an extension configuration, values set in this class are commonly used across multiple
 * operations since they represent something core from the extension.
 * 
 * @author rodrigo
 * @since 2023/03
 */
@Operations(CloudWatchOperations.class)
//@ConnectionProviders(AwscloudwatchConnectionProvider.class)
public class CloudWatchConfiguration {
	@Parameter
	private Integer eventBatchSize;
	
	@Parameter
	private Integer throttleMili;
	
	@Parameter
	private String logGroupName;
	
	@Parameter
	private String logStreamName;

	public Integer getEventBatchSize() {
		return eventBatchSize;
	}

	public void setEventBatchSize(Integer eventBatchSize) {
		this.eventBatchSize = eventBatchSize;
	}

	public Integer getThrottleMili() {
		return throttleMili;
	}

	public void setThrottleMili(Integer throttleMili) {
		this.throttleMili = throttleMili;
	}

	public String getLogGroupName() {
		return logGroupName;
	}

	public void setLogGroupName(String logGroupName) {
		this.logGroupName = logGroupName;
	}

	public String getLogStreamName() {
		return logStreamName;
	}

	public void setLogStreamName(String logStreamName) {
		this.logStreamName = logStreamName;
	}

	@Override
	public String toString() {
		return "Using Configuration [eventBatchSize=" + eventBatchSize + ", throttleMili=" + throttleMili
				+ ", logGroupName=" + logGroupName + ", logStreamName=" + logStreamName + "]";
	}
}