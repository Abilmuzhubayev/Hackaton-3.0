package com.asphyxia.routList.converters;

import com.asphyxia.routList.dto.TaskDto;
import com.asphyxia.routList.entity.LocoAcceptance;
import com.asphyxia.routList.entity.LocoSubmission;
import com.asphyxia.routList.entity.StationData;
import com.asphyxia.routList.entity.Subtask;
import org.springframework.stereotype.Component;

@Component
public class TaskConverter {
    public TaskDto getDto(LocoAcceptance locoAcceptance) {
        TaskDto taskDto = new TaskDto();
        taskDto.setTaskId(locoAcceptance.getId());
        taskDto.setTaskCategory("LocoAcceptance");
        taskDto.setName("Прием локомотива");
        taskDto.setStatus(locoAcceptance.getStatus());
        taskDto.setTime(locoAcceptance.getTime());
        return taskDto;
    }

    public TaskDto getDto(LocoSubmission locoSubmission) {
        TaskDto taskDto = new TaskDto();
        taskDto.setTaskId(locoSubmission.getId());
        taskDto.setTaskCategory("LocoSubmission");
        taskDto.setName("Сдача локомотива");
        taskDto.setStatus(locoSubmission.getStatus());
        taskDto.setTime(locoSubmission.getTime());
        return taskDto;
    }

    public TaskDto getDto(Subtask subtask) {
        TaskDto taskDto = new TaskDto();
        taskDto.setTaskId(subtask.getId());
        taskDto.setTaskCategory(subtask.getCategory());
        if (subtask.getCategory().equals("arrival")) {
            taskDto.setName("Явка на работу");
        } else {
            taskDto.setName("Завершение работы");
        }
        taskDto.setStatus(subtask.getStatus());
        taskDto.setTime(subtask.getTime());
        return taskDto;
    }

    public TaskDto getDto(StationData stationData) {
        TaskDto taskDto = new TaskDto();
        taskDto.setTaskId(stationData.getId());
        taskDto.setTaskCategory("StationData");
        taskDto.setName("Станция " + stationData.getStation().getName());

        if (stationData.getOrderNumber() == 1) {
            taskDto.setTime(stationData.getDepartureTime());
        } else {
            taskDto.setTime(stationData.getArrivalTime());
        }

        taskDto.setStatus(stationData.getStatus());
        return taskDto;
    }
}
