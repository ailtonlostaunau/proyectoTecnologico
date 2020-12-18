package com.example.tuchamba.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tuchamba.BarraMenuActivity;
import com.example.tuchamba.MapsActivity;
import com.example.tuchamba.activity_perfil;
import com.example.tuchamba.perfilDatos.perfilDatosPresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static androidx.core.content.ContextCompat.startActivity;


public class GalleryViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private TextView txtMensaje;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseUser user;
    public String solicitud = "";

    public GalleryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Modo Cliente");
    }

    public LiveData<String> getText() {
        return mText;
    }


}