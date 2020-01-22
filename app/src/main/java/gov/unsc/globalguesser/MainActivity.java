package gov.unsc.globalguesser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static ArrayList<Place> places = new ArrayList<>();
    private static Place currentPlace;
    private ImageView imageView;
    public static int score = 0;
    private TextView scoreLabel;

    public static Place getCurrentPlace() {
        return currentPlace;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        addPlaces();
        pickPlace();
        imageView.setImageResource(currentPlace.getId());
        scoreLabel = findViewById(R.id.scoreLabel);
        scoreLabel.setText("Score: " + score);
    }

    private static void addPlaces() {
        places.add(new Place(37, 51, 7, false, 119, 34, 28, true, R.drawable.p1));
        places.add(new Place(44, 23, 8, false, 71, 9, 43, true, R.drawable.p2));
        places.add(new Place(38, 34, 0, false, 7, 54, 50, true, R.drawable.p3));
        places.add(new Place(30, 0, 16, false, 31, 13, 36, false, R.drawable.p4));
        places.add(new Place(35, 44, 42, false, 51, 21, 26, false, R.drawable.p5));
        places.add(new Place(52, 27, 30, false, 12, 22, 9, false, R.drawable.p6));
        places.add(new Place(14, 36, 4, false, 90, 31, 7, true, R.drawable.p7));
        places.add(new Place(41, 56, 37, true, 75, 35, 38, true, R.drawable.p8));
        places.add(new Place(53, 21, 9, false, 7, 10, 36, true, R.drawable.p9));
        places.add(new Place(40, 42, 36, false, 14, 32, 39, false, R.drawable.p10));
    }

    private static void pickPlace() {
        if (places.size() <= 0)
            return;
        int index = (int) (Math.random() * places.size());
        currentPlace = places.get(index);
        places.remove(index);
    }

    public void goToMakeGuess(View view) {
        startActivity(new Intent(this, MapsActivity.class));
    }
}
