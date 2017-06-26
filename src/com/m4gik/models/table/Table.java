package com.m4gik.models.table;

import java.util.*;

/**
 * Created by m4gik on 6/25/17.
 */
public class Table {

    HashMap<Column, List<Row>> tableContents = null;

    public HashMap<Column, List<Row>> getTableContents() {
        return tableContents;
    }

    public Table(Column... headers) {
        tableContents = new LinkedHashMap<>();
        for (Column header: headers) {
            if(header != null) {
                tableContents.putIfAbsent(header, new ArrayList<>());
            }
        }
    }

    public Column addColumn(String columnName) {
        Column column = new Column(columnName);
        tableContents.putIfAbsent(column, new ArrayList<>());

        return column;
    }

    public Column getColumn(String columnName) {
        Column column = null;
        for(Map.Entry<Column, List<Row>> entrySet: this.tableContents.entrySet()) {
            Column columnHeader = entrySet.getKey();
            if(columnHeader.getColumnName().equalsIgnoreCase(columnName)) {
                column = columnHeader;
            }
        }

        return column;
    }

    public List<Column> getColums() {
        List<Column> columns = new ArrayList<>();
        for(Map.Entry<Column, List<Row>> entrySet: this.tableContents.entrySet()) {
            if(entrySet.getKey() != null) columns.add(entrySet.getKey());
        }

        return columns;
    }

    public List<Row> getRows(Column column) {
        return this.tableContents.get(column);
    }

    public void insertRow(Column column, Row row) {
        for(Map.Entry<Column, List<Row>> entrySet: this.tableContents.entrySet()) {
            Column columnHeader = entrySet.getKey();
            if(columnHeader.equals(column)) {
                List<Row> rows = entrySet.getValue();
                if(rows != null) {
                    rows.add(row);
                }
            }
        }
    }

}
