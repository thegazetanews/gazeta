//package com.andnet.gazeta.Adapters.ViewPagerAdapters;
//
//import android.content.Context;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentStatePagerAdapter;
//
//import com.andnet.gazeta.Databases.GazetaDatabase;
//import com.andnet.gazeta.Fragments.HomeListFragment;
//import com.andnet.gazeta.Models.Category;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//
//public class HomeFragmentPagerAdapter extends FragmentStatePagerAdapter {
//
//    private List<Category> tabItems =new ArrayList<>();
//    private Context context;
//
//    public HomeFragmentPagerAdapter(FragmentManager fm, Context context) {
//        super(fm);
//        this.context=context;
//        init();
//    }
//
//    private void init(){
//        List<Category> tempitems=GazetaDatabase.getDatabase(context).dao().getAllCatagory();
//        tabItems.clear();
//        Collections.sort(tempitems, (cat1, cat2) -> {
//            if(cat1.getPriority()>cat2.getPriority()){
//                return 1;
//            }else{
//                return -1;
//            }
//        });
//        for(Category item:tempitems){
//            if(item.isVisibility())tabItems.add(item);
//        }
//    }
//
//    @Override
//    public void notifyDataSetChanged() {
//        init();
//        super.notifyDataSetChanged();
//    }
//
//    @Override
//    public Fragment getItem(int position) {
//        return HomeListFragment.getInstance(tabItems.get(position).getName());
//    }
//
//    @Override
//    public int getCount() {
//        return tabItems!=null?tabItems.size():0;
//    }
//
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return tabItems.get(position).getName();
//    }
//
//
//
//
//
//}
