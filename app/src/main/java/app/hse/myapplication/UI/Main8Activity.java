package app.hse.myapplication.UI;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import app.hse.myapplication.ItemObject;
import app.hse.myapplication.R;

public class Main8Activity extends AppCompatActivity {
    private ImageView imageView;
    private TextView description;
    private TextView title;
    private ItemObject itemObject;

    private static final String COURSE_TAG = "item";

    public static void start(Activity activity, ItemObject item) {
        Intent intent = new Intent(activity, Main8Activity.class);
        intent.putExtra(COURSE_TAG, item);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        if (itemObject == null && getIntent() != null)
            itemObject = getIntent().getParcelableExtra(COURSE_TAG);

        if (itemObject != null) {
            init();
            setItem(itemObject);
        }
    }

    private void init() {
        imageView = findViewById(R.id.imageView2);
        description = findViewById(R.id.desr);
        title = findViewById(R.id.title);
    }

    private void setItem(ItemObject itemObject) {
        description.setText(itemObject.getDescription());
        title.setText(itemObject.getName());

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .fallback(android.R.color.holo_orange_light)
                .error(R.mipmap.ic_launcher);

        Glide.with(imageView)
                .load(itemObject.getImageUrl())
                .apply(options)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        itemObject.setPhoto(resource);
                        return false;
                    }
                })
                .into(imageView);
    }
}
