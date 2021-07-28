import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FiltraAno implements KeyListener {
    private final JTextField campo;

    public FiltraAno(JTextField campo) {
        this.campo = campo;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(!Character.isDigit(e.getKeyChar()) || campo.getText().length() >= 4){
            e.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
