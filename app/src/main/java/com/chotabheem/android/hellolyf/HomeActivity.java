package com.chotabheem.android.hellolyf;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chotabheem.android.hellolyf.Adapters.ExpandableListAdapter;
import com.chotabheem.android.hellolyf.HelperUI.NotificationDialog;
import com.chotabheem.android.hellolyf.Utilities.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chota_bheem on 29/7/16.
 */
public class HomeActivity extends AppCompatActivity implements OnOptionSelectedListener {
    private DrawerLayout mDrawer;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;

    private Class fragmentClass;

    private int hot_number = 3;
    private TextView ui_hot = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        // Find our drawer view
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        // Setup drawer view
        setupDrawerContent(nvDrawer);

        drawerToggle = setupDrawerToggle();

        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();

        expListView = (ExpandableListView) findViewById(R.id.navigationmenu);
        expListView.setDividerHeight(0);
        prepareListData();
//        mMenuAdapter = new ExpandableListAdapter(this, listDataHeader,   listDataChild, expandableList);
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);

        //Defualt Fragment to show
        getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new FragmentMain()).commit();
    }


    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Home");
        listDataHeader.add("Medicine");
        listDataHeader.add("Psychology");
        listDataHeader.add("Wellness");
        listDataHeader.add("HealthPlay");

        // Adding child data
        List<String> Medicine = new ArrayList<String>();
        Medicine.add("General Physician");
        Medicine.add("Sexology");
        Medicine.add("Gynecology");
        Medicine.add("Pediatrics");
        Medicine.add("Cardiology");
        Medicine.add("Dental");
        Medicine.add("Diabetology");
        Medicine.add("Pulmonology");
        Medicine.add("Psychiatry");
        Medicine.add("Dermatology");
        Medicine.add("Opthalmology");

        List<String> Wellness = new ArrayList<String>();
        Wellness.add("Nutrition & Lifestyle");
        Wellness.add("Ayush");


        List<String> Healthplay = new ArrayList<String>();
        Healthplay.add("Psychological Assessment");

        listDataChild.put(listDataHeader.get(1), Medicine); // Header, Child data
        listDataChild.put(listDataHeader.get(3), Wellness);
        listDataChild.put(listDataHeader.get(4), Healthplay);
    }


    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer,toolbar, R.string.drawer_open,  R.string.drawer_close);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawer.closeDrawers();
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
//        Fragment fragment = null;
//        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.nav_first_fragment:
                Toast.makeText(this, "First Fragment", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_second_fragment:
                Toast.makeText(this, "Second Fragment", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_third_fragment: {
//                Toast.makeText(this, "Third Fragment", Toast.LENGTH_SHORT).show();
                fragmentClass = FragmentAboutUs.class;
            }
                break;
            default:
                Toast.makeText(this, "First Fragment", Toast.LENGTH_SHORT).show();
        }

        try {
            Fragment fragment = (Fragment) fragmentClass.newInstance();
            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

//        // Insert the fragment by replacing any existing fragment
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.flContent, f).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);

//        toolbar.setTitle("");
//        TextView toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
//        toolbarTitle.setTextColor(Color.BLACK);
//        toolbarTitle.setText(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }

    private void showNotifications() {


        FragmentManager fragmentManager1 = getSupportFragmentManager();
        NotificationDialog mNotificationDialog = NotificationDialog.newInstance();
        mNotificationDialog.show(fragmentManager1, "NotificationDialog");

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

                if(item.getItemId() == R.id.menu_hotlist) {
            showNotifications();
            return true;
        }
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // `onPostCreate` called when activity start-up is complete after `onStart()`
    // NOTE! Make sure to override the method with only a single `Bundle` argument
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_menu, menu);

        final MenuItem menu_hotlist = menu.findItem(R.id.menu_hotlist);
        final RelativeLayout badgeLayout = (RelativeLayout) menu_hotlist.getActionView();

        ui_hot = (TextView) badgeLayout.findViewById(R.id.hotlist_hott);
        final ImageView ntfcn_image = (ImageView) badgeLayout.findViewById(R.id.hotlist_bell);
        updateHotCount(3);

        ntfcn_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateHotCount(0);
                showNotifications();

            }
        });
//        menu_hotlist.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem menuItem) {
//                updateHotCount(0);
//                showNotifications();
//                return true;
//            }
//        });
        return true;
    }

    // call the updating code on the main thread,
// so we can call this asynchronously
    public void updateHotCount(final int new_hot_number) {
        hot_number = new_hot_number;
        if (ui_hot == null) return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (new_hot_number == 0)
                    ui_hot.setVisibility(View.INVISIBLE);
                else {
                    ui_hot.setVisibility(View.VISIBLE);
                    ui_hot.setText(Integer.toString(new_hot_number));
                }
            }
        });
    }


    @Override
    public void onOptionSelected(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
//        FragmentTransaction ft= fragmentManager.beginTransaction();
//        ft.add(R.id.flContent, fragment);
//        ft.addToBackStack(null).commit();

    }

    public void logoutBtnClick(View v){
        SessionManager session = new SessionManager(getApplicationContext());
        session.logoutUser();
//        Intent i = new Intent(HomeActivity.this, LoginActivity.class);
//        startActivity(i);

    }

    public void settingBtnClick(View v){
        getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new FragmentSettings()).commit();
        mDrawer.closeDrawers();

    }







}
