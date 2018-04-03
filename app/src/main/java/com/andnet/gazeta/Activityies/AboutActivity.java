package com.andnet.gazeta.Activityies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.andnet.gazeta.R;


public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(view -> AboutActivity.this.onBackPressed());

        ListView aboutListView=findViewById(R.id.about_list_view);
        AboutAdapter aboutAdapter=new AboutAdapter();
        aboutListView.setAdapter(aboutAdapter);
        aboutListView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

    }

    private class AboutAdapter extends BaseAdapter{

        String[] titles={"Developers","Version"};
        String[] values={"Amanuel Solomon , Berhan Zikarge","1.113214"};

        public AboutAdapter() {
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View mainView= LayoutInflater.from(AboutActivity.this).inflate(R.layout.about_list_item_layout,null);
            TextView titleTextView=mainView.findViewById(R.id.title_textView);
            TextView descTextView=mainView.findViewById(R.id.desc_text_view);
            titleTextView.setText(titles[i]);
            descTextView.setText(values[i]);
            return mainView;
        }
    }



}
