package com.example.marvelheroes.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class HeroRepository {
    private HeroDao heroDao;
    private LiveData<List<Hero>> heroes;
    public HeroRepository(Application application) {
        HeroesDatabase database = HeroesDatabase.getInstance(application);
        heroDao = database.heroDao();
        heroes = heroDao.getAllHeroes();
    }
    public void insert(Hero hero) {
        new InsertNoteAsyncTask(heroDao).execute(hero);
    }
    public void update(Hero hero) {
        new UpdateNoteAsyncTask(heroDao).execute(hero);
    }
    public void delete(Hero hero) {
        new DeleteNoteAsyncTask(heroDao).execute(hero);
    }
    public void deleteAllNotes() {
        new DeleteAllNotesAsyncTask(heroDao).execute();
    }
    public LiveData<List<Hero>> getHeroes() {
        return heroes;
    }
    private static class InsertNoteAsyncTask extends AsyncTask<Hero, Void, Void> {
        private HeroDao heroDao;
        private InsertNoteAsyncTask(HeroDao heroDao) {
            this.heroDao = heroDao;
        }
        @Override
        protected Void doInBackground(Hero... heroes) {
            heroDao.insert(heroes[0]);
            return null;
        }
    }
    private static class UpdateNoteAsyncTask extends AsyncTask<Hero, Void, Void> {
        private HeroDao heroDao;
        private UpdateNoteAsyncTask(HeroDao heroDao) {
            this.heroDao = heroDao;
        }
        @Override
        protected Void doInBackground(Hero... heroes) {
            heroDao.update(heroes[0]);
            return null;
        }
    }
    private static class DeleteNoteAsyncTask extends AsyncTask<Hero, Void, Void> {
        private HeroDao heroDao;
        private DeleteNoteAsyncTask(HeroDao heroDao) {
            this.heroDao = heroDao;
        }
        @Override
        protected Void doInBackground(Hero... heroes) {
            heroDao.delete(heroes[0]);
            return null;
        }
    }
    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private HeroDao heroDao;
        private DeleteAllNotesAsyncTask(HeroDao heroDao) {
            this.heroDao = heroDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            heroDao.deleteAll();
            return null;
        }
    }
}
