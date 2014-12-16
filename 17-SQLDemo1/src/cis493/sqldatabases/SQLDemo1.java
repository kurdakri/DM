package cis493.sqldatabases;

import android.app.Activity;
import android.database.sqlite.*;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

public class SQLDemo1 extends Activity {
	SQLiteDatabase db;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //-----------------------------------------------------------------------------       
        // creating a SQLITE database.
        // Version1 uses the method: SQLiteOpenDatabase(filePath, cursor, flag)
        // where filePath is a complete destination of the form 
        //     "/data/data/<namespace>/<databaseName>"
        //     "/mnt/sdcard/<databasename>"
        // ignore cursor param, flag could be: SQLiteDatabase.CREATE_IF_NECESSARY
        // SQLiteDatabase.OPEN_READWRITE, or SQLiteDatabase.OPEN_READONLY.
        //------------------------------------------------------------------------------
        // WARNING: You must include in your manifest the following request
        //
        //  <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" /> 
        //        
        //------------------------------------------------------------------------------
        
        // this provides the 'real' path name to the SD card: "/mnt/sdcard" 
        String SDcardPath = Environment.getExternalStorageDirectory().getPath();
        String myDbPath = SDcardPath + "/" + "myfriends";
        
        TextView txtMsg = (TextView)findViewById(R.id.txtMsg);
        txtMsg.setText("DB Path: " + myDbPath); 
        
        try {        	
        	db = SQLiteDatabase.openDatabase(
        			myDbPath,
    				null,
    				SQLiteDatabase.CREATE_IF_NECESSARY);
        	// here you do something with your database
        	// ... ... ...
        	db.close();
        	Toast.makeText(this, "All done!", Toast.LENGTH_SHORT).show();
        }
        catch (SQLiteException e) {
        	 Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();        	
        }
    }
}