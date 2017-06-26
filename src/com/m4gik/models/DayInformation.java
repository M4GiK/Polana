package com.m4gik.models;

import com.m4gik.models.table.Column;
import com.m4gik.models.table.Row;
import com.m4gik.models.table.Table;

import java.time.LocalDateTime;

/**
 * Created by m4gik on 6/26/17.
 */
public class DayInformation {

    private Table table = null;

    private Column getColumnHeader(String columnHeader) {
        if(table == null) this.table = new Table(new Column(columnHeader));
        else {
            if(this.table.getColumn(columnHeader) == null) {
                this.table.addColumn(columnHeader);
            }
        }
        return this.table.getColumn(columnHeader);
    }

    private void addInformationToColumnHeader(Column columnHeader, Row row) {
        this.table.insertRow(columnHeader, row);
    }

    public void addInformation(String cloumnHeader, LocalDateTime datetime) {
        addInformationToColumnHeader(getColumnHeader(cloumnHeader), new Row(datetime.toString()));
    }

    public void addInformation(String cloumnHeader, Row information) {
        addInformationToColumnHeader(getColumnHeader(cloumnHeader), information);
    }

    public void addInformation(String cloumnHeader, String information) {
        addInformationToColumnHeader(getColumnHeader(cloumnHeader), new Row(information));
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}
