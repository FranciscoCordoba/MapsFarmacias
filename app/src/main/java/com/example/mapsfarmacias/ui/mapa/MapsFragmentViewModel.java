package com.example.mapsfarmacias.ui.mapa;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.location.Location;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MapsFragmentViewModel extends AndroidViewModel {
    private MutableLiveData<MapaActual> mMapaActual;
    private MutableLiveData<LatLng> mUltimaUbicacion;
    private FusedLocationProviderClient fused;

    public MapsFragmentViewModel(@NonNull Application application) {super(application);}

    public LiveData<MapaActual> getMMapaActual() {
        if (mMapaActual == null) {
            mMapaActual = new MutableLiveData<>();
        }
        return mMapaActual;
    }

    public LiveData<LatLng> getMUltimaUbicacion() {
        if (mUltimaUbicacion == null) {
            mUltimaUbicacion = new MutableLiveData<>();
        }
        return mUltimaUbicacion;
    }

    public void obtenerMapa() {
        MapaActual mapaActual = new MapaActual();
        mMapaActual.setValue(mapaActual);
    }

    public void obtenerUltimaUbicacion() {
        if (ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fused = LocationServices.getFusedLocationProviderClient(getApplication());
        fused.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null){
                    mUltimaUbicacion.postValue(new LatLng(location.getLatitude(),location.getLongitude()));
                }
            }
        });
    }

    public class MapaActual implements OnMapReadyCallback {

        LatLng FARMACITY_CASEROS = new LatLng(-33.3003199,-66.3427563);
        LatLng FARMACITY_JUNIN = new LatLng(-33.3015194,-66.3371757);
        LatLng FARMACIA_LOS_ALAMOS = new LatLng(-33.3026057,-66.3362217);
        LatLng FARMACIA_QUINTANA_3 = new LatLng(-33.2856896,-66.3429384);
        LatLng FARMACIA_AMERICANA = new LatLng(-33.2890922,-66.3475774);
        LatLng MI_UBICACION = mUltimaUbicacion.getValue();

        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            if (ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            googleMap.setMyLocationEnabled(true);
            googleMap.addMarker(new MarkerOptions().position(FARMACITY_CASEROS).title("Farmacity"));
            googleMap.addMarker(new MarkerOptions().position(FARMACITY_JUNIN).title("Farmacity"));
            googleMap.addMarker(new MarkerOptions().position(FARMACIA_LOS_ALAMOS).title("Farmacia Los Alamos"));
            googleMap.addMarker(new MarkerOptions().position(FARMACIA_QUINTANA_3).title("Farmacia Quintana III"));
            googleMap.addMarker(new MarkerOptions().position(FARMACIA_AMERICANA).title("Farmacia Americana"));

            CameraPosition cameraPosition =
                    new CameraPosition.Builder()
                            .target(MI_UBICACION) //Ubicacion a observar
                            .zoom(16)    //Zoom en 16
                            .bearing(0) //Establecemos la orientacion con el norte
                            .tilt(30)    //Bajamos el punto de vista de la cámara 30 grados
                            .build();    //Metodo que finaliza con la personalizacion y crea el CameraPosition
            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
            googleMap.animateCamera(cameraUpdate);
        }
    }
}
