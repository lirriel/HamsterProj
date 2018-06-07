package app.hse.myapplication.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import app.hse.myapplication.GPSTracker;
import app.hse.myapplication.R;

public class MainActivity extends AppCompatActivity {
    public static GPSTracker gps;
    public static int from = 0;
    private Button button_go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gps = new GPSTracker(MainActivity.this);
        // check if GPS enabled
        if(gps.canGetLocation()){

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            // \n is for new line
//            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }
        else
        {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_go = findViewById(R.id.angry_btn);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.shake);
        animation.reset();
        button_go.clearAnimation();
        button_go.startAnimation(animation);
    }

    public void OnButton_1Click(View view){
        this.startActivity(new Intent(MainActivity.this, Main2Activity.class));
    }
}
