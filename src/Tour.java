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

    public int Places;

    public String[] DaysOfTrips;

    public Station[] SetOfStations;

    public List<Ticket> SetOfTickets;

    public List<TicketsForDay> TicketsForMonth;


    public boolean isTicket = false;
    public String TourDate = "default";

    public String TourName;

    public int StationFrom;

    public int StationTo;
    private double TourPrice;
    private int AllPlaces;


    public Tour() {
    }

    public Tour(String id, String tourName, String from, String depT, String to, String arrT, String date, int freePlaces, Station[] stations, double price, String[] days, int allPlaces,List<Ticket> tickets) {
        this.StationFrom = 0;
        this.StationTo = stations.length - 1;
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
        this.TicketsForMonth = new ArrayList<TicketsForDay>();
        this.TourDate = date;
        this.TourPrice = price;
        this.AllPlaces = allPlaces;
        this.SetOfTickets = tickets;
    }


    public TicketsForDay DayTourNow() {
        Calendar calendar = Calendar.getInstance();
        if (TourDate == "default" && TicketsForMonth != null) {
            for (int i = 0; i < TicketsForMonth.size(); i++) {
                if (Integer.parseInt(TicketsForMonth.get(i).getKey().split(".")[0]) >= calendar.get(Calendar.DAY_OF_MONTH) && Integer.parseInt(TicketsForMonth.get(i).getKey().split(".")[1]) == (calendar.get(Calendar.MONTH) + 1)) {
                    return TicketsForMonth.get(i);
                } else if (Integer.parseInt(TicketsForMonth.get(i).getKey().split(".")[1]) > (calendar.get(Calendar.MONTH) + 1)) {
                    return TicketsForMonth.get(i);
                } else if (Integer.parseInt(TicketsForMonth.get(i).getKey().split(".")[2]) > calendar.get(Calendar.YEAR)) {
                    return TicketsForMonth.get(i);
                }
            }


        } else if (TourDate != "default") {
            String[] date = TourDate.split(".");
            int date1 = Integer.parseInt(date[0]) + Integer.parseInt(date[1]) * 100 + Integer.parseInt(date[2]) * 1000;
            String[] datenow = new String[3];
            date[0] = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
            date[1] = String.valueOf((calendar.get(Calendar.MONTH) + 1));
            date[2] = String.valueOf(calendar.get(Calendar.YEAR));   //DateTime.Now.Date.ToShortDateString().Split('.');
            int date2 = Integer.parseInt(datenow[0]) + Integer.parseInt(datenow[1]) * 100 + Integer.parseInt(datenow[2]) * 1000;
            if (date1 >= date2) {
                for (int i = 0; i < TicketsForMonth.size(); i++) {
                    if (TicketsForMonth.get(i).getKey() == TourDate) {
                        return TicketsForMonth.get(i);
                    }
                }
            }
        }
        return null;
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