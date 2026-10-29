package com.marcosatan.tarea1progrmacions3; // Define el paquete único de la aplicación

import android.os.Bundle; // Importa Bundle para manejar estados de la actividad
import android.util.Log; // Importa Log para registrar mensajes en la consola Logcat
import android.widget.Button; // Importa el componente Button para acciones
import android.widget.EditText; // Importa EditText para entrada de datos numéricos
import android.widget.TextView; // Importa TextView para mostrar resultados en pantalla
import androidx.appcompat.app.AppCompatActivity; // Importa AppCompatActivity como base de la actividad
import android.widget.Toast; // Importa Toast para mostrar mensajes emergentes temporales

public class MainActivity extends AppCompatActivity { // Declara la clase principal heredando de AppCompatActivity

    private EditText edtNum1; // Declara variable privada para el primer campo numérico
    private EditText edtNum2; // Declara variable privada para el segundo campo numérico
    private Button btnSumar; // Declara variable privada para el botón de suma
    private Button btnRestar; // Declara variable privada para el botón de resta (Reto 6)
    private TextView txtResultado; // Declara variable privada para mostrar el resultado

    @Override
    protected void onCreate(Bundle savedInstanceState) { // Método llamado automáticamente al crear la actividad
        super.onCreate(savedInstanceState); // Llama al constructor de la clase padre AppCompatActivity
        setContentView(R.layout.activity_main); // Vincula el diseño XML con esta actividad Java

        edtNum1 = findViewById(R.id.edtNum1); // Localiza el EditText del primer número por su ID
        edtNum2 = findViewById(R.id.edtNum2); // Localiza el EditText del segundo número por su ID
        btnSumar = findViewById(R.id.btnSumar); // Localiza el botón Sumar por su ID en el XML
        btnRestar = findViewById(R.id.btnRestar); // Localiza el botón Restar por su ID (Reto 6)
        txtResultado = findViewById(R.id.txtResultado); // Localiza el TextView de resultado por su ID

        btnSumar.setOnClickListener(v -> realizarOperacion("suma")); // Registra listener para sumar (Reto 6 optimizado)
        btnRestar.setOnClickListener(v -> realizarOperacion("resta")); // Registra listener para restar (Reto 6)
    }

    private void realizarOperacion(String tipo) { // Método auxiliar para evitar duplicar lógica (Reto 6)
        Log.d("APP", tipo + "() inicio"); // Registra en Logcat el inicio de la operación seleccionada

        String s1 = edtNum1.getText().toString().trim(); // Obtiene texto del primer campo eliminando espacios
        String s2 = edtNum2.getText().toString().trim(); // Obtiene texto del segundo campo eliminando espacios

        if (s1.isEmpty()) { // Valida si el primer campo está vacío
            edtNum1.setError(getString(R.string.msg_error_vacio)); // Muestra mensaje de error visual en el campo
            return; // Detiene la ejecución para evitar errores de conversión
        }

        if (s2.isEmpty()) { // Valida si el segundo campo está vacío
            edtNum2.setError(getString(R.string.msg_error_vacio)); // Muestra mensaje de error visual en el campo
            return; // Detiene la ejecución para evitar errores de conversión
        }

        try { // Inicia bloque seguro para capturar errores de formato numérico
            int n1 = Integer.parseInt(s1); // Convierte cadena del primer campo a entero
            int n2 = Integer.parseInt(s2); // Convierte cadena del segundo campo a entero
            int resultado = 0; // Variable para almacenar el resultado de la operación

            if (tipo.equals("suma")) { // Verifica si la operación solicitada es suma
                resultado = n1 + n2; // Realiza la suma aritmética de los dos números
                txtResultado.setText(String.format(getString(R.string.msg_resultado_suma), resultado)); // Muestra mensaje de suma (Reto 5)
            } else { // Si no es suma, asume que es resta
                resultado = n1 - n2; // Realiza la resta aritmética de los dos números
                txtResultado.setText(String.format(getString(R.string.msg_resultado_resta), resultado)); // Muestra mensaje de resta (Reto 5 corregido)
            }

            Log.d("APP", "n1= " + n1); // Registra valor del primer número en Logcat
            Log.d("APP", "n2= " + n2); // Registra valor del segundo número en Logcat
            Log.d("APP", "resultado= " + resultado); // Registra resultado final en Logcat para evidencia

            Toast.makeText(this, "Operación exitosa", Toast.LENGTH_SHORT).show(); // Muestra confirmación temporal al usuario

        } catch (NumberFormatException e) { // Captura excepción si el usuario ingresa letras o caracteres inválidos
            Toast.makeText(this, getString(R.string.msg_error_numero), Toast.LENGTH_LONG).show(); // Informa al usuario sobre el error de formato
        }
    }
}