package ssroman.mypasswords;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SalvarPW extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salvar_pw);
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

}
