package com.chitingwang.listviewcustomadapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlanetListActivityFragment extends Fragment {

    public final static String TAG = " PlanetListFragment";

    private final static String SELECTED_SHOW_PLANET_TYPE
            = "pref_selected_show";

    private final static String KEY_POSITION = "key_position";
    private final static String INITIAL_POSITION = "pref_position";

    private int mPosition;
    private List<Planet> planet_data ;

    private final static String SELECTED_TYPE = "pref_selected_type";
    private final static String WHATIF_BTN_PREF = "pref_display_button";
    public static final int SHOW_PREFERENCES = 1;

    ImageTextArrayAdapter adapter;

    public PlanetListActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_planet_list, container, false);
    }

    private List <Planet> setupPlanets() {
        String type_planet = getResources().getString(R.string.type_planet).toString();
        String type_minor_planet =
                getResources().getString(R.string.type_minor_planet).toString();
        String type_animal = getResources().getString(R.string.type_animal).toString();
        String type_radioactive = getResources().getString(R.string.type_radioactive).toString();

        SharedPreferences mySharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(getActivity());
        int showPlanetTypeId = Integer.parseInt(mySharedPreferences.
                getString(SELECTED_SHOW_PLANET_TYPE, "0"));

        List<Planet> planet_data_list ;
        Planet[] planets;

        switch (showPlanetTypeId) {
            case 1:
                planets = new Planet[]{
                        new Planet(R.drawable.wonder_symbol, "Wonder Woman", type_planet, R.drawable.bitcoin_2, "Themyscira"),
                        new Planet(R.drawable.flash_symbol, "Flash", type_planet, R.drawable.bitcoin_3, "Central City"),
                        new Planet(R.drawable.thor_symbol, "Thor", type_planet, R.drawable.bitcoin_4, "Asgard"),
                        new Planet(R.drawable.super_symbol, "Superman", type_planet, R.drawable.bitcoin_3, "Krypton")};
                break;
            case 2:
                planets = new Planet[]{
                        new Planet(R.drawable.green_symbol, "Green Hornet", type_minor_planet, R.drawable.bitcoin_1, "Los Angeles"),
                        new Planet(R.drawable.iron_symbol, "Iron Man", type_minor_planet, R.drawable.bitcoin_3, "Long Island, New York"),
                        new Planet(R.drawable.robin_symbol, "Robin", type_minor_planet, R.drawable.bitcoin_1, "Gotham City")};
                break;
            case 3:
                planets = new Planet[]{
                        new Planet(R.drawable.bat_symbol, "Batman", type_animal, R.drawable.bitcoin_2, "Gotham City"),
                        new Planet(R.drawable.spider_symbol, "Spider Man", type_animal, R.drawable.bitcoin_2, "New York City")};

                break;
            case 4:

                planets = new Planet[]{
                        new Planet(R.drawable.hulk_symbol, "The Hulk", type_radioactive, R.drawable.bitcoin_2, "Dayton, Ohio"),
                        new Planet(R.drawable.america_symbol, "Captain America", type_radioactive, R.drawable.bitcoin_4, "New York City"),
                };

                break;
            default:
                planets = new Planet[]{
                        new Planet(R.drawable.bat_symbol, "Batman", type_animal, R.drawable.bitcoin_2, "Gotham City"),
                        new Planet(R.drawable.wonder_symbol, "Wonder Woman", type_planet, R.drawable.bitcoin_2, "Themyscira"),
                        new Planet(R.drawable.green_symbol, "Green Hornet", type_minor_planet, R.drawable.bitcoin_1, "Los Angeles"),
                        new Planet(R.drawable.hulk_symbol, "The Hulk", type_radioactive, R.drawable.bitcoin_2, "Dayton, Ohio"),
                        new Planet(R.drawable.iron_symbol, "Iron Man", type_minor_planet, R.drawable.bitcoin_3, "Long Island, New York"),
                        new Planet(R.drawable.flash_symbol, "Flash", type_planet, R.drawable.bitcoin_3, "Central City"),
                        new Planet(R.drawable.thor_symbol, "Thor", type_planet, R.drawable.bitcoin_4, "Asgard"),
                        new Planet(R.drawable.spider_symbol, "Spider Man", type_animal, R.drawable.bitcoin_2, "New York City"),
                        new Planet(R.drawable.super_symbol, "Superman", type_planet, R.drawable.bitcoin_3, "Krypton"),
                        new Planet(R.drawable.robin_symbol, "Robin", type_minor_planet, R.drawable.bitcoin_1, "Gotham City"),
                        new Planet(R.drawable.america_symbol, "Captain America", type_radioactive, R.drawable.bitcoin_4, "New York City"),
                };

        }//Switch close
        // Convert array to List
        planet_data_list= new ArrayList<>(Arrays.asList(planets));
        return planet_data_list;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        SharedPreferences mySharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(getActivity());
        boolean showWhatIsButton =
                mySharedPreferences.getBoolean(WHATIF_BTN_PREF, true);

        //retrieve the information (the last click), and assign it to a variable mPosition
        if (savedInstanceState != null){
            mPosition = savedInstanceState.getInt(KEY_POSITION, 0);
            Log.d(TAG,"onActivityCreated savedInstanceState mPosition " + mPosition);
        } else {
            mPosition = mySharedPreferences.getInt(INITIAL_POSITION, 0);
            Log.d(TAG,"onActivityCreated mySharedPreferences mPosition " + mPosition);
        }

        planet_data = setupPlanets();

        Button whatButton = (Button) getActivity().findViewById(R.id.planetWhatIsItBtn);
        whatButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                clickWhatIsItButton();
            }
        });

        flipWhatIfButton(showWhatIsButton);

        ListView listView = (ListView) getActivity().findViewById(R.id.planetList);

        //ImageTextArrayAdapter adapter = new ImageTextArrayAdapter(getActivity(),
        //        R.layout.planet_item, planet_data);

        adapter = new ImageTextArrayAdapter(getActivity(),
                R.layout.planet_item, planet_data);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long arg3) {
                clickListItem(view, position);
            }
        });

    }

    private void clickListItem(View view, int position) {
        String item =
                ((TextView)view.findViewById(R.id.planet_name)).getText().toString();


        Toast.makeText(getActivity(), item, Toast.LENGTH_SHORT).show();
        mPosition = position;
    }


    private void clickWhatIsItButton() {
        String message = planet_data.get(mPosition).name + " "

                + getResources().getString(R.string.message_is_a) + " "

                + planet_data.get(mPosition).foodtype;
        Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT); toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.d(TAG," PlanetListActivityFragment onCreateOptionsMenu");
        // Inflate the menu; this adds items to the app bar if it is present.
        inflater.inflate(R.menu.planet_fragment_items, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Log.d(TAG,"PlanetListActivityFragment onOptionsItemSelected: " +
                Integer.toHexString(item.getItemId()));

        switch (item.getItemId()) {
            case R.id.whatIsIt:
                Log.d(TAG," PlanetListActivityFragment onOptionsItemSelected: whatIsIt ");
                clickWhatIsItButton();
                return true;

            case R.id.action_settings:
                startActivityForResult(new Intent(getActivity(), SettingActivity.class),SHOW_PREFERENCES);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Saving information (the last click) in the saveInstanceState
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_POSITION, mPosition);
    }


    @Override
    public void onDestroy()
    {
        savePrefs(INITIAL_POSITION, mPosition);
        super.onDestroy();
    }
    private void savePrefs(String key, int value) {
        SharedPreferences sp =
                PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor ed = sp.edit();
        ed.putInt(key, value);
        ed. apply ();
    }

    //show/hide the whatif button depending on the sharedPreference
    private void flipWhatIfButton(boolean mShowWhatIsButton)
    {
        Button whatButton = (Button)
                getActivity().findViewById(R.id.planetWhatIsItBtn);
        if (mShowWhatIsButton == false){
            whatButton.setVisibility(View.GONE);
        } else
            whatButton.setVisibility(View.VISIBLE);
    }


    @Override
    public void onActivityResult(int reqCode, int resCode, Intent data) {
        super.onActivityResult(reqCode, resCode, data);
        Log.d(TAG," onActivityResult " + " data: " + data);

        switch(reqCode) {
            case SHOW_PREFERENCES:
                SharedPreferences mySharedPreferences =
                        PreferenceManager.getDefaultSharedPreferences(getActivity());
                // get value of pref controlling show/hide of whatIsButton
                boolean showWhatIsButton =
                        mySharedPreferences.getBoolean(WHATIF_BTN_PREF, true);
                Log.d(TAG," onActivityResult " + " SHOW_PREFERENCES: mShowWhatIsButton "
                        + showWhatIsButton);
                flipWhatIfButton(showWhatIsButton);
                // get planet data corresponding to preference (pref is in the setupPlanet method)
                planet_data = setupPlanets();
                adapter.clear(); // Clear old data
                adapter.addAll(planet_data); // Add the new data
                adapter.notifyDataSetChanged(); // Notify adapter that the dataset changed
                break;
        }
    }

}
