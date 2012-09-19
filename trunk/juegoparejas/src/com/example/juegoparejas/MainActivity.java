package com.example.juegoparejas;

import java.util.Random;

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
    int [][]mTileGrid;
    int mXTileCount=3;
    int mYTileCount=4;
    
    int [] rIDs={R.drawable.conejo,R.drawable.oveja,R.drawable.pollo,R.drawable.rinoceronte,R.drawable.serpiente,R.drawable.tiburon};
    
    
    void initJuego()
    {
    	mTileGrid = new int[mXTileCount][mYTileCount];
    	for(int i=0;i<mXTileCount;i++)
    		for(int j=0;i<mYTileCount;j++)
    			mTileGrid[i][j]=getRandomId();
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
    	((ImageView)v).setImageResource(getRandomId());
    	vOld=v;
    }
    
    int getRandomId()
    {
    	int iValor=(int)(Math.random()*rIDs.length);
		return rIDs[iValor];
    }
    
}
