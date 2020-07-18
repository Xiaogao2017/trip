package com.trip.domain;

/**
 * @author Mr. Li xiaogao 2020-06-07 19:16
 */
public class Favourite {
//    private Route route;//旅游线路对象
//    private String date;//收藏时间
//    private User user;//所属用户
//
//    public Favourite() {
//    }
//
//    public Favourite(Route route, String date, User user) {
//        this.route = route;
//        this.date = date;
//        this.user = user;
//    }
//
//    public Route getRoute() {
//        return route;
//    }
//
//    public void setRoute(Route route) {
//        this.route = route;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
    private String date;
    private int rid;
    private int uid;

    public Favourite() {
    }

    public Favourite(String date, int rid, int uid) {
        this.date = date;
        this.rid = rid;
        this.uid = uid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
