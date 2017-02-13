package com.example.larslb.widgettest;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewExerciseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewExerciseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewExerciseFragment extends ListFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER



    private ArrayList<String> mData;
    private ArrayList<Integer> mTime;
    private ContactsContract.Directory mdir;

    private OnFragmentInteractionListener mListener;

    ListView mAthleteList;

    ArrayList<String> mStoredAthletes;
    EditText mAddAthlete;
    Button mAddAthleteButtton;

    ArrayAdapter<String> mArrayAdapter;
    public NewExerciseFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static NewExerciseFragment newInstance(ArrayList<String> mData, ArrayList<Integer> mTime) {
        NewExerciseFragment fragment = new NewExerciseFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        savedFiles = getActivity().getApplicationContext().fileList();
        ArrayAdapter mArrayAdapter = ArrayAdapter.createFromResource(getActivity(),mStoredAthletes,android.R.layout.simple_list_item_1);
        setListAdapter(mArrayAdapter);
        ArrayAdapter.createFromResource()

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_new_exercise, container, false);
        mAthleteList = (ListView) rootView.findViewById(R.id.athlete_list);

        mAddAthlete = (EditText) rootView.findViewById(R.id.athlete_name);
        mAddAthleteButtton = (Button) rootView.findViewById(R.id.add_athlete_button);




        return rootView;

    }

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
