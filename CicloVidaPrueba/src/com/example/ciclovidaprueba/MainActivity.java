package com.example.ciclovidaprueba;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
	
	TextView txtView1;
	EditText txtMsg1;
	Button btnFinish1;
	Button btnOk;
	String name="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        txtView1 = (TextView) findViewById(R.id.txtView1);
        txtMsg1 = (EditText) findViewById(R.id.txtMsg1);
        btnFinish1 = (Button) findViewById(R.id.btnFinish1);
        btnOk = (Button) findViewById(R.id.btnOk);
        
        txtView1.setText("Your name is: "+name);
        
    	btnOk.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				name = txtMsg1.toString();
				txtView1.setText(name);
				
			}
		});	
        
    	btnFinish1.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});	
    }

    protected void onPause(){
    	super.onPause();
    	name = txtMsg1.toString();
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
