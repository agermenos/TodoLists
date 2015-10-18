package agermenos.codepath.todolists.pojos;

/**
 * Created by Alejandro on 10/13/15.
 */
public class TodoList {
    private long id;
    private String name;

    public TodoList(String name) {
        this.name = name;
    }

    public TodoList() {

    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TodoList todoList = (TodoList) o;

        if (id != todoList.id) return false;
        if (!name.equals(todoList.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        long result = id;
        result = 31 * result + name.hashCode();
        return (int)result;
    }
}
