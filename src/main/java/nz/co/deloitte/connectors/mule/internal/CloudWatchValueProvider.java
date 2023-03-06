/*
 * CloudWatchValueProvider.java
 */

package nz.co.deloitte.connectors.mule.internal;

import java.util.Set;

import org.mule.runtime.api.value.Value;
import org.mule.runtime.extension.api.values.ValueProvider;
import org.mule.runtime.extension.api.values.ValueResolvingException;

/**
 * This class provides values for parameters on configuration class (AwscloudwatchConfiguration.java).
 * These values would populate combo boxes during the development phase.
 * 
 * Many types of providers can be created here, for instances:
 * - PoolingConnectionProvider --> used on connectors with connection pools.
 * - Value Provider --> Providing values for combo boxes.
 * - etc.
 * @author rodrigo
 * @since 2023/03
 * 
 * NOT USED ON CLOUDWATCH CONNECTOR SO FAR.
 */
public class CloudWatchValueProvider implements ValueProvider {

	@Override
	public Set<Value> resolve() throws ValueResolvingException {
		return null;
	}
}
