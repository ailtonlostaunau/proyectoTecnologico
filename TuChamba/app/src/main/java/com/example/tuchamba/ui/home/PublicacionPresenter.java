package com.example.tuchamba.ui.home;

import com.example.tuchamba.datosLaborales.DatosLaboralesInterface;
import com.example.tuchamba.datosLaborales.DatosLaboralesModel;

public class PublicacionPresenter implements PublicacionInterface.Presenter, PublicacionInterface.Listener {

    private PublicacionInterface.View view;
    private PublicacionInterface.Model model;

    public PublicacionPresenter (PublicacionInterface.View view){
        this.view = view;
        model = new HomeViewModel(this);
    }

    @Override
    public void onSave(String areaPublicacion, String solicitudAdicional) {
        if (view!=null){
            view.disableInputs();
            model.setAreaPublicacion(areaPublicacion);
            model.setSolicitudAdicional(solicitudAdicional);
        }

    }

    @Override
    public void onCharge() {
        if (view!=null){
            view.disableInputs();
            model.chargeDatos();
        }

    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void onSuccessSave() {
        if (view!=null){
            model.chargeDatos();
        }
    }

    @Override
    public void onSuccessCharge(String areaPublicacion, String solicitudAdicional) {
        if (view!=null){
            view.enableInputs();
            //view.fillEditText(area, profesion, especialidad, infoAdicional, tarifa, periodo);
        }

    }

    @Override
    public void onError(String error) {
        if (view!=null){
            view.enableInputs();
            view.onError(error);
        }

    }
}
