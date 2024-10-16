package org.example;




import org.example.repository.dao.CompetitionDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        // Load Spring configuration from XML
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Retrieve the CompetitionDAO bean
        CompetitionDaoImpl competitionDaoImpl = context.getBean(CompetitionDaoImpl.class);

        // Insert a new competition
        competitionDaoImpl.insertCompetition();

        System.out.println("Competition inserted successfully!");
    }
}
