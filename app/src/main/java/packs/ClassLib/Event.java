package packs.ClassLib;

/**
 * Created by ASUS on 26.12.2016.
 */
public class Event {
    public int id;
    public String title;
    public int place_id;
    public String location;
    public String imageURL;
    public double lon;
    public double lat;
    public String description;
    public String site_url;
    public boolean is_continuous;

    public Event(int id, String title, String description, String location, String imageURL, String site_url) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.imageURL = imageURL;
        this.site_url = site_url;
    }
}
