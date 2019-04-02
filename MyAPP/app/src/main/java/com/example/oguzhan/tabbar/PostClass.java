package com.example.oguzhan.tabbar;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PostClass extends ArrayAdapter<String> {

    private final ArrayList<String> post_title;
    private final ArrayList<String> post_article;
    private final Activity context;


    public PostClass(ArrayList<String> post_title, ArrayList<String> post_article, Activity context) {
        super(context,R.layout.custom_view,post_title);
        this.post_title = post_title;
        this.post_article = post_article;
        this.context = context;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View customView = layoutInflater.inflate(R.layout.custom_view,null,true);

        TextView post_titleS = customView.findViewById(R.id.title);
      //  TextView post_articleS = customView.findViewById(R.id.article);

        post_titleS.setText(post_title.get(position));
   //     post_articleS.setText(post_article.get(position));

        return customView;
    }
}