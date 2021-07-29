import javax.swing.*;
import java.sql.*;

public class Main {
    private Connection con;
    private Statement stmt;

    public static void main(String[] args) {
        new Main();
    }

    private Main(){
        try {
            sincronizaBD();
            new TelaLogin(con);
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
            stmt.executeUpdate("CREATE TABLE USUARIOS" +
                    "(login VARCHAR(30) NOT NULL," +
                    "\nsenha VARCHAR(30) NOT NULL, " +
                    "\nemail VARCHAR(30), " +
                    "\ntentativasAcesso INTEGER NOT NULL," +
                    "\nPRIMARY KEY(login))");
        } catch (SQLException exception) {
            //Tabela já criada
        }

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
            stmt.executeUpdate("INSERT INTO Usuarios (login, senha, email, tentativasacesso)VALUES ('adm', 'senha', 'email@email.com', 0)");
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
            //valores já inseridos
        }
    }
}
