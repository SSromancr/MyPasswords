package ssroman.mypasswords;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GuardarPW extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar_pw);
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

}
