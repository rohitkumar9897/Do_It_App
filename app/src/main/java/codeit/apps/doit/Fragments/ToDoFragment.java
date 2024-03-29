package codeit.apps.doit.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapters.todoAdapter;
import Models.TodoModel;
import codeit.apps.doit.Onboarding;
import codeit.apps.doit.R;
public class ToDoFragment extends Fragment {

    RecyclerView toDoRecyclerView;
    FloatingActionButton addTaskButton;

    Map<String, Object> ToDoTasks;
    FirebaseFirestore db;

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
        db = FirebaseFirestore.getInstance();
        SharedPreferences sharedPreferences;
        sharedPreferences = getActivity().getSharedPreferences("user_data", Context.MODE_PRIVATE);
        String userName = sharedPreferences.getString("spusername", null);

        getData(userName);


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
                        String date = year + "-" + (month + 1) + "-" + dayOfMonth;*/
                        String priority = spinnerPriority.getSelectedItem().toString();

                        ToDoTasks.put("text", text);
                        ToDoTasks.put("priority", priority);


                        Map<String, Object> mp = new HashMap<>();
                        mp.put("taskDesc", text);
                        mp.put("status", true);
                        mp.put("priority", priority);
                        db.collection("tasks").document(userName).collection("user_tasks").add(mp).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("app_priyanshu", "add task success");
                                getData(userName);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(), "failed", Toast.LENGTH_SHORT).show();

                            }
                        });




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

    private void getData(String userName) {
        if(userName!=null){
            db.collection("tasks")
                    .document(userName)
                    .collection("user_tasks")
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                            List<TodoModel> tasks = new ArrayList<>();
                            for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                TodoModel task = documentSnapshot.toObject(TodoModel.class);
                                if (task != null) {
                                    tasks.add(task);
                                }
                            }

                            // Set up RecyclerView adapter
                            toDoRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            todoAdapter rvadapter = new todoAdapter(tasks);
                            toDoRecyclerView.setAdapter(rvadapter);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "some error occurred", Toast.LENGTH_SHORT).show();
                        }
                    });
        }else{
            Toast.makeText(getContext(), "username is null", Toast.LENGTH_SHORT).show();
        }
    }
}