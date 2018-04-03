package com.andnet.gazeta.ui.Dialogs;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.andnet.gazeta.Databases.GazetaDatabase;
import com.andnet.gazeta.Helper.GridItemDecoration;
import com.andnet.gazeta.Models.Source;
import com.andnet.gazeta.R;
import com.andnet.gazeta.ui.Componenet.SmoothCheckBox;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("RestrictedApi")
public class SourceDialogFragment extends BottomSheetDialogFragment{

    private RecyclerView recyclerView;
    private SourceAdapter adapter;

    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
         View view= LayoutInflater.from(getContext()).inflate(R.layout.choose_source_layout,null);
         recyclerView=view.findViewById(R.id.rv);

         GridLayoutManager grid=new GridLayoutManager(getContext(),3);
         recyclerView.setLayoutManager(grid);
         recyclerView.addItemDecoration(new GridItemDecoration(8));
         adapter=new SourceAdapter(GazetaDatabase.getDatabase(getContext()).dao().getAllSource());
         recyclerView.setAdapter(adapter);
         dialog.setContentView(view);
    }


    public class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.SourceViewHolder>{

        private List<Source> sourceList=new ArrayList<>();

        public   SourceAdapter(List<Source> sourceList){
            this.sourceList=sourceList;
        }

        @NonNull
        @Override
        public SourceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.source_list_item,parent,false);
            return new SourceViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull  SourceViewHolder holder, int position) {
            RoundingParams roundingParams = RoundingParams.fromCornersRadius(5f);
            roundingParams.setBorder(ContextCompat.getColor(getContext(),R.color.colorPrimary), 3.0f);
            roundingParams.setRoundAsCircle(true);
            holder.image.getHierarchy().setRoundingParams(roundingParams);

            holder.image.setImageURI(sourceList.get(position).getImage());
            holder.name.setText(sourceList.get(position).getName());

            if(sourceList.get(position).isVisibility()){
                holder.check.setChecked(true);
            }else{
                holder.check.setChecked(false);
            }
            holder.itemView.setOnClickListener(v -> {
                if(sourceList.get(position).isVisibility()){
                    if(GazetaDatabase.getDatabase(getContext()).dao().getAllenabledSource().size()==1){
                        Toast.makeText(getContext(),"at list one source must be selected",Toast.LENGTH_SHORT).show();
                    }else{
                        sourceList.get(position).setVisibility(false);
                        holder.check.setChecked(false);
                        GazetaDatabase.getDatabase(getContext()).dao().updateSource(sourceList.get(position));
                    }
                }else{
                    holder.check.setChecked(true);
                    sourceList.get(position).setVisibility(true);
                    GazetaDatabase.getDatabase(getContext()).dao().updateSource(sourceList.get(position));
                }

            });

        }

        @Override
        public int getItemCount() {
            return sourceList!=null?sourceList.size():0;
        }

        public class SourceViewHolder extends RecyclerView.ViewHolder {
             SimpleDraweeView image;
             TextView name;
             SmoothCheckBox check;
            public SourceViewHolder(View itemView) {
                super(itemView);
                name=itemView.findViewById(R.id.name);
                image=itemView.findViewById(R.id.image);
                check=itemView.findViewById(R.id.check);

            }
        }
    }

}
