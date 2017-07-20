package ssroman.mypasswords;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class BuscarPW extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_pw);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final AyudaBD ayudabd = new AyudaBD(getApplicationContext());

        Button buscaBD = (Button)findViewById(R.id.btnBuscarBD);

        buscaBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText NombreBuscar = (EditText)findViewById(R.id.etIDBuscar);
                EditText PWBucar = (EditText)findViewById(R.id.etpasswordBuscar);
                SQLiteDatabase db = ayudabd.getReadableDatabase();
                String[] argsel = {NombreBuscar.getText().toString()};
                String[] projection = {AyudaBD.DatosTabla.COLUMNA_IDENTIFICADOR, AyudaBD.DatosTabla.COLUMNA_PASSWORD};
                Cursor c = db.query(AyudaBD.DatosTabla.NOMBRE_TABLA, projection, AyudaBD.DatosTabla.COLUMNA_IDENTIFICADOR + "=?", argsel, null, null, null);
                try {
                    c.moveToFirst();
                    NombreBuscar.setText(c.getString(0));
                    PWBucar.setText(c.getString(1));
                    PWBucar.setVisibility(View.VISIBLE);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "No se ha encontrado", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.compartir:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=ssroman.mypasswords");
                startActivity(Intent.createChooser(intent, "Compartir con:"));
                break;
            case R.id.valorar:
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=ssroman.mypasswords")));
                } catch (android.content.ActivityNotFoundException anfe) {}
                break;
            case R.id.contacto:
                startActivity(new Intent(BuscarPW.this, EmailActivity.class));
                break;
            case R.id.masapps:
                startActivity(new Intent(BuscarPW.this, MasApps.class));
                break;
            case R.id.acerca:
                AlertDialog.Builder emergente = new AlertDialog.Builder(BuscarPW.this);
                View vista = getLayoutInflater().inflate(R.layout.activity_acerca_popup, null);
                emergente.setView(vista);
                AlertDialog dialogo = emergente.create();
                dialogo.show();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }


}
