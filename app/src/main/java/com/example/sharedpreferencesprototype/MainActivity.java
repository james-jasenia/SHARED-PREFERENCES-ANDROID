package com.example.sharedpreferencesprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Properties
    SharedPreferences sharedPreferences;

    //Life Cycle Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = this.getSharedPreferences("com.example.sharedpreferencesprototype", Context.MODE_PRIVATE);
        saveAndLoadList();
    }

    //Data Management Method Prototypes
    private void saveAndLoadString() {
        //Save
        sharedPreferences.edit().putString("username", "james").apply();

        //Load/Get
        String username = sharedPreferences.getString("username", "default");
    }

    private void saveAndLoadList() {
        //Create a list
        ArrayList<String> friends = new ArrayList<>();

        friends.add("Luke");
        friends.add("Han");
        friends.add("Leia");

        //Serialize
        try {
            String serializedList = ObjectSerializer.serialize(friends);
            sharedPreferences.edit().putString("Friends", serializedList).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Deserialize
        ArrayList<String> newFriends = new ArrayList<>();

        try {
            //Default Value: You want to pass in an empty serialized list.
            String serializedList = sharedPreferences.getString("Friends", ObjectSerializer.serialize(new ArrayList<String>()));
            newFriends = (ArrayList<String>) ObjectSerializer.deserialize(serializedList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("NewFriends", newFriends.toString());
    }
}