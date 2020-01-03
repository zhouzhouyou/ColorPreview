package edu.colorpreview.view.designer;

import android.app.AlertDialog;
import android.app.Application;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import edu.colorpreview.R;
import edu.colorpreview.model.Design;
import edu.colorpreview.util.BaseViewModel;
import edu.colorpreview.util.ColorUtil;
import edu.colorpreview.view.user.UserStatus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DesignerViewModel extends BaseViewModel {
    public MutableLiveData<Design> mDesign = new MutableLiveData<>();
    public MutableLiveData<String> message = new MutableLiveData<>();
    public ColorHandler mColorHandler = new ColorHandler();

    public DesignerViewModel(@NonNull Application application) {
        super(application);
    }

    public void setDesign(Design design) {
        mDesign.postValue(design);
    }

    public class ColorHandler {
        public void changeP(View view) {
            ColorPickerDialogBuilder
                    .with(view.getContext())
                    .setTitle(R.string.getPrimaryColor)
                    .initialColor(Color.parseColor("#AA0000"))
                    .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                    .density(12)
                    .setPositiveButton(R.string.confirm, (dialog, selectedColor, allColors) -> {
                        Design design = mDesign.getValue();
                        if (design == null) design = new Design();
                        design.p = "#" + Integer.toHexString(selectedColor).substring(2);
                        design.pd = "#" + Integer.toHexString(ColorUtil.darken(selectedColor)).substring(2);
                        design.pl = "#" + Integer.toHexString(ColorUtil.lighten(selectedColor)).substring(2);
                        setDesign(design);
                    })
                    .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {})
                    .build()
                    .show();
        }

        public void changeS(View view) {
            ColorPickerDialogBuilder
                    .with(view.getContext())
                    .setTitle(R.string.getSecondaryColor)
                    .initialColor(Color.parseColor("#AA0000"))
                    .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                    .density(12)
                    .setPositiveButton(R.string.confirm, (dialog, selectedColor, allColors) -> {
                        Design design = mDesign.getValue();
                        if (design == null) design = new Design();
                        design.s = "#" + Integer.toHexString(selectedColor).substring(2);
                        design.sd = "#" + Integer.toHexString(ColorUtil.darken(selectedColor)).substring(2);
                        design.sl = "#" + Integer.toHexString(ColorUtil.lighten(selectedColor)).substring(2);
                        setDesign(design);
                    })
                    .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {})
                    .build()
                    .show();
        }

        public void changeTP(View view) {
            ColorPickerDialogBuilder
                    .with(view.getContext())
                    .setTitle(R.string.getTextOnPrimary)
                    .initialColor(Color.parseColor("#AA0000"))
                    .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                    .density(12)
                    .setPositiveButton(R.string.confirm, (dialog, selectedColor, allColors) -> {
                        Design design = mDesign.getValue();
                        if (design == null) design = new Design();
                        design.tp = "#" + Integer.toHexString(selectedColor).substring(2);
                        setDesign(design);
                    })
                    .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {})
                    .build()
                    .show();
        }

        public void changeTS(View view) {
            ColorPickerDialogBuilder
                    .with(view.getContext())
                    .setTitle(R.string.getTextOnSecondary)
                    .initialColor(Color.parseColor("#AA0000"))
                    .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                    .density(12)
                    .setPositiveButton(R.string.confirm, (dialog, selectedColor, allColors) -> {
                        Design design = mDesign.getValue();
                        if (design == null) design = new Design();
                        design.ts = "#" + Integer.toHexString(selectedColor).substring(2);
                        setDesign(design);
                    })
                    .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {})
                    .build()
                    .show();
        }

        public boolean onLongClick(View view, String color) {
            Context context = view.getContext();
            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText(null, color);
            Objects.requireNonNull(clipboard).setPrimaryClip(clipData);
            Toast.makeText(context,
                    context.getText(R.string.color) + " " + color + " " + context.getText(R.string.copied),
                    Toast.LENGTH_SHORT).
                    show();
            return true;
        }

        public void upload(View view) {
            Context context = view.getContext();
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(R.string.create_design);
            final EditText editText = new EditText(context);
            builder.setView(editText);
            builder.setPositiveButton(R.string.confirm, (dialogInterface, i) -> {
                Design design = mDesign.getValue();
                mApiInterface.createDesign(
                        editText.getText().toString(), design.p, design.pd, design.pl,
                        design.s, design.sd, design.sl,
                        design.tp, design.ts,
                        UserStatus.sUser.id
                ).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        message.postValue(response.isSuccessful() ?
                                context.getText(R.string.success).toString() :
                                context.getText(R.string.failed).toString()
                        );
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        message.postValue(t.toString());
                    }
                });
            })
            .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {})
            .setCancelable(true);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}
