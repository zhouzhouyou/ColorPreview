package edu.colorpreview.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import java.io.File;

import androidx.core.content.FileProvider;
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
        Context context = view.getContext();
        File file = XmlBuilder.generateXML(context, design);
        Intent intent = new Intent(Intent.ACTION_SEND);
        Uri uri = FileProvider.getUriForFile(context, "edu.colorpreview.fileprovider", file);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setType("text/plain");
        context.startActivity(intent);
    }

    public void bookmarkClicked(View view, Design design) {

    }
}
