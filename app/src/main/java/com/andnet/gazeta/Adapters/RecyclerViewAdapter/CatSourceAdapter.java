package com.andnet.gazeta.Adapters.RecyclerViewAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andnet.gazeta.Models.Category;
import com.andnet.gazeta.Models.Header;
import com.andnet.gazeta.Models.Source;
import com.andnet.gazeta.R;
import com.andnet.gazeta.ui.Componenet.SmoothCheckBox;
import com.andnet.gazeta.ui.Theme;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;



public class CatSourceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public List<Object> objectList=new ArrayList<>();
    public List<Object> selectedObjectList=new ArrayList<>();

    public static final int sourcetype=1;
    public static final int cattype=2;
    public static final int header=3;

    private Context context;

    @Override
    public int getItemViewType(int position) {
        if(objectList.get(position) instanceof Source){
            return sourcetype;
        }else if(objectList.get(position) instanceof Category){
            return cattype;
        }else if(objectList.get(position) instanceof Header){
            return header;
        }
        return -1;
    }

    public void addHeaader(Header o){
        objectList.add(o);
    }

    public CatSourceAdapter(Context context) {
        this.context=context;

    }

    public void setSourceCatModels(List<Source> sourceCatModels) {
            objectList.addAll(sourceCatModels);
            notifyDataSetChanged();
    }


    public void setCatagoryList(List<Category> sourceCatModels) {
        objectList.addAll(sourceCatModels);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType==sourcetype){
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.source_list_item,parent,false);
            return new sourceviewholder(view);
        }else if(viewType==cattype){
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_list_item,parent,false);
            return new catviewholder(view);
        }else{
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.header_item,parent,false);
            return new headerviewholder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Object o=objectList.get(position);

        if(getItemViewType(position) == sourcetype && o instanceof Source){
            Source source=(Source)o;

            if(source.isEnabled()){
                selectedObjectList.add(o);
                source.setEnabled(false);
            }

            sourceviewholder sourceviewholder=(sourceviewholder)holder;
            sourceviewholder.image.setImageURI(source.getImage());
            sourceviewholder.name.setText(source.getName());
            RoundingParams roundingParams = RoundingParams.fromCornersRadius(5f);
            roundingParams.setBorder(Theme.avatar_enale_color, 3.0f);
            roundingParams.setRoundAsCircle(true);
            sourceviewholder.image.getHierarchy().setRoundingParams(roundingParams);

            if(selectedObjectList.contains(o)){
                sourceviewholder.check.setChecked(true,false);
            }else{
                sourceviewholder.check.setChecked(false,false);
            }

            sourceviewholder.view.setOnClickListener(v -> {

                if(selectedObjectList.contains(o)){
                    selectedObjectList.remove(o);
                    sourceviewholder.check.setChecked(false,false);
                }else{
                    selectedObjectList.add(o);
                    sourceviewholder.check.setChecked(true,false);

                }

            });

        }else if(getItemViewType(position)==cattype && o instanceof Category){
            Category cat=(Category) o;

            if(cat.isEnabled()){
                selectedObjectList.add(o);
                cat.setEnabled(false);
            }



            catviewholder catviewholder=(catviewholder)holder;
            catviewholder.image.setImageResource(cat.getRes());
            catviewholder.name.setText(cat.getName());



            if(selectedObjectList.contains(o)){
                catviewholder.check.setChecked(true,false);
            }else{
                catviewholder.check.setChecked(false,false);
            }

            catviewholder.view.setOnClickListener(v -> {
                if(selectedObjectList.contains(o)){
                    selectedObjectList.remove(o);
                    catviewholder.check.setChecked(false,false);
                }else{
                    selectedObjectList.add(o);
                    catviewholder.check.setChecked(true,false);
                }
            });

        }else if(getItemViewType(position)==header){
            Header header=(Header) o;
            headerviewholder headerviewholder=(headerviewholder)holder;
            headerviewholder.header.setText(header.title);

        }

    }

    @Override
    public int getItemCount() {
        return objectList!=null?objectList.size():0;
    }

    public class sourceviewholder extends RecyclerView.ViewHolder {
        SimpleDraweeView image;
        TextView name;
        SmoothCheckBox check;
        View view;
        public sourceviewholder(View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
            check=itemView.findViewById(R.id.check);
            view=itemView;

        }
    }

    public class catviewholder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        SmoothCheckBox check;
        View view;
        public catviewholder(View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
            check=itemView.findViewById(R.id.check);
            view=itemView;
        }
    }

    public class headerviewholder extends RecyclerView.ViewHolder{

        TextView header;

        public headerviewholder(View itemView) {
            super(itemView);
            header=itemView.findViewById(R.id.header);
        }
    }


    public List<Object> getSelectedObjectList(){
        return selectedObjectList;
    }
}
