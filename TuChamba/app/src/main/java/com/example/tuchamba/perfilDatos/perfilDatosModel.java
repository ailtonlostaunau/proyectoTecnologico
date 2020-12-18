package com.example.tuchamba.perfilDatos;

import com.example.tuchamba.ingresoDatos.ingresoDatosInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;

class perfilDatosModel implements perfilDatosInterface.Model  {

    private perfilDatosInterface.Listener listener;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseUser user;

    public perfilDatosModel (perfilDatosInterface.Listener listener){
        this.listener = listener;
        database = FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = database.getReference("users").child(user.getUid());
    }

    @Override
    public void chargeDatos() {
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
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
                    listener.onSuccessCharge(nombre, apellido, dni, telefono, fechaNac, sexo, area, profesion, especialidad, infoAdicional, tarifa, periodo);
                } else {
                    listener.onError("Ingresa tus datos personales");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onError(databaseError.getMessage());
            }
        });
    }

    @Override
    public void setNombre(String nombre) {
        reference.child("nombre").setValue(nombre).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    listener.onSuccessSave();
                }else{
                    if(task.getException()!=null){
                        listener.onError(task.getException().getMessage());
                    }
                }
            }
        });
    }

    @Override
    public void setApellido(String apellido) {
        reference.child("apellido").setValue(apellido).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    listener.onSuccessSave();
                }else{
                    if(task.getException()!=null){
                        listener.onError(task.getException().getMessage());
                    }
                }
            }
        });
    }

    @Override
    public void setDni(String dni) {
        reference.child("dni").setValue(dni).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    listener.onSuccessSave();
                }else{
                    if(task.getException()!=null){
                        listener.onError(task.getException().getMessage());
                    }
                }
            }
        });
    }

    @Override
    public void setTelefono(String telefono) {
        reference.child("telefono").setValue(telefono).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    listener.onSuccessSave();
                }else{
                    if(task.getException()!=null){
                        listener.onError(task.getException().getMessage());
                    }
                }
            }
        });
    }

    @Override
    public void setFechaNac(String fechaNac) {
        reference.child("fechaNac").setValue(fechaNac).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    listener.onSuccessSave();
                }else{
                    if(task.getException()!=null){
                        listener.onError(task.getException().getMessage());
                    }
                }
            }
        });

    }

    @Override
    public void setSexo(String sexo) {
        reference.child("sexo").setValue(sexo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    listener.onSuccessSave();
                }else{
                    if(task.getException()!=null){
                        listener.onError(task.getException().getMessage());
                    }
                }
            }
        });

    }

    @Override
    public void setArea(String area) {
        reference.child("area").setValue(area).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    listener.onSuccessSave();
                }else{
                    if(task.getException()!=null){
                        listener.onError(task.getException().getMessage());
                    }
                }
            }
        });

    }

    @Override
    public void setProfesion(String profesion) {
        reference.child("profesion").setValue(profesion).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    listener.onSuccessSave();
                }else{
                    if(task.getException()!=null){
                        listener.onError(task.getException().getMessage());
                    }
                }
            }
        });


    }

    @Override
    public void setEspecialidad(String especialidad) {
        reference.child("especialidad").setValue(especialidad).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    listener.onSuccessSave();
                }else{
                    if(task.getException()!=null){
                        listener.onError(task.getException().getMessage());
                    }
                }
            }
        });

    }

    @Override
    public void setInfoAdicional(String infoAdicional) {
        reference.child("infoAdicional").setValue(infoAdicional).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    listener.onSuccessSave();
                }else{
                    if(task.getException()!=null){
                        listener.onError(task.getException().getMessage());
                    }
                }
            }
        });

    }

    @Override
    public void setTarifa(String tarifa) {
        reference.child("tarifa").setValue(tarifa).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    listener.onSuccessSave();
                }else{
                    if(task.getException()!=null){
                        listener.onError(task.getException().getMessage());
                    }
                }
            }
        });

    }

    @Override
    public void setPeriodo(String periodo) {
        reference.child("periodo").setValue(periodo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    listener.onSuccessSave();
                }else{
                    if(task.getException()!=null){
                        listener.onError(task.getException().getMessage());
                    }
                }
            }
        });

    }
}
