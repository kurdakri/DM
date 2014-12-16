//USING ANDROID-SQLITE DATABASES
package cis493.sqldatabases;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

public class SQLDemo3 extends Activity {
	SQLiteDatabase db;
	TextView txtMsg;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        txtMsg = (TextView) findViewById(R.id.txtMsg);
        
        try {
			openDatabase();		//open (create if needed) database
			dropTable();		//if needed drop table tblAmigos
			insertSomeDbData();	//create-populate tblAmigos
			useRawQuery1(); 	//fixed SQL with no arguments
			useRawQuery2(); 	//parameter substitution
			useRawQuery3(); 	//manual string concatenation
			useSimpleQuery1();	//simple query
			useSimpleQuery2();	//nontrivial 'simple query'
			useCursor1();		//retrieve rows from a table
			updateDB();			//use execSQL to update
			useInsertMethod();	//use insert method
			useUpdateMethod();	//use update method
			useDeleteMethod();	//use delete method
			
			db.close();			//make sure to release the DB
			Toast.makeText(this,"All done!", Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
		}        
    }// onCreate
    

	///////////////////////////////////////////////////////////////////
    private void openDatabase() {
        try {        	
            //------------------------------------------------------------------------------
            // WARNING: You must include in your manifest the following request
            //
            //  <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" /> 
            //        
            //------------------------------------------------------------------------------
            
            // this provides the 'real' path name to the SD card
            String SDcardPath = Environment.getExternalStorageDirectory().getPath();
            
            // other options for myDbPath are:
            // Using device's memory:    "/data/data/cis493.sqldatabases/myfriendsDB"
            // Absolute path   			 "/mnt/sdcard/myfriendsDB"
            // ------------------------------------------------------------------------------
            String myDbPath = SDcardPath + "/" + "myfriendsDB.db";
            // Toast.makeText(this, "DB Path: " + myDbPath, 1).show();     
        	txtMsg.append("\n-openDatabase - DB Path: " + myDbPath);
        	
        	db = SQLiteDatabase.openDatabase(
        			myDbPath,
    				null,
    				SQLiteDatabase.CREATE_IF_NECESSARY);       	
        	
        	// Toast.makeText(this, "DB was opened!", Toast.LENGTH_LONG).show();
        	txtMsg.append("\n-openDatabase - DB was opened");
        }
        catch (SQLiteException e) {
        	 Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();        	
        }
    }//createDatabase

	/////////////////////////////////////////////////////////////////////
	private void dropTable() {
	//(clean start) action query to drop table 
		try {
			db.execSQL( " drop table tblAmigo; "); 
			// Toast.makeText(this, "Table dropped", Toast.LENGTH_LONG).show();
			txtMsg.append("\n-dropTable - dropped!!");
		} catch (Exception e) {
			Toast.makeText(this,"dropTable()\n" + e.getMessage(), Toast.LENGTH_LONG).show();
		}  
	}//dropTable
    
    ///////////////////////////////////////////////////////////////////
    private void insertSomeDbData() {
    	//create table: tblAmigo
    	db.beginTransaction();
		try {
			//create table
			db.execSQL("create table tblAMIGO ("
					+ " recID integer PRIMARY KEY autoincrement, " 
			        + " name  text, " 
			        + " phone text );  ");
			//commit your changes
    		db.setTransactionSuccessful();
    		
			// Toast.makeText(this, "Table was created", Toast.LENGTH_LONG).show();
			txtMsg.append("\n-insertSomeDbData - Table was created");
			
		}
		catch (SQLException e1) {			
			Toast.makeText(this, e1.getMessage(), Toast.LENGTH_LONG).show();
		}
		finally {
    		db.endTransaction();
    	}
		
		// populate table: tblAmigo
    	db.beginTransaction();
    	try {

    		//insert rows
    		db.execSQL( "insert into tblAMIGO(name, phone) "
    					         + " values ('AAA', '555' );" );
    		db.execSQL("insert into tblAMIGO(name, phone) "
    						     + " values ('BBB', '777' );" );	
    		db.execSQL("insert into tblAMIGO(name, phone) "
    							 + " values ('CCC', '999' );" );
    		
    		//commit your changes
    		db.setTransactionSuccessful();
    		// Toast.makeText(this, " 3 records were inserted", Toast.LENGTH_LONG).show();
    		txtMsg.append("\n-insertSomeDbData - 3 rec. were inserted");
    	}
    	catch (SQLiteException e2) {
    		//report problem 
    		Toast.makeText(this, e2.getMessage(), Toast.LENGTH_LONG).show();
    	}
    	finally {
    		db.endTransaction();
    	}

    }//insertSomeData
    
    /////////////////////////////////////////////////////////////////
    private void useRawQuery1() {
    	try {
    		//hard-coded SQL select with no arguments
			String mySQL ="select count(*) as Total from tblAMIGO";
			Cursor c1 = db.rawQuery(mySQL, null);
			int index = c1.getColumnIndex("Total");
			//advance to the next record (first rec. if necessary)
			c1.moveToNext();
			int theTotal = c1.getInt(index);
			// Toast.makeText(this, "Total1: " + theTotal, Toast.LENGTH_LONG).show();
			txtMsg.append("\n-useRawQuery1 - Total rec " + theTotal);
		} catch (Exception e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}    	
    }//useRawQuery1
    
    /////////////////////////////////////////////////////////////////
    private void useRawQuery2() {
    	try {    		 	
    		// ? arguments provided for automatic replacement
    		String mySQL = 	" select count(*) as Total "     			  
    						+ " from tblAmigo "
    						+ " where recID > ? "
    						+ "   and name  = ? ";
    		String[] args = {"1", "BBB"};
    		Cursor c1 = db.rawQuery(mySQL, args);

			int index = c1.getColumnIndex("Total");
			//advance to the next record (first rec. if necessary)
			c1.moveToNext();
			int theTotal = c1.getInt(index);
			// Toast.makeText(this, "Total2: " + theTotal, Toast.LENGTH_LONG).show();
			txtMsg.append("\n-useRawQuery2 Total rec " + theTotal);
		} catch (Exception e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}    	
    }//useRawQuery2
    
/////////////////////////////////////////////////////////////////
    private void useRawQuery3() {
    	try {    		 	
    		//arguments injected by manual string concatenation
    		String[] args = {"1", "BBB"};

    		String mySQL = " select count(*) as Total "       
    				  	  + "  from tblAmigo "
    		    		  + " where recID > " + args[0]
    		    		  + "   and name  = '" + args[1] + "'";

    		Cursor c1 = db.rawQuery(mySQL, null);

			int index = c1.getColumnIndex("Total");
			//advance to the next record (first rec. if necessary)
			c1.moveToNext();
			int theTotal = c1.getInt(index);
			// Toast.makeText(this, "Total3: " + theTotal, Toast.LENGTH_LONG).show();
			txtMsg.append("\n-useRawQuery3 - Total rec " + theTotal);
		} catch (Exception e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}    	
    }//useRawQuery3

/////////////////////////////////////////////////////////////////
    private void useSimpleQuery1() {
    	try {    		 	
    		//simple (implicit) query on one table.
    		//arguments: tableName, columns, condition, cond-args, 
    		//           groupByCol, havingCond, orderBy
    		String [] columns = {"recID", "name", "phone"};   		
    		Cursor c1 = db.query (	"tblAMIGO", 
    					columns, 
    					"recID > 2 and length(name) >= 3 and name like 'B%' ", 
    					null, null, null, 
    					"recID" );
			int theTotal = c1.getCount();

			// Toast.makeText(this, "Total4: " + theTotal, Toast.LENGTH_LONG).show();
			txtMsg.append("\n-useSimpleQuery1 - Total rec " + theTotal);
		} catch (Exception e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}    	
    }//useSimpleQuery1

/////////////////////////////////////////////////////////////////
    private void useSimpleQuery2() {
    	try {    		 	
    		//nontrivial 'simple query' on one table
    		String [] selectColumns =  {"name", "count(*) as TotalSubGroup"};
    		String    whereCondition = "recID >= ?";
    		String [] whereConditionArgs = {"1"};
    		String    groupBy = "name";
    		String    having =  "count(*) <= 4";
    		String    orderBy = "name";
    		        		
    		Cursor c =  db.query (
    						"tblAMIGO", 
    						selectColumns, 
    						whereCondition, whereConditionArgs, 
    		        		groupBy, 
    						having, 
    						orderBy  );
    		
			int theTotal = c.getCount();

			// Toast.makeText(this, "Total5: " + theTotal, Toast.LENGTH_LONG).show();
			txtMsg.append("\n-useSimpleQuery2 - Total rec " + theTotal);
		} catch (Exception e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}    	
    }//useSimpleQuery2   
    
/////////////////////////////////////////////////////////////////
    private void useCursor1() {
		try {
			// obtain a list of <recId, name, phone> from DB => "select * from tblAMIGO"
			String[] columns = { "recID", "name", "phone" };

			Cursor c = db.query("tblAMIGO", columns, 
					            null, null, null, null, "recID");
			
			int theTotal = c.getCount();
			// Toast.makeText(this, "Total6: " + theTotal, Toast.LENGTH_LONG).show();
			txtMsg.append("\n-useCursor1 - Total rec " + theTotal );
			txtMsg.append("\n");
			int idCol = c.getColumnIndex("recID");
			int nameCol = c.getColumnIndex("name");
			int phoneCol = c.getColumnIndex("phone");

			while (c.moveToNext()) {
				columns[0] = Integer.toString((c.getInt(idCol)));
				columns[1] = c.getString(nameCol);
				columns[2] = c.getString(phoneCol);

				txtMsg.append( columns[0] + " " 
						     + columns[1] + " "
						     + columns[2] + "\n" );
			}
		} catch (Exception e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
    }//useCursor1    
    
/////////////////////////////////////////////////////////////////////
    private void updateDB(){
    	//action query using execSQL
    	txtMsg.append("\n-updateDB");
    	
    	String theValue;
		try {
			theValue = "222";
			
			db.execSQL( "update tblAMIGO set name = (name || 'XXX') " 
	    			+   " where phone >= '" + theValue + "' " );
	    	
	    	useCursor1();
		} catch (Exception e) {
			Toast.makeText(this,"updateDB " + e.getMessage(), Toast.LENGTH_LONG).show();
		}  
    	useCursor1();
    }
    
////////////////////////////////////////////////////////////////////
    public void useInsertMethod() {
    	// an alternative to SQL "insert into table values(...)"
    	// ContentValues is a dynamic row-like container
    	txtMsg.append("\n-useInsertMethod");
    	ContentValues initialValues = new ContentValues();

    	initialValues.put("name", "ABC");
    	initialValues.put("phone", "101");
    	int rowPosition = (int) db.insert("tblAMIGO", null, initialValues);
    	txtMsg.append("\nrec added at: " + rowPosition);
    	
    	initialValues.put("name", "DEF");
    	initialValues.put("phone", "202");
    	rowPosition = (int) db.insert("tblAMIGO", null, initialValues);
    	txtMsg.append("\nrec added at: " + rowPosition);
    	
    	//testing .insert 2nd arg [nullColumnHack]
    	//if row is empty designated column is set to NULL
    	txtMsg.append("\nnullColumnHack test");
    	initialValues.clear();
    	
    	//next instruction should fail
    	rowPosition = (int) db.insert("tblAMIGO", null, initialValues);
    	txtMsg.append("\nrec added at: " + rowPosition);
    	
    	//next instruction should be OK
    	rowPosition = (int) db.insert("tblAMIGO", "name", initialValues);
    	txtMsg.append("\nrec added at: " + rowPosition);
    	
    	useCursor1();

    }// useInsertMethod
    
/////////////////////////////////////////////////////////////////////
    private void useUpdateMethod() {
    	//using the update method to change name of selected friend   	
    	String [] whereArgs = {"2", "7"};	

    	ContentValues updValues = new ContentValues();
    	updValues.put("name", "Maria");

    	int recAffected =	db.update( "tblAMIGO", 
    									updValues, 
    									"recID > ? and recID < ?", 
    									whereArgs );
    	// Toast.makeText(this, "Total7: " + recAffected, Toast.LENGTH_LONG).show();
    	txtMsg.append("\n-useUpdateMethod - Rec Affected " + recAffected);
    	useCursor1();
    }
    
/////////////////////////////////////////////////////////////////////
    private void useDeleteMethod() {
    	//using the delete method to remove a group of friends
    	//whose id# is between 2 and 7
    	String [] whereArgs = {"2", "7"};

    	int recAffected = db.delete("tblAMIGO", 
    			"recID > ? and recID < ?", whereArgs);

    	// Toast.makeText(this, "Total8: " + recAffected, Toast.LENGTH_LONG).show();
    	txtMsg.append("\n-useDeleteMethod - Rec affected " + recAffected);
    	useCursor1();
    }    
    
}//class