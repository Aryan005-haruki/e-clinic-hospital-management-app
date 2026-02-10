package com.example.tatwa10.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tatwa10.Fragments.DiseaseDetailFragment;
import com.example.tatwa10.ModelClass.Disease;
import com.example.tatwa10.R;

import java.util.List;

public class DiseaseAdapter extends RecyclerView.Adapter<DiseaseAdapter.DiseaseViewHolder> {

    private List<Disease> diseases;
    private Context context;

    public DiseaseAdapter(List<Disease> diseases, Context context) {
        this.diseases = diseases;
        this.context = context;
    }

    @NonNull
    @Override
    public DiseaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_disease, parent, false);
        return new DiseaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiseaseViewHolder holder, int position) {
        Disease disease = diseases.get(position);
        holder.textViewName.setText(disease.getName());
        holder.textViewCategory.setText(disease.getCategory());
        holder.imageViewIcon.setImageResource(disease.getIconResId());

        holder.itemView.setOnClickListener(v -> {
            Bundle args = new Bundle();
            args.putString("name", disease.getName());
            args.putString("category", disease.getCategory());
            args.putString("symptoms", disease.getSymptoms());
            args.putString("precautions", disease.getPrecautions());
            args.putString("treatment", disease.getTreatment());

            DiseaseDetailFragment fragment = new DiseaseDetailFragment();
            fragment.setArguments(args);

            ((FragmentActivity) context).getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    public int getItemCount() {
        return diseases.size();
    }

    static class DiseaseViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewCategory;
        ImageView imageViewIcon;

        public DiseaseViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_disease_name);
            textViewCategory = itemView.findViewById(R.id.text_view_disease_category);
            imageViewIcon = itemView.findViewById(R.id.image_view_disease_icon);
        }
    }
}
