package com.example.alberto.u5_menucamara;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class U5_MenuCamara extends AppCompatActivity {
    TextView tvCamara;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u5__menu_camara);
        tvCamara=(TextView) findViewById(R.id.tvCamara);
        registerForContextMenu(tvCamara);
    }
   @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();

        if (v.getId() == R.id.tvCamara)
            inflater.inflate(R.menu.menu_contextual, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if(item.getItemId()==R.id.menu_camara){
            chamarCamara();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    public void chamarCamara(){
        startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                if (data == null) {
                    Log.i("FALLO", "Non hai imaxe.");
                    return;
                }
                ImageView imgview = (ImageView) findViewById(R.id.tvImaxe);
                imgview.setImageBitmap((Bitmap) data.getExtras().get("data"));

            } else if (resultCode == RESULT_CANCELED) {
                Log.i("FALLO","Cancelouse a foto.");
            } else {
                Log.i("FALLO","Error sacando a foto.");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_u5__menu_camara, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.salir) {
            System.exit(1);
            return true;
        }

        if(id==R.id.camara){
            chamarCamara();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
