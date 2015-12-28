public class Ticket {
    private String TicketID;
    private int PlaceNumber;
    private String Customer;
    private String StationFrom;
    private String StationTo;
    private double Price;
    private String DepTime;
    private String ArrTime;
    private String Date;
    private Ticket() {
    }


    public Ticket(String ticketId, int placeN, String FIO, String from, String to, String depT, String arrT, String date, double price){
        this.TicketID = ticketId;
        this.PlaceNumber = placeN;
        this.Customer = FIO;
        this.StationFrom = from;
        this.StationTo = to;
        this.DepTime = depT;
        this.ArrTime = arrT;
        this.Price = price;
        this.Date = date;
    }
    public Ticket(String ticketId, int placeN, String FIO, String from, String to, double price){
        this.TicketID = ticketId;
        this.PlaceNumber = placeN;
        this.Customer = FIO;
        this.StationFrom = from;
        this.StationTo = to;
        this.Price = price;
    }
    public Ticket(Ticket other){
        this.TicketID = other.getTicketID();
        this.PlaceNumber = other.getPlaceNumber();
        this.StationFrom = other.getStationFrom();
        this.Customer= other.getCustomer();
        this.StationTo = other.getStationTo();
        this.DepTime = other.getDepTime();
        this.ArrTime = other.getArrTime();
        this.Price = other.getPrice();
        this.Date = other.getDate();
    }

    public String getTicketID() {
        return TicketID;
    }

    public void setTicketID(String ticketID) {
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