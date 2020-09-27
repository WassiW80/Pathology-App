package com.example.pathology.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pathology.R;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GreetingFragment extends Fragment {

    private String URL = "https://fcm.googleapis.com/fcm/send";

    private NotificationManagerCompat notificationManager;
    private EditText editTextTitle;
    private EditText editTextMessage;
    private Button sendGreetingMessage;
    private RequestQueue requestQueue;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_greeting, container, false);
        notificationManager = NotificationManagerCompat.from(getActivity());
        editTextTitle = view.findViewById(R.id.edit_text_title);
        editTextMessage = view.findViewById(R.id.edit_text_message);
        sendGreetingMessage = view.findViewById(R.id.btn_send_greeting);

        requestQueue = Volley.newRequestQueue(getActivity());
        FirebaseMessaging.getInstance().subscribeToTopic("news");
        sendGreetingMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateField())
                    sendNotification();
            }
        });
        return view;
    }

    private boolean validateField() {
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();
        if (title.isEmpty()) {
            Toast.makeText(getActivity(), "Please enter title", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (message.isEmpty()) {
            Toast.makeText(getActivity(), "Please enter message", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(getActivity(), "Greeting Send", Toast.LENGTH_LONG).show();
        clearField();
    }

    private void clearField() {
        editTextTitle.getText().clear();
        editTextMessage.getText().clear();
    }
}