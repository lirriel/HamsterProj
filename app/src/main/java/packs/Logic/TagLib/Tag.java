package packs.Logic.TagLib;

/**
 * Created by ASUS on 25.12.2016.
 */
public class Tag {
    String mood;
    public String getMood(){return mood;}

    String[] places;
    public String[] getPlaces(){return places;}

    String[] events;
    public String[] getEvents(){return events;}

    public Tag(String mood, String[] places, String[] events){
        this.mood = mood;
        this.places = places;
        this.events = events;
    }
}
