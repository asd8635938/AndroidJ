package com.example.jy.jieyou.response;

import java.util.ArrayList;
import java.util.List;

public class ThreeListResponse {

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

    public static class Data {

        private String string1;
        private String string2;
        private int image;

        public String getString1() {
            return string1 == null ? "" : string1;
        }

        public void setString1(String string1) {
            this.string1 = string1 == null ? "" : string1;
        }

        public String getString2() {
            return string2 == null ? "" : string2;
        }

        public void setString2(String string2) {
            this.string2 = string2 == null ? "" : string2;
        }

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }
    }
}
