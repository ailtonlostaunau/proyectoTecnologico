package com.example.tuchamba;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.tuchamba.signUp.SignUpInterface;
import com.example.tuchamba.signUp.SignUpPresentador;

import java.util.Objects;

public class SignUpActivity extends Activity implements SignUpInterface.View {
    private EditText txtCorreo, txtPassword, txtConfirmarPassword;
    private Button btnSignUp;
    private MaterialDialog dialog;
    private SignUpInterface.Presentador presentador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        presentador= new SignUpPresentador(this);
        txtCorreo=findViewById(R.id.txtCorreo);
        txtPassword=findViewById(R.id.txtPassword);
        txtConfirmarPassword=findViewById(R.id.txtConfirmarPassword);
        btnSignUp=findViewById(R.id.btnSignUp);
        MaterialDialog.Builder builder = new MaterialDialog.Builder(this)
                .title("Cargando")
                .content("Espere por favor...")
                .cancelable(false)
                .progress(true,200);
        dialog=builder.build();



    }

    public void onRegistrarse(View view){
        validarSignUp();
    }

    private void ingresarDatos(boolean enable){
        txtCorreo.setEnabled(enable);
        txtPassword.setEnabled(enable);
        txtConfirmarPassword.setEnabled(enable);
        btnSignUp.setEnabled(enable);
    }


    @Override
    public void habilitarInputs() {
        ingresarDatos(true);

    }

    @Override
    public void deshabilitarInputs() {
        ingresarDatos(false);

    }

    @Override
    public void mostrarProgreso() {
        dialog.show();
    }

    @Override
    public void ocultarProgreso() {
        dialog.dismiss();
    }

    @Override
    public boolean validarVista() {
        boolean retValue= true;
        if(TextUtils.isEmpty(txtCorreo.getText())){
            txtCorreo.setError("Este campo es obligatorio");
            retValue=false;
        } else if(!Patterns.EMAIL_ADDRESS.matcher(txtCorreo.getText().toString().trim()).matches()){
            txtCorreo.setError("Correo no valido");
            retValue=false;
        }

        /*if(TextUtils.isEmpty(txtCelular.getText())){
            txtCelular.setError("Este campo es obligatorio");
            retValue=false;
        }else if(!PhoneNumberUtils.isGlobalPhoneNumber(txtCelular.getText().toString().trim())){
            txtCelular.setError("Numero no válido");
            retValue=false;
       } */
        if(TextUtils.isEmpty(txtPassword.getText())){
            txtPassword.setError("Este campo es obligatorio");
            retValue=false;
        } else if(txtPassword.getText().toString().length()<6){
            txtPassword.setError("La contraseña debe tener al menos 6 digitos");
            retValue=false;
        }
        if(TextUtils.isEmpty(txtConfirmarPassword.getText())){
            txtConfirmarPassword.setError("Este campo es obligatorio");
            retValue=false;
        } else if(!txtPassword.getText().toString().trim().equals(txtConfirmarPassword.getText().toString().trim())){
            txtConfirmarPassword.setError("Las contraseñas no coinciden");
            retValue=false;
        }
        return retValue;
    }

    @Override
    public void validarSignUp() {
        if(validarVista()){
            presentador.doSignUp(txtCorreo.getText().toString().trim(), txtPassword.getText().toString().trim());
            presentador.toLogin(txtCorreo.getText().toString().trim(), txtPassword.getText().toString().trim());
        }

    }

    @Override
    public void onError(String error) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT);
    }

    @Override
    public void onLogin() {
        startActivity(new Intent(this, CuentaCreadaActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presentador.onDestroy();
    }
}