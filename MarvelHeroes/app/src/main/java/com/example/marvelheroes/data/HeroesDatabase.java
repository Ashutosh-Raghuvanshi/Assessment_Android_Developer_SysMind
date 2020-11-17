package com.example.marvelheroes.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Hero.class}, version = 1, exportSchema = false)
public abstract class HeroesDatabase extends RoomDatabase {
    private static HeroesDatabase sInstance;
    private static Context sContext;

    private static final String RES_URI = "android.resource://com.example.marvelheroes/drawable/";

    public abstract HeroDao heroDao();

    public static synchronized HeroesDatabase getInstance(Context context){
        sContext = context;
        if(sInstance == null){
            sInstance = Room.databaseBuilder(context.getApplicationContext(), HeroesDatabase.class, "heroes_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return sInstance;
    }

    public static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new populateDBAsyncTask(sInstance).execute();
        }
    };

    private static class populateDBAsyncTask extends AsyncTask<Void, Void, Void>{
        HeroDao heroDao;

        populateDBAsyncTask(HeroesDatabase db){
            this.heroDao = db.heroDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            heroDao.insert(new Hero("Iron Man", "Genius. Billionaire. Philanthropist. Tony Stark's confidence is only matched by his high-flying abilities as the hero called Iron Man.", RES_URI+"iron_man"));
            heroDao.insert(new Hero("Captain America", "Recipient of the Super-Soldier serum, World War II hero Steve Rogers fights for American ideals as one of the world’s mightiest heroes and the leader of the Avengers.", RES_URI+"captain_america"));
            heroDao.insert(new Hero("Hulk", "Dr. Bruce Banner lives a life caught between the soft-spoken scientist he’s always been and the uncontrollable green monster powered by his rage.", RES_URI+"hulk"));
            heroDao.insert(new Hero("Captain Marvel", "Carol Danvers becomes one of the universe's most powerful heroes when Earth is caught in the middle of a galactic war between two alien races.", RES_URI+"captain_marvel"));
            heroDao.insert(new Hero("Black Panther", "T’Challa is the king of the secretive and highly advanced African nation of Wakanda - as well as the powerful warrior known as the Black Panther.", RES_URI+"black_panther"));
            heroDao.insert(new Hero("Thor", "The son of Odin uses his mighty abilities as the God of Thunder to protect his home Asgard and planet Earth alike.", RES_URI+"thor"));
            heroDao.insert(new Hero("Black Widow", "Despite super spy Natasha Romanoff’s checkered past, she’s become one of S.H.I.E.L.D.’s most deadly assassins and a frequent member of the Avengers.", RES_URI+"black_widow"));
            heroDao.insert(new Hero("Hawkeye", "A master marksman and longtime friend of Black Widow, Clint Barton serves as the Avengers’ amazing archer.", RES_URI+"hawkeye"));
            heroDao.insert(new Hero("Spiderman", "Bitten by a radioactive spider, Peter Parker’s arachnid abilities give him amazing powers he uses to help others, while his personal life continues to offer plenty of obstacles.", RES_URI+"spiderman"));
            heroDao.insert(new Hero("Thanos", "The Mad Titan Thanos quests across the universe in search of the Infinity Stones, intending to use their limitless power for shocking purposes.", RES_URI+"thanos"));

            return null;
        }
    }
}
