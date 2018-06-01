package app.hse.myapplication.UI;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import app.hse.myapplication.R;
import app.hse.myapplication.api.Weather;

public class Main5Activity extends AppCompatActivity {
    String[] emotionalBackground = {"Happiness","Placidy",
                                "Melancholy","Wistfullness",
                                "Anxiety","Anger"};
    String[] emotionalState = {"Excited", "Depressed", "Balanced"};
    int mood = -1;
    int state = -1;
    boolean f;
    String temperature, date, condition, humidity, wind, link;
    Bitmap icon = null;
    TextView title, tempText, dateText, conditionText, windText, humidityText,day1,day2, day3, day4;
    ImageView image;
    ProgressDialog dialog;
    Weather weather1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
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

    Typeface weatherFont;
}
