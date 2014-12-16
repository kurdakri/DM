package com.example.intentsexchanging;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends Activity {
	TextView txtView2;
	EditText edtText2;
	Button send2;
	String text;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.main2);
		txtView2 = (TextView) findViewById(R.id.txtview2);
		edtText2 = (EditText) findViewById(R.id.etName2);
		send2 = (Button) findViewById(R.id.btnSend2);
		
		Intent myCallerIntentHandler = getIntent();
		Bundle myBundle = myCallerIntentHandler.getExtras();
		String paramString = myBundle.getString("message");
		txtView2.setText(paramString);


		
		send2.setOnClickListener(new Clicker());
	}//onCreate
	
    private class Clicker implements OnClickListener{
    	public void onClick(View v){
    		Intent myCallerIntentHandler = getIntent();
    		Bundle myBundle = myCallerIntentHandler.getExtras();
    		text = edtText2.getText().toString();
    		myBundle.putString("message2", text);
    		myCallerIntentHandler.putExtras(myBundle);
    		setResult(Activity.RESULT_OK,myCallerIntentHandler);
    		finish();
    	}
    }
	
}
