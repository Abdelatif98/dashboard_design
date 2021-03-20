package com.example.dashboarddesign;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link operationsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class operationsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public operationsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment operationsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static operationsFragment newInstance(String param1, String param2) {
        operationsFragment fragment = new operationsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        View rootView = inflater.inflate(R.layout.fragment_operations, container, false);
        //data for the recycleview
        List<operationsModel> oplist= new ArrayList<>();
        oplist.add(new operationsModel("Virement","-51.243,00","21 Mars"));
        oplist.add(new operationsModel("Virement","-65.233,00","19 Mars"));
        oplist.add(new operationsModel("Paiment par CB","-91.243,00","18 Mars"));
        oplist.add(new operationsModel("Mise à disposition","+15.1243,00","17 Mars"));
        oplist.add(new operationsModel("Mise à disposition","+51.243,00","16 Mars"));
        oplist.add(new operationsModel("Virement","-51.243,00","13 Mars"));
        oplist.add(new operationsModel("Virement","-51.243,00","10 Mars"));
        // 1. get a reference to recyclerView
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.oprecview);

        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        // 3. create an adapter
        recyclerAdapter mAdapter = new recyclerAdapter(this.getContext(),oplist);
        // 4. set adapter
        recyclerView.setAdapter(mAdapter);
        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());



        // Inflate the layout for this fragment
        return rootView;
    }
}