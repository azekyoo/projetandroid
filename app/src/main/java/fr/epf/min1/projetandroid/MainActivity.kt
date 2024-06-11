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
import fr.epf.min1.projetandroid.QuizActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var searchEditText: EditText
    private lateinit var searchButton: Button
    private lateinit var countryViewModel: CountryViewModel
    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchEditText = findViewById(R.id.searchEditText)
        searchButton = findViewById(R.id.searchButton)
        countryViewModel = ViewModelProvider(this).get(CountryViewModel::class.java)
        //countryViewModel.resetDatabase()

        searchButton.setOnClickListener {
            val searchTerm = searchEditText.text.toString()
            Log.d("MainActivity", "Search term entered: $searchTerm")
            if (searchTerm.isNotEmpty()) {
                val intent = Intent(this, ListPaysActivity::class.java)
                intent.putExtra("search_term", searchTerm)
                startActivity(intent)
            }
        }

        val favoritesButton = findViewById<Button>(R.id.favoritesButton)
        favoritesButton.setOnClickListener {
            val intent = Intent(this, FavoriteCountriesActivity::class.java)
            startActivity(intent)
        }

        val startQuizButton: Button = findViewById(R.id.startQuizButton)
        startQuizButton.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }

        setupAutoSuggestions()
    }

    private fun setupAutoSuggestions() {
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchTerm = s.toString()
                Log.d("MainActivity", "Auto-suggest search term: $searchTerm")

                searchJob?.cancel()
                searchJob = CoroutineScope(Dispatchers.Main).launch {
                    delay(300)
                    if (searchTerm.isNotEmpty()) {
                        countryViewModel.searchCountry(searchTerm)
                    }
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        countryViewModel.searchResults.observe(this, Observer { countries ->
            Log.d("MainActivity", "Auto-suggestions received: ${countries.size}")

        })
    }

}
