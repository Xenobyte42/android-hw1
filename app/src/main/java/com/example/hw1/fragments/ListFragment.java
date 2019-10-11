package com.example.hw1.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hw1.R;


public class ListFragment extends Fragment {
    private DataAdapter mAdapter;
    private static final String SAVED_NUMBER_COUNT = "saved.numbercount";
    private int numberCount = 100;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            numberCount = savedInstanceState.getInt(SAVED_NUMBER_COUNT);
        }
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.list);
        mAdapter = new DataAdapter();
        int numOfColumns = getResources().getConfiguration()
                .orientation == Configuration.ORIENTATION_LANDSCAPE ? 4 : 3;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numOfColumns));
        recyclerView.setAdapter(mAdapter);

        view.findViewById(R.id.add_button).setOnClickListener(viewHolder -> {
            numberCount++;
            mAdapter.notifyItemInserted(numberCount);
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVED_NUMBER_COUNT, numberCount);
    }

    class DataAdapter extends RecyclerView.Adapter<DataViewHolder> {
        @NonNull
        @Override
        public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.list_element, parent, false);
            return new DataViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
            holder.numberView.setText(Integer.toString(position + 1));
            if ((position + 1) % 2 == 0) {
                holder.numberView.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
            } else {
                holder.numberView.setTextColor(ContextCompat.getColor(getContext(), R.color.blue));
            }

            holder.containerView.setOnClickListener(view -> {
                NumberViewFragment fragment = NumberViewFragment.getInstance(position + 1);
                getFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            });
        }

        @Override
        public int getItemCount() {
            return numberCount;
        }
    }

    class DataViewHolder extends RecyclerView.ViewHolder {
        private final TextView numberView;
        private final FrameLayout containerView;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            numberView = itemView.findViewById(R.id.number);
            containerView = (FrameLayout) itemView;
        }
    }
}
