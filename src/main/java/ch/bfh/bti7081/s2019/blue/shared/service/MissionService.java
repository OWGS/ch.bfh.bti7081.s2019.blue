package ch.bfh.bti7081.s2019.blue.shared.service;

import ch.bfh.bti7081.s2019.blue.shared.dto.MissionDto;

import java.util.Date;
import java.util.List;

public interface MissionService {

    List<MissionDto> find(Integer patientNumber, Date start, Date end);
}
