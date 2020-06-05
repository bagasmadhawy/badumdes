package com.example.badumdes.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.badumdes.R;
import com.example.badumdes.database.AppDatabase;
import com.example.badumdes.database.TestimoniModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class TestimoniFragment extends Fragment {

    private AppDatabase appDatabase;
    private Button btnKirim;
    private EditText etPesan, etNama;

    public TestimoniFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_testimoni, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnKirim = view.findViewById(R.id.btn_kirim);
        etPesan = view.findViewById(R.id.et_pesan);
        etNama = view.findViewById(R.id.et_namates);
        appDatabase = AppDatabase.initDatabase(getContext());

        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    TestimoniModel testimoniModel = new TestimoniModel();
                    testimoniModel.setPesan(etPesan.getText().toString());
                    testimoniModel.setNama(etNama.getText().toString());

                    appDatabase.testimoniDAO().insertTestimoni(testimoniModel);

                    Log.e("Testimoni","sukse");
                    Toast.makeText(getContext(), "Sukses menyimpan", Toast.LENGTH_SHORT).show();
                }catch (Exception ex){
                    Log.e("Testimoni","gagal menyimpan, msg :"+ex.getMessage());
                    Toast.makeText(getContext(), "Gagal menyimpan", Toast.LENGTH_SHORT).show();
                }

                etPesan.setText("");
                etNama.setText("");
            }
        });
    }

}
