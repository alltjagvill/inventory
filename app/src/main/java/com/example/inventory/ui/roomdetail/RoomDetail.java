package com.example.inventory.ui.roomdetail;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inventory.R;

public class RoomDetail extends Fragment {

    private RoomDetailViewModel mViewModel;

    public static RoomDetail newInstance() {
        return new RoomDetail();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.room_detail_fragment, container, false);
        return view;
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
////        mViewModel = ViewModelProviders.of(this).get(RoomDetailViewModel.class);
////        // TODO: Use the ViewModel
//    }

}
