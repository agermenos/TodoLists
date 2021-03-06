package agermenos.codepath.todolists.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

import agermenos.codepath.todolists.R;
import agermenos.codepath.todolists.adapters.TodoListAdapter;
import agermenos.codepath.todolists.pojos.TodoList;
import agermenos.codepath.todolists.sql.TodoListDbHelper;

/**
 * An activity representing a list of TodosSet. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link TodoListDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link TodoListListFragment} and the item details
 * (if present) is a {@link TodoListDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link TodoListListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class TodoListListActivity extends AppCompatActivity
        implements TodoListListFragment.Callbacks, TodoListDialogFragment.EditNameDialogListener {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private int fragment_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_todolist_app_bar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                 .setAction("Action", null).show();
                 */
                showEditDialog();
            }
        });

        if (findViewById(R.id.todolist_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((TodoListListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.todolist_list))
                    .setActivateOnItemClick(true);
        }
    }

    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        TodoListDialogFragment editNameDialog = new TodoListDialogFragment();
        editNameDialog.setTodoListId(null);
        editNameDialog.show(fm, "fragment_edit_todo");
    }

    /**
     * Callback method from {@link TodoListListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(TodoListDetailFragment.ARG_ITEM_ID, id);
            TodoListDetailFragment fragment = new TodoListDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.todolist_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, TodoListDetailActivity.class);
            detailIntent.putExtra(TodoListDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }

    @Override
    public void onEdit(Integer id, String text) {
        FragmentManager fm = getSupportFragmentManager();
        ((TodoListListFragment)getSupportFragmentManager().
                findFragmentById(R.id.todolist_list)).
                onEditDialog(id, text);
    }
}
