package com.example.tuchamba.ui.gallery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import com.example.tuchamba.BarraMenuActivity;
import com.example.tuchamba.MapsActivity;
import com.example.tuchamba.R;
import com.theartofdev.edmodo.cropper.CropImage;

import static androidx.core.content.ContextCompat.startActivity;


public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private Button btnMapa;
    private View rootView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        btnMapa = (Button) root.findViewById(R.id.btnMapa);
        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent=new Intent(getActivity(),MapsActivity.class);
                startActivity(myIntent);
            }
        });

        return root;
    }

}

