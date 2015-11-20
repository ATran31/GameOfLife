/*
* Handles user input and retrieving game configuration settings.
* Creates the main user input window and attaches an event listener to the "BANG!" button.
*/

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameSettings extends JFrame{

    final String [] gameOptions = {"World Size: ", "Generations: ", "Speed: "};
    final int [] defaultSettings = {25, 100, 1000};
    final JTextField [] fieldList = new JTextField[gameOptions.length];

    public GameSettings(final GameControl controller){
        // setup the GUI layout
        setTitle("Game Settings");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        FlowLayout settingsLayout = new FlowLayout(FlowLayout.TRAILING);
        setLayout(settingsLayout);
        for (int i = 0; i < gameOptions.length; i++){
            JLabel label = new JLabel(gameOptions[i], JLabel.RIGHT);
            add(label);
            fieldList[i] = new JTextField(String.valueOf(defaultSettings[i]), 10);
            label.setLabelFor(fieldList[i]);
            add(fieldList[i]);
        }
        JButton runBtn = new JButton("BANG!");
        runBtn.setToolTipText("Click to initiate the game.");

        // attach necessary event listeners
        runBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                for (int i = 0; i < gameOptions.length; i++){
                    defaultSettings[i] = Integer.parseInt(fieldList[i].getText());
                }
                System.out.printf("%d, %d, %d", defaultSettings[0], defaultSettings[1], defaultSettings[2]);
                
                controller.setWorldSize(defaultSettings[0]);
                controller.setMaxGenerations(defaultSettings[1]);
                controller.setDelay(defaultSettings[2]);
                controller.setConfigStatus(true);
                GameSettings.this.dispose();
            }
        });
        
        // pack & display
        add(runBtn);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}