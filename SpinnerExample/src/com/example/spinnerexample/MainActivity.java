package com.example.spinnerexample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends Activity{
	
	TextView txtMsg;
	Spinner spin;
	String[] items =  { "Agua", "Vino", "Refresco", "Nada",};

	
    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        txtMsg = (TextView) findViewById(R.id.txtMsg);
        spin = (Spinner) findViewById(R.id.my_spinner);
        
        spin.setOnItemSelectedListener(new OnItemSelectedListener() {
        	 @Override
        	 public void onItemSelected(AdapterView<?> container, View row,
        	 int position, long id) {
        	 txtMsg.setText(items[position]);
        	 }
        	 public void onNothingSelected(AdapterView<?> container) {
        	 txtMsg.setText("");
        	 }
        	 });
        ArrayAdapter<String> aa = new ArrayAdapter<String>(
        		 this,
        		 android.R.layout.simple_spinner_item,
        		 items);
        aa.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        spin.setAdapter(aa);

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
