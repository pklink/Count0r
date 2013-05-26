package net.einself.countr;

import android.text.Editable;
import android.text.TextWatcher;

import java.text.NumberFormat;
import java.util.Locale;


public class CounterTextChangeListener implements TextWatcher {

    private boolean formatIsInProcess = false;

    private Item item;


    public CounterTextChangeListener(Item item) {
        this.item = item;
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) { }


    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) { }


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

}
