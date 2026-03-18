import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class Vincere extends MIDlet {
    private Display display;
    private Form mainForm;

    public Vincere() {
        
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