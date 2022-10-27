package com.example.didinoapp.viewmodel

import androidx.lifecycle.*
import com.example.didinoapp.data.model.MovieAndSeriesModel
import com.example.didinoapp.data.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieAndSeriesViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val sliderMutableLiveData = MutableLiveData<List<MovieAndSeriesModel>>()
    private val specialMovieMutableLiveData = MutableLiveData<List<MovieAndSeriesModel>>()
    private val newMovieMutableLiveData = MutableLiveData<List<MovieAndSeriesModel>>()
    private val freeMovieMutableLiveData = MutableLiveData<List<MovieAndSeriesModel>>()
    private val persianSeriesMutableLiveData = MutableLiveData<List<MovieAndSeriesModel>>()
    private val foreignSeriesMutableLiveData = MutableLiveData<List<MovieAndSeriesModel>>()
    private val actionMovieMutableLiveData = MutableLiveData<List<MovieAndSeriesModel>>()
    private val historicalMovieMutableLiveData = MutableLiveData<List<MovieAndSeriesModel>>()
    private val romanticMovieMutableLiveData = MutableLiveData<List<MovieAndSeriesModel>>()
    private val familyMovieMutableLiveData = MutableLiveData<List<MovieAndSeriesModel>>()
    private val documentaryMovieMutableLiveData = MutableLiveData<List<MovieAndSeriesModel>>()
    private val comedyMovieMutableLiveData = MutableLiveData<List<MovieAndSeriesModel>>()
    private val scienceMovieMutableLiveData = MutableLiveData<List<MovieAndSeriesModel>>()
    private val scaryMovieMutableLiveData = MutableLiveData<List<MovieAndSeriesModel>>()
    private val animationMovieMutableLiveData = MutableLiveData<List<MovieAndSeriesModel>>()

    private val allSeriesMutableLiveData = MutableLiveData<List<MovieAndSeriesModel>>()
    private val allMovieAndSeriesMutableLiveData = MutableLiveData<List<MovieAndSeriesModel>>()
    private val allMoviePersianMutableLiveData = MutableLiveData<List<MovieAndSeriesModel>>()

    val allSeriesLiveData: LiveData<List<MovieAndSeriesModel>> get() = allSeriesMutableLiveData
    val allMovieAndSeriesLiveData: LiveData<List<MovieAndSeriesModel>> get() = allMovieAndSeriesMutableLiveData
    val allMoviePersianLiveData: LiveData<List<MovieAndSeriesModel>> get() = allMoviePersianMutableLiveData

    val sliderLiveData: LiveData<List<MovieAndSeriesModel>> get() = sliderMutableLiveData
    val specialMovieLiveData: LiveData<List<MovieAndSeriesModel>> get() = specialMovieMutableLiveData
    val newMovieLiveData: LiveData<List<MovieAndSeriesModel>> get() = newMovieMutableLiveData
    val freeMovieLiveData: LiveData<List<MovieAndSeriesModel>> get() = freeMovieMutableLiveData
    val persianMovieLiveData: LiveData<List<MovieAndSeriesModel>> get() = persianSeriesMutableLiveData
    val foreignMovieLiveData: LiveData<List<MovieAndSeriesModel>> get() = foreignSeriesMutableLiveData
    val actionMovieLiveData: LiveData<List<MovieAndSeriesModel>> get() = actionMovieMutableLiveData
    val historicalMovieLiveData: LiveData<List<MovieAndSeriesModel>> get() = historicalMovieMutableLiveData
    val romanticMovieLiveData: LiveData<List<MovieAndSeriesModel>> get() = romanticMovieMutableLiveData
    val familyMovieLiveData: LiveData<List<MovieAndSeriesModel>> get() = familyMovieMutableLiveData
    val documentaryMovieLiveData: LiveData<List<MovieAndSeriesModel>> get() = documentaryMovieMutableLiveData
    val comedyMovieLiveData: LiveData<List<MovieAndSeriesModel>> get() = comedyMovieMutableLiveData
    val scienceMovieLiveData: LiveData<List<MovieAndSeriesModel>> get() = scienceMovieMutableLiveData
    val scaryMovieLiveData: LiveData<List<MovieAndSeriesModel>> get() = scaryMovieMutableLiveData
    val animationMovieLiveData: LiveData<List<MovieAndSeriesModel>> get() = animationMovieMutableLiveData

    fun getAllMovieAndSeries(){
        viewModelScope.launch(Dispatchers.IO) {
            allMovieAndSeriesMutableLiveData.postValue(mainRepository.getAllMovieAndSeries())
        }
    }

    fun getAllSeriesViewModel() {
        viewModelScope.launch(Dispatchers.IO) {
            allSeriesMutableLiveData.postValue(mainRepository.getRepositoryAllSeries())
        }
    }

    fun getAllMoviesPersianViewModel() {
        viewModelScope.launch (Dispatchers.IO){
            allMoviePersianMutableLiveData.postValue(mainRepository.getRepositoryAllMoviesPersian())
        }
    }

    fun getGalleryViewModel() {
        viewModelScope.launch(Dispatchers.IO) {
            sliderMutableLiveData.postValue(mainRepository.getRepositoryGallery())
        }
    }

    fun getSpecialViewModel() {
        viewModelScope.launch(Dispatchers.IO) {
            specialMovieMutableLiveData.postValue(mainRepository.getRepositorySpecial())
        }
    }

    fun getNewViewModel() {
        viewModelScope.launch(Dispatchers.IO) {
            newMovieMutableLiveData.postValue(mainRepository.getRepositoryNew())
        }
    }

    fun getFreeViewModel() {
        viewModelScope.launch(Dispatchers.IO) {
            freeMovieMutableLiveData.postValue(mainRepository.getRepositoryFree())
        }
    }

    fun getPersianSeriesViewModel() {
        viewModelScope.launch(Dispatchers.IO) {
            persianSeriesMutableLiveData.postValue(mainRepository.getRepositoryPersianSeries())
        }
    }

    fun getForeignSeriesViewModel() {
        viewModelScope.launch(Dispatchers.IO) {
            foreignSeriesMutableLiveData.postValue(mainRepository.getRepositoryForeignSeries())
        }
    }

    fun getActionViewModel() {
        viewModelScope.launch(Dispatchers.IO) {
            actionMovieMutableLiveData.postValue(mainRepository.getRepositoryAction())
        }
    }

    fun getHistoricalViewModel() {
        viewModelScope.launch(Dispatchers.IO) {
            historicalMovieMutableLiveData.postValue(mainRepository.getRepositoryHistorical())
        }
    }

    fun getRomanticViewModel() {
        viewModelScope.launch(Dispatchers.IO) {
            romanticMovieMutableLiveData.postValue(mainRepository.getRepositoryRomantic())
        }
    }

    fun getFamilyViewModel() {
        viewModelScope.launch(Dispatchers.IO) {
            familyMovieMutableLiveData.postValue(mainRepository.getRepositoryFamily())
        }
    }

    fun getDocumentaryViewModel() {
        viewModelScope.launch(Dispatchers.IO) {
            documentaryMovieMutableLiveData.postValue(mainRepository.getRepositoryDocumentary())
        }
    }

    fun getComedyViewModel() {
        viewModelScope.launch(Dispatchers.IO) {
            comedyMovieMutableLiveData.postValue(mainRepository.getRepositoryComedy())
        }
    }

    fun getScienceViewModel() {
        viewModelScope.launch(Dispatchers.IO) {
            scienceMovieMutableLiveData.postValue(mainRepository.getRepositoryScience())
        }
    }

    fun getScaryViewModel() {
        viewModelScope.launch(Dispatchers.IO) {
            scaryMovieMutableLiveData.postValue(mainRepository.getRepositoryScary())
        }
    }

    fun getAnimationViewModel() {
        viewModelScope.launch(Dispatchers.IO) {
            animationMovieMutableLiveData.postValue(mainRepository.getRepositoryAnimation())
        }
    }
}