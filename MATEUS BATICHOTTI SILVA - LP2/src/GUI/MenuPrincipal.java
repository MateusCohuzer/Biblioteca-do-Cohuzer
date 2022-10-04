
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tools.Musicas;
 
/**
 *
 * @author radames
 */
public class MenuPrincipal extends JFrame {
    private Container cp;
    private JButton btPersonagem = new JButton("Personagem");
    private JButton btCriador = new JButton("Criador");
    private JButton btDisturbio = new JButton("Disturbio");
    private JButton btEmpresa = new JButton("Produtora");
    
    JPanel pnCentro = new JPanel();
    JPanel pnSul = new JPanel();
    
    JLabel lbCentro = new JLabel();
    
    Color purple = new Color(164, 145, 211);
    
    Musicas musicas = new Musicas();
    
    public MenuPrincipal() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        cp= getContentPane();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Biblioteca de Personagens do Cohuzer");
        
        cp.setLayout(new BorderLayout());
        
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);
        
        pnCentro.add(lbCentro);
        pnCentro.setBackground(Color.WHITE);
        
        pnSul.add(btPersonagem);
        pnSul.add(btCriador);
        pnSul.add(btDisturbio);
        pnSul.add(btEmpresa);
        
        pnSul.setBackground(Color.WHITE);
        btCriador.setBackground(purple);
        btDisturbio.setBackground(purple);
        btEmpresa.setBackground(purple);
        btPersonagem.setBackground(purple);
        
        try {
            ImageIcon icone = new ImageIcon(getClass().getResource("../res/bib_do_cory.jpg"));
            Image imagemAux;
            imagemAux = icone.getImage();
            icone.setImage(imagemAux.getScaledInstance(1040, 640, Image.SCALE_FAST));

            lbCentro.setIcon(icone);
        } catch (Exception e) {
            System.out.println("Erro ao carregar a imagem");
        }
        
        btPersonagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIPersonagem guiPersonagem = new GUIPersonagem();
            }
        });
        btCriador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUICriador guiCriador = new GUICriador();
            }
        });
        btDisturbio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIDisturbio guiDisturbio = new GUIDisturbio();
            }
        });
        btEmpresa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    GUIEmpresa guiEmpresa = new GUIEmpresa();
            }
        });
        
        musicas.playBGM();
        
        setSize(1080, 720);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
