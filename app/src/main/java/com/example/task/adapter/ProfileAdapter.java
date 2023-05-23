package com.example.task.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task.R;
import com.example.task.data.Profile;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {


    List<Profile> profileList;

    public ProfileAdapter(List<Profile> profileList){
        this.profileList = profileList;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_profile,parent,false);
        return new ProfileViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        holder.setProfileData(profileList.get(position));
    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView name, hobbies;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageview);
            name = itemView.findViewById(R.id.name);
            hobbies = itemView.findViewById(R.id.hobbies);
        }

        public void setProfileData(Profile profile){
            name.setText(profile.getName());
            hobbies.setText(profile.getHobbies());

            imageView.setImageResource(profile.getImageResourceID());

        }

    }
}
