package com.example.user.keepsolid.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.keepsolid.R;
import com.example.user.keepsolid.ui.fragments.ProfileFragment;
import com.example.user.keepsolid.ui.fragments.RaitingEmployeeFragment;
import com.example.user.keepsolid.ui.fragments.calendar.CalendarFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView profileImageView;
    TextView profileTextName;
    TextView profileTextPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("Grampus");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        replaceWithFragment(new ProfileFragment(), null);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerLayout = navigationView.getHeaderView(0);// я ебу нахуй оно, если я мог просто его импортировать же....просто работает и х с ним

        // finding our elements
        profileImageView = headerLayout.findViewById(R.id.imageView);
        profileTextName = headerLayout.findViewById(R.id.name);
        profileTextPosition = headerLayout.findViewById(R.id.position);
    }

    public void setImage(String profileImageUrl, String name, String position){
        // load image into circleImageView
        if(!TextUtils.isEmpty(profileImageUrl)) {
            Picasso.get()
                    .load(profileImageUrl)
                    .into(profileImageView);
        } else {
            profileImageView.setImageDrawable(getDrawable(R.drawable.error_picasso));
        }
        profileTextName.setText(name);
        profileTextPosition.setText(position);
    }

    public void setName(String name, String position){
        profileTextName.setText(name);
        profileTextPosition.setText(position);
    }

    public void setPhoto(String profileImageUrl){
        if(!TextUtils.isEmpty(profileImageUrl)) {
            Picasso.get()
                    .load(profileImageUrl)
                    .into(profileImageView);
        } else {
            profileImageView.setImageDrawable(getDrawable(R.drawable.error_picasso));
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.profile) {
            replaceWithFragment(new ProfileFragment(), null);
        } else if (id == R.id.rating) {
            replaceWithFragment(new RaitingEmployeeFragment(), null);
        } /*else if (id == R.id.company_state) {
            replaceWithFragment(new ChartFragment(), null);
        } */ else if (id == R.id.smart_calendar) {
            replaceWithFragment(new CalendarFragment(), null);
        } else if (id == R.id.nav_send){
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void replaceWithFragment(Fragment fragment, Handler handler) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(fragment.getClass().getSimpleName());
        ft.replace(R.id.frgmCont, fragment);
        ft.commit();
    }
}
