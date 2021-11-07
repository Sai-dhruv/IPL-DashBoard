/**
 * 
 */
package com.sai.practice.ipl.dashboard.iplDashBoard.repository;

import org.springframework.data.repository.CrudRepository;

import com.sai.practice.ipl.dashboard.iplDashBoard.model.Team;

/**
 * @author Saikrishna Vinjamuri
 *
 */
 public interface TeamRepository extends CrudRepository<Team,Long>   {
	 
	 Team findByTeamName(String teamName);

}
