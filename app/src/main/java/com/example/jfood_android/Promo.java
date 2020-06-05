package com.example.jfood_android;

/**
 * <h1>Class Promo</h1>
 * <p>Class untuk membuat dan memodifikasi objek Promo</p>
 * @author Geraldy Christanto
 * @since 16 April 2020
 */
public class Promo {
    private int id;
    private String code;
    private int discount;
    private int minPrice;
    private boolean active;

    /**
     * Constructor untuk objek class Promo
     * @param id Parameter ID untuk class promo
     * @param code Parameter Code untuk class promo
     * @param discount Parameter discount untuk class promo
     * @param minPrice Parameter harga minimum dari class promo
     * @param active Parameter untuk status keaktifan dari class promo
     */
    public Promo(int id, String code, int discount, int minPrice, boolean active){
        this.id = id;
        this.code = code;
        this.discount = discount;
        this.minPrice = minPrice;
        this.active = active;
    }

    /**
     * MEthod untuk mendapatkan id dari objek Promo
     * @return id Nilai id dari objek promo
     */
    public int getId(){
        return id;
    }

    /**
     * Method untuk mendapatkan code dari objek Promo
     * @return code Nilai variabel code dari objek promo
     */
    public String getCode(){
        return code;
    }

    /**
     * Method untuk mendapatkan discount dari objek promo
     * @return discount Nilai variabel discount dari objek promo
     */
    public int getDiscount(){
        return discount;
    }

    /**
     * Method untuk mendapatkan harga minimum dari objek promo
     * @return minPrice Nilai minPrice dari objek promo
     */
    public int getMinPrice(){
        return minPrice;
    }

    /**
     * Method untuk mendapatkan status dari objek Promo
     * @return true jika promo dalam keadaan aktif, false jika tidak aktif
     */
    public boolean getActive(){
        return active;
    }

    /**
     * Method untuk mengatur nilai id pada constructor Promo
     * @param id Id yang akan dimasukkan dalam objek Promo
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * Method untuk mengatur code pada objek Promo
     * @param code Code yang akan dimasukkan dalam objek promo
     */
    public void setCode(String code){
        this.code = code;
    }

    /**
     * Method untuk mengatur discount pada objek Promo
     * @param discount Discount yang akan dimasukkan dalam objek Promo
     */
    public void setDiscount(int discount){
        this.discount = discount;
    }

    /**
     * Method untuk mengatur nilai minPrice pada objek Promo
     * @param minPrice minPrice yang akan dimasukkan dalam objek Promo
     */
    public void setMinPrice(int minPrice){
        this.minPrice = minPrice;
    }

    /**
     * Method untuk mengatur status promo
     * @param active true jika promo aktif, jika tidak bernilai false
     */
    public void setActive(boolean active){
        this.active = active;
    }

}