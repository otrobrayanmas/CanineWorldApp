package com.amarillo.canineworld;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ContentInfoCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.appsearch.StorageInfo;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.HashSet;

public class Productos extends AppCompatActivity {

    private ImageButton backbtn;
    private ImageView imagenproducto;
    private EditText titulo, descripcion,fabricante;
    private TextView precio,descuentoPre,descuentoPor, categorias;
    private SwitchCompat descuentoSwitch;
    private Button agregarPr;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    private static final int CAMERA_REQUEST_CODE = 200;
    private static final int STORAGE_REQUEST_CODE = 300;

    private static final int IMAGE_PICK_GALLERY_CODE = 400;
    private static final int IMAGE_PICK_CAMERA_CODE = 400;

    private String[] cameraPermissions;
    private String[] storagePermissions;

    private Uri image_uri;

    ActivityResultLauncher<Intent> activityResultLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        backbtn = findViewById(R.id.backbtn);
        titulo = findViewById(R.id.tituloart);
        imagenproducto = findViewById(R.id.imagenProducto);
        descripcion = findViewById(R.id.descripcionart);
        fabricante = findViewById(R.id.fabricanteart);
        precio = findViewById(R.id.valorart);
        descuentoSwitch = findViewById(R.id.descuento);
        descuentoPor = findViewById(R.id.descuentoPor);
        descuentoPre = findViewById(R.id.descuentoPre);
        agregarPr = findViewById(R.id.AddProductobtn);
        categorias = findViewById(R.id.categoria);

        descuentoPre.setVisibility(View.GONE);
        descuentoPor.setVisibility(View.GONE);

        cameraPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        descuentoSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if(b){
                    descuentoPre.setVisibility(View.VISIBLE);
                    descuentoPor.setVisibility(View.VISIBLE);
                }
                else{
                    descuentoPre.setVisibility(View.GONE);
                    descuentoPor.setVisibility(View.GONE);
                }
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        imagenproducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImagePickDialog();
            }
        });

        categorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryDialog();
            }
        });

        agregarPr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputData();
            }
        });


        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setCanceledOnTouchOutside(false);

    }

    private String productoTitulo, productoDescripcion, productoCategoria, productoValor, productoFabricante, descuentoPrecio, porcentajeDesc;
    private boolean discountAvailable = false;

    private void inputData() {
        productoTitulo = titulo.getText().toString().trim();
        productoDescripcion = descripcion.getText().toString().trim();
        productoCategoria = categorias.getText().toString().trim();
        productoValor = precio.getText().toString().trim();
        productoFabricante = fabricante.getText().toString().trim();

        discountAvailable = descuentoSwitch.isChecked();

        if(TextUtils.isEmpty(productoTitulo)){
            Toast.makeText(this,"Title is requerid", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(productoCategoria)){
            Toast.makeText(this,"Category is requerid", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(productoValor)){
            Toast.makeText(this,"Price is requerid", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(productoFabricante)){
            Toast.makeText(this,"Fabricante is requerid", Toast.LENGTH_SHORT).show();
            return;
        }
        if(discountAvailable){
            descuentoPrecio = descuentoPre.getText().toString().trim();
            porcentajeDesc = descuentoPor.getText().toString().trim();
            if(TextUtils.isEmpty(descuentoPrecio)){
                Toast.makeText(this,"Discount Price is requerid", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        else{
            descuentoPrecio = "0";
            porcentajeDesc = "";
        }

        anadirProducto();
    }

    private void anadirProducto() {
        progressDialog.setMessage("Añadiendo Producto...");
        progressDialog.show();

        String timestamp = ""+System.currentTimeMillis();

        if(image_uri == null){
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("productId",""+timestamp);
            hashMap.put("productTitle",""+productoTitulo);
            hashMap.put("productDescripción",""+productoDescripcion);
            hashMap.put("productCategoria",""+productoCategoria);
            hashMap.put("productValor",""+productoValor);
            hashMap.put("productDescPrecio",""+descuentoPrecio);
            hashMap.put("productDescPor",""+porcentajeDesc);
            hashMap.put("discountAvailable",""+discountAvailable);
            hashMap.put("timestamp",""+timestamp);
            hashMap.put("uid",""+firebaseAuth.getUid());

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
            reference.child(firebaseAuth.getUid()).child("Products").child(timestamp).setValue(hashMap)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            progressDialog.dismiss();
                            Toast.makeText(Productos.this, "Producto Agregado",Toast.LENGTH_SHORT).show();
                            clearData();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(Productos.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        else{

            String filePathAndName = "product_images/"+""+timestamp;
            StorageReference storageReference = FirebaseStorage.getInstance().getReference();
            storageReference.putFile(image_uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                            while(!uriTask.isSuccessful());
                            Uri donwloadImageUrl = uriTask.getResult();

                            if(uriTask.isSuccessful()){

                                HashMap<String, Object> hashMap = new HashMap<>();
                                hashMap.put("productId",""+timestamp);
                                hashMap.put("productTitle",""+productoTitulo);
                                hashMap.put("productDescripción",""+productoDescripcion);
                                hashMap.put("productCategoria",""+productoCategoria);
                                hashMap.put("productValor",""+productoValor);
                                hashMap.put("productDescPrecio",""+descuentoPrecio);
                                hashMap.put("productDescPor",""+porcentajeDesc);
                                hashMap.put("discountAvailable",""+discountAvailable);
                                hashMap.put("timestamp",""+timestamp);
                                hashMap.put("uid",""+firebaseAuth.getUid());

                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                                reference.child(firebaseAuth.getUid()).child("Products").child(timestamp).setValue(hashMap)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                progressDialog.dismiss();
                                                Toast.makeText(Productos.this, "Producto Agregado",Toast.LENGTH_SHORT).show();
                                                clearData();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                progressDialog.dismiss();
                                                Toast.makeText(Productos.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(Productos.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void clearData(){
        titulo.setText("");
        descripcion.setText("");
        categorias.setText("");
        precio.setText("");
        fabricante.setText("");
        descuentoPre.setText("");
        descuentoPor.setText("");
        imagenproducto.setImageResource(R.drawable.ic_baseline_add_shopping_cart_24);
        image_uri = null;
    }

    private void categoryDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Product Category")
                .setItems(Constantes.productosCategorias, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        String categoria = Constantes.productosCategorias[i];

                        categorias.setText(categoria);
                    }
                })
                .show();

    }

    private void showImagePickDialog() {
        String [] options = {"Camera","Gallery"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick Image")
                .setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        if (which == 0){
                            if(checkCameraPermission()){
                                pickFromCamera();
                            }
                            else{
                                requestCameraPermission();
                            }
                        }
                        else{
                            if(checkStoragePermission()){
                                pickFromGallery();
                            }
                            else{
                                requestStoragePermission();
                            }
                        }
                    }
                })
                .show();
    }

    private void pickFromGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_GALLERY_CODE);
    }

    private void pickFromCamera(){

        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE,"Temp_Image_Title");
        contentValues.put(MediaStore.Images.Media.DESCRIPTION,"Temp_Image_Description");

        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(intent, IMAGE_PICK_CAMERA_CODE);
    }

    private boolean checkStoragePermission(){
        boolean result = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    private void requestStoragePermission(){
        ActivityCompat.requestPermissions(this,storagePermissions,STORAGE_REQUEST_CODE);
    }

    private boolean checkCameraPermission(){
        boolean result = ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA) ==
                (PackageManager.PERMISSION_GRANTED);

        boolean result1 = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                (PackageManager.PERMISSION_GRANTED);

        return result && result1;
    }

    private void requestCameraPermission(){
        ActivityCompat.requestPermissions(this,cameraPermissions, CAMERA_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case CAMERA_REQUEST_CODE:{
                if(grantResults.length>0){
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted && storageAccepted){
                        pickFromCamera();
                    }
                    else{
                        Toast.makeText(this, "Camera & Storage permissions are requerid",Toast.LENGTH_SHORT).show();
                    }
                }
            }
            case STORAGE_REQUEST_CODE:{
                if(grantResults.length>0){
                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if(storageAccepted){
                        pickFromGallery();
                    }
                    else{
                        Toast.makeText(this, "Storage permissions are requerid",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK){
            if(requestCode == IMAGE_PICK_GALLERY_CODE){

                image_uri = data.getData();

                imagenproducto.setImageURI(image_uri);
            }
            else if(requestCode == IMAGE_PICK_CAMERA_CODE){
                imagenproducto.setImageURI(image_uri);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}