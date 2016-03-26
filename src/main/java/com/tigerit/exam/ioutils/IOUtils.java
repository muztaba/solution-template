package com.tigerit.exam.ioutils;

import com.tigerit.exam.io.InputReader;
import com.tigerit.exam.model.Table;
import com.tigerit.exam.model.TableRepo;

import java.util.List;

/**
 * Created by seal on 3/26/2016.
 */
public class IOUtils {
    public static void readTables(InputReader in, TableRepo repo) {
        int nT = in.nextInt();
        while (nT-- > 0) {
            String tableName = in.nextLine();
            int col = in.nextInt();
            int row = in.nextInt();
            Table table = new Table(tableName, row, col);
            for (int i = 0; i < col; i++) {
                table.addColName(in.next());
            }

            for (int i = 0; i < row; i++) {
                int[] rowArray = new int[col];
                for (int j = 0; j < col; j++) {
                    rowArray[j] = in.nextInt();
                }
                table.addRow(rowArray);
            }

            repo.addTable(table);
        }
    }

    public static final int SELECT = 0;
    public static final int FROM = 1;
    public static final int JOIN = 2;
    public static final int ON = 3;

    public static void readQueries(InputReader in, List<String[]> queries) {
        int nQ = in.nextInt();
        while (nQ-- > 0) {
            String[] query = new String[4];
            query[SELECT] = in.nextLine();
            query[FROM] = in.nextLine();
            query[JOIN] = in.nextLine();
            query[ON] = in.nextLine();
            in.nextLine();
            queries.add(query);
        }
    }
}
