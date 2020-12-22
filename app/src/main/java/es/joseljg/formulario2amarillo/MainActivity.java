package es.joseljg.formulario2amarillo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner sp_combo1 = null;
    TextView txt_item_seleccionado = null;
    TextView txt_alerta1 = null;
    TextView txt_calendario = null;
    TextView txt_hora = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp_combo1 = (Spinner) findViewById(R.id.sp_combo1);
        txt_item_seleccionado = (TextView) findViewById(R.id.txt_item_seleccionado);
        //--------------------------------------------------
        sp_combo1.setOnItemSelectedListener(this);
        // cargar el combo con los datos
        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this, R.array.frutas, R.layout.casilladesplegable);
        adaptador.setDropDownViewResource(R.layout.casilladesplegable);
        sp_combo1.setAdapter(adaptador);
        //-------------------------------------------------
        txt_alerta1 = (TextView) findViewById(R.id.txt_alerta1);
        txt_calendario = (TextView) findViewById(R.id.txt_calendario);
        txt_hora = (TextView) findViewById(R.id.txt_hora);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       Log.i("errores", "ha entrado al itemSelected");
       String valorItem = String.valueOf(sp_combo1.getItemAtPosition(position));
       Toast.makeText( parent.getContext(),"has elegido " + valorItem, Toast.LENGTH_SHORT).show();
       txt_item_seleccionado.setText(valorItem);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void mostrarAlerta(View view) {
        AlertDialog.Builder alerta1 = new AlertDialog.Builder(this);
        alerta1.setTitle("INSERTAR FRUTA");
        alerta1.setMessage("Elige SI para insertar la fruta y NO para cancelar la fruta");
        alerta1.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            txt_alerta1.setText("INSERCION CORRECTA");
            }
        });
        alerta1.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                txt_alerta1.setText("SE CANCELÓ LA INSERCION");
            }
        });
        alerta1.show();
    }

    public void mostrarCalendario(View view) {
       DatePickerFragment calendario = new DatePickerFragment();
       calendario.show(getSupportFragmentManager(),"datePicker");
    }

    public void mostrarHora(View view) {
        TimePickerFragment seleccionHora = new TimePickerFragment();
        seleccionHora.show(getSupportFragmentManager(),"timePicker");
    }

    public void procesarCalendario(int year, int month, int dayOfMonth) {
        String textoyear = String.valueOf(year);
        String textomonth = String.valueOf(month);
        String textoday   = String.valueOf(dayOfMonth);
        txt_calendario.setText(textoday + "/" + textomonth + "/" + textoyear);
    }

    public void procesarHora(int hourOfDay, int minute) {
       String textohourOfDay = String.valueOf(hourOfDay);
       String textominute = String.valueOf(minute);
       txt_hora.setText(textohourOfDay + ":" + textominute);
    }
}