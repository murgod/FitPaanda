package gopalareddy.huskyp.neu.edu.quickapplauncher;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import gopalareddy.huskyp.neu.edu.quickapplauncher.Model.Order;

public class ThirdActivity extends AppCompatActivity {
    TextView msg;
    Button ConfirmOrder;

    TextView textResponse;
    EditText editTextAddress, editTextPort;
    Button buttonConnect, buttonClear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Order Order = (Order) getIntent().getParcelableExtra("parcel_data");
        Toast.makeText(ThirdActivity.this, "OrderDetails" +Order.getBurgerCount(), Toast.LENGTH_SHORT).show();


        msg =  findViewById(R.id.msg);
        msg.setText(Order.toString());
        ConfirmOrder = findViewById(R.id.ConfirmOrder);

        editTextAddress = findViewById(R.id.address);
        editTextPort = findViewById(R.id.port);
        buttonConnect = findViewById(R.id.connect);
        buttonClear = findViewById(R.id.clear);
        textResponse = findViewById(R.id.response);

        buttonConnect.setOnClickListener(buttonConnectOnClickListener);

        buttonClear.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                textResponse.setText("");
            }});

    }
    View.OnClickListener buttonConnectOnClickListener =
            new View.OnClickListener(){

                @Override
                public void onClick(View arg0) {
                    MyClientTask myClientTask = new MyClientTask(
                            editTextAddress.getText().toString(),
                            Integer.parseInt(editTextPort.getText().toString()));
                    myClientTask.execute();
                }};

    public class MyClientTask extends AsyncTask<Void, Void, Void> {

        String dstAddress;
        int dstPort;
        String response = "";

        MyClientTask(String addr, int port) {
            dstAddress = addr;
            dstPort = port;
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            Socket socket = null;

            try {
                socket = new Socket(dstAddress, dstPort);

                ByteArrayOutputStream byteArrayOutputStream =
                        new ByteArrayOutputStream(1024);
                byte[] buffer = new byte[1024];

                int bytesRead;
                InputStream inputStream = socket.getInputStream();

                /*
                 * notice:
                 * inputStream.read() will block if no data return
                 */
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                    response += byteArrayOutputStream.toString("UTF-8");

                    //socket.sendUrgentData();
                }

            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                response = "UnknownHostException: " + e.toString();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                response = "IOException: " + e.toString();
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            textResponse.setText(response);
            super.onPostExecute(result);
        }
    }
}
