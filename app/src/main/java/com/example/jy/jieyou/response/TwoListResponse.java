package com.example.jy.jieyou.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TwoListResponse implements Serializable{

    public List<Data> data;

    public List<Data> getData() {
        if (data == null) {
            return new ArrayList<>();
        }
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data implements Serializable {

        private String title;
        private List<Content> content;

        public String getTitle() {
            return title == null ? "" : title;
        }

        public void setTitle(String title) {
            this.title = title == null ? "" : title;
        }

        public List<Content> getContent() {
            if (content == null) {
                return new ArrayList<>();
            }
            return content;
        }

        public void setContent(List<Content> content) {
            this.content = content;
        }

        public static class Content implements Serializable {
            private String text;
            private String title;

            public String getTitle() {
                return title == null ? "" : title;
            }

            public void setTitle(String title) {
                this.title = title == null ? "" : title;
            }

            public String getText() {
                return text == null ? "" : text;
            }

            public void setText(String text) {
                this.text = text == null ? "" : text;
            }
        }
    }
}
