package com.example.pathology.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.example.pathology.R;
import com.example.pathology.helperclass.Uploads;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ViewFragment extends Fragment {

    ListView myPDFFileView;
    SearchView searchFile;
    DatabaseReference databaseReference;

    List<Uploads> uploadPDFFile;
    ArrayAdapter<String> adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_report, container, false);

        myPDFFileView = (ListView) view.findViewById(R.id.listView);
        searchFile = (SearchView) view.findViewById(R.id.search_filter);

        uploadPDFFile = new ArrayList();
        Collections.reverse(uploadPDFFile);

        viewAllFiles();

        myPDFFileView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Uploads pdfFile = uploadPDFFile.get(i);

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(pdfFile.getUrl()));
                startActivity(intent);
            }
        });

        searchFile.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ViewFragment.this.adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ViewFragment.this.adapter.getFilter().filter(newText);
                return false;
            }
        });
        return view;
    }

    private void viewAllFiles() {
        databaseReference = FirebaseDatabase.getInstance().getReference("File");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshots : snapshot.getChildren()) {
                    Uploads pdfFile = snapshots.getValue(Uploads.class);
                    uploadPDFFile.add(0, pdfFile);

                }

                String[] uploads = new String[uploadPDFFile.size()];

                for (int i = 0; i < uploads.length; i++) {
                    uploads[i] = uploadPDFFile.get(i).getName();

                }

                adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, uploads) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        TextView myText = view.findViewById(android.R.id.text1);
                        myText.setTextColor(Color.BLACK);

                        return view;
                    }
                };
                myPDFFileView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}