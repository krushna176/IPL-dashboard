package com.IplDashboard.ipl.Repository;

import com.IplDashboard.ipl.model.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Long> {
    Team findByTeamName(String teamName);
}
