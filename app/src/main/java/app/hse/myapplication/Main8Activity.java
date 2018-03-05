package app.hse.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class Main8Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        TextView textView = (TextView)findViewById(R.id.title);
        textView.setText(Main7Activity.itemObject.getName());
        ImageView imageView = (ImageView)findViewById(R.id.imageView2);
        imageView.setImageDrawable(Main7Activity.itemObject.getPhoto().getDrawable());
        TextView textView1 = (TextView)findViewById(R.id.desr);
        textView1.setText(Main7Activity.description);
    }
}
