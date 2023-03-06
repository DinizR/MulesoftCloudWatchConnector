/*
 * CloudWatchConnection.java
 */
package nz.co.deloitte.connectors.mule.internal;


/**
 * This class represents an extension connection for components with connection pools.
 * 
 * @author rodrigo
 * @since 2023/03
 * 
 * NOT USED ON CLOUDWATCH CONNECTOR (IT IS HERE ONLY FOR ILLUSTRATION PURPOSES)
 */
public final class CloudWatchConnection {

  private final String id;

  public CloudWatchConnection(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void invalidate() {
    // do something to invalidate this connection!
  }
}
