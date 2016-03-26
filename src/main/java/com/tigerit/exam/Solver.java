package com.tigerit.exam;

import com.tigerit.exam.io.InputReader;
import com.tigerit.exam.ioutils.IOUtils;
import com.tigerit.exam.model.Table;
import com.tigerit.exam.model.TableRepo;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by seal on 3/24/2016.
 */
public class Solver {
    private TableRepo tableRepo;
    public void solve(int testCase, InputReader in, PrintWriter out) {
        tableRepo = new TableRepo();
        List<String[]> queries = new ArrayList<>();
        out.println("Test: " + testCase);

        IOUtils.readTables(in, tableRepo);
        IOUtils.readQueries(in, queries);

        for (String[] query : queries) {
            QueryProcessor queryProcessor = new QueryProcessor();
            Table table = queryProcessor.queryProcess(query, tableRepo);
            out.println(table);
        }

    }

}





















