package com.example.jfood_android;

/**
 * <h1>Class Food</h1>
 * <p>Class untuk membuat objek food dan memodifikasi objek food</p>
 * @author Geraldy Christanto
 * @since 16 April 2020
 */
public class Food{

    private int id;
    private String name;
    private int price;
    private Seller seller;
    private String category;

    /**
     * Constructor untuk objek dari class Food
     * @param id Parameter pertama untuk constructor merujuk kepada instance variable id
     * @param name Parameter kedua untuk constructor merujuk kepada instance vaiable name
     * @param seller Parameter ketiga untuk constructor merujuk kepada instance variable seller
     * @param price Parameter keempat untuk constructor merujuk kepada instance variable price
     * @param category Parameter kelima untuk constructor merujuk kepada instance variable category
     */
    public Food(int id, String name, Seller seller, int price, String category){
        this.id = id;
        this.name = name;
        this.seller = seller;
        this.price = price;
        this.category = category;
    }

    /**
     * Method ini bertujuan untuk mendapatkan id dari objek Food
     * @return id untuk mengembalikan nilai instance variable id
     */
    public int getId(){
        return id;
    }

    /**
     * Method ini bertujuan untuk mendapatkan nilai seller dari objek Seller
     * dengan memanggil instance dari object yang dibuat
     * @return seller untuk mengembalikan nilai instance variable seller dari class Seller
     */
    public Seller getSeller(){
        return seller;
    }

    /**
     * Method ini bertujuan untuk mendapatkan nilai name dari class Food
     * @return name untuk mengembalikan nilai instance variable name
     */
    public String getName(){
        return name;
    }

    /**
     * Method ini bertujuan untuk mendapatkan nilai price dari class Food
     * @return price untuk mengembalikan nilai instance variable price
     */
    public int getPrice(){
        return price;
    }

    /**
     * Method ini bertujuan untuk mendapatkan nilai category dari class Food
     * @return category untuk mengembalikan nilai instance variable category
     */
    public String getCategory(){
        return category;
    }

    /**
     * Method ini bertujuan untuk mengatur nilai dari id objek Food
     * @param id Parameter nilai Id food yang akan di atur
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * Method ini bertujuan untuk mengatur nama dari objek food pada constructor
     * @param name Nama food yang akan diatur pada objek food
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Method ini bertujuan untuk mengatur objek seller dari objek food pada constructor
     */
    public void setSeller(){
        this.seller = seller;
    }

    /**
     * Method ini bertujuan untuk mengatur variable price dari objek food pada constructor
     * @param price Harga makanan yang akan diatur pada objek food
     */
    public void setPrice(int price){
        this.price = price;
    }

    /**
     * Method ini bertujuan untuk mengatur enum Category dari objek food pada constructor
     * @param category Kategori yang akan diatur pada saat membuat objek food
     */
    public void setCategory(String category){
        this.category = category;
    }

    /**
     * Method untuk menampilkan informasi objek Food pada terminal
     *@return informasi objek food
     */
    @Override
    public String toString(){
        return "\nId = " + id +
                "\nName = " + name +
                "\nSeller = " + getSeller().getName() +
                "\nCity =" + getSeller().getLocation().getCity() +
                "\nPrice = " + price +
                "\nCategory = " + getCategory();
    }
}
