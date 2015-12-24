import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Tour {
    private int FreePlaces;
    //номер рейса
    public String TourID;
    //Начальный пункт рейса
    public String From;
    //Конечный пункт рейса
    public String To;
    //Время отправления из начального пункта
    public String DepartureTime;
    //Время прибытия в конечный пункт
    public String ArrivalTime;
    //Все места
    public int Places;
    //Дни недели, по которым ходит рейс
    public String[] DaysOfTrips;
    //Массив остановок
    public Station[] SetOfStations;

    //Массив билетов на месяц
    public List<TicketsForDay> TicketsForMonth;

    //показывает продан ли хоть один билет на этот рейс
    public boolean isTicket = false;
    //текущая дата рейса
    public String TourDate = "default";
    //название
    public String TourName;
    //номер станции отправления из массива станций (для отображения при поиске и покупке билета)
    public int StationFrom;
    //номер станци прибытия из массива станций (для отображения при поиске и покупке билета)
    public int StationTo;
    private double TourPrice;
    private int AllPlaces;

    //конструктор по уумолчанию
    public Tour(){}

    public Tour(String id,String tourName,String from,String depT,String to,String arrT,String date,int freePlaces,Station[] stations,double price,String[] days,int allPlaces) {
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
        //заполнение массива станций
        this.SetOfStations = new Station[stations.length];
        for (int i = 0; i < SetOfStations.length; i++) {
            this.SetOfStations[i] = new Station(stations[i]);
        }
        //
        this.TicketsForMonth = new ArrayList<TicketsForDay>();
        this.TourDate = date;
        this.TourPrice = price;
        this.AllPlaces = allPlaces;
    }

    //метод, который возращает коллекцию билетов по дате рейса
    public TicketsForDay DayTourNow() {
        Calendar calendar =Calendar.getInstance();
        if (TourDate == "default" && TicketsForMonth != null) {
            for (int i = 0; i < TicketsForMonth.size(); i++) {
                if (Integer.parseInt(TicketsForMonth.get(i).getKey().split(".")[0]) >= calendar.get(Calendar.DAY_OF_MONTH) && Integer.parseInt(TicketsForMonth.get(i).getKey().split(".")[1]) == (calendar.get(Calendar.MONTH)+1)) {
                    return TicketsForMonth.get(i);
                } else if (Integer.parseInt(TicketsForMonth.get(i).getKey().split(".")[1]) > (calendar.get(Calendar.MONTH)+1)) {
                    return TicketsForMonth.get(i);
                } else if (Integer.parseInt(TicketsForMonth.get(i).getKey().split(".")[2]) > calendar.get(Calendar.YEAR)) {
                    return TicketsForMonth.get(i);
                }
            }


        } else if (TourDate != "default") {
            String[] date = TourDate.split(".");
            int date1 = Integer.parseInt(date[0]) + Integer.parseInt(date[1]) * 100 + Integer.parseInt(date[2]) * 1000;
            String[] datenow = new String[3];
            date[0]=String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
            date[1]=String.valueOf((calendar.get(Calendar.MONTH)+1));
            date[2]=String.valueOf(calendar.get(Calendar.YEAR));   //DateTime.Now.Date.ToShortDateString().Split('.');
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

    //свойства

    //номер рейса
    public String getPropTourID(){
        return TourID;
    }

    //название рейса
    public String getPropTourName(){
        return TourName;
    }

    //откуда
    public String getPropFrom(){
        //return SetOfStations[StationFrom].StationName;
        return From;
    }

    //время отправления
    /*public String getPropDepartureTime(){
        return SetOfStations[StationFrom].DepTime;
    }*/
    public String getPropDepartureTime(){
        return DepartureTime;
    }

    //куда
    public String getPropTo(){
        //return SetOfStations[StationTo].StationName;
        return To;
    }

    //время прибытия
    public String getPropArrivalTime(){
        //return SetOfStations[StationTo].ArrTime;
        return ArrivalTime;
    }

    //дата рейса
    public String getPropDate(){
            /*if (DayTourNow() != null) {
                return DayTourNow().getKey();
            }
            return "No trip";*/
        return TourDate;
    }

    //количество свободных мест
    public int getPropFreePlaces(){
            /*if (DayTourNow() != null) {
                return Places - DayTourNow().getProtectedPlaces();
            }
            return 0;*/
        return FreePlaces;
    }

    // цена
    public double getPropPrice(){
            /*if (SetOfStations != null) {
                return SetOfStations[StationTo].Price;
            }
            return 0.0;*/
        return TourPrice;

    }
    public String[] getPropDays(){
        return DaysOfTrips;
    }
    //Дни недели, по которым ходит рейс
    /*public String getPropDays(){
            if (DaysOfTrips != null) {
                String res = "";
                for (int i = DaysOfTrips.length - 1; i >= 0; i--) {
                    res += DaysOfTrips.get(i).substring(0, 3) + ", ";
                }
                if (res.length() != 0) {
                    return res.substring(0, res.length() - 2);
                }
            }
            return null;
    }*/

    //все места на рейсе
    public void setFreePlaces(int newFreePlaces){
        this.FreePlaces = newFreePlaces;
    }
    public int getPropAllPlaces(){
        return AllPlaces;
    }
    public Station[] getPropStations(){
        return SetOfStations;
    }
    public String toString(){
        return TourID+" "+TourName+" "+From+" "+DepartureTime+" "+To+" "+ArrivalTime+" "+TourDate+" "+FreePlaces+" "+TourPrice+" "+AllPlaces;
    }
}