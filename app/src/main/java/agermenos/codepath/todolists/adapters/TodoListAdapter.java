package agermenos.codepath.todolists.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import agermenos.codepath.todolists.R;
import agermenos.codepath.todolists.pojos.Todo;
import agermenos.codepath.todolists.pojos.TodoList;
import agermenos.codepath.todolists.utils.FormatUtil;

/**
 * Created by Alejandro on 10/18/15.
 */
public class TodoListAdapter extends ArrayAdapter<TodoList> {
    public TodoListAdapter(Context context, List<TodoList> todoList) {
        super(context, 0, todoList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TodoList todoList = (TodoList)getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_todos, parent, false);
        }
        TextView tv_taskName = (TextView)convertView.findViewById(R.id.todo_task);
        tv_taskName.setText(todoList.getName());
        return convertView;
    }
}
