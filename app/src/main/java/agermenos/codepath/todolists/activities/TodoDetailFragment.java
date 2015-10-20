package agermenos.codepath.todolists.activities;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import agermenos.codepath.todolists.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class TodoDetailFragment extends Fragment {

    public TodoDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_todo_detail, container, false);
    }
}
