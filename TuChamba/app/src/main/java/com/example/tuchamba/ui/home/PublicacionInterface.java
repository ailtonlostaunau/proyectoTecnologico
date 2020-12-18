package com.example.tuchamba.ui.home;

public interface PublicacionInterface {

    interface View{

        void enableInputs();
        void disableInputs();

        //void fillEditText(String area, String profesion, String especialidad, String infoAdicional, String tarifa, String periodo);
        void onError(String error);
    }

    interface Presenter{
        void onSave(String areaPublicacion, String infoPublicacion);
        void onCharge();

        void onDestroy();
    }

    interface Model{
        void chargeDatos();
        void setAreaPublicacion(String areaPublicacion);
        void setSolicitudAdicional(String solicitudAdicional);
    }

    interface Listener{
        void onSuccessSave();
        void onSuccessCharge(String areaPublicacion, String solicitudAdicional);;
        void onError(String error);
    }
}
