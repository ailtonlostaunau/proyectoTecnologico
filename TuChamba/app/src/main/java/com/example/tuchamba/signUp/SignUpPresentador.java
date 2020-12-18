package com.example.tuchamba.signUp;

public class SignUpPresentador implements SignUpInterface.Presentador,  SignUpInterface.Respuesta {

    private SignUpInterface.Modelo modelo;
    private SignUpInterface.View view;

    public SignUpPresentador(SignUpInterface.View view) {
        this.view = view;
        modelo= new SignUpModelo(this);
    }

    @Override
    public void onDestroy() {
        view=null;
    }

    @Override
    public void doSignUp(String correo, String clave) {
        if (view!=null){
            view.deshabilitarInputs();
            view.mostrarProgreso();
        }
        modelo.onSignUp(correo,clave);
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
        if (view!=null){
            view.habilitarInputs();
            view.ocultarProgreso();
            view.onLogin();
        }

    }

    @Override
    public void onError(String error) {
        if (view!=null){
            view.habilitarInputs();
            view.ocultarProgreso();
            view.onError(error);
        }

    }
}
