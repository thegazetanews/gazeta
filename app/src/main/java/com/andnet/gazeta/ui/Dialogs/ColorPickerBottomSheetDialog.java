package com.andnet.gazeta.ui.Dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.andnet.gazeta.PreferenceUtility;
import com.andnet.gazeta.R;
import com.andnet.gazeta.ui.Theme;

import java.util.ArrayList;
import java.util.List;


public class ColorPickerBottomSheetDialog extends BottomSheetDialogFragment {


    public OnMainAppThemeChanged onMainAppThemeChanged;
    public interface OnMainAppThemeChanged {
        void appThemeChanged();
    }


    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        onMainAppThemeChanged=(OnMainAppThemeChanged)getContext();
        View view= LayoutInflater.from(getContext()).inflate(R.layout.color_picker_layout,null);
        if(PreferenceUtility.getAppTheme().equals("dark")){
            view.setBackgroundColor(Color.parseColor("#212121"));
        }
        GridView gridView=view.findViewById(R.id.colorGridView);
        ColorGridAdapter adapter=new ColorGridAdapter(getContext());
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener((parent, view1, position, id) -> {
            Theme.ThemeInfo themeInfo=adapter.getThemeInfo(position);
            PreferenceUtility.setAppMainThemeColor(themeInfo.color);
            if(onMainAppThemeChanged!=null){
                onMainAppThemeChanged.appThemeChanged();
            }
        });

        dialog.setContentView(view);

    }

    private class ColorGridAdapter extends BaseAdapter{

        List<Theme.ThemeInfo> themeInfoList=new ArrayList<>();
        Context context;
        ColorGridAdapter(Context context){
            this.context=context;
            themeInfoList=Theme.ThemeInfo.themeInfoList;
        }

        Theme.ThemeInfo getThemeInfo(int posation){
            return themeInfoList.get(posation);
        }

        @Override
        public int getCount() {
            return themeInfoList!=null?themeInfoList.size():0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=null;
            if(convertView==null){
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.color_item_layout,null);
            }else {
                view=convertView;
            }
            View colorView=view.findViewById(R.id.colorView);
            TextView colorName=view.findViewById(R.id.colorNameTextView);
            Theme.ThemeInfo themeInfo=themeInfoList.get(position);
            colorName.setText(themeInfo.name);
            Drawable drawable=colorView.getBackground();
            drawable.setColorFilter(themeInfo.color, PorterDuff.Mode.SRC_IN);
            colorView.setBackground(drawable);
            return view;
        }
    }


}
