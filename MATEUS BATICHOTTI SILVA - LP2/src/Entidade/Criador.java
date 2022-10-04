
package Entidade;

import java.util.Date;
import tools.ConverteDatas;

/**
 *
 * @author radames
 */
public class Criador {
    private int idCriador;//pk
    private String nomeCriador;
    private String nomeEmpresa; //fk
    private String paisCriador; //lista
    private Date dataAniversario;
    private int qntPremios;

    public Criador() {
    }

    public Criador(int idCriador, String nomeCriador, String nomeEmpresa, String paisCriador, Date dataPrimeiraAparicao, int qntPremios) {
        this.idCriador = idCriador;
        this.nomeCriador = nomeCriador;
        this.nomeEmpresa = nomeEmpresa;
        this.paisCriador = paisCriador;
        this.dataAniversario = dataPrimeiraAparicao;
        this.qntPremios = qntPremios;
    }

    public int getIdCriador() {
        return idCriador;
    }

    public void setIdCriador(int idCriador) {
        this.idCriador = idCriador;
    }

    public String getNomeCriador() {
        return nomeCriador;
    }

    public void setNomeCriador(String nomeCriador) {
        this.nomeCriador = nomeCriador;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getPaisCriador() {
        return paisCriador;
    }

    public void setPaisCriador(String paisCriador) {
        this.paisCriador = paisCriador;
    }

    public Date getDataAniversario() {
        return dataAniversario;
    }

    public void setDataAniversario(Date dataPrimeiraAparicao) {
        this.dataAniversario = dataPrimeiraAparicao;
    }

    public int getPremios() {
        return qntPremios;
    }

    public void setPremios(int idPersonagem) {
        this.qntPremios = idPersonagem;
    }

    @Override
    public String toString() {
        ConverteDatas converteDatas = new ConverteDatas();
        return idCriador + ";" + nomeCriador + ";" + nomeEmpresa + ";" + paisCriador + ";" + converteDatas.converteDeDateParaString(dataAniversario) + ";" + qntPremios;
    }
    
    
}
