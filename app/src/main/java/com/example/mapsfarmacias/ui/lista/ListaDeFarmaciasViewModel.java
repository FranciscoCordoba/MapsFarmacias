package com.example.mapsfarmacias.ui.lista;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mapsfarmacias.R;
import com.example.mapsfarmacias.modelos.Farmacia;

import java.util.ArrayList;

public class ListaDeFarmaciasViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<Farmacia>> mFarmacias;
    private ArrayList<Farmacia> farmacias = new ArrayList<>();

    public ListaDeFarmaciasViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ArrayList<Farmacia>> getMCiudad(){
        if(mFarmacias == null){
            mFarmacias= new MutableLiveData<>();
        }
        return mFarmacias;
    }

    public void cargarLista(){
        farmacias.clear();
        farmacias.add(new Farmacia("Farmacity", "Abierto las 24 horas", R.drawable.farmacity_junin, "Junin 952"));
        farmacias.add(new Farmacia("Farmacity", "Abierto las 24 horas", R.drawable.farmacity_caseros, "Caseros 925"));
        farmacias.add(new Farmacia("Farmacia Quintana III", "Todos los días de 9:00 a 14:00", R.drawable.farmacia_quintana_3, "Av. Italia 2095"));
        farmacias.add(new Farmacia("Farmacia Los Alamos", "Abierto las 24 horas", R.drawable.farmacia_los_alamos, "Pringles 911"));
        farmacias.add(new Farmacia("Farmacia Americana", "Desconocido", R.drawable.farmacia_americana, "Falucho 1800"));
        mFarmacias.setValue(farmacias);
    }


}