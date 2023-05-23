package com.example.task.data;

public class Profile {
    private String name, hobbies;
    private int imageResourceID;

    public int getImageResourceID() {
        return imageResourceID;
    }

    public Profile(int imageResourceID, String name, String hobbies) {
        this.imageResourceID = imageResourceID;
        this.name = name;
        this.hobbies = hobbies;
    }


    public String getName() {
        return name;
    }

    public String getHobbies() {
        return hobbies;
    }
}
