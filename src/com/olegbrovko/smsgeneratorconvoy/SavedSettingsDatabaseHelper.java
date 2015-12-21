package com.olegbrovko.smsgeneratorconvoy;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class SavedSettingsDatabaseHelper extends SQLiteOpenHelper implements BaseColumns
{
	private static final String DATABASE_NAME = "convoy.db";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_TABLE = "defaultsettings";
	
	//QUERY CONFIGURATIONS
	public static final String CB_QUERYCONFIG_QUERYCONFIG = "cb_queryconfing";
	
	private static final String DATABASE_CREATE_SCRIPT = 
	"CREATE TABLE " + DATABASE_TABLE + 
	" (" 
			+ BaseColumns._ID + " integer primary key autoincrement" + ", " 
			+ CB_QUERYCONFIG_QUERYCONFIG + " INTEGER"
			+ 
	 ");";
	
	SavedSettingsDatabaseHelper(Context context) 
	{
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public SavedSettingsDatabaseHelper(Context context, String name, CursorFactory factory, int version) 
	{
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	public SavedSettingsDatabaseHelper(Context context, String name, CursorFactory factory, int version, DatabaseErrorHandler errorHandler) 
	{
		super(context, name, factory, version, errorHandler);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		db.execSQL(DATABASE_CREATE_SCRIPT);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		//Delete old table
		db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_TABLE);
		
		//Create new table
		onCreate(db);
	}

}
