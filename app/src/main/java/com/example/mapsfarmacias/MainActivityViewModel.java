package com.example.mapsfarmacias;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainActivityViewModel extends AndroidViewModel {

    private MutableLiveData<String> mMensaje;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getMMensaje(){
        if(mMensaje == null){
            mMensaje = new MutableLiveData<>();
        }
        return mMensaje;
    }

    public void validarLogin(String usuario, String contrasenia){
        if(usuario.equals("Persona") && contrasenia.equals("123")){
            Intent intent = new Intent(getApplication(), MenuActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplication().startActivity(intent);
        }else{
            mMensaje.setValue("Usuario y/o contraseña incorrectos");
        }
    }


}
