package com.enjamamulhoque.gridview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<ModelUser> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.grid_view);

        GridViewAdapter gridViewAdapter = new GridViewAdapter(getApplicationContext(), 0, arrayList);

        gridView.setAdapter(gridViewAdapter);

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        firestore.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (value != null) {
                    for(DocumentChange documentChange: value.getDocumentChanges()){
                        if(documentChange.getType() == DocumentChange.Type.ADDED){

                            ModelUser modelUser = documentChange.getDocument().toObject(ModelUser.class);
                            arrayList.add(modelUser);

                            gridViewAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });


    }
}