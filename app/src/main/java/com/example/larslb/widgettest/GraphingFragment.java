package com.example.larslb.widgettest;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GraphingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GraphingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GraphingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String DATA = "Data";
    public static final String TIME = "Time";

    // TODO: Rename and change types of parameters
    private ArrayList<String> mData;
    private ArrayList<Integer> mTime;

    private OnFragmentInteractionListener mListener;

    public GraphingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param mData Parameter 1.
     * @param mTime Parameter 2.
     * @return A new instance of fragment GraphingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GraphingFragment newInstance(ArrayList<String>  mData, ArrayList<Integer> mTime) {
        GraphingFragment fragment = new GraphingFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(DATA, mData);
        args.putIntegerArrayList(TIME, mTime);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mData = getArguments().getStringArrayList(DATA);
            mTime = getArguments().getIntegerArrayList(TIME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_graphing, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
