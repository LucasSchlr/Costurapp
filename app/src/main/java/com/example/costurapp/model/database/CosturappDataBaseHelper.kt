package com.example.costurapp.model.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

val DB_NAME = "costurapp"
val DB_VERSION = 1

class CosturappDataBaseHelper(
    context: Context
): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION ) {
    override fun onCreate(db: SQLiteDatabase?) {
        updateMyDatabase(db, 0/*, DB_VERSION*/)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        updateMyDatabase(db, oldVersion/*, newVersion*/)
    }

    fun updateMyDatabase(db: SQLiteDatabase?, oldVersion: Int/*, newVersion:Int*/){
        if (oldVersion < 1){
            db?.execSQL(
                "CREATE TABLE COMPRA (" +
                        "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                        "VALOR REAL NOT NULL," +
                        "FORNECEDOR TEXT NOT NULL," +
                        "DATA_COMPRA TEXT NOT NULL);"
            )

            db?.execSQL(
                "CREATE TABLE ITENS_COMPRA(" +
                        "ID_COMPRA INTEGER NOT NULL," +
                        "ID_MATERIAL INTEGER NOT NULL," +
                        "QUANTIDADE REAL," +
                        "VALOR REAL," +
                        "VALOR_UNITARIO REAL);"
            )

            db?.execSQL(
                "CREATE TABLE ENCOMENDA_VENDA (" +
                        "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                        "VALOR REAL NOT NULL," +
                        "NOME_CLIENTE TEXT," +
                        "DATA_VENDA TEXT NOT NULL," +
                        "ENTREGUE TEXT);"//SIM\NAO
            )

            db?.execSQL(
                "CREATE TABLE ITENS_ENCOMENDA_VENDA (" +
                        "ID_VENDA INTEGER NOT NULL," +
                        "ID_PRODUTO INTEGER NOT NULL," +
                        "QUANTIDADE INTEGER);"
            )

            db?.execSQL(
                "CREATE TABLE MATERIAL_PRODUTO (" +
                        "ID_PRODUTO INTEGER," +
                        "ID_MATERIAL INTEGER," +
                        "QUANTIDADE INTEGER);"
            )

            db?.execSQL(
                "CREATE TABLE MATERIAL (" +
                        "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                        "TIPO_MATERIAL INTEGER," + //(TECIDO, LINHA, ZIPER, BOTÃO, GANCHO)
                        "DESCRICAO TEXT,"+
                        "UNIDADE_MEDIDA INTEGER,"+//(METROS, UNIDADES, CENTIMETROS)
                        // TECIDO
                        "NOME TEXT,"+
                        "ESTAMPA TEXT,"+
                        "TECIDO_PLANO TEXT,"+//(SIN\NÃO)
                        "TRANSPARENTE TEXT, "+//(SIN\NÃO)
                        //LINHA
                        "TIPO_LINHA INTEGER,"+//(OVERLOCK, RETA)
                        //ZIPER
                        "TAMANHO INTEGER,"+//(PEQUENO, MEDIO, GRANDE)
                        "TIPO_ZIPER INTEGER,"+//NORMAL, JEANS, INVISIVEL
                        "ESTAMPADO TEXT);"//SIM, NÃO
            )

            db?.execSQL(
                "CREATE TABLE PRODUTO (" +
                        "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                        "TIPO_PRODUTO INTEGER NOT NULL," +//(SAIA, CROPPED, VESTIDO, TOP, CARDIGAN, CALÇA, KIMONO)
                        "DESCRICAO TEXT NOT NULL," +
                        "TAMANHO INTEGER," +//(PP/P/M/G/GG)
                        "NUMERACAO INTEGER);"
            )

            db?.execSQL(
                "CREATE TABLE ENCOMENDA_COMPRA(" +
                        "ID_MATERIAL INTEGER," +
                        "QUANTIDADE INTEGER);"
            )
        }
    }
}
