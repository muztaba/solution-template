package com.tigerit.exam.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by seal on 3/25/2016.
 */
public class TableRepo {
    private Map<String, Table> tableMap;
    public TableRepo() {
        tableMap = new HashMap<>();
    }

    public void addTable(Table table) {
        String tableName = table.getTableName();
        if (!tableMap.containsKey(tableName)) {
            tableMap.put(tableName, table);
        } else {
            throw new RuntimeException("Duplicate com.tigerit.exam.model.Table Name");
        }
    }

    public Table getTable(String tableName) {
        Table table = null;
        if (tableMap.containsKey(tableName)) {
            table = tableMap.get(tableName);
        } else {
            throw new RuntimeException("com.tigerit.exam.model.Table name not found!!!");
        }

        return table;
    }

    public void printRepo() {
        for (Map.Entry<String, Table> itr : tableMap.entrySet()) {
            System.out.println(itr.getValue());
        }
    }
}
