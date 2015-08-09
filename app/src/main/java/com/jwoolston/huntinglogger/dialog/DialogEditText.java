package com.jwoolston.huntinglogger.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.jwoolston.huntinglogger.R;

/**
 * @author Jared Woolston (jwoolston@idealcorp.com)
 */
public class DialogEditText extends DialogFragment implements TextView.OnEditorActionListener {

    private static final String TAG = DialogEditText.class.getSimpleName();

    private EditText mEditText;
    private EditTextListener mNameDialogListener;
    private String mCreateType;

    public DialogEditText() {
        // Empty constructor required for DialogFragment
    }

    public void setEditTextListener(EditTextListener listener) {
        mNameDialogListener = listener;
    }

    public void setCreateType(String type) {
        mCreateType = type;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        // request a window without the title
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_edit_text, container);
        mEditText = (EditText) view.findViewById(R.id.text_field);
        mEditText.setOnEditorActionListener(this);
        getDialog().setTitle("Create new " + mCreateType);

        final Toolbar toolbar = (Toolbar) view.findViewById(R.id.edit_text_toolbar);
        return view;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text to activity
            mNameDialogListener.onFinishEditDialog(mEditText.getText().toString());
            dismiss();
            return true;
        }
        return false;
    }

    public interface EditTextListener {
        void onFinishEditDialog(String input);
    }
}
