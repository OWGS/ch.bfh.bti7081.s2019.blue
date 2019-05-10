package ch.bfh.bti7081.s2019.blue.server.service;

import ch.bfh.bti7081.s2019.blue.server.mapper.Mapper;
import ch.bfh.bti7081.s2019.blue.server.persistence.MissionRepository;
import ch.bfh.bti7081.s2019.blue.server.persistence.MissionSeriesRepository;
import ch.bfh.bti7081.s2019.blue.server.persistence.model.Mission;
import ch.bfh.bti7081.s2019.blue.server.persistence.model.MissionSeries;
import ch.bfh.bti7081.s2019.blue.shared.dto.MissionDto;
import ch.bfh.bti7081.s2019.blue.shared.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;
    private final MissionSeriesRepository missionSeriesRepository;
    private final MissionGenerator generator;
    private final Mapper mapper;

    @Autowired
    public MissionServiceImpl(MissionRepository missionRepository, MissionSeriesRepository missionSeriesRepository, MissionGenerator generator, Mapper mapper) {
        this.missionRepository = missionRepository;
        this.missionSeriesRepository = missionSeriesRepository;
        this.generator = generator;
        this.mapper = mapper;
    }

    @Override
    public List<MissionDto> findMissions(Integer patientNumber, Date startDate, Date endDate) {
        List<MissionSeries> series = new ArrayList<>(missionSeriesRepository.findByPatientNumberAndIntersectingDateRange(patientNumber, startDate, endDate));
        List<Mission> missions = generator.generateMissionsFromSeries(series, new DateRange(startDate, endDate));
        return mapper.map(missions, MissionDto.class);
    }


    @Override
    public List<MissionDto> findMissionsForEmployee(Integer employeeId, Date start, Date end) {
        List<Mission> missions = missionRepository.findByHealthVisitorAndIntersectingDateRange(employeeId, start, end);
        return mapper.map(missions, MissionDto.class);
    }
}