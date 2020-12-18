package com.example.tuchamba.signUp;

public interface SignUpInterface {

    interface View{

        void habilitarInputs();
        void deshabilitarInputs();

        void mostrarProgreso();
        void ocultarProgreso();

        boolean validarVista();
        void validarSignUp();
        void onError(String error);
        void onLogin();
    }

    //Realiza la accion una vez que se selecciona una opcion o boton
    interface Presentador{
        void onDestroy();
        void doSignUp(String correo, String clave);
        void toLogin(String correo, String clave);


    }

    //Realiza la transaccion de una funcion
    interface Modelo{
        void onSignUp(String correo, String clave);
        void doLogin(String correo, String clave);
    }

    //Indica si la informacion obtenida es correcta o no
    interface Respuesta{
        void onExito();
        void onError(String error);

    }

}
