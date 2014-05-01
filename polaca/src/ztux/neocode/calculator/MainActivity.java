package ztux.neocode.calculator;


import java.math.BigDecimal;
import java.math.RoundingMode;

import android.util.Log;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity implements OnLongClickListener{
	
	Button btn, delete;
	EditText et;
	String $input; //Entrada del EditTex
	
	int nDecimals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText)findViewById(R.id.input);
        $input = "";
        et.setText($input);
        et.setSelection(et.getText().length());
        //et.setInputType(InputType.TYPE_NULL); //Quitamos el teclado virtual
        
//		Toast t = Toast.makeText(this,"Calculator",Toast.LENGTH_LONG);
//		t.setGravity(Gravity.TOP, 0,0);
//		t.show();
        btn = (Button)findViewById(R.id.egg);
        btn.setOnLongClickListener(this);
        
        //En caso de que se mantenga presionado el boton de borrar
        delete = (Button)findViewById(R.id.borra);
        delete.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				et.setText("");
				return false;
			}
		});
        
        this.nDecimals = 2;
    }
    
    /**
     * Menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.main, menu);
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	super.onOptionsItemSelected(item);
    	switch(item.getItemId()){
    	case R.id.about:
    		aboutMessage();
    		break;
    	case R.id.settings:
    		settings();
    		break;
    	}
    	return true;
    }
    
    //Menu Items
    private void aboutMessage(){
    	new AlertDialog.Builder(this)
    	.setTitle("About")
    	.setMessage("Calculator Version 1.0")
    	.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {}
		}).show();
    }
    
    @Override
    public boolean onLongClick(View v) {
    	//Egg :3
    	switch(v.getId()){
    		case R.id.egg:
    			Toast t = Toast.makeText(this,
    					"Why Do Programmers Think that Halloween and Christmas are the Same Day?\n"
    					+ "Because 31OCT = 25DEC.",
    					Toast.LENGTH_LONG);
    			t.show();
    	}
    	return false;
    }
    
    private void settings(){
    	Log.d("ZTUX","Ajustes de la calculadora");
    	//Intent i = new Intent("ztux.neocode.calculator.ActivitySettings");
    	//this.startActivity(i);
    	
    	Intent i= new Intent();
    	i.setClass(this,ActivitySettings.class);
    	//startActivity(i);
    	startActivityForResult(i, 1); //RequestCode es 1
    }
    
    /**
     * Lectura de las teclas
     */
    public void setTextInput(View v){
    	Log.d("Info","N decimales"+ nDecimals);
    	//Si hay mensaje de Error, limpiamos la cadena
    	if(this.$input=="Error"){
    		this.$input = "";
    	}
    	//En caso de que se haya borrado la cadena del input
    	//al dejar presionado, seteamos la cadena a vacio.
    	if(this.et.getText().toString().equals("")){
    			this.$input="";
    	}
    	switch(v.getId()){
    	case R.id.btn_0:
    		this.$input+="0";
    		break;
    	case R.id.btn_1:
    		this.$input+="1";
    		break;
    	case R.id.btn_2:
    		this.$input+="2";
    		break;
    	case R.id.btn_3:
    		this.$input+="3";
    		break;
    	case R.id.btn_4:
    		this.$input+="4";
    		break;
    	case R.id.btn_5:
    		this.$input+="5";
    		break;
    	case R.id.btn_6:
    		this.$input+="6";
    		break;
    	case R.id.btn_7:
    		this.$input+="7";
    		break;
    	case R.id.btn_8:
    		this.$input+="8";
    		break;
    	case R.id.btn_9:
    		this.$input+="9";
    		break;
    	case R.id.btn_punto:
    		this.$input+=".";
    		break;
    	case R.id.btn_cos:
    		this.$input+="cos(";
    		break;
    	case R.id.btn_sin:
    		this.$input+="sin(";
    		break;
    	case R.id.btn_tan:
    		this.$input+="tan(";
    		break;
    	case R.id.btn_pi:
    		this.$input+="PI";
    		break;
    	case R.id.btn_factorial:
    		this.$input+="!";
    		break;
    	case R.id.btn_ln:
    		this.$input+="ln(";
    		break;
    	case R.id.btn_log:
    		this.$input+="log(";
    		break;
    	case R.id.btn_e:
    		this.$input+="e";
    		break;
    	case R.id.btn_potencia:
    		this.$input+="^";
    		break;
    	case R.id.btn_raiz:
    		this.$input+="sqrt(";
    		break;
    	case R.id.btn_pAbre:
    		this.$input+="(";
    		break;
    	case R.id.btn_pCierra:
    		this.$input+=")";
    		break;
    	case R.id.btn_suma:
    		this.$input+="+";
    		break;
    	case R.id.btn_resta:
    		this.$input+="-";
    		break;
    	case R.id.btn_mult:
    		this.$input+="*";
    		break;
    	case R.id.btn_div:
    		this.$input+="/";
    		break;
	 		
    	}

    	et.setText(this.$input);
    	//Movemos el cursor al final
    	et.setSelection(et.getText().length());
    }
	
    /**
	 * Limpia el EditText
	 */
    public void clearInput(View v){
    	this.$input ="";
    	et.setText($input);
    }
    
    /**
     * Borra un elemento y regresa el cursor
     */
    public void back(View v){
    	String str = et.getText().toString().trim();
    	if(str.length()!=0){
    		str  = str.substring( 0, str.length() - 1 ); 
    		this.$input = str;
    	    et.setText (str);
    	}
    	et.setSelection(et.getText().length());
    }
    
    public void eval(View v){
    	//This string gets a temporal value
    	Log.d("ANTES DE=> ",et.getText().toString());
    	String ans="";
    	if(!et.getText().toString().equals("")){
	    	Pila p = new Pila();
	    	if(p.Test(et.getText().toString())){
	    		//Log.d("PILA","Expresion correcta");
	    		ans = p.eval();
	    		if(ans.equals("Error")){
	    			Toast t = Toast.makeText(this,"Error!",Toast.LENGTH_LONG);
		    		t.show();
	    		}else{
		    		this.$input = ans;
		    		String val = this.$input+"";
		    		BigDecimal big = new BigDecimal(val);
		    		big = big.setScale(nDecimals, RoundingMode.HALF_UP);
		    		this.$input = big.toString();
		    		if(ans!=null){
		    			et.setText(this.$input);
		    		}
	    		}
	    	}else{
	    		Toast t = Toast.makeText(this,"Error!",Toast.LENGTH_LONG);
	    		t.show();
	    	}
	    	et.setSelection(et.getText().length());
	    	Log.d("DESPUES DE=> ",this.$input);
    	}
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	Log.d("RESULT","OK =)");
    	super.onActivityResult(requestCode, resultCode, data);
    	if (requestCode == 1) {
        	switch(resultCode){
        	case RESULT_OK:
        		this.nDecimals = data.getIntExtra("decimals", 0);
        		Log.d("Info","N decimales"+ nDecimals);
        		break;
        	case RESULT_CANCELED:
        		break;
        	} 
    	}
    }
}
