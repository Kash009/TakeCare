package com.example.shoaibiqbal.takecare;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

public class MainPage extends AppCompatActivity {

    private ViewPager viewPagerObj = null;
    TabLayout mainTabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Takecare");
        setSupportActionBar(toolbar);

        FragmentManager FM = getSupportFragmentManager();
        MainPageFragmentAdapter mainPageFragmentAdapter = new MainPageFragmentAdapter(FM);

        // Tablayout for placing tabs on the main page
        mainTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mainTabLayout.addTab(mainTabLayout.newTab().setText(mainPageFragmentAdapter.tabName[0]));
        mainTabLayout.addTab(mainTabLayout.newTab().setText(mainPageFragmentAdapter.tabName[1]));
        mainTabLayout.addTab(mainTabLayout.newTab().setText(mainPageFragmentAdapter.tabName[2]));
        mainTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // ViewPager for introducing multiple frames in the main page
        viewPagerObj = (ViewPager) findViewById(R.id.pager);
        //FragmentManager FM = getSupportFragmentManager();
        viewPagerObj.setAdapter(mainPageFragmentAdapter);
        viewPagerObj.addOnPageChangeListener((new TabLayout.TabLayoutOnPageChangeListener(mainTabLayout)));
        viewPagerObj.setOffscreenPageLimit(3);
        mainTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerObj.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_page, menu);
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

    /** currently inheriting from Frag Pager Adapter - NOT Frag State Pager Adapter
     * as current design doesn't require saving states in the pages. Same fragment
     * can be created and destroyed to save MEM. However later design may require
     * state Pager Adapter.
     */
    class MainPageFragmentAdapter extends FragmentPagerAdapter{
        // total fragments for now
        public String[] tabName = { "Healthy Tips", "Find..", "Easy Remedies"};
        public MainPageFragmentAdapter(FragmentManager fm)
        {
            super(fm);
            //this.totalPages = numOfTabs;
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) { //first page News Feed
                return new NewsFeedFragment();
            }
            else if (position == 1) { //second page find Dr/Lab
                return new FindThingsFragment();
            }
            if (position == 2) {
                return new FrgRemedies();
            }
            return null;
        }

        @Override
        public int getCount() {
            return tabName.length;
        }
    }
}
