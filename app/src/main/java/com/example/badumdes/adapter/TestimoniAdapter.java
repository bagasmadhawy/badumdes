package com.example.badumdes.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.badumdes.R;
import com.example.badumdes.database.AppDatabase;
import com.example.badumdes.database.TestimoniModel;
import com.example.badumdes.fragment.HomeFragment;

import java.util.ArrayList;

public class TestimoniAdapter extends RecyclerView.Adapter<TestimoniAdapter.ViewHolder> {

    private Context context;
    private ArrayList<TestimoniModel> testimoniItems = new ArrayList<>();
    private AppDatabase appDatabase;

    public TestimoniAdapter(Context context){
        this.context = context;
        appDatabase = AppDatabase.initDatabase(this.context);
    }

    public void setData(ArrayList<TestimoniModel> items){
        testimoniItems.clear();
        testimoniItems.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TestimoniAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestimoniAdapter.ViewHolder holder, final int position) {
        holder.tvPesan.setText(testimoniItems.get(position).getPesan());
        holder.tvNama.setText(testimoniItems.get(position).getNama());

        holder.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    TestimoniModel testimoniModel = new TestimoniModel();

                    testimoniModel.setId(testimoniItems.get(position).getId());
                    testimoniModel.setPesan(testimoniItems.get(position).getPesan());
                    testimoniModel.setNama(testimoniItems.get(position).getNama());

                    appDatabase.testimoniDAO().deleteTestimoni(testimoniModel);

                    Log.e("Testimoni","sukse");
                    Toast.makeText(context, "Sukses dihapus", Toast.LENGTH_SHORT).show();
                }catch (Exception ex){
                    Log.e("Testimoni","gagal menampilkan, msg :"+ex.getMessage());
                    Toast.makeText(context, "Gagal menampilkan", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return testimoniItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button btnHapus;
        TextView tvPesan, tvNama;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            btnHapus = itemView.findViewById(R.id.itemlist_btn_hapus);
            tvPesan = itemView.findViewById(R.id.itemlist_tv_pesan);
            tvNama = itemView.findViewById(R.id.itemlist_tv_nama);
        }
    }
}
