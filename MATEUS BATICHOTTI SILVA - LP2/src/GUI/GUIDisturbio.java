package GUI;

import Controle.ControleDisturbio;
import Controle.ControleEmpresa;
import Entidade.Disturbio;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import tools.ConverteDatas;

/**
 *
 * @author radames
 */
public class GUIDisturbio extends JDialog {

    Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JPanel pnSul = new JPanel();

    String joke = "Nunca confie em uma pessoa chamada Primeira, porque Primeiramente";

    JLabel lbPK = new JLabel("Id");
    JTextField tfPK = new JTextField(20);
    JLabel lbNomeDisturbio = new JLabel("Nome");
    JTextField tfNomeDisturbio = new JTextField(50);

    JLabel lbDesc = new JLabel("Descrição");
    JTextField tfDesc = new JTextField(100);

    JButton btBuscar = new JButton("Buscar");
    JButton btAdicionar = new JButton("Adicionar");
    JButton btSalvar = new JButton("Salvar");
    JButton btAlterar = new JButton("Alterar");
    JButton btExcluir = new JButton("Excluir");
    JButton btListar = new JButton("Listar");
    JButton btCancelar = new JButton("Cancelar");
    JButton btPiada = new JButton("Risadinhas alegram seu dia :)");

    JTextArea taPiada1 = new JTextArea();
    JTextArea taPiada2 = new JTextArea();

    ControleDisturbio controle = new ControleDisturbio();
    ControleEmpresa controleEmpresa = new ControleEmpresa();
    Disturbio disturbio = new Disturbio();
    String acao = "";

    /////////////////////////////////////////////////
    String[] colunas = new String[]{"ID Disturbio", "Nome", "Descrição"};
    String[][] dados = new String[0][colunas.length];

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    private JScrollPane scrollTabela = new JScrollPane();

    private JPanel pnAvisos = new JPanel(new GridLayout(1, 1));
    private JPanel pnListagem = new JPanel(new GridLayout(1, 1));
    private JPanel pnVazio = new JPanel(new GridLayout(6, 1));

    private CardLayout cardLayout;

    ConverteDatas converteDatas = new ConverteDatas();
    Color purple = new Color(164, 145, 211);

    public GUIDisturbio() {

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - Disturbio");

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnNorte.setBackground(purple);
        pnCentro.setBorder(BorderFactory.createLineBorder(Color.black));

        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnNorte.add(lbPK);
        pnNorte.add(tfPK);
        pnNorte.add(btBuscar);
        pnNorte.add(btAdicionar);
        pnNorte.add(btAlterar);
        pnNorte.add(btExcluir);
        pnNorte.add(btListar);
        pnNorte.add(btSalvar);
        pnNorte.add(btCancelar);
        pnNorte.add(btPiada);

        btSalvar.setVisible(false);
        btAdicionar.setVisible(false);
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
        btCancelar.setVisible(false);

        pnCentro.setLayout(new GridLayout(1, colunas.length - 1));
        pnCentro.add(lbNomeDisturbio);
        pnCentro.add(tfNomeDisturbio);
        pnCentro.add(lbDesc);
        pnCentro.add(tfDesc);
        pnCentro.add(taPiada1);
        pnCentro.add(taPiada2);

        cardLayout = new CardLayout();
        pnSul.setLayout(cardLayout);

        pnAvisos.setBackground(purple);
        pnCentro.setBackground(Color.WHITE);
        pnListagem.setBackground(purple);
        pnSul.setBackground(purple);
        pnVazio.setBackground(purple);
        pnNorte.setBackground(purple);
        btAdicionar.setBackground(Color.WHITE);
        btAlterar.setBackground(Color.WHITE);
        btBuscar.setBackground(Color.WHITE);
        btCancelar.setBackground(Color.WHITE);
        btExcluir.setBackground(Color.WHITE);
        btListar.setBackground(Color.WHITE);
        btPiada.setBackground(Color.WHITE);
        btSalvar.setBackground(Color.WHITE);

        for (int i = 0; i < 5; i++) {
            pnVazio.add(new JLabel(" "));
        }
        pnSul.add(pnVazio, "vazio");
        pnSul.add(pnAvisos, "avisos");
        pnSul.add(pnListagem, "listagem");
        tabela.setEnabled(false);

        pnAvisos.add(new JLabel("Avisos"));

        //inicia com atributos bloqueados
        tfNomeDisturbio.setEditable(false);
        tfDesc.setEditable(false);

        String caminho = "Disturbio.csv";
        //carregar dados do HD para memória RAM
        controle.carregarDados(caminho);

        taPiada1.setEditable(false);
        taPiada2.setEditable(false);

        taPiada1.setLineWrap(true);
        taPiada2.setLineWrap(true);

        btPiada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] piada = {"twopart", "Hora de Por Café", "true", "Sabe qual o país que mais transpira? A YOUguslavia\nPois ela SUA"};
                try {
                    piada = getJoke();
                } catch (ProtocolException ex) {
                    Logger.getLogger(GUIDisturbio.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(GUIDisturbio.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(GUIDisturbio.class.getName()).log(Level.SEVERE, null, ex);
                }
                taPiada1.setText("Tipo: " + piada[0] + "\nCategoria: " + piada[1] + "\nA piada é segura?" + (piada[2].equals("true") ? " SIM" : " NÃO"));
                taPiada2.setText(piada[3]);
            }
        });

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(pnSul, "avisos");
                int valor = 0;
                try {
                    valor = Integer.valueOf(tfPK.getText());

                    disturbio = controle.buscar(valor);
                    if (disturbio != null) {//achou o disturbio na lista
                        //mostrar
                        btAdicionar.setVisible(false);
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        tfNomeDisturbio.setText(disturbio.getNomeDisturbio());
                        tfDesc.setText(disturbio.getDescricaoDisturbio());

                        tfNomeDisturbio.setEditable(false);
                        tfDesc.setEditable(false);

                    } else {//não achou na lista
                        //mostrar botão incluir
                        tfNomeDisturbio.setText("");
                        tfDesc.setText("");

                        tfNomeDisturbio.setEditable(false);
                        tfDesc.setEditable(false);
                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(cp, "Erro no tipo de dados", "Erro ao buscar", JOptionPane.PLAIN_MESSAGE);
                    tfPK.selectAll();
                    tfPK.requestFocus();
                }
            }
        });

        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfNomeDisturbio.requestFocus();
                tfPK.setEnabled(false);
                tfNomeDisturbio.setEditable(true);
                tfDesc.setEditable(true);

                btAdicionar.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                acao = "adicionar";
            }
        });

        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acao.equals("adicionar")) {
                    disturbio = new Disturbio();
                }
                Disturbio disturbioAntigo = disturbio;

                disturbio.setIdDisturbio(Integer.valueOf(tfPK.getText()));
                disturbio.setNomeDisturbio(tfNomeDisturbio.getText());
                disturbio.setDescricaoDisturbio(tfDesc.getText());

                if (acao.equals("adicionar")) {
                    controle.adicionar(disturbio);
                } else {
                    controle.alterar(disturbio, disturbioAntigo);
                }
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                
                tfPK.setEnabled(true);
                tfPK.setEditable(true);
                tfPK.requestFocus();
                tfPK.setText("");
                tfNomeDisturbio.setText("");
                tfDesc.setText("");
                tfNomeDisturbio.setEditable(false);
                tfDesc.setEditable(false);

            }
        });

        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btBuscar.setVisible(false);
                btAlterar.setVisible(false);
                tfNomeDisturbio.requestFocus();
                tfPK.setEditable(false);
                tfNomeDisturbio.setEditable(true);
                tfDesc.setEditable(true);

                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btListar.setVisible(false);
                btExcluir.setVisible(false);
                acao = "alterar";

            }
        });

        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int response = JOptionPane.showConfirmDialog(cp, "Confirme a exclusão?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                btExcluir.setVisible(false);
                tfPK.setEnabled(true);
                tfPK.setEditable(true);
                tfPK.requestFocus();
                tfPK.setText("");
                tfNomeDisturbio.setText("");
                tfDesc.setText("");

                btBuscar.setVisible(true);

                tfNomeDisturbio.setEditable(false);
                tfDesc.setEditable(false);

                btAlterar.setVisible(false);
                if (response == JOptionPane.YES_OPTION) {
                    controle.excluir(disturbio);
                }
            }
        });

        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Disturbio> listaDisturbio = controle.listar();
                String[] colunas = {"ID Disturbio", "Nome", "Descrição"};
                Object[][] dados = new Object[listaDisturbio.size()][colunas.length];
                String aux[];
                for (int i = 0; i < listaDisturbio.size(); i++) {
                    aux = listaDisturbio.get(i).toString().split(";");
                    for (int j = 0; j < colunas.length; j++) {
                        dados[i][j] = aux[j];
                    }
                }
                cardLayout.show(pnSul, "listagem");
                scrollTabela.setPreferredSize(tabela.getPreferredSize());
                pnListagem.add(scrollTabela);
                scrollTabela.setViewportView(tabela);
                model.setDataVector(dados, colunas);

                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                btAdicionar.setVisible(false);

            }
        });

        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btCancelar.setVisible(false);
                tfPK.setText("");
                tfPK.requestFocus();
                tfPK.setEnabled(true);
                tfPK.setEditable(true);
                tfNomeDisturbio.setText("");
                tfDesc.setText("");

                tfNomeDisturbio.setEditable(false);
                tfDesc.setEditable(false);

                btBuscar.setVisible(true);
                btListar.setVisible(true);
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);

            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //antes de sair, salvar a lista em disco
                controle.gravarLista(caminho);
                // Sai da classe
                dispose();
            }
        });

        setModal(true);
        setSize(1080, 720);
        setResizable(false);
        setLocationRelativeTo(null);//centraliza na tela
        setVisible(true);

    }

    public String[] getJoke() throws MalformedURLException, ProtocolException, IOException, ParseException {
        StringBuilder result = new StringBuilder();
        URL url = new URL("https://v2.jokeapi.dev/joke/Any");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try ( BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null;) {
                result.append(line);
            }
        }

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(result.toString());

        String joke_type = (String) json.get("type");
        String joke_category = (String) json.get("category");
        String[] joke_joke = {"", ""};
        int ditto = 1;
        boolean joke_safety = (boolean) json.get("safe");
        if (joke_type.equals("twopart")) {
            ditto++;
            joke_joke[0] = (String) json.get("setup");
            joke_joke[0] = joke_joke[0] + "\n";
            joke_joke[1] = (String) json.get("delivery");
        } else if (joke_type.equals("single")) {
            joke_joke[0] = (String) json.get("joke");
        }

        String joke_joke_ = "";
        for (int i = 0; i < ditto; i++) {
            joke_joke_ += joke_joke[i];
        }

        String[] joke_return = {joke_type, joke_category, String.valueOf(joke_safety), joke_joke_};//joke_safety need cast
        return joke_return;
    }

    public static void main(String[] args) {
        GUIDisturbio guiDisturbio = new GUIDisturbio();
    }

}
