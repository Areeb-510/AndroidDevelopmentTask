package com.example.task.fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.task.R;
import com.example.task.adapter.ProfileAdapter;
import com.example.task.data.Profile;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private ViewPager2 viewPager2;
    private List<Profile> profileList;
    private ProfileAdapter adapter;


    private ImageButton datebtn, relationbtn, friendsbtn;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        profileList = new ArrayList<>();
        viewPager2 = view.findViewById(R.id.viewpager2);

        String packageName = requireContext().getPackageName();
        int r1 = requireContext().getResources().getIdentifier("p1", "raw", packageName);
        int r2 = requireContext().getResources().getIdentifier("p2", "raw", packageName);
        int r3 = requireContext().getResources().getIdentifier("p3", "raw", packageName);

        profileList.add(new Profile(r1,"Georgia","Swimming, Dancing, Binge Watching"));

        profileList.add(new Profile(r2,"Bella","Loves to travel"));

        profileList.add(new Profile(r3,"Rose","Playing piano, Singing"));

        adapter = new ProfileAdapter(profileList);

        viewPager2.setAdapter(adapter);




        datebtn = view.findViewById(R.id.datebtn);
        relationbtn = view.findViewById(R.id.realtionbtn);
        friendsbtn = view.findViewById(R.id.friendsbtn);


//        int tintColor = ContextCompat.getColor(getActivity(), R.color.blue);
//        datebtn.setColorFilter(tintColor, PorterDuff.Mode.SRC_IN);
//
//        int tintColor2 = ContextCompat.getColor(getActivity(), R.color.red);
//        relationbtn.setColorFilter(tintColor2, PorterDuff.Mode.SRC_IN);
//
//        int tintColor3 = ContextCompat.getColor(getActivity(), R.color.green);
//        friendsbtn.setColorFilter(tintColor3, PorterDuff.Mode.SRC_IN);

        int currentItem = 0;

        datebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = viewPager2.getCurrentItem();
                int nextPosition = currentPosition + 1;
                if (nextPosition < adapter.getItemCount()) {
                    viewPager2.setCurrentItem(nextPosition);
                }else{
                    Toast.makeText(getActivity(),"No more profiles",Toast.LENGTH_SHORT).show();
                }
                showSnackbar(v, "Date initiated");
            }


        });

        relationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = viewPager2.getCurrentItem();
                int nextPosition = currentPosition + 1;
                if (nextPosition < adapter.getItemCount()) {
                    viewPager2.setCurrentItem(nextPosition);
                }else{
                    Toast.makeText(getActivity(),"No more profiles",Toast.LENGTH_SHORT).show();
                }
                showSnackbar(v, "Interested in romantic friendship");
            }

        });

        friendsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = viewPager2.getCurrentItem();
                int nextPosition = currentPosition + 1;
                if (nextPosition < adapter.getItemCount()) {
                    viewPager2.setCurrentItem(nextPosition);
                }else{
                    Toast.makeText(getActivity(),"No more profiles",Toast.LENGTH_SHORT).show();
                }
                showSnackbar(v, "Friend request sent");
            }
        });


        return view;
    }

    private void showSnackbar(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}