package io.mopel.materialdesignstudy;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class SubActivity extends AppCompatActivity {
  private Toolbar toolbar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sub);
    toolbar = (Toolbar) findViewById(R.id.app_bar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle("SubActivity");
    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    //if (item.getItemId() == android.R.id.home){
    //  NavUtils.navigateUpFromSameTask(this);
    //}
    return super.onOptionsItemSelected(item);
  }
}
