/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidade;

/**
 *
 * @author Mateus CohuzEr
 */
public class Disturbio {
    private int idDisturbio;//pk
    private String nomeDisturbio;
    private String descricaoDisturbio;

    public Disturbio() {
    }

    public Disturbio(int idDisturbio, String nomeDisturbio, String descricaoDisturbio) {
        this.idDisturbio = idDisturbio;
        this.nomeDisturbio = nomeDisturbio;
        this.descricaoDisturbio = descricaoDisturbio;
    }

    public int getIdDisturbio() {
        return idDisturbio;
    }

    public void setIdDisturbio(int idDisturbio) {
        this.idDisturbio = idDisturbio;
    }

    public String getNomeDisturbio() {
        return nomeDisturbio;
    }

    public void setNomeDisturbio(String nomeDisturbio) {
        this.nomeDisturbio = nomeDisturbio;
    }

    public String getDescricaoDisturbio() {
        return descricaoDisturbio;
    }

    public void setDescricaoDisturbio(String descricaoDisturbio) {
        this.descricaoDisturbio = descricaoDisturbio;
    }

    @Override
    public String toString() {
        return idDisturbio + ";" + nomeDisturbio + ";" + descricaoDisturbio;
    }
}
