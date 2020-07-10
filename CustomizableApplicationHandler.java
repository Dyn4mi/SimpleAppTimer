

import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import java.io.OutputStream;
import java.io.FileWriter;

public class CustomizableApplicationHandler implements ApplicationHandler{
    String BrowserCommand;
    String BrowserName;
    final String BROWSERNAME_KEY = "BROWSER_NAME";
    final String BROWSERCMD_KEY = "BROWSER_CMD";
        
    @Override
    public boolean runApp() throws java.io.IOException{
        File configFile = new File("cfg.txt");
        configFile.createNewFile();
        Properties properties = new Properties();
        FileReader reader = new FileReader(configFile);
        FileWriter writer = new FileWriter(configFile);
        properties.load(reader);
        if(!properties.containsKey(BROWSERNAME_KEY)){
            properties.setProperty(BROWSERNAME_KEY, "//Put the name of the exe of your browser without the extension! ex.(Chrome.exe -> chrome)");
            properties.setProperty(BROWSERCMD_KEY, "// Put the command to run your browser, as if you were " + 
                "doing it from the terminal. Most of the time you can just copy-paste the \"Target\" In the shortcut. " +
                "Then, add a backslash before each backslash.");
            properties.store(writer, "");
            return false;
        } else {
            BrowserCommand = "cmd /c " + properties.getProperty(BROWSERCMD_KEY);
            BrowserName = properties.getProperty(BROWSERNAME_KEY);
            if (BrowserCommand.startsWith("/") || BrowserName.startsWith("/")){
                System.exit(-1);
        }
    }
        reader.close();

        Process dos = Runtime.getRuntime().exec(BrowserCommand);
        int debugDosExitValue = dos.exitValue();
        writer.close();
        return true;
    }
    
    @Override
    public boolean closeApp() throws java.io.IOException{
        Process dos = Runtime.getRuntime().exec(String.format("cmd /c TASKKILL /IM %s.exe",(BrowserName)));
        return true;
    }
    
    @Override
    public boolean killProcess() throws java.io.IOException{
        Process dos = Runtime.getRuntime().exec("cmd /c start cmd.exe");
        OutputStream out = dos.getOutputStream();
        out.write(String.format("TSKILL %s/r/n",BROWSERNAME_KEY).getBytes());
        out.flush();
        out.close();
        return true;
    }
}