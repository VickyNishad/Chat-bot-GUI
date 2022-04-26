import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class Client extends JFrame {
    Socket socket;
    BufferedReader br;
    PrintWriter out;
    // component
    private JLabel heading = new JLabel("Client Area");
    private JTextArea messageArea = new JTextArea();
    private JTextField messageInput = new JTextField();
    private Font font = new Font("Roboto", Font.PLAIN, 20);

    public Client() {
        try {
            // System.out.println("sending request to server");
            // socket = new Socket("127.0.0.1", 7777);
            // System.out.println("server is ready to accept connection");
            // System.out.println("connection done.");

            // br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // out = new PrintWriter(socket.getOutputStream());
            createGUI();
            handleEvents();
            // startReading();
            // startWriting();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private void handleEvents()

    {
        messageInput.addKeyListener(new KeyListener(){
            
        });
    }
    // if(e.getKeycode() == 10)
    // {
    // String content = messageInput.getText();
    // messageArea.append("Me :"+content+"\n");
    // out.println(content);
    // out.flush();
    // messageInput.setText("");
    // messageInput.requestFocus();
    // }
    // messageInput.addKeyListener(l);

    private void createGUI() {
        // gui
        this.setTitle("Client message[End]");
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // coding for component
        heading.setFont(font);
        messageArea.setFont(font);
        messageInput.setFont(font);
        heading.setHorizontalTextPosition(SwingConstants.CENTER);
        heading.setVerticalTextPosition(SwingConstants.BOTTOM);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        messageInput.setHorizontalAlignment(SwingConstants.CENTER);
        // layout of frame
        // this.setLayout(new BorderLayout());
        // this.setLayout(new Border() {
            
        // });

        // adding component
        // this.add(heading, Border.NORTH);
        // this.add(messageArea, Border.CENTER);
        // this.add(messageInput, Border.SOUTH);

        //

        this.setVisible(true);

    }

   

    private void startReading() {
        Runnable runnable1 = () -> {
            System.out.println("reader started...");
            try {
                while (true) {
                    String msg;
                    msg = br.readLine();
                    if (msg.equals("exite")) {
                        System.out.println("Server terminated the chat");
                        JOptionPane.showMessageDialog(this, "server terminated the chat");
                        messageInput.setEnabled(false);
                        socket.close();
                        break;
                    }
                    // System.out.println("Server : " + msg);
                    messageArea.append("Server : " + msg + "\n");

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
                    var content = bufferedReader.readLine();
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
        System.out.println("this is client...");
        new Client();
    }
}
