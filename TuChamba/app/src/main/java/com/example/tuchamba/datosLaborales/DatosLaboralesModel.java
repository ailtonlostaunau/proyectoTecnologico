package com.example.tuchamba.datosLaborales;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DatosLaboralesModel implements DatosLaboralesInterface.Model {

    private DatosLaboralesInterface.Listener listener;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseUser user;

    public DatosLaboralesModel (DatosLaboralesInterface.Listener listener){
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
                    String area = (String) dataSnapshot.child("area").getValue();
                    String profesion = (String) dataSnapshot.child("profesion").getValue();
                    String especialidad = (String) dataSnapshot.child("especialidad").getValue();
                    String infoAdicional = (String) dataSnapshot.child("infoAdicional").getValue();
                    String tarifa= (String) dataSnapshot.child("tarifa").getValue();
                    String periodo = (String) dataSnapshot.child("periodo").getValue();

                    listener.onSuccessCharge(area, profesion, especialidad, infoAdicional, tarifa, periodo);
                }else{
                    listener.onError("Ingresa tus datos laborales");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onError(databaseError.getMessage());
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
