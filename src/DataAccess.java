import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DataAccess {
    public static final String LOG_FOLDER="log";
    private static final String TICKET_FOLDER="tickets";
    private File[] files;
    private int numberTickets;
    private Map<String,String> tourNameDate=new HashMap<String, String>();
    public DataAccess(){
        File myFolder = new File(DataAccess.LOG_FOLDER);
        this.files = myFolder.listFiles();
        String name="";
        for (int i=0;i<files.length;i++){
            name = this.files[i].getName().substring(0,9); //tour_XXXX = 9 symbols
            this.tourNameDate.put(name,"None");
        }
    }
    public void deleteById(String id){
        File file = new File(DataAccess.LOG_FOLDER+"\\tour_"+id+".txt");
        file.delete();
    }
    public String[] getAllNames(){
        String[]names = new String[tourNameDate.size()];
        names=tourNameDate.keySet().toArray(names);
        return names;
    }
    public void addTour(Tour tour) throws FileNotFoundException {
        rewriteTour(tour);
    }
    public Tour getTourById(String id){
        Scanner sc = null;
        try {
            FileReader fr = new FileReader(LOG_FOLDER+"\\tour_"+id+".txt");
            sc = new Scanner(fr);
        } catch (FileNotFoundException e) {
            return null;
        }
        if (isAvaivable(id)){
            String line="";
            line = sc.nextLine();
            String tourId=line;
            line = sc.nextLine();
            String tourName = line;
            line = sc.nextLine();
            String from = line;
            line = sc.nextLine();
            String depT = line;
            line = sc.nextLine();
            String to = line;
            line = sc.nextLine();
            String arrT = line;
            line = sc.nextLine();
            String date = line;
            line = sc.nextLine();
            int freePlaces = Integer.parseInt(line);
            line = sc.nextLine();
            String parts[]=line.split(" ");
            Station [] stations = new Station[parts.length / 2];
            int ind=0;
            for (int i=0; i<parts.length;i+=2){
                stations[ind]=new Station(parts[i],Double.parseDouble(parts[i+1]));
                ind++;
            }
            line = sc.nextLine();
            double price  = Double.parseDouble(line);
            line = sc.nextLine();
            String days = line;
            parts=line.split(" ");
            line = sc.nextLine();
            int allPlaces = Integer.parseInt(line);
            sc.close();
            tourNameDate.put(tourName,date);
            return new Tour(tourId,tourName,from,depT,to,arrT,date,freePlaces,stations,price,parts,allPlaces);
        }
        return null;
    }
    public void rewriteTour(Tour tour) throws FileNotFoundException {
        File file = new File(getNameOfFile(tour.getPropTourID()));
        PrintWriter pw = new PrintWriter(file);
        String line="";
        pw.write(tour.getPropTourID());
        pw.println();
        pw.write(tour.getPropTourName());
        pw.println();
        pw.write(tour.getPropFrom());
        pw.println();
        pw.write(tour.getPropDepartureTime());
        pw.println();
        pw.write(tour.getPropTo());
        pw.println();
        pw.write(tour.getPropArrivalTime());
        pw.println();
        pw.write(tour.getPropDate());
        pw.println();
        pw.write(Integer.toString(tour.getPropFreePlaces()));
        pw.println();
        for (int i=0;i<tour.getPropStations().length;i++){
            pw.write(tour.getPropStations()[i].getPropStationName()+" ");
            pw.write(Double.toString(tour.getPropStations()[i].getPropPrice()));
            if (i!=tour.getPropStations().length-1) pw.write(" ");
        }
        pw.println();
        pw.write(Double.toString(tour.getPropPrice()));
        pw.println();
        for (int i=0;i<tour.getPropDays().length;i++){
            pw.write(tour.getPropDays()[i]+" ");
            if (i!=tour.getPropDays().length-1) pw.write(" ");
        }
        pw.println();
        pw.write(Integer.toString(tour.getPropAllPlaces()));
        pw.println();
        pw.close();
        tourNameDate.put(tour.getPropTourName(),tour.getPropDate());

    }
    public String getNameOfFile(String id){
        return LOG_FOLDER+"\\tour_"+id+".txt";
    }
    public boolean isAvaivable(String id){
        String [] names = getAllNames();
        String tourId="";
        for (int i=0;i<getAllNames().length;i++){
            tourId = names[i].substring(5);
            if (tourId.equals(id)) return true;
        }
        return false;
    }

    public Map<String, String> getTourNameDate() {
        return tourNameDate;
    }

    public void printTicket(Ticket ticket) throws FileNotFoundException {
        File file = new File(getNameOfTicket(ticket));
        PrintWriter pw = new PrintWriter(file);
        pw.write(Integer.toString(Ticket.getTicketID()));
        pw.println();
        pw.write(ticket.getCustomer());
        pw.println();
        pw.write(ticket.getStationFrom() + " " + ticket.getDepTime());
        pw.println();
        pw.write(ticket.getStationTo() + " " + ticket.getArrTime());
        pw.println();
        pw.write(ticket.getDate());
        pw.println();
        pw.write(Integer.toString(ticket.getPlaceNumber()));
        pw.println();
        pw.write(Double.toString(ticket.getPrice()));
        pw.println();
        pw.close();
    }
    

    public String getNameOfTicket(Ticket ticket){
       return TICKET_FOLDER+"\\ticket_"+Integer.toString(ticket.getTicketID())+".txt";
    }

    public static void main (String [] args) throws IOException {
        DataAccess ds = new DataAccess();
        System.out.print(ds.getTourNameDate().keySet());
        //System.out.print(ds.getTourNameDate().get("tour_0001.txt"));
        //System.out.print(ds.getAllNames());
//        System.out.println(ds.getTourById("0001").toString());
        Station station[] = new Station[2];
        station[0]=new Station("Основа",30.2);
        station[1]=new Station("Кременчуг",60.75);
        String [] days = {"1","2","3"};
        Tour tour1= new Tour("0002","Харьков-Полтава","Харьков","12:12","Полтава","17:10","30.12.2015",25,station,99.99,days,101);
        ds.addTour(tour1);

        station= new Station[2];
        station[0]=new Station("Шевченково",4.05);
        station[1]=new Station("Основа",4.5);
        days = new String[]{"Ежедневно"};
        Tour tour2= new Tour("0001","Купянск-Харьков","Купянск","14:36","Харьков","19:00","24.12.2015",150,station,5.94,days,300);
        ds.addTour(tour2);

        station=new Station[3];
        station[0]=new Station("Шевченково",10.13);
        station[1]=new Station("Кременчук",35.65);
        station[2]=new Station("Подгорки",45);
        days = new String[]{"2","4","5"};
        Tour tour3= new Tour("0003","Купянск-Полтава","Купянск","10:26","Полтава","19:00","24.12.2015",80,station,50.50,days,107);
        ds.addTour(tour3);

        station=new Station[4];
        station[0]=new Station("Харьков",10.3);
        station[1]=new Station("Полтава",60.1);
        station[2]=new Station("Чернигов",76.24);
        station[3]=new Station("Нипки",100.79);
        days = new String[]{"1","3","5","7"};
        Tour tour4= new Tour("0004","Купянск-Киев","Купянск","14:36","Киев","23:00","23.12.2015",150,station,150.57,days,300);
        ds.addTour(tour4);

        station= new Station[2];
        station[0]=new Station("Основа",1.25);
        station[1]=new Station("Граково",3.6);
        days = new String[]{"Ежедневно"};
        Tour tour5= new Tour("0005","Харьков-Купянск","Харьков","14:00","Купянск","17:00","25.12.2015",300,station,6,days,300);
        ds.addTour(tour5);

        station= new Station[4];
        station[0]=new Station("Полтава",30.1);
        station[1]=new Station("Киев",120.56);
        station[2]=new Station("Житомир",200.1);
        station[3]=new Station("Хмельницкий",240.9);
        days = new String[]{"2","6","7"};
        Tour tour6= new Tour("0006","Харьков-Львов","Харьков","16:36","Львов","6:05","26.12.2015",179,station,306.23,days,300);
        ds.addTour(tour6);
    }
}
