package com.example.proveedormovil;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proveedormovil.Api.ApiClient;
import com.example.proveedormovil.Api.ApiService;
import com.example.proveedormovil.adapters.ProveedoresAdapter;
import com.example.proveedormovil.models.Proveedor;
import com.example.proveedormovil.models.ProveedoresResponse;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProveedoresActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProveedoresAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proveedores);

        // Inicializa la variable de instancia
        recyclerView = findViewById(R.id.recyclerViewProveedores);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String token = getIntent().getStringExtra("token");
        fetchProveedores(token);
    }

    private void fetchProveedores(String token) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<ProveedoresResponse> call = apiService.getProveedores("Bearer " + token);

        call.enqueue(new Callback<ProveedoresResponse>() {
            @Override
            public void onResponse(Call<ProveedoresResponse> call, Response<ProveedoresResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Proveedor> proveedores = response.body().getData();
                    adapter = new ProveedoresAdapter(proveedores);
                    recyclerView.setAdapter(adapter); // Se utiliza la variable de instancia correctamente inicializada
                } else {
                    Toast.makeText(ProveedoresActivity.this, "Error al obtener proveedores", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProveedoresResponse> call, Throwable t) {
                Log.e("ProveedoresActivity", "Error: " + t.getMessage());
                Toast.makeText(ProveedoresActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}