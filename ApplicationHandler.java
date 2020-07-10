
/**
 * Write a description of interface ApplicationHandler here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface ApplicationHandler{
  boolean runApp() throws java.io.IOException;
  boolean closeApp() throws java.io.IOException;
  boolean killProcess() throws java.io.IOException;
}
