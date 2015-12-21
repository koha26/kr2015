public class Station {
    //название остановки
    public String StationName;
    //время прибытия
    public String ArrTime;
    //время стоянки
    public String StayTime;
    //время отправления
    public String DepTime;
    //стоимость проезда до станции
    public double Price;

    //конструктор по умолчанию
    public Station() {
    }

    //конструктор с параметрами
    public Station(String name, String arrT, String stayT, String depT, double price) {
        this.StationName = name.trim();
        this.ArrTime = arrT.trim();
        this.StayTime = stayT.trim();
        this.DepTime = depT.trim();
        this.Price = price;
    }

    //конструктор из другой станции
    public Station(Station other) {
        this.StationName = other.StationName;
        this.ArrTime = other.ArrTime;
        this.StayTime = other.StayTime;
        this.DepTime = other.DepTime;
        this.Price = other.Price;
    }

    //свойства
    //вывод названия станции
    public String getPropStationName() {
        return StationName;
    }

    public void setPropStationName(String stationName) {
        StationName = stationName;
    }

    //вывод времени прибытия
    public String getPropArrTime() {
        return ArrTime;
    }

    public void setPropArrTime(String arrTime) {
        ArrTime = arrTime;
    }

    //вывод времени стоянки
    public String getPropStayTime() {
        return StayTime;
    }

    public void setPropStayTime(String stayTime) {
        StayTime = stayTime;
    }

    //вывод времени отправления
    public String getPropDepTime() {
        return DepTime;
    }

    public void setPropDepTime(String depTime) {
        DepTime = depTime;
    }

    //вывод цены
    public double getPropPrice() {
        return Price;
    }

    public void setPropPrice(double price) {
        Price = price;
    }
}