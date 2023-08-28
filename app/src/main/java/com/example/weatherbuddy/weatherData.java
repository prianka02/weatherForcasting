package com.example.weatherbuddy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class weatherData {

    private String mTemperature, mcity, mWeatherType;
    private int mCondition;

    public static weatherData fromJson(JSONObject jsonObject)
    {
        // fetching data from weather data url
        try {
           weatherData weatherD = new weatherData();
           weatherD.mcity = jsonObject.getString("name");
           weatherD.mCondition = jsonObject.getJSONArray("weather").getJSONObject(0).getInt("id");
           weatherD.mWeatherType=jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
           double tempResult = jsonObject.getJSONObject("main").getDouble("temp")-273.15;
           int roundedValue = (int)Math.rint(tempResult);
           weatherD.mTemperature = Integer.toString(roundedValue);
           return weatherD;
        }catch (JSONException e){
            e.printStackTrace();
            return null;

        }
    }

    public String getmTemperature() {
        return mTemperature+"°C";
    }

    public String getMcity() {
        return mcity;
    }

    public String getmWeatherType() {
        return mWeatherType;
    }
}
