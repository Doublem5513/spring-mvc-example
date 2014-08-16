package org.doublem.examples.webapp.utils;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mint on 16/8/14.
 */
public class FileDataUtils {

    public static long findLastId(File dbFile){
        long lastId = 0;
        Pattern idExtractingPattern = Pattern.compile("id=(\\d+?)\\D");

        if(dbFile == null){
            throw new RuntimeException("File is null!");
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(dbFile));

            String line;
            while ((line = reader.readLine()) != null){
                Matcher matcher = idExtractingPattern.matcher(line);
                if(matcher.find()){
                    long id = Long.parseLong(matcher.group(1));
                    if(lastId < id){
                        lastId = id;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error reading file!", e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lastId;
    }
}
