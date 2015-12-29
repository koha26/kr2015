import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Tour {
    private int FreePlaces;

    public String TourID;

    public String From;

    public String To;

    public String DepartureTime;

    public String ArrivalTime;


    public String[] DaysOfTrips;

    public Station[] SetOfStations;

    public List<Ticket> SetOfTickets;

    public String TourDate = "default";

    public String TourName;

    private double TourPrice;
    private int AllPlaces;


    public Tour() {
    }

    public Tour(String id, String tourName, String from, String depT, String to, String arrT, String date, int freePlaces, Station[] stations, double price, String[] days, int allPlaces,List<Ticket> tickets) {
        //
        this.TourID = id;
        this.From = from;
        this.To = to;
        this.DepartureTime = depT;
        this.ArrivalTime = arrT;
        this.FreePlaces = freePlaces;
        this.TourName = tourName;
        //
        this.DaysOfTrips = days;
        this.SetOfStations = new Station[stations.length];
        for (int i = 0; i < SetOfStations.length; i++) {
            this.SetOfStations[i] = new Station(stations[i]);
        }
        //
        this.TourDate = date;
        this.TourPrice = price;
        this.AllPlaces = allPlaces;
        this.SetOfTickets = tickets;
    }


    public String getPropTourID() {
        return TourID;
    }

    public String getPropTourName() {
        return TourName;
    }

    public String getPropFrom() {
        return From;
    }

    public String getPropDepartureTime() {
        return DepartureTime;
    }

    public String getPropTo() {
        return To;
    }

    public String getPropArrivalTime() {
        return ArrivalTime;
    }

    public String getPropDate() {

        return TourDate;
    }

    public int getPropFreePlaces() {

        return FreePlaces;
    }

    public double getPropPrice() {
        return TourPrice;

    }

    public String[] getPropDays() {
        return DaysOfTrips;
    }

    public List<Ticket> getSetOfTickets() {
        return SetOfTickets;
    }

    public void setFreePlaces(int newFreePlaces) {
        this.FreePlaces = newFreePlaces;
    }

    public int getPropAllPlaces() {
        return AllPlaces;
    }

    public void addTicketToSet(Ticket ticket){
        this.SetOfTickets.add(ticket);
    }
    public Station[] getPropStations() {
        return SetOfStations;
    }

    public String toString() {
        return TourID + " " + TourName + " " + From + " " + DepartureTime + " " + To + " " + ArrivalTime + " " + TourDate + " " + FreePlaces + " " + TourPrice + " " + AllPlaces;
    }
}