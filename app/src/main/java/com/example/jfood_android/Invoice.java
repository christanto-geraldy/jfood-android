package com.example.jfood_android;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * <h1> Class untuk Invoice </h1>
 * Invoice merupakan tagihan dari sistem JFood
 * Program ini ditujukan untuk membuat invoice transaksi sistem JFood
 *
 * @author Geraldy Christanto
 * @since 4 Juni 2020
 */
public class Invoice {

    private int id;
    private ArrayList<String> foods;
    protected int totalPrice;
    private String date;
    private String paymentType;
    private String invoiceStatus;
    private String promoCode;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");

    /**
     * Constructor untuk objek dari class CashInvoice
     * Constructor untuk mendapatkan argumen dari setiap parameter dan merujuk kepada setiap variabel di bawah
     * @param id Parameter pertama untuk mendapatkan id invoice
     * @param foods Parameter untuk mendapatkan objek food
     */
    public Invoice(int id, String date, ArrayList<String> foods, int totalPrice, String paymentType, String invoiceStatus){
        this.id = id;
        this.foods = foods;
        this.date = date;
        this.totalPrice = totalPrice;
        this.paymentType = paymentType;
        this.invoiceStatus = invoiceStatus;
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MONTH, 1);
    }

    /**
     * Constructor untuk objek dari class CashlessInvoice
     * Constructor untuk mendapatkan argumen dari setiap parameter dan merujuk kepada setiap variabel di bawah
     * @param id Parameter pertama untuk mendapatkan id invoice
     * @param foods Parameter untuk mendapatkan objek food
     */
    public Invoice(int id, String date, ArrayList<String> foods, int totalPrice, String paymentType, String invoiceStatus, String promoCode){
        this.id = id;
        this.foods = foods;
        this.date = date;
        this.totalPrice = totalPrice;
        this.paymentType = paymentType;
        this.invoiceStatus = invoiceStatus;
        this.promoCode = promoCode;
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MONTH, 1);
    }

    /**
     * Method untuk mendapatkan id invoice transaksi
     * @return id Mengembalikan nilai id pada class Invoice
     */
    public int getId(){
        return id;
    }

    /**
     * Method untuk mendapatkan idFood dari invoice transaksi
     * @return idFood Mengembalikan nilai idFood pada class Invoice
     */
    public ArrayList<String> getFoods(){
        return foods;
    }

    /**
     * Method untuk mendapatkan tanggal invoice transaksi
     * @return getDate Mengembalikan nilai getDate pada class Invoice
     */
    public String getDate(){
        return date;
    }

    /**
     * Method untuk mendapatkan total harga invoice transaksi
     * @return totalPrice Mengembalikan nilai totalPrice pada class Invoice
     */
    public int getTotalPrice(){
        return totalPrice;
    }


    public String getPaymentType(){ return paymentType;}

    public String getInvoiceStatus(){
        return invoiceStatus;
    }

    /**
     * Method untuk mengatur nilai id invoice
     * @param id Merujuk kepada variabel id dari class Invoice
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * Method untuk mengatur nilai idFood
     * @param foods Merujuk kepada List food dari class Food
     */
    public void setFoods(ArrayList<String> foods){
        this.foods = foods;
    }

    /**
     * Method untuk mengatur nilai date invoice
     * @param date Parameter objek date yang dibuat dengan class Calendar
     * @return date mengembalikan nilai date
     */
    public Calendar setDate(Calendar date){
        return date;
    }

    /**
     * Method untuk mengatur nilai totalPrice dari invoice
     */
    public void setTotalPrice(int totalPrice){ this.totalPrice = totalPrice;}

    /**
     * Method untuk mengatur nilai PaymentType
     * @param paymentType Merujuk kepada objek paymentType dari class PaymentType
     */
    public void setPaymentType(String paymentType){
        this.paymentType = paymentType;
    }

    /**
     * Method untuk mengatur nilai invoiceStatus
     * @param invoiceStatus Merujuk kepada objek invoiceStatus dari class InvoiceStatus
     */
    public void setInvoiceStatus(String invoiceStatus){
        this.invoiceStatus = invoiceStatus;
    }
}