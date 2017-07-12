package ssroman.mypasswords;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button guardar = (Button)findViewById(R.id.btnGuardar);
        Button modificar = (Button)findViewById(R.id.btnModificar);
        Button buscar = (Button)findViewById(R.id.btnBuscar);
        Button borrar = (Button)findViewById(R.id.btnBorrar);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuPrincipal.this, GuardarPW.class));
            }
        });

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuPrincipal.this, ModificarPW.class));
            }
        });

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuPrincipal.this, BuscarPW.class));
            }
        });

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuPrincipal.this, BorrarPW.class));
            }
        });


    }

}
