package com.chemique3d.app.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.chemique3d.app.R;
import com.chemique3d.app.Settings;
import com.chemique3d.app.home;
import com.google.android.material.navigation.NavigationView;

public class Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageView bon, reac, quiz, perio, bal;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        bon = findViewById(R.id.bond);
        reac = findViewById(R.id.reaction);
        quiz = findViewById(R.id.quiz);
        perio = findViewById(R.id.periodictable);

        bon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(Dashboard.this, DetectBonds.class);
                startActivity(i);
            }
        });

        reac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(Dashboard.this, TypesOfReactions.class);
                startActivity(i);
            }
        });

        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(Dashboard.this, home.class);
                startActivity(i);
            }
        });

        perio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(Dashboard.this, PeriodicTable.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_notifications) {

            Intent intent = new Intent(this.getApplication(), Settings.class);
            startActivity(intent);

        } else if (id == R.id.nav_settings) {

            Intent intent = new Intent(this.getApplication(), Settings.class);
            startActivity(intent);

        } else if (id == R.id.nav_about) {

            Intent intent = new Intent(this.getApplication(), About.class);
            startActivity(intent);
//
//        } else if (id == R.id.nav_help) {
//
//            Intent intent = new Intent(this.getApplication(), Help.class);
//            startActivity(intent);
//
//        } else if (id == R.id.nav_invite) {
//
//            invite();
//
//        } else if (id == R.id.nav_report) {
//
//            Intent intent = new Intent(this.getApplication(), Report.class);
//            startActivity(intent);
//
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void invite() {

    }
}
