package net.einself.countr;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.text.InputFilter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private Item item = new Item();

    private TextView counter;

    private Button plus;

    private Button minus;

    private int colourScheme;


    protected TextView getCounter() {
        if (counter == null) {
            counter = (TextView) findViewById(R.id.etxt_count);
        }

        return counter;
    }


    protected Button getPlus() {
        if (plus == null) {
            plus = (Button) findViewById(R.id.btn_plus);
        }

        return plus;
    }


    protected Button getMinus() {
        if (minus == null) {
            minus = (Button) findViewById(R.id.btn_minus);
        }

        return minus;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get shared preferences
        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);

        // set saved counter to item
        item.setCount(preferences.getLong(getString(R.string.pref_counter), 0l));

        // set value to UI-counter
        getCounter().setText(item.getCount().toString());

        // set colour scheme
        setColourScheme(preferences.getInt(getString(R.string.pref_colour_scheme), R.id.action_colour_scheme_blue));

        // set listender for "+"-button
        getPlus().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Counter erhoehen
                item.increment();

                // Neuen Counterstand setzen
                getCounter().setText(item.getCount().toString());
            }
        });

        // set listener for "-"-button
        getMinus().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Counter erniedrigen
                item.decrement();

                // Neuen Counterstand setzen
                getCounter().setText(item.getCount().toString());
            }
        });

        // set filter for UI-counter field
        getCounter().setFilters(new InputFilter[]{ new CounterFilter() });

        // set listener for UI-Counter field
        getCounter().addTextChangedListener(new CounterTextChangeListener(item));
    }


    protected void setColourScheme(int id) {
        colourScheme        = id;
        ColourScheme scheme = ColourScheme.Factory.create(id);

        findViewById(R.id.etxt_count).setBackgroundColor(scheme.getCounter());
        findViewById(R.id.btn_plus).setBackgroundColor(scheme.getPlusButton());
        findViewById(R.id.btn_minus).setBackgroundColor(scheme.getMinusButton());
        ((TextView) findViewById(R.id.etxt_count)).setTextColor(scheme.getText());
        ((TextView) findViewById(R.id.btn_plus)).setTextColor(scheme.getText());
        ((TextView) findViewById(R.id.btn_minus)).setTextColor(scheme.getText());
    }


    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences preferences   = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        // counter
        editor.putLong(getString(R.string.pref_counter), item.getCount());

        // colour scheme
        editor.putInt(getString(R.string.pref_colour_scheme), colourScheme);

        // save
        editor.commit();
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
                this.item.setCount(0l);
                getCounter().setText("0");
                return false;

            // show plus- and minus-button
            case R.id.action_visibility_both:
                getPlus().setVisibility(View.VISIBLE);
                getMinus().setVisibility(View.VISIBLE);
                return false;

            // show plus-button only
            case R.id.action_visibility_only_plus:
                getPlus().setVisibility(View.VISIBLE);
                getMinus().setVisibility(View.GONE);
                return false;

            // show minus-button only
            case R.id.action_visibility_only_minus:
                getPlus().setVisibility(View.GONE);
                getMinus().setVisibility(View.VISIBLE);
                return false;

            // change color scheme
            case R.id.action_colour_scheme_blue:
            case R.id.action_colour_scheme_green:
            case R.id.action_colour_scheme_red:
            case R.id.action_colour_scheme_orange:
            case R.id.action_colour_scheme_purple:
                setColourScheme( item.getItemId() );
                return false;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
