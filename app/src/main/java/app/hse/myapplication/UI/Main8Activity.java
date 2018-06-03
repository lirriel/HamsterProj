package app.hse.myapplication.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import app.hse.myapplication.R;
import app.hse.myapplication.UI.Main7Activity;

public class Main8Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        TextView textView = findViewById(R.id.title);
        textView.setText(Main7Activity.itemObject.getName());
        ImageView imageView = findViewById(R.id.imageView2);
        imageView.setImageDrawable(Main7Activity.itemObject.getPhoto());
        TextView textView1 = findViewById(R.id.desr);
        textView1.setText(Main7Activity.description);
    }
}
