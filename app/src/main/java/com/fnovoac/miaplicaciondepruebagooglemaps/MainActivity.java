package com.fnovoac.miaplicaciondepruebagooglemaps;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    int PLACE_PICKER_REQUEST = 1;

    TextView texto;
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto = (TextView) findViewById(R.id.texto);
        boton = (Button) findViewById(R.id.button);
        boton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                startPlacePickerActivity();


        }
    }

    private void startPlacePickerActivity() {
        PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
        Intent intent;
        try {
            intent = intentBuilder.build(MainActivity.this);
            startActivityForResult(intent, PLACE_PICKER_REQUEST);
            System.out.println("start activity for result");
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("onActivityResult");
        if (requestCode == PLACE_PICKER_REQUEST && resultCode == RESULT_OK) {
            displaySelectedPlaceFromPlacePicker(data);
        }
    }

    private void displaySelectedPlaceFromPlacePicker(Intent data) {
        Place placeSelected = PlacePicker.getPlace(this,data);

        Double latitude = placeSelected.getLatLng().latitude;
        Double longitude = placeSelected.getLatLng().longitude;
        String direccion = placeSelected.getAddress().toString();
        String address = String.valueOf(latitude)+ " | " + String.valueOf(longitude) +"\n" + direccion;

        texto.setText(address);
    }
}
