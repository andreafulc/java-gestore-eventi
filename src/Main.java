// Step 2


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;




// Creare una classe Main di test, in cui si chiede all’utente di inserire un nuovo evento con tutti i parametri.

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Inserisci il titolo dell'evento: ");
            String titolo = scanner.nextLine();

            System.out.print("Inserisci la data dell'evento (yyyy-MM-dd): ");
            LocalDate data = LocalDate.parse(scanner.nextLine());

            System.out.print("Inserisci il numero totale di posti: ");
            int postiTotali = scanner.nextInt();
            scanner.nextLine(); // Consuma la newline

            System.out.print("Inserisci l'artista del concerto: ");
            String artista = scanner.nextLine();

            System.out.print("Inserisci l'orario del concerto (HH:mm): ");
            LocalTime ora = LocalTime.parse(scanner.nextLine());

            System.out.print("Inserisci il prezzo del biglietto: ");
            double prezzo = scanner.nextDouble();

            // Crea un'istanza di Concerto
            Concerto concerto = new Concerto(titolo, data, postiTotali, artista, ora, prezzo);

            // Stampa i dettagli del concerto
            System.out.println("Concerto creato con successo:");
            System.out.println(concerto);

            // Passa direttamente alla gestione delle prenotazioni
            gestisciPrenotazioni(scanner, concerto);

        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    //Dopo che l’evento è stato istanziato, chiedere all’utente se e quante prenotazioni vuole fare e provare ad effettuarle, implementando opportuni controlli
    private static void gestisciPrenotazioni(Scanner scanner, Evento evento) {
        while (true) {
            System.out.print("Vuoi prenotare un posto? (s/n): ");
            String risposta = scanner.nextLine().trim().toLowerCase();

            if (risposta.equals("s")) {
                try {
                    evento.prenota();
                    System.out.println("Prenotazione effettuata con successo. Posti prenotati: " + evento.getPostiPrenotati());
                    // Stampa i dettagli dell'evento dopo la prenotazione
                    stampaPosti(evento);
                } catch (IllegalStateException e) {
                    System.out.println(e.getMessage());
                }
            } else if (risposta.equals("n")) {
                // Chiedi se l'utente vuole gestire le disdette
                System.out.print("Vuoi disdire una prenotazione? (s/n): ");
                String rispostaDisdetta = scanner.nextLine().trim().toLowerCase();
                if (rispostaDisdetta.equals("s")) {
                    gestisciDisdette(scanner, evento);
                } else {
                    // Stampa nuovamente i dettagli dell'evento
                    System.out.println("Dettagli dell'evento:");
                    System.out.println(evento);
                }
                break;
            } else {
                System.out.println("Risposta non valida. Inserisci 's' per sì o 'n' per no.");
            }
        }
    }
    

// Stampare a video il numero di posti prenotati e quelli disponibili

    private static void stampaPosti(Evento evento) {
        System.out.println("Dettagli dell'evento:");
        System.out.println("Posti totali: " + evento.getPostiTotali());
        System.out.println("Posti prenotati: " + evento.getPostiPrenotati());
        System.out.println("Posti disponibili: " + (evento.getPostiTotali() - evento.getPostiPrenotati()));
    }


// Chiedere all’utente se e quanti posti vuole disdire

private static void gestisciDisdette(Scanner scanner, Evento evento) {
    while (true) {
        System.out.print("Vuoi disdire una prenotazione? (s/n): ");
        String risposta = scanner.nextLine().trim().toLowerCase();

        if (risposta.equals("s")) {
            try {
                evento.disdici();
                System.out.println("Disdetta effettuata con successo. Posti prenotati: " + evento.getPostiPrenotati());
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        } else if (risposta.equals("n")) {
            break;
        } else {
            System.out.println("Risposta non valida. Inserisci 's' per sì o 'n' per no.");
        }
    }
    scanner.close();
}
}