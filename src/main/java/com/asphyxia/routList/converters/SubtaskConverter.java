package com.asphyxia.routList.converters;

import com.asphyxia.routList.dto.SubtaskDto;
import com.asphyxia.routList.entity.Status;
import com.asphyxia.routList.entity.Subtask;
import com.asphyxia.routList.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SubtaskConverter {

    public Subtask getEntity(SubtaskDto subtaskDto) {
        Subtask subtask = new Subtask();
        subtask.setId(subtaskDto.getId());
        subtask.setTime(subtaskDto.getTime());
        subtask.setCategory(subtaskDto.getCategory());
        subtask.setStatus(Status.finished);
        return subtask;
    }

    public SubtaskDto getDto(Subtask subtask) {
        SubtaskDto subtaskDto = new SubtaskDto();
        subtaskDto.setCategory(subtask.getCategory());
        subtaskDto.setId(subtask.getId());
        subtaskDto.setPlanId(subtask.getPlan().getId());
        subtaskDto.setTime(subtask.getTime());
        subtaskDto.setStatus(subtask.getStatus());
        return subtaskDto;
    }

    public List<SubtaskDto> getAllMessagesDTO(List<Subtask> messages) {
        return messages.stream().map(x -> getDto(x)).collect(Collectors.toList());
    }
}
