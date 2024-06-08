package fr.epf.min1.projetandroid

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class CountryDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)

        val countryName = intent.getStringExtra("country_name") ?: return
        val countryCapital = intent.getStringExtra("country_capital") ?: ""
        val countryFlag = intent.getStringExtra("country_flag") ?: ""

        val nameTextView: TextView = findViewById(R.id.countryNameTextView)
        val capitalTextView: TextView = findViewById(R.id.countryCapitalTextView)
        val flagImageView: ImageView = findViewById(R.id.countryFlagImageView)

        nameTextView.text = countryName
        capitalTextView.text = countryCapital
        Glide.with(this).load(countryFlag).into(flagImageView)
    }
}
