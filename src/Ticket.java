public class Ticket {
    //номер билета
    private int TicketID;
    //номер места
    private int PlaceNumber;
    //ФИО покупателя
    private String Customer;
    //Станция отправления
    private String StationFrom;
    //Станция прибытия
    private String StationTo;
    //Цена
    private double Price;
    //время отправления из начального пункта
    private String DepTime;
    //Время прибытия в конечный пункт
    private String ArrTime;
    //конструктор по умолчанию
    private Ticket() {
    }

    //конструктор с параметрами
    public Ticket(int id, int placeN, String FIO, String from, String to, String depT, String arrT, double price){
        this.TicketID = id;
        this.PlaceNumber = placeN;
        this.Customer = FIO;
        this.StationFrom = from;
        this.StationTo = to;
        this.DepTime = depT;
        this.ArrTime = arrT;
        this.Price = price;
    }
    // конструктор из другого билета
    public Ticket(Ticket other){
        this.TicketID = other.getTicketID();
        this.PlaceNumber = other.getPlaceNumber();
        this.Customer = other.getCustomer();
        this.StationFrom = other.getStationFrom();
        this.StationTo = other.getStationTo();
        this.DepTime = other.getDepTime();
        this.ArrTime = other.getArrTime();
        this.Price = other.getPrice();
    }

    public int getTicketID() {
        return TicketID;
    }

    public void setTicketID(int ticketID) {
        this.TicketID = ticketID;
    }

    public int getPlaceNumber() {
        return PlaceNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.PlaceNumber = placeNumber;
    }

    public String getCustomer() {
        return Customer;
    }

    public void setCustomer(String customer) {
        this.Customer = customer;
    }

    public String getStationFrom() {
        return StationFrom;
    }

    public void setStationFrom(String stationFrom) {
        this.StationFrom = stationFrom;
    }

    public String getStationTo() {
        return StationTo;
    }

    public void setStationTo(String stationTo) {
        this.StationTo = stationTo;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        this.Price = price;
    }

    public String getDepTime() {
        return DepTime;
    }

    public void setDepTime(String depTime) {
        this.DepTime = depTime;
    }

    public String getArrTime() {
        return ArrTime;
    }

    public void setArrTime(String arrTime) {
        this.ArrTime = arrTime;
    }
}