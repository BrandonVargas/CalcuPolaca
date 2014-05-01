package ztux.neocode.calculator;

import java.util.Stack;
import android.util.Log;

public class Eval {
	public String value(String str){  
		try{
	    //Entrada (Expresión en Postfija)
	    String expr = str; // 
	    String[] post = expr.split(" ");    
	    
	    //Declaración de las pilas
	    Stack<String> entrada = new Stack<String>(); //Pila entrada
	    Stack<String> temporal = new Stack<String>();//Pila de operandos

	    //Añadir post (array) a la Pila de entrada (E)
	    for (int i = post.length - 1; i >= 0; i--) {
	      entrada.push(post[i]);
	    }

	    //Algoritmo de Evaluación Postfija
	    String operadores = "+-*/()^!sctnlq";
	    String aux=entrada.pop();
	    if(entrada.isEmpty()){
  		  if(operadores.contains(""+aux)){
  			return "Error";  
  		  }else{
  			return aux;  
  		  }
  	  	}else{
  	  		entrada.push(aux);
  	  	}
	    while (!entrada.empty()) {
		      if (operadores.contains("" + entrada.peek())) {
		    	  char i=entrada.peek().charAt(0);
		    	  Double parc=0.0d;
		    	  Double parc1=0.0d;
		    	  Double parc2=0.0d;
		    	  Long parcf=(long) 0;
		    	  switch(i){
		    	  	case '+':
		    	  		parc=Double.parseDouble(temporal.pop())+ Double.parseDouble(temporal.pop());
		    	  		temporal.push(parc.toString());
		    	  		entrada.pop();
		    	  		break;
		    	  	case '-':
		    	  		parc=Double.parseDouble(temporal.pop());
		    	  		parc1=Double.parseDouble(temporal.pop());
		    	  		parc2=parc1-parc;
		    	  		temporal.push(parc2.toString());
		    	  		entrada.pop();
		    	  		break;
		    	  	case '*':
		    	  		parc=Double.parseDouble(temporal.pop())* Double.parseDouble(temporal.pop());
		    	  		temporal.push(parc.toString());
		    	  		entrada.pop();
		    	  		break;
		    	  	case '/':
		    	  		parc=Double.parseDouble(temporal.pop());
		    	  		parc1=Double.parseDouble(temporal.pop());
		    	  		parc2=parc1/parc;
		    	  		temporal.push(parc2.toString());
		    	  		entrada.pop();
		    	  		break;
		    	  	case '^':
		    	  		parc=Double.parseDouble(temporal.pop());
		    	  		parc1=Double.parseDouble(temporal.pop());
		    	  		parc2=Math.pow(parc1,parc);
		    	  		temporal.push(parc2.toString());
		    	  		entrada.pop();
		    	  		break;
		    	  	case 's':
		    	  		parc=Math.sin(Double.parseDouble(temporal.pop()));
		    	  		temporal.push(parc.toString());
		    	  		entrada.pop();
		    	  		break;
		    	  	case 'c':
		    	  		parc=Math.cos(Double.parseDouble(temporal.pop()));
		    	  		temporal.push(parc.toString());
		    	  		entrada.pop();
		    	  		break;
		    	  	case 't':
		    	  		parc=Math.tan(Double.parseDouble(temporal.pop()));
		    	  		temporal.push(parc.toString());
		    	  		entrada.pop();
		    	  		break;
		    	  	case 'n':
		    	  		if(Integer.parseInt(temporal.peek())>0){
		    	  		parc=Math.log(Double.parseDouble(temporal.pop()));
		    	  		temporal.push(parc.toString());
		    	  		entrada.pop();
		    	  		}else{
		    	  			return "Error";
		    	  		}
		    	  		break;
		    	  	case 'l':
		    	  		if(Integer.parseInt(temporal.peek())>0){
		    	  		parc=Math.log10(Double.parseDouble(temporal.pop()));
		    	  		temporal.push(parc.toString());
		    	  		entrada.pop();
		    	  		break;
		    	  		}else{
		    	  			return "Error";
		    	  		}
		    	  	case 'q':
		    	  		if(Integer.parseInt(temporal.peek())>=0){
			    	  		parc=Math.sqrt(Double.parseDouble(temporal.pop()));
			    	  		temporal.push(parc.toString());
			    	  		entrada.pop();
		    	  		}else{
		    	  			return "Error";
		    	  		}
		    	  		break;
		    	  	case '!':
		    	  		if(Integer.parseInt(temporal.peek())<=20){
			    	  		parcf=factorial((long)(Integer.parseInt(temporal.pop())));
			    	  		temporal.push(parcf.toString());
			    	  		entrada.pop();
		    	  		}else{
		    	  			return "Error";
		    	  		}
		    	  		break;
		    	  }
		      }else {
		        temporal.push(entrada.pop());
		      } 
		    }

	    //Mostrar resultados:(temporal.peek())
	    return temporal.peek();
		}catch(Exception e){
			Log.d("Error","No se pudo evaluar");
		}
		return "Error";
	  }

	public static long factorial(long N)
    {
        long multi = 1;
        for (long i = 1; i <= N; i++) {
            multi = multi * i;
        }
        return multi;
    }
	

}
