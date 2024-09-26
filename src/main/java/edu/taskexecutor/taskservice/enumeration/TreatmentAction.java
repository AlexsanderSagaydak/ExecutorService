package edu.taskexecutor.taskservice.enumeration;

import lombok.Getter;

@Getter
public enum TreatmentAction {
    ACTION_A("Action A"),
    ACTION_B("Action B");

    private final String description;

    TreatmentAction(String description) {
        this.description = description;
    }

}
