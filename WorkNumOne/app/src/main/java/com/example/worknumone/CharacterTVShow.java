package com.example.worknumone;

import android.media.Image;

public class CharacterTVShow
{
    public String name;
    public String description;
    public int Picture;

    public CharacterTVShow(String name, String description, int picture)
    {
        this.name = name;
        this.description = description;
        Picture = picture;
    }
    //Getters:

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPicture() {
        return Picture;
    }

}
