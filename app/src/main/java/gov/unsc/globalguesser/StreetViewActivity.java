package gov.unsc.globalguesser;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class StreetViewActivity extends AppCompatActivity implements OnStreetViewPanoramaReadyCallback {

    private StreetViewPanorama streetViewPanorama;
    private StreetViewPanoramaFragment streetViewPanoramaFragment;
    private static ArrayList<Place> places = new ArrayList<>();
    private static Place currentPlace;

    public static Place getCurrentPlace() {
        return currentPlace;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_street_view);
        addPlaces();
        pickPlace();
        streetViewPanoramaFragment = (StreetViewPanoramaFragment) getFragmentManager()
                .findFragmentById(R.id.streetViewMap);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);
    }

    private static void pickPlace() {
        int index = (int) (Math.random() * places.size());
        currentPlace = places.get(index);
        places.remove(index);
    }

    private static void addPlaces() {
        places.add(new Place(37, 51, 7, false, 119, 34, 28, true));
        places.add(new Place(44, 23, 8, false, 71, 9, 43, true));
        places.add(new Place(38, 34, 0, false, 7, 54, 50, true));
        places.add(new Place(30, 0, 16, false, 31, 13, 36, false));
        places.add(new Place(35, 44, 42, false, 51, 21, 26, false));
//        places.add(new Place());
//        places.add(new Place());
//        places.add(new Place());
//        places.add(new Place());
//        places.add(new Place());
    }

    @Override
    protected void onResume() {
        super.onResume();
        streetViewPanoramaFragment.onResume();
    }


    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {
        this.streetViewPanorama = streetViewPanorama;
        this.streetViewPanorama.setPosition(new LatLng(-33.873398, 150.976744));
    }

    @Override
    protected void onStop() {
        super.onStop();
        streetViewPanoramaFragment.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (streetViewPanoramaFragment != null)
            streetViewPanoramaFragment.onDestroy();
        streetViewPanorama = null;
    }

    public void goToMakeGuess(View view) {
        startActivity(new Intent(this, MapsActivity.class));
    }
}