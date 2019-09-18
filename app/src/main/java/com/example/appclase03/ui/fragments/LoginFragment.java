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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appclase03.MapsActivity;
import com.example.appclase03.R;
import com.example.appclase03.utils.AppPreferences;


public class LoginFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button btn_Log;
    private EditText txt_name;
    private EditText txt_pass;

    private int maxLenghtPass = 15;

    private String blockCharacterSet = "~#^|$%&*!/\'¿¡?!#$%&/();,-_[]{}´+¨*";

    private OnFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        btn_Log = view.findViewById(R.id.button_login);
        txt_name = view.findViewById(R.id.email);
        txt_pass = view.findViewById(R.id.password);

        txt_name.setFilters(new InputFilter[] { filter });
        txt_pass.setFilters(new InputFilter[] { filter });

        btn_Log.setOnClickListener(this);

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
        makeLogin();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void makeLogin() {
        String email = txt_name.getText().toString();
        String password = txt_pass.getText().toString();
        txt_pass.setFilters(new InputFilter[] { filter });
        txt_pass.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLenghtPass)});
        txt_name.setFilters(new InputFilter[] { filter });

        if (email.isEmpty()) {
            txt_name.setError("Requiere correo electrónico");
            return;
        }
        if (password.isEmpty()) {
            txt_pass.setError("Requiere contraseña");
            return;
        }
        if (email.equalsIgnoreCase("a") && password.equalsIgnoreCase("1")) {

            AppPreferences.getInstance(getActivity()).put(AppPreferences.Keys.IS_LOGGED_IN, true);

            Toast.makeText( getActivity(), "Has hecho login", Toast.LENGTH_LONG).show();


            Intent intent = new Intent(getActivity(), MapsActivity.class);
            startActivity(intent);
            //finish();


        } else {
            Toast.makeText( getActivity(), "No coindicen", Toast.LENGTH_LONG).show();
        }

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
