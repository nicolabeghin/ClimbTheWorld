package org.unipd.nbeghin.climbtheworld.db;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class PreExistingDbLoader extends SQLiteAssetHelper {
    public PreExistingDbLoader(Context context) {
        super(context, DbHelper.DATABASE_NAME, null, DbHelper.DATABASE_VERSION);
    }
}