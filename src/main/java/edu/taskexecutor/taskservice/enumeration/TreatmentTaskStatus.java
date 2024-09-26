package edu.taskexecutor.taskservice.enumeration;

import lombok.Getter;

@Getter
public enum TreatmentTaskStatus {

    ACTIVE("Task is ACTIVE"),
    COMPLETED("Task is COMPLETED");

    private final String description;

    TreatmentTaskStatus(String description) {
        this.description = description;
    }

}
