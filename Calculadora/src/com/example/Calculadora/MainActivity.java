package com.example.Calculadora;

import com.example.nopetes.R;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
	
	public static final String MYPREFSID = "MyPrefs001";
	public static final int actMode = Activity.MODE_PRIVATE; 
	
	MediaPlayer mediaPlayer;
	TextView pantalla;
	Button uno,dos,tres,cuatro,cinco,seis,
	siete,ocho,nueve,cero,igual,mas,menos,mult,div,
	punto;
	String mostrar="";
	String operacion="empty";
	double num1=0;
	double num2=0;
	double res=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        pantalla = (TextView) findViewById(R.id.pantalla);
        uno = (Button) findViewById(R.id.button1);
        dos = (Button) findViewById(R.id.button2);
        tres = (Button) findViewById(R.id.button3);
        cuatro = (Button) findViewById(R.id.button4);
        cinco = (Button) findViewById(R.id.button5);
        seis = (Button) findViewById(R.id.button6);
        siete = (Button) findViewById(R.id.button7);
        ocho = (Button) findViewById(R.id.button8);
        nueve = (Button) findViewById(R.id.button9);
        cero = (Button) findViewById(R.id.button0);
        igual = (Button) findViewById(R.id.buttoneq);
        mas = (Button) findViewById(R.id.buttonsum);
        menos = (Button) findViewById(R.id.buttonres);
        mult = (Button) findViewById(R.id.buttonmult);
        div = (Button) findViewById(R.id.buttondiv);
        punto = (Button) findViewById(R.id.buttonpoint);
        
        mediaPlayer = MediaPlayer.create(this, R.raw.song);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(100,100);
        mediaPlayer.start();
        
        punto.setOnClickListener(new OnClickListener(){
    		public void onClick(View v){
    			mostrar=mostrar+".";
    			pantalla.setText(mostrar);
    		}
    	}	
    );        
        
        
        uno.setOnClickListener(new OnClickListener(){
        		public void onClick(View v){
        			mostrar=mostrar+"1";
        			pantalla.setText(mostrar);
        		}
        	}	
        );
        dos.setOnClickListener(new OnClickListener(){
	    		public void onClick(View v){
	    			mostrar=mostrar+"2";
	    			pantalla.setText(mostrar);
	    		}
	    	}	
        );
        
        tres.setOnClickListener(new OnClickListener(){
	    		public void onClick(View v){
	    			mostrar=mostrar+"3";
	    			pantalla.setText(mostrar);
	    		}
	    	}	
	    );

        cuatro.setOnClickListener(new OnClickListener(){
	    		public void onClick(View v){
	    			mostrar=mostrar+"4";
	    			pantalla.setText(mostrar);
	    		}
	    	}	
	    );

        cinco.setOnClickListener(new OnClickListener(){
	    		public void onClick(View v){
	    			mostrar=mostrar+"5";
	    			pantalla.setText(mostrar);
	    		}
	    	}	
	    );

        seis.setOnClickListener(new OnClickListener(){
	    		public void onClick(View v){
	    			mostrar=mostrar+"6";
	    			pantalla.setText(mostrar);
	    		}
	    	}	
	    );
        
        siete.setOnClickListener(new OnClickListener(){
	    		public void onClick(View v){
	    			mostrar=mostrar+"7";
	    			pantalla.setText(mostrar);
	    		}
	    	}	
	    );
        
        ocho.setOnClickListener(new OnClickListener(){
	    		public void onClick(View v){
	    			mostrar=mostrar+"8";
	    			pantalla.setText(mostrar);
	    		}
	    	}	
	    );
        
        nueve.setOnClickListener(new OnClickListener(){
	    		public void onClick(View v){
	    			mostrar=mostrar+"9";
	    			pantalla.setText(mostrar);
	    		}
	    	}	
	    );
        
        cero.setOnClickListener(new OnClickListener(){
	    		public void onClick(View v){
	    			mostrar=mostrar+"0";
	    			pantalla.setText(mostrar);
	    		}
	    	}	
	    );
        
        mas.setOnClickListener(new OnClickListener(){
	    		public void onClick(View v){
	    			if(mostrar.equals("")){
	    				pantalla.setText("Syntax error");
	    			}else{
	    			num1=Double.parseDouble(mostrar);
	    			mostrar="";
	    			operacion="suma";
	    			pantalla.setText("+");
	    			}
	    		}
	    	}	
	    );
        
        menos.setOnClickListener(new OnClickListener(){
	    		public void onClick(View v){
	    			if(mostrar.equals("")){
	    				pantalla.setText("Syntax error");
	    			}else{
	    			num1=Double.parseDouble(mostrar);
	    			mostrar="";
	    			operacion="resta";
	    			pantalla.setText("-");
	    			}
	    		}
	    	}	
	    );
        
        mult.setOnClickListener(new OnClickListener(){
	    		public void onClick(View v){
	    			if(mostrar.equals("")){
	    				pantalla.setText("Syntax error");
	    			}else{
	    			num1=Double.parseDouble(mostrar);
	    			mostrar="";
	    			operacion="multiplicacion";
	    			pantalla.setText("x");
	    			}
	    		}
	    	}	
	    );
        
        div.setOnClickListener(new OnClickListener(){
	    		public void onClick(View v){
	    			if(mostrar.equals("")){
	    				pantalla.setText("Syntax error");
	    			}else{
	    			num1=Double.parseDouble(mostrar);
	    			mostrar="";
	    			operacion="division";
	    			pantalla.setText("/");
	    			}
	    		}
	    	}	
	    );
        
        igual.setOnClickListener(new OnClickListener(){
	    		public void onClick(View v){
	    			if(operacion.equals("empty")){
	    				mostrar="";
	    				num1=0;
	    				num2=0;
	    				res=0;
		    			pantalla.setText("Syntax incorrect");
	    			}else if(operacion.equals("suma")){
	    				num2=Double.parseDouble(mostrar);
	    				res=num1+num2;
	    				pantalla.setText(Double.toString(res));
	    				mostrar="";
	    				num1=0;
	    				num2=0;
	    				res=0;
	    				operacion="empty";
	    			}else if(operacion.equals("resta")){
	    				num2=Double.parseDouble(mostrar);
	    				res=num1-num2;
	    				pantalla.setText(Double.toString(res));
	    				mostrar="";
	    				num1=0;
	    				num2=0;
	    				res=0;
	    				operacion="empty";
	    			}else if(operacion.equals("multiplicacion")){
	    				num2=Double.parseDouble(mostrar);
	    				res=num1*num2;
	    				pantalla.setText(Double.toString(res));
	    				mostrar="";
	    				num1=0;
	    				num2=0;
	    				res=0;
	    				operacion="empty";
	    			}else{
	    				num2=Double.parseDouble(mostrar);
	    				if(num2==0){
		    				pantalla.setText("Imposible dividir por cero");
		    				mostrar="";
		    				num1=0;
		    				num2=0;
		    				res=0;
		    				operacion="empty";	
	    				}else{
		    				res=num1/num2;
		    				pantalla.setText(Double.toString(res));
		    				mostrar="";
		    				num1=0;
		    				num2=0;
		    				res=0;
		    				operacion="empty";
	    				}
	    			}

	    		}
	    	}	
	    );
        
        
        		
        		
        //btnBegin.setOnClickListener(
        //		new OnClickListener(){
        //			public void onClick(View v){
        //				String userName = txtUserName.getText().toString();
        //				if(userName.compareTo("Maria Macarena")==0){
        //					labelUserName.setText("Ok,please wait...");
        //					Toast.makeText(getApplicationContext(),
        //							"Bienvenido"+userName,
        //							Toast.LENGTH_SHORT).show();
        //				}else{
        //					Toast.makeText(getApplicationContext(), "Invalid User",
        //							Toast.LENGTH_SHORT).show();
        //				}
        //			}
        //		}
        //		);
        		
    }
    
    
    protected void onPause(){
    	super.onPause();
    	SharedPreferences myPrefs = getSharedPreferences(MYPREFSID,actMode);
    	SharedPreferences.Editor myEditor = myPrefs.edit();
    	String temp = mostrar;
    	myEditor.putString("mydata",temp);
    	myEditor.commit();
    	mediaPlayer.stop();
    }
    
    protected void onStart(){
    	super.onStart();
    	SharedPreferences myPrefs = getSharedPreferences(MYPREFSID,actMode);
    	if((myPrefs != null)&&(myPrefs.contains("mydata"))){
    		String temp = myPrefs.getString("mydata","***");
    		pantalla.setText(temp);
    		mostrar = temp;
    	}
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