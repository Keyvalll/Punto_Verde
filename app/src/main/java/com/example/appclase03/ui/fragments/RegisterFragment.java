package com.example.appclase03.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.appclase03.MapsActivity;
import com.example.appclase03.R;
import com.example.appclase03.model.User;

import java.util.ArrayList;


public class RegisterFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText txt_Email;
    private EditText txt_Name;
    private EditText txt_Password;
    private EditText txt_ComfirmPass;
    private ArrayList<User> users;

    private String blockCharacterSet = "~#^|$%&*!/\'¿¡?!#$%&/();,-_[]{}´+¨*";
    private OnFragmentInteractionListener mListener;

    public RegisterFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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
        //inflater to view
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        txt_Email = view.findViewById(R.id.txt_Email);
        txt_Name = view.findViewById(R.id.txt_Name);
        txt_Password = view.findViewById(R.id.txt_Password);
        txt_ComfirmPass = view.findViewById(R.id.txt_confirm_pass);

        txt_Email.setFilters(new InputFilter[] { filter });
        txt_Name.setFilters(new InputFilter[] { filter });
        txt_Password.setFilters(new InputFilter[] { filter });
        txt_ComfirmPass.setFilters(new InputFilter[] { filter });

        view.findViewById(R.id.btn_register).setOnClickListener(this);

        users = new ArrayList<>();

        return view;
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_register:
                makeRegister();
                break;
        }
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


    private void makeRegister(){
        String name= txt_Name.getText().toString();
        String email = txt_Email.getText().toString();
        String password = txt_Password.getText().toString();

        if (name.isEmpty()){
            txt_Name.setError("Require");
            return;
        }
        if (email.isEmpty()){
            txt_Email.setError("Require");
            return;
        }
        if (password.isEmpty()){
            txt_Password.setError("Require");
            return;
        }

        //TODO: Verificar que no existan
        User user = new User(txt_Name.getText().toString(), txt_Email.getText().toString(), txt_Password.getText().toString());

        //TODO: Almacenar un nuevo usuario
        users.add(user);

        Intent intent = new Intent(getActivity(), MapsActivity.class);
        startActivity(intent);
        //finish();
    }

    private InputFilter filter = new InputFilter() {

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

            if (source != null && blockCharacterSet.contains(("" + source))) {
                return "";
            }
            return null;
        }
    };
}
