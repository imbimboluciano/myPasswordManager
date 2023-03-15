package com.code.passwordmanager;

import java.util.Objects;

public class Credentials implements Comparable<Credentials>{

    private String nome;
    private String nomeUtente;
    private String password;
    private String logo;

    private String url;


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNome() {
        return nome;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public String getPassword() {
        return password;
    }

    public String getLogo() {
        return logo;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Credentials{" +
                "nome='" + nome + '\'' +
                ", nomeUtente='" + nomeUtente + '\'' +
                ", password='" + password + '\'' +
                ", logo='" + logo + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public int compareTo(Credentials o) {
        return this.getNome().toLowerCase().compareTo(o.getNome().toLowerCase());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credentials that = (Credentials) o;
        return Objects.equals(nome, that.nome) && Objects.equals(nomeUtente, that.nomeUtente) && Objects.equals(password, that.password) && Objects.equals(logo, that.logo) && Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, nomeUtente, password, logo, url);
    }
}
