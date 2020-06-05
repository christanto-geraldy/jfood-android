package com.example.jfood_android;

/**
 * <h1>Class NetworkAdapter</h1>
 * <p>Class untuk mempermudah penggantian alamat Server</p>
 *
 * @author Geraldy Christanto
 * @version 1.0
 * @since 6 Juni 2020
 */
public class NetworkAdapter {
    private static NetworkAdapter instance;
    private static String ipAddress = "http://192.168.0.8:8090";

    private NetworkAdapter(){}

    /**
     * Method untuk mendapatkan alamat IP
     * @return ipAddress
     */
    public static String getIpAddress() {
        return ipAddress;
    }

    /**
     * Method setter untuk mengganti variable ipAddress
     * @param ipAddress
     */
    public static void setIpAddress(String ipAddress) {
        NetworkAdapter.ipAddress = ipAddress;
    }

    /**
     * Untuk membuat instance objek NetworkAdapter sendiri
     * @return instance objek
     */
    public static synchronized NetworkAdapter getInstance(){
        if(instance==null){
            instance = new NetworkAdapter();
        }
        return instance;
    }
}