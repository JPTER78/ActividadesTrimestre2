package com.example.activitatcasa1.bd

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.activitatcasa1.dao.ClienteDAO


class MiBD
/**
 * Constructor de clase
 */
protected constructor(context: Context?) :
    SQLiteOpenHelper(context, database, null, version) {
    //Instrucción SQL para crear la tabla de Clientes
    private val sqlCreacionClientes =
        "CREATE TABLE clientes ( id INTEGER PRIMARY KEY AUTOINCREMENT, nif STRING, nombre STRING, " +
                "apellidos STRING, claveSeguridad STRING, email STRING);"

    //Instruccion SQL para crear la tabla de Cuentas


    //Instruccion SQL para crear la tabla de movimientos

    val clienteDAO: ClienteDAO?
        get() = Companion.clienteDAO




    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(sqlCreacionClientes)
        insercionDatos(db)
        Log.i("SQLite", "Se crea la base de datos " + database + " version " + version)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.i(
            "SQLite",
            "Control de versiones: Old Version=$oldVersion New Version= $newVersion"
        )
        if (newVersion > oldVersion) {
            //elimina tabla
            db.execSQL("DROP TABLE IF EXISTS clientes")

            //y luego creamos la nueva tabla
            db.execSQL(sqlCreacionClientes)
            insercionDatos(db)
            Log.i(
                "SQLite",
                "Se actualiza versión de la base de datos, New version= $newVersion"
            )
        }
    }

    private fun insercionDatos(db: SQLiteDatabase) {
        // Insertamos los clientes
        db.execSQL("INSERT INTO clientes(id, nif, nombre, apellidos, claveSeguridad, email) VALUES (1, '11111111A', 'Filemón', 'Pí', '1234', 'filemon.pi@tia.es');")
        db.execSQL("INSERT INTO clientes(id, nif, nombre, apellidos, claveSeguridad, email) VALUES (2, '22222222B', 'Mortadelo', 'Ibáñez', '1234', 'mortadelo.ibanez@tia.es');")
        db.execSQL("INSERT INTO clientes(id, nif, nombre, apellidos, claveSeguridad, email) VALUES (3, '33333333C', 'Vicente', 'Mondragón', '1234', 'vicente.mondragon@tia.es');")
        db.execSQL("INSERT INTO clientes (rowid, id, nif, nombre, apellidos, claveSeguridad, email) VALUES (null, null, '44444444D', 'Ayrton', 'Senna', '1234', 'ayrton.senna@f1.es');")
        db.execSQL("INSERT INTO clientes(rowid, id, nif, nombre, apellidos, claveSeguridad, email)VALUES(null, null, 'B1111111A', 'Ibertrola', '-', '1234', '-');")
        db.execSQL("INSERT INTO clientes (rowid, id, nif, nombre, apellidos, claveSeguridad, email) VALUES (null, null, 'B2222222B', 'Gas Natural', '-', '1234', '-');")
        db.execSQL("INSERT INTO clientes (rowid, id, nif, nombre, apellidos, claveSeguridad, email) VALUES (null, null, 'B3333333C', 'Telefónica', '-', '1234', '-');")
        db.execSQL("INSERT INTO clientes (rowid, id, nif, nombre, apellidos, claveSeguridad, email) VALUES (null, null, 'B4444444D', 'Aguas de Valencia', '-', '1234', '-');")
        db.execSQL("INSERT INTO clientes (rowid, id, nif, nombre, apellidos, claveSeguridad, email) VALUES (null, null, 'B5555555E', 'Audi', '-', '1234', '-');")
        db.execSQL("INSERT INTO clientes (rowid, id, nif, nombre, apellidos, claveSeguridad, email) VALUES (null, null, 'B6666666F', 'BMW', '-', '1234', '-');")
        db.execSQL("INSERT INTO clientes (rowid, id, nif, nombre, apellidos, claveSeguridad, email) VALUES (null, null, 'B7777777G', 'PayPal', '-', '1234', '-');")
        db.execSQL("INSERT INTO clientes (rowid, id, nif, nombre, apellidos, claveSeguridad, email) VALUES (null, null, 'B8888888H', 'Ayuntamiento de Valencia', '-', '1234', '-');")

        // Insertamos las cuentas
    }




    companion object {
        var dB: SQLiteDatabase? = null
            private set

        //nombre de la base de datos
        private const val database = "MiBanco"
        //versión de la base de datos
        private const val version = 11
        private var instance: MiBD? = null
        private var clienteDAO: ClienteDAO? = null

        fun getInstance(context: Context?): MiBD? {
            if (instance == null) {
                instance = MiBD(context)
                dB = instance!!.writableDatabase
                clienteDAO = ClienteDAO()
            }
            return instance
        }



        /*protected fun MiBD(context: Context?): MiBD? {
            super(context, database, null, version)
        }*/

        fun closeDB() {
            dB?.close()
        }

        /*fun getDB(): SQLiteDatabase? {
            return dB
        }*/
    }
}