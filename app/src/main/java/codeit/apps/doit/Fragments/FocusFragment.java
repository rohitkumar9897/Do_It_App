package codeit.apps.doit.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import codeit.apps.doit.R;

public class FocusFragment extends Fragment {
    private Button startButton, stopButton, resumeButton, pauseButton;
    private long pausedTime = 0;
    ExpandableListView expandableChoice;
    Chronometer chronometer;
    private boolean isRunning;
    Spinner modeChoice;
    SharedPreferences sharedPreferences;


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
        stopButton = view.findViewById(R.id.FocusStopButton);
        chronometer = view.findViewById(R.id.FocusChronometer);
        resumeButton = view.findViewById(R.id.FocusResumeButton);
        pauseButton= view.findViewById(R.id.FocusPauseBtn);
        modeChoice = view.findViewById(R.id.focus_mode_spinner);
       // expandableChoice = view.findViewById(R.id.expandable_choice);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        sharedPreferences = requireActivity().getSharedPreferences("user_data", Context.MODE_PRIVATE);



        startButton.setVisibility(View.VISIBLE);
        stopButton.setVisibility(View.GONE);
        resumeButton.setVisibility(View.GONE);
        pauseButton.setVisibility(View.GONE);

        isRunning = false;

        modeChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedMode = (String) parent.getItemAtPosition(position);
                if(selectedMode == "Timer"){
                    Toast.makeText(getContext(), "timer", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Focus", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        startButton.setOnClickListener(v -> {

            startButton.setVisibility(View.GONE);
            stopButton.setVisibility(View.GONE);
            resumeButton.setVisibility(View.GONE);
            pauseButton.setVisibility(View.VISIBLE);


            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
            isRunning = true;






        });



        stopButton.setOnClickListener(v -> {

                    startButton.setVisibility(View.VISIBLE);
                    stopButton.setVisibility(View.GONE);
                    resumeButton.setVisibility(View.GONE);
                    pauseButton.setVisibility(View.GONE);

                    long pushTime = SystemClock.elapsedRealtime() - chronometer.getBase();
                    pushToBase(pushTime);


                    chronometer.setBase(SystemClock.elapsedRealtime());
                    chronometer.stop();
                    isRunning = false;

                    String username = sharedPreferences.getString("spname", null);
        });



        resumeButton.setOnClickListener(v -> {

            startButton.setVisibility(View.GONE);
            stopButton.setVisibility(View.GONE);
            resumeButton.setVisibility(View.GONE);
            pauseButton.setVisibility(View.VISIBLE);

            chronometer.setBase(SystemClock.elapsedRealtime() - pausedTime);

            chronometer.start();
            isRunning = true;

        });

        pauseButton.setOnClickListener(v -> {

            startButton.setVisibility(View.GONE);
            stopButton.setVisibility(View.VISIBLE);
            resumeButton.setVisibility(View.VISIBLE);
            pauseButton.setVisibility(View.GONE);

            pausedTime = SystemClock.elapsedRealtime() - chronometer.getBase();

            chronometer.stop();
            isRunning = false;



        });



        return view;
    }

    private void pushToBase(long pushTime) {
        //3000 = 3 seconds


    }
}