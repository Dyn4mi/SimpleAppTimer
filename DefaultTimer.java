import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;



class DefaultTimer implements Runnable,SimpleTimer{
    PopupHandler popup;
    int setSeconds;
    CountDownLatch remainingSeconds;
    boolean isOver = true;
    Thread thisThread = Thread.currentThread();
    
    
    public DefaultTimer(int seconds){
        setSeconds(seconds);
    }
    
    public void setPopup(PopupHandler handler){
        popup = handler;
    }
    
    
    public Thread returnThread(){
        return thisThread;
    }
    
    
    @Override
        public void start(){
        try{
            TimerTask task = new TimerTask(){
                public void run(){
                    remainingSeconds.countDown();
                    popup.displaySeconds((int)remainingSeconds.getCount());
                    if(thisThread.isInterrupted()){
                        this.cancel();
                        while(remainingSeconds.getCount() != 0){
                            remainingSeconds.countDown();
                        }
                    }
                    
                    
            };
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task,0,1000);
        if(thisThread.isInterrupted()){
            throw new InterruptedException();
        }
        try{
            remainingSeconds.await();
        }catch (InterruptedException e){
            timer.cancel();
        }
        if(thisThread.isInterrupted()){
            throw new InterruptedException();
        }
        task.cancel();
        popup.showWarning("YOU HAVE 2 MINUTES BEFOER THIS APPLICATION CLOSES! \n IF YOU NEED TO SAVE SOMETHING NOW IS THE TIME!");
        setSeconds(120);
        TimerTask task2 = new TimerTask(){       
                public void run(){
                    remainingSeconds.countDown();
                    popup.displaySeconds((int)remainingSeconds.getCount());
                    
                    
            };
        };
        Timer timer2 = new Timer();
        if(thisThread.isInterrupted()){
            throw new InterruptedException();
        }
        timer2.scheduleAtFixedRate(task2,0,1000);
        try{
            remainingSeconds.await();
        }catch (InterruptedException e){
            timer2.cancel();
        }
        if(thisThread.isInterrupted()){
            throw new InterruptedException();
        }
    } catch (InterruptedException e){
        
    }
    }
    

    
    @Override
    public void run(){
        start();
    }
    
    @Override
    public void setSeconds(int seconds){
        setSeconds = seconds;
        remainingSeconds = new CountDownLatch(seconds);
        isOver = false;
    }
    
    @Override
    public int checkRemaining(){
        return (int)remainingSeconds.getCount();
    }
    
    @Override
    public boolean isOver(){
        return isOver;
    }
}
