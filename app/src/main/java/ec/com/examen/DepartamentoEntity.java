package ec.com.examen;

import com.google.firebase.firestore.DocumentReference;

public class DepartamentoEntity {
    private String costo;
    private String descripcion;
    private String direccion;
    private DocumentReference id;
    private String telefono;

    public DepartamentoEntity() {}

    public DepartamentoEntity(String costo, String descripcion, String direccion, DocumentReference id, String telefono) {
        this.costo = costo;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.id = id;
        this.telefono = telefono;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public DocumentReference getId() {
        return id;
    }

    public void setId(DocumentReference id) {
        this.id = id;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
