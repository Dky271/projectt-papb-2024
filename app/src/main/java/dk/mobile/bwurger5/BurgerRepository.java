package dk.mobile.bwurger5;

import android.util.Log;
import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class BurgerRepository {
    private DatabaseReference databaseReference;

    public BurgerRepository() {
        FirebaseDatabase database = FirebaseDatabase.getInstance(
                "https://bwurger-5cea1-default-rtdb.asia-southeast1.firebasedatabase.app/"
        );
        databaseReference = database.getReference("burgers");
    }

    public void addBurger(Burger burger) {
        String key = databaseReference.push().getKey();
        burger.setId(key);
        databaseReference.child(key).setValue(burger);
    }

    public void deleteBurger(Burger burger) {
        if (burger.getId() != null) {
            databaseReference.child(burger.getId()).removeValue();
        }
    }

    public void getAllBurgers(OnBurgersLoadedListener listener) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Burger> burgers = new ArrayList<>();
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    Burger burger = childSnapshot.getValue(Burger.class);
                    if (burger != null) {
                        burger.setId(childSnapshot.getKey());
                        burgers.add(burger);
                    }
                }
                listener.onBurgersLoaded(burgers);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("BurgerRepository", "Error fetching burgers", error.toException());
                listener.onError(error.getMessage());
            }
        });
    }

    public interface OnBurgersLoadedListener {
        void onBurgersLoaded(List<Burger> burgers);
        void onError(String errorMessage);
    }

    public void migrateRoomDataToFirebase(BurgerDatabase roomDatabase) {
        new Thread(() -> {
            List<Burger> roomBurgers = (List<Burger>) roomDatabase;
            for (Burger burger : roomBurgers) {
                addBurger(burger);
            }
        }).start();
    }
}