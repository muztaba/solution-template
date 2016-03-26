package com.tigerit.exam;



import com.tigerit.exam.model.Table;
import com.tigerit.exam.model.TableRepo;
import com.tigerit.exam.utils.Pair;
import com.tigerit.exam.utils.RegexUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by seal on 3/25/2016.
 */
public class QueryProcessor {

    public static final int SELECT = 0;
    public static final int FROM = 1;
    public static final int JOIN = 2;
    public static final int ON = 3;

    String[] query;
    TableRepo tableRepo;
    private Map<String, String> tableShortNameMap = new HashMap<>();
    private Map<String, List<String>> tableColumnMap = new HashMap<>();
    private List<String> columnOrder = new ArrayList<>();


    List<int[]> newTableData;
    boolean asteriskSelect;
    Table newTable;


    public Table queryProcess(String[] query, TableRepo tableRepo) {
        this.query = query;
        this.tableRepo = tableRepo;

        tableShortName(query[FROM]);
        tableShortName(query[JOIN]);

        List<String> selectString = RegexUtil.regexChecker("[a-z\\_0-9]*\\.[a-z\\_0-9]*", query[SELECT]);
        if (selectString.size() == 0) {
            asteriskSelect = true;
        } else {
            tableColumnMapping(selectString);
        }

        List<String> onString = RegexUtil.regexChecker("[a-z\\_0-9]*\\.[a-z\\_0-9]*", query[ON]);

        Pair<String, String> compTableInfo1 = RegexUtil.splitTableColumnName(onString.get(0));
        Pair<String, String> compTableInfo2 = RegexUtil.splitTableColumnName(onString.get(1));

        Table table1 = tableRepo.getTable(tableShortNameMap.get(compTableInfo1.first));
        Table table2 = tableRepo.getTable(tableShortNameMap.get(compTableInfo2.first));
        joinTable(table1, table2, compTableInfo1.second, compTableInfo2.second);

        return newTable;
    }

    private void joinTable(Table table1, Table table2, String table1Col, String table2Col) {

        int[] a = table1.getColumn(table1Col);
        int[] b = table2.getColumn(table2Col);
        newTableData = new ArrayList<>();
        if (!asteriskSelect) {
            List<String> table1ColumnList = tableColumnMap.get(table1.getTableName());
            List<String> table2ColumnList = tableColumnMap.get(table2.getTableName());

            boolean a1 = table1ColumnList != null, b1 = table2ColumnList != null;
            int[][] table1Columns = null;
            int[][] table2Columns = null;
            if (a1) {
                table1Columns = table1.getColumns(table1ColumnList);
            }
            if (b1) {
                table2Columns = table2.getColumns(table2ColumnList);
            }

            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < b.length; j++) {
                    int[] array = new int[columnOrder.size()];
                    if (a[i] == b[j]) {

                        for (int k = 0; k < columnOrder.size(); k++) {
                            String str = columnOrder.get(k);
                            int row = 0;
                            int col = 0;
                            int value = 0;
                            if (a1 && tableColumnMap.get(table1.getTableName()).contains(str)) {
                                col = tableColumnMap.get(table1.getTableName()).indexOf(str);
                                row = i;
                                value = table1Columns[row][col];
                            } else if (b1 && tableColumnMap.get(table2.getTableName()).contains(str)) {
                                col = tableColumnMap.get(table2.getTableName()).indexOf(str);
                                row = j;
                                value = table2Columns[row][col];
                            }
                            array[k] = value;
                        }

                        newTableData.add(array);
                    }
                }
            }

        } else {
            int[][] table1Columns = table1.getTable();
            int[][] table2Columns = table2.getTable();
            columnOrder.addAll(table1.getColName());
            columnOrder.addAll(table2.getColName());

            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < b.length; j++) {
                    int[] array = new int[table1Columns[0].length + table2Columns[0].length];
                    if (a[i] == b[j]) {
                        System.arraycopy(table1Columns[i], 0, array, 0, table1Columns[i].length);
                        System.arraycopy(table2Columns[j], 0, array, table1Columns[i].length, table2Columns[i].length);
                        newTableData.add(array);
                    }
                 }
            }
        }

        fillNewTable();

    }

    private void fillNewTable() {
        if (newTableData.size() != 0) {
            newTable = new Table("newTable", newTableData.size(), newTableData.get(0).length);
            for (int[] row : newTableData) {
                newTable.addRow(row);
            }
        } else {
            newTable = new Table();
        }
        newTable.setColName(this.columnOrder);
    }

    private void tableColumnMapping(List<String> list) {
        for (String str : list) {
            Pair<String, String> pair = RegexUtil.splitTableColumnName(str);
            String tableName = this.tableShortNameMap.get(pair.first);
            if (!this.tableColumnMap.containsKey(tableName)) {
                tableColumnMap.put(tableName, new ArrayList<>());
                tableColumnMap.get(tableName).add(pair.second);
            } else {
                tableColumnMap.get(tableName).add(pair.second);
            }
            this.columnOrder.add(pair.second);
        }
    };

    private void tableShortName(String string) {
        String[] str = string.split(" ");
        if (str.length == 3) {
            tableShortNameMap.put(str[str.length - 1], str[1]);
        } else {
            tableShortNameMap.put(str[1], str[1]);
        }
    }

    private void printNewTable() {
        for(String str : columnOrder) {
            System.out.print(str + " ");
        }
        System.out.println();
        for (int[] i : newTableData) {
            for (int j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

}















