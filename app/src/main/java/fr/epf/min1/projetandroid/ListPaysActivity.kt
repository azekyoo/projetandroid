package fr.epf.min1.projetandroid

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.epf.min1.projetandroid.databinding.ActivityListPaysBinding
import android.util.Log

class ListPaysActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListPaysBinding
    private val countryViewModel: CountryViewModel by viewModels()
    private lateinit var countryAdapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListPaysBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        countryViewModel.searchResults.observe(this) { countries ->
            Log.d("ListPaysActivity", "Countries received: ${countries.size}")
            countryAdapter.updateCountries(countries)
        }

        val searchTerm = intent.getStringExtra("search_term")
        Log.d("ListPaysActivity", "Search term: $searchTerm")
        if (!searchTerm.isNullOrEmpty()) {
            countryViewModel.searchCountry(searchTerm)
        }
    }

    private fun setupRecyclerView() {
        countryAdapter = CountryAdapter(emptyList()) { country ->
            val intent = Intent(this, CountryDetailActivity::class.java).apply {
                putExtra("country_name", country.name)
                putExtra("country_capital", country.capital)
                putExtra("country_flag", country.flags.png)
            }
            startActivity(intent)
        }
        binding.countriesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ListPaysActivity)
            adapter = countryAdapter
        }
    }
}