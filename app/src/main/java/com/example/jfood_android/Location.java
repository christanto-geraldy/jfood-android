package com.example.jfood_android;

public class Location {
    private String province;
    private String description;
    private String city;

    public Location(String city, String province, String description){
        this.city = city;
        this.province = province;
        this.description = description;
    }

    /**
     * Method untuk mendapatkan provinsi lokasi penjual makanan
     * @return province Mengembalikan nilai provinsi pada class Location
     */
    public String getProvince(){
        return province;
    }

    /**
     * Method untuk mendapatkan kota lokasi penjual makanan
     * @return city Mengembalikan nilai kota pada class Location
     */
    public String getCity(){
        return city;
    }

    /**
     * Method untuk mendapatkan deskripsi lokasi penjual makanan
     * @return description Mengembalikan nilai deskripsi pada class Location
     */
    public String getDescription(){
        return description;
    }

    /**
     * Method untuk mengatur nilai provinsi lokasi penjual makanan
     * Merujuk kepada variabel province dari class Location
     */
    public void setProvince(String province){
        this.province = province;
    }

    /**
     * Method untuk mengatur nilai kota lokasi penjual makanan
     * Merujuk kepada variabel city dari class Location
     */
    public void setCity(String city){
        this.city = city;
    }

    /**
     * Method untuk mengatur nilai deskripsi lokasi penjual makanan
     * Merujuk kepada variabel description dari class Location
     */
    public void setDescription(String description){
        this.description = description;
    }
}
