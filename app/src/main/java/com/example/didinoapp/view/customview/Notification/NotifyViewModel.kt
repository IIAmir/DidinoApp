package com.example.didinoapp.view.customview.Notification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.didinoapp.data.model.MovieAndSeriesModel
import com.example.didinoapp.data.repository.NotifyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotifyViewModel(private val notifyRepository: NotifyRepository): ViewModel() {

    private val notifyMutableLiveData = MutableLiveData<MutableList<MovieAndSeriesModel>>()
    val notifyLiveData:LiveData<MutableList<MovieAndSeriesModel>> get() = notifyMutableLiveData

    fun getNotify(){
        viewModelScope.launch(Dispatchers.IO) {
            notifyRepository.getNotifyData().collect {
                notifyMutableLiveData.postValue(it)
            }
        }
    }
}