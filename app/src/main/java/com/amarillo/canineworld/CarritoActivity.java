package com.amarillo.canineworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class CarritoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);


        ImageButton back = (ImageButton)findViewById(R.id.imageButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        Button btn1 = (Button)findViewById(R.id.continuar);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CarritoActivity.this, DireccionesActivity.class));
            }
        });

        ImageButton art = (ImageButton)findViewById(R.id.art1);

        art.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CarritoActivity.this, DescripcionArticuloActivity.class));
            }
        });

        ImageButton art2 = (ImageButton)findViewById(R.id.art2);

        art2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CarritoActivity.this, DescripcionArticuloActivity.class));
            }
        });

        ImageButton art3 = (ImageButton)findViewById(R.id.art3);

        art3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CarritoActivity.this, DescripcionArticuloActivity.class));
            }
        });

        ImageButton art4 = (ImageButton)findViewById(R.id.art4);

        art4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CarritoActivity.this, DescripcionArticuloActivity.class));
            }
        });


    }
}