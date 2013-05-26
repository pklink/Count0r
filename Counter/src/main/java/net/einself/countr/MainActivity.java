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

    /**
     * the representation of the counter
     */
    private Item item = new Item();

    /**
     * the representation of the counter
     */
    private TextView counter;

    /**
     * the "+"-button
     */
    private Button plus;

    /**
     * the "-"-button
     */
    private Button minus;

    /**
     * the active colour scheme
     */
    private int colourScheme;


    private ButtonStatus buttonStatus;


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

        // set colour scheme
        setColourScheme(preferences.getInt(getString(R.string.pref_colour_scheme), R.id.action_colour_scheme_blue));

        // create ButtonStatus
        buttonStatus = new ButtonStatus(getPlus(), getMinus());
        buttonStatus.setStatus(preferences.getInt(getString(R.string.pref_button_visibility), ButtonStatus.STATUS_BOTH));

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

        // set value to UI-counter
        getCounter().setText(item.getCount().toString());
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

        // button visibility
        editor.putInt(getString(R.string.pref_button_visibility), buttonStatus.getStatus());

        // save
        editor.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        // set selected item for button-visibility
        switch (buttonStatus.getStatus()) {
            case ButtonStatus.STATUS_PLUS_ONLY:
                menu.findItem(R.id.action_visibility_only_plus).setChecked(true);
                break;

            case ButtonStatus.STATUS_MINUS_ONLY:
                menu.findItem(R.id.action_visibility_only_minus).setChecked(true);
                break;

            case ButtonStatus.STATUS_BOTH:
            default:
                menu.findItem(R.id.action_visibility_both).setChecked(true);
        }

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
                buttonStatus.showBoth();
                return false;

            // show plus-button only
            case R.id.action_visibility_only_plus:
                buttonStatus.showPlusOnly();
                return false;

            // show minus-button only
            case R.id.action_visibility_only_minus:
                buttonStatus.showMinusOnly();
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
