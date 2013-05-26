package net.einself.countr;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends Activity {

    private Item item = new Item();

    private TextView counter;

    private Button plus;

    private Button minus;


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

        // set default value to UI-counter
        getCounter().setText(item.getCount().toString());

        // Click-Event fuer Increment-Button erstellen
        getPlus().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Counter erhoehen
                item.increment();

                // Neuen Counterstand setzen
                getCounter().setText(item.getCount().toString());
            }
        });


        // Click-Event fuer Decrement-Button erstellen
        getMinus().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Counter erniedrigen
                item.decrement();

                // Neuen Counterstand setzen
                getCounter().setText(item.getCount().toString());
            }
        });


        getCounter().addTextChangedListener(new TextWatcher() {

            private boolean formatIsInProcess = false;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // get value
                String value = editable.toString().replace(" ", "");

                if (value.length() == 0 || "-".equals(value)) {
                    value = "0";
                }

                if (!formatIsInProcess) {
                    // flag formatting process as active
                    formatIsInProcess = true;

                    // format value
                    value = NumberFormat.getInstance(Locale.US).format( Long.parseLong(value) ).replace(",", " ");

                    // set formatted value in editable
                    editable.clear();
                    editable.append(value);

                    // unflag formatting process as active
                    formatIsInProcess = false;
                }

                item.setCount(Long.parseLong(value.replace(" ", "")));
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

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
