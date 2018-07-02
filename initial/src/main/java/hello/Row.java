package hello;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Optional;

public class Row {


    private List<Object> columns;


    public List<Object> getColumns() {
        return columns;
    }

    public void setColumns(List<Object> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "Row{" +
                "columns=" + columns +
                '}';
    }
}
