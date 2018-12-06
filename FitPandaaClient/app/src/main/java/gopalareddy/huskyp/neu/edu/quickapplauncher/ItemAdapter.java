package gopalareddy.huskyp.neu.edu.quickapplauncher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    String [] items;
    String [] prices;
    String [] descriptions;

    public ItemAdapter (Context c, String [] i, String [] p, String [] d){

        items = i;
        prices = p;
        descriptions = d;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int i) {
        return items[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = mInflater.inflate(R.layout.activity_list_view,null);
        TextView nameTextView = v.findViewById(R.id.nameTextView);
        TextView descriptionTextView = v.findViewById(R.id.descriptionTextView);
        TextView priceTextView = v.findViewById(R.id.priceTextView);

        String name = items[i];
        String desc = descriptions[i];
        String cost = prices[i];

        nameTextView.setText(name);
        descriptionTextView.setText(desc);
        priceTextView.setText(cost);
        return v;
    }
}
