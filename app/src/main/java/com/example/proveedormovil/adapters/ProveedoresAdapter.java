package com.example.proveedormovil.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proveedormovil.R;
import com.example.proveedormovil.models.Proveedor;

import java.util.List;

public class ProveedoresAdapter extends RecyclerView.Adapter<ProveedoresAdapter.ViewHolder> {
    private List<Proveedor> proveedores;

    public ProveedoresAdapter(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_proveedor, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Proveedor proveedor = proveedores.get(position);
        holder.nombreTextView.setText(proveedor.getNombreProv());
        holder.direccionTextView.setText(proveedor.getDireccionProv());
        holder.telefonoTextView.setText(proveedor.getTelefonoProv());
    }

    @Override
    public int getItemCount() {
        return proveedores.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTextView, direccionTextView, telefonoTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
            direccionTextView = itemView.findViewById(R.id.direccionTextView);
            telefonoTextView = itemView.findViewById(R.id.telefonoTextView);
        }
    }
}