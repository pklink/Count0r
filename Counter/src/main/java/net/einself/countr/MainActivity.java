package net.einself.countr;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    protected Item item;

    protected TextView counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Item erstellen
        item = new Item();

        // Counter setzen
        counter = (TextView) findViewById(R.id.txtCount);

        // Text fuer Counter setzen
        counter.setText(item.getCount().toString());

        // Click-Event fuer Increment-Button erstellen
        ((Button) findViewById(R.id.increment)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // MainActivity holen
                MainActivity activity = (MainActivity) view.getContext();

                // Counter erhoehen
                activity.getItem().increment();

                // Neuen Counterstand setzen
                activity.getCounter().setText(activity.getItem().getCount().toString());
            }
        });

        // Click-Event fuer Decrement-Button erstellen
        ((Button) findViewById(R.id.decrement)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // MainActivity holen
                MainActivity activity = (MainActivity) view.getContext();

                // Counter erniedrigen
                activity.getItem().decrement();

                // Neuen Counterstand setzen
                activity.getCounter().setText( activity.getItem().getCount().toString() );
            }
        });

        // Add onClick-Eventhandler for Reset-Button
        ((Button) findViewById(R.id.btnReset)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get MainActivity
                MainActivity ma = (MainActivity) view.getContext();

                // set countr to 0
                ma.getItem().setCount(0);

                // set new countr value to view
                ma.getCounter().setText( ma.getItem().getCount().toString() );
            }
        });
    }


    public Item getItem() {
        return item;
    }

    public TextView getCounter() {
        return counter;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
