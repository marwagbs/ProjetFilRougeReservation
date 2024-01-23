DROP TABLE IF EXISTS Messages,Produits_Cartes, Commandes_Produits ,Produits , Categories,Commandes , Statuts, TablesRes,Reservations , Avis, Utilisateurs, Horaires_restaurants, Horaires ,Restaurants  ,cartes   ;

CREATE TABLE Categories(

	id				INT				PRIMARY KEY IDENTITY,
	libelle			VARCHAR(20)			NOT NULL

);

CREATE TABLE Statuts(
	id				INT			PRIMARY KEY IDENTITY,
	libelle			VARCHAR(20)			NOT NULL

);


CREATE TABLE Utilisateurs(
	id			  INT	PRIMARY KEY IDENTITY,
	nom		VARCHAR(50) NOT NULL,
	prenom	VARCHAR(50) NOT NULL,
	email VARCHAR (50) NOT NULL,
	mot_de_passe VARCHAR (50) NOT NULL	,
	telephone	VARCHAR (50) NOT NULL,
	isAdmin bit 
);

CREATE TABLE Produits(
	id				INT				PRIMARY KEY IDENTITY,
	nom VARCHAR (50) NOT NULL,
	description  varchar(250) NOT NULL,
	prix NUMERIC (5,2) NOT NULL,
	id_categorie int NOT NULL,
	

);
CREATE TABLE Cartes(
	id				INT				PRIMARY KEY IDENTITY,
	libelle			VARCHAR(40)			NOT NULL
);

CREATE TABLE Produits_Cartes(
	id				INT				PRIMARY KEY IDENTITY,
	id_produit   INT,
	id_carte   INT
	
);

CREATE TABLE Restaurants(
	id			  INT	PRIMARY KEY IDENTITY,
	nom		VARCHAR(50) NOT NULL,
	adresse VARCHAR(60) NOT NULL,
    cpo CHAR(5) NOT NULL CHECK (cpo BETWEEN 01000 AND 95999),
    ville VARCHAR(40) NOT NULL DEFAULT 'NANTES',
	id_carte INT  NULL

);

CREATE TABLE Reservations(
	id			  INT	PRIMARY KEY IDENTITY,
	date_res DATE NOT NULL,
	heure time NOT NULL,
	nb_personne int NOT NULL CHECK (nb_personne>=1),
	id_utilisateur int NOT NULL,
	id_restaurant INT NOT NULL,
	statut VARCHAR (50) NULL CHECK (statut in ('acceptée', 'refusée', null)),
	commentaire VARCHAR (250) NULL

);


CREATE TABLE Avis(
	id			  INT	PRIMARY KEY IDENTITY,
	id_utilisateur int NOT NULL,
	contenu VARCHAR(250) NOT NULL,
);
CREATE TABLE Messages(
	id			  INT	PRIMARY KEY IDENTITY,
	id_utilisateur int NOT NULL,
	id_restaurant int NOT NULL,
	contenu VARCHAR(250) NOT NULL,
	sujet  VARCHAR(100) NOT NULL
);

CREATE TABLE TablesRes(
	id			  INT	PRIMARY KEY IDENTITY,
	nombre_places INT NOT NULL,
	numero_table   INT NOT NULL, 
	statut  VARCHAR (50) NULL DEFAULT 'absent' CHECK (statut in ('absent', 'present')),
	id_reservation INT NULL,
	id_restaurant INT NOT NULL

);

CREATE TABLE Commandes(
	id			  INT	PRIMARY KEY IDENTITY,
	id_statut     INT NOT NULL,
	id_table INT NOT NULL


);

CREATE TABLE Commandes_Produits(
	id				INT				PRIMARY KEY IDENTITY,
	id_commande INT NOT NULL,
	id_produit INT NOT NULL
	
);
CREATE TABLE Horaires(
		id			  INT	PRIMARY KEY IDENTITY,
		jour  VARCHAR(15) NOT NULL,
		heure_ouverture VARCHAR(15) NOT NULL,
		heure_fermeture VARCHAR(15) NOT NULL
);

CREATE TABLE Horaires_restaurants (
	id				INT				PRIMARY KEY IDENTITY,
	id_restaurant INT NOT NULL,
	id_horaire    INT NOT NULL

);

ALTER TABLE Produits 
	with CHECK ADD
		FOREIGN KEY (id_categorie) REFERENCES categories(id)
		ON DELETE NO ACTION;


ALTER TABLE Produits_Cartes 
	with CHECK ADD
		FOREIGN KEY (id_produit) REFERENCES produits(id),
		FOREIGN KEY (id_carte) REFERENCES cartes(id);
		

ALTER TABLE Commandes
	with CHECK ADD
		FOREIGN KEY (id_statut) REFERENCES statuts(id)
		ON DELETE NO ACTION,
		FOREIGN KEY (id_table) REFERENCES TablesRes(id)
		ON DELETE CASCADE;

ALTER TABLE Commandes_Produits 
	with CHECK ADD
		FOREIGN KEY (id_produit) REFERENCES Produits(id),
		FOREIGN KEY (id_commande) REFERENCES commandes(id);
		

ALTER TABLE TablesRes 
	with CHECK ADD
		FOREIGN KEY (id_reservation) REFERENCES reservations(id)
		ON DELETE NO ACTION,
		FOREIGN KEY (id_restaurant) REFERENCES restaurants(id)
		ON DELETE CASCADE;
	


ALTER TABLE Restaurants 
	with CHECK ADD
		FOREIGN KEY (id_carte) REFERENCES Cartes(id)
		ON DELETE NO ACTION;



ALTER TABLE Reservations 
	with CHECK ADD
		FOREIGN KEY (id_restaurant) REFERENCES restaurants(id)
		ON DELETE CASCADE,
		FOREIGN KEY (id_utilisateur) REFERENCES Utilisateurs(id)
		ON DELETE CASCADE;

ALTER TABLE Avis 
	with CHECK ADD
		FOREIGN KEY (id_utilisateur) REFERENCES Utilisateurs(id)
		ON DELETE NO ACTION;

		
ALTER TABLE Messages 
	with CHECK ADD
		FOREIGN KEY (id_utilisateur) REFERENCES Utilisateurs(id),
		FOREIGN KEY (id_restaurant) REFERENCES restaurants(id)
		ON DELETE NO ACTION;

ALTER TABLE Horaires_restaurants
	with CHECK ADD
		FOREIGN KEY (id_horaire) REFERENCES horaires(id),
		FOREIGN KEY (id_restaurant) REFERENCES restaurants(id);
		


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
('Coca-Cola', 'Boisson gazeuse', 2.50, 3),
('Tiramisu', 'Dessert italien au café', 7.99, 4),
('Soupe à l oignon', 'Soupe à l oignon avec fromage gratiné', 8.50, 1),
('Steak frites', 'Steak saignant avec frites croustillantes', 18.50, 2),
('Eau minérale', 'Eau plate de source', 1.99, 3),
('Salade de fruits', 'Assortiment de fruits frais', 9.99, 4),
('Pâtes Carbonara', 'Pâtes avec sauce à la crème, lardons et parmesan', 12.50, 2),
('Thé vert', 'Thé vert japonais', 3.99, 3);


-- Insertion des utilisateurs
INSERT INTO Utilisateurs (nom, prenom, email, mot_de_passe, telephone, isAdmin) VALUES
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

-- Insertion des restaurants
INSERT INTO Restaurants (nom, adresse, cpo, ville, id_carte) VALUES
('Le Petit Bistro', '12 Rue de la Gastronomie', '44000', 'NANTES', 1),
('La Brasserie du Coin', '24 Avenue Gourmet', '75001', 'PARIS', 2),
('Chez Marcel', '8 Rue de Délices', '69002', 'LYON', 3),
('Le Bon Appétit', '15 Quai Savoureux', '33000', 'BORDEAUX', 4);

-- Insertion des réservations
INSERT INTO Reservations (date_res, heure, nb_personne, id_utilisateur, id_restaurant, statut, commentaire) VALUES
('2024-02-01', '19:30:00', 4, 1, 1, 'acceptée', 'Réservation pour un anniversaire'),
('2024-02-02', '20:00:00', 2, 2, 2, 'refusée', NULL),
('2024-02-03', '18:45:00', 3, 3, 3, 'refusée', 'Réservation pour un groupe trop important'),
('2024-02-04', '19:00:00', 5, 4, 4, 'acceptée', NULL),
('2024-02-05', '21:00:00', 2, 5, 1, 'acceptée', NULL),
('2024-02-06', '19:15:00', 3, 6, 2, 'refusée', NULL),
('2024-02-07', '20:30:00', 4, 7, 3, 'acceptée', NULL),
('2024-02-08', '18:00:00', 2, 8, 4, 'refusée', NULL),
('2024-02-09', '19:45:00', 5, 9, 1, 'acceptée', NULL),
('2024-02-10', '20:15:00', 3, 10, 2, 'acceptée', NULL);

-- Insertion des avis
INSERT INTO Avis (id_utilisateur, contenu) VALUES
(1, 'Excellent service et plats délicieux !'),
(3, 'Je n ai pas aimé l attitude du personnel.'),
(5, 'Très bon rapport qualité-prix.'),
(7, 'Le restaurant était bruyant, mais la nourriture était bonne.'),
(9, 'Service lent mais nourriture délicieuse.');

-- Insertion des messages
INSERT INTO Messages (id_utilisateur, id_restaurant, contenu, sujet) VALUES
(2, 1, 'Bonjour, nous avons des allergies alimentaires spécifiques.', 'Allergies alimentaires'),
(4, 2, 'Pouvez-vous nous réserver une table pour deux ce soir ?', 'Réservation pour ce soir'),
(6, 3, 'Y a-t-il des plats végétariens disponibles ?', 'Options végétariennes'),
(8, 4, 'Pouvez-vous personnaliser un plat pour moi ?', 'Personnalisation du plat'),
(10, 1, 'Nous avons un grand groupe, avez-vous une salle privée ?', 'Groupe important');

-- Insertion des tables de réservation
INSERT INTO TablesRes (nombre_places, numero_table, statut, id_reservation, id_restaurant) VALUES
(4, 1, 'absent', 1, 1),
(2, 2, 'present', 2, 2),
(6, 3, 'absent', 3, 3),
(5, 4, 'absent', 4, 4),
(3, 5, 'present', 5, 1),
(4, 6, 'absent', 6, 2),
(2, 7, 'absent', 7, 3),
(6, 8, 'absent', 8, 4),
(3, 9, 'present', 9, 1),
(5, 10, 'absent', 10, 2);
-- Insertion des statuts
INSERT INTO Statuts (libelle) VALUES
('Prête'),
('Servie'),
('Réglée');
-- Insertion des commandes
INSERT INTO Commandes (id_statut, id_table) VALUES
(1, 1),
(2, 2),
(1, 3),
(3, 4),
(2, 5),
(3, 6),
(1, 7),
(2, 8),
(3, 9),
(1, 10);

-- Insertion des commandes produits
INSERT INTO Commandes_Produits (id_commande, id_produit) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10);

-- Insertion des horaires
INSERT INTO Horaires (jour, heure_ouverture, heure_fermeture) VALUES
('Lundi', '11:00:00', '22:00:00'),
('Mardi', '11:00:00', '22:00:00'),
('Mercredi', '11:00:00', '22:00:00'),
('Jeudi', '11:00:00', '22:00:00'),
('Vendredi', '11:00:00', '23:00:00'),
('Samedi', '12:00:00', '23:00:00'),
('Dimanche', '12:00:00', '22:00:00');

-- Insertion des horaires restaurants
INSERT INTO Horaires_restaurants (id_restaurant, id_horaire) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4);

