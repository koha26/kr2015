public class TourInfoCommand extends Command{
    private Tour tour;
    public TourInfoCommand(int id,String tourName,String from,String to,String depT,String arrT, String date, int freePlaces,double prices,String days,int allPlaces){
        super(CommandType.valueOf("TOUR_INFO"));
        //TODO
    }
}
