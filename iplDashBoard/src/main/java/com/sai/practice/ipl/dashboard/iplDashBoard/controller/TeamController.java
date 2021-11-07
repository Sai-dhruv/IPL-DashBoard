/**
 * 
 */
package com.sai.practice.ipl.dashboard.iplDashBoard.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import net.bytebuddy.asm.Advice.Local;

import com.sai.practice.ipl.dashboard.iplDashBoard.model.MatchData;
import com.sai.practice.ipl.dashboard.iplDashBoard.model.Team;
import com.sai.practice.ipl.dashboard.iplDashBoard.repository.MatchRepository;
import com.sai.practice.ipl.dashboard.iplDashBoard.repository.TeamRepository;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author Saikrishna Vinjamuri
 *
 */
@RestController
@CrossOrigin
public class TeamController {
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private MatchRepository matchRepository;
	
	@GetMapping("/team/{teamName}")
	public Team getTeamData(@PathVariable  String teamName) {
		Team team =  teamRepository.findByTeamName(teamName);
		List<MatchData> matches = matchRepository.findByTeamNameLatest(teamName,4);
		team.setMatches(matches);
		return team;
	}
	
	@GetMapping("/team/{teamName}/matches")
	public List<MatchData> getMatches(@PathVariable String teamName, @RequestParam int year) {
		LocalDate startDate = LocalDate.of(year, 1, 1);
		LocalDate endDate = LocalDate.of(year+1, 1, 1);
		System.out.println(startDate+"++++++++++++++++++Hellll+++++++++++++++++++>>>>>>>>>"+endDate);
		return matchRepository.getMatchesByTeameNamesBetweenDates(teamName,startDate,endDate); 
	}
	
	
	
	
	
	
	
}
