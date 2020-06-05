package com.example.jfood_android;

/**
 * <h1> Class untuk Seller JFood </h1>
 * Seller merupakan penjual makanan dalam sistem JFood
 * Program ini ditujukan untuk membuat objek Seller
 *
 * @author Geraldy Christanto
 * @since 16 April 2020
 */
public class Seller {
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private Location location;

    /**
     * Constructor object dari class Seller
     * Constructor untuk mendapatkan argumen dari setiap parameter dan merujuk kepada setiap variabel di bawah
     * @param id Parameter pertama untuk merujuk kepada objek id dari Seller JFood
     * @param name Parameter kedua untuk merujuk kepada objek name dari Seller JFood
     * @param email Parameter ketiga untuk merujuk kepada objek email dari Seller JFood
     * @param phoneNumber Parameter keempat untuk merujuk kepada objek phoneNumber dari Seller JFood
     * @param location  Parameter kelima untuk merujuk kepada objek Location dari Location JFood
     */
    public Seller(int id, String name, String email, String phoneNumber, Location location)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.location = location;
    }

    /**
     * Method untuk mendapatkan id penjual makanan
     * @return id Mengembalikan nilai id pada objek Seller
     */
    public int getId(){
        return id;
    }

    /**
     * Method untuk mendapatkan nama penjual makanan
     * @return name Mengembalikan nilai name pada objek Seller
     */
    public String getName(){
        return name;
    }

    /**
     * Method untuk mendapatkan email penjual makanan
     * @return email Mengembalikan nilai email pada objek Seller
     */
    public String getEmail(){
        return email;
    }

    /**
     * Method untuk mendapatkan nomor telepon penjual makanan
     * @return phoneNumber Mengembalikan nilai phoneNumber pada objek Seller
     */
    public String getPhoneNumber(){
        return phoneNumber;
    }

    /**
     * Method untuk mendapatkan lokasi penjual makanan
     * @return location Mengembalikan nilai location dari objek Location
     */
    public Location getLocation(){
        return location;
    }

    /**
     * Method untuk mendapatkan id penjual makanan
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * Method untuk mengatur nilai nama penjual makanan
     * Merujuk kepada variabel name dari objek Seller
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Method untuk mengatur nilai email penjual makanan
     * Merujuk kepada variabel email dari objek Seller
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * Method untuk mengatur nilai phoneNumber penjual makanan
     * Merujuk kepada variabel phoneNumber dari objek Seller
     */
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    /**
     * Method untuk mengatur nilai objek location penjual makanan
     * Merujuk kepada variabel location dari objek Location
     */
    public void setLocation(Location location){
        this.location = location;
    }
}
