package app.hse.myapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<ItemObject> itemObjects;
    private int rowLayout;
    private Context mContext;

    public MyAdapter(List<ItemObject> itemObjects, int rowLayout, Context context) {
        this.itemObjects = itemObjects;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }

    public void setData(List<ItemObject> newItems) {
        itemObjects = newItems;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return itemObjects == null ? 0 : itemObjects.size();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.bind(itemObjects.get(i));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.country_name);
            imageView = itemView.findViewById(R.id.country_photo);
        }

        void bind(ItemObject itemObject) {
            name.setText(itemObject.getName());

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .fallback(android.R.color.holo_orange_light)
                    .error(R.mipmap.ic_launcher);

            Glide.with(itemView)
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
}