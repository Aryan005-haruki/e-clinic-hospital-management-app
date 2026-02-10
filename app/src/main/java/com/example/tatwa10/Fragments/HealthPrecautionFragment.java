package com.example.tatwa10.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tatwa10.Adapters.DiseaseAdapter;
import com.example.tatwa10.MainActivity;
import com.example.tatwa10.ModelClass.Disease;
import com.example.tatwa10.R;

import java.util.ArrayList;
import java.util.List;

public class HealthPrecautionFragment extends Fragment {

    private RecyclerView recyclerView;
    private DiseaseAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        MainActivity.navigationView.setCheckedItem(R.id.nav_health_precaution);
        MainActivity.currentFragment = "healthPrecaution";
        View view = inflater.inflate(R.layout.fragment_health_precaution, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_diseases);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Disease> diseases = getDiseaseList();
        adapter = new DiseaseAdapter(diseases, getContext());
        recyclerView.setAdapter(adapter);

        return view;
    }

    private List<Disease> getDiseaseList() {
        List<Disease> diseases = new ArrayList<>();

        // Minor Diseases
        diseases.add(new Disease(
                "Common Cold",
                "Minor",
                "• Runny or stuffy nose\n• Sore throat\n• Cough\n• Sneezing\n• Mild headache\n• Mild fever",
                "• Wash hands frequently\n• Avoid close contact with sick people\n• Don't touch your face\n• Keep immune system strong\n• Get adequate sleep",
                "• Rest and drink plenty of fluids\n• Use saline nasal drops\n• Gargle with salt water\n• Take over-the-counter pain relievers\n• Usually resolves in 7-10 days",
                R.drawable.ic_prescription));

        diseases.add(new Disease(
                "Fever",
                "Minor",
                "• Body temperature above 100.4°F (38°C)\n• Sweating\n• Chills\n• Headache\n• Muscle aches\n• Weakness",
                "• Maintain good hygiene\n• Stay hydrated\n• Avoid crowded places during flu season\n• Get vaccinated\n• Eat healthy food",
                "• Rest and stay hydrated\n• Take fever-reducing medication (Paracetamol)\n• Use cool compress\n• Consult doctor if fever persists >3 days\n• Monitor temperature regularly",
                R.drawable.ic_prescription));

        diseases.add(new Disease(
                "Cough",
                "Minor",
                "• Persistent coughing\n• Throat irritation\n• Mucus production\n• Chest discomfort\n• Wheezing (sometimes)",
                "• Avoid smoking and polluted air\n• Stay hydrated\n• Use air humidifier\n• Avoid allergens\n• Cover mouth when coughing",
                "• Drink warm liquids\n• Use cough syrup\n• Honey and ginger tea\n• Steam inhalation\n• Consult doctor if persists >2 weeks",
                R.drawable.ic_prescription));

        diseases.add(new Disease(
                "Headache",
                "Minor",
                "• Pain in head or neck\n• Throbbing or constant pain\n• Sensitivity to light\n• Nausea (sometimes)\n• Dizziness",
                "• Manage stress\n• Get regular sleep\n• Stay hydrated\n• Avoid trigger foods\n• Limit screen time",
                "• Rest in quiet, dark room\n• Apply cold/hot compress\n• Take pain relievers\n• Stay hydrated\n• Gentle neck massage",
                R.drawable.ic_prescription));

        diseases.add(new Disease(
                "Stomach Ache",
                "Minor",
                "• Abdominal pain or cramping\n• Bloating\n• Nausea\n• Loss of appetite\n• Diarrhea or constipation",
                "• Eat clean, fresh food\n• Wash hands before eating\n• Avoid overeating\n• Limit spicy/oily food\n• Drink clean water",
                "• Rest and stay hydrated\n• Eat bland foods (rice, banana)\n• Avoid dairy temporarily\n• Take antacids if needed\n• See doctor if severe or persistent",
                R.drawable.ic_prescription));

        // Major Diseases
        diseases.add(new Disease(
                "Dengue Fever",
                "Major",
                "• High fever (104°F/40°C)\n• Severe headache\n• Pain behind eyes\n• Joint and muscle pain\n• Skin rash\n• Bleeding from nose/gums",
                "• Use mosquito repellent\n• Wear long-sleeved clothes\n• Use mosquito nets\n• Remove stagnant water\n• Keep surroundings clean",
                "• CONSULT DOCTOR IMMEDIATELY\n• Complete bed rest\n• Drink plenty of fluids (ORS, coconut water)\n• Take Paracetamol only (NO Aspirin)\n• Monitor platelet count daily\n• Hospitalization if severe",
                R.drawable.ic_prescription));

        diseases.add(new Disease(
                "Diabetes",
                "Major",
                "• Increased thirst and urination\n• Extreme hunger\n• Unexplained weight loss\n• Fatigue\n• Blurred vision\n• Slow-healing wounds",
                "• Maintain healthy weight\n• Exercise regularly (30 min/day)\n• Eat balanced diet\n• Limit sugar intake\n• Regular health checkups\n• Manage stress",
                "• Regular blood sugar monitoring\n• Take prescribed medication\n• Follow diabetic diet plan\n• Regular exercise\n• Annual eye & foot checkups\n• Consult endocrinologist",
                R.drawable.ic_prescription));

        diseases.add(new Disease(
                "Hypertension (High BP)",
                "Major",
                "• Often NO symptoms\n• Severe headaches\n• Nosebleeds\n• Fatigue\n• Vision problems\n• Chest pain\n• Irregular heartbeat",
                "• Reduce salt intake\n• Maintain healthy weight\n• Exercise regularly\n• Limit alcohol\n• Quit smoking\n• Manage stress\n• Regular BP monitoring",
                "• Take prescribed BP medication daily\n• Follow DASH diet\n• Regular exercise\n• Daily BP monitoring\n• Reduce caffeine\n• Regular doctor visits",
                R.drawable.ic_prescription));

        diseases.add(new Disease(
                "Asthma",
                "Major",
                "• Shortness of breath\n• Chest tightness\n• Wheezing\n• Coughing (especially at night)\n• Difficulty speaking\n• Rapid breathing",
                "• Avoid triggers (dust, smoke, pollen)\n• Keep inhaler always with you\n• Don't smoke\n• Exercise regularly (with precaution)\n• Get flu vaccine\n• Keep home dust-free",
                "• Use prescribed inhaler correctly\n• Take controller medication daily\n• Avoid triggers\n• Learn breathing exercises\n• Have action plan for attacks\n• Regular pulmonologist visits",
                R.drawable.ic_prescription));

        diseases.add(new Disease(
                "Typhoid",
                "Major",
                "• Prolonged high fever\n• Weakness and fatigue\n• Abdominal pain\n• Headache\n• Loss of appetite\n• Diarrhea or constipation\n• Rose-colored spots on chest",
                "• Drink clean, boiled water\n• Wash hands before eating\n• Get typhoid vaccination\n• Eat freshly cooked food\n• Avoid street food\n• Maintain hygiene",
                "• CONSULT DOCTOR (Antibiotics required)\n• Complete bed rest\n• Drink plenty of fluids\n• Eat easily digestible food\n• Take prescribed antibiotics fully\n• Isolation from others",
                R.drawable.ic_prescription));

        diseases.add(new Disease(
                "COVID-19",
                "Major",
                "• Fever or chills\n• Dry cough\n• Difficulty breathing\n• Loss of taste/smell\n• Fatigue\n• Body ache\n• Sore throat",
                "• Get vaccinated and boosted\n• Wear mask in crowded places\n• Maintain 6 feet distance\n• Wash hands frequently\n• Avoid touching face\n• Stay home if sick",
                "• Isolate immediately\n• Monitor oxygen levels\n• Rest and hydrate\n• Take prescribed medication\n• Consult doctor if breathing difficulty\n• Hospital if oxygen <94%",
                R.drawable.ic_prescription));

        return diseases;
    }
}
