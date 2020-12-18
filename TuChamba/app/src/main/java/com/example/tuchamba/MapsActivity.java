package com.example.tuchamba;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        database = FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = database.getReference("users").child(user.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String nombre = (String) dataSnapshot.child("nombre").getValue();
                    String apellido = (String) dataSnapshot.child("apellido").getValue();
                    String area = (String) dataSnapshot.child("area").getValue();
                    String profesion = (String) dataSnapshot.child("profesion").getValue();
                    String latitud= (String) dataSnapshot.child("latitud").getValue();
                    String longitud = (String) dataSnapshot.child("longitud").getValue();
                    String telefono = (String) dataSnapshot.child("telefono").getValue();

                    LatLng tra1 = new LatLng( Double.parseDouble(latitud), Double.parseDouble(longitud));
                    mMap.addMarker(new MarkerOptions().position(tra1).title(nombre + " " + apellido + " Tel: " + telefono).snippet(area + ": " + profesion ).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(tra1));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        // Add a marker in Sydney and move the camera
        LatLng tra1 = new LatLng(-11.946282, -77.0909513);
        mMap.addMarker(new MarkerOptions().position(tra1).title("Ailton Lostaunau").snippet("Construccion: Jefe de obra").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tra1));

        LatLng tra2 = new LatLng(-12.03435, -76.925654);
        mMap.addMarker(new MarkerOptions().position(tra2).title("David Carranza").snippet("Salud: medico").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        LatLng tra3 = new LatLng(-12.047440378763184, -76.97190283642192);
        mMap.addMarker(new MarkerOptions().position(tra3).title("Proyecto Tecnológico").snippet("Enseñanza: profesor").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

    }
}