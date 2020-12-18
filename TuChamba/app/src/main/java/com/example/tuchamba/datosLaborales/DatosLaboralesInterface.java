package com.example.tuchamba.datosLaborales;

public interface DatosLaboralesInterface {

    interface View{

        void enableInputs();
        void disableInputs();

        //void fillEditText(String area, String profesion, String especialidad, String infoAdicional, String tarifa, String periodo);
        void onError(String error);
    }

    interface Presenter{
        void onSave(String area, String profesion, String especialidad, String infoAdicional, String tarifa, String periodo);
        void onCharge();

        void onDestroy();
    }

    interface Model{
        void chargeDatos();
        void setArea(String area);
        void setProfesion(String profesion);
        void setEspecialidad(String especialidad);
        void setInfoAdicional(String infoAdicional);
        void setTarifa(String tarifa);
        void setPeriodo(String periodo);
    }

    interface Listener{
        void onSuccessSave();
        void onSuccessCharge(String area, String profesion, String especialidad, String infoAdicional, String tarifa, String periodo);;
        void onError(String error);
    }
}
