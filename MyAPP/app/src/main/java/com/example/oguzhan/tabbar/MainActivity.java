package com.example.oguzhan.tabbar;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class MainActivity extends AppCompatActivity {

    SectionPageAdapter sectionPageAdapter;
    private  ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.container);
        setupPager(viewPager);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
  //      tabLayout.getTabAt(0).setIcon(R.mipmap.ic_launcher);

       /* ParseObject object = new ParseObject("posts");
        object.put("name","oguzhan");
        object.put("surname","ozer");
        object.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

            }
        });
*/


    }

    private  void setupPager(ViewPager viewPager){

        SectionPageAdapter sectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        sectionPageAdapter.addFragment(new FragmentHot(),"Recent");
        sectionPageAdapter.addFragment(new FragmentFresh(),"Category");
        sectionPageAdapter.addFragment(new FragmentRecent(),"Most Viewed");
        sectionPageAdapter.addFragment(new FragmentTrending(),"Add Post");
        viewPager.setAdapter(sectionPageAdapter);
    }


}
