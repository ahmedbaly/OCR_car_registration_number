package com.example.ahmed.blank_project.parents;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment {
    public Unbinder unbinder;

    private Callbacks mCallbacks;

    public interface Callbacks {
        public void onBackPressedCallback();
    }

    public abstract int getLayout();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(getLayout(), container, false);

        unbinder = ButterKnife.bind(this, view);

        return view;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @Override
    public void onDetach(){
        super.onDetach();
    }

    public void onBackPressed() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.popBackStack();

//        FragmentTransaction tx = getFragmentManager().beginTransaction();
//        tx.replace( getLayout(), new Fragment() ).addToBackStack( "tag" ).commit();
    }





}