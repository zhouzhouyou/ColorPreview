package edu.colorpreview.view.designer;

import android.app.Application;
import android.graphics.Color;

import androidx.annotation.NonNull;
import edu.colorpreview.util.BaseViewModel;

public class DesignerViewModel extends BaseViewModel {
    private Color primaryColor;
    private Color primaryLighter;
    private Color primaryDarker;
    private Color secondaryColor;
    private Color secondaryLighter;
    private Color secondaryDarker;
    private Color textOnP;
    private Color textOnS;

    public DesignerViewModel(@NonNull Application application) {
        super(application);
    }
}
