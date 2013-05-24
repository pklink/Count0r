package net.einself.countr;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
                // Counter erhoehen
                item.increment();

                // Neuen Counterstand setzen
                counter.setText(item.getCount().toString());
            }
        });

        // Click-Event fuer Decrement-Button erstellen
        ((Button) findViewById(R.id.decrement)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Counter erniedrigen
                item.decrement();

                // Neuen Counterstand setzen
                counter.setText( item.getCount().toString() );
            }
        });

        // Add onClick-Eventhandler for Reset-Button
        ((Button) findViewById(R.id.btnReset)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create confirm-dialog
                new AlertDialog.Builder(view.getContext())
                        .setTitle("Reset counter to 0?")
                        .setPositiveButton(R.string.reset_confirm_positive, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                item.setCount(0);
                                counter.setText("0");
                            }
                        })
                        .setNegativeButton(R.string.reset_confirm_negative, null)
                        .show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
