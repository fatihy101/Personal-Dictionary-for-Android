package com.fatihy.pdictionarypre_alpha;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FeedRecyclerAdapter extends RecyclerView.Adapter<FeedRecyclerAdapter.WordHolder> {

    private ArrayList<String> words;
    private ArrayList<String> meanings;
//Constructor
    public FeedRecyclerAdapter(ArrayList<String> words, ArrayList<String> meanings) {
        this.words = words;
        this.meanings = meanings;
    }

    @NonNull
    @Override
    public WordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext()); //We're saying that parent variable demand a context in this line.
        View view = layoutInflater.inflate(R.layout.recycler_view_item,parent,false); //We're binding our view_item.xml to the parent.
                return new WordHolder(view); // Now we're returning the value as a "WordHolder".
    }

    @Override
    public void onBindViewHolder(@NonNull WordHolder holder, int position) {

  holder.word.setText(words.get(position));
  holder.meaning.setText(meanings.get(position));
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    class WordHolder extends RecyclerView.ViewHolder{
Button word, meaning;

        public WordHolder(@NonNull View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.firstWord);
            meaning = itemView.findViewById(R.id.secondWord);
        }
    }

}
