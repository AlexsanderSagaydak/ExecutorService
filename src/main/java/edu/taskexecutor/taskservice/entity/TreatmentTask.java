package edu.taskexecutor.taskservice.entity;

import edu.taskexecutor.taskservice.enumeration.TreatmentAction;
import edu.taskexecutor.taskservice.enumeration.TreatmentTaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

import static jakarta.persistence.EnumType.STRING;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "treatment_task")
public class TreatmentTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;

    @Enumerated(value = STRING)
    @Column(name = "treatment_action", nullable = false)
    private TreatmentAction treatmentAction;

    @Column(name = "subject_patient", nullable = false)
    private String patient;

    @Enumerated(value = STRING)
    @Column(name = "task_status", nullable = false)
    private TreatmentTaskStatus taskStatus;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startTime;

    //TODO: assume TreatmentPlan as @OneToMany may be here with N + 1 issue in mind

    public Long getId() {
        return id;
    }

    public TreatmentAction getTreatmentAction() {
        return treatmentAction;
    }

    public void setTreatmentAction(TreatmentAction treatmentAction) {
        this.treatmentAction = treatmentAction;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public TreatmentTaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TreatmentTaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TreatmentTask that)) return false;

        return (Objects.equals(treatmentAction, that.treatmentAction))
                && (Objects.equals(patient, that.patient))
                && (Objects.equals(taskStatus, that.taskStatus))
                && (Objects.equals(startTime, that.startTime));
    }

    @Override
    public String toString() {
        return "TreatmentTask{" +
                "id=" + id +
                ", treatmentAction=" + treatmentAction +
                ", patient='" + patient + '\'' +
                ", taskStatus=" + taskStatus +
                ", startTime=" + startTime +
                '}';
    }
}
