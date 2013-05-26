package net.einself.countr;

import android.view.View;
import android.widget.Button;


public class ButtonStatus {

    final static int STATUS_PLUS_ONLY = 1;

    final static int STATUS_MINUS_ONLY = 2;

    final static int STATUS_BOTH = 3;

    private Button plus;

    private Button minus;

    private int status;


    public ButtonStatus(Button plus, Button minus) {
        this.plus  = plus;
        this.minus = minus;
    }


    public void showPlusOnly() {
        status = STATUS_PLUS_ONLY;
        plus.setVisibility(View.VISIBLE);
        minus.setVisibility(View.GONE);
    }


    public void showMinusOnly() {
        status = STATUS_MINUS_ONLY;
        plus.setVisibility(View.GONE);
        minus.setVisibility(View.VISIBLE);
    }


    public void showBoth() {
        status = STATUS_BOTH;
        plus.setVisibility(View.VISIBLE);
        minus.setVisibility(View.VISIBLE);
    }


    public void setStatus(int status) {
        switch (status) {
            case STATUS_PLUS_ONLY:
                showPlusOnly();
                break;
            case STATUS_MINUS_ONLY:
                showMinusOnly();
                break;
            case STATUS_BOTH:
            default:
                showBoth();
        }
    }

    public int getStatus() {
        return status;
    }

}
