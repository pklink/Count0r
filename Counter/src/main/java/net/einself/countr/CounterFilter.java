package net.einself.countr;

import android.text.InputFilter;
import android.text.Spanned;

public class CounterFilter implements InputFilter {

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

