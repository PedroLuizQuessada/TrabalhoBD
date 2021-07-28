import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//TODO triggers manuais
public class BotaoAcaoRegistro implements ActionListener {
    private final DataUtil dataUtil = new DataUtil();
    private final ValidacoesUtil validacoesUtil = new ValidacoesUtil();

    private final JFrame jFrame;
    private final String sql;
    private final Connection con;
    private final JButton botao;
    private JTextField tf1, tf2, tf3, tf4, tf5;
    private JComboBox<String> cb1, cb2;
    private String indice1, indice2;

    //EXCLUIR
    public BotaoAcaoRegistro(JButton botao, JFrame jFrame, String sql, Connection con){
        this.botao = botao;
        this.jFrame = jFrame;
        this.sql = sql;
        this.con = con;
    }

    //EDITAR PISTA E CAMPEONATO
    public BotaoAcaoRegistro(JButton botao, JFrame jFrame, String sql, Connection con, JTextField tf1, JTextField tf2, JTextField tf3, String indice1) {
        this.botao = botao;
        this.jFrame = jFrame;
        this.sql = sql;
        this.con = con;
        this.tf1 = tf1;
        this.tf2 = tf2;
        this.tf3 = tf3;
        this.indice1 = indice1;
    }

    //EDITAR CORRIDA
    public BotaoAcaoRegistro(JButton botao, JFrame jFrame, String sql, Connection con, JTextField tf1, JTextField tf2, JComboBox<String> cb1, String indice1){
        this.botao = botao;
        this.jFrame = jFrame;
        this.sql = sql;
        this.con = con;
        this.tf1 = tf1;
        this.tf2 = tf2;
        this.cb1 = cb1;
        this.indice1 = indice1;
    }

    //EDITAR EMPRESA
    public BotaoAcaoRegistro(JButton botao, JFrame jFrame, String sql, Connection con, JTextField tf1, JTextField tf2, String indice1){
        this.botao = botao;
        this.jFrame = jFrame;
        this.sql = sql;
        this.con = con;
        this.tf1 = tf1;
        this.tf2 = tf2;
        this.indice1 = indice1;
    }

    //EDITAR EQUIPE
    public BotaoAcaoRegistro(JButton botao, JFrame jFrame, String sql, Connection con, JTextField tf1, JTextField tf2, JTextField tf3, JTextField tf4, String indice1){
        this.botao = botao;
        this.jFrame = jFrame;
        this.sql = sql;
        this.con = con;
        this.tf1 = tf1;
        this.tf2 = tf2;
        this.tf3 = tf3;
        this.tf4 = tf4;
        this.indice1 = indice1;
    }

    //EDITAR PILOTO
    public BotaoAcaoRegistro(JButton botao, JFrame jFrame, String sql, Connection con, JTextField tf1, JTextField tf2, JTextField tf3, JTextField tf4, JTextField tf5, String indice1){
        this.botao = botao;
        this.jFrame = jFrame;
        this.sql = sql;
        this.con = con;
        this.tf1 = tf1;
        this.tf2 = tf2;
        this.tf3 = tf3;
        this.tf4 = tf4;
        this.tf5 = tf5;
        this.indice1 = indice1;
    }

    //EDITAR CARRO
    public BotaoAcaoRegistro(JButton botao, JFrame jFrame, String sql, Connection con, JTextField tf1, JTextField tf2, JComboBox<String> cb1, String indice1, String indice2){
        this.botao = botao;
        this.jFrame = jFrame;
        this.sql = sql;
        this.con = con;
        this.tf1 = tf1;
        this.tf2 = tf2;
        this.cb1 = cb1;
        this.indice1 = indice1;
        this.indice2 = indice2;
    }

    //EDITAR PATROCINA E CORRIDASREALIZADAS
    public BotaoAcaoRegistro(JButton botao, JFrame jFrame, String sql, Connection con, JComboBox<String> cb1, JComboBox<String> cb2, String indice1, String indice2){
        this.botao = botao;
        this.jFrame = jFrame;
        this.sql = sql;
        this.con = con;
        this.cb1 = cb1;
        this.cb2 = cb2;
        this.indice1 = indice1;
        this.indice2 = indice2;
    }

    //EDITAR ATUACAOPILOTO E COMPETIDA
    public BotaoAcaoRegistro(JButton botao, JFrame jFrame, String sql, Connection con, JTextField tf1, JComboBox<String> cb1, JComboBox<String> cb2, String indice1, String indice2){
        this.botao = botao;
        this.jFrame = jFrame;
        this.sql = sql;
        this.con = con;
        this.tf1 = tf1;
        this.cb1 = cb1;
        this.cb2 = cb2;
        this.indice1 = indice1;
        this.indice2 = indice2;
    }

    //EDITAR VOLTATEMPO
    public BotaoAcaoRegistro(JButton botao, JFrame jFrame, String sql, Connection con, JTextField tf1, JTextField tf2, JComboBox<String> cb1, JComboBox<String> cb2, String indice1){
        this.botao = botao;
        this.jFrame = jFrame;
        this.sql = sql;
        this.con = con;
        this.tf1 = tf1;
        this.tf2 = tf2;
        this.cb1 = cb1;
        this.cb2 = cb2;
        this.indice1 = indice1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            PreparedStatement pStmt = con.prepareStatement(sql);

            switch (this.botao.getText()){
                case "Excluir":
                    break;

                case "Editar pista":
                    validacoesUtil.validaPista(tf1, tf2, tf3, con);

                    pStmt.setString(1, tf1.getText());
                    pStmt.setString(2, tf2.getText());
                    pStmt.setString(3, tf3.getText());
                    pStmt.setString(4, indice1);
                    break;

                case "Editar campeonato":
                    validacoesUtil.validaCampeonato(tf1, tf2, con);

                    pStmt.setString(1, dataUtil.stringParaSqlDate(tf1.getText()).toString());
                    pStmt.setString(2, tf2.getText());
                    pStmt.setString(3, tf3.getText());
                    pStmt.setString(4, indice1);
                    break;

                case "Editar corrida":
                    validacoesUtil.validaCorrida(tf1, tf2, cb1, con);

                    pStmt.setString(1, tf1.getText());
                    pStmt.setString(2, dataUtil.stringParaSqlDate(tf2.getText()).toString());
                    pStmt.setString(3, cb1.getSelectedItem().toString());
                    pStmt.setString(4, indice1);
                    break;

                case "Editar empresa":
                    validacoesUtil.validaEmpresa(tf1, tf2, con);

                    pStmt.setString(1, tf1.getText());
                    pStmt.setString(2, tf2.getText());
                    pStmt.setString(3, indice1);
                    break;

                case "Editar equipe":
                    validacoesUtil.validaEquipe(tf1, tf2, tf3, con);

                    pStmt.setString(1, tf1.getText());
                    pStmt.setString(2, tf2.getText());
                    pStmt.setString(3, tf3.getText());
                    pStmt.setString(4, tf4.getText());
                    pStmt.setString(5, indice1);
                    break;

                case "Editar piloto":
                    validacoesUtil.validaPiloto(tf1, tf2, tf4, tf5, con);

                    pStmt.setString(1, tf1.getText());
                    pStmt.setString(2, tf2.getText());
                    pStmt.setString(3, tf3.getText());
                    pStmt.setString(4, dataUtil.stringParaSqlDate(tf4.getText()).toString());
                    pStmt.setString(5, tf5.getText());
                    pStmt.setString(6, indice1);
                    break;

                case "Editar carro":
                    validacoesUtil.validaCarro(tf1, tf2, cb1, con);

                    pStmt.setString(1, tf1.getText());
                    pStmt.setString(2, tf2.getText());
                    pStmt.setString(3, cb1.getSelectedItem().toString());
                    pStmt.setString(4, indice1);
                    pStmt.setString(5, indice2);
                    break;

                case "Editar patrocinio":
                    validacoesUtil.validaPatrocinia(cb1, cb2, con);

                    pStmt.setString(1, cb1.getSelectedItem().toString());
                    pStmt.setString(2, cb2.getSelectedItem().toString());
                    pStmt.setString(3, indice1);
                    pStmt.setString(4, indice2);
                    break;

                case "Editar corrida realizada":
                    validacoesUtil.validaCorridaRealizada(cb1, cb2, con);

                    pStmt.setString(1, cb1.getSelectedItem().toString());
                    pStmt.setString(2, cb2.getSelectedItem().toString());
                    pStmt.setString(3, indice1);
                    pStmt.setString(4, indice2);
                    break;

                case "Editar atuação":
                    validacoesUtil.validaAtuacaoPiloto(tf1, cb1, cb2, con);

                    pStmt.setString(1, cb1.getSelectedItem().toString());
                    pStmt.setString(2, cb2.getSelectedItem().toString());
                    pStmt.setString(3, dataUtil.stringParaSqlDate(tf1.getText()).toString());
                    pStmt.setString(4, indice1);
                    pStmt.setString(5, indice2);
                    break;

                case "Editar competida":
                    validacoesUtil.validaCompeteCorre(tf1, cb1, cb2, con);

                    pStmt.setString(1, tf1.getText());
                    pStmt.setString(2, cb1.getSelectedItem().toString());
                    pStmt.setString(3, cb2.getSelectedItem().toString());
                    pStmt.setString(4, indice1);
                    pStmt.setString(5, indice2);
                    break;

                case "Editar volta":
                    validacoesUtil.validaVoltaTempo(cb1, cb2);

                    pStmt.setString(1, cb1.getSelectedItem().toString());
                    pStmt.setString(2, cb2.getSelectedItem().toString());
                    pStmt.setString(3, tf1.getText());
                    pStmt.setString(4, tf2.getText());
                    pStmt.setString(5, indice1);
                    break;
            }

            pStmt.executeUpdate();
            jFrame.dispose();
            JOptionPane.showMessageDialog(jFrame, "Tabela atualizada", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (CampoVazioException | CnpjInvalidoException | DataInvalidaException | PkDuplicadaException exception){
            JOptionPane.showMessageDialog(jFrame, exception.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        catch (SQLException exception){
            if(this.botao.getText().equals("Excluir")){
                JOptionPane.showMessageDialog(jFrame, "Existem registros vinculados a esse, necessário altera-los antes de deleta-lo.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(jFrame, "Problema interno.\n" + exception, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
