package packs.Logic;

import android.os.AsyncTask;
import app.hse.myapplication.UI.Main7Activity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import packs.ClassLib.Event;
import packs.ClassLib.Place;
import packs.Logic.TagLib.TagMap;

/**
 * Created by ASUS on 25.12.2016.
 */
/*public class GetWebRequest {

    static HttpURLConnection connection = null;
    static String JSONText;

    static public String combineURLString(String activity, ArrayList<String> tagsForMood){
        String urlString = "https://kudago.com/public-api/v1.2/"+activity+"/?fields=id,images,description,site_url,categories,title,address,location&location=msk&page_size=20&text_format=text&categories=";
        TagMap tagMap = new TagMap();
        ArrayList<String> tagsToSearch = activity=="places"?tagMap.getPlaceTagsToSearch(tagsForMood):tagMap.getEventTagsToSearch(tagsForMood);
        for(int i = 0; i < tagsToSearch.size(); i++)
            urlString+=tagsToSearch.get(i) + (tagsToSearch.size() - 1 == i?"":",");

        return urlString;
    }

    static private String combinePlaceURLString(int id){
        return "https://kudago.com/public-api/v1.2/places/" + String.valueOf(id);
    }

    static private String combineEventURLString(int id){
        return "https://kudago.com/public-api/v1.2/events/" + String.valueOf(id);
    }

    static private URL getURL(String urlAddress) {
        URL url = null;
        try {
            url = new URL(urlAddress);

            return url;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    static private String getJSONText(String urlAddress) {

        final URL url = getURL(urlAddress);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    StringBuilder builder = null;
                    connection = (HttpURLConnection) url.openConnection();
                    int response = connection.getResponseCode();

                    if (response == HttpURLConnection.HTTP_OK) {
                        builder = new StringBuilder();

                        try (BufferedReader reader = new BufferedReader(
                                new InputStreamReader(connection.getInputStream()))) {

                            String line;

                            while ((line = reader.readLine()) != null) {
                                builder.append(line);
                            }

                            JSONText = builder.toString();
                        } catch (IOException e) {
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    connection.disconnect();
                }
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return JSONText;
    }

    static protected JSONObject getJSONObject(String urlAddress) {
        try {
            return new JSONObject(getJSONText(urlAddress));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    static protected JSONArray getJSONArray(String urlAddress) {
        try {
            return new JSONArray(getJSONText(urlAddress));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    static public ArrayList<Place> getPlaces(String urlAddress) {
        JSONObject jsonResult = getJSONObject(urlAddress);
        try {
            JSONArray places = jsonResult.getJSONArray("results");
            ArrayList<Place> placesToGo = new ArrayList<>();

            for (int i = 0; i < places.length(); i++) {
                JSONObject place = places.getJSONObject(i);

                Place foundPlace = new Place(
                        place.getInt("id"),
                        place.getString("title"),
                        place.getString("description"),
                        place.getString("address"),
                        place.getString("location"),
                        place.getJSONArray("images").getJSONObject(0).getString("image"),
                        place.getString("site_url")
                );

                JSONObject placeDetails = getJSONObject(combinePlaceURLString(foundPlace.id));

                foundPlace.is_closed = placeDetails.getBoolean("is_closed");

                if(!foundPlace.is_closed) {
                    foundPlace.lon = placeDetails.getJSONObject("coords").getDouble("lon");
                    foundPlace.lat = placeDetails.getJSONObject("coords").getDouble("lat");

                    placesToGo.add(foundPlace);
                }
            }

            return placesToGo;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    static public ArrayList<Event> getEvents(String urlAddress) {
        JSONObject jsonResult = getJSONObject(urlAddress);
        try {
            JSONArray events = jsonResult.getJSONArray("results");
            ArrayList<Event> eventsToGo = new ArrayList<>();

            for (int i = 0; i < events.length(); i++) {
                JSONObject event = events.getJSONObject(i);

                Event foundEvent = new Event(
                        event.getInt("id"),
                        event.getString("title"),
                        event.getString("description"),
                        event.getJSONObject("location").getString("slug"),
                        event.getJSONArray("images").getJSONObject(0).getString("image"),
                        event.getString("site_url")
                );

                //JSONObject eventDetails = getJSONObject(combineEventURLString(foundEvent.id));
                //foundEvent.place_id = eventDetails.getJSONObject("place").getInt("id");
                //JSONObject placeDetails = getJSONObject(combinePlaceURLString(foundEvent.place_id));
                //foundEvent.lon = placeDetails.getJSONObject("coords").getDouble("lon");
                //foundEvent.lat = placeDetails.getJSONObject("coords").getDouble("lat");

                eventsToGo.add(foundEvent);
            }

            return eventsToGo;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
*/



/**
 * Created by ASUS on 25.12.2016.
 */
public class GetWebRequest {

    static HttpURLConnection connection = null;
    static String JSONText;

    static public String combineURLString(String activity, ArrayList<String> tagsForMood){
        String urlString = "https://kudago.com/public-api/v1.2/"+activity+"/?fields=id,images,description,site_url,categories,title,address,location&location=msk&page_size=20&text_format=text&categories=";
        TagMap tagMap = new TagMap();
        ArrayList<String> tagsToSearch = activity=="places"?tagMap.getPlaceTagsToSearch(tagsForMood):tagMap.getEventTagsToSearch(tagsForMood);
        for(int i = 0; i < tagsToSearch.size(); i++)
            urlString+=tagsToSearch.get(i) + (tagsToSearch.size() - 1 == i?"":",");

        return urlString;
    }

    static private String combinePlaceURLString(int id){
        return "https://kudago.com/public-api/v1.2/places/" + String.valueOf(id);
    }

    static private String combineEventURLString(int id){
        return "https://kudago.com/public-api/v1.2/events/" + String.valueOf(id);
    }

    static private URL getURL(String urlAddress) {
        URL url = null;
        try {
            url = new URL(urlAddress);

            return url;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    class AsyncPlaces extends AsyncTask<String, Void, ArrayList<Place>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        private String getJSONText(String urlAddress) {

            final URL url = getURL(urlAddress);

            Thread t = new Thread(() -> {
                try {
                    StringBuilder builder = null;
                    connection = (HttpURLConnection) url.openConnection();
                    int response = connection.getResponseCode();

                    if (response == HttpURLConnection.HTTP_OK) {
                        builder = new StringBuilder();

                        try (BufferedReader reader = new BufferedReader(
                                new InputStreamReader(connection.getInputStream()))) {

                            String line;

                            while ((line = reader.readLine()) != null) {
                                builder.append(line);
                            }

                            JSONText = builder.toString();
                        } catch (IOException e) {
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    connection.disconnect();
                }
            });
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return JSONText;
        }
        protected JSONObject getJSONObject(String urlAddress) {
            try {
                return new JSONObject(getJSONText(urlAddress));
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        protected JSONArray getJSONArray(String urlAddress) {
            try {
                return new JSONArray(getJSONText(urlAddress));
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        public ArrayList<Place> getPlaces(String urlAddress) {
            JSONObject jsonResult = getJSONObject(urlAddress);
            try {
                JSONArray places = jsonResult.getJSONArray("results");
                ArrayList<Place> placesToGo = new ArrayList<>();

                for (int i = 0; i < places.length(); i++) {
                    JSONObject place = places.getJSONObject(i);

                    Place foundPlace = new Place(
                            place.getInt("id"),
                            place.getString("title"),
                            place.getString("description"),
                            place.getString("address"),
                            place.getString("location"),
                            place.getJSONArray("images").getJSONObject(0).getString("image"),
                            place.getString("site_url")
                    );

                    JSONObject placeDetails = getJSONObject(combinePlaceURLString(foundPlace.id));

                    foundPlace.is_closed = placeDetails.getBoolean("is_closed");

                    if(!foundPlace.is_closed) {
                        foundPlace.lon = placeDetails.getJSONObject("coords").getDouble("lon");
                        foundPlace.lat = placeDetails.getJSONObject("coords").getDouble("lat");

                        placesToGo.add(foundPlace);
                    }
                }

                return placesToGo;

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected ArrayList<Place> doInBackground(String... params) {
            return getPlaces(params[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<Place> result) {
            Main7Activity.setPlaces(result);
            Main7Activity.updatePlaces(result);
            super.onPostExecute(result);
        }
    }

    class MyTaskEvents extends AsyncTask<String, Void, ArrayList<Event>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        private String getJSONText(String urlAddress) {

            final URL url = getURL(urlAddress);

            Thread t = new Thread(() -> {
                try {
                    StringBuilder builder = null;
                    connection = (HttpURLConnection) url.openConnection();
                    int response = connection.getResponseCode();

                    if (response == HttpURLConnection.HTTP_OK) {
                        builder = new StringBuilder();

                        try (BufferedReader reader = new BufferedReader(
                                new InputStreamReader(connection.getInputStream()))) {

                            String line;

                            while ((line = reader.readLine()) != null) {
                                builder.append(line);
                            }

                            JSONText = builder.toString();
                        } catch (IOException e) {
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    connection.disconnect();
                }
            });
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return JSONText;
        }

        protected JSONObject getJSONObject(String urlAddress) {
            try {
                return new JSONObject(getJSONText(urlAddress));
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        protected JSONArray getJSONArray(String urlAddress) {
            try {
                return new JSONArray(getJSONText(urlAddress));
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        public ArrayList<Event> getEvents(String urlAddress) {
            JSONObject jsonResult = getJSONObject(urlAddress);
            try {
                JSONArray events = jsonResult.getJSONArray("results");
                ArrayList<Event> eventsToGo = new ArrayList<>();

                for (int i = 0; i < events.length(); i++) {
                    JSONObject event = events.getJSONObject(i);

                    Event foundEvent = new Event(
                            event.getInt("id"),
                            event.getString("title"),
                            event.getString("description"),
                            event.getJSONObject("location").getString("slug"),
                            event.getJSONArray("images").getJSONObject(0).getString("image"),
                            event.getString("site_url")
                    );

                    JSONObject eventDetails = getJSONObject(combineEventURLString(foundEvent.id));
//
//                    foundEvent.place_id = eventDetails.getJSONObject("event").getInt("id");
//                    JSONObject placeDetails = getJSONObject(combinePlaceURLString(foundEvent.place_id));
//                    foundEvent.lon = placeDetails.getJSONObject("coords").getDouble("lon");
//                    foundEvent.lat = placeDetails.getJSONObject("coords").getDouble("lat");

                    eventsToGo.add(foundEvent);
                }

                return eventsToGo;

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected ArrayList<Event> doInBackground(String... params) {
            return getEvents(params[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<Event> result) {
            Main7Activity.setEvents(result);
            Main7Activity.updateEvents(result);
            super.onPostExecute(result);
        }
    }
}
