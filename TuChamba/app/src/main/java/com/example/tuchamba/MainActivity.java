package com.example.tuchamba;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.tuchamba.login.LoginInterface;
import com.example.tuchamba.login.LoginPresenter;

public class MainActivity extends Activity implements LoginInterface.View {
    private EditText txtCorreo, txtPassword;
    private Button btnLogin;
    private MaterialDialog dialog;
    private LoginInterface.Presentador presentar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCorreo=findViewById(R.id.txtCorreo);
        txtPassword=findViewById(R.id.txtPassword);
        btnLogin=findViewById(R.id.btnLogin);
        MaterialDialog.Builder builder= new MaterialDialog.Builder(this)
                .title("Cargando")
                .content("Espere, por favor...")
                .cancelable(false)
                .progress(true, 0);
        dialog = builder.build();
        presentar= new LoginPresenter(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarLogin();
            }
        });
    }

    private void ingresarDatos(boolean enable){
        txtCorreo.setEnabled(enable);
        txtPassword.setEnabled(enable);
        btnLogin.setEnabled(enable);
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
    public void validarLogin() {
        if(!esValidoEmail()){
            Toast.makeText(this,"Correo no válido",Toast.LENGTH_SHORT).show();
        }else if(!esValidoClave()){
            Toast.makeText(this,"Clave no válida", Toast.LENGTH_SHORT).show();
        }else{
            presentar.toLogin(txtCorreo.getText().toString().trim(), txtPassword.getText().toString().trim());
        }

    }

    @Override
    public boolean esValidoEmail() {
        //indica que es un formato valido
        return Patterns.EMAIL_ADDRESS.matcher(txtCorreo.getText().toString()).matches();
    }

    @Override
    public boolean esValidoClave() {
        if(TextUtils.isEmpty(txtPassword.getText().toString()) || txtPassword.getText().toString().length()<6){
            txtPassword.setError("La contraseña debe ser mayor de 5 caracteres");
            return false;
        }else {
            return true;
        }

    }

    @Override
    public void onLogin() {
        Toast.makeText(this,"Hiciste login correctamente",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, BarraMenuActivity.class));
        finish();
    }

    @Override
    public void onError(String error) {
        Toast.makeText(this, "Contraseña o correo incorrecta intenta nuevamente", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presentar.onDestroy();
    }

    public void onRegistrarse(View view){
        startActivity(new Intent(this, SignUpActivity.class));
    }
}