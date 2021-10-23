package com.amarillo.canineworld;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrarseActivity extends AppCompatActivity {

    EditText email, contrasena, nombre;
    String emailPatter = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        email = findViewById(R.id.editTextTextEmailAddress);
        contrasena = findViewById(R.id.edit_password);
        nombre = findViewById(R.id.editTextTextNombreAddress);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        ImageButton back = (ImageButton)findViewById(R.id.imageButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        TextView btn = (TextView) findViewById(R.id.textView11);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrarseActivity.this, IniciarSesionActivity.class));
            }
        });


        Button btn1 = (Button)findViewById(R.id.button2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerforAuth();
            }
        });
    }

    private void PerforAuth() {
        String email1 = email.getText().toString();
        String contrasena1 = contrasena.getText().toString();
        String nombre1 = nombre.getText().toString();

        if(!email1.matches(emailPatter)){
            email.setError("Enter connext email");
        }else if(contrasena1.isEmpty() || contrasena1.length()<6){
            contrasena.setError("Enter proper password");
        }else if(nombre1.isEmpty()){
            nombre.setError("El nombre no puede estar vacio");
        }else{
            progressDialog.setMessage("Registrandose....");
            progressDialog.setTitle("Registrado");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email1,contrasena1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(RegistrarseActivity.this,"Registro completado", Toast.LENGTH_SHORT);
                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(RegistrarseActivity.this,""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void sendUserToNextActivity() {
     Intent intent = new Intent(RegistrarseActivity.this, IniciarSesionActivity.class);
     intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
     startActivity(intent);
    }
}