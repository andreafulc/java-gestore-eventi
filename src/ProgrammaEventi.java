// Creare una classe ProgrammaEventi con i seguenti attributi: 
// - titolo: String  (una variabile di istanza chiamata titolo, di tipo String)
// - eventi: List<Evento>  (una variabile di istanza chiamata eventi, di tipo “implementatore di” List<Event>)
// Nel costruttore valorizzare il titolo, passato come parametro, e inizializzare la lista di eventi come una nuova ArrayList
// Aggiungere i seguenti metodi :
// un metodo che aggiunge alla lista un Evento, passato come parametro
// un metodo che restituisce una lista con tutti gli eventi presenti in una certa data
// un metodo che restituisce quanti eventi sono presenti nel programma
// un metodo che svuota la lista di eventi
// un metodo che restituisce una stringa che mostra il titolo del programma e tutti gli eventi ordinati per data nella forma: 
// data1 - titolo1
// data2 - titolo2 
// …

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProgrammaEventi {

    private String titolo;
    private List<Evento> eventi;
    


public ProgrammaEventi(String titolo) {
        this.titolo = titolo;
        this.eventi = new ArrayList<>();
    }

    //aggiungo evento alla lista
    public void aggiungiEvento(Evento evento) {
        eventi.add(evento);
        
    }

    //restituisco la lista con gli eventi della stessa data
    public List<Evento> getEventiInData(LocalDate data) {
        return eventi.stream()
                .filter(e -> e.getData().equals(data))
                .collect(Collectors.toList());
    }

    //conta gli eventi
    public int getNumeroEventi() {
        return eventi.size();
    }

    //cancella eventi
    public void svuotaEventi() {
        eventi.clear();
    }

    //stampa il programma con gli eventi ordinati per data

     public String stampaProgramma() {
        List<Evento> ordinati = new ArrayList<>(eventi); 
        ordinati.sort(Comparator.comparing(Evento::getData)); 

        StringBuilder sb = new StringBuilder();
        sb.append("Programma: ").append(titolo).append("\n");

        for (Evento e : ordinati) {
            sb.append(e.getData()).append(" - ").append(e.getTitolo()).append("\n");
        }

        return sb.toString();
    }

    public List<Evento> getEventi() {
        return eventi;
    }
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(titolo + "\n");
        eventi.stream()
                .sorted(Comparator.comparing(Evento::getData))
                .forEach(e -> sb.append(e.getDataFormattata()).append(" - ").append(e.getTitolo()).append("\n"));
        return sb.toString();
    }
}