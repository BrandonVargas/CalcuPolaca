package ztux.neocode.calculator;

import android.app.Activity;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class ActivitySettings extends Activity {
	
	int nDecimals;
	EditText nD;
	RadioButton a,b;
	boolean isDefault;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_settings);
		nD = (EditText)findViewById(R.id.editTextnDec);
		//Oculto por defecto
		nD.setVisibility(View.INVISIBLE);
		nD.setText("3");
		nDecimals = 0;
	}
	
	public void onRadioButtonClicked(View view) {
	    // Is the button now checked?
	    boolean checked = ((RadioButton) view).isChecked();
	    
	    // Check which radio button was clicked
	    switch(view.getId()) {
	        case R.id.rbDefault:
	            if (checked){
	            	this.nD.setVisibility(View.INVISIBLE);
	                isDefault = true;
	                Log.d("RadioButton","DEFAULT");
	            }
	            break;
	        case R.id.rbSelect:
	            if (checked){
	            	Log.d("RadioButton","SELECT");
	                this.nD.setVisibility(View.VISIBLE);
	                isDefault = false;
	            }
	            break;
	    }
	}
	
	public void save(View v){
		//For decimals
		if(v.getId()==R.id.btnSave){
			if(!nD.getText().toString().equals("") && !isDefault){
	        	this.nDecimals = Integer.parseInt(nD.getText().toString());
	        	if(this.nDecimals<0){
	        		this.nDecimals = 2;
	        	}
	        }else{
	        	this.nDecimals=2;
	        }
		}
		Log.d("SAVE","nDecimals=> "+nDecimals);
		
		//Send the information to main Activity
		Intent i = new Intent();
		Bundle b = new Bundle();
		b.putInt("decimals", this.nDecimals);
		i.putExtras(b);
		setResult(RESULT_OK, i);
		finish();
	}
}
