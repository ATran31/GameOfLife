import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameConfig extends JFrame{
    final String [] gameOptions = {"World Size: ", "Generations: ", "Speed: "};
    final int [] currentSettings = {25, 100, 1000};
    final JTextField [] fieldList = new JTextField[gameOptions.length];

    public GameConfig(final GameControl controller){   
        setTitle("Game Settings");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        FlowLayout settingsLayout = new FlowLayout(FlowLayout.TRAILING);
        setLayout(settingsLayout);
        for (int i = 0; i < gameOptions.length; i++){
            JLabel label = new JLabel(gameOptions[i], JLabel.RIGHT);
            add(label);
            fieldList[i] = new JTextField(String.valueOf(currentSettings[i]), 10);
            label.setLabelFor(fieldList[i]);
            add(fieldList[i]);
        }
        JButton runBtn = new JButton("BANG!");
        runBtn.setToolTipText("Click to initiate the game.");

        runBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                for (int i = 0; i < gameOptions.length; i++){
                    currentSettings[i] = Integer.parseInt(fieldList[i].getText());
                }
                controller.setWorldSize(currentSettings[0]);
                controller.setMaxGenerations(currentSettings[1]);
                controller.setDelay(currentSettings[2]);
                controller.setConfigStatus(true);
                GameConfig.this.dispose();
            }
        });
        
        add(runBtn);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}