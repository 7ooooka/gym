package com.example.mahmoud.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.SwitchDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class MainActivity extends AppCompatActivity {
    private Button test;
    private TextView txt;
    protected DrawerLayout mDrawerLayout;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        test = findViewById(R.id.test);
        txt = findViewById(R.id.txt);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "font.ttf");
txt.setTypeface(custom_font);

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, myToolbar, R.string.material_drawer_open, R.string.material_drawer_close) {
            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                if (item != null && item.getItemId() == android.R.id.home) {
                    if (mDrawerLayout.isDrawerOpen(GravityCompat.END)) {
                        mDrawerLayout.closeDrawer(GravityCompat.END);
                    } else {
                        mDrawerLayout.openDrawer(GravityCompat.END);
                    }
                }
                return false;
            }
        };





        final PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withTextColor(getResources().
                getColor(R.color.white))
                .withIcon(R.drawable.face).withName("tttttttttttt");
        final SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withName("ddddddd");

        final Drawer result = new DrawerBuilder()
                .withActivity(MainActivity.this)
                .withToolbar(myToolbar)
                .withActionBarDrawerToggle(mDrawerToggle)
                .addDrawerItems(
                        new PrimaryDrawerItem().withIcon(getResources().getDrawable(R.drawable.ic_menu_camera)).withName(R.string.app_name).withSelectedTextColor(getResources().getColor(R.color.white)).withSelectedColor(getResources().getColor(R.color.colorPrimary)).withIdentifier(1).withSelectable(true),

                        new SectionDrawerItem().withName(R.string.app_name),
                        new SecondaryDrawerItem().withName(R.string.app_name).withIcon(getResources().getDrawable(R.drawable.face)).withIdentifier(20).withSelectable(false),
                      //  new DividerDrawerItem(),
                        new SwitchDrawerItem().withName("Switch").withIcon(getResources().getDrawable(R.drawable.face)).withChecked(true)

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        if (drawerItem != null)
                        {
                            Intent intent = null;
                            if (drawerItem.getIdentifier()==1){
//                                intent = new Intent(MainActivity.this,null);
                            }
                        }
                        return true;
                    }
                }).withSavedInstance(savedInstanceState).withShowDrawerOnFirstLaunch(true)
                .build();

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_camera);
        if (savedInstanceState == null) {
            result.setSelection(1);
        }
//create the drawer and remember the `Drawer` result object

    }

}
