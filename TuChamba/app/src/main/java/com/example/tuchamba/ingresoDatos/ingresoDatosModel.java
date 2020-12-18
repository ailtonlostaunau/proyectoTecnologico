package com.example.tuchamba.ingresoDatos;

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

public class ingresoDatosModel implements ingresoDatosInterface.Model {

    private ingresoDatosInterface.Listener listener;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseUser user;

    public ingresoDatosModel (ingresoDatosInterface.Listener listener){
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

                if(dataSnapshot.exists()){
                    String nombre = (String) dataSnapshot.child("nombre").getValue();
                    String apellido = (String) dataSnapshot.child("apellido").getValue();
                    String dni = (String) dataSnapshot.child("dni").getValue();
                    String telefono = (String) dataSnapshot.child("telefono").getValue();
                    String fechaNac= (String) dataSnapshot.child("fechaNac").getValue();
                    String sexo = (String) dataSnapshot.child("sexo").getValue();
                    String latitud= (String) dataSnapshot.child("latitud").getValue();
                    String longitud= (String) dataSnapshot.child("longitud").getValue();
                    listener.onSuccessCharge(nombre, apellido, dni, telefono, fechaNac, sexo, latitud,longitud);
                }else{
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
    public void setLatitud(String latitud) {
        reference.child("latitud").setValue(latitud).addOnCompleteListener(new OnCompleteListener<Void>() {
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
    public void setLongitud(String longitud) {
        reference.child("longitud").setValue(longitud).addOnCompleteListener(new OnCompleteListener<Void>() {
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
