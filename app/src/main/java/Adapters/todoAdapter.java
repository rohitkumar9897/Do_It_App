package Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Models.TodoModel;
import codeit.apps.doit.R;

public class todoAdapter extends RecyclerView.Adapter<todoAdapter.TaskViewHolder> {

    private List<TodoModel> tasks;

    public todoAdapter(List<TodoModel> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public todoAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item_layout, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull todoAdapter.TaskViewHolder holder, int position) {
        TodoModel task = tasks.get(position);
        holder.bind(task);

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }


    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        private TextView taskDescTextView;
        private TextView taskPriorityTextView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            taskDescTextView = itemView.findViewById(R.id.todo_taskdesctv);
            taskPriorityTextView = itemView.findViewById(R.id.todo_priority);
        }

        public void bind(TodoModel task) {
            taskDescTextView.setText(task.getTaskDesc());
            taskPriorityTextView.setText(task.getPriority());
        }
    }


}
