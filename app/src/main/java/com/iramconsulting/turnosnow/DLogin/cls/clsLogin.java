package com.iramconsulting.turnosnow.DLogin.cls;

/**
 * Created by RAMOS on 11/12/2016.
 */

public class clsLogin {

    //Atributos
    private String usuario;
    private String pass;

    public clsLogin(String usuario, String pass) {
        this.usuario = usuario;
        this.pass = pass;

    }



    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


}
