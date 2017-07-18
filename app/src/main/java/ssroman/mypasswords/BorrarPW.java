package ssroman.mypasswords;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BorrarPW extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_pw);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText borrarPW = (EditText)findViewById(R.id.etIDBorrar);
        Button btnborrarPW = (Button)findViewById(R.id.btnBorrarBD);

        final AyudaBD ayudabd = new AyudaBD(getApplicationContext());

        btnborrarPW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = ayudabd.getWritableDatabase();
                String Selection = AyudaBD.DatosTabla.COLUMNA_IDENTIFICADOR + "=?";
                String[] argsel = {borrarPW.getText().toString()};

                db.delete(AyudaBD.DatosTabla.NOMBRE_TABLA, Selection, argsel);

                Toast.makeText(getApplicationContext(), "Dato borrado", Toast.LENGTH_LONG).show();
            }
        });

    }

}
