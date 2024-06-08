import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.epf.min1.projetandroid.Country
import fr.epf.min1.projetandroid.R
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var countryAdapter: CountryAdapter
    private var allCountries: List<Country> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countryRecyclerView.layoutManager = LinearLayoutManager(this)
        countryAdapter = CountryAdapter(listOf()) { country ->
            // Handle country item click (e.g., open a new activity)
        }
        countryRecyclerView.adapter = countryAdapter

        RetrofitInstance.api.getAllCountries().enqueue(object : Callback<List<Country>> {
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                if (response.isSuccessful) {
                    allCountries = response.body() ?: listOf()
                    countryAdapter = CountryAdapter(allCountries) { country ->
                        // Handle country item click
                    }
                    countryRecyclerView.adapter = countryAdapter
                }
            }

            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                // Handle failure
            }
        })

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterCountries(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filterCountries(query: String) {
        val filteredList = allCountries.filter {
            it.name.contains(query, true) || it.capital.contains(query, true)
        }
       
