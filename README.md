# Shared Preferences Prototype - Android
##### Tutorial: The Complete Android Oreo Developer Course - Section 7

## Purpose: 
The purpose of this app is to explore the used of Share Preferences to store small pieces of data.


## Key Classes:
### SharedPreferences
SharedPreferences is Android's version of UserDefaults (iOS). It is not designed for handling large pieces of data as writing and reading to this object are expensive operations that could slow down the app. SharedPreferences uses key:value storage. One of the noticable differences between SharedPreferences and UserDefaults is the use of a default value when retrieve data from SharePrefernces. 

#####Putting and Getting a String
```
    SharedPreferences sharedPreferences;

    private void saveAndLoadString() {
        //Put
        sharedPreferences.edit().putString("username", "james").apply();

        //Get
        String username = sharedPreferences.getString("username", "default");
    }
```


#####Putting and Getting an ArrayList
```
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
```



