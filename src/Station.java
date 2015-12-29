public class Station {
    public String StationName;
    public String ArrTime;
    public String StayTime;
    public String DepTime;
    public double Price;
    public Station() {
    }

    public Station(String name, double price) {
        this.StationName = name.trim();
        //this.ArrTime = arrT.trim();
        //this.StayTime = stayT.trim();
        //this.DepTime = depT.trim();
        this.Price = price;
    }

    public Station(Station other) {
        this.StationName = other.StationName;
        //this.ArrTime = other.ArrTime;
        //this.StayTime = other.StayTime;
        //this.DepTime = other.DepTime;
        this.Price = other.Price;
    }

    public String getPropStationName() {
        return StationName;
    }

    public void setPropStationName(String stationName) {
        StationName = stationName;
    }


    public String getPropArrTime() {
        return ArrTime;
    }

    public void setPropArrTime(String arrTime) {
        ArrTime = arrTime;
    }

    public String getPropStayTime() {
        return StayTime;
    }

    public void setPropStayTime(String stayTime) {
        StayTime = stayTime;
    }


    public String getPropDepTime() {
        return DepTime;
    }

    public void setPropDepTime(String depTime) {
        DepTime = depTime;
    }


    public double getPropPrice() {
        return Price;
    }

    public void setPropPrice(double price) {
        Price = price;
    }
}