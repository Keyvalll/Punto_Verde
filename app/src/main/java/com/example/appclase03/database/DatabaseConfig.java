package com.example.appclase03.database;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DatabaseConfig extends OrmLiteConfigUtil{

    public static void main (String args[]) throws IOException, SQLException {
        writeConfigFile("ormlite_config.txt");
    }
}
