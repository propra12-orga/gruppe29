
import javax.swing.JFrame;

public class Bomberman extends JFrame {

    public Bomberman() {
        add(new Board());
        setTitle("Bomberman");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

}

