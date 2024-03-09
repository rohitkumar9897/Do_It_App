package codeit.apps.doit.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import codeit.apps.doit.R;

public class FocusFragment extends Fragment {
    private Button startButton, stopButton;
    Chronometer chronometer;
    private boolean isRunning;


    public FocusFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_focus, container, false);

        startButton = view.findViewById(R.id.FocusStartBtn);
        stopButton = view.findViewById(R.id.FocusStopBtn);
        chronometer = view.findViewById(R.id.FocusChronometer);
        stopButton.setVisibility(View.GONE);
        isRunning = false;




        startButton.setOnClickListener(v -> {
            if(!isRunning){
                Toast.makeText(getContext(), "start", Toast.LENGTH_SHORT).show();

                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                stopButton.setVisibility(View.VISIBLE);
                startButton.setText("RESET");

            }else{
                Toast.makeText(getContext(), "start2", Toast.LENGTH_SHORT).show();
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                stopButton.setVisibility(View.GONE);
                startButton.setText("START");

            }




        });



        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "stop", Toast.LENGTH_SHORT).show();
                chronometer.stop();

            }
        });






        return view;
    }
}