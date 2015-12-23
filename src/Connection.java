import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Connection {
    private Socket socket;
    private static final String VERSION = "ChatApp 2015";
    private DataInputStream in;
    private DataOutputStream out;
    private InputStream sin;

    public Connection(Socket socket) {
        this.socket = socket;
    }

    public void login(String nick, String password) throws IOException {
        OutputStream sout = socket.getOutputStream();
        out = new DataOutputStream(sout);
        out.write((nick + " " + password).getBytes("UTF-8"));
        out.write(0x0a);
        out.flush();
    }

    public void sendRequest(String tourId) throws IOException {
        OutputStream sout = socket.getOutputStream();
        out = new DataOutputStream(sout);
        out.write(("TOUR_REQUEST").getBytes("UTF-8"));
        out.write(0x0a);
        out.write((tourId).getBytes("UTF-8"));
        out.write(0x0a);
        out.flush();
    }

    public void disconnect() throws IOException {
        OutputStream sout = socket.getOutputStream();
        out = new DataOutputStream(sout);
        out.write(("Disconnect").getBytes("UTF-8"));
        out.write(0x0a);
        out.flush();
        socket.close();
    }

    public void accept() throws IOException {
        if (socket.isConnected()) {
            OutputStream sout = socket.getOutputStream();
            out = new DataOutputStream(sout);
            out.write(("Accepted").getBytes("UTF-8"));
            out.write(0x0a);
            out.flush();
        }
    }

    public void reject() throws IOException {
        if (!socket.isConnected()) {
            OutputStream sout = socket.getOutputStream();
            out = new DataOutputStream(sout);
            out.write(("Rejected").getBytes("UTF-8"));
            out.write(0x0a);
            out.flush();
        }
    }

    public void close() throws IOException {
        socket.close();
    }

    public Command receive() throws IOException {
        String text = "";
        int b;
        StringBuffer stringBuffer = new StringBuffer();
        sin = socket.getInputStream();
        in = new DataInputStream(sin);
        while (true) {
            if ((b = in.read()) == 0x0a) {
                text = stringBuffer.toString().toUpperCase();
                if (text.equals("TOUR_REQUEST")) {
                    stringBuffer = new StringBuffer();
                    while (true) {
                        if ((b = in.read()) == 0x0a) {
                            break;
                        } else
                            stringBuffer.append((char) b);
                    }
                    return new TourCommand(stringBuffer.toString());
                } else {
                    if (((text.length() == 8) && (text.startsWith("ACCEPT") || text.startsWith("REJECT")) && text.endsWith("ED")) || (text.startsWith("DISCONNECT") && text.length() == 10)) {
                        if (text.endsWith("ED")) {
                            text = text.replace("ED", "");
                            return new Command(Command.CommandType.valueOf(text));
                        } else
                            return new Command(Command.CommandType.valueOf(text));
                    } else {
                        if (text.equals("TOUR_INFO")) {

                        } else {
                            if (text.equals("LOGIN")) {
                                stringBuffer = new StringBuffer();
                                while (true) {
                                    if ((b = in.read()) == 0x0a) {
                                        break;
                                    } else
                                        stringBuffer.append((char) b);
                                }
                                String part[] = stringBuffer.toString().split(" ");
                                return new LoginCommand(part[0], part[1]);
                            }
                        }
                    }
                }
                text = "";
                stringBuffer = new StringBuffer();
            } else {
                stringBuffer.append((char) b);
            }
        }
    }


    public boolean isOpen() {
        if (!socket.isConnected()) {
            return false;
        } else {
            if (socket.isClosed()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {

    }
}