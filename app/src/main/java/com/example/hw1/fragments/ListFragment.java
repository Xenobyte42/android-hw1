package com.example.hw1.fragments;

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

import com.example.hw1.OnItemSelected;
import com.example.hw1.R;


public class ListFragment extends Fragment {
    private DataAdapter mAdapter;
    private static final String SAVED_NUMBER_COUNT = "saved.numbercount";
    private int numberCount = 100;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            numberCount = savedInstanceState.getInt(SAVED_NUMBER_COUNT);
        }
        mAdapter = new DataAdapter();
    }

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
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),
                getResources().getInteger(R.integer.element_in_row)));
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
            int color;
            holder.numberView.setText(Integer.toString(position + 1));
            if ((position + 1) % 2 == 0) {
                color = R.color.red;
            } else {
                color = R.color.blue;
            }
            holder.numberView.setTextColor(ContextCompat.getColor(getContext(), color));
            holder.containerView.setOnClickListener(view -> {
                ((OnItemSelected)getActivity()).onItemSelected(position + 1, color);
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
