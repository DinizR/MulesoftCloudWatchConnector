package nz.co.deloitte.connectors.mule.internal;


/**
 * This class represents an extension connection just as example (there is no real connection with anything here c:).
 */
public final class AwscloudwatchConnection {

  private final String id;

  public AwscloudwatchConnection(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void invalidate() {
    // do something to invalidate this connection!
  }
}
