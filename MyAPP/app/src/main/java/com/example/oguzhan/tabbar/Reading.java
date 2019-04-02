package com.example.oguzhan.tabbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

public class Reading extends AppCompatActivity {

    TextView titleText;
    TextView articleText;

    int viewerCount;
    String title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);

        titleText = (TextView) findViewById(R.id.titleText);
        articleText = (TextView) findViewById(R.id.articleText);

        articleText.setMovementMethod(new ScrollingMovementMethod());

        Intent intent = getIntent();

        title = intent.getStringExtra("title");

        getArticle(title);

        titleText.setText(title);

    }
    public void   getArticle(String title){

        ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("posts");
        parseQuery.whereEqualTo("post_title",title);
        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if(objects.size()>0){

                    for(ParseObject object : objects){

                        String article = object.getString("post_article");
                        int count = object.getInt("views_count");

                        viewerCount = count;
                        articleText.setText(article+" - "+" - Viewer Count : "+viewerCount);

                        updateViewerCount();
                    }

                }

            }
        });



    }

        public  void  updateViewerCount(){

            int number = 1;
            viewerCount = viewerCount + number;


            ParseQuery<ParseObject> query = ParseQuery.getQuery("posts");
            query.whereEqualTo("post_title",title);

            query.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> objects, ParseException eg) {
                    if (eg == null && objects.size() > 0) {
                        ParseObject Grillen = objects.get(0);
                        Grillen.put("views_count", viewerCount);
                        Grillen.saveInBackground(new SaveCallback() {
                            public void done(ParseException e) {
                                if (e == null) {
                                    //success, saved!
                                    Log.d("MyApp", "Successfully saved!");
                                 } else {
                                    //fail to save!
                                    e.printStackTrace();
                                }
                            }
                        });
                    }else {
                        //fail to get!!
                        eg.printStackTrace();
                    }
                }
            });


        }


}
