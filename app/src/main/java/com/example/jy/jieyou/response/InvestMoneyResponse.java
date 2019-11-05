package com.example.jy.jieyou.response;

import java.util.ArrayList;
import java.util.List;

public class InvestMoneyResponse {

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

        private String money;
        private String message;
        private boolean click = false;

        public boolean isClick() {
            return click;
        }

        public void setClick(boolean click) {
            this.click = click;
        }

        public String getMoney() {
            return money == null ? "" : money;
        }

        public void setMoney(String money) {
            this.money = money == null ? "" : money;
        }

        public String getMessage() {
            return message == null ? "" : message;
        }

        public void setMessage(String message) {
            this.message = message == null ? "" : message;
        }
    }
}
