package ch.bfh.bti7081.s2019.blue.client.ws;

import ch.bfh.bti7081.s2019.blue.client.rest.IsRestService;
import ch.bfh.bti7081.s2019.blue.client.rest.Path;
import ch.bfh.bti7081.s2019.blue.client.rest.RestPromise;
import ch.bfh.bti7081.s2019.blue.client.rest.ReturnType;
import ch.bfh.bti7081.s2019.blue.shared.dto.MissionDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Path("/missionrecommendations")
public interface EmployeeMissionRecommendationsSubService extends IsRestService {

    @GetMapping
    @ReturnType(RestConverter.KEY_MISSION_DTO_LIST)
    RestPromise<List<MissionDto>> find(@RequestParam("start") LocalDateTime start, @RequestParam("end") LocalDateTime end);
}
