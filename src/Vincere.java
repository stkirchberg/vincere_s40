import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.util.Vector;

public class Vincere extends MIDlet implements CommandListener {
    private Display display;
    private ChatCanvas canvas;
    private TextBox inputBuffer;
    private Command sendCommand, exitCommand;
    private String userName = "User"; 

    public Vincere() {
        display = Display.getDisplay(this);
        canvas = new ChatCanvas();
        inputBuffer = new TextBox("Nachricht", "", 100, TextField.ANY);
        sendCommand = new Command("Senden", Command.OK, 1);
        exitCommand = new Command("Abbrechen", Command.BACK, 2);
        inputBuffer.addCommand(sendCommand);
        inputBuffer.addCommand(exitCommand);
        inputBuffer.setCommandListener(this);
    }

    protected void startApp() {
        display.setCurrent(canvas);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == sendCommand) {
            String msg = inputBuffer.getString();
            if (msg.length() > 0) {
                canvas.addMessage(userName, msg);
                inputBuffer.setString("");
            }
            display.setCurrent(canvas);
        } else if (c == exitCommand) {
            display.setCurrent(canvas);
        }
    }

    protected void pauseApp() {}
    protected void destroyApp(boolean unconditional) {}

    class ChatCanvas extends Canvas {
        private Vector messages = new Vector();
        private int bgColor = 0x000000;
        private int matrixGreen = 0x00FF00;
        private int userColor = 0x00AA00;

        public void addMessage(String user, String text) {
            messages.addElement(user + ": " + text);
            if (messages.size() > 15) {
                messages.removeElementAt(0);
            }
            repaint();
        }

        protected void paint(Graphics g) {
            int w = getWidth();
            int h = getHeight();

            g.setColor(bgColor);
            g.fillRect(0, 0, w, h);

            g.setColor(matrixGreen);
            g.drawLine(0, 25, w, 25);
            g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
            g.drawString("VINCERE // PUBLIC_ROOM", 5, 5, Graphics.TOP | Graphics.LEFT);

            int y = h - 40;
            g.setFont(Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_SMALL));
            
            for (int i = messages.size() - 1; i >= 0; i--) {
                String m = (String) messages.elementAt(i);
                if (m.startsWith(userName)) {
                    g.setColor(0xFFFFFF);
                } else {
                    g.setColor(matrixGreen);
                }
                g.drawString(m, 5, y, Graphics.TOP | Graphics.LEFT);
                y -= 18;
                if (y < 30) break;
            }

            g.setColor(userColor);
            g.fillRect(0, h - 20, w, 20);
            g.setColor(0x000000);
            g.drawString("Druecke Taste zum Tippen...", 5, h - 18, Graphics.TOP | Graphics.LEFT);
        }

        protected void keyPressed(int keyCode) {
            display.setCurrent(inputBuffer);
        }
    }
}