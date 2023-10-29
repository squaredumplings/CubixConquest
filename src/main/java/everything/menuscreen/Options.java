package everything.menuscreen;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;

import everything.top.Config;
import everything.top.Window;

public class Options extends JLayeredPane implements ActionListener {

    public Options() {

        // images from resources
        final ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/Background.png"));
        final ImageIcon titleImage = new ImageIcon(getClass().getResource("/optionsTitle.png"));
        final ImageIcon volumeImage = new ImageIcon(getClass().getResource("/VolumeSlider.png"));
        final ImageIcon backImage = new ImageIcon(getClass().getResource("/BackButton.png"));

        //panel settings
        this.setBounds(0, 0, Config.WINDOWWIDTH, Config.WINDOWHEIGHT);
        this.setBackground(Color.darkGray);
        this.setLayout(null);
        this.setVisible(true);
        int distFromLeft = 400;

        // background
        JLabel background = new JLabel();
        background.setBounds(0, 0, Config.WINDOWWIDTH, Config.WINDOWHEIGHT);
        background.setIcon(backgroundImage);
        this.add(background, Integer.valueOf(0));

        // title
        JLabel title = new JLabel();
        title.setBounds(distFromLeft - 20, 120, 480, 155);
        title.setIcon(titleImage);
        this.add(title, Integer.valueOf(1));

        // volume text
        JLabel volume = new JLabel();
        volume.setBounds(distFromLeft, 360, 120, 30);
        volume.setIcon(volumeImage);
        this.add(volume, Integer.valueOf(1));

        // volume slider
        JSlider slider = new JSlider();
        slider.setBounds(distFromLeft, 420, 600, 50);
        slider.setOpaque(false);
        slider.setMajorTickSpacing(25);
        slider.setMinorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        this.add(slider, Integer.valueOf(1));

        // radio button 1
        JRadioButton option1 = new JRadioButton("Option 1");
        option1.setBounds(distFromLeft, 530, 100, 20);
        option1.setOpaque(false);
        option1.setForeground(new Color(255, 255, 255));
        this.add(option1, Integer.valueOf(1));

        // radio button 2
        JRadioButton option2 = new JRadioButton("Option 2");
        option2.setBounds(distFromLeft, 580, 100, 20);
        option2.setOpaque(false);
        option2.setForeground(new Color(255, 255, 255));
        this.add(option2, Integer.valueOf(1));

        // radio button 3
        JRadioButton option3 = new JRadioButton("Option 3");
        option3.setBounds(distFromLeft, 630, 100, 20);
        option3.setOpaque(false);
        option3.setForeground(new Color(255, 255, 255));
        this.add(option3, Integer.valueOf(1));

        // radio button 4
        JRadioButton option4 = new JRadioButton("Option 4");
        option4.setBounds(distFromLeft, 680, 100, 20);
        option4.setOpaque(false);
        option4.setForeground(new Color(255, 255, 255));
        this.add(option4, Integer.valueOf(1));

        // radio button 4
        JRadioButton option5 = new JRadioButton("Option 5");
        option5.setBounds(distFromLeft, 730, 100, 20);
        option5.setOpaque(false);
        option5.setForeground(new Color(255, 255, 255));
        this.add(option5, Integer.valueOf(1));


        // back button
        JButton backButton = new JButton();
        backButton.setBounds(distFromLeft, 850, 130, 55);
        backButton.setActionCommand("back");
        backButton.addActionListener(this);
        backButton.setOpaque(false);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusable(false);
        backButton.setIcon(backImage);
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
