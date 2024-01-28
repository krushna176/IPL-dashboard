package com.IplDashboard.ipl.data;

import com.IplDashboard.ipl.model.Match;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


@Configuration
public class BatchConfig {
    private final String[] FIELD_NAME = new String[]{
            "ID","City","Date","Season","MatchNumber","Team1","Team2","Venue","TossWinner","TossDecision",
            "SuperOver","WinningTeam","WonBy","Margin","method","Player_of_Match","Team1Players",
            "Team2Players","Umpire1","Umpire2"
    };




    @Bean
    public FlatFileItemReader<MatchInput> reader() {
        return new FlatFileItemReaderBuilder<MatchInput>()
                .name("MatchItemReader")
                .resource(new ClassPathResource("IPL_Matches_2008_2022.csv"))
                .delimited()
                .names(FIELD_NAME)
                .targetType(MatchInput.class)
                .build();
    }

    @Bean
    public MatchDataProcessor processor() {
        return new MatchDataProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Match> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Match>()
                .sql("INSERT INTO match (ID, City, Date, Player_Of_Match, team1, team2, Venue, Toss_Winner, Toss_Decision, Winning_Team, Won_By, Margin, Umpire1, Umpire2)" +
                        " VALUES (:ID, :City, :Date, :PlayerOfMatch, :team1, :team2, :Venue, :TossWinner, :TossDecision, :WinningTeam, :WonBy, :Margin, :Umpire1, :Umpire2)")
                .dataSource(dataSource)
                .beanMapped()
                .build();
    }


    @Bean
    public Job importUserJob(JobRepository jobRepository,JobCompletionNotificationListener listener, Step step1) {
        return  new JobBuilder("importUserJob",jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(step1)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager,JdbcBatchItemWriter<Match> writer) {
        return new StepBuilder("step1",jobRepository)
                .<MatchInput, Match>chunk(10,transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

}
