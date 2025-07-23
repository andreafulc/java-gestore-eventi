// Step 2


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;




// Creare una classe Main di test, in cui si chiede all’utente di inserire un nuovo evento con tutti i parametri.

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Benvenuto nel sistema di gestione eventi!");
        
        ProgrammaEventi programma = new ProgrammaEventi("Eventi in Programma");
        

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

            // istanza di Concerto
            Concerto concerto = new Concerto(titolo, data, postiTotali, artista, ora, prezzo);

            // Stampa i dettagli del concerto
            System.out.println("Concerto creato con successo:");
            System.out.println(concerto);

            //qua vedo se va creato n'altro evento
            scanner.nextLine(); 
            boolean creaAltroEvento = true;
            while (creaAltroEvento) {
                System.out.print("Vuoi creare un altro evento? (s/n): ");
                String risposta = scanner.nextLine().trim().toLowerCase();
                if (risposta.equals("s")) {
                    creaAltroEvento = true;
                    System.out.println("Procedi con la creazione di un nuovo evento.");
                    // Chiamata ricorsiva per creare un altro evento
                    main(args);
                    return; // Esce dal metodo corrente per evitare di continuare con la prenotazione
                } else if (risposta.equals("n")) {
                    creaAltroEvento = false;
                    System.out.println("Bene");
                } else {
                    System.out.println("Risposta non valida. Inserisci 's' per sì o 'n' per no.");
                    
         
                } 
            }
           

            // Passa direttamente alla gestione delle prenotazioni
            gestisciPrenotazioni(scanner, concerto);
            programma.aggiungiEvento(concerto);

            // Stampa il programma degli eventi
            System.out.println("Eventi registrati:");
            System.out.println(programma.stampaProgramma());


        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }
    

    System.out.println("Vuoi gestire le prenotazioni di un evento specifico?");
System.out.print("Vuoi procedere? (s/n): ");
String risposta = scanner.nextLine().trim().toLowerCase();

if (risposta.equals("s")) {
    List<Evento> eventiNelProgramma = programma.getEventi();

    if (eventiNelProgramma.isEmpty()) {
        System.out.println(" Nessun evento disponibile.");
    } else {
        // Stampa numerata
        System.out.println(" Elenco degli eventi:");
        for (int i = 0; i < eventiNelProgramma.size(); i++) {
            Evento e = eventiNelProgramma.get(i);
            System.out.println((i + 1) + ". " + e.getData() + " - " + e.getTitolo());
        }

        System.out.print("Inserisci il numero dell'evento da gestire: ");
        int scelta = Integer.parseInt(scanner.nextLine());

        if (scelta >= 1 && scelta <= eventiNelProgramma.size()) {
            Evento eventoScelto = eventiNelProgramma.get(scelta - 1);
            gestisciPrenotazioni(scanner, eventoScelto);
         } else if (risposta.equals("n")) {
    System.out.println("Grazie per aver utilizzato il sistema di gestione eventi!");
    } else {
    System.out.println("Risposta non valida. Inserisci 's' per sì o 'n' per no.");
        }
    }}
    
    
} 

    //Dopo che l’evento è stato istanziato, chiedere all’utente se e quante prenotazioni vuole fare e provare ad effettuarle, implementando opportuni controlli
    private static void gestisciPrenotazioni(Scanner scanner, Evento evento) {
        System.out.println("\n🔔 Stai gestendo le prenotazioni per l'evento: " 
            + evento.getData() + " - " + evento.getTitolo());
    
        while (true) {
            System.out.print("Vuoi prenotare un posto? (s/n): ");
            String risposta = scanner.nextLine().trim().toLowerCase();
    
            if (risposta.equals("s")) {
                try {
                    evento.prenota();
                    System.out.println("Prenotazione effettuata con successo. Posti prenotati: " + evento.getPostiPrenotati());
                    stampaPosti(evento);
                } catch (IllegalStateException e) {
                    System.out.println("⚠️ " + e.getMessage());
                }
            } else if (risposta.equals("n")) {
                System.out.print("Vuoi disdire una prenotazione? (s/n): ");
                String rispostaDisdetta = scanner.nextLine().trim().toLowerCase();
                if (rispostaDisdetta.equals("s")) {
                    gestisciDisdette(scanner, evento);
                } else {
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