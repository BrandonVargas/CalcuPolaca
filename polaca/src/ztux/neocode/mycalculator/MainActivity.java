package ztux.neocode.mycalculator;

import ztux.neocode.calculator.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class MainActivity extends Activity{
	private int egg; // I'm bored =)
	private Button delete;
	private EditText et;
	private String $input; //Entrada del EditTex
	private int identificador;
	private RadioButton rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText)findViewById(R.id.input);
        $input = "";
        et.setText($input);
        et.setSelection(et.getText().length());
        
        /*Por default tenemos a la notacio prefija*/
        rb = (RadioButton)findViewById(R.id.rbPre);
        rb.setChecked(true);
        this.identificador = 0;
        
        //En caso de que se mantenga presionado el boton de borrar
        delete = (Button)findViewById(R.id.borra);
        delete.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				et.setText("");
				return false;
			}
		});
        
        
        
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
    	}
    	return true;
    }
    
    //Menu Items
    private void aboutMessage(){
    	new AlertDialog.Builder(this)
    	.setTitle("About")
    	.setMessage("MyCalculator Version 1.0")
    	.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {}
		}).show();
    }
    
    public void egg(View v){
    	if(v.getId()==R.id.egg){
    		this.egg++;
    	}
    	if(this.egg==10){
			Toast t = Toast.makeText(this,
					"Why Do Programmers Think that Halloween and Christmas are the Same Day?\n"
					+ "Because 31OCT = 25DEC.",
					Toast.LENGTH_LONG);//Egg :3
			t.show();
			this.egg=0;
    	}
    }
    
    
    /*Radio buttons
     * 
     */
    public void onRadioButtonClicked(View v){
    	boolean checked = ((RadioButton)v).isChecked();
    	switch(v.getId()){
    	case R.id.rbPre:
    		if(checked){
    			/*Si se elige notacion prefija*/
    			this.identificador = 0;
    		}
    		break;
    	case R.id.rbPos:
    		if(checked){
    			/*Si se elige notacion posfija*/
    			this.identificador = 1;
    		}
    		break;
    	}
    }
    
    /**
     * Lectura de las teclas
     */
    public void setTextInput(View v){
    	
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
    	if(v.getId()==R.id.btn_igual){
	    	//This string gets a temporal value
	    	String ans="";
	    	if(!et.getText().toString().equals("")){
		    	Pila p = new Pila();
		    	if(p.Test( et.getText().toString(),this.identificador)){
		    		ans = p.eval(this.identificador);
		    		if(ans.equals("Error")){
		    			Toast t = Toast.makeText(this,"Error!",Toast.LENGTH_LONG);
			    		t.show();
		    		}else{
			    		this.$input = ans;
			    		/*String val = this.$input+"";
			    		BigDecimal big = new BigDecimal(val);
			    		big = big.setScale(nDecimals, RoundingMode.HALF_UP);
			    		this.$input = big.toString();*/
			    		if(ans!=null){
			    			et.setText(this.$input);
			    		}
		    		}
		    	}else{
		    		Toast t = Toast.makeText(this,"Error!",Toast.LENGTH_LONG);
		    		t.show();
		    	}
		    	et.setSelection(et.getText().length());
	    	}
    	}
    }
   
}
