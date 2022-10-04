package Controle;

import Entidade.Empresa;
import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;

/**
 *
 * @author radames
 */
public class ControleEmpresa {

    private List<Empresa> lista = new ArrayList<>();

    public ControleEmpresa() { //esse construtor é usado para adicionar alguns dados na lista e 

    }

    public void limparLista() {
        lista.clear();//zera a lista
    }

    public void adicionar(Empresa produto) {
        lista.add(produto);
    }

    public List<Empresa> listar() {
        return lista;
    }

    public Empresa buscar(int id) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdEmpresa()== id) {
                return lista.get(i);
            }
        }
        return null;
    }

    public void alterar(Empresa empresa, Empresa empresaAntigo) {
        lista.set(lista.indexOf(empresaAntigo), empresa);

    }

    public void excluir(Empresa empresa) {
        lista.remove(empresa);
    }

    public void gravarLista(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        List<String> listaDeString = new ArrayList<>();
        for (Empresa produto : lista) {
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
        Empresa produto;
        for (String string : listaDeString) {
            String aux[] = string.split(";");
            produto = new Empresa(Integer.valueOf(aux[0]), aux[1], aux[2]);
            lista.add(produto);
        }
    }

}
