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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Random;

public class GuardarPW extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar_pw);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button GuardarPW = (Button)findViewById(R.id.btnGuardarPW);
        Button GenerarPWAleatoria = (Button)findViewById(R.id.btnPWAleatoria);

        final AyudaBD ayudabd = new AyudaBD(getApplicationContext());

        GuardarPW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText Id = (EditText)findViewById(R.id.etId);
                TextView Password = (TextView) findViewById(R.id.etpassword);
                SQLiteDatabase db = ayudabd.getWritableDatabase();
                ContentValues valores = new ContentValues();

                String userName=Id.getText().toString();
                String password=Password.getText().toString();

                // check if any of the fields are vaccant
                if(userName.equals("")||password.equals("")) {
                    Toast.makeText(getApplicationContext(), "Rellene los campos solicitados", Toast.LENGTH_LONG).show();
                    return;

                }else {

                    valores.put(AyudaBD.DatosTabla.COLUMNA_IDENTIFICADOR, Id.getText().toString());
                    valores.put(AyudaBD.DatosTabla.COLUMNA_PASSWORD, Password.getText().toString());

                    Long IdGuardado = db.insert(AyudaBD.DatosTabla.NOMBRE_TABLA, AyudaBD.DatosTabla.COLUMNA_ID, valores);
                    Toast.makeText(getApplicationContext(), "Dato guardado: " + IdGuardado, Toast.LENGTH_LONG).show();
                }

            }
        });

        GenerarPWAleatoria.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                EditText num_carac = (EditText)findViewById(R.id.numCaracteres);
                TextView pw = (TextView)findViewById(R.id.etpassword);
                pw.setText("");
                int maximo = Integer.parseInt(num_carac.getText().toString());
                int i;
                char[] caracteres;
                char letra;
                String pwd;
                caracteres = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
                if (maximo>0) {
                    for (i = 0; i < maximo; i++) {
                        letra = caracteres[new Random().nextInt(62)];
                        pwd = new String(String.valueOf(letra));
                        pw.setText(pw.getText() + pwd);
                    }
                }else{
                    Toast.makeText(GuardarPW.this, "Asigna valor al número de caracteres", Toast.LENGTH_LONG).show();
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
                startActivity(new Intent(GuardarPW.this, EmailActivity.class));
                break;
            case R.id.masapps:
                startActivity(new Intent(GuardarPW.this, MasApps.class));
                break;
            case R.id.acerca:
                AlertDialog.Builder emergente = new AlertDialog.Builder(GuardarPW.this);
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
