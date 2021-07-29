import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

public class TelaInicial extends JFrame implements ActionListener {
    Connection con;

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

    public TelaInicial(Connection con){
        this.con = con;

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
