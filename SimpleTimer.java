
public interface SimpleTimer{
  void setSeconds(int seconds);
  void start();
  int checkRemaining();
  boolean isOver();
  Thread returnThread();
  void setPopup(PopupHandler popup);
}


  
