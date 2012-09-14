package com.foc.calculadora;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	
	enum eEstado { Operando1,Operando2,Operador};
    enum eOperador { suma,resta,division,multiplicacion};
	
    eEstado estadoCalculadora; 
    eOperador operador;
    float operando1;
    float operando2;
    int iNumeroPuntos=0;
    TextView tv;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    	View vt=findViewById(R.id.textView1);
    	tv=(TextView)vt;
    	
    	inicializar();
    }
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void inicializar()
    {
    	estadoCalculadora=eEstado.Operando1;
    	tv.setText("");
    	operando1=0;
    	operando2=0;
    	iNumeroPuntos=0;
    }
    
    float getValorFloatPantalla()
    {
    	String sTV=tv.getText().toString();
    	float valor=Float.parseFloat(sTV);
    	return valor;
    }
    
    void cambiaEstadoOperador()
    {
    	
		estadoCalculadora=eEstado.Operador;
    	switch(operador)
    	{
    	case suma:
    		break;
    	case resta:
    		break;
    	case multiplicacion:
    		break;
    	case division:
    		break;
    	}
    	operando1=getValorFloatPantalla();
    	tv.setText("");
    	iNumeroPuntos=0;
    	estadoCalculadora=eEstado.Operando2;
		
    }
    
    public void botonclic(View vBoton)
    {
    	switch(vBoton.getId())
    	{
    	case R.id.bPunto:
    		if(iNumeroPuntos==0)
    		{
    			iNumeroPuntos=iNumeroPuntos+1;
	        	String sTV=tv.getText().toString();
	        	Button bt=(Button)vBoton;
	        	
	        	tv.setText(sTV+bt.getText().toString());    			
    		}
    		break;
    	case R.id.b0:
    	case R.id.b1:
    	case R.id.b2:
    	case R.id.b3:
    	case R.id.b4:
    	case R.id.b5:
    	case R.id.b6:
    	case R.id.b7:
    	case R.id.b8:
    	case R.id.b9:
    		{
	        	String sTV=tv.getText().toString();
	        	Button bt=(Button)vBoton;
	        	
	        	tv.setText(sTV+bt.getText().toString());
    		}
        	break;
    		
    	case R.id.bDivision:
    		operador=eOperador.division;
    		
    		cambiaEstadoOperador();
    		break;
    	case R.id.bMultiplicacion:
    		operador=eOperador.multiplicacion;
    		cambiaEstadoOperador();
    		break;
    	case R.id.bSuma:
    		operador=eOperador.suma;
    		cambiaEstadoOperador();
    		break;
    	case R.id.bResta:
    		operador=eOperador.resta;
    		cambiaEstadoOperador();
    		break;
    	case R.id.bIgual:
	    	{
		    	if(estadoCalculadora==eEstado.Operando2)
		    	{
		        	float valor=getValorFloatPantalla();
		        	operando2=valor;
		        	float resultado=0;
		        	if(operador==eOperador.suma)
		        		resultado=operando1+operando2;
		        	else  if (operador==eOperador.resta)
		        		resultado=operando1-operando2;
		        	else if (operador==eOperador.multiplicacion)
		        		resultado=operando1*operando2;
		        	else if(operador==eOperador.division)
		        		resultado=operando1/operando2;
		        	
		        	tv.setText(String.valueOf(resultado));
		        	estadoCalculadora=eEstado.Operando1;
		    	}
	    	}
	    	break;
    	case R.id.bC:
    		inicializar();
    		break;
    	}

    }
}
