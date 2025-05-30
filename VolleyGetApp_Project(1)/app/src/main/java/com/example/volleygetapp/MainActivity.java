package com.example.volleygetapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    TextView textViewTitle, textViewBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewTitle = findViewById(R.id.textTitle);
        textViewBody = findViewById(R.id.textBody);

        fetchPost();
    }

    private void fetchPost() {
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
            response -> {
                try {
                    String title = response.getString("title");
                    String body = response.getString("body");

                    textViewTitle.setText("Title: " + title);
                    textViewBody.setText("Body: " + body);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            },
            error -> {
                textViewTitle.setText("Error!");
                textViewBody.setText(error.toString());
            });

        queue.add(request);
    }
}