package com.proyecto.albanbassante.espelapp;

/**
 * Created by gonzaloalban on 6/5/15.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    private final Bundle fragmentBundle;

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb, Bundle data) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
        fragmentBundle = data;
    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if(position == 0) // if the position is 0 we are returning the First tab
        {
            Tab1 tab1 = new Tab1();
            tab1.setArguments(this.fragmentBundle);
            return tab1;
        }

        if(position == 1) // if the position is 0 we are returning the First tab
        {
            Tab2 tab2 = new Tab2();
            tab2.setArguments(this.fragmentBundle);
            return tab2;
        }

        if(position == 2) // if the position is 0 we are returning the First tab
        {
            Tab3 tab3 = new Tab3();
            tab3.setArguments(this.fragmentBundle);
            return tab3;
        }


        return null;
    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}