package packs.Logic;

import java.util.ArrayList;

import packs.ClassLib.Event;

/**
 * Created by ASUS on 26.12.2016.
 */

public class BasicRequest {
//    static public ArrayList<packs.ClassLib.Place> getPlacesToGUI(ArrayList<String> moods){
//        GetWebRequest.AsyncPlaces r = new GetWebRequest().new AsyncPlaces();
//        ArrayList<packs.ClassLib.Place> pl;
//        r.execute(GetWebRequest.combineURLString("places", moods));
//       // return GetWebRequest.new AsyncPlaces()(GetWebRequest.combineURLString("places", moods));
//    }
//    static public ArrayList<Event> getEventsToGUI(ArrayList<String> moods){
//        return GetWebRequest.getEvents(GetWebRequest.combineURLString("events", moods));
//    }

    static public void getPlacesToGUI(ArrayList<String> moods){
        GetWebRequest.AsyncPlaces r = new GetWebRequest().new AsyncPlaces();
        r.execute(GetWebRequest.combineURLString("places", moods));
        // return GetWebRequest.new AsyncPlaces()(GetWebRequest.combineURLString("places", moods));
    }

    static public void getEventsToGUI(ArrayList<String> moods){
        GetWebRequest.MyTaskEvents r = new GetWebRequest().new MyTaskEvents();
        r.execute(GetWebRequest.combineURLString("events", moods));
    }
}
