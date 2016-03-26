package com.tigerit.exam;


import com.tigerit.exam.io.InputReader;

import java.io.File;
import java.io.PrintWriter;

import static com.tigerit.exam.IO.*;

/**
 * All of your application logic should be placed inside this class.
 * Remember we will load your application from our custom container.
 * You may add private method inside this class but, make sure your
 * application's execution points start from inside run method.
 */
public class Solution implements Runnable {
    @Override
    public void run() {
/*        // your application entry point

        // sample input process
        String string = readLine();

        Integer integer = readLineAsInteger();

        // sample output process
        printLine(string);
        printLine(integer);*/
//        InputReader in = new InputReader(new File("input.txt"));
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int testCase = in.nextInt();
        for (int i = 1; i <= testCase; i++) {
            Solver solution = new Solver();
            solution.solve(i, in, out);
        }
        out.close();
    }
}
