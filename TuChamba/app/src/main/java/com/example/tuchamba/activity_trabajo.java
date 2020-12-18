package com.example.tuchamba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tuchamba.datosLaborales.DatosLaboralesInterface;
import com.example.tuchamba.datosLaborales.DatosLaboralesPresenter;

public class activity_trabajo extends AppCompatActivity implements AdapterView.OnItemSelectedListener, DatosLaboralesInterface.View {
    private ArrayAdapter<CharSequence> adapter1, adapter3;
    private Spinner areaSpinner, periodoSpinner;
    private EditText txtEspecialidad, txtInfoAdicional, txtTarifa, txtProfesion;
    private DatosLaboralesPresenter presenter;
    private TextView txtArea,txtPeriodo;
    private Button btnGuardarInfoLaboral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new DatosLaboralesPresenter(this);
        setContentView(R.layout.activity_trabajo);

        areaSpinner=findViewById(R.id.areaSpinner);
        periodoSpinner=findViewById(R.id.periodoSpinner);

        txtEspecialidad=findViewById(R.id.txtProfesion);
        txtInfoAdicional=findViewById(R.id.txtInfoAdicional);
        txtTarifa=findViewById(R.id.txtTarifa);
        txtProfesion=findViewById(R.id.txtProfesion);
        txtArea=findViewById(R.id.txtTelefono);
        txtPeriodo=findViewById(R.id.txtPeriodo);
        btnGuardarInfoLaboral=findViewById(R.id.btnGuardarInfoLaboral);

        adapter1= ArrayAdapter.createFromResource(this, R.array.areas,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        areaSpinner.setAdapter(adapter1);

        adapter3=ArrayAdapter.createFromResource(this,R.array.periodo,android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        periodoSpinner.setAdapter(adapter3);

        areaSpinner.setOnItemSelectedListener(this);
        periodoSpinner.setOnItemSelectedListener(this);

        presenter.onCharge();
        btnGuardarInfoLaboral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtArea.getText().toString().equals("Area") || txtPeriodo.getText().toString().equals("Periodo")){
                    Toast.makeText(activity_trabajo.this,"Falta completar los datos",Toast.LENGTH_SHORT).show();
                }else {
                    presenter.onSave(txtArea.getText().toString(), txtProfesion.getText().toString(), txtEspecialidad.getText().toString(), txtInfoAdicional.getText().toString(), txtTarifa.getText().toString(), txtPeriodo.getText().toString());
                    Toast.makeText(activity_trabajo.this,"Datos guardados correctamente",Toast.LENGTH_SHORT).show();
                    guardar();

                }


            }
        });
    }

    private void guardar(){
        Toast.makeText(activity_trabajo.this,"Datos guardados correctamente",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, BarraMenuActivity.class));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String area= areaSpinner.getSelectedItem().toString();
        txtArea.setText(area);

        String periodo= periodoSpinner.getSelectedItem().toString();
        txtPeriodo.setText(periodo);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void setInputs(boolean enable){
        txtProfesion.setEnabled(enable);
        txtEspecialidad.setEnabled(enable);
        txtInfoAdicional.setEnabled(enable);
        txtTarifa.setEnabled(enable);
    }

    @Override
    public void enableInputs() {
        setInputs(true);
    }

    @Override
    public void disableInputs() {
        setInputs(false);
    }

    //@Override
    /*public void fillEditText(String area, String profesion, String especialidad, String infoAdicional, String tarifa, String periodo) {
        txtProfesion.setText(profesion);
        txtEspecialidad.setText(especialidad);
        txtInfoAdicional.setText(infoAdicional);
        txtTarifa.setText(tarifa);
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