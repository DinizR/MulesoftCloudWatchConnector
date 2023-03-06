package nz.co.deloitte.connectors.mule.internal;

import java.util.Set;

import org.mule.runtime.api.value.Value;
import org.mule.runtime.extension.api.values.ValueProvider;
import org.mule.runtime.extension.api.values.ValueResolvingException;

/**
 * This class provides values for parameters on configuration class (AwscloudwatchConfiguration.java).
 * These values would populate comboboxes during the development phase.
 * 
 * Many types of providers can be created here, for instances:
 * - PoolingConnectionProvider
 * - Value Provider
 * - etc.
 */
public class AwscloudwatchConnectionProvider implements ValueProvider {

	@Override
	public Set<Value> resolve() throws ValueResolvingException {
		return null;
	}
}
