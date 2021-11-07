package com.sai.practice.ipl.dashboard.iplDashBoard.data;


import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sai.practice.ipl.dashboard.iplDashBoard.model.Team;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	private final EntityManager entityManager;

	@Autowired
	public JobCompletionNotificationListener(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("!!! JOB FINISHED! Time to verify the results");
			// entityManager.query("select team1 from MATCH_DATA",
			//	  (rs,row) -> "Team 1 "+ rs.getString(1)).forEach(str->System.out.print(str));




			Map<String,Team> teamData = new HashMap();

			String  TEAM_1_QUERY = "select m.team1, count(*) from MatchData m group by m.team1";
			String TEAM_2_QUERY = "select m.team2, count(*) from MatchData m group by m.team2";
			String MATCH_WINNER_QUERY = "select m.matchWinner, count(*) from MatchData m group by m.matchWinner"; 

			entityManager.createQuery(TEAM_1_QUERY, Object[].class)
			.getResultList()
			.stream()
			.map(e -> new Team((String) e[0] , (long) e[1]))
			.forEach(team -> teamData.put(team.getTeamName(),team));

			entityManager.createQuery(TEAM_2_QUERY, Object[].class)
			.getResultList()
			.stream()
			.forEach(e -> {

				Team team = teamData.get((String) e[0]);
				team.setTotalMatches(team.getTotalMatches() + (long) e[1]);
			});

			entityManager.createQuery(MATCH_WINNER_QUERY, Object[].class)
			.getResultList()
			.stream()
			.forEach(e -> {
				Team team = teamData.get((String) e[0]);
				if(team != null)team.setTotalwins((long) e[1]);
			});

			teamData.values().forEach(team -> entityManager.persist(team));
			//teamData.values().forEach(team -> System.out.println(team.getTeamName()+":"+team.getTotalMatches()+":"+team.getTotalwins()));






		}
	}
}