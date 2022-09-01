package ec.com.examen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rvListado;
    Button btnCrear;
    Query query;
    FirebaseFirestore mFirestore;
    DepartamentoAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCrear = findViewById(R.id.btn_crear);
        rvListado = findViewById(R.id.rv_Listado);
        btnCrear.setOnClickListener(this);
        mFirestore = FirebaseFirestore.getInstance();
        llenarRecyclerDepartamento();
    }

    private void llenarRecyclerDepartamento() {
        rvListado.setLayoutManager(new LinearLayoutManager(this));

        query = mFirestore.collection("departamento");
        FirestoreRecyclerOptions<DepartamentoEntity> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<DepartamentoEntity>()
                .setQuery(query, DepartamentoEntity.class).build();
        mAdapter = new DepartamentoAdapter(firestoreRecyclerOptions);
        mAdapter.notifyDataSetChanged();
        rvListado.setAdapter(mAdapter);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_crear:
                llamarALaSiguienteActividad();
                break;
        }
    }

    private void llamarALaSiguienteActividad() {
        Intent intent = new Intent(this, CrearDepartamentoActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }
}