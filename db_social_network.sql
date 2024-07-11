-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3310
-- Creato il: Lug 11, 2024 alle 21:14
-- Versione del server: 10.4.32-MariaDB
-- Versione PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_social_network`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `commenti`
--

CREATE TABLE `commenti` (
  `id` int(11) NOT NULL,
  `data_ora` datetime NOT NULL,
  `contenuto` text NOT NULL,
  `id_post` int(11) DEFAULT NULL,
  `id_utente` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `like_apposti`
--

CREATE TABLE `like_apposti` (
  `id` int(11) NOT NULL,
  `id_post` int(11) DEFAULT NULL,
  `id_utente` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `post_pubblicati`
--

CREATE TABLE `post_pubblicati` (
  `id` int(11) NOT NULL,
  `data_ora` datetime NOT NULL,
  `contenuto` text DEFAULT NULL,
  `immagine` longtext DEFAULT NULL,
  `id_utente` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `relazioni_amicizia`
--

CREATE TABLE `relazioni_amicizia` (
  `id` int(11) NOT NULL,
  `stato` tinyint(1) NOT NULL,
  `id_richiedente` int(11) DEFAULT NULL,
  `id_ricevente` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `utenti`
--

CREATE TABLE `utenti` (
  `id` int(11) NOT NULL,
  `data_iscrizione` date NOT NULL,
  `nome` varchar(50) NOT NULL,
  `cognome` varchar(50) NOT NULL,
  `nickname` varchar(20) NOT NULL,
  `immagine_profilo` longtext DEFAULT NULL,
  `password` text NOT NULL,
  `token` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `utenti`
--

INSERT INTO `utenti` (`id`, `data_iscrizione`, `nome`, `cognome`, `nickname`, `immagine_profilo`, `password`, `token`) VALUES
(6, '2024-07-11', 'Laura', 'Gialli', 'lauragialli', NULL, '$2a$10$fAWKb7XJB2elzo25vr1M6O7F0EDGU67aceX3U.oHSrIC231Mh43wy', NULL),
(7, '2024-07-11', 'Mario', 'Rossi', 'mario.rossi', NULL, '$2a$10$D5rg8ovEI58p4/W0noyHQ.lsi.SazhwMSwAaIbVbtuP52D9LF2eEG', NULL),
(9, '2024-07-11', 'Gianni', 'Verdi', 'gianni.verdi', NULL, '$2a$10$BOPcoQrhK9PYGZRfx.7fa.GwicLgIJU2gE1gL3YevFtBXtir68p0G', NULL);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `commenti`
--
ALTER TABLE `commenti`
  ADD PRIMARY KEY (`id`),
  ADD KEY `comment_post` (`id_post`),
  ADD KEY `comment_user` (`id_utente`);

--
-- Indici per le tabelle `like_apposti`
--
ALTER TABLE `like_apposti`
  ADD PRIMARY KEY (`id`),
  ADD KEY `like_post` (`id_post`),
  ADD KEY `like_user` (`id_utente`);

--
-- Indici per le tabelle `post_pubblicati`
--
ALTER TABLE `post_pubblicati`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_post` (`id_utente`);

--
-- Indici per le tabelle `relazioni_amicizia`
--
ALTER TABLE `relazioni_amicizia`
  ADD PRIMARY KEY (`id`),
  ADD KEY `applicant` (`id_richiedente`),
  ADD KEY `requested` (`id_ricevente`);

--
-- Indici per le tabelle `utenti`
--
ALTER TABLE `utenti`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `commenti`
--
ALTER TABLE `commenti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `like_apposti`
--
ALTER TABLE `like_apposti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `post_pubblicati`
--
ALTER TABLE `post_pubblicati`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `relazioni_amicizia`
--
ALTER TABLE `relazioni_amicizia`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `utenti`
--
ALTER TABLE `utenti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `commenti`
--
ALTER TABLE `commenti`
  ADD CONSTRAINT `post_commento` FOREIGN KEY (`id_post`) REFERENCES `post_pubblicati` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `utente_commento` FOREIGN KEY (`id_utente`) REFERENCES `utenti` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `like_apposti`
--
ALTER TABLE `like_apposti`
  ADD CONSTRAINT `post_like` FOREIGN KEY (`id_post`) REFERENCES `post_pubblicati` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `utente_like` FOREIGN KEY (`id_utente`) REFERENCES `utenti` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `post_pubblicati`
--
ALTER TABLE `post_pubblicati`
  ADD CONSTRAINT `utente_post` FOREIGN KEY (`id_utente`) REFERENCES `utenti` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `relazioni_amicizia`
--
ALTER TABLE `relazioni_amicizia`
  ADD CONSTRAINT `utente_ricevente` FOREIGN KEY (`id_ricevente`) REFERENCES `utenti` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `utente_richiedente` FOREIGN KEY (`id_richiedente`) REFERENCES `utenti` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
