package app.hse.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<ItemObject> countries;
    private int rowLayout;
    private Context mContext;

    public MyAdapter(List<ItemObject> countries, int rowLayout, Context context) {
        this.countries = countries;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }

    @Override
    public int getItemCount() {
        return countries == null ? 0 : countries.size();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ItemObject myItem = countries.get(i);
        viewHolder.Name.setText(myItem.getName());
        viewHolder.Image.setImageDrawable(myItem.getPhoto().getDrawable());// = myItem.getPhoto();
//        viewHolder.Image.setImageDrawable(mContext.getDrawable(myItem.getPhoto()));
//        viewHolder.email.setText(myItem.getEmail());
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView Name, email;
        public ImageView Image;

        public ViewHolder(View itemView) {
            super(itemView);
            Name = (TextView) itemView.findViewById(R.id.country_name);
            Image = (ImageView) itemView.findViewById(R.id.country_photo);
//            email = (TextView) itemView.findViewById(R.id.text_email);
        }
    }
}