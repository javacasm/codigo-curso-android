package com.example.juegoparejas;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	Chronometer crono;
    TextView tvPuntos;
    Button btStart;
    Button btStop;
    TableLayout tl;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Guardamos accesos a los controles que vamos a usar para que se puedan modificar más facilmente
        
        crono=((Chronometer)findViewById(R.id.chronometer1));
        tvPuntos=(TextView)findViewById(R.id.textView1);
        tl=(TableLayout)findViewById(R.id.TableLayout1);
        btStart=(Button)findViewById(R.id.button1);
        btStop=(Button)findViewById(R.id.button2);
        initJuego();
    }
    
    int iPuntuacion=0;
    int [] rDibujos={R.drawable.conejo,R.drawable.oveja,R.drawable.pollo,R.drawable.rinoceronte,R.drawable.serpiente,R.drawable.tiburon};
    int [] rIVs={R.id.iv11,R.id.iv12,R.id.iv13,R.id.iv21,R.id.iv22,R.id.iv23,R.id.iv31,R.id.iv32,R.id.iv33,R.id.iv41,R.id.iv42,R.id.iv43};
    int [] cartas=new int[rIVs.length];
    int [] estadoCartas=new int[rIVs.length];
    
    int ciCartaAcertada=1;
    int ciCartaPendiente=0;
    
    
    
    void initJuego()
    {
    	// Inicializo todas las cartas a 0
    	for(int i=0;i<cartas.length;i++)
    	{
    		cartas[i]=0;
    		estadoCartas[i]=ciCartaPendiente;
    	}
    	// Relleno las cartas con el id de la imagen que van a contener
    	for(int i=0;i<rDibujos.length;i++)
   		{
    		int iRandomCarta;
    		
    		// Carta primera de la pareja
    		do
    		{
    			iRandomCarta=getRandomCarta();
    		}
    		while(cartas[iRandomCarta]!=0);
    		cartas[iRandomCarta]=rDibujos[i];
    		// Carta segunda de la pareja
    		do
    		{
    			iRandomCarta=getRandomCarta();
    		}
    		while(cartas[iRandomCarta]!=0);
    		cartas[iRandomCarta]=rDibujos[i];

   		}
    	
    	// Ponemos a 0 el temporizador y el mensaje de pulsar Start
    	stopCrono(null);
    	
    	// Inicializamos la puntuación
    	iPuntuacion=0;
    }
    
    int getPosicion(int ID)
    {
    	for(int i=0;i<rIVs.length;i++)
    		if(ID==rIVs[i])
    			return i;
    	return -1;
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    //http://stackoverflow.com/questions/1520887/how-to-pause-sleep-thread-or-process-in-android
    
    int iCartaPrimera;
    int iCartaSegunda;
    boolean bPrimeraCarta=true;
    ImageView ivCartaPrimera;
    ImageView ivCartaSegunda;
    boolean bEsperandoVolteo=false;
    boolean bJugando=false;
    public void pulsaImagen(View v)
    {
    	if(bJugando==false)
    		return;
    	if(bEsperandoVolteo)
    	{
    		ivCartaPrimera.setImageResource(R.drawable.interrogacion);
    		ivCartaSegunda.setImageResource(R.drawable.interrogacion);
    		
    	}
    	
    	// Busco que control han pulsado
    	int iPosicion=getPosicion(v.getId());
    	// Si ya la acerto no hago nada
    	if(estadoCartas[iPosicion]==ciCartaAcertada)
    		return;
    	
    	if(bPrimeraCarta)
    	{
    		// Volteo la carta 
    		ivCartaPrimera=((ImageView)v);
    		ivCartaPrimera.setImageResource(cartas[iPosicion]);
    		
    		//Guardo como primeraCarta
    		iCartaPrimera=iPosicion;
    		
    		bPrimeraCarta=false;
    	}
    	else
    	{
    		// Volteo la carta 
    		ivCartaSegunda=((ImageView)v);
    		ivCartaSegunda.setImageResource(cartas[iPosicion]);

    		// Guardo la segunda carta
    		iCartaSegunda=iPosicion;
    		
    		if(cartas[iCartaPrimera]==cartas[iCartaSegunda]) // Acierto
    		{
    			estadoCartas[iCartaPrimera]=ciCartaAcertada;
    			estadoCartas[iCartaSegunda]=ciCartaAcertada;
    			
    		}
    		else
    		{
    			bEsperandoVolteo=true;
	        	Handler handler = new Handler(); 
	        	handler.postDelayed(new Runnable() { 
	               public void run() {
            	   
                    ivCartaPrimera.setImageResource(R.drawable.interrogacion);
                    ivCartaSegunda.setImageResource(R.drawable.interrogacion);
                    bEsperandoVolteo=false;
                   }
	        	}, 2000); 
    		}
    		bPrimeraCarta=true;
    	}

    	
    }
    int getRandomCarta()
    {
    	return(int)(Math.random()*cartas.length);
    }
    
//    int getRandomId()
//    {
//    	int iValor=(int)(Math.random()*rDibujos.length);
//		return rDibujos[iValor];
//    }
    
    public void startCrono(View v)
    {
    	crono.setBase(SystemClock.elapsedRealtime());
    	initJuego();
    	crono.start();
    	btStart.setVisibility(View.INVISIBLE);
    	btStop.setVisibility(View.VISIBLE);
    	tl.setVisibility(View.VISIBLE);
    	bJugando=true;
    }
    
    public void stopCrono(View v)
    {
    	crono.stop();
    	Resources res=getResources();
    	tvPuntos.setText(res.getString(R.string.pulseparajugar));
    	bJugando=false;
    	
    	btStart.setVisibility(View.VISIBLE);
    	btStop.setVisibility(View.INVISIBLE);
    //	tl.setVisibility(View.INVISIBLE);
    	long elapsedMillis = SystemClock.elapsedRealtime() - crono.getBase();
    }
    
}
