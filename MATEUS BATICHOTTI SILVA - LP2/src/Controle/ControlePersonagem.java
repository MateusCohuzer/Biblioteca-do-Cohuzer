package Controle;

import Entidade.Personagem;
import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;

/**
 *
 * @author radames
 */
public class ControlePersonagem {

    private List<Personagem> lista = new ArrayList<>();

    public ControlePersonagem() { //esse construtor é usado para adicionar alguns dados na lista e 

    }

    public void limparLista() {
        lista.clear();//zera a lista
    }

    public void adicionar(Personagem personagem) {
        lista.add(personagem);
    }

    public List<Personagem> listar() {
        return lista;
    }

    public Personagem buscar(int target_id) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdPersonagem() == target_id) {
                return lista.get(i);
            }
        }
        return null;

    }

    public void alterar(Personagem personagem, Personagem personagemAntigo) {
        lista.set(lista.indexOf(personagemAntigo), personagem);
    }

    public void excluir(Personagem personagem) {
        lista.remove(personagem);
    }

    public void gravarLista(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        List<String> listaDeString = new ArrayList<>();
        for (Personagem unidadeDeMedida : lista) {
            listaDeString.add(unidadeDeMedida.toString());
        }
        manipulaArquivo.salvarArquivo(caminho, listaDeString);
    }

    public void carregarDados(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        if (!manipulaArquivo.existeOArquivo(caminho)) {
            manipulaArquivo.criarArquivoVazio(caminho);
        }

        List<String> listaDeString = manipulaArquivo.abrirArquivo(caminho);
        //converter de CSV para Personagem
        Personagem personagem;
        for (String string : listaDeString) {
            String aux[] = string.split(";");
            personagem = new Personagem(Integer.valueOf(aux[0]), aux[1], aux[2], aux[3], aux[4], aux[5]);
            lista.add(personagem);
        }
    }

}
