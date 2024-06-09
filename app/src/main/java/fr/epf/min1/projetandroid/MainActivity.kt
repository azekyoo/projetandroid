package fr.epf.min1.projetandroid

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var searchEditText: EditText
    private lateinit var searchButton: Button
    private lateinit var countryViewModel: CountryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchEditText = findViewById(R.id.searchEditText)
        searchButton = findViewById(R.id.searchButton)
        countryViewModel = ViewModelProvider(this).get(CountryViewModel::class.java)

        searchButton.setOnClickListener {
            val searchTerm = searchEditText.text.toString()
            if (searchTerm.isNotEmpty()) {
                val intent = Intent(this, ListPaysActivity::class.java)
                intent.putExtra("search_term", searchTerm)
                startActivity(intent)
            }
        }

        setupAutoSuggestions()
    }

    private fun setupAutoSuggestions() {
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchTerm = s.toString()
                if (searchTerm.isNotEmpty()) {
                    countryViewModel.searchCountry(searchTerm)
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        countryViewModel.countries.observe(this, Observer { countries ->
            // Use the list of countries for auto-suggestions
            // You can use a library like MaterialAutoCompleteTextView or custom implementation
        })
    }
}
