package fr.epf.min1.projetandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val detailsButton = findViewById<Button>(R.id.home_detailspays_button)

        detailsButton.setOnClickListener {
            val intent = Intent(this, ListPaysActivity::class.java)
            Log.d("TEST", "Click on Details Button")
            startActivity(intent)
        }

    }
}
