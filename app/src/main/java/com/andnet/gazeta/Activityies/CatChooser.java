package com.andnet.gazeta.Activityies;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.andnet.gazeta.Adapters.RecyclerViewAdapter.CatSourceAdapter;
import com.andnet.gazeta.Databases.GazetaDao;
import com.andnet.gazeta.Databases.GazetaDatabase;
import com.andnet.gazeta.Helper.ItemDivider;
import com.andnet.gazeta.Models.Category;
import com.andnet.gazeta.Models.Source;
import com.andnet.gazeta.R;
import com.andnet.gazeta.ui.RadialProgressView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class CatChooser extends AppCompatActivity{

    private CatSourceAdapter adapter;
    private RadialProgressView progress;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_chooser);

        firebaseDatabase=FirebaseDatabase.getInstance();
        toolbar=findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.check_menu);

        toolbar.setOnMenuItemClickListener(item -> {
            if(item.getItemId()==R.id.done){
                if(adapter==null)return false;
                List<Object> objects=adapter.getSelectedObjectList();
                if(objects==null || objects.isEmpty()){
                    Toast.makeText(CatChooser.this,"Selected At list one source and  category",Toast.LENGTH_SHORT).show();
                }else {
                    boolean hassource=false;
                    boolean hasCat=false;
                    for(Object object:objects){
                        if(object instanceof Source){
                              hassource=true;
                              break;
                         }
                    }
                    for(Object object:objects){
                        if(object instanceof Category){
                            hasCat=true;
                            break;
                        }
                    }

                    if(!hasCat){
                        Toast.makeText(CatChooser.this,"Selected At list one category",Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    if(!hassource){
                        Toast.makeText(CatChooser.this,"Selected At list one source",Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    GazetaDao dao=GazetaDatabase.getDatabase(CatChooser.this).dao();
                    for(Object o:objects){
                        if(o instanceof Source){
                            Source source=(Source)o;
                            dao.insertSource(source);
                        }else
                        if(o instanceof Category){
                            Category cat=(Category) o;

                        }

                        Intent intent=new Intent(CatChooser.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }

            }
            return false;
        });
        recyclerView = findViewById(R.id.rv);
//        progress=findViewById(R.id.progress);
        adapter=new CatSourceAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new ItemDivider(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getSourceFromFirebase();

    }



    private void getSourceFromFirebase(){
        DatabaseReference databaseReference= firebaseDatabase.getReference("source");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> snapshotList=dataSnapshot.getChildren();
                List<Source> sourceList=new ArrayList<>();
                for(DataSnapshot data: snapshotList){
                    Source s=data.getValue(Source.class);
                    sourceList.add(s);
                }

                Collections.sort(sourceList, new Comparator<Source>() {
                    @Override
                    public int compare(Source o1, Source o2) {

                        if(o1.getPri()>o2.getPri())return -1;
                        else return 1;

                    }
                });




////                Header header=new Header();
//                header.title="Categories";
//                adapter.addHeaader(header);
//                List<CategoryChooserBottomSheet.TabItem> tabItems= PreferenceUtility.getTabIconList(CatChooser.this);
//                List<Catagory> catagories=new ArrayList<>();
//                for(CategoryChooserBottomSheet.TabItem item:tabItems){
//                    catagories.add(new Catagory(item.title,item.icon,item.enabled));
//                }
//                adapter.setCatagoryList(catagories);
////                header=new Header();
//                header.title="Source";
//                adapter.addHeaader(header);
//                adapter.setSourceCatModels(sourceList);
//                adapter.notifyDataSetChanged();
//                changeVisibility();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    private void changeVisibility(){
        if(recyclerView.getVisibility()!=View.VISIBLE){
            recyclerView.setVisibility(View.VISIBLE);
            progress.setVisibility(View.GONE);
        }
    }



}
