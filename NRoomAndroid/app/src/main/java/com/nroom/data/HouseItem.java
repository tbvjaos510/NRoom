package com.nroom.data;

import android.graphics.drawable.Drawable;

public class HouseItem {

    private Drawable image;
    private String 시군구코드;
    private String 시도명;
    private String 시군구명;
    private String id;
    private String 지역번호;
    private String 법정동;
    private String 집종류;
    private String 건물명;
    private String 전용면적;
    private String 층;
    private String 건축년도;
    private String 일;
    private String 지번;
    private String 거래금액;
    private String 도로명코드;
    private int 월세금액;
    private int 보증금;
    private double 위도;
    private double 경도;
    private int 대피소수;

    public HouseItem(Drawable image, String 시군구코드, String 시도명, String 시군구명, String id, String 지역번호, String 법정동, String 집종류, String 건물명, String 전용면적, String 층, String 건축년도, String 일, String 지번, String 도로명코드, int 월세금액, int 보증금, double lat, double lng, int 대피소수) {
        this.image = image;
        this.시군구코드 = 시군구코드;
        this.시도명 = 시도명;
        this.시군구명 = 시군구명;
        this.id = id;
        this.지역번호 = 지역번호;
        this.법정동 = 법정동;
        this.집종류 = 집종류;
        this.건물명 = 건물명;
        this.전용면적 = 전용면적;
        this.층 = 층;
        this.건축년도 = 건축년도;
        this.일 = 일;
        this.지번 = 지번;
        this.도로명코드 = 도로명코드;
        this.월세금액 = 월세금액;
        this.보증금 = 보증금;
        this.위도 = lat;
        this.경도 = lng;
        this.대피소수 = 대피소수;
    }

    public HouseItem(Drawable image, String 시군구코드, String 시도명, String 시군구명, String id, String 지역번호, String 법정동, String 집종류, String 건물명, String 전용면적, String 층, String 건축년도, String 일, String 지번, String 거래금액, String 도로명코드, double lat, double lng, int 대피소수) {
        this.image = image;
        this.시군구코드 = 시군구코드;
        this.시도명 = 시도명;
        this.시군구명 = 시군구명;
        this.id = id;
        this.지역번호 = 지역번호;
        this.법정동 = 법정동;
        this.집종류 = 집종류;
        this.건물명 = 건물명;
        this.전용면적 = 전용면적;
        this.층 = 층;
        this.건축년도 = 건축년도;
        this.일 = 일;
        this.지번 = 지번;
        this.거래금액 = 거래금액;
        this.도로명코드 = 도로명코드;
        this.위도 = lat;
        this.경도 = lng;
        this.대피소수 = 대피소수;
    }

    public String getId() {
        return id;
    }

    public Drawable getImage() {
        return image;
    }

    public String get시군구코드() {
        return 시군구코드;
    }

    public String get시도명() {
        return 시도명;
    }

    public String get시군구명() {
        return 시군구명;
    }

    public String get지역번호() {
        return 지역번호;
    }

    public String get법정동() {
        return 법정동;
    }

    public String get집종류() {
        return 집종류;
    }

    public String get건물명() {
        return 건물명;
    }

    public String get전용면적() {
        return 전용면적;
    }

    public String get층() {
        return 층;
    }

    public String get건축년도() {
        return 건축년도;
    }

    public String get일() {
        return 일;
    }

    public String get지번() {
        return 지번;
    }

    public String get거래금액() {
        return 거래금액;
    }

    public String get도로명코드() {
        return 도로명코드;
    }

    public int get월세금액() {
        return 월세금액;
    }

    public int get보증금() {
        return 보증금;
    }

    public double get위도() {
        return 위도;
    }

    public double get경도() {
        return 경도;
    }

    public int get대피소수() {
        return 대피소수;
    }
}
