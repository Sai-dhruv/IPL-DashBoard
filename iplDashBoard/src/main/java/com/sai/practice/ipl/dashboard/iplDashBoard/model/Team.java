/**
 * 
 */
package com.sai.practice.ipl.dashboard.iplDashBoard.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * @author Saikrishna Vinjamuri
 *
 */
@Entity
public class Team {
	
	public Team(String teamName, Long totalMatches) {
		super();
		this.teamName = teamName;
		this.totalMatches = totalMatches;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long teamId;
	private String teamName;
	private Long totalMatches;
	private Long totalwins;
	@Transient
	private List<MatchData> matches;
	
	public List<MatchData> getMatches() {
		return matches;
	}
	public void setMatches(List<MatchData> matches) {
		this.matches = matches;
	}
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public Long getTotalMatches() {
		return totalMatches;
	}
	public void setTotalMatches(Long totalMatches) {
		this.totalMatches = totalMatches;
	}
	public Long getTotalwins() {
		return totalwins;
	}
	public void setTotalwins(Long totalwins) {
		this.totalwins = totalwins;
	}
	
	public Team() {
		//
	}

}
