package com.example.tuchamba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.tuchamba.ingresoDatos.ingresoDatosPresenter;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class activity_inicio extends AppCompatActivity implements com.example.tuchamba.ingresoDatos.ingresoDatosInterface.View {

    private FusedLocationProviderClient ubicacion;
    private EditText txtNombre, txtApellido, txtDni, txtTelefono, txtFechaNac, txtLatitud, txtLongitud;
    private RadioButton rbtMasculino, rbtFemenino;
    private Button btnGuardar;
    private com.example.tuchamba.ingresoDatos.ingresoDatosInterface.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ingresoDatosPresenter(this);
        setContentView(R.layout.activity_inicio);
        setViews();
    }

    private void setViews() {
            txtNombre = findViewById(R.id.txtNombre);
            txtApellido = findViewById(R.id.txtApellido);
            txtDni = findViewById(R.id.txtTelefono);
            txtTelefono = findViewById(R.id.txtTelefono);
            txtFechaNac= findViewById(R.id.txtProfesion);
            txtLatitud = findViewById(R.id.txtLatitud);
            txtLongitud= findViewById(R.id.txtLongitud);
            btnGuardar= findViewById(R.id.btnGuardar);
            rbtMasculino=findViewById(R.id.rbtMasculino);
            rbtFemenino=findViewById(R.id.rbtFemenino);
            presenter.onCharge();
            btnGuardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String sexo;
                    if(rbtMasculino.isChecked()){
                        sexo="Masculino";
                    }else {
                        sexo="Femenino";
                    }

                    if(TextUtils.isEmpty(txtLatitud.getText()) || TextUtils.isEmpty(txtLongitud.getText())){
                        Toast.makeText(activity_inicio.this,"Pulsar el boton de guardar direccion",Toast.LENGTH_SHORT).show();

                    }else {
                        presenter.onSave(txtNombre.getText().toString().trim(), txtApellido.getText().toString().trim(), txtDni.getText().toString().trim(), txtTelefono.getText().toString().trim(), txtFechaNac.getText().toString(), sexo.trim(),txtLatitud.getText().toString(),txtLongitud.getText().toString());
                        guardado();
                    }


                }
            });
        }

    private void guardado(){
        Toast.makeText(this,"Datos guardado correctamente",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, activity_trabajo.class));
    }

    public void onGuardarDireccion(View view){
        if (ContextCompat.checkSelfPermission(activity_inicio.this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(activity_inicio.this, new String[]{
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION},1);
        }


        ubicacion= LocationServices.getFusedLocationProviderClient(activity_inicio.this);
        ubicacion.getLastLocation().addOnSuccessListener(activity_inicio.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location!=null){

                    txtLatitud.setText(""+location.getLatitude());
                    txtLongitud.setText(""+location.getLongitude());

                    Toast.makeText(activity_inicio.this, "Latitud: " + txtLatitud.getText().toString() + " Longitud: " + txtLongitud.getText().toString(),Toast.LENGTH_LONG)
                            .show();

                }
            }
        });

    }

    private void setInputs(boolean enable){
        txtNombre.setEnabled(enable);
        txtApellido.setEnabled(enable);
        txtDni.setEnabled(enable);
        txtTelefono.setEnabled(enable);
        txtFechaNac.setEnabled(enable);
        btnGuardar.setEnabled(enable);
    }

    @Override
    public void enableImputs() {
        setInputs(true);
    }

    @Override
    public void disableImputs() {
        setInputs(false);
    }

    //@Override
    /*public void fillEditText(String nombre, String apellido, String dni, String telefono, String fechaNac, String sexo, String latitud, String longitud) {
        txtNombre.setText(nombre);
        txtApellido.setText(apellido);
        txtDni.setText(dni);
        txtTelefono.setText(telefono);
        txtFechaNac.setText(fechaNac);

        if(sexo.equals("Masculino")){
            rbtMasculino.setChecked(true);
        }else if(sexo.equals("Femenino")) {
            rbtFemenino.setChecked(true);
        }
    }*/

    @Override
    public void onError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    protected void onDestroy(){
        super.onDestroy();
        presenter.onDestroy();
    }

}