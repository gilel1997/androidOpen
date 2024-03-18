package com.example.worknumtow;

import java.io.Serializable;

public class Item implements Serializable
{
    public String itemName;
    public int itemNumberOf;

    public Item(String itemName, int itemNumberOf)
    {
        this.itemName = itemName;
        this.itemNumberOf = itemNumberOf;
    }

    public String getItemName()
    {
        return itemName;
    }

    public int getItemNumberOf()
    {
        return itemNumberOf;
    }

    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }

    public void setItemNumberOf(int itemNumberOf)
    {
        this.itemNumberOf = itemNumberOf;
    }
}
