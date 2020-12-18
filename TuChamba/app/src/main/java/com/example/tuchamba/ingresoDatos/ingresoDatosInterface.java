package com.example.tuchamba.ingresoDatos;

public interface ingresoDatosInterface {

    interface View{

        void enableImputs();
        void disableImputs();

        //void fillEditText(String nombre, String apellido, String dni, String telefono, String fechaNac, String sexo,String latitud, String longitud);
        void onError(String error);
    }

    interface Presenter{
        void onSave(String nombre, String apellido, String dni, String telefono, String fechaNac, String sexo, String latitud, String longitud);
        void onCharge();

        void onDestroy();
    }

    interface Model{
        void chargeDatos();
        void setNombre(String nombre);
        void setApellido(String apellido);
        void setDni(String dni);
        void setTelefono(String telefono);
        void setFechaNac(String fechaNac);
        void setSexo(String sexo);
        void setLatitud(String latitud);
        void setLongitud(String longitud);
    }

    interface Listener{
        void onSuccessSave();
        void onSuccessCharge(String nombre, String apellido, String dni, String telefono, String fechaNac, String sexo, String latitud, String longitud);
        void onError(String error);
    }
}
