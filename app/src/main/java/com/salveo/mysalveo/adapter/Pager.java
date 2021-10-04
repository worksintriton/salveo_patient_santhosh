package com.salveo.mysalveo.adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.salveo.mysalveo.fragmentvendor.myorders.FragementNewOrders;
import com.salveo.mysalveo.fragmentvendor.myorders.FragmentCancelledOrders;
import com.salveo.mysalveo.fragmentvendor.myorders.FragmentCompletedOrders;

import org.jetbrains.annotations.NotNull;

//Extending FragmentStatePagerAdapter
public class Pager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    //Overriding method getItem
    @Override
    public @NotNull Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                return new FragementNewOrders();
            case 1:
                return new FragmentCompletedOrders();
            case 2:
                return new FragmentCancelledOrders();
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}