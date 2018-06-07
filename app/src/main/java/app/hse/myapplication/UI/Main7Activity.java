package app.hse.myapplication.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.ArrayList;
import app.hse.myapplication.ItemObject;
import app.hse.myapplication.MyAdapter;
import app.hse.myapplication.R;
import packs.ClassLib.Event;
import packs.ClassLib.Place;
import packs.Logic.BasicRequest;

public class Main7Activity extends AppCompatActivity {
    private static MyAdapter eventsAdapter;
    private static MyAdapter placesAdapter;
    private RecyclerView mRecyclerView;
    private ArrayList<ArrayList<ItemObject>> list = new ArrayList<>();

    static ArrayList<Place> listPlaces;
    static ArrayList<Event> listEvents;

    static boolean f1 = false, f2 = false;

    private ImageView imageView;

    private ArrayList<ItemObject> listAll = new ArrayList<>();

    public static ArrayList<ItemObject> placesItems = new ArrayList<>();
    public static ArrayList<ItemObject> eventsItems = new ArrayList<>();

    public static void setPlaces(ArrayList<Place> list) {
        listPlaces = list;
        f2 = true;
    }

    public static void setEvents(ArrayList<Event> list) {
        listEvents = list;
        f1 = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Places"));
        tabLayout.addTab(tabLayout.newTab().setText("Events"));
        tabLayout.addOnTabSelectedListener(new Tabs());

        placesItems = new ArrayList<>();
        eventsItems = new ArrayList<>();

        ArrayList<String> moods = new ArrayList<>();

        if (MainActivity.from == 1)
            moods = Main3Activity.getTags();
        else if (MainActivity.from == 2)
            moods = Main4Activity.getTags();

        BasicRequest.getPlacesToGUI(moods);
        BasicRequest.getEventsToGUI(moods);

        imageView = new ImageView(this);

        list.add(placesItems);
        list.add(eventsItems);

        listAll.addAll(placesItems);
        listAll.addAll(eventsItems);

        initRecycleView();
        MainActivity.from = 0;
    }

    private static void placeToItem(ArrayList<Place> res) {
        for (Place place : res) {
            placesItems.add(new ItemObject(place.title, place.imageURL, place.description));
        }
    }

    private static void eventToItem(ArrayList<Event> res) {
        for (Event event: res) {
            eventsItems.add(new ItemObject(event.title, event.imageURL, event.description));
        }
    }

    public static void updatePlaces(ArrayList<Place> result) {
        placeToItem(result);
        placesAdapter.setData(placesItems);
    }

    public static void updateEvents(ArrayList<Event> result) {
        eventToItem(result);
        eventsAdapter.setData(eventsItems);
    }

    private void initRecycleView(){
        mRecyclerView =  findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        /*initialised Adapter Class and set Adapter on ListView */
        placesAdapter = new MyAdapter(null, R.layout.cards, this, itemObject1 -> Main8Activity.start(this, itemObject1));
        eventsAdapter = new MyAdapter(null, R.layout.cards, this, itemObject1 -> Main8Activity.start(this, itemObject1));

        mRecyclerView.setAdapter(placesAdapter);
    }

    public static String description = "";

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

    class Tabs implements TabLayout.OnTabSelectedListener{
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            assert tab.getText() != null;
            if (tab.getText().equals("Places")){

                if (f2) {
                   f2 = false;
                }

                mRecyclerView.setAdapter(placesAdapter);
            }
            else {

                if (f1) {
                    f1 = false;
                }
                mRecyclerView.setAdapter(eventsAdapter);
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
