package agermenos.codepath.todolists.pojos;

import java.util.Date;

/**
 * Created by Alejandro on 10/11/2015.
 */
public class Todo {
    private int id;
    private String text;
    private Date creationDate;
    private String status;
    private enum priority {LOW, MEDIUM, HIGH};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Todo() {
    }

    public Todo(int id, String text, Date creationDate, String status) {
        this.id = id;
        this.text = text;
        this.creationDate = creationDate;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Todo todo = (Todo) o;

        if (id != todo.id) return false;
        if (!text.equals(todo.text)) return false;
        if (creationDate != null ? !creationDate.equals(todo.creationDate) : todo.creationDate != null)
            return false;
        return status.equals(todo.status);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + text.hashCode();
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + status.hashCode();
        return result;
    }
}
