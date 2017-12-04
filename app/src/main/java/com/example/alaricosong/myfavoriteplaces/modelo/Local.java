package com.example.alaricosong.myfavoriteplaces.modelo;

import java.io.Serializable;

/**
 * Created by Alarico Song on 26/11/2017.
 */

public class Local implements Serializable{

    private Long id;
    private String local;
    private String endereco;
    private String telefone;
    private String cidade;
    private String site;
    private String utimaVisita;
    private Double nota;
    private String foto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getUtimaVisita() {
        return utimaVisita;
    }

    public void setUtimaVisita(String utimaVisita) {
        this.utimaVisita = utimaVisita;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) { this.foto = foto; }

    @Override
    public String toString() {
        return getId() + " - " + getLocal();
    }
}
