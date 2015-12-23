
public class Command {
    private CommandType commandType;
    public Command(CommandType t){
        this.commandType = t;
    }
    public static enum CommandType{
        ACCEPT,DISCONNECT,LOGIN,TOUR_REQUEST,TOUR_INFO;

    }
    public CommandType getCommandType(){
        return commandType;
    }
    @Override
    public String toString() {
        return  commandType.toString();
    }
}
