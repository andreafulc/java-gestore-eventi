import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Concerto extends Evento {

    // Creare una classe Concerto che estenda Evento, che abbia anche gli attributi :
    private String artista;
    private LocalTime ora;
    private double prezzo;

    public Concerto(String titolo, LocalDate data, int postiTotali, String artista, LocalTime ora, double prezzo) {
        super(titolo, data, postiTotali);
        this.artista = artista;
        this.ora = ora;
        setPrezzo(prezzo);
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public LocalTime getOra() {
        return ora;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        if (prezzo < 0) {
            throw new IllegalArgumentException("Il prezzo non può essere negativo.");
        }
        this.prezzo = prezzo;
    }

    public String getDataFormattata() {
        return getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getOraFormattata() {
        return ora.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public String getPrezzoFormattato() {
        return String.format("%.2f€", prezzo);
    }

    @Override
    public String toString() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return super.toString() + "\nArtista: " + artista + 
               "\nOra: " + ora.format(timeFormatter) + 
               "\nPrezzo: €" + String.format("%.2f", prezzo);
    }

}

