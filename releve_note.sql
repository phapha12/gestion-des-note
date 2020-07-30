-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mer. 14 fév. 2018 à 23:59
-- Version du serveur :  5.7.19
-- Version de PHP :  5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `releve_note`
--

-- --------------------------------------------------------

--
-- Structure de la table `annee`
--

DROP TABLE IF EXISTS `annee`;
CREATE TABLE IF NOT EXISTS `annee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numAnnee` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `annee`
--

INSERT INTO `annee` (`id`, `numAnnee`) VALUES
(1, 'L1'),
(2, 'L2');

-- --------------------------------------------------------

--
-- Structure de la table `ecue`
--

DROP TABLE IF EXISTS `ecue`;
CREATE TABLE IF NOT EXISTS `ecue` (
  `codeEcue` varchar(11) NOT NULL,
  `nom` varchar(65) DEFAULT NULL,
  `nbCredit` int(11) DEFAULT NULL,
  `UE` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`codeEcue`),
  KEY `UE` (`UE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `ecue`
--

INSERT INTO `ecue` (`codeEcue`, `nom`, `nbCredit`, `UE`) VALUES
('1CEL1104', 'Cours d\'Education aux valeurs', 2, 'CEL1104'),
('1MTH1201', 'Algèbre 2', 4, 'MTH1201'),
('1PBD1202', 'POO', 4, 'PBD1202'),
('1REA1103', 'Base des Réseaux', 2, 'REA1103'),
('2CEL1104', 'TCEO', 2, 'CEL1104'),
('2PBD1202', 'SGBD', 4, 'PBD1202'),
('2REA1103', 'ATO', 2, 'REA1103'),
('3CEL1104', 'Anglais', 2, 'CEL1104'),
('3PBD1202', 'Algorithme-Programmation Avancée', 4, 'PBD1202'),
('4PBD1202', 'MERISE', 4, 'PBD1202'),
('TAF ', 'MAINTENANT', 0, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

DROP TABLE IF EXISTS `etudiant`;
CREATE TABLE IF NOT EXISTS `etudiant` (
  `numeroMatricule` varchar(15) NOT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  `sexe` varchar(1) DEFAULT NULL,
  `dateNaissance` date DEFAULT NULL,
  `idGrade` int(11) DEFAULT NULL,
  `idInstitut` int(11) DEFAULT NULL,
  `IdAnnee` int(11) DEFAULT NULL,
  `idSemestre` int(11) DEFAULT NULL,
  PRIMARY KEY (`numeroMatricule`),
  KEY `idGrade` (`idGrade`),
  KEY `idInstitut` (`idInstitut`),
  KEY `idSemestre` (`idSemestre`),
  KEY `IdAnnee` (`IdAnnee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `etudiant`
--

INSERT INTO `etudiant` (`numeroMatricule`, `nom`, `prenom`, `sexe`, `dateNaissance`, `idGrade`, `idInstitut`, `IdAnnee`, `idSemestre`) VALUES
('10900MIIH', 'Test', NULL, NULL, '0200-01-20', NULL, NULL, NULL, NULL),
('1600M1997228', 'Samey', 'Olivia Joanita', 'F', '1997-12-03', 1, 1, 1, 2),
('1909M2000228', 'Samey', 'Carlos Lickem', 'M', '2000-01-04', 1, 1, 1, 1),
('1978F1999228', 'Bawara', 'Titiane Irma', 'F', '1999-01-02', 1, 1, 1, 1),
('1999F1999228', 'BALLY', 'YEMPAB', 'F', '1999-01-03', 1, 1, 2, 1),
('1MM', 'ABALO', 'Koffi Komi', 'M', '2018-02-01', 1, 1, 1, 1),
('2300', 'BALLY ', 'Geneviève', 'F', '1998-12-31', 1, 1, 1, 1),
('2309', 'ETOU', 'Deladem', 'M', '2018-02-16', 1, 1, 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `grade`
--

DROP TABLE IF EXISTS `grade`;
CREATE TABLE IF NOT EXISTS `grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `grade` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `grade`
--

INSERT INTO `grade` (`id`, `grade`) VALUES
(1, 'Licence'),
(2, 'Master');

-- --------------------------------------------------------

--
-- Structure de la table `institut`
--

DROP TABLE IF EXISTS `institut`;
CREATE TABLE IF NOT EXISTS `institut` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nomInstitut` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `institut`
--

INSERT INTO `institut` (`id`, `nomInstitut`) VALUES
(1, 'EITEC'),
(2, 'ISEG'),
(3, 'ISJ');

-- --------------------------------------------------------

--
-- Structure de la table `login`
--

DROP TABLE IF EXISTS `login`;
CREATE TABLE IF NOT EXISTS `login` (
  `login` varchar(20) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`login`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `login`
--

INSERT INTO `login` (`login`, `password`) VALUES
('carlos', 'carlos');

-- --------------------------------------------------------

--
-- Structure de la table `mark`
--

DROP TABLE IF EXISTS `mark`;
CREATE TABLE IF NOT EXISTS `mark` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `devoir` double DEFAULT NULL,
  `synthese` double DEFAULT NULL,
  `moyenne` double DEFAULT NULL,
  `validation` varchar(1) DEFAULT NULL,
  `mention` varchar(20) DEFAULT NULL,
  `poids` int(11) DEFAULT NULL,
  `matriculeEtudiant` varchar(35) DEFAULT NULL,
  `ecue` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `matriculeEtudiant` (`matriculeEtudiant`),
  KEY `ecue` (`ecue`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `mark`
--

INSERT INTO `mark` (`id`, `devoir`, `synthese`, `moyenne`, `validation`, `mention`, `poids`, `matriculeEtudiant`, `ecue`) VALUES
(5, 14, 17, 15.5, 'A', 'Bien', 50, '1909M2000228', '1REA1103'),
(6, 13, 16, 14.5, 'A', 'Bien', 50, '1909M2000228', '2REA1103'),
(7, 15, 18, 16.2, 'A', 'Très-Bien', 60, '1909M2000228', '1CEL1104'),
(10, 10, 9, 9.39, 'R', '', 40, '1909M2000228', '2CEL1104'),
(11, 18, 19, 18.6, 'A', 'Excellent', 40, '1909M2000228', '3CEL1104'),
(15, 18, 19, 18.6, 'A', 'Excellent', 40, '1978F1999228', '1REA1103'),
(16, 16, 18, 17, 'A', 'Très-Bien', 50, '1978F1999228', '2REA1103'),
(17, 13, 15.5, 14, 'A', 'Bien', 50, '2300', '1REA1103'),
(18, 14, 12, 13, 'A', 'Assez-bien', 50, '1999F1999228', '2REA1103'),
(19, 14, 12, 13, 'A', 'Assez-bien', 50, '2300', '2REA1103'),
(20, 12, 12, 12, 'A', 'Assez-bien', 50, '1600M1997228', '1MTH1201'),
(21, 14, 18, 16.4, 'A', 'Très-Bien', 40, '1MM', '1REA1103'),
(22, 12, 13, 12.5, 'A', 'Assez-bien', 50, '1MM', '2CEL1104');

-- --------------------------------------------------------

--
-- Structure de la table `note`
--

DROP TABLE IF EXISTS `note`;
CREATE TABLE IF NOT EXISTS `note` (
  `idNote` int(11) NOT NULL AUTO_INCREMENT,
  `note` decimal(10,0) NOT NULL,
  `matriculeEtudiant` varchar(30) DEFAULT NULL,
  `codeEcue` varchar(10) DEFAULT NULL,
  `idType` int(11) DEFAULT NULL,
  PRIMARY KEY (`idNote`),
  KEY `matriculeEtudiant` (`matriculeEtudiant`),
  KEY `codeEcue` (`codeEcue`),
  KEY `idType` (`idType`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `note`
--

INSERT INTO `note` (`idNote`, `note`, `matriculeEtudiant`, `codeEcue`, `idType`) VALUES
(10, '17', '1909M2000228', '1MTH1201', 1),
(11, '18', '1909M2000228', '1MTH1201', 2),
(14, '17', '1909M2000228', '1PBD1202', 1),
(15, '14', '1600M1997228', '1CEL1104', 1),
(16, '14', '1600M1997228', '1CEL1104', 2),
(17, '16', '1600M1997228', '1CEL1104', 1),
(18, '14', '1909M2000228', '1CEL1104', 1),
(19, '14', '1909M2000228', '1CEL1104', 2),
(20, '15', '1909M2000228', '1CEL1104', 1),
(21, '17', '1909M2000228', '1REA1103', 1),
(22, '18', '1909M2000228', '1REA1103', 2),
(23, '13', '1909M2000228', '1REA1103', 1),
(24, '14', '1909M2000228', '2REA1103', 1),
(25, '16', '2300', '1REA1103', 2),
(26, '14', '2300', '1REA1103', 1),
(27, '15', '1909M2000228', '1REA1103', 1),
(31, '12', '1909M2000228', '1CEL1104', 1);

-- --------------------------------------------------------

--
-- Structure de la table `semestre`
--

DROP TABLE IF EXISTS `semestre`;
CREATE TABLE IF NOT EXISTS `semestre` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numSemestre` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `semestre`
--

INSERT INTO `semestre` (`id`, `numSemestre`) VALUES
(1, 1),
(2, 2);

-- --------------------------------------------------------

--
-- Structure de la table `typenote`
--

DROP TABLE IF EXISTS `typenote`;
CREATE TABLE IF NOT EXISTS `typenote` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `typenote`
--

INSERT INTO `typenote` (`id`, `type`) VALUES
(1, 'Devoir'),
(2, 'Synthese');

-- --------------------------------------------------------

--
-- Structure de la table `ue`
--

DROP TABLE IF EXISTS `ue`;
CREATE TABLE IF NOT EXISTS `ue` (
  `codeUE` varchar(8) NOT NULL,
  `nomUE` varchar(65) DEFAULT NULL,
  `idInstitut` int(11) DEFAULT NULL,
  `idAnnee` int(11) DEFAULT NULL,
  `idSemestre` int(11) DEFAULT NULL,
  PRIMARY KEY (`codeUE`),
  KEY `idInstitut` (`idInstitut`),
  KEY `idAnnee` (`idAnnee`),
  KEY `idSemestre` (`idSemestre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `ue`
--

INSERT INTO `ue` (`codeUE`, `nomUE`, `idInstitut`, `idAnnee`, `idSemestre`) VALUES
('CEL1104', 'COMMUNICATION ETHIQUE ET LANGAGE', 1, 1, 1),
('MTH1101', 'MATHEMATIQUES', 1, 1, 1),
('MTH1201', 'MATHEMATIQUES', 1, 1, 2),
('PBD1102', 'PROGRAMMATION-BASE DE DONNEES ET SYSTEME D\'EXPLOITATION', 1, 1, 1),
('PBD1202', 'PROGRAMMATION ET BASE DE DONNEES', 1, 1, 2),
('REA1103', 'RESEAUX ET ARCHITECTURE', 1, 1, 1);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `ecue`
--
ALTER TABLE `ecue`
  ADD CONSTRAINT `ecue_ibfk_1` FOREIGN KEY (`UE`) REFERENCES `ue` (`codeUE`);

--
-- Contraintes pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD CONSTRAINT `etudiant_ibfk_1` FOREIGN KEY (`idGrade`) REFERENCES `grade` (`id`),
  ADD CONSTRAINT `etudiant_ibfk_2` FOREIGN KEY (`idInstitut`) REFERENCES `institut` (`id`),
  ADD CONSTRAINT `etudiant_ibfk_3` FOREIGN KEY (`idSemestre`) REFERENCES `semestre` (`id`),
  ADD CONSTRAINT `etudiant_ibfk_4` FOREIGN KEY (`IdAnnee`) REFERENCES `annee` (`id`);

--
-- Contraintes pour la table `mark`
--
ALTER TABLE `mark`
  ADD CONSTRAINT `mark_ibfk_1` FOREIGN KEY (`matriculeEtudiant`) REFERENCES `etudiant` (`numeroMatricule`),
  ADD CONSTRAINT `mark_ibfk_2` FOREIGN KEY (`ecue`) REFERENCES `ecue` (`codeEcue`);

--
-- Contraintes pour la table `note`
--
ALTER TABLE `note`
  ADD CONSTRAINT `note_ibfk_1` FOREIGN KEY (`idType`) REFERENCES `typenote` (`id`),
  ADD CONSTRAINT `note_ibfk_2` FOREIGN KEY (`matriculeEtudiant`) REFERENCES `etudiant` (`numeroMatricule`),
  ADD CONSTRAINT `note_ibfk_3` FOREIGN KEY (`codeEcue`) REFERENCES `ecue` (`codeEcue`);

--
-- Contraintes pour la table `ue`
--
ALTER TABLE `ue`
  ADD CONSTRAINT `ue_ibfk_1` FOREIGN KEY (`idInstitut`) REFERENCES `institut` (`id`),
  ADD CONSTRAINT `ue_ibfk_2` FOREIGN KEY (`idAnnee`) REFERENCES `annee` (`id`),
  ADD CONSTRAINT `ue_ibfk_3` FOREIGN KEY (`idSemestre`) REFERENCES `semestre` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
