import{React, useEffect, useState} from 'react';
import { useParams, Link } from 'react-router-dom';
import { MatchDetailCard } from '../components/MatchDetailsCard';
import { MatchSmallCard } from '../components/MatchSmallCard';
import { PieChart } from 'react-minimal-pie-chart';
import './TeamPage.scss'

export const TeamPage=()=>{

    const [team,setTeam]=useState({matches:[]});
    const{teamName}=useParams();
    useEffect(
        ()=>{
            const fetchMatches=async()=>{
                const response =await fetch(`http://localhost:8080/team/${teamName}`);
                const data=await response.json();
                setTeam(data);
            };
            fetchMatches();
        },[teamName]
        
    );
    if(!team || !team.teamName){
        return <h1>Team not Found</h1>
    }
    return(
        <div className='TeamPage'>
            
            <div className='teamNameSection'>
                <h1 className='teamName'>{team.teamName}</h1>
            </div>
           
            <div className='winloss'>
                Win/Loss
                <PieChart
                data={[
                    { title: 'Losses', value: team.totalMatches - team.totalWins, color: '#a34d5d' },
                    { title: 'Wins', value: team.totalWins, color: '#4da375' },
                ]}
                />
            </div>

            <div className='matchDetail'>
            <h3>LetsetMatches</h3>
                <MatchDetailCard teamName ={team.teamName} match={team.matches[0]}/>
            </div>

            {team.matches.slice(1).map(match=><MatchSmallCard key={match.id} teamName ={team.teamName} match={match}/>)}
            <div>
            <Link className='more-link' to={`/teams/${teamName}/matches/${process.env.REACT_APP_DATA_END_YEAR}`}>More</Link>
                
            </div>
        </div>
    )
}