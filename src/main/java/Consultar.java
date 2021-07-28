import javax.swing.*;
import java.awt.*;
import java.sql.*;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;

public class Consultar extends JFrame {
    private final DataUtil dataUtil = new DataUtil();
    private final ComboboxUtil comboboxUtil = new ComboboxUtil();
    private PreparedStatement pStmt;
    private final JPanel modalPanel = new JPanel();
    private final GridBagConstraints c = new GridBagConstraints();
    private JTextField tf1, tf2, tf3, tf4, tf5;
    private JComboBox<String> cb1, cb2;
    private JButton editar;
    private JButton excluir;

    public Consultar(JFrame jFrame, Connection con, String tabela){
        try {
            boolean tabelaVazia = false;
            modalPanel.setLayout(new GridBagLayout());
            c.gridx = 0;
            c.gridy = 0;
            pStmt = con.prepareStatement(String.format("SELECT * FROM %s", tabela));

            switch (tabela) {
                case "Pista":
                    cabecalhoPistas();
                    tabelaVazia = puxaInfosPistas(con);
                    break;

                case "Campeonato":
                    cabecalhoCamepeonatos();
                    tabelaVazia = puxaInfosCampeonatos(con);
                    break;

                case "Corrida":
                    cabecalhoCorridas();
                    tabelaVazia = puxaInfosCorridas(con);
                    break;

                case "Empresa":
                    cabecalhoEmpresas();
                    tabelaVazia = puxaInfosEmpresas(con);
                    break;

                case "Equipe":
                    cabecalhoEquipes();
                    tabelaVazia = puxaInfosEquipes(con);
                    break;

                case "Piloto":
                    cabecalhoPilotos();
                    tabelaVazia = puxaInfosPilotos(con);
                    break;

                case "Carro":
                    cabecalhoCarros();
                    tabelaVazia = puxaInfosCarros(con);
                    break;

                case "Patrocinia":
                    cabecalhoPatrocinia();
                    tabelaVazia = puxaInfosPatrocinia(con);
                    break;

                case "AtuacaoPiloto":
                    cabecalhoAtuacaoPiloto();
                    tabelaVazia = puxaInfosAtuacaoPiloto(con);
                    break;

                case "CompeteCorre":
                    cabecalhoParticipacaoCorrida();
                    tabelaVazia = puxaInfosParticipacaoCorrida(con);
                    break;

                case "CorridasRealizadas":
                    cabecalhoCorridasRealizadas();
                    tabelaVazia = puxaInfosCorridasRealizadas(con);
                    break;

                case "VoltaTempo":
                    cabecalhoVoltaTempo();
                    tabelaVazia = puxaInfosVoltaTempo(con);
                    break;
            }

            if(tabelaVazia){
                dispose();
                JOptionPane.showMessageDialog(jFrame, "Não foram encontrados registros para a tabela " + tabela, "Sem registros", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            JScrollPane scroll = new JScrollPane(modalPanel);
            scroll.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
            add(scroll);

            pack();
            setTitle(String.format("Consulta tabela %s", tabela));
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setVisible(true);
            setSize(900, 300);
            setResizable(false);
        }
        catch (SQLException exception){
            JOptionPane.showMessageDialog(jFrame, "Problema interno.\n" + exception, "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cabecalhoPistas(){
        c.insets = new Insets(0, 50, 0, 0);
        modalPanel.add(new JLabel("Nome"), c);

        c.gridx++;
        modalPanel.add(new JLabel("Capacidade"), c);

        c.gridx++;
        modalPanel.add(new JLabel("Voltas"), c);
    }

    private void cabecalhoCamepeonatos(){
        c.insets = new Insets(0, 50, 0, 0);
        modalPanel.add(new JLabel("Código"), c);

        c.gridx++;
        modalPanel.add(new JLabel("Ano"), c);

        c.gridx++;
        modalPanel.add(new JLabel("Nome"), c);
    }

    private void cabecalhoCorridas(){
        c.insets = new Insets(0, 50, 0, 0);
        modalPanel.add(new JLabel("Código"), c);

        c.gridx++;
        modalPanel.add(new JLabel("Data"), c);

        c.gridx++;
        modalPanel.add(new JLabel("Código do campeonato"), c);
    }

    private void cabecalhoEmpresas(){
        c.insets = new Insets(0, 50, 0, 0);
        modalPanel.add(new JLabel("Código"), c);

        c.gridx++;
        modalPanel.add(new JLabel("CNPJ"), c);
    }

    private void cabecalhoEquipes(){
        c.insets = new Insets(0, 50, 0, 0);
        modalPanel.add(new JLabel("Nome"), c);

        c.gridx++;
        modalPanel.add(new JLabel("Código"), c);

        c.gridx++;
        modalPanel.add(new JLabel("Engenheiro chefe"), c);

        c.gridx++;
        modalPanel.add(new JLabel("Nacionalidade"), c);
    }

    private void cabecalhoPilotos(){
        c.insets = new Insets(0, 50, 0, 0);
        modalPanel.add(new JLabel("Código"), c);

        c.gridx++;
        modalPanel.add(new JLabel("Nome"), c);

        c.gridx++;
        modalPanel.add(new JLabel("Apelido"), c);

        c.gridx++;
        modalPanel.add(new JLabel("Data de nascimento"), c);

        c.gridx++;
        modalPanel.add(new JLabel("Nacionalidade"), c);
    }

    private void cabecalhoCarros(){
        c.insets = new Insets(0, 50, 0, 0);
        modalPanel.add(new JLabel("Ano"), c);

        c.gridx++;
        modalPanel.add(new JLabel("Motor"), c);

        c.gridx++;
        modalPanel.add(new JLabel("Código do piloto"), c);
    }

    private void cabecalhoPatrocinia(){
        c.insets = new Insets(0, 50, 0, 0);
        modalPanel.add(new JLabel("Código da equipe"), c);

        c.gridx++;
        modalPanel.add(new JLabel("CNPJ da empresa"), c);
    }

    private void cabecalhoAtuacaoPiloto(){
        c.insets = new Insets(0, 50, 0, 0);
        modalPanel.add(new JLabel("Código do piloto"), c);

        c.gridx++;
        modalPanel.add(new JLabel("Código da equipe"), c);

        c.gridx++;
        modalPanel.add(new JLabel("Ano da atuação"), c);
    }

    private void cabecalhoParticipacaoCorrida(){
        c.insets = new Insets(0, 50, 0, 0);
        modalPanel.add(new JLabel("Posição de largada"), c);

        c.gridx++;
        modalPanel.add(new JLabel("Código do piloto"), c);

        c.gridx++;
        modalPanel.add(new JLabel("Código da corrida"), c);
    }

    private void cabecalhoCorridasRealizadas(){
        c.insets = new Insets(0, 50, 0, 0);
        modalPanel.add(new JLabel("Nome da pista"), c);

        c.gridx++;
        modalPanel.add(new JLabel("Código da corrida"), c);
    }

    private void cabecalhoVoltaTempo(){
        c.insets = new Insets(0, 50, 0, 0);
        modalPanel.add(new JLabel("Código da volta"));

        c.gridx++;
        modalPanel.add(new JLabel("Código da corrida"), c);

        c.gridx++;
        modalPanel.add(new JLabel("Código do piloto"), c);

        c.gridx++;
        modalPanel.add(new JLabel("Tempo"), c);
    }

    private boolean puxaInfosPistas(Connection con) throws SQLException {
        boolean tabelaVazia = true;
        ResultSet rs = pStmt.executeQuery();

        while (rs.next()) {
            tabelaVazia = false;

            c.gridx = 0;
            c.gridy++;

            String nome = rs.getString(1);
            tf1 = new JTextField(nome);
            tf1.setColumns(15);
            tf1.setHorizontalAlignment(JTextField.CENTER);
            modalPanel.add(tf1, c);

            c.gridx++;
            String capacidade = rs.getString(2);
            tf2 = new JTextField(capacidade);
            tf2.addKeyListener(new FiltraNumeros());
            tf2.setColumns(3);
            tf2.setHorizontalAlignment(JTextField.CENTER);
            modalPanel.add(tf2, c);

            c.gridx++;
            String voltas = rs.getString(3);
            tf3 = new JTextField(voltas);
            tf3.addKeyListener(new FiltraNumeros());
            tf3.setColumns(3);
            tf3.setHorizontalAlignment(JTextField.CENTER);
            modalPanel.add(tf3, c);

            c.gridx++;
            modalPanel.add(editar = new JButton("Editar pista"), c);
            editar.addActionListener(new BotaoAcaoRegistro(editar, this, "UPDATE PISTA SET pista_nome = ?, pista_capacidade = ?, pista_voltas = ? WHERE pista_nome = ?", con, tf1, tf2, tf3, tf1.getText()));

            c.gridx++;
            modalPanel.add(excluir = new JButton("Excluir"), c);
            excluir.addActionListener(new BotaoAcaoRegistro(excluir, this, "DELETE FROM PISTA WHERE pista_nome = '" + tf1.getText() + "'", con));
        }

        return tabelaVazia;
    }

    private boolean puxaInfosCampeonatos(Connection con) throws SQLException{
        boolean tabelaVazia = true;
        ResultSet rs = pStmt.executeQuery();

        while (rs.next()) {
            tabelaVazia = false;
            c.gridx = 0;
            c.gridy++;

            String codigo = rs.getString(3);
            tf3 = new JTextField(codigo);
            tf3.addKeyListener(new FiltraNumeros());
            tf3.setColumns(3);
            tf3.setHorizontalAlignment(JTextField.CENTER);
            modalPanel.add(tf3, c);

            c.gridx++;
            java.sql.Date ano = rs.getDate(1);
            tf1 = new JTextField(dataUtil.sqlDateParaString(ano));
            tf1.addKeyListener(new FiltraData(tf1));
            modalPanel.add(tf1, c);

            c.gridx++;
            String nome = rs.getString(2);
            tf2 = new JTextField(nome);
            tf2.setColumns(15);
            tf2.setHorizontalAlignment(JTextField.CENTER);
            modalPanel.add(tf2, c);

            c.gridx++;
            modalPanel.add(editar = new JButton("Editar campeonato"), c);
            editar.addActionListener(new BotaoAcaoRegistro(editar, this, "UPDATE CAMPEONATO SET camp_ano = ?, camp_nome = ?, camp_cod = ? WHERE camp_cod = ?", con, tf1, tf2, tf3, tf3.getText()));

            c.gridx++;
            modalPanel.add(excluir = new JButton("Excluir"), c);
            excluir.addActionListener(new BotaoAcaoRegistro(excluir, this, "DELETE FROM CAMPEONATO WHERE camp_cod = " + tf3.getText(), con));
        }

        return tabelaVazia;
    }

    private boolean puxaInfosCorridas(Connection con) throws SQLException{
        boolean tabelaVazia = true;
        ResultSet rs = pStmt.executeQuery();

        while(rs.next()){
            tabelaVazia = false;
            c.gridx = 0;
            c.gridy++;

            String codigo = rs.getString(1);
            tf1 = new JTextField(codigo);
            tf1.addKeyListener(new FiltraNumeros());
            tf1.setColumns(3);
            tf1.setHorizontalAlignment(JTextField.CENTER);
            modalPanel.add(tf1, c);

            c.gridx++;
            java.sql.Date data = rs.getDate(2);
            tf2 = new JTextField(dataUtil.sqlDateParaString(data));
            tf2.addKeyListener(new FiltraData(tf2));
            modalPanel.add(tf2, c);

            c.gridx++;
            String codCamp = rs.getString(3);
            cb1 = new JComboBox<>();
            comboboxUtil.populaCombobox(cb1, "SELECT camp_cod FROM CAMPEONATO", con, false);
            cb1.setSelectedItem(codCamp);
            modalPanel.add(cb1, c);

            c.gridx++;
            modalPanel.add(editar = new JButton("Editar corrida"), c);
            editar.addActionListener(new BotaoAcaoRegistro(editar, this, "UPDATE CORRIDA SET cod_corrida = ?, data_corrida = ?, fk_camp_codigo = ? WHERE cod_corrida = ?", con, tf1, tf2, cb1, tf1.getText()));

            c.gridx++;
            modalPanel.add(excluir = new JButton("Excluir"), c);
            excluir.addActionListener(new BotaoAcaoRegistro(excluir, this, "DELETE FROM CORRIDA WHERE cod_corrida = " + tf1.getText(), con));
        }

        return tabelaVazia;
    }

    private boolean puxaInfosEmpresas(Connection con) throws SQLException{
        boolean tabelaVazia = true;
        ResultSet rs = pStmt.executeQuery();

        while(rs.next()){
            tabelaVazia = false;
            c.gridx = 0;
            c.gridy++;

            String nome = rs.getString(1);
            tf1 = new JTextField(nome);
            tf1.setColumns(30);
            tf1.setHorizontalAlignment(JTextField.CENTER);
            modalPanel.add(tf1, c);

            c.gridx++;
            String cnpj = rs.getString(2);
            tf2 = new JTextField(cnpj);
            tf2.addKeyListener(new FiltraCnpj(tf2));
            modalPanel.add(tf2, c);

            c.gridx++;
            modalPanel.add(editar = new JButton("Editar empresa"), c);
            editar.addActionListener(new BotaoAcaoRegistro(editar, this, "UPDATE EMPRESA SET emp_nome = ?, emp_CNPJ = ? WHERE emp_CNPJ = ?", con, tf1, tf2, tf2.getText()));

            c.gridx++;
            modalPanel.add(excluir = new JButton("Excluir"), c);
            excluir.addActionListener(new BotaoAcaoRegistro(excluir, this, "DELETE FROM EMPRESA WHERE emp_CNPJ = '" + tf2.getText() + "'", con));
        }

        return tabelaVazia;
    }

    private boolean puxaInfosEquipes(Connection con) throws SQLException{
        boolean tabelaVazia = true;
        ResultSet rs = pStmt.executeQuery();

        while(rs.next()){
            tabelaVazia = false;
            c.gridx = 0;
            c.gridy++;

            String nome = rs.getString(1);
            tf1 = new JTextField(nome);
            tf1.setColumns(10);
            tf1.setHorizontalAlignment(JTextField.CENTER);
            modalPanel.add(tf1, c);

            c.gridx++;
            String codigo = rs.getString(2);
            tf2 = new JTextField(codigo);
            tf2.addKeyListener(new FiltraNumeros());
            tf2.setColumns(3);
            tf2.setHorizontalAlignment(JTextField.CENTER);
            modalPanel.add(tf2, c);

            c.gridx++;
            String engenheiroChefe = rs.getString(3);
            tf3 = new JTextField(engenheiroChefe);
            tf3.setColumns(10);
            tf3.setHorizontalAlignment(JTextField.CENTER);
            modalPanel.add(tf3, c);

            c.gridx++;
            String nacionalidade = rs.getString(4);
            tf4 = new JTextField(nacionalidade);
            tf4.setColumns(10);
            tf4.setHorizontalAlignment(JTextField.CENTER);
            modalPanel.add(tf4, c);

            c.gridx++;
            modalPanel.add(editar = new JButton("Editar equipe"), c);
            editar.addActionListener(new BotaoAcaoRegistro(editar, this, "UPDATE EQUIPE SET equipe_nome = ?, cod_equipe = ?, equipe_engChefe = ?, equipe_nac = ? WHERE cod_equipe = ?", con, tf1, tf2, tf3, tf4, tf2.getText()));

            c.gridx++;
            modalPanel.add(excluir = new JButton("Excluir"), c);
            excluir.addActionListener(new BotaoAcaoRegistro(excluir, this, "DELETE FROM EQUIPE WHERE cod_equipe = " + tf2.getText(), con));
        }

        return tabelaVazia;
    }

    private boolean puxaInfosPilotos(Connection con) throws SQLException{
        boolean tabelaVazia = true;
        ResultSet rs = pStmt.executeQuery();

        while(rs.next()){
            tabelaVazia = false;
            c.gridx = 0;
            c.gridy++;

            String codigo = rs.getString(1);
            tf1 = new JTextField(codigo);
            tf1.addKeyListener(new FiltraNumeros());
            tf1.setColumns(3);
            tf1.setHorizontalAlignment(JTextField.CENTER);
            modalPanel.add(tf1, c);

            c.gridx++;
            String nome = rs.getString(2);
            tf2 = new JTextField(nome);
            tf2.setColumns(10);
            tf2.setHorizontalAlignment(JTextField.CENTER);
            modalPanel.add(tf2, c);

            c.gridx++;
            String apelido = rs.getString(3);
            tf3 = new JTextField(apelido);
            tf3.setColumns(10);
            tf3.setHorizontalAlignment(JTextField.CENTER);
            modalPanel.add(tf3, c);

            c.gridx++;
            java.sql.Date dataNascimento = rs.getDate(4);
            tf4 = new JTextField(dataUtil.sqlDateParaString(dataNascimento));
            tf4.addKeyListener(new FiltraData(tf4));
            modalPanel.add(tf4, c);

            c.gridx++;
            String nacionalidade = rs.getString(5);
            tf5 = new JTextField(nacionalidade);
            tf5.setColumns(10);
            tf5.setHorizontalAlignment(JTextField.CENTER);
            modalPanel.add(tf5, c);

            c.gridx++;
            modalPanel.add(editar = new JButton("Editar piloto"), c);
            editar.addActionListener(new BotaoAcaoRegistro(editar, this, "UPDATE PILOTO SET cod_piloto = ?, piloto_nome = ?, piloto_apelido = ?, piloto_nasc = ?, piloto_nac = ? WHERE cod_piloto = ?", con, tf1, tf2, tf3, tf4, tf5, tf1.getText()));

            c.gridx++;
            modalPanel.add(excluir = new JButton("Excluir"), c);
            excluir.addActionListener(new BotaoAcaoRegistro(excluir, this, "DELETE FROM PILOTO WHERE cod_piloto = " + tf1.getText(), con));

            setPreferredSize(new Dimension(1150, 300));
        }

        return tabelaVazia;
    }

    private boolean puxaInfosCarros(Connection con) throws SQLException{
        boolean tabelaVazia = true;
        ResultSet rs = pStmt.executeQuery();

        while(rs.next()){
            tabelaVazia = false;
            c.gridx = 0;
            c.gridy++;

            String ano = rs.getString(1);
            tf1 = new JTextField(ano);
            tf1.addKeyListener(new FiltraAno(tf1));
            tf1.setHorizontalAlignment(JTextField.CENTER);
            tf1.setColumns(4);
            modalPanel.add(tf1, c);

            c.gridx++;
            String motor = rs.getString(2);
            tf2 = new JTextField(motor);
            tf2.setColumns(30);
            tf2.setHorizontalAlignment(JTextField.CENTER);
            modalPanel.add(tf2, c);

            c.gridx++;
            String codPiloto = rs.getString(3);
            cb1 = new JComboBox<>();
            comboboxUtil.populaCombobox(cb1, "SELECT cod_piloto FROM PILOTO", con, false);
            cb1.setSelectedItem(codPiloto);
            modalPanel.add(cb1, c);

            c.gridx++;
            modalPanel.add(editar = new JButton("Editar carro"), c);
            editar.addActionListener(new BotaoAcaoRegistro(editar, this, "UPDATE CARRO SET carro_ano = ?, carro_motor = ?, fk_cod_piloto = ? WHERE carro_ano = ? AND carro_motor = ?", con, tf1, tf2, cb1, tf1.getText(), tf2.getText()));

            c.gridx++;
            modalPanel.add(excluir = new JButton("Excluir"), c);
            excluir.addActionListener(new BotaoAcaoRegistro(excluir, this, "DELETE FROM CARRO WHERE carro_ano = " + tf1.getText() + " AND carro_motor = '" + tf2.getText() + "'", con));
        }

        return tabelaVazia;
    }

    private boolean puxaInfosPatrocinia(Connection con) throws SQLException{
        boolean tabelaVazia = true;
        ResultSet rs = pStmt.executeQuery();

        while(rs.next()){
            tabelaVazia = false;
            c.gridx = 0;
            c.gridy++;

            String codEquipe = rs.getString(1);
            cb1 = new JComboBox<>();
            comboboxUtil.populaCombobox(cb1, "SELECT cod_equipe FROM Equipe", con, false);
            cb1.setSelectedItem(codEquipe);
            modalPanel.add(cb1, c);

            c.gridx++;
            String empCnpj = rs.getString(2);
            cb2 = new JComboBox<>();
            comboboxUtil.populaCombobox(cb2, "SELECT emp_CNPJ FROM Empresa", con, false);
            cb2.setSelectedItem(empCnpj);
            modalPanel.add(cb2, c);

            c.gridx++;
            modalPanel.add(editar = new JButton("Editar patrocinio"), c);
            editar.addActionListener(new BotaoAcaoRegistro(editar, this, "UPDATE PATROCINIA SET fk_cod_equipe = ?, fk_emp_CNPJ = ? WHERE fk_cod_equipe = ? AND fk_emp_CNPJ = ?", con, cb1, cb2, codEquipe, empCnpj));

            c.gridx++;
            modalPanel.add(excluir = new JButton("Excluir"), c);
            excluir.addActionListener(new BotaoAcaoRegistro(excluir, this, "DELETE FROM PATROCINIA WHERE fk_cod_equipe = " + codEquipe + " AND fk_emp_CNPJ = '" + empCnpj + "'", con));
        }

        return tabelaVazia;
    }

    private boolean puxaInfosAtuacaoPiloto(Connection con) throws SQLException{
        boolean tabelaVazia = true;
        ResultSet rs = pStmt.executeQuery();

        while(rs.next()){
            tabelaVazia = false;
            c.gridx = 0;
            c.gridy++;

            String codPiloto = rs.getString(1);
            cb1 = new JComboBox<>();
            comboboxUtil.populaCombobox(cb1, "SELECT cod_piloto FROM Piloto", con, false);
            cb1.setSelectedItem(codPiloto);
            modalPanel.add(cb1, c);

            c.gridx++;
            String codEquipe = rs.getString(2);
            cb2 = new JComboBox<>();
            comboboxUtil.populaCombobox(cb2, "SELECT cod_equipe FROM Equipe", con, false);
            cb2.setSelectedItem(codEquipe);
            modalPanel.add(cb2, c);

            c.gridx++;
            Date dataAnoAtuacao = rs.getDate(3);
            String anoAtuacao = dataUtil.sqlDateParaString(dataAnoAtuacao);
            tf1 = new JTextField(anoAtuacao);
            tf1.addKeyListener(new FiltraData(tf1));
            modalPanel.add(tf1, c);

            c.gridx++;
            modalPanel.add(editar = new JButton("Editar atuação"), c);
            editar.addActionListener(new BotaoAcaoRegistro(editar, this, "UPDATE ATUACAOPILOTO SET fk_cod_piloto = ?, fk_cod_equipe = ?, atua_ano = ? WHERE fk_cod_piloto = ? AND atua_ano = ?", con, tf1, cb1, cb2, codPiloto, dataAnoAtuacao.toString()));

            c.gridx++;
            modalPanel.add(excluir = new JButton("Excluir"), c);
            excluir.addActionListener(new BotaoAcaoRegistro(excluir, this, "DELETE FROM ATUACAOPILOTO WHERE fk_cod_piloto = " + codPiloto + " AND atua_ano = '" + dataAnoAtuacao.toString() + "'", con));
        }

        return tabelaVazia;
    }

    private boolean puxaInfosParticipacaoCorrida(Connection con) throws SQLException{
        boolean tabelaVazia = true;
        ResultSet rs = pStmt.executeQuery();

        while(rs.next()){
            tabelaVazia = false;
            c.gridx = 0;
            c.gridy++;

            String posLargada = rs.getString(1);
            tf1 = new JTextField(posLargada);
            tf1.addKeyListener(new FiltraNumeros());
            modalPanel.add(tf1, c);

            c.gridx++;
            String codPiloto = rs.getString(2);
            cb1 = new JComboBox<>();
            comboboxUtil.populaCombobox(cb1, "SELECT cod_piloto FROM Piloto", con, false);
            cb1.setSelectedItem(codPiloto);
            modalPanel.add(cb1, c);

            c.gridx++;
            String codCorrida = rs.getString(3);
            cb2 = new JComboBox<>();
            comboboxUtil.populaCombobox(cb2, "SELECT cod_corrida FROM Corrida", con, false);
            cb2.setSelectedItem(codCorrida);
            modalPanel.add(cb2, c);

            c.gridx++;
            modalPanel.add(editar = new JButton("Editar competida"), c);
            editar.addActionListener(new BotaoAcaoRegistro(editar, this, "UPDATE COMPETECORRE SET comp_posLargada = ?, fk_cod_piloto = ?, fk_cod_corrida = ? WHERE fk_cod_piloto = ? AND fk_cod_corrida = ?", con, tf1, cb1, cb2, codPiloto, codCorrida));

            c.gridx++;
            modalPanel.add(excluir = new JButton("Excluir"), c);
            excluir.addActionListener(new BotaoAcaoRegistro(excluir, this, "DELETE FROM COMPETECORRE WHERE fk_cod_piloto = " + codPiloto + " AND fk_cod_corrida = " + codCorrida, con));
        }

        return tabelaVazia;
    }

    private boolean puxaInfosCorridasRealizadas(Connection con) throws SQLException{
        boolean tabelaVazia = true;
        ResultSet rs = pStmt.executeQuery();

        while(rs.next()){
            tabelaVazia = false;
            c.gridx = 0;
            c.gridy++;

            String nomePista = rs.getString(1);
            cb1 = new JComboBox<>();
            comboboxUtil.populaCombobox(cb1, "SELECT pista_nome FROM Pista", con, false);
            cb1.setSelectedItem(nomePista);
            modalPanel.add(cb1, c);

            c.gridx++;
            String codCorrida = rs.getString(2);
            cb2 = new JComboBox<>();
            comboboxUtil.populaCombobox(cb2, "SELECT cod_corrida FROM Corrida", con, false);
            cb2.setSelectedItem(codCorrida);
            modalPanel.add(cb2, c);

            c.gridx++;
            modalPanel.add(editar = new JButton("Editar corrida realizada"), c);
            editar.addActionListener(new BotaoAcaoRegistro(editar, this, "UPDATE CORRIDASREALIZADAS SET fk_pista_nome = ?, fk_cod_corrida = ? WHERE fk_pista_nome = ? AND fk_cod_corrida = ?", con, cb1, cb2, nomePista, codCorrida));

            c.gridx++;
            modalPanel.add(excluir = new JButton("Excluir"), c);
            excluir.addActionListener(new BotaoAcaoRegistro(excluir, this, "DELETE FROM CORRIDASREALIZADAS WHERE fk_pista_nome = '" + nomePista + "' AND fk_cod_corrida = " + codCorrida, con));
        }

        return tabelaVazia;
    }

    private boolean puxaInfosVoltaTempo(Connection con) throws SQLException{
        boolean tabelaVazia = true;
        ResultSet rs = pStmt.executeQuery();

        while(rs.next()){
            tabelaVazia = false;
            c.gridx = 0;
            c.gridy++;

            String codVolta = rs.getString(3);
            tf2 = new JTextField(codVolta);
            tf2.addKeyListener(new FiltraNumeros());
            tf2.setColumns(3);
            modalPanel.add(tf2, c);

            c.gridx++;
            String codigoCorrida = rs.getString(1);
            cb1 = new JComboBox<>();
            comboboxUtil.populaCombobox(cb1, "SELECT cod_corrida FROM Corrida", con, false);
            cb1.setSelectedItem(codigoCorrida);
            modalPanel.add(cb1, c);

            c.gridx++;
            String codigoPiloto = rs.getString(2);
            cb2 = new JComboBox<>();
            comboboxUtil.populaCombobox(cb2, "SELECT cod_piloto FROM Piloto", con, false);
            cb2.setSelectedItem(codigoPiloto);
            modalPanel.add(cb2, c);

            c.gridx++;
            String tempo = rs.getString(4);
            tf1 = new JTextField(tempo);
            tf1.addKeyListener(new FiltraNumeros());
            modalPanel.add(tf1, c);

            c.gridx++;
            modalPanel.add(editar = new JButton("Editar volta"), c);
            editar.addActionListener(new BotaoAcaoRegistro(editar, this, "UPDATE VOLTATEMPO SET fk_corrida_codigo = ?, fk_piloto_codigo = ?, cod_volta = ?, tempo_volta = ? WHERE cod_volta = ?", con, tf2, tf1, cb1, cb2, tf2.getText()));

            c.gridx++;
            modalPanel.add(excluir = new JButton("Excluir"), c);
            excluir.addActionListener(new BotaoAcaoRegistro(excluir, this, "DELETE FROM VOLTATEMPO WHERE cod_volta = " + tf2.getText(), con));
        }

        return tabelaVazia;
    }
}
