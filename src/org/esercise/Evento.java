package org.esercise;

import org.esercise.exceptions.InvalidCapacityException;
import org.esercise.exceptions.InvalidReservedSeatsException;
import org.esercise.exceptions.InvalidTitleException;
import org.esercise.exceptions.InvalidDateException;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Evento {

    // ATTRIBUTI
    private String title;
    private LocalDate date;
    private int  capacity;
    private int reservedSeats;

    //COSTRUTTORE per la creazione del evento
    public Evento(String title, LocalDate date, int capacity)
            throws InvalidTitleException,InvalidDateException,InvalidCapacityException {
        this.title=validateTitle(title);
        this.date=validateDate(date);
        this.reservedSeats=0;
        this.capacity=validateCpacity(capacity);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws InvalidTitleException {
        this.title = validateTitle(title);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) throws InvalidDateException  {
        this.date = validateDate(date);
    }

    public int getReservedSeats() {
        return reservedSeats;
    }

    public int getCapacity() {
        return capacity;
    }

    //METODO PER VALIDARE IL TITOLO
    private String validateTitle(String title) throws InvalidTitleException {
        if (title == null || title.isEmpty()) {
            throw new InvalidTitleException("Invalid Title: " + title);
        }
        return title;
    }

    //METODO CHE VALIDA LA DATA
    private LocalDate validateDate(LocalDate date) throws InvalidDateException {
        if (date == null || date.isBefore(LocalDate.now())) {
            throw new InvalidDateException("Invalid date: " + date);
        }
        return date;
    }

    //METODO CHE VALIDA LA CAPACITA
    private int validateCpacity(int capacity)throws InvalidCapacityException{
        if ( capacity<0) {
            throw new InvalidCapacityException("Invalid Capacity: " + capacity);
        }
        return capacity;
    }

    //METODO CHE GESTISCE LA PRENOTAZIONE
    public void addReservedSeats(int nSeats){
       if(reservedSeats+nSeats<=capacity){
           reservedSeats+=nSeats;
           System.out.println("Posti aggiunti con successo");
       }
       else {
           System.out.println("ci sono solo: "+seatCalculator()+" posti disponibili");
       }
    }

    //METODO CHE GESTISCE LA DISDETTA
    public void removeReservedSeats(int nSeats){
        if(date.isBefore(LocalDate.now())|| reservedSeats==0){
            System.out.println("Non si puo disdire");
        }
        else {
            reservedSeats-=nSeats;
            System.out.println("Posti disdetti con successo");
        }
    }

    //METODO CHE CALCOLA I POSTI DISPONIBILI
    public int seatCalculator(){
        int totalseat= capacity-reservedSeats;
    return totalseat;

    };

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = date.format(dateFormatter);
        return "Evento "+title+" del "+formattedDate;
    }
}
