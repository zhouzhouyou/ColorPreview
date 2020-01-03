package edu.colorpreview.view;

import android.content.Intent;
import android.view.View;

import edu.colorpreview.model.Design;
import edu.colorpreview.util.XmlBuilder;
import edu.colorpreview.view.designer.DesignerActivity;

public class MyHandler {
    public void navToDesignActivity(View view, Design design) {
        Intent intent = new Intent(view.getContext(), DesignerActivity.class);
        intent.putExtra(DesignerActivity.ARG, design.getBundle());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        view.getContext().startActivity(intent);
    }

    public void shareClicked(View view, Design design) {
        XmlBuilder.shareXML(design);
    }

    public void bookmarkClicked(View view, Design design) {

    }

    public void delete(View view, Design design) {

    }
}
