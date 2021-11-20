package com.amarillo.canineworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Main_venta extends AppCompatActivity {



    private ArrayList<ModelProductos> productList;
    private AllProducts allProducts;

    private FirebaseAuth firebaseAuth;

    private RelativeLayout rlProducts;

    private TextView tablaProductos;
    private EditText busquedaProductos;
    private RecyclerView productoslista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_venta);

        rlProducts = findViewById(R.id.rlProducts);
        busquedaProductos = findViewById(R.id.busquedaProductos);
        tablaProductos = findViewById(R.id.tablaProductos);
        productoslista = findViewById(R.id.productosLista);

        firebaseAuth = FirebaseAuth.getInstance();
        loadAllProducts();
        showProductsUi();

        ImageButton more= (ImageButton) findViewById(R.id.masPr);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main_venta.this, Productos.class));
            }
        });

        busquedaProductos.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    allProducts.getFilter().filter(s);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void showProductsUi() {
        rlProducts.setVisibility(View.VISIBLE);
        tablaProductos.setBackgroundResource(R.color.blancoCanino);
    }

    private void loadAllProducts() {
        productList = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Productos");
        reference.child("Products")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        productList.clear();
                        for (DataSnapshot ds: snapshot.getChildren()){
                            ModelProductos modelProductos = ds.getValue(ModelProductos.class);
                            productList.add(modelProductos);
                        }
                        allProducts = new AllProducts(Main_venta.this, productList);
                        productoslista.setAdapter(allProducts);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }


}