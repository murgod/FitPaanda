package gopalareddy.huskyp.neu.edu.quickapplauncher;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent in = getIntent();
        int index = in.getIntExtra("gopalareddy.huskyp.neu.edu.INDEX", -1);

        if (index > -1){
            int pic = getImg(index);
            ImageView img = findViewById(R.id.imageView);
            scaleImg (img, pic);
        }

    }

    private void scaleImg(ImageView img, int pic) {

        Display screen = getWindowManager().getDefaultDisplay();
        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(),pic,options);

        int imgWidth = options.outWidth;
        int screenWidth = screen.getWidth();


        if (imgWidth > screenWidth){
            int ratio = Math.round((float)imgWidth / (float) screenWidth);
            options.inSampleSize = ratio;
        }

        options.inJustDecodeBounds = false;
        Bitmap scaledImg = BitmapFactory.decodeResource(getResources(),pic,options);
        img.setImageBitmap(scaledImg);
    }

    private int getImg (int index){
        switch (index){
            case 0: return R.drawable.burger;
            case 1: return R.drawable.chicken;
            case 2: return  R.drawable.frenchfries;
            case 3: return  R.drawable.onionrings;
            default: return -1;
        }

    }
}
