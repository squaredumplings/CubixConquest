package everything.menuscreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

import everything.top.Config;
import everything.top.Debug;
import everything.top.Window;

public class MenuPanel extends JLayeredPane implements ActionListener {
    
    public MenuPanel() {
        // images from resources
        final ImageIcon backgroundImage = new ImageIcon("src/main/resources/Background.png");
        final ImageIcon titleImage = new ImageIcon("src/main/resources/Title.png");
        final ImageIcon startImage = new ImageIcon("src/main/resources/Start.png");
        final ImageIcon optionsImage = new ImageIcon("src/main/resources/Options.png");
        final ImageIcon quitImage = new ImageIcon("src/main/resources/Quit.png");

        //panel settings
        this.setBounds(0, 0, Config.WINDOWWIDTH, Config.WINDOWHEIGHT);
        this.setBackground(Color.darkGray);
        this.setLayout(null);
        this.setVisible(true);

        // background
        JLabel background = new JLabel();
        background.setBounds(0, 0, Config.WINDOWWIDTH, Config.WINDOWHEIGHT);
        background.setIcon(backgroundImage);
        this.add(background, Integer.valueOf(0));

        // title
        JLabel title = new JLabel();
        title.setBounds(480, 90, 960, 180);
        title.setIcon(titleImage);
        this.add(title, Integer.valueOf(1));

        // start button
        JButton startButton  = new JButton();
        startButton.setBounds(720, 450, 480, 90);
        startButton.setActionCommand("start");
        startButton.addActionListener(this);
        startButton.setBorderPainted(false);
        startButton.setFocusable(false);
        startButton.setIcon(startImage);
        this.add(startButton, Integer.valueOf(1));
        
        // options button
        JButton optionsButton  = new JButton();
        optionsButton.setBounds(720, 630, 480, 90);
        optionsButton.setActionCommand("options");
        optionsButton.addActionListener(this);
        optionsButton.setBorderPainted(false);
        optionsButton.setFocusable(false);
        optionsButton.setIcon(optionsImage);
        this.add(optionsButton, Integer.valueOf(1));

        // quit button
        JButton quitButton  = new JButton();
        quitButton.setBounds(720, 810, 480, 90);
        quitButton.setActionCommand("quit");
        quitButton.addActionListener(this);
        quitButton.setOpaque(false);
        quitButton.setBorderPainted(false);
        quitButton.setFocusable(false);
        quitButton.setIcon(quitImage);
        this.add(quitButton, Integer.valueOf(1));
    }

    // when action is performed it executes that buttons purpose
    @Override
    public void actionPerformed(ActionEvent e) {
        Window parentWindow = (Window) SwingUtilities.getWindowAncestor(this);

        switch (e.getActionCommand()) {
            case "start": Debug.log("START PRESSED"); 
                parentWindow.addGame();
                parentWindow.removeMenu();
                break;
            case "options": Debug.log("OPTIONS PRESSED"); 
                parentWindow.addOptions();
                parentWindow.removeMenu();
                break;
            case "quit": Debug.log("QUIT PRESSED"); 
                System.exit(34);
                break;
            default: Debug.log("error");
        }
    }
}
