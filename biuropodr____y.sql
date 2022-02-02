-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 02 Lut 2022, 13:46
-- Wersja serwera: 10.4.17-MariaDB
-- Wersja PHP: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `biuropodróży`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `faktury`
--

CREATE TABLE `faktury` (
  `id_faktury` int(10) NOT NULL,
  `imie` varchar(100) DEFAULT NULL,
  `nazwisko` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `id_platnosc` int(20) DEFAULT NULL,
  `data` date DEFAULT NULL,
  `kwota` float NOT NULL,
  `id_wycieczki` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `faktury`
--

INSERT INTO `faktury` (`id_faktury`, `imie`, `nazwisko`, `email`, `id_platnosc`, `data`, `kwota`, `id_wycieczki`) VALUES
(1, 'Jarosław', 'Kot', 'jarek@wp.pl', 3, '2022-02-01', 3006.72, 5),
(2, 'Jarosław', 'Kot', 'jarek@wp.pl', 1, '2022-02-02', 3891.08, 5);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `klient`
--

CREATE TABLE `klient` (
  `id_klienta` int(10) NOT NULL,
  `imie` varchar(100) DEFAULT NULL,
  `nazwisko` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `login` varchar(100) DEFAULT NULL,
  `haslo` varchar(100) DEFAULT NULL,
  `dataZalozenia` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `klient`
--

INSERT INTO `klient` (`id_klienta`, `imie`, `nazwisko`, `email`, `login`, `haslo`, `dataZalozenia`) VALUES
(1, 'Jarosław', 'Kot', 'jarek@wp.pl', 'jarek', 'jarek', '2022-02-01');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `ogloszenia`
--

CREATE TABLE `ogloszenia` (
  `id_ogloszenia` int(10) NOT NULL,
  `data` date NOT NULL,
  `tresc` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `ogloszenia`
--

INSERT INTO `ogloszenia` (`id_ogloszenia`, `data`, `tresc`) VALUES
(1, '1999-04-05', 'ok'),
(2, '2022-02-02', 'Zebranie pracowników.'),
(3, '2022-02-27', 'Podsumowanie miesiąca.'),
(4, '2022-03-29', 'Dzień Ziemniaka - dzień wolny od pracy.'),
(5, '2023-01-09', 'Aktualizacja ofert wycieczek.');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `opinie`
--

CREATE TABLE `opinie` (
  `id_opinia` int(10) NOT NULL,
  `tresc` varchar(200) DEFAULT NULL,
  `data` date DEFAULT NULL,
  `id_klienta` int(10) DEFAULT NULL,
  `id_wycieczki` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `opinie`
--

INSERT INTO `opinie` (`id_opinia`, `tresc`, `data`, `id_klienta`, `id_wycieczki`) VALUES
(1, 'Fajna wycieczka !', '2022-02-01', 1, 5);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `platnosc`
--

CREATE TABLE `platnosc` (
  `id_platnosc` int(10) NOT NULL,
  `rodzaj` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `platnosc`
--

INSERT INTO `platnosc` (`id_platnosc`, `rodzaj`) VALUES
(1, 'Blik'),
(2, 'Karta kredytowa'),
(3, 'Przelew'),
(4, 'PayPal');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `pracownik`
--

CREATE TABLE `pracownik` (
  `id_pracownika` int(10) NOT NULL,
  `imie` varchar(100) DEFAULT NULL,
  `nazwisko` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `login` varchar(100) DEFAULT NULL,
  `haslo` varchar(100) DEFAULT NULL,
  `rola` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `pracownik`
--

INSERT INTO `pracownik` (`id_pracownika`, `imie`, `nazwisko`, `email`, `login`, `haslo`, `rola`) VALUES
(1, 'admin', 'admin', 'admin', 'admin', 'admin', 'admin'),
(2, 'Aleksandra', 'Kwiatkowska', 'akwiatkowska@skyroutetravel.pl', 'ola', 'ola', 'Sprzedawca');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `rezerwacje`
--

CREATE TABLE `rezerwacje` (
  `id_rezerwacji` int(10) NOT NULL,
  `id_klienta` int(10) DEFAULT NULL,
  `id_wycieczki` int(10) DEFAULT NULL,
  `termin` date DEFAULT NULL,
  `ileDoroslych` int(10) DEFAULT NULL,
  `ileDzieci` int(10) DEFAULT NULL,
  `cena` float DEFAULT NULL,
  `id_platnosc` int(10) DEFAULT NULL,
  `statusPotwierdzenia` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `rezerwacje`
--

INSERT INTO `rezerwacje` (`id_rezerwacji`, `id_klienta`, `id_wycieczki`, `termin`, `ileDoroslych`, `ileDzieci`, `cena`, `id_platnosc`, `statusPotwierdzenia`) VALUES
(1, 1, 5, '2022-01-25', 1, 2, 3006.72, 3, 'Zaakceptowana'),
(2, 1, 2, '2022-12-20', 2, 2, 5436.12, 3, 'Odrzucona'),
(3, 1, 5, '2022-06-14', 2, 2, 3891.08, 1, 'Zaakceptowana');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `transport`
--

CREATE TABLE `transport` (
  `id_transport` int(10) NOT NULL,
  `cena` float DEFAULT NULL,
  `rodzaj` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `transport`
--

INSERT INTO `transport` (`id_transport`, `cena`, `rodzaj`) VALUES
(1, 500, 'Samolot'),
(2, 250, 'Prom'),
(3, 150, 'Pociąg'),
(4, 200, 'Autokar');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `wiadomosci`
--

CREATE TABLE `wiadomosci` (
  `id_wiadomosci` int(10) NOT NULL,
  `temat` varchar(100) DEFAULT NULL,
  `adresat` varchar(100) DEFAULT NULL,
  `tresc` varchar(300) DEFAULT NULL,
  `data` date NOT NULL,
  `id_pracownika` int(10) DEFAULT NULL,
  `id_klienta` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `wiadomosci`
--

INSERT INTO `wiadomosci` (`id_wiadomosci`, `temat`, `adresat`, `tresc`, `data`, `id_pracownika`, `id_klienta`) VALUES
(1, 'Anulowanie rezerwacji', 'jarek@wp.pl', 'Czy da sie anulowac reezrwacje ?', '2022-02-01', 2, 1),
(2, 'Akceptacja rezerwacji.', 'akwiatkowska@skyroutetravel.pl', 'Dzień dobry!\nTwoja rezerwacja została zaakceptowana.\nDziękujemy za wybranie Sky Route Travel i życzymy udanej wycieczki!\n\n	Polecana oferta:\nFrancja - Paryż - 567.0', '2022-02-01', 2, 1),
(3, 'RE: Anulowanie rezerwacji', 'jarek@wp.pl', 'tak', '2022-02-01', 2, 1),
(4, 'Akceptacja rezerwacji.', 'akwiatkowska@skyroutetravel.pl', 'Dzień dobry!\nTwoja rezerwacja została zaakceptowana.\nDziękujemy za wybranie Sky Route Travel i życzymy udanej wycieczki!\n\n	Polecana oferta:\nMalediwy - Malé - 1091.0', '2022-02-02', 2, 1),
(5, 'Akceptacja rezerwacji.', 'akwiatkowska@skyroutetravel.pl', 'Dzień dobry!\nTwoja rezerwacja została zaakceptowana.\nDziękujemy za wybranie Sky Route Travel i życzymy udanej wycieczki!\n\n	Polecana oferta:\nWłochy - Rzym - 542.98', '2022-02-02', 2, 1),
(6, 'Akceptacja rezerwacji.', 'akwiatkowska@skyroutetravel.pl', 'Dzień dobry!\nTwoja rezerwacja nie została zaakceptowana.\nDziękujemy za wybranie Sky Route Travel!\n\n	Polecana oferta:\nWłochy - Rzym - 542.98', '2022-02-02', 2, 1),
(7, 'Akceptacja rezerwacji.', 'akwiatkowska@skyroutetravel.pl', 'Dzień dobry!\nTwoja rezerwacja nie została zaakceptowana.\nDziękujemy za wybranie Sky Route Travel!\n\n	Polecana oferta:\nWłochy - Rzym - 542.98', '2022-02-02', 2, 1),
(8, 'Akceptacja rezerwacji.', 'akwiatkowska@skyroutetravel.pl', 'Dzień dobry!\nTwoja rezerwacja została zaakceptowana.\nDziękujemy za wybranie Sky Route Travel i życzymy udanej wycieczki!\n\n	Polecana oferta:\nWłochy - Rzym - 542.98', '2022-02-02', 2, 1),
(9, 'Akceptacja rezerwacji.', 'akwiatkowska@skyroutetravel.pl', 'Dzień dobry!\nTwoja rezerwacja nie została zaakceptowana.\nDziękujemy za wybranie Sky Route Travel!\n\n	Polecana oferta:\nWłochy - Rzym - 542.98', '2022-02-02', 2, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `wycieczki`
--

CREATE TABLE `wycieczki` (
  `id_wycieczki` int(10) NOT NULL,
  `nazwa` varchar(100) DEFAULT NULL,
  `miejsce` varchar(200) DEFAULT NULL,
  `cena` float DEFAULT NULL,
  `id_transport` int(10) DEFAULT NULL,
  `czas` varchar(50) DEFAULT NULL,
  `id_zakwaterowanie` int(10) DEFAULT NULL,
  `wyzywienie` varchar(30) NOT NULL,
  `premium` varchar(200) DEFAULT NULL,
  `cenaPrem` float NOT NULL,
  `atrakcje` varchar(200) DEFAULT NULL,
  `rodzajWycieczki` varchar(100) DEFAULT NULL,
  `iloscDni` int(11) NOT NULL,
  `zdjecie` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `wycieczki`
--

INSERT INTO `wycieczki` (`id_wycieczki`, `nazwa`, `miejsce`, `cena`, `id_transport`, `czas`, `id_zakwaterowanie`, `wyzywienie`, `premium`, `cenaPrem`, `atrakcje`, `rodzajWycieczki`, `iloscDni`, `zdjecie`) VALUES
(1, 'Polska', 'Kraków', 291.99, 4, '2h', 2, 'Nie', 'Energylandia', 200, 'Wawel\nKościół Mariacki\nStarbucks', 'Promocja', 9, 'krakow.jpg'),
(2, 'Włochy', 'Wenecja', 1298, 1, '4h', 1, 'Tak', 'Gondole', 192, 'Most Westchnień\nMurano', 'Last Minute', 11, 'wenecja.jpg'),
(3, 'Litwa', 'Wilno', 1112, 4, '10h', 4, 'Nie', 'Ostra Brama', 112, 'Muzeum Pieniądza', 'Promocja', 5, 'wilno.jpg'),
(4, 'Grecja', 'Ateny', 2210, 1, '2h', 2, 'Tak', 'Łuk Hadriana', 87, 'Stadion Panateński\nOgród Narodowy', 'Last Minute', 10, 'ateny.jpeg'),
(5, 'Japonia', 'Tokio', 982, 1, '8h', 4, 'Nie', 'Harajuku', 44, 'Świątynia Asakusa\nSushi bar', 'Egzotyka', 8, 'japan.jpg'),
(6, 'Francja', 'Paryż', 567, 3, '4h', 1, 'Tak', 'Katedra Notre Dame', 100, 'Wieża Eiffla\nLuwr\nŁuk Triumfalny', 'Egzotyka', 15, 'paryz.jpg'),
(7, 'Polska', 'Warszawa', 432, 1, '4h', 2, 'Nie', 'Pałac Kultury i Nauki', 99, 'Syrenka Wrszawska', 'Last Minute', 3, 'warszawa.jpg'),
(8, 'Włochy', 'Rzym', 542.98, 3, '5h20min', 4, 'Tak', 'Koloseum', 150, 'Panteon\nForum Romanum\nSchody Hiszpańskie', 'Last Minute', 13, 'rzym.jpg'),
(9, 'Chiny', 'Pekin', 1765, 3, '4h', 1, 'Nie', 'Wielki Mur Chiński', 65, 'Góry Tianmen', 'Promocja', 10, 'chiny.jpg'),
(10, 'Hiszpania', 'Madryt', 678, 2, '7h', 3, 'Tak', 'Costa Brava', 76, 'Costa Blanca\nSewilla', 'Egzotyka', 6, 'hiszpania.jpg'),
(11, 'Szwecja', 'Sztokholm', 452, 2, '10h', 3, 'Nie', 'Gotlandia ', 99, 'Park Narodowy Abisko ', 'Promocja', 7, 'szwecja.jpg'),
(12, 'Malediwy', 'Malé', 1091, 1, '10h', 4, 'Tak', 'Noc w domku na wodzie', 99.99, 'Wyprawy łodzią na wyspy\n', 'Egzotyka', 7, 'malediwy.jpg');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `wycieczki_klient`
--

CREATE TABLE `wycieczki_klient` (
  `id` int(11) NOT NULL,
  `id_klienta` int(10) DEFAULT NULL,
  `id_wycieczki` int(10) DEFAULT NULL,
  `TwojaCena` float DEFAULT NULL,
  `status` varchar(4) DEFAULT NULL,
  `premium` varchar(100) DEFAULT NULL,
  `cenaPrem` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zakwaterowanie`
--

CREATE TABLE `zakwaterowanie` (
  `id_zakwaterowanie` int(10) NOT NULL,
  `cena` float DEFAULT NULL,
  `rodzaj` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `zakwaterowanie`
--

INSERT INTO `zakwaterowanie` (`id_zakwaterowanie`, `cena`, `rodzaj`) VALUES
(1, 450, 'Hotel'),
(2, 350, 'Motel'),
(3, 570, 'Kurort'),
(4, 800, 'Domek jednorodzinny');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zalogowany`
--

CREATE TABLE `zalogowany` (
  `id_zalogowanego` int(10) NOT NULL,
  `id_klienta` int(10) DEFAULT NULL,
  `id_pracownika` int(10) DEFAULT NULL,
  `login` varchar(100) DEFAULT NULL,
  `haslo` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `zalogowany`
--

INSERT INTO `zalogowany` (`id_zalogowanego`, `id_klienta`, `id_pracownika`, `login`, `haslo`) VALUES
(1, NULL, 2, 'ola', 'ola');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `faktury`
--
ALTER TABLE `faktury`
  ADD PRIMARY KEY (`id_faktury`),
  ADD KEY `fk_fakt_wyc` (`id_wycieczki`),
  ADD KEY `fk_pl_fakt` (`id_platnosc`);

--
-- Indeksy dla tabeli `klient`
--
ALTER TABLE `klient`
  ADD PRIMARY KEY (`id_klienta`);

--
-- Indeksy dla tabeli `opinie`
--
ALTER TABLE `opinie`
  ADD PRIMARY KEY (`id_opinia`),
  ADD KEY `op_kl_fk` (`id_klienta`),
  ADD KEY `op_wy_fk` (`id_wycieczki`);

--
-- Indeksy dla tabeli `platnosc`
--
ALTER TABLE `platnosc`
  ADD PRIMARY KEY (`id_platnosc`);

--
-- Indeksy dla tabeli `pracownik`
--
ALTER TABLE `pracownik`
  ADD PRIMARY KEY (`id_pracownika`);

--
-- Indeksy dla tabeli `rezerwacje`
--
ALTER TABLE `rezerwacje`
  ADD PRIMARY KEY (`id_rezerwacji`),
  ADD KEY `fk_kl_rezr` (`id_klienta`),
  ADD KEY `fk_wyc_rezr` (`id_wycieczki`),
  ADD KEY `fk_pla_rezr` (`id_platnosc`);

--
-- Indeksy dla tabeli `transport`
--
ALTER TABLE `transport`
  ADD PRIMARY KEY (`id_transport`);

--
-- Indeksy dla tabeli `wiadomosci`
--
ALTER TABLE `wiadomosci`
  ADD PRIMARY KEY (`id_wiadomosci`),
  ADD KEY `fk_kl_wiad` (`id_klienta`),
  ADD KEY `fk_prac_wiad` (`id_pracownika`);

--
-- Indeksy dla tabeli `wycieczki`
--
ALTER TABLE `wycieczki`
  ADD PRIMARY KEY (`id_wycieczki`),
  ADD KEY `fk_tr_wyc` (`id_transport`),
  ADD KEY `fk_zak_wyc` (`id_zakwaterowanie`);

--
-- Indeksy dla tabeli `wycieczki_klient`
--
ALTER TABLE `wycieczki_klient`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_kl_wy` (`id_klienta`),
  ADD KEY `fk_wy_wy` (`id_wycieczki`);

--
-- Indeksy dla tabeli `zakwaterowanie`
--
ALTER TABLE `zakwaterowanie`
  ADD PRIMARY KEY (`id_zakwaterowanie`);

--
-- Indeksy dla tabeli `zalogowany`
--
ALTER TABLE `zalogowany`
  ADD PRIMARY KEY (`id_zalogowanego`),
  ADD KEY `fk_prac_zal` (`id_pracownika`),
  ADD KEY `fk_kl_zal` (`id_klienta`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `wiadomosci`
--
ALTER TABLE `wiadomosci`
  MODIFY `id_wiadomosci` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT dla tabeli `wycieczki_klient`
--
ALTER TABLE `wycieczki_klient`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `faktury`
--
ALTER TABLE `faktury`
  ADD CONSTRAINT `fk_fakt_wyc` FOREIGN KEY (`id_wycieczki`) REFERENCES `wycieczki` (`id_wycieczki`),
  ADD CONSTRAINT `fk_pl_fakt` FOREIGN KEY (`id_platnosc`) REFERENCES `platnosc` (`id_platnosc`);

--
-- Ograniczenia dla tabeli `opinie`
--
ALTER TABLE `opinie`
  ADD CONSTRAINT `op_kl_fk` FOREIGN KEY (`id_klienta`) REFERENCES `klient` (`id_klienta`),
  ADD CONSTRAINT `op_wy_fk` FOREIGN KEY (`id_wycieczki`) REFERENCES `wycieczki` (`id_wycieczki`);

--
-- Ograniczenia dla tabeli `rezerwacje`
--
ALTER TABLE `rezerwacje`
  ADD CONSTRAINT `fk_kl_rezr` FOREIGN KEY (`id_klienta`) REFERENCES `klient` (`id_klienta`),
  ADD CONSTRAINT `fk_pla_rezr` FOREIGN KEY (`id_platnosc`) REFERENCES `platnosc` (`id_platnosc`),
  ADD CONSTRAINT `fk_wyc_rezr` FOREIGN KEY (`id_wycieczki`) REFERENCES `wycieczki` (`id_wycieczki`);

--
-- Ograniczenia dla tabeli `wiadomosci`
--
ALTER TABLE `wiadomosci`
  ADD CONSTRAINT `fk_kl_wiad` FOREIGN KEY (`id_klienta`) REFERENCES `klient` (`id_klienta`),
  ADD CONSTRAINT `fk_prac_wiad` FOREIGN KEY (`id_pracownika`) REFERENCES `pracownik` (`id_pracownika`);

--
-- Ograniczenia dla tabeli `wycieczki`
--
ALTER TABLE `wycieczki`
  ADD CONSTRAINT `fk_tr_wyc` FOREIGN KEY (`id_transport`) REFERENCES `transport` (`id_transport`),
  ADD CONSTRAINT `fk_zak_wyc` FOREIGN KEY (`id_zakwaterowanie`) REFERENCES `zakwaterowanie` (`id_zakwaterowanie`);

--
-- Ograniczenia dla tabeli `wycieczki_klient`
--
ALTER TABLE `wycieczki_klient`
  ADD CONSTRAINT `fk_kl_wy` FOREIGN KEY (`id_klienta`) REFERENCES `klient` (`id_klienta`),
  ADD CONSTRAINT `fk_wy_wy` FOREIGN KEY (`id_wycieczki`) REFERENCES `wycieczki` (`id_wycieczki`);

--
-- Ograniczenia dla tabeli `zalogowany`
--
ALTER TABLE `zalogowany`
  ADD CONSTRAINT `fk_kl_zal` FOREIGN KEY (`id_klienta`) REFERENCES `klient` (`id_klienta`),
  ADD CONSTRAINT `fk_prac_zal` FOREIGN KEY (`id_pracownika`) REFERENCES `pracownik` (`id_pracownika`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
