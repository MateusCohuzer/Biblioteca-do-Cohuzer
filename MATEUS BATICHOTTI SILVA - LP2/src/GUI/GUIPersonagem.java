package GUI;

import Controle.ControlePersonagem;
import Entidade.Personagem;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import tools.ManipulaArquivo;

/**
 *
 * @author radames
 */
public class GUIPersonagem extends JDialog {

    Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JPanel pnSul = new JPanel();
    JLabel lbPK = new JLabel("ID do Personagem");
    JTextField tfSiglaPk = new JTextField(20);

    JLabel lbNome = new JLabel("Nome");
    JTextField tfNome = new JTextField(50);
    JLabel lbObra = new JLabel("Obra");
    JTextField tfObra = new JTextField(50);

    JLabel lbTipoObra = new JLabel("Tipo da Obra");
    String[] aux_model = {"Anime", "Conto", "Filme", "HQ", "Indie", "Jogo", "Livro", "Mangá", "Manhwa", "Mini Série", "Mitologia", "Música", "Novela", "OC", "Outro", "Série", "Web Comic"};
    JComboBox cbTipoObra = new JComboBox(aux_model);

    JLabel lbDisturbio = new JLabel("Disturbio");
    DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
    JComboBox cbDisturbio = new JComboBox(comboBoxModel);

    JLabel lbCriador = new JLabel("Criador");
    DefaultComboBoxModel comboBoxModel1 = new DefaultComboBoxModel();
    JComboBox cbCriador = new JComboBox(comboBoxModel1);

    JButton btBuscar = new JButton("Buscar");
    JButton btAdicionar = new JButton("Adicionar");
    JButton btSalvar = new JButton("Salvar");
    JButton btAlterar = new JButton("Alterar");
    JButton btExcluir = new JButton("Excluir");
    JButton btListar = new JButton("Listar");
    JButton btCancelar = new JButton("Cancelar");
    JButton btAssistir = new JButton("Consumir Obra");

    ControlePersonagem controlePersonagem = new ControlePersonagem();
    Personagem personagem = new Personagem();
    String acao = "";

    /////////////////////////////////////////////////
    String[] colunas = new String[]{"ID Personagem", "Nome Personagem", "Obra", "Tipo da Obra", "Disturbio", "Criador"};
    String[][] dados = new String[0][6];

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    private JScrollPane scrollTabela = new JScrollPane();

    private JPanel pnAvisos = new JPanel(new GridLayout(1, 1));
    private JPanel pnListagem = new JPanel(new GridLayout(1, 1));
    private JPanel pnVazio = new JPanel(new GridLayout(6, 1));

    private CardLayout cardLayout;
    Color purple = new Color(164, 145, 211);

    public GUIPersonagem() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - Personagem");
        
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
        btSalvar.setBackground(Color.WHITE);
        btAssistir.setBackground(Color.WHITE);

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnCentro.setBorder(BorderFactory.createLineBorder(Color.black));

        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnNorte.add(lbPK);
        pnNorte.add(tfSiglaPk);
        pnNorte.add(btBuscar);
        pnNorte.add(btAdicionar);
        pnNorte.add(btAlterar);
        pnNorte.add(btExcluir);
        pnNorte.add(btListar);
        pnNorte.add(btSalvar);
        pnNorte.add(btCancelar);
        pnNorte.add(btAssistir);

        btSalvar.setVisible(false);
        btAdicionar.setVisible(false);
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
        btCancelar.setVisible(false);
        btAssistir.setVisible(false);

        pnCentro.setLayout(new GridLayout(5, 2));
        pnCentro.add(lbNome);
        pnCentro.add(tfNome);
        pnCentro.add(lbObra);
        pnCentro.add(tfObra);
        pnCentro.add(lbTipoObra);
        pnCentro.add(cbTipoObra);
        pnCentro.add(lbDisturbio);
        pnCentro.add(cbDisturbio);
        pnCentro.add(lbCriador);
        pnCentro.add(cbCriador);

        cardLayout = new CardLayout();
        pnSul.setLayout(cardLayout);

        for (int i = 0; i < 5; i++) {
            pnVazio.add(new JLabel(" "));
        }
        pnSul.add(pnVazio, "vazio");
        pnSul.add(pnAvisos, "avisos");
        pnSul.add(pnListagem, "listagem");
        tabela.setEnabled(false);

        pnAvisos.add(new JLabel("Avisos"));

        tfNome.setEditable(false);
        tfObra.setEditable(false);
        cbCriador.setEnabled(false);
        cbDisturbio.setEnabled(false);
        cbTipoObra.setEnabled(false);

        String caminho = "Personagem.csv";
        //carregar dados do HD para memória RAM
        controlePersonagem.carregarDados(caminho);

        List<String> umCSV = new ManipulaArquivo().abrirArquivo("Disturbio.csv");
        for (String string : umCSV) {
            String[] aux = string.split(";");
            comboBoxModel.addElement(aux[0] + "-" + aux[1]);
        }

        List<String> doisCSV = new ManipulaArquivo().abrirArquivo("Criador.csv");
        for (String string : doisCSV) {
            String[] aux = string.split(";");
            comboBoxModel1.addElement(aux[0] + "-" + aux[1]);
        }

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(pnSul, "avisos");
                try {
                    personagem = controlePersonagem.buscar(Integer.valueOf(tfSiglaPk.getText()));
                    if (personagem != null) {//achou o personagem na lista
                        //mostrar
                        btAdicionar.setVisible(false);
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        btAssistir.setVisible(true);
                        tfNome.setText(personagem.getNomePersonagem());
                        tfNome.setEditable(false);
                        tfObra.setText(personagem.getObraPersonagem());
                        tfObra.setEditable(false);
                        cbDisturbio.setSelectedItem(personagem.getIdDisturbio());
                        cbCriador.setSelectedItem(personagem.getIdCriador());
                        cbTipoObra.setSelectedItem(personagem.getTipoObraPersonagem());

                    } else {//não achou na lista
                        //mostrar botão incluir
                        tfNome.setText("");
                        tfObra.setText("");
                        btAdicionar.setVisible(true);
                        tfNome.setEditable(false);
                        tfObra.setEditable(false);

                        cbDisturbio.setSelectedIndex(0);
                        cbCriador.setSelectedIndex(0);
                        cbTipoObra.setSelectedIndex(0);

                        cbCriador.setEnabled(false);
                        cbDisturbio.setEnabled(false);
                        cbTipoObra.setEnabled(false);

                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                    }
                } catch (Exception esx) {
                    JOptionPane.showMessageDialog(cp, "Erro no tipo de dados", "Erro ao buscar", JOptionPane.PLAIN_MESSAGE);
                    tfSiglaPk.selectAll();
                    tfSiglaPk.requestFocus();

                }
            }
        });

        btAssistir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] string_url_target = {"https://www.netflix.com", "https://www.hbomax.com/br/pt", "https://www.primevideo.com/?_encoding=UTF8&language=pt_br", "https://disney.com.br/disneyplus", "https://www.apple.com/br/apple-tv-plus/", "https://globoplay.globo.com"};
                Random rand = new Random();
                String randomElement = string_url_target[rand.nextInt(string_url_target.length)];

                URL url = null;
                try {
                    url = new URL(randomElement);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(GUIPersonagem.class.getName()).log(Level.SEVERE, null, ex);
                }
                openWebpage(url);
            }
        });

        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfSiglaPk.setEnabled(false);
                tfNome.setEditable(true);
                tfNome.requestFocus();

                tfObra.setEditable(true);
                cbCriador.setEnabled(true);
                cbDisturbio.setEnabled(true);
                cbTipoObra.setEnabled(true);

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
                    personagem = new Personagem();
                }
                Personagem unidadeDeMedidaAntigo = personagem;

                personagem.setIdPersonagem(Integer.valueOf(tfSiglaPk.getText()));
                personagem.setNomePersonagem(tfNome.getText());
                personagem.setObraPersonagem(tfObra.getText());
                personagem.setTipoObraPersonagem(String.valueOf(cbTipoObra.getSelectedItem()));
                personagem.setIdDisturbio(String.valueOf(cbDisturbio.getSelectedItem()));
                personagem.setIdCriador(String.valueOf(cbCriador.getSelectedItem()));

                if (acao.equals("adicionar")) {
                    controlePersonagem.adicionar(personagem);
                } else {
                    controlePersonagem.alterar(personagem, unidadeDeMedidaAntigo);
                }
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                tfSiglaPk.setEnabled(true);
                tfSiglaPk.setEditable(true);
                tfSiglaPk.requestFocus();
                tfSiglaPk.setText("");

                tfNome.setText("");
                tfObra.setText("");

                cbCriador.setSelectedIndex(0);
                cbDisturbio.setSelectedIndex(0);
                cbTipoObra.setSelectedIndex(0);

                btBuscar.setVisible(true);
                btListar.setVisible(true);
                tfNome.setEditable(false);
                tfObra.setEditable(false);
                cbCriador.setEnabled(false);
                cbDisturbio.setEnabled(false);
                cbTipoObra.setEnabled(false);
                btAssistir.setVisible(false);

            }
        });

        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btBuscar.setVisible(false);
                btAlterar.setVisible(false);
                tfSiglaPk.setEditable(false);
                tfNome.setEditable(true);
                tfObra.setEditable(true);
                cbCriador.setEnabled(true);
                cbDisturbio.setEnabled(true);
                cbTipoObra.setEnabled(true);

                tfNome.requestFocus();
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btListar.setVisible(false);
                btExcluir.setVisible(false);
                btAssistir.setVisible(false);
                acao = "alterar";

            }
        });

        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int response = JOptionPane.showConfirmDialog(cp, "Confirme a exclusão?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                btExcluir.setVisible(false);
                tfSiglaPk.setEnabled(true);
                tfSiglaPk.setEditable(true);
                tfSiglaPk.requestFocus();
                tfSiglaPk.setText("");

                tfNome.setText("");
                tfObra.setText("");
                cbCriador.setSelectedIndex(0);
                cbDisturbio.setSelectedIndex(0);
                cbTipoObra.setSelectedIndex(0);

                btBuscar.setVisible(true);
                tfNome.setEditable(false);
                tfObra.setEditable(false);
                cbCriador.setEnabled(false);
                cbDisturbio.setEnabled(false);
                cbTipoObra.setEnabled(false);
                btAssistir.setVisible(false);

                btAlterar.setVisible(false);
                if (response == JOptionPane.YES_OPTION) {
                    controlePersonagem.excluir(personagem);
                }
            }
        });

        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Personagem> listaPersonagens = controlePersonagem.listar();
                String[] colunas = new String[]{"ID Personagem", "Nome Personagem", "Obra", "Tipo da Obra", "Disturbio", "Criador"};
                Object[][] dados = new Object[listaPersonagens.size()][colunas.length];
                String aux[];
                for (int i = 0; i < listaPersonagens.size(); i++) {
                    aux = listaPersonagens.get(i).toString().split(";");
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
                tfSiglaPk.setText("");
                tfSiglaPk.requestFocus();
                tfSiglaPk.setEnabled(true);
                tfSiglaPk.setEditable(true);
                tfNome.setText("");
                tfObra.setText("");
                tfObra.setEditable(false);
                cbCriador.setEnabled(false);
                cbDisturbio.setEnabled(false);
                cbTipoObra.setEnabled(false);
                tfNome.setEditable(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btAssistir.setVisible(false);

            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //antes de sair, salvar a lista em disco
                controlePersonagem.gravarLista(caminho);
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

    public static boolean openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean openWebpage(URL url) {
        try {
            return openWebpage(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

}
