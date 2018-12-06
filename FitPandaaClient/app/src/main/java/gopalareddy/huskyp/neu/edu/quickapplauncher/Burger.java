package gopalareddy.huskyp.neu.edu.quickapplauncher;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

public class Burger extends AppCompatActivity {
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;
    Intent intent = new Intent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burger);


        btnCart = (FloatingActionButton) findViewById(R.id.btnCart);
        numberButton = findViewById(R.id.number_button);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Burger.this, "Preparing Cart with " +numberButton.getNumber() +" burgers", Toast.LENGTH_SHORT).show();

                intent.putExtra("editText", numberButton.getNumber());
                setResult(RESULT_OK, intent);
                finish(); // Once you hit cart button the activity goes back to previous activity.
            }
        });
    }

    @Override
    public void onBackPressed() {
//        intent.putExtra("editText", 0);
//        setResult(RESULT_OK, intent);
        finish();
    }
}
