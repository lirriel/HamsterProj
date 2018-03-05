package packs.ClassLib;

/**
 * Created by ASUS on 25.12.2016.
 */
public class Place {
    public int id;
    public String title;
    public String address;
    public String location;
    public String imageURL;
    public double lon;
    public double lat;
    public String description;
    public String site_url;
    public boolean is_closed;

    public Place(int id, String title, String description, String address, String location, String imageURL, String site_url) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.address = address;
        this.location = location;
        this.imageURL = imageURL;
        this.site_url = site_url;

    }
}
