package fr.epf.min1.projetandroid

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.epf.min1.projetandroid.databinding.ActivityListPaysBinding

class ListPaysActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListPaysBinding
    private val countryViewModel: CountryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListPaysBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        countryViewModel.countries.observe(this) { countries ->
            binding.countriesRecyclerView.adapter = CountryAdapter(countries) { country ->
                val intent = Intent(this, CountryDetailActivity::class.java)
                intent.putExtra("country_name", country.name)
                intent.putExtra("country_capital", country.capital)
                intent.putExtra("country_flag", country.flag)
                startActivity(intent)
            }
        }

        val searchTerm = intent.getStringExtra("search_term")
        if (!searchTerm.isNullOrEmpty()) {
            countryViewModel.searchCountry(searchTerm)
        }
    }

    private fun setupRecyclerView() {
        binding.countriesRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}
