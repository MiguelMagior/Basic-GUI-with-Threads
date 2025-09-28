package br.com.basicgui.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils{
    public static String[] readFileLines(File file) throws IOException{
        List<String> lines = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while((line = reader.readLine()) != null){
                lines.add(line);
            }
        }
        return lines.toArray(new String[0]);
    }
}