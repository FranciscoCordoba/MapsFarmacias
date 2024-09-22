package com.example.mapsfarmacias.ui.lista;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mapsfarmacias.R;
import com.example.mapsfarmacias.modelos.Farmacia;

import java.io.Serializable;
import java.util.List;

public class FarmaciaAdapter extends RecyclerView.Adapter<FarmaciaAdapter.ViewHolderFarmacia>{

    private List<Farmacia> farmacias;
    private LayoutInflater li;

    public FarmaciaAdapter(List<Farmacia> farmacias, LayoutInflater li){
        this.farmacias = farmacias;
        this.li = li;
    }

    @NonNull
    @Override
    public ViewHolderFarmacia onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = li.inflate(R.layout.item, parent, false);
        return new ViewHolderFarmacia(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFarmacia holder, int position) {
        Farmacia farm = farmacias.get(position);
        holder.tvDireccion.setText(farm.getDireccion());
        holder.tvNombre.setText(farm.getNombre());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("elegida", farm);
                Navigation.findNavController(view).navigate(R.id.detalleFarmaciaFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return farmacias.size();
    }

    public class ViewHolderFarmacia extends RecyclerView.ViewHolder {

        TextView tvNombre, tvDireccion;

        public ViewHolderFarmacia(@NonNull View itemView) {
            super(itemView);

            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
        }
    }

}
