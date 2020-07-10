
/**
 * Write a description of interface a here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface PopupHandler{
  void showWarning(String warning);
  void showTimer();
  void displaySeconds(int seconds);
  void setTimerInstance(SimpleTimer time);
}