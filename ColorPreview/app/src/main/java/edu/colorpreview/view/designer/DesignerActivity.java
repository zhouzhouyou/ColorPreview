package edu.colorpreview.view.designer;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import edu.colorpreview.R;

public class DesignerActivity extends AppCompatActivity {
    private DesignerViewModel mDesignerViewModel;

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    private void initDesignerViewModel() {
        mDesignerViewModel = ViewModelProviders.of(this).get(DesignerViewModel.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.designer_activity);
        initDesignerViewModel();
    }
}
