package com.example.tuchamba.perfilDatos;

public interface perfilDatosInterface {

    interface View{

        void enableImputs();
        void disableImputs();

        void onError(String error);
    }

    interface Presenter{
        void onSave(String nombre, String apellido, String dni, String telefono, String fechaNac, String sexo, String area, String profesion, String especialidad, String infoAdicional, String tarifa, String periodo);
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
        void setArea(String area);
        void setProfesion(String profesion);
        void setEspecialidad(String especialidad);
        void setInfoAdicional(String infoAdicional);
        void setTarifa(String tarifa);
        void setPeriodo(String periodo);

    }

    interface Listener{
        void onSuccessSave();
        void onSuccessCharge(String nombre, String apellido, String dni, String telefono, String fechaNac,  String sexo, String area, String profesion, String especialidad, String infoAdicional, String tarifa, String periodo);
        void onError(String error);
    }
}
