package fr.epf.min1.projetandroid

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteCountriesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CountryAdapter
    private lateinit var db: CountryDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_countries)

        db = CountryDatabase.getDatabase(this)

        recyclerView = findViewById(R.id.favoritesRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = CountryAdapter(emptyList(), onItemClick = { country ->
            val intent = Intent(this, CountryDetailActivity::class.java).apply {
                putExtra("COUNTRY_NAME", country.name)
                putExtra("COUNTRY_CAPITAL", country.capital)
                putExtra("COUNTRY_FLAG", country.flag)
                // Add any other country details you need to pass
            }
            startActivity(intent)
        })
        recyclerView.adapter = adapter

        loadFavorites()
    }

    private fun loadFavorites() {
        lifecycleScope.launch {
            val favorites = db.countryDao().getAllFavorites()
            withContext(Dispatchers.Main) {
                adapter.updateData(favorites)
            }
        }
    }
}
