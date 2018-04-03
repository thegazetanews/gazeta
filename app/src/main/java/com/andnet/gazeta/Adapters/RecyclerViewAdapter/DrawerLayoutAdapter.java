package com.andnet.gazeta.Adapters.RecyclerViewAdapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andnet.gazeta.R;
import com.andnet.gazeta.ui.Componenet.CustomSwitch;
import com.andnet.gazeta.ui.Divider;
import com.andnet.gazeta.ui.EmptyView;
import com.andnet.gazeta.ui.Theme;

import java.util.ArrayList;
import java.util.List;

public class DrawerLayoutAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int divider=1;
    private static final int header=2;
    private static final int menu_item=3;
    private static final int menu_item_switch=4;
    private static final int space=5;

    private List<SettingItem> settingItemList=new ArrayList<>();
    private Context context;

    public OnDrawerItemClickListner onDrawerItemClickListner;

    public interface OnDrawerItemClickListner{
        void onItemClicked(int id,boolean on);
    }


    public DrawerLayoutAdapter(Context context){
       this.context=context;
       onDrawerItemClickListner=(OnDrawerItemClickListner)context;
       init();
    }

    private void init(){
        settingItemList.add(new SettingItem("Settings", 0,0,header));
        settingItemList.add(new SettingItem("", 0,1,space));
        settingItemList.add(new SettingItem("Categories", R.drawable.ic_cat_side,2,menu_item));
        settingItemList.add(new SettingItem("Source", R.drawable.ic_source_side,3,menu_item));
        settingItemList.add(new SettingItem("Image loading", R.drawable.ic_image,4,menu_item_switch));

        settingItemList.add(new SettingItem("", 0,21,divider));
        settingItemList.add(new SettingItem("User Interface", 0,20,header));
        settingItemList.add(new SettingItem("", 0,19,space));

        settingItemList.add(new SettingItem("Tab Layout", R.drawable.ic_tab,22,menu_item));
        settingItemList.add(new SettingItem("Theme", R.drawable.ic_color,5,menu_item));
        settingItemList.add(new SettingItem("Dark theme", R.drawable.ic_dark_theme,6,menu_item_switch));
        settingItemList.add(new SettingItem("Font", R.drawable.ic_font,7,menu_item));
        settingItemList.add(new SettingItem("Article Text Size", R.drawable.ic_text_size,8,menu_item));
        settingItemList.add(new SettingItem("Article View Mode", R.drawable.ic_view_mode,19,menu_item));

        settingItemList.add(new SettingItem("", 0,9,divider));
        settingItemList.add(new SettingItem("Notification", 0,10,header));
        settingItemList.add(new SettingItem("", 0,11,space));

        settingItemList.add(new SettingItem("Notification", R.drawable.ic_notifications,12,menu_item_switch));
        settingItemList.add(new SettingItem("Ringtone", R.drawable.ic_sound,13,menu_item));

        settingItemList.add(new SettingItem("", 0,14,divider));
        settingItemList.add(new SettingItem("Language", 0,15,header));
        settingItemList.add(new SettingItem("", 0,16,space));

        settingItemList.add(new SettingItem("App Language", R.drawable.ic_app_lang,17,menu_item));
        settingItemList.add(new SettingItem("Content Language", R.drawable.ic_language,18,menu_item));
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case divider:
                return new SideMenuItemDivider(new Divider(context));
            case header:
                return new SideMenuItemHeader(LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_text_view,parent,false));
            case menu_item:
                return new SideMenuItem(LayoutInflater.from(parent.getContext()).inflate(R.layout.side_menu_item,parent,false));
            case menu_item_switch:
                return new SideMenuItemSwitch(LayoutInflater.from(parent.getContext()).inflate(R.layout.side_menu_item_switch,parent,false));
            case space:
                return new SideMenuItemSpace(new EmptyView(context));
                default:
                    return new SideMenuItemSpace(new EmptyView(context));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {
        switch (getItemViewType(i)){
            case divider:
                break;
            case header:
                ((SideMenuItemHeader)holder).textView.setText(settingItemList.get(i).title);
                break;
            case menu_item:
                SideMenuItem sideMenuItem=(SideMenuItem)holder;
                sideMenuItem.icon.setImageResource(settingItemList.get(i).icon);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    sideMenuItem.icon.getDrawable().setTint(Theme.side_nav_item_icon_color);
                }else {
                    sideMenuItem.icon.getDrawable().setColorFilter(Theme.side_nav_item_icon_color, PorterDuff.Mode.SRC_IN);
                }
                sideMenuItem.title.setText(settingItemList.get(i).title);
                break;
            case menu_item_switch:
                SideMenuItemSwitch sideMenuItemSwitch=(SideMenuItemSwitch)holder;
                sideMenuItemSwitch.icon.setImageResource(settingItemList.get(i).icon);
                sideMenuItemSwitch.customSwitch.setText(settingItemList.get(i).title);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    sideMenuItemSwitch.icon.getDrawable().setTint(Theme.side_nav_item_icon_color);
                }else {
                    sideMenuItemSwitch.icon.getDrawable().setColorFilter(Theme.side_nav_item_icon_color, PorterDuff.Mode.SRC_IN);
                }
//                if(settingItemList.get(i).id==22){
//                    sideMenuItemSwitch.customSwitch.setChecked(PreferenceUtility.isTabIconEnabled());
//                }
//                sideMenuItemSwitch.customSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
//                    SettingItem item=settingItemList.get(i);
//                    if(item!=null){
//                        onDrawerItemClickListner.onItemClicked(item.id,isChecked);
//                    }
//                });
                break;
            case space:
                break;
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position < 0 || position >= settingItemList.size()) {
            return -1;
        }
        SettingItem item = settingItemList.get(position);
        return item != null ? item.type : -1;
    }


    public long getItemId(int position) {
        if (position < 0 || position >= settingItemList.size()) {
            return -1;
        }
        SettingItem item = settingItemList.get(position);
        return item != null ? item.id : -1;
    }


    @Override
    public int getItemCount() {
        return settingItemList==null?0:settingItemList.size();
    }


    public class SideMenuItem extends RecyclerView.ViewHolder{

        TextView title;
        ImageView icon;
        public SideMenuItem(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.sidetext);
            icon=itemView.findViewById(R.id.sideicon);
            title.setTextColor(Theme.side_nav_item_text_color);

            itemView.setOnClickListener(v -> {
                SettingItem item=settingItemList.get(getAdapterPosition());
                if(item!=null){
                    onDrawerItemClickListner.onItemClicked(item.id,false);
                }
            });
        }
    }

    public class SideMenuItemSwitch extends RecyclerView.ViewHolder{

        ImageView icon;
        CustomSwitch customSwitch;
        @SuppressLint("ClickableViewAccessibility")
        public SideMenuItemSwitch(View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.sideicon);
            customSwitch=itemView.findViewById(R.id.sideswitch);

        }
    }

    public class SideMenuItemHeader extends RecyclerView.ViewHolder{
        TextView textView;
        public SideMenuItemHeader(View itemView) {
            super(itemView);
            textView=(TextView)itemView;
            textView.setTextColor(Theme.side_nav_item_header_text_color);


        }
    }


    public class SideMenuItemSpace extends RecyclerView.ViewHolder{
        public SideMenuItemSpace(View itemView) {
            super(itemView);
        }
    }

    public class SideMenuItemDivider extends RecyclerView.ViewHolder{
        public SideMenuItemDivider(View itemView) {
            super(itemView);
        }
    }


    public class SettingItem{
        public String title;
        public int icon;
        public int id;
        public int type;

        public SettingItem(String title, int icon, int id,int type) {
            this.title = title;
            this.icon = icon;
            this.id = id;
            this.type=type;
        }
    }



}
