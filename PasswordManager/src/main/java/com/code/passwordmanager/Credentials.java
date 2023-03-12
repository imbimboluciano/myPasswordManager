package com.code.passwordmanager;

public class Credentials {

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
}
