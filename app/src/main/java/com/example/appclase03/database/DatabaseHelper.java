package com.example.appclase03.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.appclase03.model.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "loginapp.db";
    private static final int DATABASE_VERSION = 1;

    //El Dao es para tener el acceso a la tabla y sus elementos
    private Dao<User, Integer> userDao = null;

    public DatabaseHelper(Context context){
        //TODO:Crear el archivo de configuracion
        super(context, DATABASE_NAME, null, DATABASE_VERSION, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            //esta linea de codigo se pone por cada modelo que se tenga
            TableUtils.createTable(connectionSource, User.class);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }//

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        //Esta linea tambien se hace por cada modelo que se tenga
        try {
            TableUtils.dropTable(connectionSource, User.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Saber si el DAO esta inicializado
    public Dao<User, Integer> getUserDao() throws SQLException{
        if (userDao == null) userDao =  getDao(User.class);
        return userDao;
    }

    @Override
    public void close(){
        super.close();

        userDao = null;
    }
}
