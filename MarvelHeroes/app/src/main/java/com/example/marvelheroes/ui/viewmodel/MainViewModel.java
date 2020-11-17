package com.example.marvelheroes.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.marvelheroes.data.Hero;
import com.example.marvelheroes.data.HeroRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private HeroRepository repository;
    private LiveData<List<Hero>> allHeroes;

    public MainViewModel(@NonNull Application application) {
        super(application);

        repository = new HeroRepository(application);
        allHeroes = repository.getHeroes();
    }

    public void insert(Hero hero){ repository.insert(hero); }

    public void update(Hero hero){ repository.update(hero); }

    public void delete(Hero hero){ repository.delete(hero); }

    public void deleteAll(){ repository.deleteAllNotes(); }

    public LiveData<List<Hero>> getAllHeroes() {
        return allHeroes;
    }
}
