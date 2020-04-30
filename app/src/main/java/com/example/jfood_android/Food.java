package com.example.jfood_android;

public class Food {
    private int id;
    private String name;
    private int price;
    private String category;
    private Seller seller;

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
     * Method ini bertujuan untuk mendapatkan id dari class Food
     * @return id untuk mengembalikan nilai instance variable id
     */
    public int getId(){
        return id;
    }

    /**
     * Method ini bertujuan untuk mendapatkan nilai seller dari class Seller
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
     * Method ini b
     * @param id Parameter nilai Id food yang akan di atur
     */
    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setSeller(){
        this.seller = seller;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public void setCategory(String category){
        this.category = category;
    }

    /**
     * Method untuk mencetak variabel nama dari objek Food
     */
    public String toString(){
        return "\nId = " + id +
                "\nName = " + name +
                "\nSeller = " + getSeller().getName().toString() +
                "\nCity =" + getSeller().getLocation().getCity().toString() +
                "\nPrice = " + price +
                "\nCategory = " + getCategory().toString();
    }

}
