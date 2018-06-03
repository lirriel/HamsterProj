package app.hse.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import packs.ClassLib.Event;
import packs.ClassLib.Place;

import static app.hse.myapplication.UI.Main7Activity.placesItems;
import static app.hse.myapplication.UI.Main7Activity.eventsItems;

/**
 * Created by MiBook on 22.01.2017.
 */

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    Drawable bmImage;
    int i0;
    ArrayList<Place> listP = null;
    ArrayList<Event> listE = null;

    public DownloadImageTask(Drawable bmImage, ArrayList<Place> list, int i) {
        this.bmImage = bmImage;
        i0 = i;
        listP = list;
    }

    public DownloadImageTask(Drawable bmImage, int i, ArrayList<Event> list) {
        this.bmImage = bmImage;
        i0 = i;
        listE = list;
    }

    public Drawable getBmImage(){
        return bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        URL url = null;
        try {
            try {
                url = new URL(urldisplay);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                mIcon11 = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    public static boolean f = false;

    protected void onPostExecute(Bitmap result) {
//        bmImage.setImageBitmap(result);
        if (listP!=null)
        {
//            placesItems.add(new ItemObject(listP.get(i0).title,bmImage));
            System.err.println("imageView added");
            if (i0 == listP.size() - 1){
                f = true;
            }
        }
        else
        {
            System.err.println("imageView added");
//            eventsItems.add(new ItemObject(listE.get(i0).title,bmImage));
        }
    }
}