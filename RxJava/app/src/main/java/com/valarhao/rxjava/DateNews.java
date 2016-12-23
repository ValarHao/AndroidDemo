package com.valarhao.rxjava;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于RetrofitActivity中知乎日报API解析json时的类
 */

public class DateNews {

    private String date;
    private List<NewsItem> stories = new ArrayList<>();

    public String getDate() {
        return date;
    }

    public List<NewsItem> getStories() {
        return stories;
    }

    public static class NewsItem {

        //private String images;
        private int type;
        private int id;
        private int ga_prefix;
        private String title;

        /*public String getImages() {
            return images;
        }*/

        public int getType() {
            return type;
        }

        public int getId() {
            return id;
        }

        public int getGa() {
            return ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public String toString() {
            return "NewsItem [type=" + type + ", id=" + id
                    + ", ga_prefix=" + ga_prefix + ", title=" + title + "]";
        }
    }
}
