package com.example.stockholder.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {
    private var _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }

    private var _text2 = MutableLiveData<String>().apply{
        value = "Thow thow ThowThow"
    }
    fun changeValue(s1:String){
        _text2.value = "가나다라마바사"
    }

    val text: LiveData<String> = _text
    val text2: LiveData<String> = _text2
}