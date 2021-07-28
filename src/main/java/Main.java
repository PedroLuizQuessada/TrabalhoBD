import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Main extends JFrame implements ActionListener {
    Connection con;
    Statement stmt;

    JMenuItem inserirPista;
    JMenuItem inserirCampeonato;
    JMenuItem inserirCorrida;
    JMenuItem inserirEmpresa;
    JMenuItem inserirEquipe;
    JMenuItem inserirPiloto;
    JMenuItem inserirCarro;
    JMenuItem inserirPatrocinio;
    JMenuItem inserirAtuacaoPiloto;
    JMenuItem inserirCompeteCorre;
    JMenuItem inserirCorridaRealizada;
    JMenuItem inserirVoltaTempo;

    JMenuItem consultarPista;
    JMenuItem consultarCampeonato;
    JMenuItem consultarCorrida;
    JMenuItem consultarEmpresa;
    JMenuItem consultarEquipe;
    JMenuItem consultarPiloto;
    JMenuItem consultarCarro;
    JMenuItem consultarPatrocinio;
    JMenuItem consultarAtuacaoPiloto;
    JMenuItem consultarCompeteCorre;
    JMenuItem consultarCorridaRealizada;
    JMenuItem consultarVoltaTempo;

    public static void main(String[] args) {
        new Main();
    }

    private Main(){
        try {
            sincronizaBD();
            preparaTela();
        } catch (ClassNotFoundException exception) {
            JOptionPane.showMessageDialog(null, "O driver do banco de dados não foi encontrado.\n" + exception, "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        } catch (SQLException exception){
            JOptionPane.showMessageDialog(null, "Erro na iniciação do acesso ao banco de dados\n" + exception, "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        } catch (NullPointerException exception) {
            JOptionPane.showMessageDialog(null, "Problema interno.\n" + exception, "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sincronizaBD() throws ClassNotFoundException, SQLException, NullPointerException {
        Class.forName("org.hsql.jdbcDriver");
        con = DriverManager.getConnection("jdbc:HypersonicSQL:hsql://localhost:8080", "sa", "");
        stmt = con.createStatement();

        try {
            stmt.executeUpdate("CREATE TABLE PISTA" +
                    "\n(pista_nome VARCHAR(30) NOT NULL," +
                    "\npista_capacidade INTEGER NOT NULL," +
                    "\npista_voltas INTEGER NOT NULL," +
                    "\nPRIMARY KEY(pista_nome))");
        }
        catch (SQLException exception) {
            //Tabela já criada
        }

        try{
            stmt.executeUpdate("CREATE TABLE CAMPEONATO" +
                    "\n(camp_ano DATE NOT NULL," +
                    "\ncamp_nome VARCHAR(30) NOT NULL," +
                    "\ncamp_cod INTEGER NOT NULL IDENTITY," +
                    "\nPRIMARY KEY(camp_cod))");
        }
        catch (SQLException exception){
            //Tabela já criada
        }

        try{
            stmt.executeUpdate("CREATE TABLE CORRIDA" +
                    "\n(cod_corrida INTEGER NOT NULL," +
                    "\ndata_corrida DATE NOT NULL," +
                    "\nfk_camp_codigo INTEGER NOT NULL," +
                    "\nFOREIGN KEY(fk_camp_codigo) REFERENCES Campeonato(camp_cod)," +
                    "\nPRIMARY KEY(cod_corrida))");
        }
        catch (SQLException exception){
            //Tabela já criada
        }

        try{
            stmt.executeUpdate("CREATE TABLE EMPRESA" +
                    "\n(emp_nome VARCHAR(30) NOT NULL," +
                    "\nemp_CNPJ VARCHAR(20) NOT NULL," +
                    "\nPRIMARY KEY(emp_CNPJ))");
        }
        catch (SQLException exception){
            //Tabela já criada
        }

        try{
            stmt.executeUpdate("CREATE TABLE EQUIPE" +
                    "\n(equipe_nome VARCHAR(30) NOT NULL," +
                    "\ncod_equipe INTEGER NOT NULL," +
                    "\nequipe_engChefe VARCHAR(30) NOT NULL," +
                    "\nequipe_nac VARCHAR(30)," +
                    "\nPRIMARY KEY(cod_equipe))");
        }
        catch (SQLException exception){
            //Tabela já criada
        }

        try{
            stmt.executeUpdate("CREATE TABLE PILOTO" +
                    "\n(cod_piloto INTEGER NOT NULL," +
                    "\npiloto_nome VARCHAR(30) NOT NULL," +
                    "\npiloto_apelido VARCHAR(30)," +
                    "\npiloto_nasc DATE NOT NULL," +
                    "\npiloto_nac VARCHAR(30) NOT NULL," +
                    "\nPRIMARY KEY(cod_piloto))");
        }
        catch (SQLException exception){
            //Tabela já criada
        }

        try{
            stmt.executeUpdate("CREATE TABLE CARRO" +
                    "\n(carro_ano INTEGER NOT NULL," +
                    "\ncarro_motor VARCHAR(30) NOT NULL," +
                    "\nfk_cod_piloto INTEGER NOT NULL," +
                    "\nFOREIGN KEY(fk_cod_piloto) REFERENCES Piloto (cod_piloto)," +
                    "\nPRIMARY KEY(carro_ano, carro_motor))");
        }
        catch (SQLException exception){
            //Tabela já criada
        }

        try{
            stmt.executeUpdate("CREATE TABLE PATROCINIA" +
                    "\n(fk_cod_equipe INTEGER NOT NULL," +
                    "\nfk_emp_CNPJ VARCHAR(20) NOT NULL," +
                    "\nFOREIGN KEY(fk_emp_CNPJ) REFERENCES Empresa(emp_CNPJ)," +
                    "\nFOREIGN KEY(fk_cod_equipe) REFERENCES Equipe(cod_equipe)," +
                    "\nPRIMARY KEY (fk_cod_equipe, fk_emp_CNPJ))");
        }
        catch (SQLException exception){
            //Tabela já criada
        }

        try{
            stmt.executeUpdate("CREATE TABLE ATUACAOPILOTO" +
                    "\n(fk_cod_piloto INTEGER NOT NULL," +
                    "\nfk_cod_equipe INTEGER NOT NULL," +
                    "\natua_ano DATE NOT NULL," +
                    "\nFOREIGN KEY(fk_cod_equipe) REFERENCES Equipe(cod_equipe)," +
                    "\nFOREIGN KEY(fk_cod_piloto) REFERENCES Piloto(cod_piloto)," +
                    "\nPRIMARY KEY(fk_cod_piloto, atua_ano))");
        }
        catch (SQLException exception){
            //Tabela já criada
        }

        try{
            stmt.executeUpdate("CREATE TABLE COMPETECORRE" +
                    "\n(comp_posLargada INTEGER NOT NULL," +
                    "\nfk_cod_piloto INTEGER NOT NULL," +
                    "\nfk_cod_corrida INTEGER NOT NULL," +
                    "\nPRIMARY KEY(fk_cod_piloto, fk_cod_corrida)," +
                    "\nFOREIGN KEY(fk_cod_piloto) REFERENCES Piloto (cod_piloto)," +
                    "\nFOREIGN KEY(fk_cod_corrida) REFERENCES Corrida (cod_corrida))");
        }
        catch (SQLException exception){
            //Tabela já criada
        }

        try{
            stmt.executeUpdate("CREATE TABLE CORRIDASREALIZADAS" +
                    "\n(fk_pista_nome VARCHAR(30) NOT NULL," +
                    "\nfk_cod_corrida INTEGER NOT NULL," +
                    "\nPRIMARY KEY(fk_pista_nome, fk_cod_corrida)," +
                    "\nFOREIGN KEY (fk_pista_nome) REFERENCES Pista(pista_nome)," +
                    "\nFOREIGN KEY (fk_cod_corrida) REFERENCES Corrida(cod_corrida))");
        }
        catch (SQLException exception){
            //Tabela já criada
        }

        try{
            stmt.executeUpdate("CREATE TABLE VOLTATEMPO" +
                    "\n(fk_corrida_codigo INTEGER NOT NULL," +
                    "\nfk_piloto_codigo INTEGER NOT NULL," +
                    "\ncod_volta INTEGER NOT NULL IDENTITY," +
                    "\ntempo_volta REAL NOT NULL," +
                    "\nPRIMARY KEY(cod_volta)," +
                    "\nFOREIGN KEY(fk_corrida_codigo) REFERENCES Corrida(cod_corrida)," +
                    "\nFOREIGN KEY(fk_piloto_codigo) REFERENCES Piloto(cod_piloto))");
        }
        catch (SQLException exception){
            //Tabela já criada
        }

        //INSERE TUPLAS INICIAIS
        try{
            stmt.executeUpdate("INSERT INTO Pista (pista_nome, pista_capacidade, pista_voltas)VALUES ('Monza', 20000, 50)");
            stmt.executeUpdate("INSERT INTO Campeonato(camp_ano, camp_nome, camp_cod) VALUES ('2021-01-01', 'Formula 1', 1)");
            stmt.executeUpdate("INSERT INTO Corrida (cod_corrida, data_corrida, fk_camp_codigo)VALUES (1, '2021-08-08', 1)");
            stmt.executeUpdate("INSERT INTO Empresa (emp_nome, emp_CNPJ)VALUES ('Pirelli', '34.404.418/0001-86')");
            stmt.executeUpdate("INSERT INTO Equipe (equipe_nome, cod_equipe, equipe_engChefe, equipe_nac) VALUES ('Red Bull', 1, 'Christian Horner', 'Inglaterra')");
            stmt.executeUpdate("INSERT INTO Piloto (cod_piloto, piloto_nome, piloto_apelido, piloto_nasc, piloto_nac) VALUES (1, 'Max Verstappen', 'VER', '1997-09-30', 'Bélgica')");
            stmt.executeUpdate("INSERT INTO Carro (carro_ano, carro_motor, fk_cod_piloto) VALUES (2021, 'Honda', 1)");
            stmt.executeUpdate("INSERT INTO Patrocinia (fk_cod_equipe, fk_emp_CNPJ)VALUES (1, '34.404.418/0001-86')");
            stmt.executeUpdate("INSERT INTO ATUACAOPILOTO (fk_cod_piloto, fk_cod_equipe, atua_ano) VALUES (1, 1, '2021-01-01')");
            stmt.executeUpdate("INSERT INTO COMPETECORRE (comp_posLargada, fk_cod_piloto, fk_cod_corrida)VALUES (1, 1, 1)");
            stmt.executeUpdate("INSERT INTO CORRIDASREALIZADAS (fk_pista_nome, fk_cod_corrida)VALUES ('Monza', 1)");
            stmt.executeUpdate("INSERT INTO VOLTATEMPO (fk_corrida_codigo, fk_piloto_codigo, cod_volta, tempo_volta) VALUES (1, 1, 1, 81.046)");
        }
        catch (SQLException exception){
            exception.printStackTrace();
            JOptionPane.showMessageDialog(this, "Falha ao inserir tuplas iniciais.\n" + exception.getMessage(), "Falha", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void preparaTela(){
        JMenuBar jMenuBar = new JMenuBar();
        JMenu insere = new JMenu("Insere");
        jMenuBar.add(insere);
        inserirPista = new JMenuItem("Pista");
        insere.add(inserirPista);
        inserirPista.addActionListener(this);
        inserirCampeonato = new JMenuItem("Campeonato");
        insere.add(inserirCampeonato);
        inserirCampeonato.addActionListener(this);
        inserirCorrida = new JMenuItem("Corrida");
        insere.add(inserirCorrida);
        inserirCorrida.addActionListener(this);
        inserirEmpresa = new JMenuItem("Empresa");
        insere.add(inserirEmpresa);
        inserirEmpresa.addActionListener(this);
        inserirEquipe = new JMenuItem("Equipe");
        insere.add(inserirEquipe);
        inserirEquipe.addActionListener(this);
        inserirPiloto = new JMenuItem("Piloto");
        insere.add(inserirPiloto);
        inserirPiloto.addActionListener(this);
        inserirCarro = new JMenuItem("Carro");
        insere.add(inserirCarro);
        inserirCarro.addActionListener(this);
        inserirPatrocinio = new JMenuItem("Patrocínio");
        insere.add(inserirPatrocinio);
        inserirPatrocinio.addActionListener(this);
        inserirAtuacaoPiloto = new JMenuItem("Atuação de piloto");
        insere.add(inserirAtuacaoPiloto);
        inserirAtuacaoPiloto.addActionListener(this);
        inserirCompeteCorre = new JMenuItem("Particidação em corrida");
        insere.add(inserirCompeteCorre);
        inserirCompeteCorre.addActionListener(this);
        inserirCorridaRealizada = new JMenuItem("Corrida realizada");
        insere.add(inserirCorridaRealizada);
        inserirCorridaRealizada.addActionListener(this);
        inserirVoltaTempo = new JMenuItem("Tempo de volta");
        insere.add(inserirVoltaTempo);
        inserirVoltaTempo.addActionListener(this);

        JMenu consulta = new JMenu("Consulta");
        jMenuBar.add(consulta);
        consultarPista = new JMenuItem("Pista");
        consulta.add(consultarPista);
        consultarPista.addActionListener(this);
        consultarCampeonato = new JMenuItem("Campeonato");
        consulta.add(consultarCampeonato);
        consultarCampeonato.addActionListener(this);
        consultarCorrida = new JMenuItem("Corrida");
        consulta.add(consultarCorrida);
        consultarCorrida.addActionListener(this);
        consultarEmpresa = new JMenuItem("Empresa");
        consulta.add(consultarEmpresa);
        consultarEmpresa.addActionListener(this);
        consultarEquipe = new JMenuItem("Equipe");
        consulta.add(consultarEquipe);
        consultarEquipe.addActionListener(this);
        consultarPiloto = new JMenuItem("Piloto");
        consulta.add(consultarPiloto);
        consultarPiloto.addActionListener(this);
        consultarCarro = new JMenuItem("Carro");
        consulta.add(consultarCarro);
        consultarCarro.addActionListener(this);
        consultarPatrocinio = new JMenuItem("Patrocínio");
        consulta.add(consultarPatrocinio);
        consultarPatrocinio.addActionListener(this);
        consultarAtuacaoPiloto = new JMenuItem("Atuação de piloto");
        consulta.add(consultarAtuacaoPiloto);
        consultarAtuacaoPiloto.addActionListener(this);
        consultarCompeteCorre = new JMenuItem("Particidação em corrida");
        consulta.add(consultarCompeteCorre);
        consultarCompeteCorre.addActionListener(this);
        consultarCorridaRealizada = new JMenuItem("Corrida realizada");
        consulta.add(consultarCorridaRealizada);
        consultarCorridaRealizada.addActionListener(this);
        consultarVoltaTempo = new JMenuItem("Tempo de volta");
        consulta.add(consultarVoltaTempo);
        consultarVoltaTempo.addActionListener(this);

        setJMenuBar(jMenuBar);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
        pack();
        setTitle("Campeonatos de corridas de carros");
        setVisible(true);
        setResizable(false);
        setSize(750, 300);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == inserirPista){
            new Inserir(this, con, "Pista");
        }
        else if(e.getSource() == inserirCampeonato){
            new Inserir(this, con, "Campeonato");
        }
        else if(e.getSource() == inserirCorrida){
            new Inserir(this, con, "Corrida");
        }
        else if(e.getSource() == inserirEmpresa){
            new Inserir(this, con, "Empresa");
        }
        else if(e.getSource() == inserirEquipe){
            new Inserir(this, con, "Equipe");
        }
        else if(e.getSource() == inserirPiloto){
            new Inserir(this, con, "Piloto");
        }
        else if(e.getSource() == inserirCarro){
            new Inserir(this, con, "Carro");
        }
        else if(e.getSource() == inserirPatrocinio){
            new Inserir(this, con, "Patrocinia");
        }
        else if(e.getSource() == inserirAtuacaoPiloto){
            new Inserir(this, con, "AtuacaoPiloto");
        }
        else if(e.getSource() == inserirCompeteCorre){
            new Inserir(this, con, "CompeteCorre");
        }
        else if(e.getSource() == inserirCorridaRealizada){
            new Inserir(this, con, "CorridasRealizadas");
        }
        else if(e.getSource() == inserirVoltaTempo){
            new Inserir(this, con, "VoltaTempo");
        }

        else if(e.getSource() == consultarPista){
            new Consultar(this, con, "Pista");
        }
        else if(e.getSource() == consultarCampeonato){
            new Consultar(this, con, "Campeonato");
        }
        else if(e.getSource() == consultarCorrida){
            new Consultar(this, con, "Corrida");
        }
        else if(e.getSource() == consultarEmpresa){
            new Consultar(this, con, "Empresa");
        }
        else if(e.getSource() == consultarEquipe){
            new Consultar(this, con, "Equipe");
        }
        else if(e.getSource() == consultarPiloto){
            new Consultar(this, con, "Piloto");
        }
        else if(e.getSource() == consultarCarro){
            new Consultar(this, con, "Carro");
        }
        else if(e.getSource() == consultarPatrocinio){
            new Consultar(this, con, "Patrocinia");
        }
        else if(e.getSource() == consultarAtuacaoPiloto){
            new Consultar(this, con, "AtuacaoPiloto");
        }
        else if(e.getSource() == consultarCompeteCorre){
            new Consultar(this, con, "CompeteCorre");
        }
        else if(e.getSource() == consultarCorridaRealizada){
            new Consultar(this, con, "CorridasRealizadas");
        }
        else if(e.getSource() == consultarVoltaTempo){
            new Consultar(this, con, "VoltaTempo");
        }
    }
}
