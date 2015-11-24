/*
* Handles user input and configures game.
* Creates the main user input window and attaches an event listener to the "BANG!" button.
*/

import java.awt.FlowLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameSettings extends JFrame{

    final String [] gameOptions = {"World Size: ", "Generations: ", "Speed: "};
    final int [] defaultSettings = {25, 100, 1000};
    final JTextField [] fieldList = new JTextField[gameOptions.length];
    OutOfRangeException worldTooSmall = new OutOfRangeException("Input value out of range.");

    public GameSettings(final GameControl controller){
        // setup the GUI layout
        setTitle("Game Settings");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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

        // attach necessary event listeners to button
        runBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    
                    for (int i = 0; i < gameOptions.length; i++){
                        defaultSettings[i] = Integer.parseInt(fieldList[i].getText());
                    }

                    // throw exeption if world smaller than 3
                    if (defaultSettings[0] < 3){
                        throw worldTooSmall;
                    }

                    controller.setWorldSize(defaultSettings[0]);
                    controller.setMaxGenerations(defaultSettings[1]);
                    controller.setDelay(defaultSettings[2]);
                    controller.setConfigStatus(true);
                    GameSettings.this.dispose(); // get rid of the Game Settings window after the user clicks BANG

                } catch (NumberFormatException numFormatException) {
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, "Enter integer values only!", "Invalid Entry", JOptionPane.ERROR_MESSAGE);
                } catch (OutOfRangeException worldTooSmall) {
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, "World size cannot be smaller than 3!", "Invalid World Size", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // pack & display
        add(runBtn);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}