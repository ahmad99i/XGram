package com.ahmaddev.xgram;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ahmaddev.xgram.Help.Helpers;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewPager;

    String INSTGRAM_PACKAGE_NAME = "com.instagram.android";
    FloatingActionButton mFabInstagram;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        viewPager = (ViewPager) findViewById(R.id.container);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        TabsAdapter adapter = new TabsAdapter (getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);


        mFabInstagram = (FloatingActionButton) findViewById(R.id.fab);
        mFabInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openApp(getApplicationContext(),INSTGRAM_PACKAGE_NAME);
            }
        });


    }


    public static boolean openApp(Context context, String packageName) {
        PackageManager manager = context.getPackageManager();

        Intent i = manager.getLaunchIntentForPackage(packageName);
        if (i == null) {
            return false;
            //throw new PackageManager.NameNotFoundException();
        }
        i.addCategory(Intent.CATEGORY_LAUNCHER);
        context.startActivity(i);
        return true;

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
        if (id == R.id.action_instagram) {
            openAhmAdInstagram();

            return true;
        }
        if (id == R.id.action_about) {
            String strSupportMessage = "XGram \n Dev: AhmAd Ibrahim \n\n Used Libraries: \n uCrop";
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage(strSupportMessage);
            alertDialogBuilder.setPositiveButton("OK " + Helpers.getEmijoByUnicode(0x1F60D),new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void openAhmAdInstagram() {
        Intent likeIng = new Intent("android.intent.action.VIEW", Uri.parse("http://instagram.com/_u/ahmad_i_"));
        likeIng.setPackage("com.instagram.android");
        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://instagram.com/ahmad_i_")));
        }
    }
}
