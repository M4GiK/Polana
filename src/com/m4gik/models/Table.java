package com.m4gik.models;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by m4gik on 6/25/17.
 */
public class Table {

    HashMap<String, List<String>> tableContents = null;

    Table(String ... headers) {
        tableContents = new LinkedHashMap<>();
        for (String header: headers) {
            tableContents.putIfAbsent(header, null);
        }
    }

}
