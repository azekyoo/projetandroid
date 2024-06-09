package fr.epf.min1.projetandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import android.util.Log

class CountryAdapter(private var countries: List<Country>, private val onItemClick: (Country) -> Unit) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countries[position]
        holder.bind(country, onItemClick)
    }

    override fun getItemCount() = countries.size

    fun updateCountries(newCountries: List<Country>) {
        countries = newCountries
        notifyDataSetChanged()
    }

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val countryFlagImageView: ImageView = itemView.findViewById(R.id.flagImageView)
        private val countryNameTextView: TextView = itemView.findViewById(R.id.countryNameTextView)
        private val capitalNameTextView: TextView = itemView.findViewById(R.id.capitalNameTextView)

        fun bind(country: Country, onItemClick: (Country) -> Unit) {
            countryNameTextView.text = country.name
            capitalNameTextView.text = country.capital
            Log.d("CountryAdapter", "Loading flag URL: ${country.flag}")
            Glide.with(itemView.context)
                .load(country.flag)
                .error(R.drawable.flag_placeholder)
                .into(countryFlagImageView)
            itemView.setOnClickListener {
                onItemClick(country)
            }
        }
    }
}



