import javax.microedition.lcdui.*;
import java.util.Vector;

public class ChatCanvas extends Canvas {
    private Vincere midlet;
    private Vector messages;
    private boolean isGenerating = false;
    private CryptoEngine crypto;

    public ChatCanvas(Vincere midlet) {
        this.midlet = midlet;
        this.messages = new Vector();
        this.crypto = new CryptoEngine();
        setFullScreenMode(true);
    }

    public void startKeygen() {
        isGenerating = true;
        repaint();
        new Thread(new Runnable() {
            public void run() {
                crypto.generateKeyPair();
                isGenerating = false;
                ChatCanvas.this.repaint();
            }
        }).start();
    }

    public void addMessage(String u, String t) {
        messages.addElement(u + "> " + t);
        if (messages.size() > 15) messages.removeElementAt(0);
        repaint();
    }

    protected void paint(Graphics g) {
        int w = getWidth();
        int h = getHeight();
        g.setColor(0x000000);
        g.fillRect(0, 0, w, h);
        g.setColor(0x00FF00);
        g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
        String n = midlet.getUserName();
        String uDisp = (n == null || n.equals("")) ? "???" : n.toUpperCase();
        g.drawString("VINCERE// " + midlet.getRoomName() + "// " + uDisp, 5, 5, 0);
        g.drawLine(0, 20, w, 20);
        if (isGenerating) {
            g.drawString("GENERATING KEYS...", 10, h/2, 0);
        } else if (!midlet.isLoggedIn()) {
            g.drawString("PRESS ANY KEY TO LOGIN", 10, h/2, 0);
        } else {
            int y = h - 35;
            g.setFont(Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_SMALL));
            for (int i = messages.size() - 1; i >= 0; i--) {
                String m = (String) messages.elementAt(i);
                g.setColor(m.startsWith(n + ">") ? 0xFFFFFF : 0x00FF00);
                g.drawString(m, 5, y, 0);
                y -= 15;
                if (y < 25) break;
            }
            g.setColor(0x002200);
            g.fillRect(0, h - 15, w, 15);
            g.setColor(0x00FF00);
            g.drawString("> Type message...", 5, h - 14, 0);
        }
    }

    protected void keyPressed(int k) {
        if (!isGenerating) midlet.openInput(midlet.isLoggedIn() ? "Message" : "Username");
    }
}