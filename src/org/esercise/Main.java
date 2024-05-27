package org.esercise;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<Evento> eventi = new HashSet<>();
        boolean exit=false;

        while(!exit){
            System.out.println("1-inserisci un nuovo Evento 2-Aggiungi posti ad un evento 3-Disdici posti ad un evento 0-Esci");
            String choice = scanner.nextLine();

            switch (choice){
                case"1":
                    System.out.print("Titolo evento: ");
                    String title = scanner.nextLine();

                    System.out.print("Data evento (yyyy-MM-dd): ");
                    LocalDate date = LocalDate.parse(scanner.nextLine());

                    System.out.print("Capacita location : ");
                    int capacity = Integer.parseInt(scanner.nextLine());
                    try {
                        Evento evento = new Evento(title, date, capacity);
                        eventi.add(evento);

                        System.out.print("Vuoi inserire delle prenotazioni?:  1-si qualsiasi altro carattere-no");
                        String choice2 = scanner.nextLine();
                        if(choice2.equals("1")){
                            getprenotation(evento,scanner);
                        }
                        informationEvent(evento);
                    } catch (IllegalArgumentException e) {
                        System.out.print("Impossibile creare l'evento: ");
                        System.out.println(e.getMessage());
                    }

                    break;
                case"2":
                    System.out.print("A quale evento vuoi aggiungere prenotazioni: ");
                    String titleselected = scanner.nextLine();
                    for (Evento e: eventi) {
                        if (e.getTitle().equals(titleselected)) {
                            getprenotation(e,scanner);
                        }
                        informationEvent(e);
                    }
                    break;
                case"3":
                    System.out.print("A quale evento vuoi disdire prenotazioni: ");
                    String titleselectedtoremove = scanner.nextLine();
                    for (Evento e: eventi) {
                        if (e.getTitle().equals(titleselectedtoremove)) {
                            System.out.print("Quanti posti vuoi disdire?: ");
                            int nSeats = scanner.nextInt();
                            e.removeReservedSeats(nSeats);
                            informationEvent(e);
                        }
                    }
                    break;
                case"0":
                    exit=true;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
        scanner.close();


    }
    //METODO CHE INSERISCE LE PRENOTAZIONI
    public static void getprenotation(Evento e, Scanner scanner) {
        System.out.print("Quanti posti vuoi prenotare?: ");
        int nSeats =Integer.parseInt(scanner.nextLine());
        e.addReservedSeats(nSeats);

    }

    public static void informationEvent(Evento e){
        System.out.println(e.toString());
        System.out.println("Ci sono "+e.getReservedSeats()+" posti prenotati e "+e.seatCalculator()+" posti disponibili su "+e.getCapacity());
    }
}
