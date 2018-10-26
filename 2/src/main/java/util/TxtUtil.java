package util;

import java.io.*;
import java.util.List;

public class TxtUtil {
    public static void writeIntoTxt(String filename, List<String> stringList) {
        try {
            PrintWriter printWriter = new PrintWriter(filename);
            for(String str: stringList) {
                printWriter.write(str);
                printWriter.println();
            }
            printWriter.flush();
            printWriter.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
