package app.hse.myapplication.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import app.hse.myapplication.R;
import app.hse.myapplication.api.Weather;

import static app.hse.myapplication.UI.Main3Activity.tags;

public class Main4Activity extends AppCompatActivity {
    public String[] emotionalBackground = {"Happiness","Placidy",
            "Melancholy","Wistfullness",
            "Anxiety","Anger"};
    public String[] emotionalState = {"Excited", "Depressed", "Balanced"};
    int mood = -1;
    int state = -1;
    boolean f;
    String temperature, date, condition, humidity, wind, link;
    Bitmap icon = null;
    TextView title, tempText, dateText, conditionText, windText, humidityText,day1,day2, day3, day4;
    ImageView image;
    ProgressDialog dialog;
    public Weather weather1;

    public static ArrayList<String> getTags(){
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < Main3Activity.f.length; i++) {
            if (Main3Activity.f[i]){
                list.add(tags[i]);
            }
        }
        return list;
    }

    int count;
    public static int[] rgb = {0,0,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        for (int i = 0; i < 31; i++) {
            Main3Activity.f[i] = false;
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

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        count = -1;
    }

    void open(){
        if (count == 2){
            mood = calculateMood();
            state = calculateState();
            final URL url = createURL("Moscow");
            f = false;
            final String[] s = new String[1];
            if (url != null) {
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        try {
                            doALL(url);
                            s[0] = weather1.description + " "+weather1.maxTemp+" "+weather1.minTemp+" "+weather1.dayOfWeek+" "+weather1.weatherMain;
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }).start();
            }
            while (!f){}
            switch (mood){
                case 0:{Main3Activity.f[28] = true; break;}
                case 1:{Main3Activity.f[23] = true; break;}
                case 2:{Main3Activity.f[17] = true; break;}
                case 3:{Main3Activity.f[22] = true; break;}
                case 4:{Main3Activity.f[13] = true; break;}
                case 5:{Main3Activity.f[24] = true; break;}
            }
            switch (state){
                case 0:{Main3Activity.f[15] = true; break;}
                case 1:{Main3Activity.f[13] = true; break;}
                case 2:{Main3Activity.f[26] = true; break;}
            }
            if (weather1.weatherMain.toLowerCase().contains("rain")){
                Main3Activity.f[0] = true;
            }
            else if (weather1.weatherMain.toLowerCase().contains("clear")){
                Main3Activity.f[20] = true;
            }
            else if (weather1.weatherMain.toLowerCase().contains("storm")){
                Main3Activity.f[18] = true;
            }
            else if (weather1.weatherMain.toLowerCase().contains("snow")){
                Main3Activity.f[1] = true;
            }
            if (Integer.parseInt(weather1.maxTemp.substring(0,weather1.maxTemp.length()-2)) > 20){
                Main3Activity.f[11] = true;
            }
            else if (Integer.parseInt(weather1.minTemp.substring(0,weather1.maxTemp.length()-2)) < -25){
                Main3Activity.f[18] = true;
            }
            if (Integer.parseInt(weather1.maxTemp.substring(0,weather1.maxTemp.length()-2)) < 17 && Integer.parseInt(weather1.maxTemp.substring(0,weather1.maxTemp.length()-2)) > 10){
                Main3Activity.f[20] = true;
            }
            MainActivity.from = 2;
            this.startActivity(new Intent(Main4Activity.this, Main7Activity.class));
            onPause();
        }
    }

    public void OnClick_Red(View view){
        Button button = (Button) view;
        ViewGroup layout = (ViewGroup) button.getParent();
        if(null!=layout) //for safety only  as you are doing onClick
            layout.removeView(button);
        rgb[0] = ++count;
        open();
    }

    public void OnClick_Blue(View view){
        Button button = (Button) view;
        ViewGroup layout = (ViewGroup) button.getParent();
        if(null!=layout) //for safety only  as you are doing onClick
            layout.removeView(button);
        rgb[2] = ++count;
        open();
    }

    public void OnClick_Green(View view){
        Button button = (Button) view;
        ViewGroup layout = (ViewGroup) button.getParent();
        if(null!=layout) //for safety only  as you are doing onClick
            layout.removeView(button);
        rgb[1] = ++count;
        open();
    }

    int calculateMood(){
        int background = -1;
        if (Main4Activity.rgb[2] == 0 && Main4Activity.rgb[1] == 1){
            background = 0;
        }
        else if (Main4Activity.rgb[1] == 0 && Main4Activity.rgb[2] == 1){
            background = 1;
        }
        else if (Main4Activity.rgb[1] == 0 && Main4Activity.rgb[0] == 1){
            background = 2;
        }
        else if (Main4Activity.rgb[0] == 0 && Main4Activity.rgb[1] == 1){
            background = 3;
        }
        else if (Main4Activity.rgb[0] == 0 && Main4Activity.rgb[2] == 1){
            background = 4;
        }
        else if (Main4Activity.rgb[2] == 0 && Main4Activity.rgb[0] == 1){
            background = 5;
        }
        return background;
    }

    int calculateState(){
        switch (mood){
            case 0:return 0;
            case 5:return 0;
            case 2:return 2;
            case 3:return 2;
            case 1:return 1;
            case 4:return 1;
        }
        return 0;
    }

    private URL createURL(String city) {
        String apiKey = getString(R.string.api_key);
        String baseUrl = getString(R.string.web_service_url);

        try {
            String urlString = baseUrl + URLEncoder.encode(city, "UTF-8") +
                    "&appid=" + apiKey;
            return new URL(urlString);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null; // URL was malformed
    }

    void doALL(URL url){
        JSONObject jsonObject = null;
        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) url.openConnection();
            int response = connection.getResponseCode();

            if (response == HttpURLConnection.HTTP_OK) {
                StringBuilder builder = new StringBuilder();

                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()))) {

                    String line;

                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                }
                catch (IOException e) {
                }

                jsonObject = new JSONObject(builder.toString());
            }
            else {
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            connection.disconnect(); // close the HttpURLConnection
        }
        JSONObject day = jsonObject;
        try {
            JSONObject main = day.getJSONObject("main");
            JSONObject weather =
                    day.getJSONArray("weather").getJSONObject(0);
            // add new Weather object to weatherList
            weather1 = new Weather(
                    day.getLong("dt"), // date/time timestamp
                    main.getDouble("temp_min"), // minimum temperature
                    main.getDouble("temp_max"), // maximum temperature
                    main.getDouble("humidity"), // percent humidity
                    weather.getString("description"), // weather conditions
                    weather.getString("main")); // icon name
        } catch (JSONException e) {
            e.printStackTrace();
        }
        f = true;
    }

    // makes the REST web service call to get weather data and
    // saves the data to a local HTML file
    private class GetWeatherTask extends AsyncTask<URL, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(URL... params) {
            HttpURLConnection connection = null;

            try {
                connection = (HttpURLConnection) params[0].openConnection();
                int response = connection.getResponseCode();

                if (response == HttpURLConnection.HTTP_OK) {
                    StringBuilder builder = new StringBuilder();

                    try (BufferedReader reader = new BufferedReader(
                            new InputStreamReader(connection.getInputStream()))) {

                        String line;

                        while ((line = reader.readLine()) != null) {
                            builder.append(line);
                        }
                    }
                    catch (IOException e) {
                    }

                    return new JSONObject(builder.toString());
                }
                else {
                }
            }
            catch (Exception e) {
            }
            finally {
                connection.disconnect(); // close the HttpURLConnection
            }

            return null;
        }

        // process JSON response and update ListView
        @Override
        protected void onPostExecute(JSONObject weather) {
            convertJSONtoArrayList(weather); // repopulate weatherList
        }
    }

    // create Weather objects from JSONObject containing the forecast
    private void convertJSONtoArrayList(JSONObject forecast) {
        try {
            // get forecast's "list" JSONArray
            JSONArray list = forecast.getJSONArray("list");

            // convert each element of list to a Weather object
            for (int i = 0; i < list.length(); ++i) {
                JSONObject day = list.getJSONObject(i); // get one day's data

                // get the day's temperatures ("temp") JSONObject
                JSONObject temperatures = day.getJSONObject("temp");

                // get day's "weather" JSONObject for the description and icon
                JSONObject weather =
                        day.getJSONArray("weather").getJSONObject(0);

                // add new Weather object to weatherList
                weather1 = new Weather(
                        day.getLong("dt"), // date/time timestamp
                        temperatures.getDouble("min"), // minimum temperature
                        temperatures.getDouble("max"), // maximum temperature
                        day.getDouble("humidity"), // percent humidity
                        weather.getString("description"), // weather conditions
                        weather.getString("icon")); // icon name
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
