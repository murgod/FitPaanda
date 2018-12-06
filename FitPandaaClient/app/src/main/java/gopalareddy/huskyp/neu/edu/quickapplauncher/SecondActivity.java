package gopalareddy.huskyp.neu.edu.quickapplauncher;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import gopalareddy.huskyp.neu.edu.quickapplauncher.Model.Order;

public class SecondActivity extends AppCompatActivity {
    int mRequestCode = 0;
    Button submit;
    ListView myListView;

    int numBurgers;
    int numChickens;
    int numFrenchfries;
    int numOnionrings;

    String [] items;
    String [] prices;
    String [] descriptions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Resources res = getResources();
        myListView = findViewById(R.id.myListView);
        items = res.getStringArray(R.array.items);
        submit = findViewById(R.id.SUBMIT);


        /* myListView.setAdapter(new ArrayAdapter<String>(this,R.layout.my_listview_detail,items)); */
        prices = res.getStringArray(R.array.prices);
        descriptions = res.getStringArray(R.array.descriptions);

        ItemAdapter itemAdapter = new ItemAdapter(this,items, prices,descriptions);
        myListView.setAdapter(itemAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(i == 5) {
                    Intent showActivityDetail = new Intent(getApplicationContext(), DetailActivity.class);
                    showActivityDetail.putExtra("gopalareddy.huskyp.neu.edu.INDEX", i);
                    startActivity(showActivityDetail);
                }
                else if(i ==0){
                    mRequestCode = 100;
                    Intent showActivityDetail = new Intent(getApplicationContext(), Burger.class);
                    showActivityDetail.putExtra("gopalareddy.huskyp.neu.edu.INDEX", i);
                    //startActivity(showActivityDetail);
                    startActivityForResult(showActivityDetail, mRequestCode);


                }
                else if(i ==1){
                    mRequestCode = 200;
                    Intent showActivityDetail = new Intent(getApplicationContext(), Chicken.class);
                    showActivityDetail.putExtra("gopalareddy.huskyp.neu.edu.INDEX", i);
                    //startActivity(showActivityDetail);
                    startActivityForResult(showActivityDetail, mRequestCode);

                }
                else if(i ==2){
                    mRequestCode = 300;
                    Intent showActivityDetail = new Intent(getApplicationContext(), Frenchfries.class);
                    showActivityDetail.putExtra("gopalareddy.huskyp.neu.edu.INDEX", i);
                    //startActivity(showActivityDetail);
                    startActivityForResult(showActivityDetail, mRequestCode);

                }
                else if(i ==3){
                    mRequestCode = 400;
                    Intent showActivityDetail = new Intent(getApplicationContext(),  OnionRings.class);
                    showActivityDetail.putExtra("gopalareddy.huskyp.neu.edu.INDEX", i);
                    //startActivity(showActivityDetail);
                    startActivityForResult(showActivityDetail, mRequestCode);

                }


            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(SecondActivity.this, "Submitting the order", Toast.LENGTH_SHORT).show();
                Order newOrder = new Order();
                newOrder.setBurgerCount(numBurgers);
                newOrder.setChickensCount(numChickens);
                newOrder.setFriesCount(numFrenchfries);
                newOrder.setRingsCount(numOnionrings);

                Intent Intent = new Intent(getApplicationContext(), ThirdActivity.class);

                Intent.putExtra("parcel_data", newOrder);
                startActivity(Intent);

//                Intent startIntent = new Intent(getApplicationContext(), SecondActivity.class);
//
//                //show how to pass information to another activity
//                //startIntent.putExtra("gopalareddy.huskyp.neu.edu.quickapplauncher.SOMETHING", "HELLO WORLD!!!!!!");
//                startActivity(startIntent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == mRequestCode && resultCode == RESULT_OK){
            String editTextString = data.getStringExtra("editText");
            Toast.makeText(SecondActivity.this, "Value from cart  " +editTextString, Toast.LENGTH_SHORT).show();
            int result = Integer.parseInt(editTextString);
            Toast.makeText(SecondActivity.this, "Converted value  " +result, Toast.LENGTH_SHORT).show();
            if(mRequestCode == 100){
                numBurgers = numBurgers + result;
                Toast.makeText(SecondActivity.this, "Total burgers  " +numBurgers, Toast.LENGTH_SHORT).show();
            }
            else if(mRequestCode == 200){
                numChickens = numChickens + result;
                Toast.makeText(SecondActivity.this, "Total chickens  " +numChickens, Toast.LENGTH_SHORT).show();
            }
            else if(mRequestCode == 300){
                numFrenchfries = numFrenchfries + result;
                Toast.makeText(SecondActivity.this, "Total FrenchFries  " +numFrenchfries, Toast.LENGTH_SHORT).show();
            }
            else if(mRequestCode == 400){
                numOnionrings = numOnionrings + result;
                Toast.makeText(SecondActivity.this, "Total OnionRings  " +numOnionrings, Toast.LENGTH_SHORT).show();
            }
        }

    }
}
