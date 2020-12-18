package com.example.tuchamba.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tuchamba.datosLaborales.DatosLaboralesInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeViewModel implements PublicacionInterface.Model {

    private PublicacionInterface.Listener listener;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseUser user;

    public HomeViewModel (PublicacionInterface.Listener listener){
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
                    String areaPublicacion = (String) dataSnapshot.child("areaPublicacion").getValue();
                    String solicitudAdicional = (String) dataSnapshot.child("solicitudAdicional").getValue();


                    listener.onSuccessCharge(areaPublicacion, solicitudAdicional);
                }else{
                    listener.onError("Ingresa la informacion requerida");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onError(databaseError.getMessage());
            }
        });

    }

    @Override
    public void setAreaPublicacion(String areaPublicacion) {
        reference.child("areaPublicacion").setValue(areaPublicacion).addOnCompleteListener(new OnCompleteListener<Void>() {
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
    public void setSolicitudAdicional(String solicitudAdicional) {
        reference.child("solicitudAdicional").setValue(solicitudAdicional).addOnCompleteListener(new OnCompleteListener<Void>() {
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