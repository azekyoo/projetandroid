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
        val countryPopulation = intent.getLongExtra("country_population", 0L)
        val countryArea = intent.getDoubleExtra("country_area", 0.0)
        val countryRegion = intent.getStringExtra("country_region") ?: ""
        val countrySubregion = intent.getStringExtra("country_subregion") ?: ""
        val countryCurrency = intent.getStringExtra("country_currency") ?: ""
        val countryLanguages = intent.getStringArrayListExtra("country_languages") ?: arrayListOf<String>()

        val flagImageView: ImageView = findViewById(R.id.countryFlagImageView)
        val nameTextView: TextView = findViewById(R.id.countryNameTextView)
        val capitalTextView: TextView = findViewById(R.id.countryCapitalTextView)
        val populationTextView: TextView = findViewById(R.id.populationTextView)
        val areaTextView: TextView = findViewById(R.id.areaTextView)
        val regionTextView: TextView = findViewById(R.id.regionTextView)
        val subregionTextView: TextView = findViewById(R.id.subregionTextView)
        val currencyTextView: TextView = findViewById(R.id.currencyTextView)
        val languagesTextView: TextView = findViewById(R.id.languagesTextView)

        nameTextView.text = countryName
        capitalTextView.text = "Capital: $countryCapital"
        populationTextView.text = "Population: $countryPopulation"
        areaTextView.text = "Area: $countryArea km²"
        regionTextView.text = "Region: $countryRegion"
        subregionTextView.text = "Subregion: $countrySubregion"
        currencyTextView.text = "Currency: $countryCurrency"
        languagesTextView.text = "Languages: ${countryLanguages.joinToString(", ")}"

        Glide.with(this).load(countryFlag).into(flagImageView)
    }
}


