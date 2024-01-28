package com.IplDashboard.ipl.Controller;

import com.IplDashboard.ipl.Repository.MatchRepository;
import com.IplDashboard.ipl.Repository.TeamRepository;
import com.IplDashboard.ipl.model.Team;
import com.IplDashboard.ipl.model.Match;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TeamController {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private MatchRepository matchRepository;

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName){
        Team team=this.teamRepository.findByTeamName(teamName);
        if (team!=null)
        team.setMatches(matchRepository.findLatestMatchesbyTeam(teamName,4));
        return team;
    }
    @GetMapping("/team")
    public Iterable<Team> getTeam(){
    
        return this.teamRepository.findAll();
    }

     @GetMapping("/team/{teamName}/matches")
    public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year + 1, 1, 1);
        return this.matchRepository.getMatchesByTeamBetweenDates(
            teamName,
            startDate,
            endDate
            );
    }
}
