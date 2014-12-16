package cis493.sqldatabases;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.widget.Toast;

public class SQLDemo2 extends Activity {
	SQLiteDatabase db;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // openOrCreateDatabase.
        // Open a new PRIVATE SQLiteDatabase associated with this Context's application package
        // "/data/data/cis493.sqldatabases/databases"
		// Create the database file if it doesn't exist.
        // Physical DB is held in the device's memory, do NOT use an SD card.
        // -------------------------------------------------------------------
        
        try {        	
            db =  this.openOrCreateDatabase(
    				 "myfriendsDB2",
    				 MODE_PRIVATE, 
    				 null);
            
            // here we do something with the database
            
        	db.close();
        	Toast.makeText(this, "All done!", Toast.LENGTH_SHORT).show();
        }
        catch (SQLiteException e) {
        	 Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show(); 
        }
        catch (Exception e) {
             Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();   
        }
    }
}