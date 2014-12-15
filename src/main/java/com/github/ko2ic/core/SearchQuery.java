package com.github.ko2ic.core;

import java.util.Map;

public class SearchQuery {

    // private JSONObject object;

    private String startTime;

    private String endTime;

    private int numRows;

    private Map<String, Object> object;

    // public JSONObject getObject() {
    // return object;
    // }
    //
    // public void setObject(JSONObject object) {
    // this.object = object;
    // }

    public Map<String, Object> getObject() {
        return object;
    }

    public void setObject(Map<String, Object> object) {
        this.object = object;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }
}
