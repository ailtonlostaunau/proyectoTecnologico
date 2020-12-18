package com.example.tuchamba.signUp;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpModelo implements SignUpInterface.Modelo {

    private SignUpInterface.Respuesta respuesta;
    private FirebaseAuth auth;

    public SignUpModelo(SignUpInterface.Respuesta respuesta) {
        this.respuesta = respuesta;
        auth=FirebaseAuth.getInstance();
    }

    @Override
    public void onSignUp(String correo, String clave) {
        auth.createUserWithEmailAndPassword(correo,clave);
        respuesta.onExito();
    }

    @Override
    public void doLogin(String correo, String clave) {
        auth.signInWithEmailAndPassword(correo, clave).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    respuesta.onExito();
                }else {
                    if(task.getException()!=null){
                        respuesta.onError(task.getException().getMessage());
                    }

                }
            }
        });
    }
}
