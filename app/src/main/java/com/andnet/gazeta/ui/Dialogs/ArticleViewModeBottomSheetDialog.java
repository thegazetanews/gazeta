package com.andnet.gazeta.ui.Dialogs;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andnet.gazeta.R;

public class ArticleViewModeBottomSheetDialog extends BottomSheetDialogFragment {


    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View view= LayoutInflater.from(getContext()).inflate(R.layout.article_view_mode,null);
        ArticleViewModeAdapter adapter=new ArticleViewModeAdapter(getContext());
        RecyclerView recyclerView=view.findViewById(R.id.layoutModeRv);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dialog.setContentView(view);

    }

    private class ArticleViewModeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private static final int viewmode_1=0;
        private static final int viewmode_2=1;
        private static final int viewmode_3=2;


        private Context context;

        public ArticleViewModeAdapter(Context context) {
            this.context=context;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view=null;
            switch (viewType){
                case viewmode_1:
                    view=LayoutInflater.from(context).inflate(R.layout.view_mode_one,parent,false);
                    return new ViewModeOneViewHolder(view);
                case viewmode_2:
                    view=LayoutInflater.from(context).inflate(R.layout.view_mode_two,parent,false);
                    return new ViewModeTwoViewHolder(view);
                case viewmode_3:
                    view=LayoutInflater.from(context).inflate(R.layout.view_mode_two,parent,false);
                    return new ViewModeThreeViewHolder(view);
            }
            return null;
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 3;
        }


        public class ViewModeOneViewHolder extends RecyclerView.ViewHolder{
            public ViewModeOneViewHolder(View itemView) {
                super(itemView);
            }
        }

        public class ViewModeTwoViewHolder extends RecyclerView.ViewHolder{
            public ViewModeTwoViewHolder(View itemView) {
                super(itemView);
            }
        }

        public class ViewModeThreeViewHolder extends RecyclerView.ViewHolder{
            public ViewModeThreeViewHolder(View itemView) {
                super(itemView);
            }
        }

//        public class ViewModeFourViewHolder extends RecyclerView.ViewHolder{
//            public ViewModeFourViewHolder(View itemView) {
//                super(itemView);
//            }
//        }
//
//        public class ViewModeFiveViewHolder extends RecyclerView.ViewHolder{
//            public ViewModeFiveViewHolder(View itemView) {
//                super(itemView);
//            }
//        }

    }
}
