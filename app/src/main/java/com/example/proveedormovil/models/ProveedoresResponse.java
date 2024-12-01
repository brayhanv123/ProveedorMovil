package com.example.proveedormovil.models;

import java.util.List;

public class ProveedoresResponse {
    private String status;
    private List<Proveedor> data;

    public String getStatus() {
        return status;
    }

    public List<Proveedor> getData() {
        return data;
    }
}