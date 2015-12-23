public class TourCommand extends Command{
    private String tourId;
    public TourCommand(String tourId){
        super(CommandType.valueOf("TOUR"));
        this.tourId=tourId;
    }
}
