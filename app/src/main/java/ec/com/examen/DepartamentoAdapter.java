package ec.com.examen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class DepartamentoAdapter extends FirestoreRecyclerAdapter<DepartamentoEntity, DepartamentoAdapter.ViewHolder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public DepartamentoAdapter(@NonNull FirestoreRecyclerOptions<DepartamentoEntity> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull DepartamentoEntity model) {
        DocumentSnapshot documentSnapshot = getSnapshots().getSnapshot(holder.getAdapterPosition());
        holder.tvDescripcion.setText(model.getDescripcion());
        holder.tvPrecio.setText(model.getCosto());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_departamento, parent, false);
        return new ViewHolder(v);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDescripcion,tvPrecio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDescripcion = itemView.findViewById(R.id.tv_descripcion);
            tvPrecio = itemView.findViewById(R.id.tv_precio);
        }
    }
}
