package Entidade;

/**
 *
 * @author radames
 */
public class Personagem {

    private int idPersonagem;//pk
    private String nomePersonagem;
    private String obraPersonagem;
    private String tipoObraPersonagem;
    private String idDisturbio;//fk
    private String idCriador;//fk

    public Personagem() {
    }

    public Personagem(int idPersonagem, String nomePersonagem, String obraPersonagem, String tipoObraPersonagem, String idDisturbio, String idCriador) {
        this.idPersonagem = idPersonagem;
        this.nomePersonagem = nomePersonagem;
        this.obraPersonagem = obraPersonagem;
        this.tipoObraPersonagem = tipoObraPersonagem;
        this.idDisturbio = idDisturbio;
        this.idCriador = idCriador;
    }

    @Override
    public String toString() {
        return idPersonagem + ";" + nomePersonagem + ";" + obraPersonagem + ";" + tipoObraPersonagem + ";" + idDisturbio + ";" + idCriador;
    }

    public int getIdPersonagem() {
        return idPersonagem;
    }

    public void setIdPersonagem(int idPersonagem) {
        this.idPersonagem = idPersonagem;
    }

    public String getNomePersonagem() {
        return nomePersonagem;
    }

    public void setNomePersonagem(String nomePersonagem) {
        this.nomePersonagem = nomePersonagem;
    }

    public String getObraPersonagem() {
        return obraPersonagem;
    }

    public void setObraPersonagem(String obraPersonagem) {
        this.obraPersonagem = obraPersonagem;
    }

    public String getTipoObraPersonagem() {
        return tipoObraPersonagem;
    }

    public void setTipoObraPersonagem(String tipoObraPersonagem) {
        this.tipoObraPersonagem = tipoObraPersonagem;
    }

    public String getIdDisturbio() {
        return idDisturbio;
    }

    public void setIdDisturbio(String idDisturbio) {
        this.idDisturbio = idDisturbio;
    }

    public String getIdCriador() {
        return idCriador;
    }

    public void setIdCriador(String idCriador) {
        this.idCriador = idCriador;
    }
    
    
}
