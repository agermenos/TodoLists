package agermenos.codepath.todolists.activities;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import agermenos.codepath.todolists.R;
import agermenos.codepath.todolists.adapters.TodoAdapter;
import agermenos.codepath.todolists.adapters.TodoListAdapter;
import agermenos.codepath.todolists.pojos.Todo;
import agermenos.codepath.todolists.pojos.TodoList;
import agermenos.codepath.todolists.sql.TodoListDbHelper;

/**
 * A placeholder fragment containing a simple view.
 */
public class TodoListMainFragment extends Fragment {
    // Database Helper
    TodoListDbHelper db;
    TodoListAdapter todoListAdapter;

    public TodoListMainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_todo_list_main, container, false);

        db = new TodoListDbHelper(this.getContext());
        List<TodoList> todoLists = db.getAllToDosList();
        if(null==todoLists || todoLists.isEmpty()){
            todoLists = new ArrayList<>();
            TodoList tl = new TodoList("Testing new list");
            long todoListId = db.createToDoList(tl);
            tl.setId(todoListId);
            todoLists.add(tl);
        }
        if (todoListAdapter==null) {
            todoListAdapter = new TodoListAdapter(this.getContext(), todoLists);
        }

        final ListView listView = (ListView) rootView.findViewById(R.id.listview_todos);
        listView.setAdapter(todoListAdapter);

        return rootView;
    }
}
