import java.util.Calendar;

public class Settings{
    Calendar calendar =Calendar.getInstance();
    //счетчи для номера рейса
    public int LastTourID = 1;
    //счетчи для номера билета
    public int LastTicketID = 1;
    //счетчик для номера посадочной ведомости
    public int LandListID = 1;
    //номер месяца
    public int Month = calendar.get(Calendar.MONTH)+1;
    //номер года
    public  int Year = calendar.get(Calendar.YEAR);
    //путь последнего сохраненного расписания
    public String LastTimeTable;
    //путь для сохранения timetable
    public Settings() {
    }
}