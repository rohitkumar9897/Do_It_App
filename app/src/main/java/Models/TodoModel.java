package Models;

public class TodoModel {
    String taskDesc, taskID;
    Boolean status;

    public TodoModel(){

    }


    public TodoModel(String taskID, String taskDesc, Boolean status, String priority) {
        this.taskDesc = taskDesc;
        this.taskID = taskID;
        this.status = status;
        this.priority = priority;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    String priority;

    public String getTaskDesc() {
        return taskDesc;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


}
