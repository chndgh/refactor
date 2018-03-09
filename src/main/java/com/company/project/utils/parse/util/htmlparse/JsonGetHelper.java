package com.company.project.utils.parse.util.htmlparse;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonGetHelper {


    public String getAsString(JsonObject obj, String name) {
        JsonElement element = obj.get(name);
        if (element == null) return null;
        try {
            return element.getAsString();
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return null;
    }

    public int getAsInt(JsonObject obj, String name) {
        JsonElement element = obj.get(name);
        if (element == null) return -1;

        try {
            return element.getAsInt();
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return -1;
    }

    public long getAsLong(JsonObject obj, String name) {
        JsonElement element = obj.get(name);
        if (element == null) return -1;

        try {
            return element.getAsLong();
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return -1;
    }

}
