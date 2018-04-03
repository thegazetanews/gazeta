package com.andnet.gazeta.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andnet.gazeta.Adapters.RecyclerViewAdapter.CurrencyAdapter;
import com.andnet.gazeta.Helper.Constants;
import com.andnet.gazeta.Models.Currency;
import com.andnet.gazeta.R;
import com.andnet.gazeta.Helper.ItemDivider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class CurrencyListFragment extends Fragment{
    public static final String PAGE_ARGS="currency_args";
    private String pageKey;
    private View mainView;
    private RecyclerView recyclerView;
    private CurrencyAdapter currencyAdapter;
    private List<Currency> currencyList=new ArrayList<>();
    private FirebaseDatabase firebaseDatabase;
    public static CurrencyListFragment newInstance(String pageKey){
        CurrencyListFragment currencyListFragment=new CurrencyListFragment();
        Bundle bundle=new Bundle();
        bundle.putString(PAGE_ARGS,pageKey);
        currencyListFragment.setArguments(bundle);
        return currencyListFragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            pageKey=getArguments().getString(PAGE_ARGS);
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView=inflater.inflate(R.layout.currency_list_fragment,container,false);
        recyclerView=mainView.findViewById(R.id.rv);
        firebaseDatabase=FirebaseDatabase.getInstance();
        currencyAdapter=new CurrencyAdapter(getContext(),pageKey);
        recyclerView.setAdapter(currencyAdapter);
        recyclerView.addItemDecoration(new ItemDivider(getContext()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        reference();
        return mainView;
    }


    private void reference() {
        currencyAdapter.clearCurrency();
        firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference reference=firebaseDatabase.getReference().child(Constants.ETHIOPIA).child("banks").child(pageKey);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                reference.removeEventListener(this);
               Iterable<DataSnapshot> dataSnapshotIterable=dataSnapshot.getChildren();
               for(DataSnapshot data : dataSnapshotIterable){
                   getCurrencyName(data.getKey());
               }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getCurrencyName(String key){
        DatabaseReference reference=firebaseDatabase.getReference().child(Constants.ETHIOPIA).child("banks").child(pageKey).child(key);
        reference.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot){
                reference.removeEventListener(this);
                try {
                    if(!dataSnapshot.exists())return;
                    String selling = "";
                    String buying = "";
                    String[] i = dataSnapshot.getValue().toString().split("::");
                    if (i.length == 3) {
                        buying = i[1];
                        selling = i[2];
                    } else if (i.length == 2) {
                        buying = i[0];
                        selling = i[1];
                    }
                    Currency currency=new Currency(key, selling, buying);
                    currencyAdapter.addCurrency(currency);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

}
