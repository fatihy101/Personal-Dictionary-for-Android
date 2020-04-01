package com.fatihy.PersonalDictionary.ui.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.AbstractMap;

public class PageViewModel extends ViewModel {

    private MutableLiveData<String>  xInput = new MutableLiveData<>();

    public void setInput(String xInput)
    {
        this.xInput.setValue(xInput);
    }

    public LiveData<String> getInput()
    {
        return xInput;
    }


}