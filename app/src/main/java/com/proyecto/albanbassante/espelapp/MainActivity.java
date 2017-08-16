package com.proyecto.albanbassante.espelapp;


import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;


public class MainActivity extends ActionBarActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        new CountDownTimer(4000, 1000) {

            public void onTick(long millisUntilFinished) {

            }
            public void onFinish() {
                MainActivity.this.startActivity(new Intent(MainActivity.this, MenuDrawerActivity.class));
                //MainActivity.this.startActivity(new Intent(MainActivity.this, LocationActivity.class));
                //MainActivity.this.startActivity(new Intent(MainActivity.this, TestLocalizacionActivity.class));
                //MainActivity.this.startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
                //MainActivity.this.startActivity(new Intent(MainActivity.this, Login.class));
                finish();

            }
        }.start();



    }



    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
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

}