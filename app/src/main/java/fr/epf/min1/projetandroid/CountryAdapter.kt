package fr.epf.min1.projetandroid

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CountryAdapter(private var countries: List<Country>, private val onItemClick: (Country) -> Unit) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countries[position]
        holder.bind(country)
    }

    override fun getItemCount(): Int = countries.size

    fun updateCountries(newCountries: List<Country>) {
        countries = newCountries
        notifyDataSetChanged()
    }

    inner class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val flagImageView: ImageView = itemView.findViewById(R.id.flagImageView)
        private val countryNameTextView: TextView = itemView.findViewById(R.id.countryNameTextView)
        private val capitalNameTextView: TextView = itemView.findViewById(R.id.capitalNameTextView)
        private val favoriteButton: ImageView = itemView.findViewById(R.id.favoriteButton)
        private var isFavorite: Boolean = false

        fun bind(country: Country) {
            countryNameTextView.text = country.name
            capitalNameTextView.text = country.capital
            Log.d("CountryAdapter", "Loading flag URL: ${country.flags.png}")
            Glide.with(itemView.context)
                .load(country.flags.png)
                .error(R.drawable.flag_placeholder)
                .into(flagImageView)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, CountryDetailActivity::class.java).apply {
                    putExtra("country_name", country.name)
                    putExtra("country_capital", country.capital)
                    putExtra("country_flag", country.flags.png)
                    putExtra("country_population", country.population)
                    putExtra("country_area", country.area)
                    putExtra("country_region", country.region)
                    putExtra("country_subregion", country.subregion)
                    putExtra("country_currency", country.currencies.joinToString { "${it.name} (${it.symbol})" })
                    putStringArrayListExtra("country_languages", ArrayList(country.languages.map { it.name }))
                }
                itemView.context.startActivity(intent)
            }

            // Check if the country is already a favorite
            CoroutineScope(Dispatchers.Main).launch {
                val db = CountryDatabase.getDatabase(itemView.context)
                isFavorite = db.countryDao().isFavorite(country.name)
                updateFavoriteIcon()
            }

            favoriteButton.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    val db = CountryDatabase.getDatabase(itemView.context)
                    if (isFavorite) {
                        db.countryDao().delete(country.name)
                    } else {
                        db.countryDao().insert(country)
                    }
                    isFavorite = !isFavorite
                    withContext(Dispatchers.Main) {
                        updateFavoriteIcon()
                    }
                }
            }
        }

        private fun updateFavoriteIcon() {
            if (isFavorite) {
                favoriteButton.setImageResource(R.drawable.button_favorite_full) // Favorite icon
            } else {
                favoriteButton.setImageResource(R.drawable.button_favorite_border) // Not favorite icon
            }
        }
    }
    fun updateData(newCountries: List<Country>) {
        countries = newCountries
        notifyDataSetChanged()
    }
}



