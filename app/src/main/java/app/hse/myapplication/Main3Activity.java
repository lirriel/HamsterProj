package app.hse.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {
    public static boolean[] f = new boolean[32];
    public static String[] tags = new String[32];
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        for (int i = 0; i < 31; i++) {
            f[i] = false;
        }
        tags[0]="rain";
        tags[1]="snow";
        tags[1]="brokenHeart";
        tags[3]="family";tags[17]="romantic";
        tags[4]="kid";tags[6]="rest";tags[9]="drink";tags[18]="home";
        tags[8]="creditCard";tags[9]="roundClock";tags[11]="heat";tags[12]="expensive";
        tags[13]="tired";tags[14]="eat";tags[15]="bound";tags[16]="friends";tags[19]="night";
        tags[20]="nature";tags[21]="movie";tags[22]="sad";tags[23]="meditation";
        tags[24]="pi";tags[25]="music";tags[26]="balance";tags[27]="single";
        tags[28]="cheer";tags[29]="student";tags[30]="work";tags[31]="season";
        int color = Color.parseColor("#ffffff");
        int[] arr = {R.id.imageButton,
                R.id.imageButton2,
                R.id.imageButton3,
                R.id.imageButton4,
                R.id.imageButton5,
                R.id.imageButton7,
                R.id.imageButton9,
                R.id.imageButton10,
                R.id.imageButton11,
                R.id.imageButton12,
                R.id.imageButton13,R.id.imageButton14,
                R.id.imageButton15,
                R.id.imageButton16,
                R.id.imageButton17,
                R.id.imageButton18,
                R.id.imageButton19,
                R.id.imageButton20,
                R.id.imageButton21,
                R.id.imageButton22,
                R.id.imageButton23,
                R.id.imageButton24,
                R.id.imageButton25,
                R.id.imageButton26,
                R.id.imageButton27,
                R.id.imageButton28,
                R.id.imageButton29,
                R.id.imageButton30,
                R.id.imageButton31,
                R.id.imageButton32};
        for (int i = 0; i < arr.length; i++) {
            ImageButton button = (ImageButton)this.findViewById(arr[i]);
            ((GradientDrawable)button.getBackground()).setColor(color);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main3, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public static ArrayList<String> getTags(){
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < Main3Activity.f.length; i++) {
            if (Main3Activity.f[i]){
                list.add(tags[i]);
            }
        }
        return list;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.startActivity(new Intent(Main3Activity.this, app.hse.myapplication.Main6Activity.class));
        onPause();
        return super.onOptionsItemSelected(item);
    }

    public void OnClick5(View view){
        MainActivity.from = 1;
        this.startActivity(new Intent(Main3Activity.this, app.hse.myapplication.Main7Activity.class));
        onPause();
    }

    public void OnClick1(View view){
        ImageButton _imageButton = (ImageButton) view;
        String s = "#ffffff";

        switch (view.getId()){
            case R.id.imageButton: {if (!f[0]){ s = "#50DAC8"; count++;}else{count--;} f[0]=!f[0];break;} //rain
            case R.id.imageButton2: {if (!f[1]) { s = "#A1B6DA";count++;}else{count--;} f[1]=!f[1]; break;} //snow
            case R.id.imageButton3: {if (!f[2]) { s = "#DE3163";count++;}else{count--;} f[2]=!f[2]; break;}//broken heart
            case R.id.imageButton4: {if (!f[3]) { s = "#C6FF94";count++;}else{count--;} f[3]=!f[3]; break;}//family
            case R.id.imageButton5: {if (!f[4]) { s = "#FFC694";count++;}else{count--;} f[4]=!f[4]; break;}//baby
            case R.id.imageButton7: {if (!f[6]) { s = "#D2691E";count++;}else{count--;} f[6]=!f[6]; break;}//coffee
            case R.id.imageButton9: {if (!f[8]) { s = "#359256";count++;}else{count--;} f[8]=!f[8]; break;}//credit card
            case R.id.imageButton10: {if (!f[9]) {s = "#7FFFD4";count++;}else{count--;} f[9]=!f[9]; break;}//24hours
            case R.id.imageButton11: {if (!f[10]) {s = "#644F7D";count++;}else{count--;} f[10]=!f[10]; break;}//depressed
            case R.id.imageButton12: {if (!f[11]) {s = "#F3F153";count++;}else{count--;} f[11]=!f[11]; break;}//desert
            case R.id.imageButton13: {if (!f[12]) {s = "#579CEA";count++;}else{count--;} f[12]=!f[12]; break;}//diamond
            case R.id.imageButton14: {if (!f[13]) {s = "#BADBAD";count++;}else{count--;} f[13]=!f[13]; break;}//tired man
            case R.id.imageButton15: {if (!f[14]) {s = "#FFB63D";count++;}else{count--;} f[14]=!f[14]; break;}//food
            case R.id.imageButton16: {if (!f[15]) {s = "#DA221B";count++;}else{count--;} f[15]=!f[15]; break;}//go
            case R.id.imageButton17: {if (!f[16]) {s = "#7FC7FF";count++;}else{count--;} f[16]=!f[16]; break;}//group
            case R.id.imageButton18: {if (!f[17]) {s = "#E70D10";count++;}else{count--;} f[17]=!f[17]; break;}//happy
            case R.id.imageButton19: {if (!f[18]) {s = "#98DD98";count++;}else{count--;} f[18]=!f[18]; break;}//home
            case R.id.imageButton20: {if (!f[19]) {s = "#003366";count++;}else{count--;} f[19]=!f[19]; break;}//moon_stars
            case R.id.imageButton21: {if (!f[20]) {s = "#03C029";count++;}else{count--;} f[20]=!f[20]; break;}//landscape
            case R.id.imageButton22: {if (!f[21]) {s = "#FF4D00";count++;}else{count--;} f[21]=!f[21]; break;}//movies
            case R.id.imageButton23: {if (!f[22]) {s = "#9966CC";count++;}else{count--;} f[22]=!f[22]; break;}//sad_cry
            case R.id.imageButton24: {if (!f[23]) {s = "#4F7942";count++;}else{count--;} f[23]=!f[23]; break;}//cash
            case R.id.imageButton25: {if (!f[24]) {s = "#FFBF00";count++;}else{count--;} f[24]=!f[24]; break;}//pi
            case R.id.imageButton26: {if (!f[25]) {s = "#C41E3A";count++;}else{count--;} f[25]=!f[25]; break;}//music
            case R.id.imageButton27: {if (!f[26]) {s = "#BDB76B";count++;}else{count--;} f[26]=!f[26]; break;}//sad
            case R.id.imageButton28: {if (!f[27]) {s = "#1E90FF";count++;}else{count--;} f[27]=!f[27]; break;}//single
            case R.id.imageButton29: {if (!f[28]) {s = "#FD880D";count++;}else{count--;} f[28]=!f[28]; break;}//smiling
            case R.id.imageButton30: {if (!f[29]) {s = "#1560BD";count++;}else{count--;} f[29]=!f[29]; break;}//student
            case R.id.imageButton31: {if (!f[30]) {s = "#CD853F";count++;}else{count--;} f[30]=!f[30]; break;}//work
            case R.id.imageButton32: {if (!f[31]) {s = "#FF5757";count++;}else{count--;} f[31]=!f[31]; break;}//season
        }
        if (count > 5 || count < 0){
            Button b = (Button)this.findViewById(R.id.button4);
            b.setVisibility(View.INVISIBLE);
        }
        else {
            Button b = (Button) this.findViewById(R.id.button4);
            b.setVisibility(View.VISIBLE);
        }
        int color = Color.parseColor(s);
        ((GradientDrawable) _imageButton.getBackground()).setColor(color);
    }
}
