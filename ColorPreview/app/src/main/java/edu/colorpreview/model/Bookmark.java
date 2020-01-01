package edu.colorpreview.model;

import lombok.Data;

@Data
public class Bookmark {
    public Integer uid;

    public Integer did;

    public Bookmark(Integer uid, Integer did) {
        this.uid = uid;
        this.did = did;
    }
}
