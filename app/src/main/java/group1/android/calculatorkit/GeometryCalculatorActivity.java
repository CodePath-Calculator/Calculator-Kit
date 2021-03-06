package group1.android.calculatorkit;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class GeometryCalculatorActivity extends AppCompatActivity {

    Button btnAreaSurfaceAreaAndVolume;
    Button btnIntersections;
    Button btnDistance;
    Button btnAngles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geometry_calculator);

        btnAreaSurfaceAreaAndVolume = findViewById(R.id.btnAreaSurfaceAreaAndVolume);
        btnIntersections = findViewById(R.id.btnIntersections);
        btnDistance = findViewById(R.id.btnDistance);
        btnAngles = findViewById(R.id.btnAngles);

        btnAreaSurfaceAreaAndVolume.setOnClickListener(v -> {
            Intent i = new Intent(GeometryCalculatorActivity.this, AreaSurfaceAreaAndVolumeActivity.class);
            startActivity(i);
        });

        btnIntersections.setOnClickListener(v -> {
            Intent i = new Intent(GeometryCalculatorActivity.this, IntersectionActivity.class);
            startActivity(i);
        });

        btnDistance.setOnClickListener(v -> {
            Intent i = new Intent(GeometryCalculatorActivity.this, DistanceActivity.class);
            startActivity(i);
        });

        btnAngles.setOnClickListener(v -> {
            Intent i = new Intent(GeometryCalculatorActivity.this, AnglesActivity.class);
            startActivity(i);
        });
    }
}