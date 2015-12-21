public class TicketsForDay {
    //массив билетов
    private Ticket[] massOfTickets;
    //дата дня аоездки
    private String Key;
    //Занятые места
    private int ProtectedPlaces;

    //конструктор по умолчанию
    public TicketsForDay() {
    }

    //конструктор с параметрами: дата, количество мест
    public TicketsForDay(String key, int countOfPlaces) {
        this.Key = key;
        this.massOfTickets = new Ticket[countOfPlaces];
    }

    //конструктор с параметрами: другая коолекция билетов, увеличение их колличества
    public TicketsForDay(TicketsForDay other, int shift) {
        this.ProtectedPlaces = other.getProtectedPlaces();
        this.Key = other.getKey();
        this.massOfTickets = new Ticket[other.massOfTickets.length + shift];
        for (int i = 0; i < other.massOfTickets.length; i++) {
            if (other.massOfTickets[i] != null) {
                this.massOfTickets[i] = new Ticket(other.getTicketFromMass(i));
            } else {
                this.massOfTickets[i] = null;
            }
        }
    }

    public Ticket getTicketFromMass(int index) {
        return massOfTickets[index];
    }

    public Ticket[] getMassOfTickets() {
        return massOfTickets;
    }

    public void setMassOfTickets(Ticket[] massOfTickets) {
        this.massOfTickets = massOfTickets;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public int getProtectedPlaces() {
        return ProtectedPlaces;
    }

    public void setProtectedPlaces(int protectedPlaces) {
        ProtectedPlaces = protectedPlaces;
    }
}