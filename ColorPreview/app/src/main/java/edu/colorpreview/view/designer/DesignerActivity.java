package edu.colorpreview.view.designer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import edu.colorpreview.R;
import edu.colorpreview.model.Design;

public class DesignerActivity extends AppCompatActivity {
    private DesignerViewModel mDesignerViewModel;

    private Design mDesign;

    public static String ARG = ".DesignerActivity";
    public static String NAME = ARG + ".name";
    public static String ID = ARG + ".id";
    public static String P = ARG + ".p";
    public static String PL = ARG + ".pl";
    public static String PD = ARG + ".pd";
    public static String S = ARG + ".s";
    public static String SL = ARG + ".sl";
    public static String SD = ARG + ".sd";
    public static String TP = ARG + ".tp";
    public static String TS = ARG + ".ts";
    public static String UID = ARG + ".uid";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.designer_activity);
        mDesignerViewModel = ViewModelProviders.of(this).get(DesignerViewModel.class);
        Intent intent = getIntent();

        if (intent.hasExtra(ARG)) {
            mDesign = Design.fromBundle(intent.getBundleExtra(ARG));
            mDesignerViewModel.setDesign(mDesign);
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}
