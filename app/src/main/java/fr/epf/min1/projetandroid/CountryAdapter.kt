package fr.epf.min1.projetandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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
            Log.d("CountryAdapter", "Loading flag URL: ${country.flag}")
            Glide.with(itemView.context)
                .load(country.flag)
                .error(R.drawable.flag_placeholder)
                .into(flagImageView)
            itemView.setOnClickListener {
                onItemClick(country)
            }
            // Set the initial state of the favorite button
            updateFavoriteIcon()

            favoriteButton.setOnClickListener {
                GlobalScope.launch {
                    val db = CountryDatabase.getDatabase(itemView.context)
                    if (isFavorite) {
                        db.countryDao().delete(country.name)
                    } else {
                        db.countryDao().insert(country)
                    }
                    isFavorite = !isFavorite
                    updateFavoriteIcon()
                }
            }
        }

        private fun updateFavoriteIcon() {
            if (isFavorite) {
                favoriteButton.setImageResource(R.drawable.ic_favorite) // Favorite icon
            } else {
                favoriteButton.setImageResource(R.drawable.ic_favorite_border) // Not favorite icon
            }
        }
    }
    fun updateData(newCountries: List<Country>) {
        countries = newCountries
        notifyDataSetChanged()
    }
}



