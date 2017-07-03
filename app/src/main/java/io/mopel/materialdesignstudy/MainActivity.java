package io.mopel.materialdesignstudy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {
  private Toolbar toolbar;
  private NavigationView navigationView;
  private DrawerLayout drawerLayout;
  private View drawerHeaderView;


  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    toolbar = (Toolbar) findViewById(R.id.app_bar);
    navigationView = (NavigationView) findViewById(R.id.navigation_view);
    drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    navigationView.setItemIconTintList(null);
    drawerHeaderView = navigationView.getHeaderView(0);

    navigationView.setNavigationItemSelectedListener(this);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle("MainActivity");
    ActionBarDrawerToggle actionBarDrawerToggle =
        new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open,
            R.string.drawer_close){
          @Override public void onDrawerOpened(View drawerView) {
            super.onDrawerOpened(drawerView);
          }

          @Override public void onDrawerClosed(View drawerView) {
            super.onDrawerClosed(drawerView);
          }


        };
    drawerLayout.addDrawerListener(actionBarDrawerToggle);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main,menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_setting:
        break;
      case R.id.action_navigation:
        startActivity(new Intent(this,SubActivity.class));
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()){
      case R.id.menu_user:
        break;
      case R.id.menu_favorite:
        break;
      case R.id.menu_setting:
        break;
      case R.id.menu_about_us:
        break;
      default:
    }
    return false;
  }
}
