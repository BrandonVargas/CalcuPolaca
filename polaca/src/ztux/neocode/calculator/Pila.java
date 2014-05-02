package ztux.neocode.calculator;
import java.util.ArrayList;
import java.util.Stack;

import android.util.Log;

public class Pila {
	//DeclaraciÃ³n de las pilas
    Stack<String> entrada = new Stack<String>(); //Pila entrada
    Stack<String> temporal = new Stack<String>(); //Pila temporal para operadores
    Stack<String> salida = new Stack<String>(); //Pila salida
	
    public boolean Test(String exp){
    //Depurar la expresion
    String expr = depurar(exp);
    String[] arrayInfix = expr.split(" ");
    
    ArrayList<String> expresion= new ArrayList<String>();
    for(String o: arrayInfix){
  	  expresion.add(o);
    }
    
    //Depuracion de signos + y - al inicio de la expresion
    if(expresion.get(1).equals("-")||expresion.get(1).equals("+")){
  	  expresion.add(0,"0");
    }
    //Depuracion de numeros con signo -
    for(int i=0;i<=expresion.size()-2;i++){
  	  if((expresion.get(i).equals("*")||expresion.get(i).equals("/"))&&(expresion.get(i+1).equals("-"))){
  		  expresion.add(i+2,"-"+expresion.get(i+2));
      	  expresion.remove(i+1);
      	expresion.remove(i+2);
        }
    }

    //Anadir el array a la Pila de entrada
    for (int i = expresion.size() - 1; i >= 0; i--) {
    	entrada.push(expresion.get(i));
    }
    try {
      //Algoritmo Infijo a Postfijo
      while (!entrada.empty()) {
        switch (pref(entrada.peek())){
          case 1:
            temporal.push(entrada.pop());
            break;
          case 2:
            while(!temporal.peek().equals("(")) {
              salida.push(temporal.pop());
            }
            temporal.pop();
            entrada.pop();
            break; 
          case 3:
          case 4:
            while(pref(temporal.peek()) >= pref(entrada.peek())) {
              salida.push(temporal.pop());
            }
            temporal.push(entrada.pop());
            break; 
          case 5:
            while(pref(temporal.peek()) >= pref(entrada.peek())) {
              salida.push(temporal.pop());
            }
            temporal.push(entrada.pop());
            break;
          default:
            salida.push(entrada.pop()); 
        } 
      } 
      
      return true; //la expresion es correcta
    }catch(Exception ex){ 
    	Log.e("Error en la expresión","Verifica");
    }
	return false;   
}

  //Depurar expresion
  private static String depurar(String s) {
	String e= ""+Math.E;
	String pi=""+Math.PI;
    s = s.replaceAll("\\s+", "").replaceAll("--","+").replaceAll("\\+\\+","+").replaceAll("\\+-","-").replaceAll("-\\+","-").replaceAll("cos","c").replaceAll("sin","s").replaceAll("tan","t").replaceAll("ln","n").replaceAll("log","l").replaceAll("sqrt","q").replaceAll("e",e).replaceAll("PI",pi); //Elimina espacios en blanco
    s = "(" + s + ")";
    String simbols = "+-*/()^!sctnlq";
    String str = "";
    //Deja espacios entre operadores
    for (int i = 0; i < s.length(); i++) {
      if (simbols.contains("" + s.charAt(i))) {
        str += " " + s.charAt(i) + " ";
      }else str += s.charAt(i);
    }
    return str.replaceAll("\\s+", " ").trim();
  } 

  //Jerarquia de los operadores
  private static int pref(String op) {
    int prf = 0;
    if (op.equals("^")) prf = 5;
    if (op.equals("*") || op.equals("/")) prf = 5;
    if (op.equals("+") || op.equals("-")) prf = 4;
    if (op.equals("c") || op.equals("s")|| op.equals("t")|| op.equals("n")|| op.equals("l")|| op.equals("q")|| op.equals("!")) prf= 3;
    if (op.equals(")")) prf = 2;
    if (op.equals("(")) prf = 1;
    return prf;
  }
  public String eval(int id){
	  ArrayList<String> expresion= new ArrayList<String>();
      String exp= new String();
      for(String o: this.salida){
    	  expresion.add(o);
      }
      if(expresion.size()>=2){
	      for(int i=0;i<=expresion.size()-1;i++){
	    	  Log.d("Expresion antes",expresion.get(i));
	      }
	      
	      if(expresion.get(1).equals("-")||expresion.get(1).equals("+")){
	    	  expresion.add(0,"0");
	      }
	      for(int i=0;i<=expresion.size()-2;i++){
	    	  if((expresion.get(i+1).equals("*")||expresion.get(i+1).equals("/"))&&(expresion.get(i).equals("-"))){
	        	  Log.d("Aqui","si entre");
	    		  expresion.add(i,"-"+expresion.get(i-2));
	        	  expresion.remove(i+2);
	          }
	      }
	      for(int i=0;i<=expresion.size()-1;i++){
	    	  exp=exp + expresion.get(i) + " ";
	      }
	      Log.d("Expresion",exp);
	      
      }
      if(id==0){
    	  String expV="";
    	  for(int i=exp.length();i>=0;i--){
    		  expV=expV+exp.charAt(i);
    	  }
    	  return expV;
      }else{
    	  return exp;
      }
      
     
  }
	
}
