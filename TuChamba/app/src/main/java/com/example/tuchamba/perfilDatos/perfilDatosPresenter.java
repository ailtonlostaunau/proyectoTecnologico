package com.example.tuchamba.perfilDatos;

import com.example.tuchamba.ingresoDatos.ingresoDatosInterface;
import com.example.tuchamba.ingresoDatos.ingresoDatosModel;

public class perfilDatosPresenter implements perfilDatosInterface.Presenter, perfilDatosInterface.Listener {

    private perfilDatosInterface.View view;
    private perfilDatosInterface.Model model;


    public perfilDatosPresenter (perfilDatosInterface.View view){
        this.view = view;
        model = new perfilDatosModel(this);
    }

    @Override
    public void onSave(String nombre, String apellido, String dni, String telefono, String fechaNac,  String sexo, String area, String profesion, String especialidad, String infoAdicional, String tarifa, String periodo) {
        if (view!=null) {
            view.disableImputs();
            model.setNombre(nombre);
            model.setApellido(apellido);
            model.setDni(dni);
            model.setTelefono(telefono);
            model.setFechaNac(fechaNac);
            model.setSexo(sexo);
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
            view.disableImputs();
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
    public void onSuccessCharge(String nombre, String apellido, String dni, String telefono, String fechaNac, String sexo, String area, String profesion, String especialidad, String infoAdicional, String tarifa, String periodo) {
        view.enableImputs();
    }

    @Override
    public void onError(String error) {
        if (view!=null){
            view.enableImputs();
            view.onError(error);
        }
    }
}
