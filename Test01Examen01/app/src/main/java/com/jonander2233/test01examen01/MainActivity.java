package com.jonander2233.test01examen01;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.jonander2233.test01examen01.ui.fragments.CardListWithSpinnerFragment;
import com.jonander2233.test01examen01.ui.fragments.ListFragment;
import com.jonander2233.test01examen01.utils.SortObjects;
import com.jonander2233.test01examen01.utils.adapters.CardAdapter;
import com.jonander2233.test01examen01.utils.adapters.MatchAdapter;
import com.jonander2233.test01examen01.utils.models.Card;
import com.jonander2233.test01examen01.utils.models.Competition;
import com.jonander2233.test01examen01.utils.parsers.CompetitionParser;


import java.util.List;

public class MainActivity extends AppCompatActivity implements MatchAdapter.MatchDataListener, NavigationView.OnNavigationItemSelectedListener, CardAdapter.CardsDataListener {

    private DrawerLayout drawerLayout;
    private Competition competition;
    private CompetitionParser competitionParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();
        setLayout(savedInstanceState);
    }

    private void setLayout(Bundle savedInstanceState) {
        // Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configurar Navigation Drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View navView = navigationView.getHeaderView(0);

        //para editar el texto de la barra lateral, hay que conseguir la vista de nav_view
        TextView tvCompetitionName = navView.findViewById(R.id.tvCompetitionName);
        TextView tvCompetitionId = navView.findViewById(R.id.tvCompetitionId);
        if (competition != null) {
            tvCompetitionName.setText(competition.getName());
            tvCompetitionId.setText(String.valueOf("id: " + competition.getId()));
        }


        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Manejar navegación inicial
        if (savedInstanceState == null) {
            // Reemplazar el fragmento con el nuevo fragmento que maneja Spinner y RecyclerView
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CardListWithSpinnerFragment(this)).commit();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListFragment(new CardAdapter(this))).commit();
            navigationView.setCheckedItem(R.id.nav_aviable_cards);
        }

        // Manejar el botón "Atrás"
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    setEnabled(false);
                    getOnBackPressedDispatcher().onBackPressed();
                }
            }
        });
    }

    private void loadData() {
        competitionParser = new CompetitionParser(this);
        competitionParser.parse();
        competition = competitionParser.getCompetition();
        getCards();
        SortObjects.orderPlayers(competition);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_aviable_cards) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListFragment(new CardAdapter(this))).commit();
        } else if (item.getItemId() == R.id.nav_clasification) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ListFragment(new MatchAdapter(this))).commit();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public List<Card> getCards() {
        SortObjects.orderCards(competition.getCards());
        return competition.getCards();
    }

    @Override
    public Competition getCompetition() {
        return competition;
    }
}
