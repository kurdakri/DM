package com.example.intentsexchanging;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Activity1 extends Activity {
	TextView txtView;
	EditText edtText;
	Button send;
	String text;
	
	private final int IPC_ID = (int)(10001*Math.random());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
        	setContentView(R.layout.main1);
        	txtView = (TextView) findViewById(R.id.txtView);
        	edtText = (EditText) findViewById(R.id.etName);
        	send = (Button) findViewById(R.id.btnSend);
        	
        	//Listener on send
        	send.setOnClickListener(new Clicker());
        }catch(Exception e){
        	Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }//onCreate

    private class Clicker implements OnClickListener{
    	public void onClick(View v){
    		try{
    			Intent myIntentA1A2 = new Intent(Activity1.this,Activity2.class);
    			Bundle toSend = new Bundle();
    			text = edtText.getText().toString();
    			toSend.putString("message", text);
    			myIntentA1A2.putExtras(toSend);
    			startActivityForResult(myIntentA1A2,IPC_ID);
    			
    		}catch(Exception e){
    			Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
    		}
    		
    	}
    }
    
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
    	super.onActivityResult(requestCode, resultCode, data);
    	try{
    		if(IPC_ID == requestCode){
    			if(resultCode == Activity.RESULT_OK){
    				Bundle myReturnedData = data.getExtras();
    				String myString = myReturnedData.getString("message2");
    				txtView.setText(myString);
    			}else{
    				txtView.setText("CANCELLED!");
    			}
    		}
    	}catch(Exception e){
    		Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
    	}
    }
}
