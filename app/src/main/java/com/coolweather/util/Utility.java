package com.coolweather.util;

import android.text.TextUtils;

import com.coolweather.db.City;
import com.coolweather.db.County;
import com.coolweather.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by user on 2018/4/28.
 */

public class Utility {
    /*
    *  解析服务器返回的省级数据
    * */
    public  static  boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allprovinces= new JSONArray(response);
                for (int i=0; i<allprovinces.length();i++){
                    JSONObject provinceObj=allprovinces.getJSONObject(i);
                    Province province=new Province();
                    province.setProvinceName(provinceObj.getString("name"));
                    province.setProvinceCode(provinceObj.getInt("id"));
                    province.save();
                }
                return  true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    /*
    * 解析出来市级数据
    * */
    public  static  boolean handleCityResponse(String response,int provinceId){
       if(!TextUtils.isEmpty(response)){
           try {
               JSONArray citys=new JSONArray(response);
               for (int i=0; i<citys.length();i++){
                   JSONObject cityObj= citys.getJSONObject(i);
                   City city =new City();
                   city.setCityName(cityObj.getString("name"));
                   city.setCityCode(cityObj.getInt("id"));
                   city.setProvinceId(provinceId);
                   city.save();

               }
               return  true;
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
        return  false;
    }
    public  static  boolean handleCountyResponse(String response,int cityId){
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray counties= new JSONArray(response);
                for (int i=0; i<counties.length();i++){
                    JSONObject countyObj=counties.getJSONObject(i);
                    County county=new County();
                    county.setCountyName(countyObj.getString("name"));
                    county.setWeatherId(countyObj.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return  true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
      return  false;

    }
}
