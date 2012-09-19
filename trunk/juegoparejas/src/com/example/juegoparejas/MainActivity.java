package com.example.juegoparejas;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    
    int [] rIDs={R.drawable.conejo,R.drawable.oveja,R.drawable.pollo,R.drawable.rinoceronte,R.drawable.serpiente,R.drawable.tiburon};
    int [] rIVs={R.id.iv11,R.id.iv12,R.id.iv13,R.id.iv21,R.id.iv22,R.id.iv23,R.id.iv31,R.id.iv32,R.id.iv33,R.id.iv41,R.id.iv42,R.id.iv43};
    int [] imagenes=new int[rIVs.length];
    
    
    void initJuego()
    {
    	for(int i=0;i<rIVs.length;i++)
    		imagenes[i]=getRandomId();
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
    
    View vOld=null;
    public void pulsaImagen(View v)
    {
    	if(vOld!=null)
    		((ImageView)vOld).setImageResource(R.drawable.interrogacion);
    	int iPosicion=getPosicion(v.getId());
    	if(iPosicion>=0){
    	((ImageView)v).setImageResource(rIDs[iPosicion]);
    	vOld=v;
    	}
    }
    
    int getRandomId()
    {
    	int iValor=(int)(Math.random()*rIDs.length);
		return rIDs[iValor];
    }
    
}
