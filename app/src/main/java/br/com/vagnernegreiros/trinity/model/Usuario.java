package br.com.vagnernegreiros.trinity.model;

public class Usuario {

    Long user_id;
    String user_email;
    String user_password;

    public Usuario(Long user_id, String user_email, String user_password) {
        this.user_id = user_id;
        this.user_email = user_email;
        this.user_password = user_password;
    }

    public Usuario( String user_email, String user_password) {
        this.user_email = user_email;
        this.user_password = user_password;
    }

    public Usuario( String user_email) {
        this.user_email = user_email;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
}
