package com.andnet.gazeta.Adapters.ViewPagerAdapters;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.andnet.gazeta.Fragments.CurrencyListFragment;
import com.andnet.gazeta.Fragments.HomeListFragment;
import com.andnet.gazeta.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CurrencyPagerAdapter  extends FragmentStatePagerAdapter {

    private List<String> bankNameList=new ArrayList<>();

    public CurrencyPagerAdapter (FragmentManager fm,List<String> bankNameList) {
        super(fm);
        this.bankNameList=bankNameList;
    }

    @Override
    public Fragment getItem(int position) {
        return CurrencyListFragment.newInstance(bankNameList.get(position));
    }

    @Override
    public int getCount() {
        return bankNameList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return bankNameList.get(position);
    }
}
