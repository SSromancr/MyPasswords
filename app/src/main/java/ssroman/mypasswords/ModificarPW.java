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

public class ModificarPW extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_pw);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button actualizar = (Button)findViewById(R.id.btnActualizar);
        final AyudaBD ayudabd = new AyudaBD(getApplicationContext());

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText Nombre = (EditText)findViewById(R.id.etIDModificar);
                EditText ModificarPW = (EditText)findViewById(R.id.etpasswordModificar);
                SQLiteDatabase db = ayudabd.getWritableDatabase();
                ContentValues valores = new ContentValues();
                valores.put(AyudaBD.DatosTabla.COLUMNA_IDENTIFICADOR, Nombre.getText().toString());
                valores.put(AyudaBD.DatosTabla.COLUMNA_PASSWORD, ModificarPW.getText().toString());
                String Selection = AyudaBD.DatosTabla.COLUMNA_IDENTIFICADOR+"=?";
                String[] argsel = {Nombre.getText().toString()};

                int count = db.update(AyudaBD.DatosTabla.NOMBRE_TABLA, valores, Selection, argsel);
                Toast.makeText(getApplicationContext(), "Dato modificado: " + count, Toast.LENGTH_LONG).show();
            }
        });

    }

}
