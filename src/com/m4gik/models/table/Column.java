package com.m4gik.models.table;

/**
 * Created by m4gik on 6/26/17.
 */
public class Column {

    private String columnName = null;

    public Column(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
}
