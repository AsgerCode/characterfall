package org.academiadecodigo.bootcamp.characterfall.view;

import java.io.*;

/**
 * Created by ritafialho on 16/07/2017.
 */
public class FileManager {

    public static String load(String path) {

        String result = "";
        String currentLine;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)));




            while ((currentLine = bufferedReader.readLine()) != null){

                result = result + currentLine + "\n";


            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    return result;

    }
}
