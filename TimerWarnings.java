import javax.swing.JLabel;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;

class TimerWarnings extends JFrame implements PopupHandler,WindowListener{
    /**
     *Auto generated serialVersionUID
     */
    private static final long serialVersionUID = -6991345087093196419L;
    JLabel currentText;
    JFrame currentWindow;
    SimpleTimer time;
    
    public TimerWarnings(){
        currentWindow = new JFrame();
        currentText = new JLabel();
        showTimer();
    }

    
    
    public void setTimerInstance(SimpleTimer timer){
        time = timer;
    }
    @Override
    public void showTimer(){ 
        currentWindow.addWindowListener(this);
        currentWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        currentText.setHorizontalAlignment(JLabel.CENTER);
        currentText.setVerticalAlignment(JLabel.CENTER);
        currentWindow.setSize(400,500);
        currentWindow.setLayout(new BorderLayout());
        currentText.setSize(200,400);
        currentWindow.getContentPane().add(currentText,BorderLayout.CENTER);
        
        currentWindow.setVisible(true);
        currentText.setVisible(true);
        currentWindow.toFront();
        currentWindow.requestFocus();
        
    }
    public void windowActivated(WindowEvent e){}; 
    public void windowClosing(WindowEvent e){
        currentWindow.dispose();
        time.returnThread().interrupt();
    };
    public void windowDeactivated(WindowEvent e){};
    public void windowDeiconified(WindowEvent e){};
    public void windowIconified(WindowEvent e){};
    public void windowOpened(WindowEvent e){};  
    public void windowClosed(WindowEvent e){
        
    }
    void windowStateChanged(WindowEvent e){};
    
    @Override
    public void displaySeconds(int seconds){
        int minutes = 0;
        int innerSeconds = 0;
        boolean digested = false;
        while(!digested){
            if(seconds > 60){
                seconds -= 60;
                minutes++;
            } else {
                innerSeconds = seconds;
                digested = true;
            }
        }
        currentText.setText(String.valueOf(minutes) + ":" 
                                + String.valueOf(innerSeconds));
    }
    
    @Override
    public void showWarning(String warning){
        currentWindow.setVisible(false);
        currentText.setVisible(false);
        currentWindow = new JFrame();
        currentText = new JLabel();
        currentText.setHorizontalAlignment(JLabel.CENTER);
        currentText.setVerticalAlignment(JLabel.CENTER);
        currentWindow.setSize(400,500);
        currentWindow.setLayout(new BorderLayout());
        currentText.setSize(200,400);
        currentWindow.getContentPane().add(currentText,BorderLayout.CENTER);
        currentText.setText(warning);
        
        
        currentWindow.toFront();
        currentWindow.setVisible(true);
        currentText.setVisible(true);
        currentWindow.requestFocus();
        try{
            Thread.sleep(20000);
        } catch (Exception e1){
        }
        
        
    }
}

        
        