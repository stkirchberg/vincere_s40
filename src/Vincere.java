import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.util.Vector;

public class Vincere extends MIDlet implements CommandListener {
    private Display display;

    private Form loginForm;
    private Form chatForm;

    private TextField tfUser, tfPass, tfInput;
    private Command cmdLogin, cmdSend, cmdExit;

    private Vector chatMessages = new Vector();

    public Vincere() {
        display = Display.getDisplay(this);

        loginForm = new Form("vincere Login");
        tfUser = new TextField("Nutzer:", "", 15, TextField.ANY);
        tfPass = new TextField("Passwort:", "", 15, TextField.PASSWORD);
        cmdLogin = new Command("Login", Command.OK, 1);
        cmdExit = new Command("Exit", Command.EXIT, 1);
        
        loginForm.append(tfUser);
        loginForm.append(tfPass);
        loginForm.addCommand(cmdLogin);
        loginForm.addCommand(cmdExit);
        loginForm.setCommandListener(this);
    }

    protected void startApp() {
        display.setCurrent(loginForm);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdExit) {
            notifyDestroyed();
        } else if (c == cmdLogin) {
            startChat();
        } else if (c == cmdSend) {
            processMessage();
        }
    }

    private void startChat() {
        chatForm = new Form("vincere - " + tfUser.getString());
        tfInput = new TextField("Nachricht:", "", 100, TextField.ANY);
        cmdSend = new Command("Senden", Command.OK, 1);
        
        chatForm.addCommand(cmdSend);
        chatForm.append(tfInput);
        chatForm.setCommandListener(this);
        display.setCurrent(chatForm);
    }

    private void processMessage() {
        String msg = tfInput.getString();
        if (msg.length() > 0) {
            chatMessages.addElement(tfUser.getString() + ": " + msg);

            chatForm.insert(0, new StringItem(null, "> " + msg));
            tfInput.setString("");
        }
    }

    protected void pauseApp() {}
    protected void destroyApp(boolean unconditional) {}
}