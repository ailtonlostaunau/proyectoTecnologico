package com.example.tuchamba.login;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginModel implements LoginInterface.Modelo {

    private LoginInterface.Respuesta respuesta;
    private FirebaseAuth mAuth;

    public LoginModel(LoginInterface.Respuesta respuesta) {
        this.respuesta = respuesta;
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void doLogin(String correo, String clave) {
        mAuth.signInWithEmailAndPassword(correo, clave).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
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
