package fr.debrisgrollaud.gestionsdesstock.stockage;

import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Locale;

import fr.debrisgrollaud.gestionsdesstock.MainActivity;
import fr.debrisgrollaud.gestionsdesstock.R;

@RequiresApi(api = Build.VERSION_CODES.N)
public class AjoutStockage extends AppCompatActivity {

    private TextInputLayout nom;
    private TextInputLayout date;
    private EditText nom_text;
    private EditText date_text;

    final Calendar myCalendar = Calendar.getInstance();
    final DatePickerDialog.OnDateSetListener date_class = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_stockage);
        setTitle("Ajout d'un stockage");

        Button valider = findViewById(R.id.button_ajout_stockage);

        nom = findViewById(R.id.input_ajoutStockage_nom);
        date = findViewById(R.id.input_ajoutStockage_date);

        valider.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onValide();
            }
        });

        nom_text = nom.getEditText();
        date_text = date.getEditText();

        TextWatcher textWatcher = textWatcher();

        nom_text.addTextChangedListener(textWatcher);

        date_text.addTextChangedListener(textWatcher);
        date_text.setInputType(InputType.TYPE_NULL);
        date_text.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AjoutStockage.this, date_class, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void onValide() {

        boolean error = false;

        if (nom_text.length() == 0) {
            nom.setError("Merci de remplir le champ");
            error = true;
        }

        if (date_text.length() == 0) {
            date.setError("Merci de remplir le champ");
            error = true;
        }

        if (!error) {
            addStockage();
        }

    }

    private void addStockage() {

        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("nom", nom_text.getText().toString());
        hashMap.put("lieu", "1 chemin du bersoleau meursac 17120");
        hashMap.put("dateAjout", date_text.getText().toString());


        MainActivity.BDD.open();

        MainActivity.BDD.insert("stockage", hashMap);

        nom_text.setText("");
        date_text.setText("");

        Toast.makeText(MainActivity.Instance, R.string.app_tost_list_ajouter,
                Toast.LENGTH_LONG).show();
    }

    private TextWatcher textWatcher() {
        return new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (nom.getEditText() == null || date.getEditText() == null) {
                    return;
                }

                if (nom.getEditText().getText().hashCode() == s.hashCode() && count != 0) {
                    nom.setError(null);
                    return;
                }

                if (date.getEditText().getText().hashCode() == s.hashCode() && count != 0) {
                    date.setError(null);
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);

        date_text.setText(sdf.format(myCalendar.getTime()));
    }
}
