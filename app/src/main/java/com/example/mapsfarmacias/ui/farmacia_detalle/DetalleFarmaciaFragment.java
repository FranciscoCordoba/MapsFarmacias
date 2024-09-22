package com.example.mapsfarmacias.ui.farmacia_detalle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mapsfarmacias.R;
import com.example.mapsfarmacias.databinding.FragmentDetalleFarmaciaBinding;
import com.example.mapsfarmacias.modelos.Farmacia;

public class DetalleFarmaciaFragment extends Fragment {

    private FragmentDetalleFarmaciaBinding binding;
    private DetalleFarmaciaViewModel mViewModel;

    public static DetalleFarmaciaFragment newInstance() {
        return new DetalleFarmaciaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(DetalleFarmaciaViewModel.class);
        binding = FragmentDetalleFarmaciaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mViewModel.getMFarmacia().observe(getViewLifecycleOwner(), new Observer<Farmacia>() {
            @Override
            public void onChanged(Farmacia farmacia) {
                binding.img.setImageResource(farmacia.getImg());
                binding.tvNombre.setText(farmacia.getNombre());
                binding.tvDireccion.setText(farmacia.getDireccion());
                binding.tvHorario.setText(farmacia.getHorarios());
            }
        });
        mViewModel.recibeFarmacia(getArguments());
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetalleFarmaciaViewModel.class);
        // TODO: Use the ViewModel
    }

}