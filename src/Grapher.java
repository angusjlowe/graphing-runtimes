import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.io.*;
import java.util.Arrays;
import java.util.Hashtable;

/**
 * Created by s1578278 on 18/04/17.
 */
public class Grapher {
    public static long getAlgo1Runtime(int[] input) {
            long start = System.nanoTime();
            int studentResult = AlgoCode.algorithm1(input);
            long stop = System.nanoTime();
            return (stop - start) / 1000;
    }
    public static long getAlgo2Runtime(int[] input) {
        long start = System.nanoTime();
        int studentResult = AlgoCode.algorithm2(input);
        long stop = System.nanoTime();
        return (stop - start) / 1000;
    }
    public static void getRuntimes(int p, int t, String f) {
        try {
            BufferedWriter ofhdl = new BufferedWriter(new FileWriter(f));
            ofhdl.write("//  Input-size\t   Student-RT1\t  Student-RT2\n");
            ofhdl.flush();

            for (int i = 1; i <= t; i++) {
                int size = 10 * i;
                System.out.print(">> Testing with " + p + " sets of " + size + " items ");
                System.out.flush();
                int[] testArray1 = new int[size];
                for(int j = 0; j < size; j++) {
                    int n = (int)(Math.random()*1000);
                    testArray1[j] = n;
                }
                int[] testArray2 = new int[size];
                for(int j = 0; j < size; j++) {
                    int n = (int)(Math.random()*1000);
                    testArray2[j] = n;
                }
                long worstStudentRuntime1 = 0;
                long worstStudentRuntime2 = 0;
                for (int j = 0; j < p; j++) {
                    System.out.print(".");
                    System.out.flush();
                    long studentRuntime1 = getAlgo1Runtime(testArray1);
                    if (studentRuntime1 > worstStudentRuntime1)
                        worstStudentRuntime1 = studentRuntime1;
                    long studentRuntime2 = getAlgo2Runtime(testArray2);
                    if (studentRuntime2 > worstStudentRuntime2)
                        worstStudentRuntime2 = studentRuntime2;
                }

                System.out.println(" done");
                System.out.flush();

                ofhdl.write(String.format( "%14d\t%14d\t%15d\n", size, worstStudentRuntime1, worstStudentRuntime2));
                ofhdl.flush();
            }

            System.out.println(">> Complete!");

            ofhdl.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void plotRuntimes(String inputRuntimesPath) {
        try {
            BufferedReader ifhdl = new BufferedReader(new FileReader(inputRuntimesPath));
            Hashtable<Integer, Integer> studentTbl = new Hashtable<Integer, Integer>();
            Hashtable<Integer, Integer> studentTbl2 = new Hashtable<Integer, Integer>();
            while (true) {
                String line = ifhdl.readLine();
                if (line == null) break;
                line = line.trim();
                if (line.startsWith("//")) continue;
                String[] tokens = line.split("[ \t]+");
                if (tokens.length == 0) continue;
                if (tokens.length != 3) throw new IllegalArgumentException();
                int inputSize = Integer.parseInt(tokens[0]);
                int studentRT = Integer.parseInt(tokens[1]);
                int studentRT2 = Integer.parseInt(tokens[2]);
                studentTbl.put(inputSize, studentRT);
                studentTbl2.put(inputSize,studentRT2);
            }

            double[][] data = new double[studentTbl.size() + 1][3];

            data[0][0] = 0.0;
            data[0][1] = 0.0;
            data[0][2] = 0.0;

            Integer[] keys = new Integer[studentTbl.size()];
            keys = studentTbl.keySet().toArray(keys);
            Arrays.sort(keys);
            int inputSize = 0;
            int studentRT = 0;
            int studentRT2 = 0;
            for (int i = 0; i < keys.length; i++) {
                inputSize = keys[i];
                studentRT = studentTbl.get(inputSize);
                studentRT2 = studentTbl2.get(inputSize);
                data[i + 1][0] = inputSize;
                data[i + 1][1] = ((double) studentRT) / 1000;
                data[i + 1][2] =  ((double) studentRT2) / 1000;
            }
            //clean data
            for(int i = 1; i < data.length - 1; i++) {
                if(data[i][1] > data[i-1][1]*2 && data[i][1] > data[i+1][1]*2) {
                    data[i][1] = data[i-1][1];
                }
                if(data[i][2] > data[i-1][2]*2 && data[i][2] > data[i+1][2]*2) {
                    data[i][2] = data[i-1][2];
                }
            }
            GraphingData gd = new GraphingData("Plot", data);
            gd.pack();
            RefineryUtilities.centerFrameOnScreen(gd);
            gd.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println(">> Illegal file format: Graph plotter has terminated unexpectedly.");
        }
    }
}
