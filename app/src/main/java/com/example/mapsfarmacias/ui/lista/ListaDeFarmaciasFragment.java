package com.example.mapsfarmacias.ui.lista;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mapsfarmacias.R;
import com.example.mapsfarmacias.databinding.FragmentListaDeFarmaciasBinding;
import com.example.mapsfarmacias.modelos.Farmacia;

import java.util.ArrayList;

public class ListaDeFarmaciasFragment extends Fragment {
    private FragmentListaDeFarmaciasBinding binding;
    private ListaDeFarmaciasViewModel mViewModel;

    public static ListaDeFarmaciasFragment newInstance() {
        return new ListaDeFarmaciasFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(ListaDeFarmaciasViewModel.class);
        binding = FragmentListaDeFarmaciasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mViewModel.getMCiudad().observe(getViewLifecycleOwner(), new Observer<ArrayList<Farmacia>>() {
            @Override
            public void onChanged(ArrayList<Farmacia> farmacias) {
                FarmaciaAdapter fa = new FarmaciaAdapter(farmacias, inflater);
                GridLayoutManager glm = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
                binding.rv.setAdapter(fa);
                binding.rv.setLayoutManager(glm);
            }
        });

        mViewModel.cargarLista();

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ListaDeFarmaciasViewModel.class);
        // TODO: Use the ViewModel
    }

}