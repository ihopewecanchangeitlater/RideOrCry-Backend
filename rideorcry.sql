-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Εξυπηρετητής: 127.0.0.1
-- Χρόνος δημιουργίας: 14 Ιαν 2025 στις 21:08:10
-- Έκδοση διακομιστή: 10.4.32-MariaDB
-- Έκδοση PHP: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Βάση δεδομένων: `rideorcry`
--

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `citizen`
--

CREATE TABLE `citizen` (
  `afm` int(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Άδειασμα δεδομένων του πίνακα `citizen`
--

INSERT INTO `citizen` (`afm`, `name`, `surname`, `email`, `password`) VALUES
(5555, 'KATERINA', 'KROTKA', 'katerinakrotka01@gmail.com', '123456789'),
(123456789, 'John', 'Doe', 'kana@gmail.com', '1q2w3e4r5t'),
(741258963, 'Maria', 'Athanasiadou', 'mathanasiadou@gmail.com', '123456789qwert'),
(987654321, 'Anatoli', 'Krotka', 'anatoli@gmail.com', '1qaz2wsx3');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `dealer`
--

CREATE TABLE `dealer` (
  `afm` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `owner` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Άδειασμα δεδομένων του πίνακα `dealer`
--

INSERT INTO `dealer` (`afm`, `name`, `owner`, `email`, `password`) VALUES
(123456789, 'katrina', 'kk', 'katerina@gmail.com', '1q2w3e4r5t'),
(369852147, 'Stamatis', 'BMW', 'stamatis@gmail.com', 'deka10'),
(456987123, 'Depi', 'Opel', 'depi@gmail.com', 'depi2001');

--
-- Ευρετήρια για άχρηστους πίνακες
--

--
-- Ευρετήρια για πίνακα `citizen`
--
ALTER TABLE `citizen`
  ADD PRIMARY KEY (`afm`);

--
-- Ευρετήρια για πίνακα `dealer`
--
ALTER TABLE `dealer`
  ADD PRIMARY KEY (`afm`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
