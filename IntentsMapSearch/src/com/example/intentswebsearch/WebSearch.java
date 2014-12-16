package com.example.intentswebsearch;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class WebSearch extends Activity {
	
	EditText source;
	EditText destination;
	Button search;
	String origen="";
	String destino="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_search);
        
        source = (EditText) findViewById(R.id.etName);
        search = (Button) findViewById(R.id.btnGo);
        
        destination = (EditText) findViewById(R.id.etName2);
        
        
        search.setOnClickListener(new OnClickListener(){
    		public void onClick(View v){
    			try{
    				origen = source.getText().toString();
    				destino = destination.getText().toString();
    			Intent intent = new Intent(android.content.Intent.ACTION_VIEW,Uri.parse(
    					"http://maps.google.com/maps?"
    					+"saddr="+origen+"&"
    					+"daddr="+destino));
    			startActivity(intent);
    			}catch(Exception e){
    				Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_LONG).show();
    			}
    		}
    	}); 
        
        
    }

}
