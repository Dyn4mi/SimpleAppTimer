import java.io.OutputStream;


class ChromeHandler implements ApplicationHandler{
    String guChromeCommand = "cmd /c \"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome_proxy.exe\"  --profile-directory=Default --app-id=mhjfjjkadhogdhjmnhijeaigeecldjfi";
        
    @Override
    public boolean runApp() throws java.io.IOException{
        Process dos = Runtime.getRuntime().exec(guChromeCommand);
        return true;
    }
    
    @Override
    public boolean closeApp() throws java.io.IOException{
        Process dos = Runtime.getRuntime().exec("cmd /c TASKKILL /IM chrome.exe");
        return true;
    }
    
    @Override
    public boolean killProcess() throws java.io.IOException{
        Process dos = Runtime.getRuntime().exec("cmd /c start cmd.exe");
        OutputStream out = dos.getOutputStream();
        out.write("TSKILL chrome/r/n".getBytes());
        out.flush();
        return true;
    }
}
                                                    
        