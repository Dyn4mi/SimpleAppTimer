import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;


class main{
    static final String TIMERSECONDS_KEY = "TIMER_SECONDS";
    static final String BROWSERNAME_KEY = "BROWSER_NAME";
    static final String BROWSERCMD_KEY = "BROWSER_CMD";
    public static void main(String[] args){
        File configFile = new File("cfg.txt");
        Properties properties = new Properties();
        try{
            FileReader reader = new FileReader(configFile);
            configFile.createNewFile();
            properties.load(reader);
        } catch(Exception e){
            
        }
        int timerSeconds = 0;
        if(!properties.containsKey(TIMERSECONDS_KEY)){
            properties.setProperty(TIMERSECONDS_KEY, "//Put how many seconds you want the timer to be here!");
            properties.setProperty(BROWSERNAME_KEY, "//Put the name of the exe of your browser without the extension! ex.(Chrome.exe -> chrome)");
            properties.setProperty(BROWSERCMD_KEY, "// Put the command to run your browser, as if you were" + 
                "doing it from the terminal. Most of the time you can just copy-paste the \"Target\" In the shortcut.");
            try{
            properties.store(new FileWriter(configFile), "");
            } catch (Exception e){
            }
            System.exit(-1);
        } else {
            try{
                timerSeconds = Integer.parseInt(properties.getProperty(TIMERSECONDS_KEY));
            } catch(Exception e){
            }
        }
        CustomizableApplicationHandler app = new CustomizableApplicationHandler();
        PopupHandler popup = new TimerWarnings();
        try{
            if(!app.runApp()){
                System.exit(-1);
            }
        } catch(Exception e){
            
        }
        SimpleTimer time = new DefaultTimer(timerSeconds);
        time.setPopup(popup);
        popup.setTimerInstance(time);
        
        Thread timerThread = new Thread((Runnable)time);
        try{
            timerThread.start();
            timerThread.join();
            
        }catch (Exception e){
        }
        try{
        app.closeApp();
        } catch (Exception e){
            
        }
        System.exit(0);
}
}
        
        
        
        