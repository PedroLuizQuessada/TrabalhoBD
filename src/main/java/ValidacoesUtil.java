import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidacoesUtil {
    private final DataUtil dataUtil = new DataUtil();

    public void validaPista(JTextField tf1, JTextField tf2, JTextField tf3, Connection con) throws CampoVazioException, PkDuplicadaException, SQLException {
        if(tf1.getText().isEmpty() || tf2.getText().isEmpty() || tf3.getText().isEmpty()){
            throw new CampoVazioException("nome, capacidade e voltas");
        }

        String verificaPkSql = "SELECT pista_nome FROM PISTA WHERE pista_nome = ?";
        PreparedStatement pStmtAux = con.prepareStatement(verificaPkSql);
        pStmtAux.setString(1, tf1.getText());
        ResultSet rs = pStmtAux.executeQuery();

        if (rs.next()){
            throw new PkDuplicadaException("nome");
        }
    }

    public void validaCampeonato(JTextField tf1, JTextField tf2, Connection con) throws CampoVazioException, DataInvalidaException, PkDuplicadaException, SQLException{
        if(tf1.getText().isEmpty() || tf2.getText().isEmpty()){
            throw new CampoVazioException("ano e nome");
        }

        try{
            dataUtil.stringParaSqlDate(tf1.getText()).toString();
        }
        catch (Exception exception){
            throw new DataInvalidaException();
        }

        String verificaPkSql = "SELECT camp_nome FROM CAMPEONATO WHERE camp_ano = ? AND camp_nome = ?";
        PreparedStatement pStmtAux = con.prepareStatement(verificaPkSql);
        pStmtAux.setString(1, dataUtil.stringParaSqlDate(tf1.getText()).toString());
        pStmtAux.setString(2, tf2.getText());
        ResultSet rs = pStmtAux.executeQuery();

        if (rs.next()){
            throw new PkDuplicadaException("ano e nome");
        }
    }

    public void validaCorrida(JTextField tf1, JTextField tf2, JComboBox<String> cb1, Connection con) throws CampoVazioException, DataInvalidaException, PkDuplicadaException, SQLException{
        if(tf1.getText().isEmpty() || tf2.getText().isEmpty() || cb1.getSelectedItem() == null){
            throw new CampoVazioException("código, data e código do campeonato");
        }

        try{
            dataUtil.stringParaSqlDate(tf2.getText()).toString();
        }
        catch (Exception exception){
            throw new DataInvalidaException();
        }

        String verificaPkSql = "SELECT cod_corrida FROM CORRIDA WHERE cod_corrida = ?";
        PreparedStatement pStmtAux = con.prepareStatement(verificaPkSql);
        pStmtAux.setString(1, tf1.getText());
        ResultSet rs = pStmtAux.executeQuery();

        if (rs.next()){
            throw new PkDuplicadaException("código");
        }
    }

    public void validaEmpresa(JTextField tf1, JTextField tf2, Connection con) throws CampoVazioException, CnpjInvalidoException, PkDuplicadaException, SQLException{
        if(tf1.getText().isEmpty() || tf2.getText().isEmpty()){
            throw new CampoVazioException("nome e CNPJ");
        }

        if(tf2.getText().length() < 18){
            throw new CnpjInvalidoException();
        }

        String verificaPkSql = "SELECT emp_CNPJ FROM EMPRESA WHERE emp_CNPJ = ?";
        PreparedStatement pStmtAux = con.prepareStatement(verificaPkSql);
        pStmtAux.setString(1, tf2.getText());
        ResultSet rs = pStmtAux.executeQuery();

        if (rs.next()){
            throw new PkDuplicadaException("CNPJ");
        }
    }

    public void validaEquipe(JTextField tf1, JTextField tf2, JTextField tf3, Connection con) throws CampoVazioException, PkDuplicadaException, SQLException{
        if(tf1.getText().isEmpty() || tf2.getText().isEmpty() || tf3.getText().isEmpty()){
            throw new CampoVazioException("nome, código e engenheiro chefe");
        }

        String verificaPkSql = "SELECT cod_equipe FROM EQUIPE WHERE cod_equipe = ?";
        PreparedStatement pStmtAux = con.prepareStatement(verificaPkSql);
        pStmtAux.setString(1, tf2.getText());
        ResultSet rs = pStmtAux.executeQuery();

        if (rs.next()){
            throw new PkDuplicadaException("código");
        }
    }

    public void validaPiloto(JTextField tf1, JTextField tf2, JTextField tf4, JTextField tf5, Connection con) throws CampoVazioException, DataInvalidaException, PkDuplicadaException, SQLException{
        if(tf1.getText().isEmpty() || tf2.getText().isEmpty() || tf4.getText().isEmpty() || tf5.getText().isEmpty()){
            throw new CampoVazioException("código, nome, data de nascimento e nacionalidade");
        }

        try{
            dataUtil.stringParaSqlDate(tf4.getText()).toString();
        }
        catch (Exception exception){
            throw new DataInvalidaException();
        }

        String verificaPkSql = "SELECT cod_piloto FROM PILOTO WHERE cod_piloto = ?";
        PreparedStatement pStmtAux = con.prepareStatement(verificaPkSql);
        pStmtAux.setString(1, tf1.getText());
        ResultSet rs = pStmtAux.executeQuery();

        if (rs.next()){
            throw new PkDuplicadaException("código");
        }
    }

    public void validaCarro(JTextField tf1, JTextField tf2, JComboBox<String> cb1, Connection con) throws CampoVazioException, PkDuplicadaException, SQLException{
        if(tf1.getText().isEmpty() || tf2.getText().isEmpty() || cb1.getSelectedItem() == null){
            throw new CampoVazioException("ano, motor e código do piloto");
        }

        String verificaPkSql = "SELECT carro_ano FROM CARRO WHERE carro_ano = ? AND carro_motor = ?";
        PreparedStatement pStmtAux = con.prepareStatement(verificaPkSql);
        pStmtAux.setString(1, tf1.getText());
        ResultSet rs = pStmtAux.executeQuery();

        if (rs.next()){
            throw new PkDuplicadaException("ano e motor");
        }
    }

    public void validaPatrocinia(JComboBox<String> cb1, JComboBox<String> cb2, Connection con) throws CampoVazioException, PkDuplicadaException, SQLException{
        if(cb1.getSelectedItem() == null || cb2.getSelectedItem() == null){
            throw new CampoVazioException("código da equipe e CNPJ da empresa");
        }

        String verificaPkSql = "SELECT fk_cod_equipe FROM PATROCINIA WHERE fk_cod_equipe = ? AND fk_emp_CNPJ = ?";
        PreparedStatement pStmtAux = con.prepareStatement(verificaPkSql);
        pStmtAux.setString(1, cb1.getSelectedItem().toString());
        pStmtAux.setString(2, cb2.getSelectedItem().toString());
        ResultSet rs = pStmtAux.executeQuery();

        if (rs.next()){
            throw new PkDuplicadaException("código da equipe e CNPJ da empresa");
        }
    }

    public void validaAtuacaoPiloto(JTextField tf1, JComboBox<String> cb1, JComboBox<String> cb2, Connection con) throws CampoVazioException, DataInvalidaException, PkDuplicadaException, SQLException{
        if(cb1.getSelectedItem() == null || cb2.getSelectedItem() == null || tf1.getText().isEmpty()){
            throw new CampoVazioException("código do piloto, código da equipe e ano de atuação");
        }

        try{
            dataUtil.stringParaSqlDate(tf1.getText()).toString();
        }
        catch (Exception exception){
            throw new DataInvalidaException();
        }

        String verificaPkSql = "SELECT fk_cod_piloto FROM ATUACAOPILOTO WHERE fk_cod_piloto = ? AND atua_ano = ?";
        PreparedStatement pStmtAux = con.prepareStatement(verificaPkSql);
        pStmtAux.setString(1, cb1.getSelectedItem().toString());
        pStmtAux.setString(2, dataUtil.stringParaSqlDate(tf1.getText()).toString());
        ResultSet rs = pStmtAux.executeQuery();

        if (rs.next()){
            throw new PkDuplicadaException("código do piloto e ano da atuação");
        }
    }

    public void validaCompeteCorre(JTextField tf1, JComboBox<String> cb1, JComboBox<String> cb2, Connection con) throws CampoVazioException, PkDuplicadaException, SQLException{
        if(tf1.getText().isEmpty() || cb1.getSelectedItem() == null || cb2.getSelectedItem() == null){
            throw new CampoVazioException("posição de largada, código do piloto e código da corrida");
        }

        String verificaPkSql = "SELECT comp_posLargada FROM COMPETECORRE WHERE fk_cod_piloto = ? AND fk_cod_corrida = ?";
        PreparedStatement pStmtAux = con.prepareStatement(verificaPkSql);
        pStmtAux.setString(1, cb1.getSelectedItem().toString());
        pStmtAux.setString(2, cb2.getSelectedItem().toString());
        ResultSet rs = pStmtAux.executeQuery();

        if (rs.next()){
            throw new PkDuplicadaException("código do piloto e código da corrida");
        }
    }

    public void validaCorridaRealizada(JComboBox<String> cb1, JComboBox<String> cb2, Connection con) throws CampoVazioException, PkDuplicadaException, SQLException{
        if(cb1.getSelectedItem() == null || cb2.getSelectedItem() == null){
            throw new CampoVazioException("nome da pista e código da corrida");
        }

        String verificaPkSql = "SELECT fk_pista_nome FROM CORRIDASREALIZADAS WHERE fk_pista_nome = ? AND fk_cod_corrida = ?";
        PreparedStatement pStmtAux = con.prepareStatement(verificaPkSql);
        pStmtAux.setString(1, cb1.getSelectedItem().toString());
        pStmtAux.setString(2, cb2.getSelectedItem().toString());
        ResultSet rs = pStmtAux.executeQuery();

        if (rs.next()){
            throw new PkDuplicadaException("nome da pista e código da corrida");
        }
    }

    public void validaVoltaTempo(JComboBox<String> cb1, JComboBox<String> cb2) throws CampoVazioException{
        if(cb1.getSelectedItem() == null || cb2.getSelectedItem() == null){
            throw new CampoVazioException("código da corrida, código do piloto e tempo da volta");
        }
    }
}
