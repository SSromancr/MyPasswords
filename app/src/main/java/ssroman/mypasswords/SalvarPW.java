package ssroman.mypasswords;

import android.content.ContentValues;
import android.content.Intent;
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

public class SalvarPW extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salvar_pw);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button GuardarSPW = (Button)findViewById(R.id.btnGuardarSPW);

        final AyudaBD ayudabd = new AyudaBD(getApplicationContext());

        GuardarSPW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText Nombre = (EditText)findViewById(R.id.etIDSW);
                EditText SalvarPW = (EditText)findViewById(R.id.etpasswordSPW);
                SQLiteDatabase db = ayudabd.getWritableDatabase();
                ContentValues valores = new ContentValues();

                String userName=Nombre.getText().toString();
                String password=SalvarPW.getText().toString();

                // check if any of the fields are vaccant
                if(userName.equals("")||password.equals("")) {
                    Toast.makeText(getApplicationContext(), "Rellene los campos solicitados", Toast.LENGTH_LONG).show();
                    return;

                }else {

                    valores.put(AyudaBD.DatosTabla.COLUMNA_IDENTIFICADOR, Nombre.getText().toString());
                    valores.put(AyudaBD.DatosTabla.COLUMNA_PASSWORD, SalvarPW.getText().toString());

                    Long IdGuardado = db.insert(AyudaBD.DatosTabla.NOMBRE_TABLA, AyudaBD.DatosTabla.COLUMNA_ID, valores);
                    Toast.makeText(getApplicationContext(), "Dato guardado: " + IdGuardado, Toast.LENGTH_LONG).show();
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
                startActivity(new Intent(SalvarPW.this, EmailActivity.class));
                break;
            case R.id.masapps:
                startActivity(new Intent(SalvarPW.this, MasApps.class));
                break;
            case R.id.acerca:
                AlertDialog.Builder emergente = new AlertDialog.Builder(SalvarPW.this);
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
