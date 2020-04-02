package com.fatihy.pdictionarypre_alpha;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class FeedRecyclerAdapter extends RecyclerView.Adapter<FeedRecyclerAdapter.WordHolder> {

    @NonNull
    @Override
    public WordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_view_item,parent,false);
                return new WordHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordHolder holder, int position) {
  holder.word.setText("");
  holder.meaning.setText("");
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class WordHolder extends RecyclerView.ViewHolder{
TextView word, meaning;

        public WordHolder(@NonNull View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.firstWord);
            meaning = itemView.findViewById(R.id.secondWord);
        }
    }

}
