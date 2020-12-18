package com.example.tuchamba.datosLaborales;


public class DatosLaboralesPresenter implements DatosLaboralesInterface.Presenter, DatosLaboralesInterface.Listener {
    private DatosLaboralesInterface.View view;
    private DatosLaboralesInterface.Model model;

    public DatosLaboralesPresenter (DatosLaboralesInterface.View view){
        this.view = view;
        model = new DatosLaboralesModel(this);
    }

    @Override
    public void onSave(String area, String profesion, String especialidad, String infoAdicional, String tarifa, String periodo) {
        if (view!=null){
            view.disableInputs();
            model.setArea(area);
            model.setProfesion(profesion);
            model.setEspecialidad(especialidad);
            model.setInfoAdicional(infoAdicional);
            model.setTarifa(tarifa);
            model.setPeriodo(periodo);
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
    public void onSuccessCharge(String area, String profesion, String especialidad, String infoAdicional, String tarifa, String periodo) {
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
