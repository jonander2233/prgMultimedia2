package com.jonander2233.test01examen01.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jonander2233.test01examen01.R;
import com.jonander2233.test01examen01.utils.adapters.CardAdapter;
import com.jonander2233.test01examen01.utils.models.Card;

import java.util.ArrayList;
import java.util.List;

public class ListWithSpinnerFragment extends Fragment {

    private RecyclerView recyclerView;
    private Spinner spinner;
    private CardAdapter adapter;

    // Constructor
    public ListWithSpinnerFragment(CardAdapter.CardsDataListener cardsDataListener) {
        this.adapter = new CardAdapter(cardsDataListener);
    }

    // Se llama cuando el fragmento es adjuntado a la actividad, y en este momento obtenemos el listener
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflar el layout que contiene el Spinner y RecyclerView
        View view = inflater.inflate(R.layout.fragment_list_with_spinner, container, false);

        // Configurar RecyclerView
        recyclerView = view.findViewById(R.id.rvItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Configurar el adaptador con las tarjetas
        recyclerView.setAdapter(adapter);

        // Configurar Spinner
        spinner = view.findViewById(R.id.spinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, getSpinnerData());
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        return view;
    }

    // Método para obtener los datos del Spinner
    private List<String> getSpinnerData() {
        List<String> spinnerData = new ArrayList<>();
        spinnerData.add("Opción 1");
        spinnerData.add("Opción 2");
        spinnerData.add("Opción 3");
        return spinnerData;
    }

}
