package androidclient.example.com.FitPandaaServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

import androidclient.example.com.FitPandaaServer.Model.Inventory;

public class MainActivity extends Activity {

    TextView info, infoip, msg, inventory;
    String message = "";
    ServerSocket serverSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        info =  findViewById(R.id.info);
        infoip =  findViewById(R.id.infoip);
        msg =  findViewById(R.id.msg);
        inventory = findViewById(R.id.inventory);

        inventory.setText("Reading file Inventory.txt");
        //This is my code
        String text = "";
        InputStream is = this.getResources().openRawResource(R.raw.inventory);
        BufferedReader sc = new BufferedReader(new InputStreamReader(is));
        Inventory inv = new Inventory();
        try {
            String line;
            int count = 0;
            while ((line = sc.readLine()) != null) {
                //info.setText(line);
                count = count + 1;
                String[] details = line.split("\\|");
                String Item = details[0];
                String Quantity = details[1];
                String Qty = Quantity;
                String Price = details[2];
                String Prc = Price;
                //info.setText(Item);

                if(count == 1){
                    inv.setBurgersQty(Qty);
                    inv.setBurgersPrice(Prc);
                }
                else if(count == 2){
                    inv.setChickensQty(Qty);
                    inv.setChickensPrice(Prc);
                }
                else if(count == 3){
                    inv.setFrenchfriesQty(Qty);
                    inv.setFrenchfriesPrice(Prc);
                }
                else if(count == 4){
                    inv.setOnionringsQty(Qty);
                    inv.setOnionringsPrice(Prc);
                }
                inventory.setText(inv.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        infoip.setText(getIpAddress());
        Thread socketServerThread = new Thread(new SocketServerThread());
        socketServerThread.start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private class SocketServerThread extends Thread {

        static final int SocketServerPORT = 8080;
        int count = 0;

        @Override
        public void run() {
            try {
                serverSocket = new ServerSocket(SocketServerPORT);

                MainActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        info.setText("I'm waiting here: " + serverSocket.getLocalPort());

                    }
                });

                while (true) {
                    Socket socket = serverSocket.accept();
                    count++;
                    message += "#" + count + " from " + socket.getInetAddress()
                            + ":" + socket.getPort() + "\n";

                    MainActivity.this.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            msg.setText(message);
                        }
                    });

                    SocketServerReplyThread socketServerReplyThread = new SocketServerReplyThread(
                            socket, (char) count);
                    socketServerReplyThread.run();

                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    private class SocketServerReplyThread extends Thread {

        private Socket hostThreadSocket;
        char cnt;

        SocketServerReplyThread(Socket socket, char c) {
            hostThreadSocket = socket;
            cnt = c;
        }

        @Override
        public void run() {
            OutputStream outputStream;
            String msgReply = "Hello from Android, I am Paavan" + cnt;

            try {
                outputStream = hostThreadSocket.getOutputStream();
                PrintStream printStream = new PrintStream(outputStream);
                printStream.print(msgReply);
                printStream.close();

                message += "replayed: " + msgReply + "\n";

                MainActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        msg.setText(message);
                    }
                });

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                message += "Something wrong! " + e.toString() + "\n";
            }

            MainActivity.this.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    msg.setText(message);
                }
            });
        }

    }

    private String getIpAddress() {
        String ip = "";
        try {
            Enumeration<NetworkInterface> enumNetworkInterfaces = NetworkInterface
                    .getNetworkInterfaces();
            while (enumNetworkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = enumNetworkInterfaces
                        .nextElement();
                Enumeration<InetAddress> enumInetAddress = networkInterface
                        .getInetAddresses();
                while (enumInetAddress.hasMoreElements()) {
                    InetAddress inetAddress = enumInetAddress.nextElement();

                    if (inetAddress.isSiteLocalAddress()) {
                        ip += "SiteLocalAddress: "
                                + inetAddress.getHostAddress() + "\n";
                    }

                }
            }

        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            ip += "Something Wrong! " + e.toString() + "\n";
        }

        return ip;
    }
}