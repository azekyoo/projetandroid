package fr.epf.min1.projetandroid

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CountryViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CountryRepository(viewModelScope)

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> get() = _countries

    private val _searchResults = MutableLiveData<List<Country>>()
    val searchResults: LiveData<List<Country>> get() = _searchResults

    fun fetchAllCountries() {
        viewModelScope.launch {
            try {
                val countryList = repository.getAllCountries()
                _countries.postValue(countryList)
                Log.d("CountryViewModel", "Countries fetched: $countryList")
            } catch (e: Exception) {
                Log.e("CountryViewModel", "Failed to fetch countries", e)
            }
        }
    }

    fun searchCountry(name: String) {
        viewModelScope.launch {
            try {
                Log.d("CountryViewModel", "Searching for country with name: $name")
                val countryList = repository.searchCountryByName(name)
                Log.d("CountryViewModel", "Countries fetched: $countryList")
                _searchResults.postValue(countryList)
            } catch (e: Exception) {
                Log.e("CountryViewModel", "Failed to fetch country", e)
            }
        }
    }
}
