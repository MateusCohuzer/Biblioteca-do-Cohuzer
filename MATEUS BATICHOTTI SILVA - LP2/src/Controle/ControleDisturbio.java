package Controle;

import Entidade.Disturbio;
import java.util.ArrayList;
import java.util.List;
import tools.ConverteDatas;
import tools.ManipulaArquivo;

/**
 *
 * @author radames
 */
public class ControleDisturbio {

    private List<Disturbio> lista = new ArrayList<>();

    public ControleDisturbio() { //esse construtor é usado para adicionar alguns dados na lista e 

    }

    public void limparLista() {
        lista.clear();//zera a lista
    }

    public void adicionar(Disturbio disturbio) {
        lista.add(disturbio);
    }

    public List<Disturbio> listar() {
        return lista;
    }

    public Disturbio buscar(int id) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdDisturbio()== id) {
                return lista.get(i);
            }
        }
        return null;
    }

    public void alterar(Disturbio disturbios, Disturbio disturbiosAntigo) {
        lista.set(lista.indexOf(disturbiosAntigo), disturbios);

    }

    public void excluir(Disturbio disturbios) {
        lista.remove(disturbios);
    }

    public void gravarLista(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        List<String> listaDeString = new ArrayList<>();
        for (Disturbio produto : lista) {
            listaDeString.add(produto.toString());
        }
        manipulaArquivo.salvarArquivo(caminho, listaDeString);
    }

    public void carregarDados(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        if (!manipulaArquivo.existeOArquivo(caminho)) {
            manipulaArquivo.criarArquivoVazio(caminho);
        }

        List<String> listaDeString = manipulaArquivo.abrirArquivo(caminho);
        //converter de CSV para Produto
        Disturbio disturbio;
        ConverteDatas converteDatas = new ConverteDatas();
        for (String string : listaDeString) {
            String aux[] = string.split(";");
            disturbio = new Disturbio(Integer.valueOf(aux[0]), aux[1], aux[2]);
            lista.add(disturbio);
        }
    }

}
