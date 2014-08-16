package org.doublem.examples.webapp.settings;

import java.io.File;

/**
 * Created by mint on 16/8/14.
 */
public class GlobalSettings {

    public static final String DB_FILE = System.getProperty("user.home") +
            File.separator + "webapp" +
            File.separator + "forum" +
            File.separator + "forum.db";

    public static final String DB_DATE_FORMAT = "dd.MM.yyyy HH:mm:ss";
}
