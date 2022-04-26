import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    ServerSocket serverSocket;
    Socket socket;
    BufferedReader br;
    PrintWriter out;

    // contructor
    public Server() {
        try {
            serverSocket = new ServerSocket(7777);
            System.out.println("server is ready to accept connection");
            System.out.println("waiting...");
            socket = serverSocket.accept();

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            startReading();
            startWriting();
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    private void startReading() {
        Runnable runnable1 = () -> {
            System.out.println("reader started...");
            try {
                while (true) {
                    String msg;

                    msg = br.readLine();
                    if (msg.equals("exite")) {
                        System.out.println("Client terminated the chat");
                        socket.close();
                        break;
                    }
                    System.out.println("Client : " + msg);

                }
            } catch (IOException e) {

                e.printStackTrace();
            }

        };
        new Thread(runnable1).start();
    }

    public void startWriting() {

        Runnable runnable2 = () -> {
            System.out.println("writer started...");
            try {
                while (true) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                    String content = bufferedReader.readLine();

                    out.println(content);
                    out.flush();
                    if (content.equals(content)) {
                        socket.close();
                        break;
                    }
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        };
        new Thread(runnable2).start();
    }

    public static void main(String[] args) {
        System.out.println("this is server....");
        new Server();
    }
}