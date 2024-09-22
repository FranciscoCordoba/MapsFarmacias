package com.example.mapsfarmacias.ui.farmacia_detalle;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mapsfarmacias.modelos.Farmacia;

public class DetalleFarmaciaViewModel extends AndroidViewModel {
    private MutableLiveData<Farmacia> mFarmacia;

    public DetalleFarmaciaViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Farmacia> getMFarmacia(){
        if(mFarmacia == null){
            mFarmacia = new MutableLiveData<>();
        }
        return mFarmacia;
    }

    public void recibeFarmacia(Bundle bundle){
        Farmacia farm = bundle.getSerializable("elegida",  Farmacia.class);
        if(farm != null){
            mFarmacia.setValue(farm);
        }
    }
}