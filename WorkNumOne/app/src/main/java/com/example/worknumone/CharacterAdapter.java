package com.example.worknumone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CarterHolder>
{
    private Context context;
    private ArrayList<CharacterTVShow> characterTVShows;

    public CharacterAdapter(Context context, ArrayList<CharacterTVShow> characterTVShows) {
        this.context = context;
        this.characterTVShows = characterTVShows;
    }

    @NonNull
    @Override
    public CarterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.character_layout_item,parent,false);
        return new CarterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterAdapter.CarterHolder holder, int position) {
        CharacterTVShow characterTVShow = characterTVShows.get(position);
        holder.SetDetails(characterTVShow);
    }

    @Override
    public int getItemCount() {
        return characterTVShows.size();
    }
    public class CarterHolder extends RecyclerView.ViewHolder
    {

        private TextView txtName, txtDescription;
        private ImageView ivPicture;

        public CarterHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            ivPicture = itemView.findViewById(R.id.ivPicture);
        }
        void SetDetails(CharacterTVShow characterTVShow)
        {
            txtName.setText(characterTVShow.name);
            txtDescription.setText(characterTVShow.description);
            ivPicture.setImageResource(characterTVShow.Picture);
        }
    }
}
