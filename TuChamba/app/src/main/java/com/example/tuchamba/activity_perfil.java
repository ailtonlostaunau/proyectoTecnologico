package com.example.tuchamba;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import id.zelory.compressor.Compressor;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tuchamba.ingresoDatos.ingresoDatosPresenter;
import com.example.tuchamba.perfilDatos.perfilDatosPresenter;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class activity_perfil extends AppCompatActivity implements com.example.tuchamba.perfilDatos.perfilDatosInterface.View {
    ImageButton foto;
    DatabaseReference imgref;
    StorageReference storageReference;
    ProgressDialog cargando;

    Bitmap thumb_bitmap = null;

    private EditText txtNombre, txtApellido, txtDni, txtTelefono, txtFechaNac, txtEspecialidad, txtInfoAdicional, txtTarifa, txtProfesion, txtArea, txtPeriodo ;
    private RadioButton rbtMasculino, rbtFemenino;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseUser user;

    private com.example.tuchamba.perfilDatos.perfilDatosInterface.Presenter presenter;
    private Button btnGuardarCambios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new perfilDatosPresenter(this);
        setContentView(R.layout.activity_perfil);
        foto = findViewById(R.id.imgPerfil);
        user = FirebaseAuth.getInstance().getCurrentUser();
        imgref = FirebaseDatabase.getInstance().getReference("users").child(user.getUid());
        storageReference = FirebaseStorage.getInstance().getReference("users").child(user.getUid());
        cargando = new ProgressDialog(this);

        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.startPickImageActivity(activity_perfil.this);
            }
        });
        txtNombre = findViewById(R.id.txtNombre);
        txtApellido = findViewById(R.id.txtApellido);
        txtDni = findViewById(R.id.txtDni);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtFechaNac= findViewById(R.id.txtFecha);
        txtEspecialidad=findViewById(R.id.txtEspecialidad);
        txtInfoAdicional=findViewById(R.id.txtInfoAdicional);
        txtTarifa = findViewById(R.id.txtTarifa);
        txtProfesion = findViewById(R.id.txtProfesion);
        txtArea = findViewById(R.id.txtArea);
        txtPeriodo = findViewById(R.id.txtPeriodo);
        rbtMasculino=findViewById(R.id.rbtMasculino);
        rbtFemenino=findViewById(R.id.rbtFemenino);
        database = FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = database.getReference("users").child(user.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String nombre = (String) dataSnapshot.child("nombre").getValue();
                    String apellido = (String) dataSnapshot.child("apellido").getValue();
                    String dni = (String) dataSnapshot.child("dni").getValue();
                    String telefono = (String) dataSnapshot.child("telefono").getValue();
                    String fechaNac = (String) dataSnapshot.child("fechaNac").getValue();
                    String sexo = (String) dataSnapshot.child("sexo").getValue();
                    String area = (String) dataSnapshot.child("area").getValue();
                    String profesion = (String) dataSnapshot.child("profesion").getValue();
                    String especialidad = (String) dataSnapshot.child("especialidad").getValue();
                    String infoAdicional = (String) dataSnapshot.child("infoAdicional").getValue();
                    String tarifa = (String) dataSnapshot.child("tarifa").getValue();
                    String periodo = (String) dataSnapshot.child("periodo").getValue();
                    txtNombre.setText(nombre);
                    txtApellido.setText(apellido);
                    txtDni.setText(dni);
                    txtTelefono.setText(telefono);
                    txtFechaNac.setText(fechaNac);
                    if (sexo.equals("Masculino")) {
                        rbtMasculino.setChecked(true);
                    } else if (sexo.equals("Femenino")) {
                        rbtFemenino.setChecked(true);
                    }
                    txtArea.setText(area);
                    txtProfesion.setText(profesion);
                    txtEspecialidad.setText(especialidad);
                    txtInfoAdicional.setText(infoAdicional);
                    txtTarifa.setText(tarifa);
                    txtPeriodo.setText(periodo);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        presenter.onCharge();
        btnGuardarCambios=findViewById(R.id.btnGuardarCambios);
        btnGuardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sexo;
                if(rbtMasculino.isChecked()){
                    sexo="Masculino";
                }else {
                    sexo="Femenino";
                }
                presenter.onSave(txtNombre.getText().toString().trim(), txtApellido.getText().toString().trim(), txtDni.getText().toString().trim(), txtTelefono.getText().toString().trim(), txtFechaNac.getText().toString(), sexo.trim(), txtArea.getText().toString(), txtProfesion.getText().toString(), txtEspecialidad.getText().toString(), txtInfoAdicional.getText().toString(), txtTarifa.getText().toString(), txtPeriodo.getText().toString());
                guardado();
            }
        });

    }

    private void guardado(){
        Toast.makeText(this,"Datos guardado correctamente",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, activity_perfil.class));
    }

    //IMAGEN
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            Uri imageuri = CropImage.getPickImageResultUri(this, data);

            //Recortar imagen

            CropImage.activity(imageuri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setRequestedSize(525, 525)
                    .setAspectRatio(1,1).start(activity_perfil.this);
        }
        if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode==RESULT_OK){
                Uri resultUri = result.getUri();
                File url = new File(resultUri.getPath());
                Picasso.with(this).load(url).into(foto);

                //Comprimiendo imagen
                try{
                    thumb_bitmap = new Compressor(this)
                            .setMaxWidth(525)
                            .setMaxHeight(525)
                            .setQuality(90)
                            .compressToBitmap(url);
                }catch (IOException e){
                    e.printStackTrace();
                }

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                thumb_bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
                final byte [] thumb_byte = byteArrayOutputStream.toByteArray();

                cargando.setTitle("Subiendo foto...");
                cargando.setMessage("Espere por favor");
                cargando.show();

                final StorageReference ref = storageReference.child(user.getUid());
                UploadTask uploadTask= ref.putBytes(thumb_byte);

                //Subir imagen al storage
                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if(!task.isSuccessful()){
                            throw Objects.requireNonNull(task.getException());
                        }
                        return ref.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        Uri dowloaduri = task.getResult();
                        imgref.push().child("urlfoto").setValue(dowloaduri.toString());
                        cargando.dismiss();
                        Toast.makeText(activity_perfil.this, "Imagen cargada con exito", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
    //ACABA IMAGEN

    private void setInputs(boolean enable){
        txtNombre.setEnabled(enable);
        txtApellido.setEnabled(enable);
        txtDni.setEnabled(enable);
        txtTelefono.setEnabled(enable);
        txtFechaNac.setEnabled(enable);
        txtProfesion.setEnabled(enable);
        txtEspecialidad.setEnabled(enable);
        txtInfoAdicional.setEnabled(enable);
        txtTarifa.setEnabled(enable);
    }

    @Override
    public void enableImputs() {
        setInputs(true);
    }

    @Override
    public void disableImputs() {
        setInputs(false);
    }

    @Override
    public void onError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    protected void onDestroy(){
        super.onDestroy();
        presenter.onDestroy();
    }
}