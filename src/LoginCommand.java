import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginCommand extends Command{
    private String login,password;
    public LoginCommand(String login,String password){
        super(CommandType.valueOf("LOGIN"));
        this.login=login;
        this.password=password;
    }
    public boolean registered() throws FileNotFoundException {
        try {
            Scanner sc=new Scanner(new FileReader("log.txt"));
            String line="";
            String[] part;
            while (sc.nextLine()!=null){
                line=sc.nextLine();
                part=line.split(" ");
                if (part[0].equals(login) && part[1].equals(password))
                    return true;
            }
        }
        catch (IOException e){
            return false;
        }
        return false;
    }

    @Override
    public String toString() {
        return "LoginCommand{" +
                "password='" + password + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
