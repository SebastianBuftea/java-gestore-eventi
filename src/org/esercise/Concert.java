package org.esercise;

import org.esercise.exceptions.InvalidCapacityException;
import org.esercise.exceptions.InvalidDateException;
import org.esercise.exceptions.InvalidTitleException;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class Concert extends Evento {

    LocalTime time;
    BigDecimal price;

    public Concert(String title, LocalDate date, int capacity, String time,String price) throws InvalidTitleException, InvalidDateException, InvalidCapacityException {
        super(title, date, capacity);
        this.price=new BigDecimal(price);
        this.time=LocalTime.parse(time);
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    private BigDecimal validatePrice(BigDecimal price) {
        return price;
    }

    private String formattedPrice(BigDecimal price){
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.ITALY);
        String formattedPrice = currencyFormatter.format(price);
        return formattedPrice;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = getDate().format(dateFormatter);
        return "Evento "+getTitle()+" del "+formattedDate+"ore "+time+" dal prezzo di: "+formattedPrice(price);
    }
}
