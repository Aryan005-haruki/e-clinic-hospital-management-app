package com.example.tatwa10.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tatwa10.R;

public class DiseaseDetailFragment extends Fragment {

    private TextView textViewName, textViewCategory, textViewSymptoms, textViewPrecautions, textViewTreatment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_disease_detail, container, false);

        textViewName = view.findViewById(R.id.text_view_disease_name_detail);
        textViewCategory = view.findViewById(R.id.text_view_disease_category_detail);
        textViewSymptoms = view.findViewById(R.id.text_view_symptoms);
        textViewPrecautions = view.findViewById(R.id.text_view_precautions);
        textViewTreatment = view.findViewById(R.id.text_view_treatment);

        if (getArguments() != null) {
            String name = getArguments().getString("name");
            String category = getArguments().getString("category");
            String symptoms = getArguments().getString("symptoms");
            String precautions = getArguments().getString("precautions");
            String treatment = getArguments().getString("treatment");

            textViewName.setText(name);
            textViewCategory.setText(category + " Disease");
            textViewSymptoms.setText(symptoms);
            textViewPrecautions.setText(precautions);
            textViewTreatment.setText(treatment);
        }

        return view;
    }
}
