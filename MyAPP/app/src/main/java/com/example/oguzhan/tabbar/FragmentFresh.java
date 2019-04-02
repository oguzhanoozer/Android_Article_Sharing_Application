package com.example.oguzhan.tabbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.parse.Parse.getApplicationContext;

public class FragmentFresh extends Fragment {

    ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fresh,container,false);

        listView  = (ListView) view.findViewById(R.id.listView);

        final ArrayList<String> categorise  = new ArrayList<String>();
        categorise.add("Health");
        categorise.add("Technology");
        categorise.add("Economy");
        categorise.add("Education");
        categorise.add("Art");
        categorise.add("History");
        categorise.add("Social");
        categorise.add("Science");
        categorise.add( "Current News");
        categorise.add("Other Category");

        ArrayAdapter arrayAdapter =  new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,categorise);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =  new Intent(getApplicationContext(),CategorizeReading.class);
                intent.putExtra("categorize",categorise.get(position));
                startActivity(intent);
            }
        });

        return view;
    }
}
