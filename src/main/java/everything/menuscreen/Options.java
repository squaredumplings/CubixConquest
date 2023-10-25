package everything.menuscreen;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import everything.top.Config;
import everything.top.Window;

public class Options extends JLayeredPane implements ActionListener {

    public Options() {
        //panel settings
        this.setBounds(0, 0, Config.WINDOWWIDTH, Config.WINDOWHEIGHT);
        this.setBackground(Color.darkGray);
        this.setLayout(null);
        this.setVisible(true);

        // title
        JTextArea title = new JTextArea();
        title.setBounds(480, 90, 960, 180);
        title.setText("Work in progress");
        this.add(title, Integer.valueOf(1));

        // back button
        JButton backButton = new JButton();
        backButton.setText("back");
        backButton.setBounds(720, 450, 480, 90);
        backButton.setActionCommand("back");
        backButton.addActionListener(this);
        //backButton.setBorderPainted(false);
        backButton.setFocusable(false);
        this.add(backButton, Integer.valueOf(1));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Window parentWindow = (Window) SwingUtilities.getWindowAncestor(this);

        if (e.getActionCommand() == "back") {
            parentWindow.addMenu();
            parentWindow.removeOptions();
        }
    } 
}
