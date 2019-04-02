package com.example.oguzhan.tabbar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static com.parse.Parse.getApplicationContext;

public class FragmentHot extends Fragment{

    ListView listView;

    static PostClass postClass;
    static ArrayList<String> titleFromParse;
    static ArrayList<String> articleFromParse;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hot,container,false);

        listView = (ListView) view.findViewById(R.id.listView);
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
        query.addDescendingOrder("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override

            public void done(List<ParseObject> objects, ParseException e) {

                if(objects.size()>0){

                    for(ParseObject object : objects){

                        String title = object.getString("post_title");
                        String article = object.getString("post_article");

                        System.out.println("title :" + title);
                        System.out.println("article :" + article);

                        titleFromParse.add(title);
                        articleFromParse.add(article);

                        postClass.notifyDataSetChanged();

                    }

                }
            }
        });
    }


}
