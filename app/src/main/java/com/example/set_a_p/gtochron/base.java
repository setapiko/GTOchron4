package com.example.set_a_p.gtochron;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class base extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        ListView listView = (ListView) findViewById(R.id.listView);

        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
        Realm realm = Realm.getInstance(realmConfig);


        ArrayList<HashMap<String, Object>> mCatList = new ArrayList<>();
        HashMap<String, Object> hm;

        RealmResults<TimeBase> results = realm.where(TimeBase.class).findAll();
        for (int i = 0; i < results.size(); i++) {
            TimeBase u = results.get(i);
            hm = new HashMap<>();
            hm.put("NF", u.getName()+" "+u.getFamily());
            if (u.getGender())
            hm.put("gt", "Пол: Мужской"+" Время:"+u.getTime());
            else
            hm.put("gt", "Пол: Женский"+" Время:"+u.getTime());
            mCatList.add(hm);
        }


        SimpleAdapter adapter = new SimpleAdapter(this, mCatList,
                R.layout.list_item, new String[]{"NF", "gt", },
                new int[]{R.id.text1, R.id.text2});


        assert listView != null;
        listView.setAdapter(adapter);
    }
}
