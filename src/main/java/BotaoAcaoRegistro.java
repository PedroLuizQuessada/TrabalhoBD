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
                    validacoesUtil.validaPista(tf1, tf2, tf3, con, indice1);

                    pStmt.setString(1, tf1.getText());
                    pStmt.setString(2, tf2.getText());
                    pStmt.setString(3, tf3.getText());
                    pStmt.setString(4, indice1);

                    pStmt.executeUpdate();
                    atualizaFks("Pista");

                    break;

                case "Editar campeonato":
                    validacoesUtil.validaCampeonato(tf1, tf2, con, indice1);

                    pStmt.setString(1, dataUtil.stringParaSqlDate(tf1.getText()).toString());
                    pStmt.setString(2, tf2.getText());
                    pStmt.setString(3, tf3.getText());
                    pStmt.setString(4, indice1);

                    pStmt.executeUpdate();
                    atualizaFks("Campeonato");

                    break;

                case "Editar corrida":
                    validacoesUtil.validaCorrida(tf1, tf2, cb1, con, indice1);

                    pStmt.setString(1, tf1.getText());
                    pStmt.setString(2, dataUtil.stringParaSqlDate(tf2.getText()).toString());
                    pStmt.setString(3, cb1.getSelectedItem().toString());
                    pStmt.setString(4, indice1);

                    pStmt.executeUpdate();
                    atualizaFks("Corrida");

                    break;

                case "Editar empresa":
                    validacoesUtil.validaEmpresa(tf1, tf2, con, indice1);

                    pStmt.setString(1, tf1.getText());
                    pStmt.setString(2, tf2.getText());
                    pStmt.setString(3, indice1);

                    pStmt.executeUpdate();
                    atualizaFks("Empresa");

                    break;

                case "Editar equipe":
                    validacoesUtil.validaEquipe(tf1, tf2, tf3, con, indice1);

                    pStmt.setString(1, tf1.getText());
                    pStmt.setString(2, tf2.getText());
                    pStmt.setString(3, tf3.getText());
                    pStmt.setString(4, tf4.getText());
                    pStmt.setString(5, indice1);

                    pStmt.executeUpdate();
                    atualizaFks("Equipe");

                    break;

                case "Editar piloto":
                    validacoesUtil.validaPiloto(tf1, tf2, tf4, tf5, con, indice1);

                    pStmt.setString(1, tf1.getText());
                    pStmt.setString(2, tf2.getText());
                    pStmt.setString(3, tf3.getText());
                    pStmt.setString(4, dataUtil.stringParaSqlDate(tf4.getText()).toString());
                    pStmt.setString(5, tf5.getText());
                    pStmt.setString(6, indice1);

                    pStmt.executeUpdate();
                    atualizaFks("Piloto");

                    break;

                case "Editar carro":
                    validacoesUtil.validaCarro(tf1, tf2, cb1, con, indice1, indice2);

                    pStmt.setString(1, tf1.getText());
                    pStmt.setString(2, tf2.getText());
                    pStmt.setString(3, cb1.getSelectedItem().toString());
                    pStmt.setString(4, indice1);
                    pStmt.setString(5, indice2);

                    pStmt.executeUpdate();

                    break;

                case "Editar patrocinio":
                    validacoesUtil.validaPatrocinia(cb1, cb2, con, indice1, indice2);

                    pStmt.setString(1, cb1.getSelectedItem().toString());
                    pStmt.setString(2, cb2.getSelectedItem().toString());
                    pStmt.setString(3, indice1);
                    pStmt.setString(4, indice2);

                    pStmt.executeUpdate();

                    break;

                case "Editar corrida realizada":
                    validacoesUtil.validaCorridaRealizada(cb1, cb2, con, indice1, indice2);

                    pStmt.setString(1, cb1.getSelectedItem().toString());
                    pStmt.setString(2, cb2.getSelectedItem().toString());
                    pStmt.setString(3, indice1);
                    pStmt.setString(4, indice2);

                    pStmt.executeUpdate();

                    break;

                case "Editar atuação":
                    validacoesUtil.validaAtuacaoPiloto(tf1, cb1, cb2, con, indice1, indice2);

                    pStmt.setString(1, cb1.getSelectedItem().toString());
                    pStmt.setString(2, cb2.getSelectedItem().toString());
                    pStmt.setString(3, dataUtil.stringParaSqlDate(tf1.getText()).toString());
                    pStmt.setString(4, indice1);
                    pStmt.setString(5, indice2);

                    pStmt.executeUpdate();

                    break;

                case "Editar competida":
                    validacoesUtil.validaCompeteCorre(tf1, cb1, cb2, con, indice1, indice2);

                    pStmt.setString(1, tf1.getText());
                    pStmt.setString(2, cb1.getSelectedItem().toString());
                    pStmt.setString(3, cb2.getSelectedItem().toString());
                    pStmt.setString(4, indice1);
                    pStmt.setString(5, indice2);

                    pStmt.executeUpdate();

                    break;

                case "Editar volta":
                    validacoesUtil.validaVoltaTempo(cb1, cb2);

                    pStmt.setString(1, cb1.getSelectedItem().toString());
                    pStmt.setString(2, cb2.getSelectedItem().toString());
                    pStmt.setString(3, tf1.getText());
                    pStmt.setString(4, tf2.getText());
                    pStmt.setString(5, indice1);

                    pStmt.executeUpdate();

                    break;
            }

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
                exception.printStackTrace();
                JOptionPane.showMessageDialog(jFrame, "Problema interno.\n" + exception, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void atualizaFks(String table) throws SQLException{
        String sqlAux;

        switch (table){
            case "Pista":
                sqlAux = "UPDATE CORRIDASREALIZADAS SET fk_pista_nome = ? WHERE fk_pista_nome = ?";
                PreparedStatement pStmtAux = con.prepareStatement(sqlAux);
                pStmtAux.setString(1, tf1.getText());
                pStmtAux.setString(2, indice1);
                pStmtAux.executeUpdate();
                break;

            case "Campeonato":
                sqlAux = "UPDATE CORRIDA SET fk_camp_codigo = ? WHERE fk_camp_codigo = ?";
                PreparedStatement pStmtAux2 = con.prepareStatement(sqlAux);
                pStmtAux2.setString(1, tf3.getText());
                pStmtAux2.setString(2, indice1);
                pStmtAux2.executeUpdate();
                break;

            case "Corrida":
                sqlAux = "UPDATE CORRIDASREALIZADAS SET fk_cod_corrida = ? WHERE fk_cod_corrida = ?";
                PreparedStatement pStmtAux3 = con.prepareStatement(sqlAux);
                pStmtAux3.setString(1, tf1.getText());
                pStmtAux3.setString(2, indice1);
                pStmtAux3.executeUpdate();

                sqlAux = "UPDATE COMPETECORRE SET fk_cod_corrida = ? WHERE fk_cod_corrida = ?";
                pStmtAux3 = con.prepareStatement(sqlAux);
                pStmtAux3.setString(1, tf1.getText());
                pStmtAux3.setString(2, indice1);
                pStmtAux3.executeUpdate();

                sqlAux = "UPDATE VOLTATEMPO SET fk_corrida_codigo = ? WHERE fk_corrida_codigo = ?";
                pStmtAux3 = con.prepareStatement(sqlAux);
                pStmtAux3.setString(1, tf1.getText());
                pStmtAux3.setString(2, indice1);
                pStmtAux3.executeUpdate();
                break;

            case "Empresa":
                sqlAux = "UPDATE PATROCINIA SET fk_emp_CNPJ = ? WHERE fk_emp_CNPJ = ?";
                PreparedStatement pStmtAux4 = con.prepareStatement(sqlAux);
                pStmtAux4.setString(1, tf2.getText());
                pStmtAux4.setString(2, indice1);
                pStmtAux4.executeUpdate();
                break;

            case "Equipe":
                sqlAux = "UPDATE PATROCINIA SET fk_cod_equipe = ? WHERE fk_cod_equipe = ?";
                PreparedStatement pStmtAux5 = con.prepareStatement(sqlAux);
                pStmtAux5.setString(1, tf2.getText());
                pStmtAux5.setString(2, indice1);
                pStmtAux5.executeUpdate();

                sqlAux = "UPDATE ATUACAOPILOTO SET fk_cod_equipe = ? WHERE fk_cod_equipe = ?";
                pStmtAux5 = con.prepareStatement(sqlAux);
                pStmtAux5.setString(1, tf2.getText());
                pStmtAux5.setString(2, indice1);
                pStmtAux5.executeUpdate();
                break;

            case "Piloto":
                sqlAux = "UPDATE CARRO SET fk_cod_piloto = ? WHERE fk_cod_piloto = ?";
                PreparedStatement pStmtAux6 = con.prepareStatement(sqlAux);
                pStmtAux6.setString(1, tf1.getText());
                pStmtAux6.setString(2, indice1);
                pStmtAux6.executeUpdate();

                sqlAux = "UPDATE ATUACAOPILOTO SET fk_cod_piloto = ? WHERE fk_cod_piloto = ?";
                pStmtAux6 = con.prepareStatement(sqlAux);
                pStmtAux6.setString(1, tf1.getText());
                pStmtAux6.setString(2, indice1);
                pStmtAux6.executeUpdate();

                sqlAux = "UPDATE COMPETECORRE SET fk_cod_piloto = ? WHERE fk_cod_piloto = ?";
                pStmtAux6 = con.prepareStatement(sqlAux);
                pStmtAux6.setString(1, tf1.getText());
                pStmtAux6.setString(2, indice1);
                pStmtAux6.executeUpdate();

                sqlAux = "UPDATE VOLTATEMPO SET fk_piloto_codigo = ? WHERE fk_piloto_codigo = ?";
                pStmtAux6 = con.prepareStatement(sqlAux);
                pStmtAux6.setString(1, tf1.getText());
                pStmtAux6.setString(2, indice1);
                pStmtAux6.executeUpdate();
                break;
        }
    }
}
