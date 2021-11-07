/**
 * 
 */
package com.sai.practice.ipl.dashboard.iplDashBoard.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sai.practice.ipl.dashboard.iplDashBoard.model.MatchData;

/**
 * @author Saikrishna Vinjamuri
 *
 */
public interface MatchRepository extends CrudRepository<MatchData,Long>  {
	
	List<MatchData> findByTeam1OrTeam2OrderByDateDesc(String team1, String team2, Pageable pageble);
	
	@Query("select m from MatchData m where (m.team1 = :teamName or m.team2 = :teamName) and m.date between :startDate and :endDate  order by date desc" )
	List<MatchData> getMatchesByTeameNamesBetweenDates(
	@Param("teamName") String teamName,
	 @Param("startDate") LocalDate startDate,
	  @Param("endDate") LocalDate endDate);
	
	default List<MatchData> findByTeamNameLatest(String teamName , int count){
		return  findByTeam1OrTeam2OrderByDateDesc(teamName,teamName,PageRequest.of(0,count));
		
	}

}
