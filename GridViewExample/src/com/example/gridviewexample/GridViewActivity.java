package com.example.gridviewexample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;


public class GridViewActivity extends Activity  {

	TextView txtMsg;
	String[] items = { "Data-0", "Data-1", "Data-2", "Data-3", "Data-4", "Data-5", "Data-6", "Data-7" };
	
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.gridviewactivity);
		
		txtMsg = (TextView) findViewById(R.id.txtMsg);

		GridView grid = (GridView) findViewById(R.id.grid);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				this,
				android.R.layout.simple_list_item_1,
				android.R.id.text1,
				items );

		grid.setAdapter(adapter);
		
		grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> container, View v, int position, long id) {
				txtMsg.setText(items[position]);
			}
		});
		
	}//onCreate
	
	
}// class
