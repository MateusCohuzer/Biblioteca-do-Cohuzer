package GUI;

import Controle.ControleEmpresa;
import Entidade.Empresa;
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
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import tools.ConverteDatas;

/**
 *
 * @author radames
 */
public class GUIEmpresa extends JDialog {

    Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JPanel pnSul = new JPanel();
    JLabel lbIdEmpresa = new JLabel("Id");
    JTextField tfIdEmpresa = new JTextField(20);

    JLabel lbNomeEmpresa = new JLabel("Nome");
    JTextField tfNomeEmpresa = new JTextField(50);

    JLabel lbTemCNPJ = new JLabel("A Produtora tem CNPJ?");

    JCheckBox cbSim = new JCheckBox("Sim");

    JTextField tfCNPJ = new JTextField(25);
    JPanel pnCNPJ = new JPanel();

    JButton btBuscar = new JButton("Buscar");
    JButton btAdicionar = new JButton("Adicionar");
    JButton btSalvar = new JButton("Salvar");
    JButton btAlterar = new JButton("Alterar");
    JButton btExcluir = new JButton("Excluir");
    JButton btListar = new JButton("Listar");
    JButton btCancelar = new JButton("Cancelar");

    ControleEmpresa controle = new ControleEmpresa();
    ControleEmpresa controleEmpresa = new ControleEmpresa();
    Empresa empresa = new Empresa();
    String acao = "";

    /////////////////////////////////////////////////
    String[] colunas = new String[]{"ID Produtora", "Nome", "CNPJ"};
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

    public GUIEmpresa() {

        String CNPJDefault = "__.___.___/____-__";
        
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

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - Produtora");

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnCentro.setBorder(BorderFactory.createLineBorder(Color.black));

        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnNorte.add(lbIdEmpresa);
        pnNorte.add(tfIdEmpresa);
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

        pnCentro.setLayout(new GridLayout(3, 2));
        pnCentro.add(lbNomeEmpresa);
        pnCentro.add(tfNomeEmpresa);
        pnCentro.add(lbTemCNPJ);
        pnCentro.add(pnCNPJ);

        pnCNPJ.add(cbSim);

        pnCNPJ.add(tfCNPJ);
        tfCNPJ.setEditable(false);
        tfCNPJ.setText(CNPJDefault);

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
        tfNomeEmpresa.setEditable(false);
        cbSim.setEnabled(false);

        String caminho = "Empresa.csv";
        //carregar dados do HD para memória RAM
        controle.carregarDados(caminho);

        cbSim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbSim.isSelected()) {
                    tfCNPJ.setEditable(true);
                } else if (rootPaneCheckingEnabled) {
                    tfCNPJ.setEditable(false);
                    tfCNPJ.setText(CNPJDefault);
                }
            }
        }
        );

        btBuscar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                cardLayout.show(pnSul, "avisos");
                int valor = 0;
                try {
                    valor = Integer.valueOf(tfIdEmpresa.getText());

                    empresa = controle.buscar(valor);
                    if (empresa != null) {//achou o empresa na lista
                        //mostrar
                        btAdicionar.setVisible(false);
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        tfNomeEmpresa.setText(empresa.getNomeEmpresa());

                        tfNomeEmpresa.setEditable(false);
                        cbSim.setEnabled(false);
                        cbSim.setSelected(false);
                        tfCNPJ.setEditable(false);
                        tfCNPJ.setText(CNPJDefault);
                        
                        if (!empresa.getCnpj().equals(CNPJDefault)) {
                            cbSim.setSelected(true);
                            tfCNPJ.setText(empresa.getCnpj());
                        }

                    } else {//não achou na lista
                        //mostrar botão incluir
                        tfNomeEmpresa.setText("");

                        tfNomeEmpresa.setEditable(false);
                        cbSim.setEnabled(false);
                        cbSim.setSelected(false);
                        tfCNPJ.setEditable(false);
                        tfCNPJ.setText(CNPJDefault);
                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(cp, "Erro no tipo de dados", "Erro ao buscar", JOptionPane.PLAIN_MESSAGE);
                    tfIdEmpresa.selectAll();
                    tfIdEmpresa.requestFocus();
                }
            }
        }
        );

        btAdicionar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                tfNomeEmpresa.requestFocus();
                tfIdEmpresa.setEnabled(false);
                tfNomeEmpresa.setEditable(true);
                cbSim.setEnabled(true);
                cbSim.setSelected(false);
                tfCNPJ.setEditable(false);
                btAdicionar.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                acao = "adicionar";
            }
        }
        );

        btSalvar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (acao.equals("adicionar")) {
                    empresa = new Empresa();
                }
                Empresa empresaAntigo = empresa;

                empresa.setIdEmpresa(Integer.valueOf(tfIdEmpresa.getText()));
                empresa.setNomeEmpresa(tfNomeEmpresa.getText());
                empresa.setCnpj(tfCNPJ.getText());

                if (acao.equals("adicionar")) {
                    controle.adicionar(empresa);
                } else {
                    controle.alterar(empresa, empresaAntigo);
                }
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                tfIdEmpresa.setEnabled(true);
                tfIdEmpresa.setEditable(true);
                tfIdEmpresa.requestFocus();

                tfIdEmpresa.setText("");
                tfNomeEmpresa.setText("");
                tfCNPJ.setText(CNPJDefault);
                tfCNPJ.setEditable(false);

                cbSim.setEnabled(false);
                cbSim.setSelected(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                tfNomeEmpresa.setEditable(false);

            }
        }
        );

        btAlterar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                btBuscar.setVisible(false);
                btAlterar.setVisible(false);
                tfNomeEmpresa.requestFocus();
                tfIdEmpresa.setEditable(false);
                tfNomeEmpresa.setEditable(true);
                cbSim.setEnabled(true);
                cbSim.setSelected(false);
                tfCNPJ.setEditable(false);

                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btListar.setVisible(false);
                btExcluir.setVisible(false);
                acao = "alterar";

            }
        }
        );

        btExcluir.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {

                int response = JOptionPane.showConfirmDialog(cp, "Confirme a exclusão?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                btExcluir.setVisible(false);
                tfIdEmpresa.setEnabled(true);
                tfIdEmpresa.setEditable(true);
                tfIdEmpresa.requestFocus();
                tfIdEmpresa.setText("");
                tfNomeEmpresa.setText("");
                tfCNPJ.setText(CNPJDefault);
                tfCNPJ.setEditable(false);

                cbSim.setEnabled(false);
                cbSim.setSelected(false);
                btBuscar.setVisible(true);

                tfNomeEmpresa.setEditable(false);

                btAlterar.setVisible(false);
                if (response == JOptionPane.YES_OPTION) {
                    controle.excluir(empresa);
                }
            }
        }
        );

        btListar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                List<Empresa> listaEmpresa = controle.listar();
                String[] colunas = {"ID Produtora", "Nome", "CNPJ"};
                Object[][] dados = new Object[listaEmpresa.size()][colunas.length];
                String aux[];
                for (int i = 0; i < listaEmpresa.size(); i++) {
                    aux = listaEmpresa.get(i).toString().split(";");
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
        }
        );

        btCancelar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                btCancelar.setVisible(false);
                tfIdEmpresa.setText("");
                tfIdEmpresa.requestFocus();
                tfIdEmpresa.setEnabled(true);
                tfIdEmpresa.setEditable(true);
                tfNomeEmpresa.setText("");
                tfCNPJ.setText(CNPJDefault);
                tfCNPJ.setEditable(false);

                cbSim.setEnabled(false);
                cbSim.setSelected(false);
                tfNomeEmpresa.setEditable(false);

                btBuscar.setVisible(true);
                btListar.setVisible(true);
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);

            }
        }
        );

        addWindowListener(
                new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e
            ) {
                //antes de sair, salvar a lista em disco
                controle.gravarLista(caminho);
                // Sai da classe
                dispose();
            }
        }
        );

        setModal(
                true);
        setSize(
                720, 480);
        setLocationRelativeTo(
                null);//centraliza na tela
        setVisible(
                true);
        setResizable(false);

    }

    public static void main(String[] args) {
        GUIEmpresa guiEmpresa = new GUIEmpresa();
    }

}
