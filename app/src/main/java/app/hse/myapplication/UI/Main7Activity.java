package app.hse.myapplication.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import app.hse.myapplication.DownloadImageTask;
import app.hse.myapplication.ItemObject;
import app.hse.myapplication.MyAdapter;
import app.hse.myapplication.R;
import packs.ClassLib.Event;
import packs.ClassLib.Place;
import packs.Logic.BasicRequest;

public class Main7Activity extends AppCompatActivity {
    private RecyclerView.LayoutManager mLayoutManager;
    MyAdapter mAdapter;
    ArrayList<ArrayList<ItemObject>> list = new ArrayList<>();

    static ArrayList<Place> list2;
    static ArrayList<Event> list1;

    ProgressBar simpleProgressBar;

    static boolean f1 = false, f2 = false;

    ImageView imageView;

    ArrayList<ItemObject> listAll = new ArrayList<>();

    public static ArrayList<ItemObject> l1 = new ArrayList<>(), l2 = new ArrayList<>();

    public static void setPlaces(ArrayList<Place> list) {
        list2 = list;
        f2 = true;
    }

    public static void setEvents(ArrayList<Event> list) {
        list1 = list;
        f1 = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Places"));
        tabLayout.addTab(tabLayout.newTab().setText("Events"));
        tabLayout.addOnTabSelectedListener(new Tabs());

        l1 = new ArrayList<>();
        l2 = new ArrayList<>();

        ArrayList<String> moods = new ArrayList<>();

        if (MainActivity.from == 1)
            moods = Main3Activity.getTags();
        else if (MainActivity.from == 2)
            moods = Main4Activity.getTags();

        simpleProgressBar = (ProgressBar) findViewById(R.id.progressBar_cyclic);

//        list2 = BasicRequest.getPlacesToGUI(moods);
//        list1 = BasicRequest.getEventsToGUI(moods);
        BasicRequest.getPlacesToGUI(moods);
        BasicRequest.getEventsToGUI(moods);

        imageView = new ImageView(this);

        /*for (int i = 0; i < list2.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.load);
            new DownloadImageTask(imageView, list2, i).execute(list2.get(i).imageURL);
        }

        for (int i = 0; i < list1.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.load);
            new DownloadImageTask(imageView, i, list1).execute(list1.get(i).imageURL);
        }
*/
        list.add(l1);
        list.add(l2);

        listAll.addAll(l1);
        listAll.addAll(l2);

        RecycleViewPut(l1);
        MainActivity.from = 0;
    }

    void addFlags(double lat, double lon, String name) {
        LatLng sydney = new LatLng(lat, lon);
        MapActivity.mMap.addMarker(new MarkerOptions().position(sydney).title(name));
    }

    private void RecycleViewPut(ArrayList<ItemObject> list){
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        /*initialised Adapter Class and set Adapter on ListView */
        mAdapter = new MyAdapter(list, R.layout.cards, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    public static String description = "";
    public static ItemObject itemObject = null;

    public void OnClick8(View view){
        itemObject = null;
        TextView textView = (TextView) view.findViewById(R.id.country_name);
        String s = textView.getText().toString();


        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i).title.equals(s)){
                description = list1.get(i).description;
                for (int j = 0; j < l2.size(); j++) {
                    if (l2.get(i).getName().equals(s)){
                        itemObject = l2.get(i);
                        break;
                    }
                }
                break;
            }
        }
        if (itemObject == null)
        for (int i = 0; i < list2.size(); i++) {
            if (list2.get(i).title.equals(s)){
                description = list2.get(i).description;
                for (int j = 0; j < l1.size(); j++) {
                    if (l1.get(i).getName().equals(s)){
                        itemObject = l1.get(i);
                        break;
                    }
                }
                break;
            }
        }

        this.startActivity(new Intent(Main7Activity.this, Main8Activity.class));

        onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main8, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.startActivity(new Intent(Main7Activity.this, MapActivity.class));
        onPause();
        return super.onOptionsItemSelected(item);
    }

    private void setPlaces() {
        for (int i = 0; i < list2.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.load);
            new DownloadImageTask(imageView, list2, i).execute(list2.get(i).imageURL);
        }
    }

    private void setEvents() {
        for (int i = 0; i < list1.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.load);
            new DownloadImageTask(imageView, i, list1).execute(list1.get(i).imageURL);
        }
    }


    class Tabs implements TabLayout.OnTabSelectedListener{
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            assert tab.getText() != null;
            if (tab.getText().equals("Places")){

                if (f2) {
                   setPlaces();
                   f2 = false;
                   simpleProgressBar.setVisibility(View.INVISIBLE);
                }

                RecycleViewPut(list.get(0));
            }
            else {

                if (f1) {
                    setEvents();
                    f1 = false;
                    simpleProgressBar.setVisibility(View.INVISIBLE);
                }

                RecycleViewPut(list.get(1));
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
            onTabSelected(tab);
        }
    }


}
