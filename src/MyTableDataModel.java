import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class MyTableDataModel extends Observable {
    private List<Tour> tours = new ArrayList<Tour>();
    private String [] tourNames = new String[0];
    public void add(int id,String from,String to,String depT,String arrT,int places,Station[] stations,String driver,String company){
        tours.add(new Tour(id,from,to,depT,arrT,places,stations,driver,company));
        this.tourNames = this.getAllTourName();
        setChanged();
        notifyObservers();
    }
    public void add(Tour tour){
        tours.add(tour);
        setChanged();
        notifyObservers();
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
    public String getDateAt(int index){
        return tours.get(index).getPropDate();
    }
    public String getDays(int index){
        return tours.get(index).getPropDays();
    }

    public boolean delete (String tourName,String arrT){
        for (int i=0;i<tours.size();i++){
            if (tours.get(i).getPropTourName().equals(tourName) && tours.get(i).getPropArrivalTime().equals(arrT)){
                tours.remove(i);
                this.tourNames = this.getAllTourName();
                this.setChanged();
                this.notifyObservers();
                return true;
            }
        }
        return false;
    }
}
