package org.example.repository.dao;


import org.example.entity.Competition;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public class CompetitionDaoImpl {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void insertCompetition() {
        // Get the current session
        Session session = sessionFactory.getCurrentSession();

        // Create a new competition
        Competition competition = new Competition();
        competition.setId(UUID.randomUUID());  // ID is automatically generated
        competition.setName("Marathon 2024");
        competition.setDate(LocalDate.of(2024, 12, 10));
        competition.setPlace("Paris");
        competition.setDistance(42.195); // Marathon distance in kilometers

        // Insert the competition into the database
        session.save(competition);
    }
}
