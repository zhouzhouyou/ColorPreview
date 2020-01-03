package edu.colorpreview.view.designer;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import edu.colorpreview.model.Design;
import edu.colorpreview.util.BaseViewModel;

public class DesignerViewModel extends BaseViewModel {
    public MutableLiveData<Design> mDesign = new MutableLiveData<>();

    public DesignerViewModel(@NonNull Application application) {
        super(application);
    }

    public void setDesign(Design design) {
        mDesign.postValue(design);
    }
}
