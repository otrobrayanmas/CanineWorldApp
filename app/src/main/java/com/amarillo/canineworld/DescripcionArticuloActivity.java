package com.amarillo.canineworld;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class DescripcionArticuloActivity extends AppCompatActivity {

    private StorageReference mStorage;
    private final int GALLERY_INTENT = 1;
    private ImageView mImageView;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion_articulo);

        mStorage = FirebaseStorage.getInstance().getReference();
        mImageView = findViewById(R.id.imagenDesccripcion);
        progressDialog = new ProgressDialog(this);


        ImageButton back = (ImageButton)findViewById(R.id.imageButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Button btn = (Button)findViewById(R.id.agregarcar);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DescripcionArticuloActivity.this, CarritoActivity.class));
            }
        });

        Button btn1 = (Button)findViewById(R.id.comprarahora);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DescripcionArticuloActivity.this, DireccionesActivity.class));
            }
        });
    }


}