//package com.andnet.gazeta.ui.Dialogs;
//
//import android.annotation.SuppressLint;
//import android.app.Dialog;
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.design.widget.BottomSheetDialogFragment;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.andnet.gazeta.PreferenceUtility;
//import com.andnet.gazeta.R;
//import com.andnet.gazeta.ui.Componenet.SettingHeaderView;
//import com.andnet.gazeta.ui.Divider;
//
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//import java.util.List;
//
//
//
//public class ThemeBottomSheetDialog extends BottomSheetDialogFragment{
//
//    @SuppressLint("RestrictedApi")
//    @Override
//    public void setupDialog(Dialog dialog, int style) {
//        super.setupDialog(dialog, style);
//        View mainView= LayoutInflater.from(getContext()).inflate(R.layout.them_bottom_sheet_view,null);
//        mainView.setBackgroundColor(Theme.side_nav_background_color);
//        RecyclerView recyclerView=mainView.findViewById(R.id.rv);
//        ListViewAdapter listViewAdapter=new ListViewAdapter(getContext());
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(listViewAdapter);
//        dialog.setContentView(mainView);
//    }
//
//
//    private class ListViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//
//        private Context context;
//
//        private static final int theme=0;
//
//        public ListViewAdapter(Context context){
//            this.context=context;
//            themeInfoList.addAll(Theme.ThemeInfo.themeInfoList);
//
//        }
//
//        @Override
//        public int getItemViewType(int position) {
//            return theme;
//        }
//
//
//        @NonNull
//        @Override
//        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//        return new ThemeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.theme_bottom_sheet_item_view,parent,false));
//
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
////
////                    Theme.ThemeInfo themeInfo=themeInfoList.get(position);
////                    ThemeViewHolder themeViewHolder=(ThemeViewHolder)holder;
////                    themeViewHolder.radioButton.setCheckUncheckColor(themeInfo.color);
////                    themeViewHolder.radioButton.setText(themeInfo.name);
////                    if(PreferenceUtility.getAppTheme().equals(themeInfo.name)){
////                        themeViewHolder.radioButton.setChecked(true);
////                    }else{
////                        themeViewHolder.radioButton.setChecked(false);
////                    }
////                    themeViewHolder.radioButton.setOnClickListener(v -> {
////                        PreferenceUtility.setAppTheme(themeInfoList.get(position).name);
////                        ArrayList<String> mainKeyList=Theme.getMainThemeKeys();
////                        for(String key:mainKeyList){
////                            Theme.applyThemeColor(key,themeInfo.color);
////                        }
////                        notifyDataSetChanged();
////                    });
//
//
//        }
//
//        @Override
//        public int getItemCount() {
//            return themeInfoList!=null?themeInfoList.size():0;
//        }
//
//
//        private class ThemeViewHolder extends RecyclerView.ViewHolder{
//            ThemeViewHolder(View itemView) {
//                super(itemView);
//            }
//
//        }
//
//    }
//
//}
