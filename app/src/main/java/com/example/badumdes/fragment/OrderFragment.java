package com.example.badumdes.fragment;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.badumdes.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment {

    Button btnOrder;
    RadioGroup radioGroupOrder;
    RadioButton radioButtonOrder;
    EditText etNama, etEmail;

    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etNama = view.findViewById(R.id.et_namacus);
        etEmail = view.findViewById(R.id.et_emailcus);
        radioGroupOrder = view.findViewById(R.id.rg_layanan);
        btnOrder = view.findViewById(R.id.btn_order);

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedRb = radioGroupOrder.getCheckedRadioButtonId();

                radioButtonOrder = view.findViewById(selectedRb);

                String nama = etNama.getText().toString();
                String email = etEmail.getText().toString();
                String pilihan = radioButtonOrder.getText().toString();

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=6283822631952"+"&text=Order (Ambil dan Antar)%0A%0ANama : "+nama+"%0ALayanan : "+pilihan+"%0AEmail : "+email+"%0A%0Anb : cantumkan share location!!!"));
                startActivity(intent);

            }
        });

    }

}
