/**
 * 
 */
package com.sai.practice.ipl.dashboard.iplDashBoard.data;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.sai.practice.ipl.dashboard.iplDashBoard.model.MatchData;


/**
 * @author Saikrishna Vinjamuri
 *
 */
public class MatchDataProcessor implements ItemProcessor<MatchInput, MatchData> {

	private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

	@Override
	public MatchData process(final MatchInput matchInput) throws Exception {

		MatchData matchData = new MatchData();
		matchData.setId(Long.parseLong(matchInput.getId()));
		matchData.setDate(LocalDate.parse(matchInput.getDate()));
		matchData.setPlayerOfMatch(matchInput.getPlayer_of_match());
		matchData.setVenue(matchInput.getVenue());


		String firstInningTeam , secondInnitingTeam;
		if("bat".equalsIgnoreCase(matchInput.getToss_decision())) {
			firstInningTeam = matchInput.getToss_winner();
			secondInnitingTeam = matchInput.getToss_decision().equals(matchInput.getTeam1())?matchInput.getTeam2():matchInput.getTeam1();
		}else {
			secondInnitingTeam = matchInput.getToss_winner();
			firstInningTeam = matchInput.getToss_decision().equals(matchInput.getTeam1())?matchInput.getTeam2():matchInput.getTeam1();
		}
		matchData.setTeam1(firstInningTeam);
		matchData.setTeam2(secondInnitingTeam);
		matchData.setTossDecision(matchInput.getToss_winner());
		matchData.setMatchWinner(matchInput.getWinner());
		matchData.setResult(matchInput.getResult());
		matchData.setResultMargin(matchInput.getResult_margin());
		matchData.setUmpire1(matchInput.getUmpire1());
		matchData.setUmpire2(matchInput.getUmpire2());
		
		
		
		
		


		return matchData;
	}

}


