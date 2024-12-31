package com.jonander2233.test01examen01;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.jonander2233.test01examen01.ui.fragments.AviableCardsFragment;
import com.jonander2233.test01examen01.ui.fragments.ClasificationFragment;
import com.jonander2233.test01examen01.ui.fragments.ListFragment;
import com.jonander2233.test01examen01.utils.adapters.CardAdapter;
import com.jonander2233.test01examen01.utils.models.Card;
import com.jonander2233.test01examen01.utils.models.Competition;
import com.jonander2233.test01examen01.utils.parsers.CompetitionParser;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,CardAdapter.CardsDataListener {
    private DrawerLayout drawerLayout;
    private Competition competition;
    private CompetitionParser competitionParser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        competitionParser = new CompetitionParser(this);
        competitionParser.parse();
        competition = competitionParser.getCompetition();
        loadData();
        // Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configurar Navigation Drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Manejar navegación inicial
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListFragment(new CardAdapter(this))).commit();
            navigationView.setCheckedItem(R.id.nav_aviable_cards);
        }

        // Manejar el botón "Atrás" con OnBackPressedDispatcher
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    // Permitir el comportamiento predeterminado si no se intercepta
                    setEnabled(false);
                    getOnBackPressedDispatcher().onBackPressed();
                }
            }
        });
    }

    private void loadData() {
        getCards();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.nav_aviable_cards)
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListFragment(new CardAdapter(this))).commit();
        else if (item.getItemId() == R.id.nav_clasification)
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListFragment(null)).commit();

        drawerLayout.closeDrawer(GravityCompat.START); // Cerrar el Navigation Drawer al seleccionar un ítem
        return true;
    }

    @Override
    public List<Card> getCards() {
        return competition.getCards();
    }
}
