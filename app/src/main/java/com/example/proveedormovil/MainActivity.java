package com.example.proveedormovil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proveedormovil.Api.ApiClient;
import com.example.proveedormovil.Api.ApiService;
import com.example.proveedormovil.models.LoginRequest;
import com.example.proveedormovil.models.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText emailEditText, passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        LoginRequest loginRequest = new LoginRequest(email, password);

        Call<LoginResponse> call = apiService.login(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String message = response.body().getMessage();
                    String token = response.body().getToken();
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this, ProveedoresActivity.class);
                    intent.putExtra("token", token);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("MainActivity", "Error: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}