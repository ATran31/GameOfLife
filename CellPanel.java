import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;

class CellPanel extends JPanel {
    // create border
    public CellPanel() {
        setBorder(BorderFactory.createLineBorder(Color.yellow));
        setOpaque(true);
        setBackground(Color.black);
    }
    public CellPanel(String status) {
        setBorder(BorderFactory.createLineBorder(Color.yellow));
        setOpaque(true);
        if (status == "live"){
            setBackground(Color.red);
        } else {
            setBackground(Color.black);
        }
    }    
    // set cell size
    public Dimension getPreferredSize() {
        return new Dimension(20, 20);
    }
}