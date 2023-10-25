package everything.top;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Color;


public class Debug extends JPanel {

    static JTextArea debugText = new JTextArea();

    public Debug() {
        debugText.setText("");
        debugText.setForeground(Color.BLACK);
        debugText.setEditable(false);
        debugText.setBounds(40, 40, 160, 200);
        debugText.setLineWrap(true);
        debugText.setFocusable(false);
        debugText.setOpaque(false);
        
        this.setBounds(40, 40, 160, 200);
        this.setOpaque(false);
        this.add(debugText);
    }

    public static void log(String text) {
        debugText.setText(text + "\n" + debugText.getText());
    }
}
