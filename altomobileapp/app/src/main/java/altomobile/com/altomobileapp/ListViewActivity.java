package altomobile.com.altomobileapp;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ListViewActivity extends ListActivity {

    List<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    //Fill the list with the days of the current month
    {
        Calendar calendar = Calendar.getInstance();
        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        for ( int i = 1 ; i <= days ; i++) {
            listItems.add("Day: " + i);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        setListAdapter(adapter);
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {

        super.onListItemClick(l, v, position, id);
        Toast toast = Toast.makeText(getApplicationContext(), listItems.get(position), Toast.LENGTH_SHORT);
        toast.show();
    }
}
