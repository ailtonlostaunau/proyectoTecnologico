package com.example.tuchamba.login;

import com.example.tuchamba.MainActivity;
import com.example.tuchamba.login.LoginInterface;

public class LoginPresenter implements LoginInterface.Presentador, LoginInterface.Respuesta {

    private LoginInterface.View view;
    private LoginInterface.Modelo modelo;

    public LoginPresenter(LoginInterface.View view) {
        this.view = view;
        modelo= new LoginModel(this);
    }

    @Override
    public void onDestroy() {
        view=null;

    }

    @Override
    public void toLogin(String correo, String clave) {
        if(view!=null){
            view.deshabilitarInputs();
            view.mostrarProgreso();
        }
        modelo.doLogin(correo,clave);
    }

    @Override
    public void onExito() {
        if(view!=null){
            view.habilitarInputs();
            view.ocultarProgreso();
            view.onLogin();
        }

    }

    @Override
    public void onError(String error) {
        if(view!=null){
            view.habilitarInputs();
            view.ocultarProgreso();
            view.onError(error);
        }

    }
}
