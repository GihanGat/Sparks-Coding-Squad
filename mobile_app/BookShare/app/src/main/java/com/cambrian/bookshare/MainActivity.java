package com.cambrian.bookshare;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;

import com.cambrian.bookshare.ui.gallery.GalleryFragment;
import com.cambrian.bookshare.ui.home.HomeFragment;
import com.cambrian.bookshare.ui.user.LoginFragment;
import com.cambrian.bookshare.ui.slideshow.SlideshowFragment;
import com.cambrian.bookshare.ui.user.SignUpFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

//public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
public class MainActivity extends AppCompatActivity{
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView navigationView;

    private ActionBarDrawerToggle drawerToggle;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        //navigationView.setNavigationItemSelectedListener(this);
        drawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open,  R.string.drawer_close);

        mAppBarConfiguration = new AppBarConfiguration.Builder( R.id.nav_home, R.id.nav_login, R.id.nav_signup,  R.id.nav_gallery, R.id.nav_slideshow , R.id.nav_user_update , R.id.nav_add_new_book , R.id.nav_user_update) //,
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);//
        NavigationUI.setupWithNavController(navigationView, navController);



       // final ActionBar actionbar = getSupportActionBar();
       // actionbar.setDisplayHomeAsUpEnabled(true);
       // actionbar.setHomeAsUpIndicator(R.drawable.ic_home);

        drawerToggle.setDrawerIndicatorEnabled(true);
        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//                if ( item.getItemId() == R.id.nav_login){
//                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,new LoginFragment()).commit();
//                    item.setChecked(true);
//                } else if ( item.getItemId() == R.id.nav_home) {
//                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,new HomeFragment()).commit();
//                    item.setChecked(true);
//                } else if ( item.getItemId() == R.id.nav_gallery) {
//                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new GalleryFragment()).commit();
//                    item.setChecked(true);
//                }else if ( item.getItemId() == R.id.nav_slideshow) {
//                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new SlideshowFragment()).commit();
//                    item.setChecked(true);
//                }
//
//                drawer.closeDrawer(GravityCompat.START);
//                return true;
//            }
//        });
      //  setupDrawerContent(navigationView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

//    private void setupDrawerContent(NavigationView navigationView) {
//        navigationView.setNavigationItemSelectedListener(
//                new NavigationView.OnNavigationItemSelectedListener() {
//                    @Override
//                    public boolean onNavigationItemSelected(MenuItem menuItem) {
//                        selectDrawerItem(menuItem);
//                        return true;
//                    }
//                });
//    }
//
//
//    public void selectDrawerItem(MenuItem menuItem) {
//        // Create a new fragment and specify the fragment to show based on nav item clicked
//        Fragment fragment = null;
//        Class fragmentClass;
//        switch(menuItem.getItemId()) {
//            case R.id.nav_home:
//                fragmentClass = HomeFragment.class;
//                break;
//            case R.id.nav_login:
//                fragmentClass = LoginFragment.class;
//                break;
//            case R.id.nav_signup:
//                fragmentClass = SignUpFragment.class;
//                break;
//            case R.id.nav_gallery:
//                fragmentClass = GalleryFragment.class;
//                break;
//            case R.id.nav_slideshow:
//                fragmentClass = SlideshowFragment.class;
//                break;
//            default:
//                fragmentClass = HomeFragment.class;
//        }
//
//        try {
//            fragment = (Fragment) fragmentClass.newInstance();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // Insert the fragment by replacing any existing fragment
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.fragment_content, fragment).commit();
//
//        // Highlight the selected item has been done by NavigationView
//        menuItem.setChecked(true);
//        // Set action bar title
//        setTitle(menuItem.getTitle());
//        // Close the navigation drawer
//        drawer.closeDrawers();
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
        }
    //    setupDrawerContent(navigationView);

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
        //return super.onOptionsItemSelected(item);
    }

    // `onPostCreate` called when activity start-up is complete after `onStart()`
    // NOTE 1: Make sure to override the method with only a single `Bundle` argument
    // Note 2: Make sure you implement the correct `onPostCreate(Bundle savedInstanceState)` method.
    // There are 2 signatures and only `onPostCreate(Bundle state)` shows the hamburger icon.
//    @Override
//    protected void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        // Sync the toggle state after onRestoreInstanceState has occurred.
//        drawerToggle.syncState();
//    }
//
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        // Pass any configuration change to the drawer toggles
//        drawerToggle.onConfigurationChanged(newConfig);
//    }


//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.nav_home:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content,new HomeFragment()).commit();
//                break;
//            case R.id.nav_login:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content,new LoginFragment()).commit();
//                break;
//            case R.id.nav_signup:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content,new SignUpFragment()).commit();
//                break;
//            case R.id.nav_gallery:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content,new GalleryFragment()).commit();
//                break;
//            case R.id.nav_slideshow:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content,new SlideshowFragment()).commit();
//                break;
//        }
//
//        drawer.closeDrawer(GravityCompat.START);
 //       return true;
 //   }
}
