package net.einself.countr;

import android.graphics.Color;

/**
 * @author Pierre Klink <pierre@einself.net>
 */
public class ColourScheme {

    private int plusButton;

    private int minusButton;

    private int counter;

    private int text;


    public int getPlusButton() {
        return plusButton;
    }

    public void setPlusButton(int plusButton) {
        this.plusButton = plusButton;
    }

    public int getMinusButton() {
        return minusButton;
    }

    public void setMinusButton(int minusButton) {
        this.minusButton = minusButton;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getText() {
        return text;
    }

    public void setText(int text) {
        this.text = text;
    }

    public static class Factory {

        public static ColourScheme create(int id) {
            ColourScheme scheme = new ColourScheme();

            switch (id) {
                case R.id.action_colour_scheme_red:
                    scheme.setPlusButton( Color.parseColor("#d30a0a") );
                    scheme.setMinusButton( Color.parseColor("#ff4444") );
                    scheme.setCounter( Color.parseColor("#ffe4e4") );
                    scheme.setText(Color.parseColor("#000000"));
                    break;

                case R.id.action_colour_scheme_green:
                    scheme.setPlusButton( Color.parseColor("#669900") );
                    scheme.setMinusButton( Color.parseColor("#8abd00") );
                    scheme.setCounter( Color.parseColor("#f0f8db") );
                    scheme.setText(Color.parseColor("#000000"));
                    break;

                case R.id.action_colour_scheme_orange:
                    scheme.setPlusButton( Color.parseColor("#ff9105") );
                    scheme.setMinusButton( Color.parseColor("#ffc641") );
                    scheme.setCounter( Color.parseColor("#fff6df") );
                    scheme.setText(Color.parseColor("#000000"));
                    break;

                case R.id.action_colour_scheme_purple:
                    scheme.setPlusButton( Color.parseColor("#a041d0") );
                    scheme.setMinusButton( Color.parseColor("#c182e0") );
                    scheme.setCounter( Color.parseColor("#f5eafa") );
                    scheme.setText(Color.parseColor("#000000"));
                    break;

                case R.id.action_colour_scheme_blue:
                default:
                    scheme.setPlusButton( Color.parseColor("#0099cc") );
                    scheme.setMinusButton( Color.parseColor("#33b5e5") );
                    scheme.setCounter( Color.parseColor("#e2f4fb") );
                    scheme.setText(Color.parseColor("#000000"));
            }

            return scheme;
        }

    }

}
