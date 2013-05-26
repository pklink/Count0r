package net.einself.countr;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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


        getCounter().setFilters(new InputFilter[]{
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                        if (charSequence.length() == 0 || spanned.length() == 0) {
                            return null;
                        }

                        // everythin on position 0 is invalid if the first char of existing value '-'
                        if (spanned.charAt(0) == '-' && i3 == 0) {
                            return "";
                        }

                        // if first char of insertion is '-' and the target is not position 0 => invalid
                        if (charSequence.charAt(0) == '-' && i3 != 0) {
                            return "";
                        }

                        // create StringBuilder for the new CharSequence
                        StringBuilder insertion = new StringBuilder(32);

                        // add first char
                        insertion.append(charSequence.charAt(0));

                        // remove '-' in the rest of the CharSequence
                        if (charSequence.length() > 1) {
                            String substring = charSequence.toString().substring(1);
                            substring        = substring.replace("-", "");

                            insertion.append(substring);
                        }

                        return insertion.toString();
                    }
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

                    try {
                        // parse value to long
                        long longValue = Long.parseLong(value);

                        // format value
                        value = NumberFormat.getInstance(Locale.US).format( longValue ).replace(",", " ");

                        // set formatted value in editable
                        editable.clear();
                        editable.append(value);
                    } catch (NumberFormatException e) {
                        formatIsInProcess = false;

                        // save current value of counter
                        long counterValue = item.getCount();

                        // show message
                        Toast.makeText(getBaseContext(), getString(R.string.error_invalid_long), Toast.LENGTH_SHORT).show();

                        // zahl ist zu gross oder so
                        editable.clear();
                        editable.append( String.valueOf(counterValue) );
                        return;
                    }

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
