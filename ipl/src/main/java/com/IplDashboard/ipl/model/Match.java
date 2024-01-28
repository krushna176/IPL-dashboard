package com.IplDashboard.ipl.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;
@Entity
public class Match {
    @Id
    private long ID;
    private String City;
    private LocalDate date;
    private String PlayerOfMatch;
    private String team1;
    private String team2;
    private String Venue;
    private String TossWinner;
    private String TossDecision;
    private String WinningTeam;
    private String WonBy;
    private String Margin;
    private String Umpire1;
    private String Umpire2;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date1) {
        date = date1;
    }

    public String getPlayerOfMatch() {
        return PlayerOfMatch;
    }

    public void setPlayerOfMatch(String playerOfMatch) {
        PlayerOfMatch = playerOfMatch;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team) {
        team1 = team;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team) {
        team2 = team;
    }

    public String getVenue() {
        return Venue;
    }

    public void setVenue(String venue) {
        Venue = venue;
    }

    public String getTossWinner() {
        return TossWinner;
    }

    public void setTossWinner(String tossWinner) {
        TossWinner = tossWinner;
    }

    public String getTossDecision() {
        return TossDecision;
    }

    public void setTossDecision(String tossDecision) {
        TossDecision = tossDecision;
    }

    public String getWinningTeam() {
        return WinningTeam;
    }

    public void setWinningTeam(String winningTeam) {
        WinningTeam = winningTeam;
    }

    public String getWonBy() {
        return WonBy;
    }

    public void setWonBy(String wonBy) {
        WonBy = wonBy;
    }

    public String getMargin() {
        return Margin;
    }

    public void setMargin(String margin) {
        Margin = margin;
    }

    public String getUmpire1() {
        return Umpire1;
    }

    public void setUmpire1(String umpire1) {
        Umpire1 = umpire1;
    }

    public String getUmpire2() {
        return Umpire2;
    }

    public void setUmpire2(String umpire2) {
        Umpire2 = umpire2;
    }
}
