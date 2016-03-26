package com.tigerit.exam.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by seal on 3/24/2016.
 */
public class Table {
    private String tableName;

    private List<String> colName;
    private int[][] table;
    private int index;
//    private List<int[]> table;

    public Table() {}

    public Table(String tableName, int row, int col) {
        this.tableName = tableName;
        colName = new ArrayList<>(col);
        table = new int[row][1];
//        table = new ArrayList<>(row);
    }

    public void addColName(String colName) {
        this.colName.add(colName);
    }

    public void addRow(int[] row) {
        if (checkSize()) {
            table[index] = row;
            index++;
        } else {
            throw new RuntimeException("table.length < index");
        }
    }

    public int[][] getColumns(List<String> colNames) {
        int[][] data = new int[table.length][colNames.size()];

        for (int i = 0; i < colNames.size(); i++) {
            int index = this.colName.indexOf(colNames.get(i));
            for (int j = 0; j < this.table.length; j++) {
                data[j][i] = this.table[j][index];
            }
        }

        return data;
    }


    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setTable(int[][] table) {
        this.table = table;
    }

    public int[] getColumn(String name) {
        int colIndex = this.colName.indexOf(name);
        int[] array = new int[table.length];
        for (int i = 0; i < table.length; i++) {

            array[i] = table[i][colIndex];
        }
        return array;
    }

    private boolean checkSize() {
        return table.length >= index;
    }

    public String getTableName() {
        return tableName;
    }

    public int[][] getTable() {
        return table;
    }

    public List<String> getColName() {
        return colName;
    }


    public void setColName(List<String> colName) {
        this.colName = colName;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < colName.size(); i++) {
            builder.append(colName.get(i));
            if (i != colName.size() - 1) {
                builder.append(" ");
            }
        }
        builder.append("\n");
        if (this.table != null) {
            Arrays.sort(this.table, new Comparator<int[]>() {
                @Override
                public int compare(int[] a, int[] b) {
                    for (int i = 0; i < a.length && i < b.length; i++)
                        if (a[i] != b[i])
                            return a[i] - b[i];
                    return a.length - b.length;
                }
            });

            for (int[] row : table) {
                for (int i = 0; i < row.length; i++) {
                    builder.append(String.valueOf(row[i]));
                    if (i != row.length - 1) {
                        builder.append(" ");
                    }
                }
                builder.append("\n");
            }
        }
        return builder.toString();
    }
}
