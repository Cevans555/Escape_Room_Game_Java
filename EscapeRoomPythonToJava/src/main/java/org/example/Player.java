package org.example;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private List<String> items = new ArrayList<>();
    private String location = "wall1";

    public Player(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printItems() {
        for (String item: items){
            System.out.println(item + " ");
        }
    }

    public void addItems(String item) {
        this.items.add(item);
    }

    public void removeItems(String removeItem){

        this.items.remove(removeItem);

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public boolean checkItems(String checkItem){
        for (String item: items){
            if (checkItem.equalsIgnoreCase(item)){
                return true;
            }
        }
        return false;
    }
}
