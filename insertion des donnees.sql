-- Insertion des catégories
INSERT INTO Categories (libelle) VALUES
('Entrée'),
('Plats'),
('Boisson'),
('Dessert');

-- Insertion des produits
INSERT INTO Produits (nom, description, prix, id_categorie) VALUES
('Salade César', 'Salade romaine, poulet grillé, parmesan, croutons', 10.99, 1),
('Poulet rôti', 'Poulet rôti avec légumes de saison', 15.99, 2),
('Coca-Cola', 'Boisson gazeuse', 2.50, 4),
('Tiramisu', 'Dessert italien au café', 7.99, 3),
('Soupe à l oignon', 'Soupe à l oignon avec fromage gratiné', 8.50, 1),
('Steak frites', 'Steak saignant avec frites croustillantes', 18.50, 2),
('Eau minérale', 'Eau plate de source', 1.99, 4),
('Salade de fruits', 'Assortiment de fruits frais', 9.99, 3),
('Pâtes Carbonara', 'Pâtes avec sauce à la crème, lardons et parmesan', 12.50, 2),
('Thé vert', 'Thé vert japonais', 3.99, 4);


-- Insertion des utilisateurs
INSERT INTO Utilisateurs (nom, prenom, email, mot_de_passe, telephone, is_admin) VALUES
('Dupont', 'Jean', 'jean.dupont@mail.com', 'motdepasse1', '1234567890', 0),
('Martin', 'Sophie', 'sophie.martin@mail.com', 'motdepasse2', '2345678901', 0),
('Lefevre', 'Pierre', 'pierre.lefevre@mail.com', 'motdepasse3', '3456789012', 1),
('Dubois', 'Marie', 'marie.dubois@mail.com', 'motdepasse4', '4567890123', 0),
('Bertrand', 'Thomas', 'thomas.bertrand@mail.com', 'motdepasse5', '5678901234', 0),
('Leroux', 'Laura', 'laura.leroux@mail.com', 'motdepasse6', '6789012345', 0),
('Girard', 'Alexandre', 'alexandre.girard@mail.com', 'motdepasse7', '7890123456', 0),
('Moreau', 'Céline', 'celine.moreau@mail.com', 'motdepasse8', '8901234567', 0),
('Fournier', 'Luc', 'luc.fournier@mail.com', 'motdepasse9', '9012345678', 0),
('Roux', 'Emilie', 'emilie.roux@mail.com', 'motdepasse10', '0123456789', 0);

-- Insertion des cartes
INSERT INTO Cartes (libelle) VALUES
('Carte Printemps'),
('Carte Été'),
('Carte Automne'),
('Carte Hiver'),
('Carte Spéciale'),
('Carte Végétarienne'),
('Carte Enfant'),
('Carte Classique'),
('Carte Exotique'),
('Carte Gourmande');

INSERT INTO Produits_Cartes(id_produit, id_carte)
SELECT P.id, C.id
FROM Produits P, Cartes C;
-- Insertion des restaurants
INSERT INTO Restaurants (nom, adresse, cpo, ville, id_carte) VALUES
('Le Petit Bistro', '12 Rue de la Gastronomie', '44000', 'NANTES', 1),
('La Brasserie du Coin', '24 Avenue Gourmet', '75001', 'PARIS', 2),
('Chez Marcel', '8 Rue de Délices', '69002', 'LYON', 3),
('Le Bon Appétit', '15 Quai Savoureux', '33000', 'BORDEAUX', 4);
-- Insertion des tables de réservation
INSERT INTO tableres (nombre_places, numero_table, statut, id_restaurant) VALUES
(4, 1, 'present',  1),
(2, 2, 'present', 2),
(6, 3, 'absent', 3),
(5, 4, 'absent', 4),
(3, 5, 'present', 1),
(4, 6, 'absent', 2),
(2, 7, 'absent', 3),
(6, 8, 'absent',  4),
(3, 9, 'present',  1),
(5, 10, 'absent',  2),
(4, 1, 'absent',  1),
(2, 2, 'present',  2),
(6, 3, 'absent',  3),
(5, 4, 'absent',  4),
(3, 5, 'present',  1),
(4, 6, 'absent', 2),
(2, 7, 'absent', 3),
(6, 8, 'absent', 4),
(3, 9, 'present',  1),
(5, 10, 'absent',  2);
-- Insertion des réservations
INSERT INTO Reservations (date_res, heure, nb_personne, id_utilisateur, id_restaurant, statut, commentaire, id_table) VALUES
('2024-02-01', '19:30:00', 4, 1, 1, 'acceptée', 'Réservation pour un anniversaire',2),
('2024-02-02', '20:00:00', 2, 2, 2, 'refusée', NULL,3),
('2024-02-03', '18:45:00', 3, 3, 3, 'refusée', 'Réservation pour un groupe trop important',4),
('2024-02-04', '19:00:00', 5, 4, 4, 'acceptée', NULL,1),
('2024-02-05', '21:00:00', 2, 5, 1, 'acceptée', NULL,6),
('2024-02-06', '19:15:00', 3, 6, 2, 'refusée', NULL,7),
('2024-02-07', '20:30:00', 4, 7, 3, 'acceptée', NULL,8),
('2024-02-08', '18:00:00', 2, 8, 4, 'refusée', NULL,9),
('2024-02-09', '19:45:00', 5, 9, 1, 'acceptée', NULL,10),
('2024-02-10', '20:15:00', 3, 10, 2, 'acceptée', NULL,8);


-- Insertion des messages
INSERT INTO Messages (id_utilisateur, id_restaurant, contenu, sujet) VALUES
(2, 1, 'Bonjour, nous avons des allergies alimentaires spécifiques.', 'Allergies alimentaires'),
(4, 2, 'Pouvez-vous nous réserver une table pour deux ce soir ?', 'Réservation pour ce soir'),
(6, 3, 'Y a-t-il des plats végétariens disponibles ?', 'Options végétariennes'),
(8, 4, 'Pouvez-vous personnaliser un plat pour moi ?', 'Personnalisation du plat'),
(10, 1, 'Nous avons un grand groupe, avez-vous une salle privée ?', 'Groupe important');



-- Insertion des commandes
--INSERT INTO Commandes (statut, id_table) VALUES
--('Prête', 1),
--('Servie', 2),
--('Réglée', 3),
--('Réglée', 4),
--('Servie', 5),
--('Réglée', 6),
--('Prête', 7),
--('Réglée', 8),
--('Servie', 9),
--('Prête', 10);

-- Insertion des commandes produits
--INSERT INTO Commandes_Produits (id_commande, id_produit) VALUES
--(1, 1),
--(2, 2),
--(3, 3),
--(4, 4),
--(5, 5),
--(6, 6),
--(7, 7),
--(8, 8),
--(9, 9),
--(10, 10);

-- Insertion des horaires
INSERT INTO Horaires (jour, heure_ouverture, heure_fermeture)
VALUES
    ('Lundi', '12:00:00', '14:00:00'), ('Lundi', '19:00:00', '22:00:00'),
    ('Mardi', '11:30:00', '14:00:00'), ('Mardi', '19:00:00', '22:00:00'),
    ('Mercredi', '12:00:00', '14:00:00'), ('Mercredi', '19:00:00', '22:00:00'),
    ('Jeudi', '12:00:00', '14:00:00'), ('Jeudi', '19:00:00', '22:30:00'),
    ('Vendredi', '11:00:00', '14:30:00'), ('Vendredi', '18:30:00', '23:00:00'),
	('Samedi', '10:30:00', '15:30:00'), ('Samedi', '19:00:00', '23:00:00');
-- Insérer les données dans Horaires_restaurants pour chaque restaurant et chaque horaire
INSERT INTO Horaires_restaurants (id_restaurant, id_horaire)
VALUES (1,1),
	   (1,2),
	   (1,3),
	   (1,4),
	   (1,5),
	   (1,6),
	   (1,7),
	   (1,8),
	   (1,10),
	   (2,1),
	   (2,2),
	   (2,5),
	   (2,6),
	   (2,7),
	   (2,8),
	   (2,9),
	   (2,10),
	   (3,1),
	   (3,2),
	   (3,3),
	   (3,4),
	   (3,7),
	   (3,8),
	   (3,9),
	   (3,10),
	   (3,11),
	   (3,12),
	   (4,1),
	   (4,2),
	   (4,3),
	   (4,4),
	   (4,5),
	   (4,6),
	   (4,7),
	   (4,10),
	   (4,11),
	   (4,8);
	   
	  





