package gopalareddy.huskyp.neu.edu.quickapplauncher;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button order = findViewById(R.id.order);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent startIntent = new Intent(getApplicationContext(), SecondActivity.class);
                    startActivity(startIntent);
            }
        });


        Button googleBtn = findViewById(R.id.googleBtn);
        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent startIntent = new Intent(getApplicationContext(), AboutUsActivity.class);
                startActivity(startIntent);

            }
              });
    }
}
