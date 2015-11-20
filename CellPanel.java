/*
* Handles visual representation of each dead or living  cell on the GUI display board
* Child of JPanel
*/

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;

class CellPanel extends JPanel {
    // constructors
    public CellPanel() {
        setBorder(BorderFactory.createLineBorder(Color.yellow));
        setOpaque(true);
        setBackground(Color.black);
    }
    public CellPanel(Boolean hasLife) {
        setBorder(BorderFactory.createLineBorder(Color.yellow));
        setOpaque(true);
        if (hasLife){
            setBackground(Color.red); // live cells red
        } else {
            setBackground(Color.black); // dead cells black
        }
    }    
    // set cell size
    public Dimension getPreferredSize() {
        return new Dimension(20, 20);
    }
}