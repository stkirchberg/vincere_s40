import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class NChat extends MIDlet {
    private Display display;
    private ChatCanvas canvas;

    public NChat() {
    }

    protected void startApp() {
        if (display == null) {
            display = Display.getDisplay(this);
            canvas = new ChatCanvas();
        }
        display.setCurrent(canvas);
    }

    protected void pauseApp() {}

    protected void destroyApp(boolean unconditional) {}

    class ChatCanvas extends Canvas {
        protected void paint(Graphics g) {
            g.setColor(0, 0, 0);
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setColor(255, 255, 255);
            g.drawString("nChat laeuft!", getWidth()/2, getHeight()/2, Graphics.BASELINE | Graphics.HCENTER);
        }
    }
}