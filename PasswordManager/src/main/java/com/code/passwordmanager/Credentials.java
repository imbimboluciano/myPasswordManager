package com.code.passwordmanager;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Base64;
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



    public String encryptPassword(String valueToEnc, Key key) throws Exception {

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] encValue = cipher.doFinal(valueToEnc.getBytes());
        byte[] encryptedByteValue = Base64.getEncoder().encode(encValue);
        return new String(encryptedByteValue);
    }

    public String decryptPassword(String encryptedValue, Key key) throws Exception {
        // Key key = generateKey();
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] decodedBytes = Base64.getDecoder().decode(encryptedValue.getBytes());

        byte[] enctVal = cipher.doFinal(decodedBytes);
        return new String(enctVal);
    }




    public Key generateKey() throws Exception {
        final byte[] keyValue = "1234567891234567".getBytes();
        Key key = new SecretKeySpec(keyValue, "AES");
        return key;
    }


    public void checkParameters() throws Exception{

        if(nome.isBlank() || nomeUtente.isBlank() || password.isBlank() || url.isBlank()){
            throw new IllegalArgumentException();
        }
    }


}
