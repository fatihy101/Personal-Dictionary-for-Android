package com.fatihy.PersonalDictionary;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fatihy.PersonalDictionary.ui.main.PageViewModel;


public class libraryFragment extends Fragment {

    PageViewModel pageViewModel;
    public libraryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*TODO: You have to initialize a ViewModelProvider. Problem is you don't know how to do that. Because in older version there was another
        *  method for that. It was ViewModelProviders but now it's deprecated. Find another way to do. */
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_library,container,false);
    }
}
