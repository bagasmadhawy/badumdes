package com.example.badumdes.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.badumdes.R;
import com.example.badumdes.adapter.TestimoniAdapter;
import com.example.badumdes.database.AppDatabase;
import com.example.badumdes.database.TestimoniModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private TestimoniAdapter testimoniAdapter;
    private RecyclerView rvTestimoni;
    private AppDatabase appDatabase;
    private ArrayList<TestimoniModel> listTestimoni = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvTestimoni = view.findViewById(R.id.rv_testimoni);
        testimoniAdapter = new TestimoniAdapter(getContext());
        testimoniAdapter.notifyDataSetChanged();

        if (appDatabase==null){
            appDatabase = AppDatabase.initDatabase(getContext());
        }

        listTestimoni.addAll(appDatabase.testimoniDAO().getTestimoni());
        testimoniAdapter.setData(listTestimoni);

        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvTestimoni.setLayoutManager(linearLayoutManager);
        rvTestimoni.setAdapter(testimoniAdapter);

    }
}
