package com.jonander2233.test01examen01.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jonander2233.test01examen01.R;
import com.jonander2233.test01examen01.utils.adapters.CardAdapter;

import java.util.ArrayList;
import java.util.List;

public class CardListWithSpinnerFragment extends Fragment {

    private RecyclerView recyclerView;
    private Spinner spinner;
    private CardAdapter adapter;

    // Constructor
    public CardListWithSpinnerFragment(CardAdapter.CardsDataListener cardsDataListener) {
        this.adapter = new CardAdapter(cardsDataListener);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflar el layout que contiene el Spinner y RecyclerView
        View view = inflater.inflate(R.layout.fragment_list_with_spinner, container, false);

        // Configurar RecyclerView
        recyclerView = view.findViewById(R.id.rvItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        // Configurar Spinner
        spinner = view.findViewById(R.id.spinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, getSpinnerData());
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        // Configurar el listener del Spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Obtener la opción seleccionada
                String selectedOption = parent.getItemAtPosition(position).toString();

                // Manejar la lógica según la opción seleccionada
                handleSpinnerSelection(selectedOption);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Manejar el caso donde no se seleccionó ninguna opción
            }
        });

        return view;
    }

    // Método para manejar la lógica cuando se selecciona una opción del Spinner
    private void handleSpinnerSelection(String selectedOption) {
        // Aquí puedes realizar acciones según la opción seleccionada
        switch (selectedOption) {
            case "Opción 1":
                // Lógica para la opción 1
                System.out.println("Seleccionaste Opción 1");
                break;
            case "Opción 2":
                // Lógica para la opción 2
                System.out.println("Seleccionaste Opción 2");
                break;
            case "Opción 3":
                // Lógica para la opción 3
                System.out.println("Seleccionaste Opción 3");
                break;
            default:
                System.out.println("Opción desconocida seleccionada");
                break;
        }
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
