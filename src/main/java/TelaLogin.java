import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

public class TelaLogin extends JFrame{

    TelaLogin(Connection con) {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(20, 0, 0, 20);
        c.gridx = 0;
        c.gridy = 0;

        //PASSANDO ACESSO PARA PROFESSOR
        add(new JLabel("LOGIN PARA ACESSO: adm"));
        c.gridx++;
        add(new JLabel("SENHA PARA ACESSO: senha"));

        c.gridx = 0;
        c.gridy++;
        add(new JLabel("Login"), c);

        JTextField login = new JTextField();
        login.setColumns(20);
        c.gridx++;
        add(login, c);

        c.gridx = 0;
        c.gridy++;
        add(new JLabel("Senha"), c);

        JPasswordField senha = new JPasswordField();
        senha.setColumns(20);
        c.gridx++;
        add(senha, c);

        JButton logar = new JButton("Logar");
        logar.addActionListener(new LogarBotaoListener(this, login, senha, con));
        c.gridx ++;
        add(logar, c);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
        pack();
        setVisible(true);
        setResizable(false);
        setSize(750, 300);
    }
}
