package dk.mobile.bwurger5;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BottomFragment extends Fragment implements BurgerRepository.OnBurgersLoadedListener {
    private BurgerDatabase roomDatabase;
    private BurgerRepository firebaseRepository;
    private BurgerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewBurgers);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new BurgerAdapter();
        recyclerView.setAdapter(adapter);
        roomDatabase = BurgerDatabase.getInstance(getContext());
        firebaseRepository = new BurgerRepository();
        adapter.setOnBurgerClickListener(burger -> {
            firebaseRepository.deleteBurger(burger);
        });
        loadBurgers();
        return view;
    }

    private void loadBurgers() {
        firebaseRepository.migrateRoomDataToFirebase(roomDatabase);
        firebaseRepository.getAllBurgers(this);
    }

    @Override
    public void onBurgersLoaded(List<Burger> burgers) {
        adapter.setBurgerList(burgers);
    }

    @Override
    public void onError(String errorMessage) {
        Toast.makeText(getContext(), "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
    }
}