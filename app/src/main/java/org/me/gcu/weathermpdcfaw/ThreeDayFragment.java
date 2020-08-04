package org.me.gcu.weathermpdcfaw;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ThreeDayFragment extends android.support.v4.app.Fragment {

    private BlankViewModel mViewModel;

    public static ThreeDayFragment newInstance() {
        return new ThreeDayFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.blank_fragment.xml, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(getActivity()).get(BlankViewModel.class);

        // TODO: Use the ViewModel

    setText("text");
        

    }


    public void setText(String text){
        TextView textView = (TextView) getView().findViewById(R.id.cityTitle);
        textView.setText(mViewModel.getStringText());
    }




}


