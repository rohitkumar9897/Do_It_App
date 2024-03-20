package codeit.apps.doit.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.Map;

import codeit.apps.doit.R;
public class ToDoFragment extends Fragment {

    RecyclerView toDoRecyclerView;
    FloatingActionButton addTaskButton;

    Map<String, Object> ToDoTasks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_to_do, container, false);


        toDoRecyclerView = view.findViewById(R.id.to_do_recyclerview);
        addTaskButton = view.findViewById(R.id.add_task_fab);
        ToDoTasks = new HashMap<>();




        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.add_todo_dialog, null);
                builder.setView(dialogView);

                EditText editTextText = dialogView.findViewById(R.id.add_todo_edittext);
                Spinner spinnerPriority = dialogView.findViewById(R.id.add_todo_spinner);

                builder.setTitle("ADD TASK").setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String text = editTextText.getText().toString();
                        // Get selected date from DatePicker
                        /*int year = datePicker.getYear();
                        int month = datePicker.getMonth();
                        int dayOfMonth = datePicker.getDayOfMonth();
                        String date = year + "-" + (month + 1) + "-" + dayOfMonth;
                        String priority = spinnerPriority.getSelectedItem().toString();

                        ToDoTasks.put("text", text);
                        ToDoTasks.put("priority", priority);*/



                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });







        return view;
    }
}