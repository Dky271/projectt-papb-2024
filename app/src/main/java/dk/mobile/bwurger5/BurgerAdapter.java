package dk.mobile.bwurger5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BurgerAdapter extends RecyclerView.Adapter<BurgerAdapter.BurgerViewHolder> {

    private List<Burger> burgerList;
    private OnBurgerClickListener listener;

    public interface OnBurgerClickListener {
        void onDelete(Burger burger);
    }

    public void setOnBurgerClickListener(OnBurgerClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public BurgerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.burger_item, parent, false);
        return new BurgerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BurgerViewHolder holder, int position) {
        Burger burger = burgerList.get(position);
        holder.textViewName.setText(burger.getName());
        holder.textViewIngredients.setText(burger.getIngredients());

        holder.buttonDelete.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDelete(burger);
            }
        });
    }

    @Override
    public int getItemCount() {
        return burgerList != null ? burgerList.size() : 0;
    }

    public void setBurgerList(List<Burger> burgerList) {
        this.burgerList = burgerList;
        notifyDataSetChanged();
    }

    static class BurgerViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName, textViewIngredients;
        private Button buttonDelete;

        public BurgerViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewIngredients = itemView.findViewById(R.id.textViewIngredients);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }
    }
}
