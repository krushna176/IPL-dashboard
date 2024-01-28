import{React} from 'react';
import { Link } from 'react-router-dom';
import './MatchSmallCard.scss';

export const MatchSmallCard=({teamName,match})=>{
    const otherTeam=match.team1===teamName? match.team2 : match.team1
    const teamRout=`/teams/${otherTeam}`;
    const isMatchWon = teamName === match.winningTeam;
    return(
        <div className={isMatchWon ? 'MatchSmallCard won-card' : 'MatchSmallCard lost-card'}>
            <span className="vs">vs</span>
            <h1><Link to={teamRout}> {otherTeam}</Link></h1>
            <p className='matchResult'>{match.winningTeam} Won By {match.margin} {match.wonBy} </p>
        </div>
    )
}