import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.util.Vector;

public class Vincere extends MIDlet implements CommandListener {
    private Display display;
    private ChatCanvas canvas;
    private TextBox inputBuffer;
    private Command sendCommand, backCommand;
    private String userName = "";
    private boolean isLoggedIn = false;

    public Vincere() {
        display = Display.getDisplay(this);
        canvas = new ChatCanvas();
        inputBuffer = new TextBox("Input", "", 100, TextField.ANY);
        sendCommand = new Command("Send", Command.OK, 1);
        backCommand = new Command("Back", Command.BACK, 2);
        inputBuffer.addCommand(sendCommand);
        inputBuffer.addCommand(backCommand);
        inputBuffer.setCommandListener(this);
    }

    protected void startApp() {
        display.setCurrent(canvas);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == sendCommand) {
            String msg = inputBuffer.getString();
            if (!isLoggedIn) {
                if (msg.trim().length() > 0) {
                    userName = msg.trim();
                    isLoggedIn = true;
                }
            } else {
                if (msg.length() > 0) {
                    canvas.addMessage(userName, msg);
                }
            }
            inputBuffer.setString("");
            display.setCurrent(canvas);
        } else if (c == backCommand) {
            display.setCurrent(canvas);
        }
    }

    protected void pauseApp() {}
    protected void destroyApp(boolean unconditional) {}

    class ChatCanvas extends Canvas {
        private Vector messages = new Vector();

        public ChatCanvas() {
            setFullScreenMode(true);
        }

        public void addMessage(String user, String text) {
            messages.addElement(user + ": " + text);
            if (messages.size() > 20) messages.removeElementAt(0);
            repaint();
        }

        protected void paint(Graphics g) {
            int w = getWidth();
            int h = getHeight();
            g.setColor(0x000000);
            g.fillRect(0, 0, w, h);
            g.setColor(0x00FF00);
            g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));

            if (!isLoggedIn) {
                g.drawString("VINCERE // LOGIN", 5, 5, 0);
                g.drawLine(0, 20, w, 20);
                g.drawString("Press any key", 5, h/2 - 10, 0);
                g.drawString("to set Username", 5, h/2 + 5, 0);
            } else {
                g.drawString("VINCERE// PUBLIC_ROOM / " + userName.toUpperCase(), 5, 5, 0);
                g.drawLine(0, 20, w, 20);

                int y = h - 35;
                g.setFont(Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_SMALL));
                for (int i = messages.size() - 1; i >= 0; i--) {
                    String m = (String) messages.elementAt(i);
                    g.setColor(m.startsWith(userName + ":") ? 0xFFFFFF : 0x00FF00);
                    g.drawString(m, 5, y, 0);
                    y -= 15;
                    if (y < 25) break;
                }
                g.setColor(0x004400);
                g.fillRect(0, h - 15, w, 15);
                g.setColor(0x00FF00);
                g.drawString("> Press key to message...", 5, h - 14, 0);
            }
        }

        protected void keyPressed(int keyCode) {
            inputBuffer.setTitle(isLoggedIn ? "Message" : "Enter Username");
            display.setCurrent(inputBuffer);
        }
    }
}