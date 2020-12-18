package com.example.tuchamba.ingresoDatos;

public class ingresoDatosPresenter implements ingresoDatosInterface.Presenter, ingresoDatosInterface.Listener {

    private ingresoDatosInterface.View view;
    private ingresoDatosInterface.Model model;

    public ingresoDatosPresenter (ingresoDatosInterface.View view){
        this.view = view;
        model = new ingresoDatosModel(this);
    }

    @Override
    public void onSave(String nombre, String apellido, String dni, String telefono, String fechaNac, String sexo, String latitud, String longitud) {
        if (view!=null){
            view.disableImputs();
            model.setNombre(nombre);
            model.setApellido(apellido);
            model.setDni(dni);
            model.setTelefono(telefono);
            model.setFechaNac(fechaNac);
            model.setSexo(sexo);
            model.setLatitud(latitud);
            model.setLongitud(longitud);
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
    public void onSuccessCharge(String nombre, String apellido, String dni, String telefono, String fechaNac, String sexo, String latitud, String longitud) {
        if (view!=null){
            view.enableImputs();
            //view.fillEditText(nombre, apellido, dni, telefono, fechaNac, sexo, latitud, longitud);

        }
    }

    @Override
    public void onError(String error) {
        if (view!=null){
            view.enableImputs();
            view.onError(error);
        }
    }
}
