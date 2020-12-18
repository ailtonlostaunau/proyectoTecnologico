package com.example.tuchamba.login;

public interface LoginInterface {
    //Muestra la interaz donde se realizan las funciones
    interface View{

        void habilitarInputs();
        void deshabilitarInputs();

        void mostrarProgreso();
        void ocultarProgreso();

        void validarLogin();
        boolean esValidoEmail();
        boolean esValidoClave();

        void onLogin();
        void onError(String error);
    }

    //Envia las funciones al Modelo para que este obtenga los datos
    interface Presentador{
        void onDestroy();
        void toLogin(String correo, String clave);


    }

    //Conecta con la base de datos y muestra la informacion
    interface Modelo{
        void doLogin(String correo, String clave);
    }

    //Indica si la informacion obtenida es correcta o no
    interface Respuesta{
        void onExito();
        void onError(String error);

    }
}
