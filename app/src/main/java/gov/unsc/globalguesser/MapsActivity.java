package gov.unsc.globalguesser;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng coord;
    private TextView scoreLabel;

    /*
        This game is played by looking at the picture provided, and when ready to guess, click the "Go to Map" button.
        This brings the player to a map where they can pick the place they believe the picture to be from.  When they press the "Make Guess" button,
        the game will add to the players score the distance between where they guessed and where the actual location was.  The goal is to play
        through all 10 locations with the minimum score possible.

        Bugs:
        -Noticed unwanted errors when leaving and reentering game in the middle of play
        -The map pictures give away the location
        -Must restart app when you go through all 10 places
        -Location reveal not working properly


     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        clicked = false;
        scoreLabel = findViewById(R.id.scoreLabel2);
        scoreLabel.setText("Score: " + MainActivity.score);
    }

    public void makeGuess(View v) {
        if (clicked)
            return;
        clicked = true;
        if (coord == null || MainActivity.getCurrentPlace() == null)
            return;
        double dist = Place.dist(new Place(coord), MainActivity.getCurrentPlace());
        MainActivity.score += dist / 1000;

        // debug purposes
        if (dist <= 100000) {
            System.out.println("Dubs");
        } else {
            System.out.println(dist);
        }
        scoreLabel.setText("Score: " + MainActivity.score);
    }

    private boolean clicked = false;
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setOnMapClickListener(latLng -> {
            mMap.clear();
            if (!clicked) {
                mMap.addMarker(new MarkerOptions().position(latLng));
            }
            coord = latLng;
        });
    }

    public void nextPlace(View v) {
        if (clicked)
            startActivity(new Intent(this, MainActivity.class));
    }
}
