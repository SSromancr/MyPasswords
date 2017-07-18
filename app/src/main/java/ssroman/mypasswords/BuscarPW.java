package ssroman.mypasswords;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuscarPW extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_pw);
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

}
