package edu.colorpreview.model;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import lombok.Data;

import static edu.colorpreview.view.designer.DesignerActivity.*;

@Data
public class Design {
    public Integer id;

    public String name;

    /**
     * Primary Color
     */
    public String p;

    /**
     * Primary Lighter
     */
    public String pl;

    /**
     * Primary Darker
     */
    public String pd;

    /**
     * Secondary
     */
    public String s;

    /**
     * Secondary Lighter
     */
    public String sl;

    /**
     * Secondary Darker
     */
    public String sd;

    /**
     * Text on Primary
     */
    public String tp;

    /**
     * Text on Secondary
     */
    public String ts;

    public Integer uid;

    public Design(Integer id, String name, String p, String pl, String pd, String s, String sl, String sd, String tp, String ts, Integer uid) {
        this.id = id;
        this.name = name;
        this.p = p;
        this.pl = pl;
        this.pd = pd;
        this.s = s;
        this.sl = sl;
        this.sd = sd;
        this.tp = tp;
        this.ts = ts;
        this.uid = uid;
    }

    @BindingAdapter("android:background")
    public static void setBackground(View view, String color) {
        if (color == null) color = "#00000000";
        view.setBackgroundColor(Color.parseColor(color));
    }

    @BindingAdapter("android:textColor")
    public static void setTextColor(View view, String color) {
        if (color == null) color = "#000000";
        ((Button) view).setTextColor(Color.parseColor(color));
    }

    @InverseBindingAdapter(attribute = "android:background")
    public static String fromBackground(View view) {
        int color = ((ColorDrawable) view.getBackground()).getColor();
        return "#" +
                Integer.toHexString(Color.red(color)) +
                Integer.toHexString(Color.green(color)) +
                Integer.toHexString(Color.blue(color));
    }

    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(ID, id);
        bundle.putString(NAME, name);
        bundle.putString(P, p);
        bundle.putString(PL, pl);
        bundle.putString(PD, pd);
        bundle.putString(S, s);
        bundle.putString(SL, sl);
        bundle.putString(SD, sd);
        bundle.putString(TP, tp);
        bundle.putString(TS, ts);
        bundle.putInt(UID, uid);
        return bundle;
    }

    public static Design fromBundle(Bundle bundle) {
        return new Design(
                bundle.getInt(ID),
                bundle.getString(NAME),
                bundle.getString(P),
                bundle.getString(PL),
                bundle.getString(PD),
                bundle.getString(S),
                bundle.getString(SL),
                bundle.getString(SD),
                bundle.getString(TP),
                bundle.getString(TS),
                bundle.getInt(UID)
        );
    }
}

