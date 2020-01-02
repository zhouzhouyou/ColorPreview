package edu.colorpreview.model;

import android.graphics.Color;
import android.view.View;

import androidx.databinding.BindingAdapter;
import lombok.Data;

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
        view.setBackgroundColor(Color.parseColor(color));
    }
}

