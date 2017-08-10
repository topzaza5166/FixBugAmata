package com.example.topza.fixbugamata.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by teepop.r on 8/9/2017.
 */

public class NewsDao {

    @SerializedName("response")
    @Expose
    private Integer response;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;

    public Integer getResponse() {
        return response;
    }

    public void setResponse(Integer response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("newsUpdates")
        @Expose
        private List<NewsUpdate> newsUpdates = null;

        public List<NewsUpdate> getNewsUpdates() {
            return newsUpdates;
        }

        public void setNewsUpdates(List<NewsUpdate> newsUpdates) {
            this.newsUpdates = newsUpdates;
        }

    }

    public static class NewsUpdate implements Parcelable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("created_at")
        @Expose
        private Time createdAt;
        @SerializedName("updated_at")
        @Expose
        private Time updatedAt;

        protected NewsUpdate(Parcel in) {
            image = in.readString();
            title = in.readString();
            description = in.readString();
        }

        public static final Creator<NewsUpdate> CREATOR = new Creator<NewsUpdate>() {
            @Override
            public NewsUpdate createFromParcel(Parcel in) {
                return new NewsUpdate(in);
            }

            @Override
            public NewsUpdate[] newArray(int size) {
                return new NewsUpdate[size];
            }
        };

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(image);
            dest.writeString(title);
            dest.writeString(description);
        }
    }

    public class Time {

        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("timezone_type")
        @Expose
        private Integer timezoneType;
        @SerializedName("timezone")
        @Expose
        private String timezone;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Integer getTimezoneType() {
            return timezoneType;
        }

        public void setTimezoneType(Integer timezoneType) {
            this.timezoneType = timezoneType;
        }

        public String getTimezone() {
            return timezone;
        }

        public void setTimezone(String timezone) {
            this.timezone = timezone;
        }

    }

}
