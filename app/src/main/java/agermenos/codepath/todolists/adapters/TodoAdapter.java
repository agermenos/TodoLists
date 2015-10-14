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
import agermenos.codepath.todolists.utils.FormatUtil;


/**
 * Created by Alejandro on 10/11/2015.
 */
public class TodoAdapter extends ArrayAdapter<Todo> {
    public TodoAdapter(Context context, List<Todo> todos) {
        super(context, 0, todos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Todo todo = (Todo)getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_todos, parent, false);
        }
        TextView tv_taskName = (TextView)convertView.findViewById(R.id.todo_task);
        TextView tv_creationDate = (TextView)convertView.findViewById(R.id.creation_date);
        tv_taskName.setText(todo.getText());
        tv_creationDate.setText(FormatUtil.formatDate(todo.getCreationDate(), getContext().getResources().getString(R.string.todo_date_pattern)));
        return convertView;
    }
}
