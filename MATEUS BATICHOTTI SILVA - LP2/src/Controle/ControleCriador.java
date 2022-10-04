package Controle;

import Entidade.Criador;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import tools.ConverteDatas;
import tools.ManipulaArquivo;

/**
 *
 * @author radames
 */
public class ControleCriador {

    private List<Criador> lista = new ArrayList<>();

    public ControleCriador() { //esse construtor é usado para adicionar alguns dados na lista e 

    }

    public void limparLista() {
        lista.clear();//zera a lista
    }

    public void adicionar(Criador produto) {
        lista.add(produto);
    }

    public List<Criador> listar() {
        return lista;
    }

    public Criador buscar(int id) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdCriador() == id) {
                return lista.get(i);
            }
        }
        return null;
    }

    public void alterar(Criador criador, Criador criadorAntigo) {
        lista.set(lista.indexOf(criadorAntigo), criador);

    }

    public void excluir(Criador criador) {
        lista.remove(criador);
    }

    public void gravarLista(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        List<String> listaDeString = new ArrayList<>();
        for (Criador produto : lista) {
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
        Criador produto;
        ConverteDatas converteDatas = new ConverteDatas();
        for (String string : listaDeString) {
            String aux[] = string.split(";");
            produto = new Criador(Integer.valueOf(aux[0]), aux[1], aux[2], aux[3], converteDatas.converteDeStringParaDate(aux[4]), Integer.valueOf(aux[5]));
            lista.add(produto);
        }
    }

}
