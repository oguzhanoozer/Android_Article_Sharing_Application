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
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import static com.parse.Parse.getApplicationContext;

public class FragmentRecent extends Fragment {


    ListView listView;

    static PostClass postClass;
    static ArrayList<String> titleFromParse;
    static ArrayList<String> articleFromParse;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.most_recent,container,false);

        listView = (ListView) view.findViewById(R.id.listview);
        titleFromParse = new ArrayList<>();
        articleFromParse = new ArrayList<String>();

        postClass = new PostClass(titleFromParse,articleFromParse,getActivity());

        listView.setAdapter(postClass);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =  new Intent(getApplicationContext(),Reading.class);
                intent.putExtra("title",titleFromParse.get(position));
                startActivity(intent);
            }
        });

        download();

        return view;
    }

    public void download() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("posts");
        query.addDescendingOrder("views_count");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override

            public void done(List<ParseObject> objects, ParseException e) {

                if(objects.size()>0){

                    for(ParseObject object : objects){

                        String title = object.getString("post_title");
                        String article = object.getString("post_article");

                        titleFromParse.add(title);
                        articleFromParse.add(article);

                        postClass.notifyDataSetChanged();

                    }

                }
            }
        });
    }


}


