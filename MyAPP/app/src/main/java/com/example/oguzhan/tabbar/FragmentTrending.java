package com.example.oguzhan.tabbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import static com.parse.Parse.getApplicationContext;

public class FragmentTrending extends Fragment {

    EditText add_title,add_article;

    Button button;
    Spinner dropdown;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_trending,container,false);

        add_title = (EditText) view.findViewById(R.id.add_title);
        add_article = (EditText) view.findViewById(R.id.add_article);
        button = (Button) view.findViewById(R.id.button);

         dropdown= view.findViewById(R.id.spinner1);
         //create a list of items for the spinner.
         String[] items = new String[]{
                "Education",
                "Health",
                "Technology",
                "Art",
                "History",
                "Social",
                "Science",
                "Current News",
                "Other Category"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);

        dropdown.setAdapter(adapter);



        button.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                String title = add_title.getText().toString();
                String article=add_article.getText().toString();
                String categorize=String.valueOf(dropdown.getSelectedItem());


                if(title.equalsIgnoreCase("") || article.equalsIgnoreCase("") || categorize.equalsIgnoreCase("")){
                    Toast.makeText(getActivity() ,"Not make Empty ! " ,Toast.LENGTH_LONG).show();
                }else{
                    ParseObject parseObject = new ParseObject("posts");

                    parseObject.put("post_title",title);
                    parseObject.put("post_article",article);
                    parseObject.put("categorize",categorize);

                    parseObject.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e!=null){
                                Toast.makeText(getActivity() ,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                            }else{
                                add_title.setText("");
                                add_article.setText("");
                                dropdown.setTop(0);

                                Toast.makeText(getActivity() ,"Saved Post ! " ,Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }



            }
        });


        return view;
    }

}
