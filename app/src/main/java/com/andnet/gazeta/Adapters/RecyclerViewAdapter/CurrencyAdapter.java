package com.andnet.gazeta.Adapters.RecyclerViewAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.andnet.gazeta.Models.Currency;
import com.andnet.gazeta.R;
import java.util.ArrayList;
import java.util.List;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>{

    private List<Currency> currencies=new ArrayList<>();
    private Context context;
    private String bankName="";

    public CurrencyAdapter(Context context,String bankName){
        this.context=context;
        this.bankName=bankName;
    }

    public void clearCurrency(){
        currencies.clear();
        notifyDataSetChanged();
    }

    public void addCurrency(Currency currency){
        if(currencies.isEmpty()){
            currencies.add(currency);
            notifyDataSetChanged();
        }else {
            currencies.add(currency);
            notifyItemInserted(currencies.size()-1);
        }

    }

    @Override
    public CurrencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new CurrencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CurrencyViewHolder holder, int position) {

         Currency currency=currencies.get(position);
         holder.currencyName.setText(currency.getCurencyName());
         holder.soldTextView.setText(currency.getCurencySale());
         holder.boughtTextView.setText(currency.getCurenctBaught());

    }

    @Override
    public int getItemCount() {
        return currencies.size();
    }

    public class CurrencyViewHolder extends RecyclerView.ViewHolder {

        TextView currencyName;
        TextView boughtTextView;
        TextView soldTextView;

        public CurrencyViewHolder(View itemView) {
            super(itemView);
            currencyName=itemView.findViewById(R.id.currencyName);
            boughtTextView=itemView.findViewById(R.id.baughtTextView);
            soldTextView=itemView.findViewById(R.id.soldTextView);
        }
    }



}
