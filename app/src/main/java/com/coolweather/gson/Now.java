package com.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 2018/4/28.
 */

public class Now {
    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public More more;
    public class More{
        @SerializedName("txt")
        public String info;
    }
}
