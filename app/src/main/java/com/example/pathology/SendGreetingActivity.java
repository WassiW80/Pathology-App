package com.example.pathology;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SendGreetingActivity extends AppCompatActivity {

    private String URL = "https://fcm.googleapis.com/fcm/send";

    private NotificationManagerCompat notificationManager;
    private EditText editTextTitle;
    private EditText editTextMessage;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_greeting);

        notificationManager = NotificationManagerCompat.from(this);
        editTextTitle = findViewById(R.id.edit_text_title);
        editTextMessage = findViewById(R.id.edit_text_message);

        requestQueue = Volley.newRequestQueue(this);
        FirebaseMessaging.getInstance().subscribeToTopic("news");

    }

    public void sendMessage(View view) {
        if (validateField())
        sendNotification();
    }

    private boolean validateField() {
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();
        if (title.isEmpty()) {
            Toast.makeText(this, "Please enter title", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (message.isEmpty()) {
            Toast.makeText(this, "Please enter message", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void sendNotification() {
        JSONObject mainObj = new JSONObject();
        try {
            mainObj.put("to", "/topics/" + "news");
            JSONObject notification = new JSONObject();
            notification.put("title", editTextTitle.getText().toString().trim());
            notification.put("body", editTextMessage.getText().toString().trim());
            mainObj.put("notification", notification);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL, mainObj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> header = new HashMap<String, String>();
                    header.put("content-type", "application/json");
                    header.put("authorization", "key=AAAAVbFyNh0:APA91bGG1PQZSoBnvSxUezkqovUyi5ulrW7fK4xJwCSyHJI3zbiFpmHwt9acpi1ERt8sgAt7m0UAqeXlGM6iE5ZJKQZytKNNIV-N8Qxx8gV7ZfTgbNbuTxlC8GopPSK5Ev95FliEmubJ");
                    return header;
                }
            };
            requestQueue.add(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(SendGreetingActivity.this, "Greeting Send", Toast.LENGTH_LONG).show();
        clearField();
    }

    private void clearField() {
        editTextTitle.getText().clear();
        editTextMessage.getText().clear();
    }
}