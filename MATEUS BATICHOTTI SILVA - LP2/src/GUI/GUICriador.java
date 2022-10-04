package GUI;

import Controle.ControleCriador;
import Controle.ControleEmpresa;
import Entidade.Criador;
import Entidade.Personagem;
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
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import tools.ConverteDatas;
import tools.DateTextField;
import tools.ManipulaArquivo;

/**
 *
 * @author radames
 */
public class GUICriador extends JDialog {

    Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JPanel pnSul = new JPanel();
    JLabel lbIdCriador = new JLabel("Id");
    JTextField tfIdCriador = new JTextField(20);

    JLabel lbNomeCriador = new JLabel("Nome");
    JTextField tfNomeCriador = new JTextField(50);
    
    JLabel lbEmpresa = new JLabel("Empresa");
    DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
    JComboBox cbEmpresa = new JComboBox(comboBoxModel);
    
    JLabel lbPais = new JLabel("País");
    String[] comboBoxModel3 = {"Afghanistan","Albania","Algeria","Andorra","Angola","Anguilla","Antigua & Barbuda","Argentina","Armenia","Aruba","Australia","Austria","Azerbaijan","Bahamas","Bahrain","Bangladesh","Barbados","Belarus","Belgium","Belize","Benin","Bermuda","Bhutan","Bolivia","Bosnia & Herzegovina","Botswana","Brazil","British Virgin Islands","Brunei","Bulgaria","Burkina Faso","Burundi","Cambodia","Cameroon","Cape Verde","Cayman Islands","Chad","Chile","China","Colombia","Congo","Cook Islands","Costa Rica","Cote D Ivoire","Croatia","Cruise Ship","Cuba","Cyprus","Czech Republic","Denmark","Djibouti","Dominica","Dominican Republic","Ecuador","Egypt","El Salvador","Equatorial Guinea","Estonia","Ethiopia","Falkland Islands","Faroe Islands","Fiji","Finland","France","French Polynesia","French West Indies","Gabon","Gambia","Georgia","Germany","Ghana","Gibraltar","Greece","Greenland","Grenada","Guam","Guatemala","Guernsey","Guinea","Guinea Bissau","Guyana","Haiti","Honduras","Hong Kong","Hungary","Iceland","India","Indonesia","Iran","Iraq","Ireland","Isle of Man","Israel","Italy","Jamaica","Japan","Jersey","Jordan","Kazakhstan","Kenya","Kuwait","Kyrgyz Republic","Laos","Latvia","Lebanon","Lesotho","Liberia","Libya","Liechtenstein","Lithuania","Luxembourg","Macau","Macedonia","Madagascar","Malawi","Malaysia","Maldives","Mali","Malta","Mauritania","Mauritius","Mexico","Moldova","Monaco","Mongolia","Montenegro","Montserrat","Morocco","Mozambique","Namibia","Nepal","Netherlands","Netherlands Antilles","New Caledonia","New Zealand","Nicaragua","Niger","Nigeria","Norway","Oman","Pakistan","Palestine","Panama","Papua New Guinea","Paraguay","Peru","Philippines","Poland","Portugal","Puerto Rico","Qatar","Reunion","Romania","Russia","Rwanda","Saint Pierre & Miquelon","Samoa","San Marino","Satellite","Saudi Arabia","Senegal","Serbia","Seychelles","Sierra Leone","Singapore","Slovakia","Slovenia","South Africa","South Korea","Spain","Sri Lanka","St Kitts & Nevis","St Lucia","St Vincent","St. Lucia","Sudan","Suriname","Swaziland","Sweden","Switzerland","Syria","Taiwan","Tajikistan","Tanzania","Thailand","Timor L'Este","Togo","Tonga","Trinidad & Tobago","Tunisia","Turkey","Turkmenistan","Turks & Caicos","Uganda","Ukraine","United Arab Emirates","United Kingdom", "United States America","Uruguay","Uzbekistan","Venezuela","Vietnam","Virgin Islands (US)","Yemen","Zambia","Zimbabwe"};
    JComboBox cbPais = new JComboBox(comboBoxModel3);
    
    JLabel lbDataAniversario = new JLabel("Data de Aniversário");
    JTextField tfDataAniversario = new DateTextField();
    
    JLabel lbPremios = new JLabel("Prêmios");
    SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 200, 1);
    JSpinner spPremios = new JSpinner(spinnerModel);

    JButton btBuscar = new JButton("Buscar");
    JButton btAdicionar = new JButton("Adicionar");
    JButton btSalvar = new JButton("Salvar");
    JButton btAlterar = new JButton("Alterar");
    JButton btExcluir = new JButton("Excluir");
    JButton btListar = new JButton("Listar");
    JButton btCancelar = new JButton("Cancelar");

    ControleCriador controle = new ControleCriador();
    ControleEmpresa controleEmpresa = new ControleEmpresa();
    Criador criador = new Criador();
    String acao = "";

    /////////////////////////////////////////////////
    String[] colunas = new String[]{"ID Criador", "Nome", "Empresa", "País", "Data de Aniversário", "Prêmios"};
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

    public GUICriador() {

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - Criador");
        
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

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnCentro.setBorder(BorderFactory.createLineBorder(Color.black));

        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnNorte.add(lbIdCriador);
        pnNorte.add(tfIdCriador);
        pnNorte.add(btBuscar);
        pnNorte.add(btAdicionar);
        pnNorte.add(btAlterar);
        pnNorte.add(btExcluir);
        pnNorte.add(btListar);
        pnNorte.add(btSalvar);
        pnNorte.add(btCancelar);

        btSalvar.setVisible(false);
        btAdicionar.setVisible(false);
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
        btCancelar.setVisible(false);

        pnCentro.setLayout(new GridLayout(5, 2));
        pnCentro.add(lbNomeCriador);
        pnCentro.add(tfNomeCriador);
        
        pnCentro.add(lbEmpresa);
        pnCentro.add(cbEmpresa);
        
        pnCentro.add(lbPais);
        pnCentro.add(cbPais);
        
        pnCentro.add(lbDataAniversario);
        pnCentro.add(tfDataAniversario);
        
        pnCentro.add(lbPremios);
        pnCentro.add(spPremios);

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

        //inicia com atributos bloqueados
        tfNomeCriador.setEditable(false);
        tfDataAniversario.setEditable(false);
        cbEmpresa.setEnabled(false);
        cbPais.setEnabled(false);
        spPremios.setEnabled(false);

        String caminho = "Criador.csv";
        //carregar dados do HD para memória RAM
        controle.carregarDados(caminho);

        //carregar dados da entidade relacionada (Personagem)
        //esses dados serão usados na FK
        List<String> umCSV = new ManipulaArquivo().abrirArquivo("Empresa.csv");
        for (String string : umCSV) {
            String[] aux = string.split(";");
            comboBoxModel.addElement(aux[1]);
        }

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(pnSul, "avisos");
                int valor = 0;
                try {
                    valor = Integer.valueOf(tfIdCriador.getText());

                    criador = controle.buscar(valor);
                    if (criador != null) {//achou o criador na lista
                        //mostrar
                        btAdicionar.setVisible(false);
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        btCancelar.setVisible(true);
                        tfNomeCriador.setText(criador.getNomeCriador());
                        tfDataAniversario.setText(converteDatas.converteDeDateParaString(criador.getDataAniversario()));
                        cbEmpresa.setSelectedItem(criador.getNomeEmpresa());
                        cbPais.setSelectedItem(criador.getPaisCriador());
                        
                        spPremios.setValue(Integer.valueOf(String.valueOf(criador.getPremios())));

                        tfNomeCriador.setEditable(false);
                        tfDataAniversario.setEditable(false);
                        cbEmpresa.setEnabled(false);
                        cbPais.setEnabled(false);
                        spPremios.setEnabled(false);

                    } else {//não achou na lista
                        //mostrar botão incluir
                        tfNomeCriador.setText("");
                        tfDataAniversario.setText("");

                        tfNomeCriador.setEditable(false);
                        tfDataAniversario.setEditable(false);
                        cbEmpresa.setEnabled(false);
                        cbPais.setEnabled(false);
                        spPremios.setEnabled(false);
                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                    }
                } catch (Exception ex) {
                     JOptionPane.showMessageDialog(cp,"Erro no tipo de dados","Erro ao buscar",JOptionPane.PLAIN_MESSAGE);
                     tfIdCriador.selectAll();
                     tfIdCriador.requestFocus();
                }
            }
        });

        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfNomeCriador.requestFocus();
                tfIdCriador.setEnabled(false);
                tfNomeCriador.setEditable(true);
                tfDataAniversario.setEditable(true);
                cbEmpresa.setEnabled(true);
                cbPais.setEnabled(true);
                spPremios.setEnabled(true);
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
                    criador = new Criador();
                }
                Criador criadorAntigo = criador;

                criador.setIdCriador(Integer.valueOf(tfIdCriador.getText()));
                criador.setNomeCriador(tfNomeCriador.getText());
                criador.setDataAniversario(converteDatas.converteDeStringParaDate(tfDataAniversario.getText()));

                criador.setPremios((int) spPremios.getValue());
                criador.setPaisCriador(String.valueOf(cbPais.getSelectedItem()));
                criador.setNomeEmpresa(String.valueOf(cbEmpresa.getSelectedItem()));
                criador.setPremios(Integer.valueOf(String.valueOf(spPremios.getValue())));

                if (acao.equals("adicionar")) {
                    controle.adicionar(criador);
                } else {
                    controle.alterar(criador, criadorAntigo);
                }
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                tfIdCriador.setEnabled(true);
                tfIdCriador.setEditable(true);
                tfIdCriador.requestFocus();

                tfIdCriador.setText("");
                tfNomeCriador.setText("");
                tfDataAniversario.setText("");
                spPremios.setValue(0);
                
                spPremios.setEnabled(false);
                cbEmpresa.setSelectedIndex(0);
                cbPais.setSelectedIndex(0);

                btBuscar.setVisible(true);
                btListar.setVisible(true);
                tfNomeCriador.setEditable(false);
                tfDataAniversario.setEditable(false);
                cbEmpresa.setEnabled(false);
                cbPais.setEnabled(false);
                spPremios.setEnabled(false);

            }
        });

        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btBuscar.setVisible(false);
                btAlterar.setVisible(false);
                tfNomeCriador.requestFocus();
                tfIdCriador.setEditable(false);
                tfNomeCriador.setEditable(true);
                tfDataAniversario.setEditable(true);
                cbEmpresa.setEnabled(true);
                cbPais.setEnabled(true);
                spPremios.setEnabled(true);

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
                tfIdCriador.setEnabled(true);
                tfIdCriador.setEditable(true);
                tfIdCriador.requestFocus();
                tfIdCriador.setText("");
                tfNomeCriador.setText("");
                tfDataAniversario.setText("");
                spPremios.setValue(0);

                spPremios.setEnabled(false);
                cbEmpresa.setSelectedIndex(0);
                cbPais.setSelectedIndex(0);
                btBuscar.setVisible(true);

                tfNomeCriador.setEditable(false);
                tfDataAniversario.setEditable(false);
                cbEmpresa.setEnabled(false);
                cbPais.setEnabled(false);
                spPremios.setEnabled(false);

                btAlterar.setVisible(false);
                if (response == JOptionPane.YES_OPTION) {
                    controle.excluir(criador);
                }
            }
        });

        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Criador> listaCriador = controle.listar();
                String[] colunas = {"ID Criador", "Nome", "Empresa", "País", "Data de Aniversário", "Prêmios"};
                Object[][] dados = new Object[listaCriador.size()][colunas.length];
                String aux[];
                for (int i = 0; i < listaCriador.size(); i++) {
                    aux = listaCriador.get(i).toString().split(";");
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
                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                tfIdCriador.setText("");
                tfIdCriador.requestFocus();
                tfIdCriador.setEnabled(true);
                tfIdCriador.setEditable(true);
                tfNomeCriador.setText("");
                tfDataAniversario.setText("");
                spPremios.setValue(0);

                spPremios.setEnabled(false);
                cbEmpresa.setSelectedIndex(0);
                cbPais.setSelectedIndex(0);
                
                tfNomeCriador.setEditable(false);
                tfDataAniversario.setEditable(false);

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

    public static void main(String[] args) {
        GUICriador guiCriador = new GUICriador();
    }

}
