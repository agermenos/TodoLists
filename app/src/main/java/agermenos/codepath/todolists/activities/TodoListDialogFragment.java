package agermenos.codepath.todolists.activities;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import agermenos.codepath.todolists.R;

/**
 * Created by Alejandro on 10/23/15.
 */
public class TodoListDialogFragment extends DialogFragment implements TextView.OnEditorActionListener {

    public interface EditNameDialogListener {
        public void onEdit(String text);
    }

    EditText mEditText;
    Button mOkButton;
    Button mCancelButton;
    public TodoListDialogFragment() {
        // Empty constructor required for DialogFragment
    }

    public TodoListDialogFragment getMe(){
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.todo_list_dialog, container);
        mEditText = (EditText) view.findViewById(R.id.txt_todo_list_name);
        mOkButton = (Button) view.findViewById(R.id.button_ok);
        mCancelButton = (Button) view.findViewById(R.id.button_cancel);

        getDialog().setTitle("New ToDo List");

        // Show soft keyboard automatically
        mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        mEditText.setOnEditorActionListener((TextView.OnEditorActionListener) this);

        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditNameDialogListener activity = (EditNameDialogListener) getActivity();
                activity.onEdit(mEditText.getText().toString());
                getMe().dismiss();
            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMe().dismiss();
            }
        });

        return view;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text to activity
            EditNameDialogListener activity = (EditNameDialogListener) getActivity();
            activity.onEdit(mEditText.getText().toString());
            this.dismiss();
            return true;
        }
        return false;
    }
}