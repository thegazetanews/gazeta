package com.andnet.gazeta.Fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andnet.gazeta.R;
import com.andnet.gazeta.ui.CustomToolbar;
import com.andnet.gazeta.ui.MenuDrawable;


public class FavoriteFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View mainView = inflater.inflate(R.layout.read_later_fragment, container, false);
        CustomToolbar toolbar=mainView.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.main_menu);
        toolbar.setTitle(getString(R.string.fav));
        Drawable menuDrawable=new MenuDrawable();
        toolbar.setNavigationIcon(menuDrawable);
        return mainView;
    }



}
