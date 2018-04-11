package com.andnet.gazeta.ui.Dialogs;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.andnet.gazeta.Activityies.MainActivity;
import com.andnet.gazeta.Databases.GazetaDatabase;
import com.andnet.gazeta.Models.Category;
import com.andnet.gazeta.PreferenceUtility;
import com.andnet.gazeta.R;
import com.andnet.gazeta.ui.Componenet.CustomCheckBox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CategoryChooserBottomSheet extends BottomSheetDialogFragment{

    private RecyclerView rv;

    public OnTabItemChanged onTabItemChanged;

    public interface OnTabItemChanged{
        void tabchanged();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context!=null){
           if( context instanceof MainActivity){
               onTabItemChanged=(OnTabItemChanged)context;
           }
        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View view=View.inflate(getContext(),R.layout.cat_bottom_sheet,null);
        rv=view.findViewById(R.id.rv);
        if(PreferenceUtility.getAppTheme().equals("dark")){
            view.setBackgroundColor(Color.parseColor("#212121"));
        }
        CataSelectorAdapter cataSelectorAdapter=new CataSelectorAdapter();
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(cataSelectorAdapter);
        ItemTouchHelper.Callback callback =
                new EditItemTouchHelperCallback(cataSelectorAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(rv);
        dialog.setContentView(view);
    }

    public interface ItemTouchHelperAdapter {

        void onItemMove(int fromPosition, int toPosition);

    }

    public class CataSelectorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemTouchHelperAdapter{

       private List<Category> catagories=new ArrayList<>();

        CataSelectorAdapter(){
            catagories= GazetaDatabase.getDatabase(getContext()).dao().getAllCatagory();
            Collections.sort(catagories, (cat1, cat2) -> {
                if(cat1.getPriority()>cat2.getPriority()){
                    return 1;
                }else{
                    return -1;
                }
            });
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new TabItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_cat_item,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            TabItemHolder tabItemHolder=(TabItemHolder)holder;
            tabItemHolder.icon.setImageResource(catagories.get(position).getRes());


            tabItemHolder.check.setText(catagories.get(position).getName());

            if(catagories.get(position).isVisibility()){
                tabItemHolder.check.setChecked(true);

            }else {
                tabItemHolder.check.setChecked(false);

            }
            ((TabItemHolder) holder).check.setOnClickListener(v -> {
                if(catagories.get(position).isVisibility()){
                    if(GazetaDatabase.getDatabase(getContext()).dao().getAllVisibleCategories().size()==1){
                        tabItemHolder.check.setChecked(true);
                        Toast.makeText(getContext(),"at list one source must be selected",Toast.LENGTH_SHORT).show();
                    }else{
                        catagories.get(position).setVisibility(false);
                        tabItemHolder.check.setChecked(false);
                        GazetaDatabase.getDatabase(getContext()).dao().updateCategory(catagories.get(position));
                        if(onTabItemChanged!=null){
                            onTabItemChanged.tabchanged();
                        }
                    }
                }else{
                    tabItemHolder.check.setChecked(true);
                    catagories.get(position).setVisibility(true);
                    GazetaDatabase.getDatabase(getContext()).dao().updateCategory(catagories.get(position));
                    GazetaDatabase.getDatabase(getContext()).dao().updateCategory(catagories.get(position));
                    if(onTabItemChanged!=null){
                        onTabItemChanged.tabchanged();
                    }
                }

            });

        }

        @Override
        public int getItemCount() {
            return catagories!=null?catagories.size():0;
        }

        @Override
        public void onItemMove(int fromPosition, int toPosition) {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(catagories, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(catagories, i, i - 1);
                }
            }
            Category firtCat=catagories.get(fromPosition);
            Category secCat=catagories.get(toPosition);
            int first=firtCat.getPriority();
            int sec=secCat.getPriority();
            firtCat.setPriority(sec);
            secCat.setPriority(first);
            GazetaDatabase.getDatabase(getContext()).dao().updateCategory(firtCat);
            GazetaDatabase.getDatabase(getContext()).dao().updateCategory(secCat);
            notifyItemMoved(fromPosition, toPosition);
            if(onTabItemChanged!=null){
                onTabItemChanged.tabchanged();

            }

        }

        public class TabItemHolder extends RecyclerView.ViewHolder{
            ImageView icon;
            CustomCheckBox check;
            ImageView order;
            public TabItemHolder(View itemView) {
                super(itemView);
                icon = itemView.findViewById(R.id.image);
                check = itemView.findViewById(R.id.check);
                order = itemView.findViewById(R.id.order);

                Drawable drawable=order.getDrawable();
                if(drawable!=null){
                    if(PreferenceUtility.getAppTheme().equals("dark")){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            drawable.setTint(Color.WHITE);
                        }else{
                           drawable.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                        }
                    }else{
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            drawable.setTint(Color.parseColor("#212121"));
                        }else{
                            drawable.setColorFilter(Color.parseColor("#212121"), PorterDuff.Mode.SRC_IN);
                        }
                    }
                }

            }
        }
    }

    public static class TabItem{
        public String title;
        public int icon;
        public boolean enabled;
        public int order;

        public TabItem(String title, int icon, boolean enabled, int order) {
            this.title = title;
            this.icon = icon;
            this.enabled = enabled;
            this.order = order;
        }
    }

    public class EditItemTouchHelperCallback extends ItemTouchHelper.Callback {

        private final CataSelectorAdapter mAdapter;

        public EditItemTouchHelperCallback(CataSelectorAdapter adapter) {
            mAdapter = adapter;
        }

        @Override
        public boolean isLongPressDragEnabled() {
            return true;
        }

        @Override
        public boolean isItemViewSwipeEnabled() {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        }

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
            return makeMovementFlags(dragFlags, swipeFlags);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                              RecyclerView.ViewHolder target) {
            mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
            return true;
        }



    }


}
