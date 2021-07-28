import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Inserir extends JFrame {
    private final DataUtil dataUtil = new DataUtil();
    private final ComboboxUtil comboboxUtil = new ComboboxUtil();
    private final ValidacoesUtil validacoesUtil = new ValidacoesUtil();

    private final String sqlPista = "INSERT INTO Pista VALUES (?, ?, ?)";
    private final String sqlCampeonato = "INSERT INTO Campeonato VALUES (?, ?)";
    private final String sqlCorrida = "INSERT INTO Corrida VALUES (?, ?, ?)";
    private final String sqlEmpresa = "INSERT INTO Empresa VALUES (?, ?)";
    private final String sqlEquipe = "INSERT INTO Equipe VALUES (?, ?, ?, ?)";
    private final String sqlPiloto = "INSERT INTO Piloto VALUES (?, ?, ?, ?, ?)";
    private final String sqlCarro = "INSERT INTO Carro VALUES (?, ?, ?)";
    private final String sqlPatrocinia = "INSERT INTO Patrocinia VALUES (?, ?)";
    private final String sqlAtuacaoPiloto = "INSERT INTO ATUACAOPILOTO VALUES (?, ?, ?)";
    private final String sqlParticipacaoCorrida = "INSERT INTO COMPETECORRE VALUES (?, ?, ?)";
    private final String sqlCorridasRealizadas = "INSERT INTO CORRIDASREALIZADAS VALUES (?, ?)";
    private final String sqlVoltaTempo = "INSERT INTO VOLTATEMPO (fk_corrida_codigo, fk_piloto_codigo, tempo_volta) VALUES (?, ?, ?)";

    PreparedStatement pStmt;
    private JTextField tf1, tf2, tf3, tf4, tf5;
    private JComboBox<String> cb1, cb2;
    private final JButton inserir = new JButton("Inserir");

    public Inserir(JFrame jFrame, Connection con, String tabela){
        setLayout(new FlowLayout());

        inserir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    switch (tabela){
                        case "Pista":
                            limpaTelaPista(con);
                            break;

                        case "Campeonato":
                            limpaTelaCampeonato(con);
                            break;

                        case "Corrida":
                            limpaTelaCorrida(con);
                            break;

                        case "Empresa":
                            limpaTelaEmpresa(con);
                            break;

                        case "Equipe":
                            limpaTelaEquipe(con);
                            break;

                        case "Piloto":
                            limpaTelaPiloto(con);
                            break;

                        case "Carro":
                            limparTelaCarro(con);
                            break;

                        case "Patrocinia":
                            limparTelaPatrocinia(con);
                            break;

                        case "AtuacaoPiloto":
                            limparTelaAtuacaoPiloto(con);
                            break;

                        case "CompeteCorre":
                            limparTelaCompeteCorre(con);
                            break;

                        case "CorridasRealizadas":
                            limparTelaCorridaRealizada(con);
                            break;

                        case "VoltaTempo":
                            limparTelaVoltaTempo();
                            break;
                    }

                    pStmt.executeUpdate();
                    Inserir.super.dispose();
                    JOptionPane.showMessageDialog(jFrame, "Tabela " + tabela + " atualizada", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                }
                catch (CampoVazioException | CnpjInvalidoException | DataInvalidaException | PkDuplicadaException exception){
                    JOptionPane.showMessageDialog(jFrame, exception.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
                catch (SQLException exception) {
                    JOptionPane.showMessageDialog(jFrame, "Problema interno.\n" + exception, "Erro", JOptionPane.ERROR_MESSAGE);
                    exception.printStackTrace();
                }
            }
        });

        try {
            switch (tabela){
                case "Pista":
                    pStmt = con.prepareStatement(sqlPista);
                    preparaTelaPista();
                    break;

                case "Campeonato":
                    pStmt = con.prepareStatement(sqlCampeonato);
                    preparaTelaCampeonato();
                    break;

                case "Corrida":
                    pStmt = con.prepareStatement(sqlCorrida);
                    preparaTelaCorrida(con);
                    break;

                case "Empresa":
                    pStmt = con.prepareStatement(sqlEmpresa);
                    preparaTelaEmpresa();
                    break;

                case "Equipe":
                    pStmt = con.prepareStatement(sqlEquipe);
                    preparaTelaEquipe();
                    break;

                case "Piloto":
                    pStmt = con.prepareStatement(sqlPiloto);
                    preparaTelaPiloto();
                    break;

                case "Carro":
                    pStmt = con.prepareStatement(sqlCarro);
                    preparaTelaCarro(con);
                    break;

                case "Patrocinia":
                    pStmt = con.prepareStatement(sqlPatrocinia);
                    preparaTelaPatrocinia(con);
                    break;

                case "AtuacaoPiloto":
                    pStmt = con.prepareStatement(sqlAtuacaoPiloto);
                    preparaTelaAtuacaoPiloto(con);
                    break;

                case "CompeteCorre":
                    pStmt = con.prepareStatement(sqlParticipacaoCorrida);
                    preparaTelaParticipacaoCorrida(con);
                    break;

                case "CorridasRealizadas":
                    pStmt = con.prepareStatement(sqlCorridasRealizadas);
                    preparaTelaCorridaRealizada(con);
                    break;

                case "VoltaTempo":
                    pStmt = con.prepareStatement(sqlVoltaTempo);
                    preparaTelaVoltaTempo(con);
                    break;
            }

            setTitle(String.format("Insere na tabela %s", tabela));
            add(inserir);
            pack();
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setVisible(true);
            setResizable(false);
        }
        catch (SQLException exception){
            JOptionPane.showMessageDialog(jFrame, "Problema interno.\n" + exception, "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void preparaTelaPista(){
        add(new JLabel("Nome:"));
        add(tf1 = new JTextField(30));
        add(new JLabel("Capacidade:"));
        add(tf2 = new JTextField(5));
        tf2.addKeyListener(new FiltraNumeros());
        add(new JLabel("Número de voltas"));
        add(tf3 = new JTextField(5));
        tf3.addKeyListener(new FiltraNumeros());
    }

    private void preparaTelaCampeonato(){
        add(new JLabel("Ano"));
        add(tf1 = new JTextField(10));
        tf1.addKeyListener(new FiltraData(tf1));
        add(new JLabel("Nome"));
        add(tf2 = new JTextField(30));
    }

    private void preparaTelaCorrida(Connection con) throws SQLException{
        add(new JLabel("Código"));
        add(tf1 = new JTextField(3));
        tf1.addKeyListener(new FiltraNumeros());
        add(new JLabel("Data"));
        add(tf2 = new JTextField(10));
        tf2.addKeyListener(new FiltraData(tf2));
        add(new JLabel("Código do campeonato"));
        add(cb1 = new JComboBox<>());
        comboboxUtil.populaCombobox(cb1, "SELECT camp_cod FROM Campeonato", con, false);
    }

    private void preparaTelaEmpresa(){
        add(new JLabel("Nome"));
        add(tf1 = new JTextField(30));
        add(new JLabel("CNPJ"));
        add(tf2 = new JTextField(20));
        tf2.addKeyListener(new FiltraCnpj(tf2));
    }

    private void preparaTelaEquipe(){
        add(new JLabel("Nome"));
        add(tf1 = new JTextField(30));
        add(new JLabel("Código"));
        add(tf2 = new JTextField(3));
        tf2.addKeyListener(new FiltraNumeros());
        add(new JLabel("Engenheiro chefe"));
        add(tf3 = new JTextField(30));
        add(new JLabel("Nacionalidade"));
        add(tf4 = new JTextField(30));

        setPreferredSize(new Dimension(980, 100));
    }

    private void preparaTelaPiloto(){
        add(new JLabel("Código"));
        add(tf1 = new JTextField(3));
        add(new JLabel("Nome"));
        add(tf2 = new JTextField(30));
        add(new JLabel("Apelido"));
        add(tf3 = new JTextField(30));
        add(new JLabel("Data de nascimento"));
        add(tf4 = new JTextField(10));
        tf4.addKeyListener(new FiltraData(tf4));
        add(new JLabel("Nacionalidade"));
        add(tf5 = new JTextField(30));

        setPreferredSize(new Dimension(1150, 100));
    }

    private void preparaTelaCarro(Connection con) throws SQLException{
        add(new JLabel("Ano"));
        add(tf1 = new JTextField(4));
        tf1.addKeyListener(new FiltraAno(tf1));
        add(new JLabel("Motor"));
        add(tf2 = new JTextField(30));
        add(new JLabel("Código do piloto"));
        add(cb1 = new JComboBox<>());
        comboboxUtil.populaCombobox(cb1, "SELECT cod_piloto FROM PILOTO", con, false);
    }

    private void preparaTelaPatrocinia(Connection con) throws SQLException{
        add(new JLabel("Código da equipe"));
        add(cb1 = new JComboBox<>());
        comboboxUtil.populaCombobox(cb1, "SELECT cod_equipe FROM Equipe", con, false);
        add(new JLabel("CNPJ da empresa"));
        add(cb2 = new JComboBox<>());
        comboboxUtil.populaCombobox(cb2, "SELECT emp_CNPJ FROM Empresa", con, false);
    }

    private void preparaTelaAtuacaoPiloto(Connection con) throws SQLException{
        add(new JLabel("Código do piloto"));
        add(cb1 = new JComboBox<>());
        comboboxUtil.populaCombobox(cb1, "SELECT cod_piloto FROM Piloto", con, false);
        add(new JLabel("Código da equipe"));
        add(cb2 = new JComboBox<>());
        comboboxUtil.populaCombobox(cb2, "SELECT cod_equipe FROM Equipe", con, false);
        add(new JLabel("Ano da atuação"));
        add(tf1 = new JTextField(10));
        tf1.addKeyListener(new FiltraData(tf1));
    }

    private void preparaTelaParticipacaoCorrida(Connection con) throws SQLException{
        add(new JLabel("Posição na largada"));
        add(tf1 = new JTextField(2));
        tf1.addKeyListener(new FiltraNumeros());
        add(new JLabel("Código do piloto"));
        add(cb1 = new JComboBox<>());
        comboboxUtil.populaCombobox(cb1, "SELECT cod_piloto FROM Piloto", con, false);
        add(new JLabel("Código da corrida"));
        add(cb2 = new JComboBox<>());
        comboboxUtil.populaCombobox(cb2, "SELECT cod_corrida FROM Corrida", con, false);
    }

    private void preparaTelaCorridaRealizada(Connection con) throws SQLException{
        add(new JLabel("Nome da pista"));
        add(cb1 = new JComboBox<>());
        comboboxUtil.populaCombobox(cb1, "SELECT pista_nome FROM Pista", con, false);
        add(new JLabel("Código da corrida"));
        add(cb2 = new JComboBox<>());
        comboboxUtil.populaCombobox(cb2, "SELECT cod_corrida FROM Corrida", con, false);
    }

    private void preparaTelaVoltaTempo(Connection con) throws SQLException{
        add(new JLabel("Código da corrida"));
        add(cb1 = new JComboBox<>());
        comboboxUtil.populaCombobox(cb1, "SELECT cod_corrida FROM Corrida", con, false);
        add(new JLabel("Código do piloto"));
        add(cb2 = new JComboBox<>());
        comboboxUtil.populaCombobox(cb2, "SELECT cod_piloto FROM Piloto", con, false);
        add(new JLabel("Tempo"));
        add(tf1 = new JTextField(5));
        tf1.addKeyListener(new FiltraNumeros());
    }

    private void limpaTelaPista(Connection con) throws CampoVazioException, PkDuplicadaException, SQLException{
        validacoesUtil.validaPista(tf1, tf2, tf3, con, null);

        pStmt.setString(1, tf1.getText());
        pStmt.setString(2, tf2.getText());
        pStmt.setString(3, tf3.getText());
    }

    private void limpaTelaCampeonato(Connection con) throws CampoVazioException, DataInvalidaException, PkDuplicadaException, SQLException{
        validacoesUtil.validaCampeonato(tf1, tf2, con, null);

        pStmt.setString(1, dataUtil.stringParaSqlDate(tf1.getText()).toString());
        pStmt.setString(2, tf2.getText());
    }

    private void limpaTelaCorrida(Connection con) throws CampoVazioException, DataInvalidaException, PkDuplicadaException, SQLException{
        validacoesUtil.validaCorrida(tf1, tf2, cb1, con, null);

        pStmt.setString(1, tf1.getText());
        pStmt.setString(2, dataUtil.stringParaSqlDate(tf2.getText()).toString());
        pStmt.setString(3, cb1.getSelectedItem().toString());
    }

    private void limpaTelaEmpresa(Connection con) throws CampoVazioException, CnpjInvalidoException, PkDuplicadaException, SQLException{
        validacoesUtil.validaEmpresa(tf1, tf2, con, null);

        pStmt.setString(1, tf1.getText());
        pStmt.setString(2, tf2.getText());
    }

    private void limpaTelaEquipe(Connection con) throws CampoVazioException, PkDuplicadaException, SQLException{
        validacoesUtil.validaEquipe(tf1, tf2, tf3, con, null);

        String nacionalidade;
        if(tf4.getText().isEmpty()){
            nacionalidade = "null";
        }
        else{
            nacionalidade = tf4.getText();
        }

        pStmt.setString(1, tf1.getText());
        pStmt.setString(2, tf2.getText());
        pStmt.setString(3, tf3.getText());
        pStmt.setString(4, nacionalidade);
    }

    private void limpaTelaPiloto(Connection con) throws CampoVazioException, DataInvalidaException, PkDuplicadaException, SQLException{
        validacoesUtil.validaPiloto(tf1, tf2, tf4, tf5, con, null);

        String apelido;
        if(tf3.getText().isEmpty()){
            apelido = "null";
        }
        else{
            apelido = tf3.getText();
        }

        pStmt.setString(1, tf1.getText());
        pStmt.setString(2, tf2.getText());
        pStmt.setString(3, apelido);
        pStmt.setString(4, dataUtil.stringParaSqlDate(tf4.getText()).toString());
        pStmt.setString(5, tf5.getText());
    }

    private void limparTelaCarro(Connection con) throws CampoVazioException, PkDuplicadaException, SQLException{
        validacoesUtil.validaCarro(tf1, tf2, cb1, con, null, null);

        pStmt.setString(1, tf1.getText());
        pStmt.setString(2, tf2.getText());
        pStmt.setString(3, cb1.getSelectedItem().toString());
    }

    private void limparTelaPatrocinia(Connection con) throws CampoVazioException, PkDuplicadaException, SQLException{
        validacoesUtil.validaPatrocinia(cb1, cb2, con, null, null);

        pStmt.setString(1, cb1.getSelectedItem().toString());
        pStmt.setString(2, cb2.getSelectedItem().toString());
    }

    private void limparTelaAtuacaoPiloto(Connection con) throws CampoVazioException, DataInvalidaException, PkDuplicadaException, SQLException{
        validacoesUtil.validaAtuacaoPiloto(tf1, cb1, cb2, con, null, null);

        pStmt.setString(1, cb1.getSelectedItem().toString());
        pStmt.setString(2, cb2.getSelectedItem().toString());
        pStmt.setString(3, dataUtil.stringParaSqlDate(tf1.getText()).toString());
    }

    private void limparTelaCompeteCorre(Connection con) throws CampoVazioException, PkDuplicadaException, SQLException{
        validacoesUtil.validaCompeteCorre(tf1, cb1, cb2, con, null, null);

        pStmt.setString(1, tf1.getText());
        pStmt.setString(2, cb1.getSelectedItem().toString());
        pStmt.setString(3, cb2.getSelectedItem().toString());
    }

    private void limparTelaCorridaRealizada(Connection con) throws CampoVazioException, PkDuplicadaException, SQLException{
        validacoesUtil.validaCorridaRealizada(cb1, cb2, con, null, null);

        pStmt.setString(1, cb1.getSelectedItem().toString());
        pStmt.setString(2, cb2.getSelectedItem().toString());
    }

    private void limparTelaVoltaTempo() throws CampoVazioException, SQLException{
        validacoesUtil.validaVoltaTempo(cb1, cb2);

        String tempo;
        if(tf1.getText().isEmpty()){
            tempo = "0";
        }
        else {
            tempo = tf1.getText();
        }

        pStmt.setString(1, cb1.getSelectedItem().toString());
        pStmt.setString(2, cb2.getSelectedItem().toString());
        pStmt.setString(3, tempo);
    }
}
