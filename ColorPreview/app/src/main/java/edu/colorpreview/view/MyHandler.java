package edu.colorpreview.view;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import edu.colorpreview.model.Design;
import edu.colorpreview.util.XmlBuilder;

public class MyHandler {
    public void navToDesignActivity(View view, Design design) {

    }

    public void shareClicked(View view, Design design) {
        XmlBuilder.shareXML(design);
    }

    public void bookmarkClicked(View view, Design design) {

    }
}
