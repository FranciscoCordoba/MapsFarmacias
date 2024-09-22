package com.example.mapsfarmacias.ui.mapa;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mapsfarmacias.R;
import com.example.mapsfarmacias.databinding.FragmentListaDeFarmaciasBinding;
import com.example.mapsfarmacias.databinding.FragmentMapsBinding;
import com.example.mapsfarmacias.ui.lista.ListaDeFarmaciasViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Objects;

public class MapsFragment extends Fragment {

    private MapsFragmentViewModel vm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        solicitarPermisos();
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        vm = new ViewModelProvider(this).get(MapsFragmentViewModel.class);
        vm.obtenerUltimaUbicacion();
        vm.getMMapaActual().observe(getViewLifecycleOwner(), new Observer<MapsFragmentViewModel.MapaActual>() {
            @Override
            public void onChanged(MapsFragmentViewModel.MapaActual mapaActual) {
                if(mapaActual != null) {
                    SupportMapFragment mapFragment =
                            (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
                    if (mapFragment != null) {
                        mapFragment.getMapAsync(Objects.requireNonNull(vm.getMMapaActual().getValue())); //Seteamos el mapa actual
                    }
                }
            }
        });
        vm.getMUltimaUbicacion().observe(getViewLifecycleOwner(), new Observer<LatLng>() {
            @Override
            public void onChanged(LatLng latLng) {
                vm.obtenerMapa();
            }
        });
        vm.obtenerUltimaUbicacion();
    }

    private void solicitarPermisos(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && (getContext().checkSelfPermission(ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) ||
                (getContext().checkSelfPermission(ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)){
            requestPermissions(new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION}, 1000); //El segundo parametro es un código para identificar el permiso, es arbitrario
        }
    }
}