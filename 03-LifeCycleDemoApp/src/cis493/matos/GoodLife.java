package cis493.matos;
// important link -  changing background color
// http://android-er.blogspot.com/2009/08/change-background-color-by-seekbar.html
/////////////////////////////////////////////////////////////////////////////////

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

public class GoodLife extends Activity {
	public static final String MYPREFSID = "MyPrefs001";
	public static final int actMode = Activity.MODE_PRIVATE;
	
	LinearLayout myScreen;
	EditText txtColorSelect;
	TextView txtToDo;
	Button	btnFinish;

	int x = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        myScreen = (LinearLayout) findViewById(R.id.myScreen);        
        
        txtToDo = (TextView) findViewById(R.id.txtToDo);
		String msg = "Instructions:                	               \n "
				+ "0. New instance (onCreate, onStart, onResume)   \n "
				+ "1. Back Arrow   (onPause, onStop, onDestroy)    \n "
				+ "2. Finish       (onPause, onStop, onDestroy)    \n "
				+ "3. Home		 (onPause, onStop)	               \n "
				+ "4. After 3 > App Tab > re-execute current app   \n "
				+ "    (onRestart, onStart, onResume)		       \n "
				+ "5. Run DDMS > Receive a phone call or SMS	   \n "
				+ "    (onRestart, onStart, onResume)		       \n "
				+ "6. Enter some data - repeat steps 1-5   	       \n ";

		txtToDo.setText(msg);
        
		txtColorSelect = (EditText) findViewById(R.id.txtColorSelect);
		// you may want to skip discussing the listener until later
		txtColorSelect.addTextChangedListener(new TextWatcher(){        	
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub	
			}
			
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub				
			}
			
			public void afterTextChanged(Editable s) {			
				changeBackgroundColor(s.toString());
			}
		});
        
        btnFinish = (Button) findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				finish();								
			}        	
        });
        
        Toast.makeText(getApplicationContext(), "onCreate", Toast.LENGTH_SHORT).show();
    }

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		updateFromSavedState();
		Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();

        Log.d("GoodLife", "OnResume [x]=" + x);		
	}
	
    @Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		saveDataFromCurrentState();
		Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
    }

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();		
		Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();

		x = 666;
        Log.d("GoodLife", "OnStop [x]=" + x);		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
	}


    ///////////////////////////////////////////////////////////////////////////////
	protected void saveDataFromCurrentState() {
		SharedPreferences myPrefs = getSharedPreferences(MYPREFSID, actMode);
		SharedPreferences.Editor myEditor = myPrefs.edit();
		myEditor.putString("myBkColor", txtColorSelect.getText().toString());
		myEditor.commit();
	}
    
	protected void updateFromSavedState() {
		SharedPreferences myPrefs = getSharedPreferences(MYPREFSID, actMode);
		
		if ((myPrefs != null) && (myPrefs.contains("myBkColor"))) {
			String theChosenColor = myPrefs.getString("myBkColor", "");
			txtColorSelect.setText(theChosenColor);
			changeBackgroundColor(theChosenColor);
			}		
	}
	
	protected void clearMyPreferences() {
		SharedPreferences myPrefs = getSharedPreferences(MYPREFSID, actMode);
		SharedPreferences.Editor myEditor = myPrefs.edit();
		myEditor.clear();
		myEditor.commit();
	}
	////////////////////////////////////////////////////////////////////////////
	
	private void changeBackgroundColor (String theChosenColor){
		// change background color
		if (theChosenColor.contains("red"))
			myScreen.setBackgroundColor(0xffff0000);
		else if (theChosenColor.contains("green"))
			myScreen.setBackgroundColor(0xff00ff00);
		else if (theChosenColor.contains("blue"))
			myScreen.setBackgroundColor(0xff0000ff);
		else {
			//reseting user preferences
			clearMyPreferences();
			myScreen.setBackgroundColor(0xff000000);
		}
	}
	////////////////////////////////////////////////////////////////////////////

/*		
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("x", x);
		Toast.makeText(getBaseContext(),"onSaveInstanceState ...BUNDLING", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		x = savedInstanceState.getInt("x");
		Toast.makeText(getBaseContext(), "onRestoreInstanceState ...BUNDLING", Toast.LENGTH_SHORT).show();
	}
*/
	
}