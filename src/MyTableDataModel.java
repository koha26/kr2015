import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class MyTableDataModel extends Observable {
    private List<Tour> tours = new ArrayList<Tour>();
    private String [] tourNames = new String[0];
    public void add(String id,String tourName,String from,String depT,String to,String arrT,String date,int freePlaces,Station[] stations,double price,String[] days,int allPlaces,ArrayList<Ticket> tickets){
        tours.add(new Tour(id,tourName,from,depT,to,arrT,date,freePlaces,stations,price,days,allPlaces,tickets));
        this.tourNames = this.getAllTourName();
        setChanged();
        notifyObservers();
    }
    public void add(Tour tour){
        tours.add(tour);
        setChanged();
        notifyObservers();
    }
    public Tour getAt(int index){
        return tours.get(index);
    }
    public int getSize(){
        return tours.size();
    }
    public String getFromAt(int index){
        return tours.get(index).getPropFrom();
    }
    public String getToAt(int index){
        return tours.get(index).getPropTo();
    }
    public String getIdAt(int index){
        return tours.get(index).getPropTourID();
    }
    public String getDepTimeAt(int index){
        return tours.get(index).getPropDepartureTime();
    }
    public String getArrTimeAt(int index){
        return tours.get(index).getPropArrivalTime();
    }
    public String getTourNameAt(int index){
        return tours.get(index).getPropTourName();
    }
    public Station[] getStationsAt(int index){
        return tours.get(index).getPropStations();
    }
    public double getPriceAt(int index){
        return tours.get(index).getPropPrice();
    }
    public int getFreePlacesAt(int index){
        return tours.get(index).getPropFreePlaces();
    }
    public int getAllPlacesAt(int index){
        return tours.get(index).getPropAllPlaces();
    }
    public String[] getAllTourName(){
        String[] names = new String[tours.size()];
        for (int i=0;i<tours.size();i++){
            names[i]=tours.get(i).getPropTourName();
        }
        return names;
    }
    public Station[] getStations(int index){
        return tours.get(index).getPropStations();
    }
    public String getDateAt(int index){
        return tours.get(index).getPropDate();
    }
    public String getDays(int index){
        String text="";
        for (int i=0;i<tours.get(index).DaysOfTrips.length;i++){
            text+=tours.get(index).DaysOfTrips[i]+" ";
        }
        return text;
    }

    public boolean delete (String tourName,String date){
        for (int i=0;i<tours.size();i++){
            if (tours.get(i).getPropTourName().equals(tourName) && tours.get(i).getPropDate().equals(date)){
                //File file = new File(DataAccess.LOG_FOLDER+"\\tour_"+tours.get(i).getPropTourID()+".txt");
                //file.delete();
                tours.remove(i);
                this.tourNames = this.getAllTourName();
                this.setChanged();
                this.notifyObservers();
                return true;
            }
        }
        return false;
    }
    public void clear() {
        this.tours.clear();
        this.setChanged();
        this.notifyObservers();
    }
}
