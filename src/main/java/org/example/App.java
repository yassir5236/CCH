////package org.example;
////
////import org.example.config.PersistenceConfig;
////import org.example.entity.Competition;
////import org.example.repository.CompetitionRepository;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.context.ApplicationContext;
////import org.springframework.context.annotation.AnnotationConfigApplicationContext;
////import org.springframework.context.support.ClassPathXmlApplicationContext;
////import org.springframework.stereotype.Component;
////import org.springframework.transaction.annotation.Transactional;
////
////import java.time.LocalDate;
////import java.util.UUID;
////import java.util.concurrent.ThreadLocalRandom;
////
////@Component
////public class App {
////
////    @Autowired
////    private CompetitionRepository competitionRepository;
////
////    @Transactional
////    public void insertFakeCompetitions() {
////        try {
////            for (int i = 0; i < 10; i++) {
////                Competition competition = new Competition();
////                competition.setId(UUID.randomUUID());
////                competition.setName("Competition " + (i + 1));
////                competition.setDate(LocalDate.now().plusDays(ThreadLocalRandom.current().nextInt(1, 30))); // Dates aléatoires dans le mois suivant
////                competition.setPlace("Place " + (i + 1));
////                competition.setDistance(ThreadLocalRandom.current().nextDouble(5.0, 100.0)); // Distances aléatoires entre 5 et 100 km
////
////                competitionRepository.save(competition);
////            }
////            System.out.println("Fake competitions inserted successfully!");
////        } catch (Exception e) {
////            System.err.println("Error inserting competitions: " + e.getMessage());
////        }
////    }
////
////    public static void main(String[] args) {
////        // Initialize Spring context using Java configuration
////        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PersistenceConfig.class);
////
////        // Retrieve the bean App
////        App app = context.getBean(App.class);
////
////        // Call the method to insert competitions
////        app.insertFakeCompetitions();
////
////        // Close the context
////        context.close();
////    }
////}
//
//
//
//
//
//package org.example;
//
////import org.example.config.PersistenceConfig;
//import org.example.entity.Competition;
//import org.example.entity.Team;
//import org.example.repository.CompetitionRepository;
//import org.example.services.Impl.TeamService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.util.UUID;
//import java.util.concurrent.ThreadLocalRandom;
//
//@Component
//public class App {
//
//    @Autowired
//    private CompetitionRepository competitionRepository;
//
//
//    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//
//        TeamService teamService = (TeamService) context.getBean("teamService");
//
//        Team newTeam = new Team();
//        newTeam.setName("Wora");
//        teamService.createTeam(newTeam);
//
//        teamService.getAllTeam().forEach(status -> System.out.println(status.getName()));
//    }
//}


package org.example;

import org.example.entity.Competition;
import org.example.entity.Cyclist;
import org.example.entity.Team;
import org.example.repository.CompetitionRepository;
import org.example.repository.CyclistRepository;
import org.example.repository.TeamRepository;
import org.example.services.CompetitionService;
import org.example.services.CyclistService;
import org.example.services.Impl.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class App {

    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private CyclistService cyclistService;


    // Method to insert fake competitions for testing
    @Transactional
    public void insertFakeCompetitions() {
        try {
            for (int i = 0; i < 5; i++) {
                Competition competition = new Competition();
                competition.setId(UUID.randomUUID());

//                competition.set
                competition.setDate(LocalDate.now().plusDays(ThreadLocalRandom.current().nextInt(1, 30)));
                competition.setName("bbbbbbb ");

                competition.setPlace("cccccccc ");
                competition.setDistance(0.14);

                competitionService.saveCompetition(competition);
            }
            System.out.println("Fake competitions inserted successfully!");
        } catch (Exception e) {
            System.err.println("Error inserting competitions: " + e.getMessage());
        }
    }

//     Method to display all competitions
    public void displayAllCompetitions() {
        List<Competition> competitions = competitionService.getAllCompetitions();
        competitions.forEach(competition ->
                System.out.println("Competition Name: " + competition.getName() + ", Place: " + competition.getPlace()));
    }


    public void updateCompetition() {
        // Fetch all competitions
        List<Competition> competitions = competitionService.getAllCompetitions();

        // Display the competitions to the user
        competitions.forEach(competition ->
                System.out.println("Competition ID: " + competition.getId() +
                        " | Name: " + competition.getName() +
                        " | Place: " + competition.getPlace())
        );

        // Ask the user for the competition ID they want to update
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter competition ID to update: ");
        String competitionIdStr = scanner.nextLine();

        // Convert the entered ID to UUID
        try {
            UUID competitionId = UUID.fromString(competitionIdStr);

            // Check if the competition exists
            Optional<Competition> competitionOpt = competitionService.getCompetition(competitionId);
            if (competitionOpt.isPresent()) {
                Competition competition = competitionOpt.get();

                // Prompt the user for new information
                System.out.println("Enter new name (current: " + competition.getName() + "): ");
                String newName = scanner.nextLine();
                if (!newName.trim().isEmpty()) {
                    competition.setName(newName);
                }

                System.out.println("Enter new place (current: " + competition.getPlace() + "): ");
                String newPlace = scanner.nextLine();
                if (!newPlace.trim().isEmpty()) {
                    competition.setPlace(newPlace);
                }

                System.out.println("Enter new distance (current: " + competition.getDistance() + "): ");
                String newDistanceStr = scanner.nextLine();
                if (!newDistanceStr.trim().isEmpty()) {
                    double newDistance = Double.parseDouble(newDistanceStr);
                    competition.setDistance(newDistance);
                }

                System.out.println("Enter new date (current: " + competition.getDate() + ") [format: yyyy-MM-dd]: ");
                String newDateStr = scanner.nextLine();
                if (!newDateStr.trim().isEmpty()) {
                    LocalDate newDate = LocalDate.parse(newDateStr);
                    competition.setDate(newDate);
                }

                // Save the updated competition
                competitionService.saveCompetition(competition);
                System.out.println("Competition updated successfully.");

            } else {
                System.out.println("Competition with ID " + competitionId + " not found.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid competition ID format. Please enter a valid UUID.");
        } catch (Exception e) {
            System.out.println("An error occurred while updating the competition: " + e.getMessage());
        }
    }


    public void DeleteCompetition() {
        // Fetch all competitions
        List<Competition> competitions = competitionService.getAllCompetitions();

        // Display the competitions to the user
        competitions.forEach(competition ->
                System.out.println("Competition ID: " + competition.getId() +
                        " | Name: " + competition.getName() +
                        " | Place: " + competition.getPlace())
        );

        // Ask the user for the competition ID they want to delete
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter competition ID to delete: ");
        String competitionIdStr = scanner.nextLine();

        // Convert the entered ID to UUID
        try {
            UUID competitionId = UUID.fromString(competitionIdStr);

            // Check if the competition exists
            Optional<Competition> competitionOpt = competitionService.getCompetition(competitionId);
            if (competitionOpt.isPresent()) {
                // Delete the competition
                competitionService.deleteCompetition(competitionId);
                System.out.println("Competition deleted successfully.");
            } else {
                System.out.println("Competition with ID " + competitionId + " not found.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid competition ID format. Please enter a valid UUID.");
        }
    }




    public void insertCyclist() {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for cyclist details
        System.out.println("Enter cyclist's first name: ");
        String firstName = scanner.nextLine();

        System.out.println("Enter cyclist's last name: ");
        String lastName = scanner.nextLine();

        System.out.println("Enter cyclist's date of birth (yyyy-MM-dd): ");
        String ageStr = scanner.nextLine();

        System.out.println("Enter cyclist's nationality: ");
        String nationality = scanner.nextLine();

        System.out.println("Enter team ID: ");
        String teamIdStr = scanner.nextLine(); // Assuming team ID is UUID

        try {
            // Parse age (date of birth)
            LocalDate age = LocalDate.parse(ageStr);

            // Convert team ID to UUID
            UUID teamId = UUID.fromString(teamIdStr);

            // Fetch the team by ID
            Optional<Team> teamOpt = teamService.getTeamById(teamId);
            if (teamOpt.isPresent()) {
                Team team = teamOpt.get();

                // Create a new Cyclist instance
                Cyclist cyclist = new Cyclist();
                cyclist.setFirstName(firstName);
                cyclist.setLastName(lastName);
                cyclist.setAge(age);
                cyclist.setNationality(nationality);
                cyclist.setTeam(team);

                // Save the cyclist
                cyclistService.saveCyclist(cyclist);
                System.out.println("Cyclist added successfully.");

            } else {
                System.out.println("Team with ID " + teamId + " not found.");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid team ID format. Please enter a valid UUID.");
        } catch (Exception e) {
            System.out.println("An error occurred while inserting the cyclist: " + e.getMessage());
        }
    }



    public void updateCyclist() {
        Scanner scanner = new Scanner(System.in);

        // Demander l'ID du cycliste à mettre à jour
        System.out.println("Enter cyclist ID to update: ");
        String cyclistIdStr = scanner.nextLine();

        try {
            // Convertir l'ID en UUID
            UUID cyclistId = UUID.fromString(cyclistIdStr);

            // Rechercher le cycliste par ID
            Optional<Cyclist> cyclistOpt = cyclistService.getCyclistById(cyclistId);

            // Vérifier si le cycliste existe
            if (cyclistOpt.isPresent()) {
                Cyclist cyclist = cyclistOpt.get();

                // Demander les nouvelles informations
                System.out.println("Enter new first name (current: " + cyclist.getFirstName() + "): ");
                String firstName = scanner.nextLine();
                System.out.println("Enter new last name (current: " + cyclist.getLastName() + "): ");
                String lastName = scanner.nextLine();
                System.out.println("Enter new age (YYYY-MM-DD format) (current: " + cyclist.getAge() + "): ");
                String ageStr = scanner.nextLine();
                System.out.println("Enter new nationality (current: " + cyclist.getNationality() + "): ");
                String nationality = scanner.nextLine();
                System.out.println("Enter new team ID (current team: " + cyclist.getTeam().getName() + "): ");
                String teamIdStr = scanner.nextLine();

                // Mettre à jour les informations si elles sont saisies (laisser inchangées sinon)
                if (!firstName.isBlank()) cyclist.setFirstName(firstName);
                if (!lastName.isBlank()) cyclist.setLastName(lastName);
                if (!ageStr.isBlank()) cyclist.setAge(LocalDate.parse(ageStr));
                if (!nationality.isBlank()) cyclist.setNationality(nationality);

                // Rechercher la nouvelle équipe
                if (!teamIdStr.isBlank()) {
                    UUID teamId = UUID.fromString(teamIdStr);
                    Optional<Team> teamOpt = teamService.getTeamById(teamId);

                    if (teamOpt.isPresent()) {
                        cyclist.setTeam(teamOpt.get());
                    } else {
                        System.out.println("Team with ID " + teamId + " not found.");
                    }
                }

                // Sauvegarder les modifications
                cyclistService.saveCyclist(cyclist);
                System.out.println("Cyclist updated successfully.");
            } else {
                System.out.println("Cyclist with ID " + cyclistId + " not found.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid ID format. Please enter a valid UUID.");
        } catch (Exception e) {
            System.out.println("An error occurred while updating the cyclist: " + e.getMessage());
        }
    }




    public void deleteCyclist() {
        Scanner scanner = new Scanner(System.in);

        // Demander l'ID du cycliste à supprimer
        System.out.println("Enter cyclist ID to delete: ");
        String cyclistIdStr = scanner.nextLine();

        try {
            // Convertir l'ID en UUID
            UUID cyclistId = UUID.fromString(cyclistIdStr);

            // Rechercher le cycliste par ID
            Optional<Cyclist> cyclistOpt = cyclistService.getCyclistById(cyclistId);

            // Vérifier si le cycliste existe
            if (cyclistOpt.isPresent()) {

                cyclistService.deleteCyclist(cyclistId);
                System.out.println("Cyclist with ID " + cyclistId + " deleted successfully.");
            } else {
                System.out.println("Cyclist with ID " + cyclistId + " not found.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid cyclist ID format. Please enter a valid UUID.");
        } catch (Exception e) {
            System.out.println("An error occurred while deleting the cyclist: " + e.getMessage());
        }
    }



    public void getAllCyclists() {
        // Récupérer tous les cyclistes
        List<Cyclist> cyclists = cyclistService.getCyclists();

        // Vérifier si la liste est vide
        if (cyclists.isEmpty()) {
            System.out.println("No cyclists found in the system.");
        } else {
            // Afficher chaque cycliste
            cyclists.forEach(cyclist -> {
                System.out.println("Cyclist ID: " + cyclist.getId());
                System.out.println("First Name: " + cyclist.getFirstName());
                System.out.println("Last Name: " + cyclist.getLastName());
                System.out.println("Age: " + cyclist.getAge());
                System.out.println("Nationality: " + cyclist.getNationality());
                System.out.println("Team: " + cyclist.getTeam().getName());
                System.out.println("---------------------------------------");
            });
        }
    }




    public static void main(String[] args) {
        // Load Spring application context from applicationContext.xml
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Retrieve App bean
        App app = context.getBean(App.class);

        // Insert fake competitions
//        app.insertFakeCompetitions();

        // Retrieve TeamService bean
        TeamService teamService = context.getBean(TeamService.class);

        // Create a new team and save it
        Team newTeam = new Team();
        newTeam.setName("ssdssd");
        teamService.createTeam(newTeam);

        // Fetch and display all teams
//        teamService.getAllTeam().forEach(team -> System.out.println("Team Name: " + team.getName()));

        // Display all competitions
//        app.displayAllCompetitions();

//        app.DeleteCompetition();
//        app.updateCompetition();
//        app.insertCyclist();

//        app.deleteCyclist();

//        app.updateCyclist();

//        app.getAllCyclists();






    }
}
