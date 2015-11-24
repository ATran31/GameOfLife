/*
* Handles user input and configures game.
* Creates the main user input window and attaches an event listener to the "BANG!" button.
*/

import java.awt.FlowLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameSettings extends JFrame{

    private final String [] GAME_OPTIONS = {"World Size: ", "Generations: ", "Speed: "};
    private final int [] DEFAULT_SETTINGS = {25, 100, 1000};
    private final JTextField [] FIELD_LIST = new JTextField[GAME_OPTIONS.length];
    private OutOfRangeException worldTooSmall = new OutOfRangeException("Input value out of range.");

    public GameSettings(final GameControl controller){
        // setup the GUI layout
        setTitle("Game Settings");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        FlowLayout settingsLayout = new FlowLayout(FlowLayout.TRAILING);
        setLayout(settingsLayout);
        for (int i = 0; i < GAME_OPTIONS.length; i++){
            JLabel label = new JLabel(GAME_OPTIONS[i], JLabel.RIGHT);
            add(label);
            FIELD_LIST[i] = new JTextField(String.valueOf(DEFAULT_SETTINGS[i]), 10);
            label.setLabelFor(FIELD_LIST[i]);
            add(FIELD_LIST[i]);
        }
        JButton runBtn = new JButton("BANG!");
        runBtn.setToolTipText("Click to initiate the game.");

        // attach necessary event listeners to button
        runBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    
                    for (int i = 0; i < GAME_OPTIONS.length; i++){
                        DEFAULT_SETTINGS[i] = Integer.parseInt(FIELD_LIST[i].getText());
                    }

                    // throw exeption if world smaller than 3
                    if (DEFAULT_SETTINGS[0] < 3){
                        throw worldTooSmall;
                    }

                    controller.setWorldSize(DEFAULT_SETTINGS[0]);
                    controller.setMaxGenerations(DEFAULT_SETTINGS[1]);
                    controller.setDelay(DEFAULT_SETTINGS[2]);
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