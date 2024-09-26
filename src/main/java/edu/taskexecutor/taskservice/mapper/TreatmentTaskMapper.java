package edu.taskexecutor.taskservice.mapper;

import edu.taskexecutor.taskservice.dto.TreatmentTaskDTO;
import edu.taskexecutor.taskservice.entity.TreatmentTask;

public class TreatmentTaskMapper {

    public static TreatmentTaskDTO mapToTreatmentTaskDTO(TreatmentTaskDTO taskDTO, TreatmentTask taskEntity) {
        taskDTO.setTaskStatus(taskEntity.getTaskStatus());
        taskDTO.setPatient(taskEntity.getPatient());
        taskDTO.setStartTime(taskEntity.getStartTime());
        return taskDTO;
    }

    //TODO: same here for backward mapping or use 3th party mapper
}
