import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class vincere extends MIDlet {
    private Display display;
    private Form mainForm;

    public vincere() {
        
    }

    protected void startApp() {
        display = Display.getDisplay(this);
        mainForm = new Form("vincere v0.1");
        mainForm.append("It works!");
        display.setCurrent(mainForm);
    }

    protected void pauseApp() {}

    protected void destroyApp(boolean unconditional) {}
}