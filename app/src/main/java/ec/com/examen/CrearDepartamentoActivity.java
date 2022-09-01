package ec.com.examen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CrearDepartamentoActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etDescripcion, etDireccion, etTelefono, etCosto;
    Button btnGuardar;
    FirebaseFirestore mFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_departamento);
        etDescripcion = findViewById(R.id.et_descripcion);
        etDireccion = findViewById(R.id.et_direccion);
        etTelefono = findViewById(R.id.et_telefono);
        etCosto = findViewById(R.id.et_costo);
        btnGuardar = findViewById(R.id.btn_guardar);
        btnGuardar.setOnClickListener(this);

        mFirestore = FirebaseFirestore.getInstance();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_guardar:
                guardarDepartamento();
        }
    }

    private void guardarDepartamento() {
        String descripcion = etDescripcion.getText().toString();
        String direccion = etDireccion.getText().toString();
        String telefono = etTelefono.getText().toString();
        String costo = etCosto.getText().toString();
        if(descripcion == null && descripcion.length() < 1){
            Toast.makeText(this, "El usuario no ha digitado la descripción", Toast.LENGTH_SHORT).show();
            return;
        }
        if (direccion == null && direccion.length() < 1){
            Toast.makeText(this, "El usuario no ha digitado la direccion", Toast.LENGTH_SHORT).show();
            return;
        }
        if (telefono == null && telefono.length() < 1){
            Toast.makeText(this, "El usuario no ha digitado el teléfono", Toast.LENGTH_SHORT).show();
            return;
        }
        if (costo == null && costo.length() < 1){
            Toast.makeText(this, "El usuario no ha digitado el costo", Toast.LENGTH_SHORT).show();
            return;
        }
        DocumentReference _id = mFirestore.collection("departamento").document();
        Map<String, Object> map = new HashMap<>();
        map.put("id", _id);
        map.put("descripcion", descripcion);
        map.put("direccion", direccion);
        map.put("telefono", telefono);
        map.put("costo", costo);

        mFirestore.collection("departamento")
                .document(_id.getId())
                .set(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(), "Creado exitosamente", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error al crear la mascota", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}