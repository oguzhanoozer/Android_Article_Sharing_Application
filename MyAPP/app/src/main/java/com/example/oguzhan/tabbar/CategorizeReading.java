package com.example.oguzhan.tabbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import static com.parse.Parse.getApplicationContext;

public class CategorizeReading extends AppCompatActivity {

    ListView listView;
    PostClass postClass;
    static ArrayList<String> titleFromParse;
    static ArrayList<String> articleFromParse;
    String categorize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorize_reading);

        Intent intent = getIntent();

        categorize = intent.getStringExtra("categorize");

        listView = (ListView) findViewById(R.id.listView);
        titleFromParse = new ArrayList<>();
        articleFromParse = new ArrayList<String>();

        postClass = new PostClass(titleFromParse,articleFromParse,this);

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
    }

    public void download() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("posts");
        query.whereEqualTo("categorize",categorize);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override

            public void done(List<ParseObject> objects, ParseException e) {

                if(objects.size()>0){

                    for(ParseObject object : objects){

                        String title = object.getString("post_title");
                        String article = object.getString("post_article");

                        System.out.println("catx :" + title);
                        System.out.println("catear :" + article);

                        titleFromParse.add(title);
                        articleFromParse.add(article);

                        postClass.notifyDataSetChanged();

                    }

                }
            }
        });
    }

}


