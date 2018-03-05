package app.hse.myapplication;

import android.widget.ImageView;

public class ItemObject {

    private String name;
    private ImageView photo;

    public ItemObject(String name, ImageView imageView) {
        this.name = name;
        this.photo = imageView;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }
}