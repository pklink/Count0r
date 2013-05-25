package net.einself.countr;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    protected Item item;

    protected TextView counter;

    private Button plus;

    private Button minus;


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

        // set buttons
        plus  = (Button) findViewById(R.id.btn_plus);
        minus = (Button) findViewById(R.id.btn_minus);

        // Click-Event fuer Increment-Button erstellen
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Counter erhoehen
                item.increment();

                // Neuen Counterstand setzen
                counter.setText(item.getCount().toString());
            }
        });

        // Click-Event fuer Decrement-Button erstellen
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Counter erniedrigen
                item.decrement();

                // Neuen Counterstand setzen
                counter.setText( item.getCount().toString() );
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setChecked(true);

        switch (item.getItemId()) {
            // reset counter
            case R.id.action_reset_counter:
                this.item.setCount(0);
                counter.setText("0");
                return false;

            // show plus- and minus-button
            case R.id.action_visibility_both:
                plus.setVisibility(View.VISIBLE);
                minus.setVisibility(View.VISIBLE);
                return false;

            // show plus-button only
            case R.id.action_visibility_only_plus:
                plus.setVisibility(View.VISIBLE);
                minus.setVisibility(View.GONE);
                return false;

            // show minus-button only
            case R.id.action_visibility_only_minus:
                plus.setVisibility(View.GONE);
                minus.setVisibility(View.VISIBLE);
                return false;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
