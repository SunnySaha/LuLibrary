package com.example.lulibrary;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContentsFragment extends Fragment {

    FirebaseAuth firebaseAuth;
    Button logout;
    Context context;
    public ContentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {


        View view =    inflater.inflate(R.layout.fragment_contents, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
       logout = view.findViewById(R.id.logoutButton);
       logout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               firebaseAuth.signOut();
               Intent i = new Intent(container.getContext(), LoginActivity.class);
               startActivity(i);
               getActivity().finish();

           }
       });

       return view;

    }

}
