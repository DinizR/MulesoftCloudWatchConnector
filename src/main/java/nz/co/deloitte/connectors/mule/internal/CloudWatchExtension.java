/*
 * CloudWatchExtension.java
 */

package nz.co.deloitte.connectors.mule.internal;

import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.Configurations;
import org.mule.runtime.extension.api.annotation.dsl.xml.Xml;

/**
 * This is the main class of an extension, is the entry point from which configurations, connection providers, operations
 * and sources are going to be declared.
 * 
 * @author rodrigo
 * @since 2023/03
 */
@Xml(prefix = "aws-cloudwatch")
@Extension(name = "AWS-Cloudwatch")
@Configurations(CloudWatchConfiguration.class)
public class CloudWatchExtension {
}