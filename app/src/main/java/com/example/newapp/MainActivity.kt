package com.example.newapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var nombreEditText: EditText
    private lateinit var edadEditText: EditText
    private lateinit var registrarButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtiene las referencias de los campos de texto y el botón
        nombreEditText = findViewById(R.id.nombre_edit_text)
        edadEditText = findViewById(R.id.edad_edit_text)
        registrarButton = findViewById(R.id.registrar_button)

        // Establece el listener de clic del botón
        registrarButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v == registrarButton) {
            // Obtiene los valores de los campos de texto
            val nombre = nombreEditText.text.toString()
            val edad = edadEditText.text.toString()

            val queue = Volley.newRequestQueue(this)

            val jsonBody = JSONObject()
            jsonBody.put("name","Lind")
            jsonBody.put("age", 26)

            val url = "http://192.168.101.15:8000/api/insert-customer"

            val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, jsonBody,
                Response.Listener { response ->
                    // Manejar la respuesta de la API
                    println(response)
                }, Response.ErrorListener { error ->
                    // Manejar el error de la petición
                    //Toast.makeText(this, error, Toast.LENGTH_LONG).show()
                    println(error)
                })

            queue.add(jsonObjectRequest)

            // Muestra un Toast con los valores obtenidos
            val mensaje = "El nombre es: $nombre\nLa edad es: $edad"
            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
        }
    }
}