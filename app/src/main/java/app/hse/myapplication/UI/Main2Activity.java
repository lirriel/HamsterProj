package app.hse.myapplication.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import app.hse.myapplication.R;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void OnClick(View view){
        this.startActivity(new Intent(Main2Activity.this, Main3Activity.class));
        onPause();
    }

    public void OnClick1(View view){
        this.startActivity(new Intent(Main2Activity.this, Main4Activity.class));
        onPause();
    }
}
