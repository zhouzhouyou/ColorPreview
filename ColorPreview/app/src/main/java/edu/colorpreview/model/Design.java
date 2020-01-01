package edu.colorpreview.model;

import lombok.Data;

@Data
public class Design {
    public Integer id;

    public String name;

    public String p;

    public String pl;

    public String pd;

    public String s;

    public String sl;

    public String sd;

    public String tp;

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
}

