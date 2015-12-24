public class Ticket {
    //номер билета
    private static int TicketID=0;
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
    private String Date;
    //конструктор по умолчанию
    private Ticket() {
    }

    //конструктор с параметрами
    public Ticket(int placeN, String FIO, String from, String to, String depT, String arrT, String date, double price){
        TicketID++;
        this.PlaceNumber = placeN;
        this.Customer = FIO;
        this.StationFrom = from;
        this.StationTo = to;
        this.DepTime = depT;
        this.ArrTime = arrT;
        this.Price = price;
        this.Date = date;
    }
    // конструктор из другого билета
    public Ticket(Ticket other){
        TicketID++;
        this.PlaceNumber = other.getPlaceNumber();
        this.StationFrom = other.getStationFrom();
        this.Customer= other.getCustomer();
        this.StationTo = other.getStationTo();
        this.DepTime = other.getDepTime();
        this.ArrTime = other.getArrTime();
        this.Price = other.getPrice();
        this.Date = other.getDate();
    }

    public static int getTicketID() {
        return TicketID;
    }

    public static void setTicketID(int ticketID) {
        TicketID = ticketID;
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

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}