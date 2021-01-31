package com.bpapps.connectivitymanagertest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class MainViewFragment extends Fragment {

    private MainViewViewModel mViewModel;

    private AppCompatEditText mEetDataSetFlag;
    private AppCompatTextView mTvDataSetShower;
    private AppCompatButton mBtnGetDataSet;

    public static MainViewFragment newInstance() {
        return new MainViewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_view_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(MainViewViewModel.class);

        mEetDataSetFlag = view.findViewById(R.id.etDataSetFlag);
        mTvDataSetShower = view.findViewById(R.id.tvDataSetShower);
        mBtnGetDataSet = view.findViewById(R.id.btnGetDataSet);
        mBtnGetDataSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag;
                try {
                    flag = Integer.parseInt(mEetDataSetFlag.getText().toString());
                } catch (Exception e) {
                    flag = -1;
                }

                String dataSet = mViewModel.getDataSet(flag);

                mTvDataSetShower.setText(dataSet);
            }
        });
    }
}