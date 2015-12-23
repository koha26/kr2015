import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Tour {
    //номер рейса
    public int TourID;
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
    public List<String> DaysOfTrips;
    //Массив остановок
    public Station[] SetOfStations;

    //Посадочная ведомость
    public String Driver;       // водитель
    public String Company;      // компания

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

    //конструктор по уумолчанию
    public Tour() {
    }

    public Tour(
            int id,
            String from,
            String to,
            String depT,
            String arrT,
            int places,
            Station[] stations,
            String driver,
            String company
    ) {
        this.StationFrom = 0;
        this.StationTo = stations.length - 1;
        //
        this.TourID = id;
        this.From = from;
        this.To = to;
        this.DepartureTime = depT;
        this.ArrivalTime = arrT;
        this.Places = places;
        this.TourName = From + "-" + To;
        //
        this.DaysOfTrips = new ArrayList<String>();
        //заполнение массива станций
        this.SetOfStations = new Station[stations.length];
        for (int i = 0; i < SetOfStations.length; i++) {
            this.SetOfStations[i] = new Station(stations[i]);
        }
        //
        this.Driver = driver;
        this.Company = company;
        this.TicketsForMonth = new ArrayList<TicketsForDay>();
        this.TourDate = "default";
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
        return String.valueOf(TourID);
    }

    //название рейса
    public String getPropTourName(){
        return TourName;
    }

    //откуда
    public String getPropFrom(){
        return SetOfStations[StationFrom].StationName;
    }

    //время отправления
    public String getPropDepartureTime(){
        return SetOfStations[StationFrom].DepTime;
    }

    //куда
    public String getPropTo(){
        return SetOfStations[StationTo].StationName;
    }

    //время прибытия
    public String getPropArrivalTime(){
        return SetOfStations[StationTo].ArrTime;
    }

    //дата рейса
    public String getPropDate(){
            if (DayTourNow() != null) {
                return DayTourNow().getKey();
            }
            return "No trip";
    }

    //количество свободных мест
    public int getPropFreePlaces(){
            if (DayTourNow() != null) {
                return Places - DayTourNow().getProtectedPlaces();
            }
            return 0;
    }

    // цена
    public double getPropPrice(){
            if (SetOfStations != null) {
                return SetOfStations[StationTo].Price;
            }
            return 0.0;
    }

    //Дни недели, по которым ходит рейс
    public String getPropDays(){
            if (DaysOfTrips != null) {
                String res = "";
                for (int i = DaysOfTrips.size() - 1; i >= 0; i--) {
                    res += DaysOfTrips.get(i).substring(0, 3) + ", ";
                }
                if (res.length() != 0) {
                    return res.substring(0, res.length() - 2);
                }
            }
            return null;
    }

    //все места на рейсе
    public int getPropAllPlaces(){
        return Places;
    }
    public Station[] getPropStations(){
        return SetOfStations;
    }
}