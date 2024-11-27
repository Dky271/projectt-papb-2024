package dk.mobile.bwurger5;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Burger.class}, version = 1)
public abstract class BurgerDatabase extends RoomDatabase {
    private static BurgerDatabase instance;

    public static synchronized BurgerDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            BurgerDatabase.class, "burger_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
