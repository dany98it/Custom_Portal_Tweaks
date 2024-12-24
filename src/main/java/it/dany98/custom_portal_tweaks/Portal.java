package it.dany98.custom_portal_tweaks;

import com.google.gson.Gson;

public class Portal {
    public  String frameBlock;
    public String itemIgniter;
    public String dimension;

    public Portal(String frameBlock, String itemIgniter, String dimension) {
        this.frameBlock = frameBlock;
        this.itemIgniter = itemIgniter;
        this.dimension = dimension;
    }

    public String getFrameBlock() {
        return frameBlock;
    }

    public void setFrameBlock(String frameBlock) {
        this.frameBlock = frameBlock;
    }

    public String getItemIgniter() {
        return itemIgniter;
    }

    public void setItemIgniter(String itemIgniter) {
        this.itemIgniter = itemIgniter;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }


    public String toJsonSting() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Portal fromJsonString(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Portal.class);
    }
}
