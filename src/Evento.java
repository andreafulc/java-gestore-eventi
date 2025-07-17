import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;


// Step 1
// Creare una classe Evento che abbia le seguenti proprietà:
// titolo
// data
// numero di posti in totale
// numero di posti prenotati
// Quando si istanzia un nuovo evento questi attributi devono essere tutti valorizzati nel costruttore, tranne posti prenotati che va inizializzato a 0.
// Inserire il controllo che la data non sia già passata e che il numero di posti totali sia positivo. In caso contrario mostrare i dovuti avvisi all’utente
// Aggiungere metodi getter e setter in modo che:
// titolo sia in lettura e in scrittura
// data sia in lettura e scrittura
// numero di posti totale sia solo in lettura
// numero di posti prenotati sia solo in lettura

// Vanno inoltre implementati dei metodi public che svolgano le seguenti funzioni:
// prenota: aggiunge uno ai posti prenotati. Se l’evento è già passato o non ha posti disponibili deve restituire un messaggio di avviso.
// disdici: riduce di uno i posti prenotati. Se l’evento è già passato o non ci sono prenotazioni restituisce un messaggio di avviso.
// l’override del metodo toString() in modo che venga restituita una stringa contenente: data formattata - titolo
// Aggiungete eventuali metodi (public e private) che vi aiutino a svolgere le funzioni richieste.





public class Evento {

private String titolo;
private LocalDate data;
private int postiTotali;
private int postiPrenotati;

public Evento(String titolo, LocalDate data2, int postiTotali) {
    this.titolo = titolo;
    setData(data2);
    setPostiTotali(postiTotali);
    this.postiPrenotati = 0;

    if (this.data.isBefore(LocalDate.now())) {
        throw new IllegalArgumentException("La data dell'evento non può essere nel passato.");
    }

    if (this.postiTotali <= 0) {
        throw new IllegalArgumentException("Il numero di posti totali deve essere positivo.");
    }

}


public String getTitolo() {
    return titolo;
}

public void setTitolo(String titolo) {
    this.titolo = titolo;
}

public LocalDate getData() {
    return data;
}

public void setData(LocalDate data2) {
    try {
        this.data = data2;
        if (this.data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La data dell'evento non può essere nel passato.");
        }
    } catch (DateTimeParseException e) {
        throw new IllegalArgumentException("Formato data non valido. Usa dd/MM/yyyy.");
    }
}

public int getPostiTotali() {
    return postiTotali;
}

public void setPostiTotali(int postiTotali) {
    if (postiTotali <= 0) {
        throw new IllegalArgumentException("Il numero di posti totali deve essere positivo.");
    }
    this.postiTotali = postiTotali;
}

public int getPostiPrenotati() {
    return postiPrenotati;
}

public String prenota() {
    if (data.isBefore(LocalDate.now())) {
        return "Impossibile prenotare: l'evento è già passato.";
    }
    if (postiPrenotati >= postiTotali) {
        return "Impossibile prenotare: posti esauriti.";
    }
    postiPrenotati++;
    return "Prenotazione effettuata con successo.";
}

public String disdici() {
    if (data.isBefore(LocalDate.now())) {
        return "Impossibile disdire: l'evento è già passato.";
    }
    if (postiPrenotati <= 0) {
        return "Impossibile disdire: nessuna prenotazione da annullare.";
    }
    postiPrenotati--;
    return "Disdetta effettuata con successo.";
}

@Override

public String toString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    return data.format(formatter) + " - " + titolo;
}

@Override

public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Evento)) return false;
    Evento evento = (Evento) o;
    return postiTotali == evento.postiTotali &&
           postiPrenotati == evento.postiPrenotati &&
           Objects.equals(titolo, evento.titolo) &&
           Objects.equals(data, evento.data);
}

@Override

public int hashCode() {
    return Objects.hash(titolo, data, postiTotali, postiPrenotati);
}


public void prenotaPosti(int prenotazioni) {
    if (prenotazioni <= 0) {
        throw new IllegalArgumentException("Il concerto lo facciamo n'altra volta");
    }
    throw new UnsupportedOperationException("Unimplemented method 'prenotaPosti'");
}


public void disdiciPosti(int disdette) {
    
    throw new UnsupportedOperationException("Unimplemented method 'disdiciPosti'");
}


public String getPostiDisponibili() {
    // Calcola i posti disponibili come differenza tra posti totali e prenotati
    int postiDisponibili = postiTotali - postiPrenotati;
    if (postiDisponibili < 0) {
        postiDisponibili = 0; // Non possono esserci posti disponibili negativi
    }
    return String.valueOf(postiDisponibili);
}
public void setPostiPrenotati(int postiPrenotati) {
    if (postiPrenotati < 0 || postiPrenotati > postiTotali) {
        throw new IllegalArgumentException("Il numero di posti prenotati deve essere compreso tra 0 e il numero totale di posti.");
    }
    this.postiPrenotati = postiPrenotati;
}
}
