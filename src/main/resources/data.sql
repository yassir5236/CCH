-- Insertion des coureurs
INSERT INTO coureur (nom, prenom, date_naissance, nationalite, equipe) VALUES
                                                                           ('Dupont', 'Jean', '1990-05-15', 'Française', 'Cyclo Club A'),
                                                                           ('Martin', 'Sophie', '1988-07-22', 'Française', 'Cyclo Club B'),
                                                                           ('Lefebvre', 'Paul', '1992-03-10', 'Belge', 'Cyclo Club C'),
                                                                           ('Moreau', 'Lucie', '1995-11-30', 'Française', 'Cyclo Club A'),
                                                                           ('Garnier', 'Thomas', '1985-01-17', 'Suisse', 'Cyclo Club B');

-- Insertion des compétitions
INSERT INTO competition (nom, date, lieu, distance) VALUES
                                                        ('Championnat National', '2024-06-10', 'Paris', 40),
                                                        ('Tour de France', '2024-07-01', 'Nice', 120),
                                                        ('Grand Prix de la Ville', '2024-05-20', 'Lyon', 60);

-- Insertion des inscriptions
INSERT INTO inscription (coureur_id, competition_id) VALUES
                                                         (1, 1),  -- Jean Dupont s'inscrit au Championnat National
                                                         (2, 1),  -- Sophie Martin s'inscrit au Championnat National
                                                         (3, 2),  -- Paul Lefebvre s'inscrit au Tour de France
                                                         (4, 3),  -- Lucie Moreau s'inscrit au Grand Prix de la Ville
                                                         (5, 2);  -- Thomas Garnier s'inscrit au Tour de France

-- Insertion des résultats
INSERT INTO resultat (coureur_id, competition_id, temps) VALUES
                                                             (1, 1, '01:10:15'),  -- Jean Dupont temps pour le Championnat National
                                                             (2, 1, '01:12:30'),  -- Sophie Martin temps pour le Championnat National
                                                             (3, 2, '04:25:00'),  -- Paul Lefebvre temps pour le Tour de France
                                                             (4, 3, '01:05:45'),  -- Lucie Moreau temps pour le Grand Prix de la Ville
                                                             (5, 2, '04:30:00');  -- Thomas Garnier temps pour le Tour de France

-- Insertion des classements (à calculer après l'enregistrement des résultats)
-- Ici, vous pouvez les calculer selon la logique de votre application
