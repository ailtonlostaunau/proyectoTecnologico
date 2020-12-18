package com.example.tuchamba.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tuchamba.BarraMenuActivity;
import com.example.tuchamba.R;
import com.example.tuchamba.activity_trabajo;
import com.example.tuchamba.datosLaborales.DatosLaboralesInterface;
import com.example.tuchamba.datosLaborales.DatosLaboralesPresenter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment implements AdapterView.OnItemSelectedListener, PublicacionInterface.View{

    private ArrayAdapter<CharSequence> adapter;
    private Spinner areaPublicacionSpinner;
    private PublicacionPresenter presenter;
    private TextView txtAreaPublicacion;
    private EditText txtSolicitudAdicional;
    private Button btnGuardarPublicacion;
    FirebaseFirestore mFirestore;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        presenter = new PublicacionPresenter(this);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        mFirestore=FirebaseFirestore.getInstance();

        areaPublicacionSpinner=root.findViewById(R.id.areaPublicacionSpinner);
        txtAreaPublicacion=root.findViewById(R.id.txtAreaPublicacion);
        txtSolicitudAdicional=root.findViewById(R.id.txtSolicitudAdicional);
        btnGuardarPublicacion=root.findViewById(R.id.btnGuardarPublicacion);

        adapter= ArrayAdapter.createFromResource(getContext(), R.array.areas,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        areaPublicacionSpinner.setAdapter(adapter);

        areaPublicacionSpinner.setOnItemSelectedListener(this);

        presenter.onCharge();
        btnGuardarPublicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtAreaPublicacion.getText().toString().equals("Area")){
                    Toast.makeText(getContext(),"Falta completar los datos",Toast.LENGTH_SHORT).show();
                }else {
                    presenter.onSave(txtAreaPublicacion.getText().toString(), txtSolicitudAdicional.getText().toString());
                    Toast.makeText(getContext(),"Datos guardados",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return root;
    }


    public void crearDatos(){

        String  areaPublicacion = txtAreaPublicacion.getText().toString();
        String  solicitudAdicional = txtSolicitudAdicional.getText().toString();
        Map<String,Object> map= new HashMap<>();
        map.put("areaPublicacion",areaPublicacion);
        map.put("solicitudAdicional",solicitudAdicional);

        mFirestore.collection("publicacion").document("2").set(map);

        /*mFirestore.collection("publicacion").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getContext(),"publicacion creada exitosamente", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(),"publicacion no pudo ser creada", Toast.LENGTH_SHORT).show();
            }
        });*/
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String areaSolicitud= areaPublicacionSpinner.getSelectedItem().toString();
        txtAreaPublicacion.setText(areaSolicitud);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void enableInputs() {

    }

    @Override
    public void disableInputs() {

    }

    @Override
    public void onError(String error) {
        presenter.onDestroy();

    }
}