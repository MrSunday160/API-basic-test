package com.example.jsonplaceholderapitest;

import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity{

    // hold the textView from layouts

    private LinearLayout postContainer;

//    // setup seperate text view for each variable from the JSON response
//    private TextView appTitle;
//    private TextView userIdTextView;
//    private TextView idTextView;
//    private TextView titleTextView;
//    private TextView bodyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        appTitle = findViewById(R.id.appTitleTextView);
//        userIdTextView = findViewById(R.id.userIdTextView);
//        idTextView = findViewById(R.id.idTextView);
//        titleTextView = findViewById(R.id.titleTextView);
//        bodyTextView = findViewById(R.id.bodyTextView);

        postContainer = findViewById(R.id.postContainer);

        // make API request in the background
        new ApiTask().execute();

    }

    private class ApiTask extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... voids){

            return ApiService.getPost();

        }

        @Override
        protected void onPostExecute(String result){

            if(result != null){

                try{

                    // Parse the JSON response
                    JSONArray jsonArray = new JSONArray(result);

                    for(int i = 0; i < jsonArray.length(); i++){

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        // Extract values
                        int userId = jsonObject.getInt("userId");
                        int id = jsonObject.getInt("id");
                        String title = jsonObject.getString("title");
                        String body = jsonObject.getString("body");

                        // Display values in TextViews
//                        userIdTextView.setText("User ID: " + userId);
//                        idTextView.setText("ID: " + id);
//                        titleTextView.setText("Title: " + title);
//                        bodyTextView.setText("Body: " + body);

                        // new textview to display the post
                        TextView postTextView = new TextView(MainActivity.this);
                        postTextView.setText("User ID: " + userId + "\nID: " + id + "\nTitle: " + title + "\nBody: " + body);
                        postTextView.setPadding(0, 0, 0, 16); // for spacing

                        postContainer.addView(postTextView);

                        Log.d("MainActivity", "JSON Response: " + result);

                        Log.d("MainActivity", "User ID: " + userId);
                        Log.d("MainActivity", "ID: " + id);
                        Log.d("MainActivity", "Title: " + title);
                        Log.d("MainActivity", "Body: " + body);

                    }

                }

                catch(JSONException e){

                    e.printStackTrace();
                    // error handler here

                }

            }

            else
                Log.d("Error on else bit", "Error, check again pls");

        }

    }

}