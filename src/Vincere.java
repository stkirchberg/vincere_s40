import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class Vincere extends MIDlet implements CommandListener {
    private Display display;
    private ChatCanvas canvas;
    private TextBox inputBuffer;
    private Command sendCommand, backCommand, exitCommand;
    private String userName = "";
    private String roomName = "PUBLIC_ROOM";
    private boolean isLoggedIn = false;

    public Vincere() {
        display = Display.getDisplay(this);
        canvas = new ChatCanvas(this);
        
        sendCommand = new Command("Send", Command.OK, 1);
        backCommand = new Command("Back", Command.BACK, 2);
        exitCommand = new Command("Exit", Command.EXIT, 3);
        
        inputBuffer = new TextBox("Input", "", 50, TextField.ANY);
        inputBuffer.addCommand(sendCommand);
        inputBuffer.addCommand(backCommand);
        inputBuffer.setCommandListener(this);
        
        canvas.addCommand(exitCommand);
        canvas.setCommandListener(this);
    }

    protected void startApp() {
        if (display != null && canvas != null) {
            display.setCurrent(canvas);
        }
    }

    public void commandAction(Command c, Displayable d) {
        if (c == sendCommand) {
            String val = inputBuffer.getString().trim();
            if (!isLoggedIn && val.length() > 0) {
                userName = val;
                isLoggedIn = true;
                canvas.startKeygen();
            } else if (val.length() > 0) {
                canvas.addMessage(userName, val);
            }
            inputBuffer.setString("");
            display.setCurrent(canvas);
        } else if (c == backCommand) {
            display.setCurrent(canvas);
        } else if (c == exitCommand) {
            notifyDestroyed();
        }
    }

    public void openInput(String title) {
        inputBuffer.setTitle(title);
        display.setCurrent(inputBuffer);
    }

    public String getUserName() { return userName; }
    public String getRoomName() { return roomName; }
    public boolean isLoggedIn() { return isLoggedIn; }
    protected void pauseApp() {}
    protected void destroyApp(boolean u) {}
}