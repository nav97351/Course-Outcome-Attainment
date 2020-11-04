-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 04, 2020 at 04:20 AM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `coadb`
--

-- --------------------------------------------------------

--
-- Table structure for table `assign`
--

CREATE TABLE `assign` (
  `Serial no.` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `A1` char(1) NOT NULL,
  `A2` char(1) NOT NULL,
  `A3` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `mst1`
--

CREATE TABLE `mst1` (
  `Serial no.` int(5) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Q1` float NOT NULL,
  `Q2` float NOT NULL,
  `Q3` float NOT NULL,
  `Q4` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `mst2`
--

CREATE TABLE `mst2` (
  `Serial no.` int(5) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Q1` float NOT NULL,
  `Q2` float NOT NULL,
  `Q3` float NOT NULL,
  `Q4` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `mst3`
--

CREATE TABLE `mst3` (
  `Serial no.` int(5) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Q1` float NOT NULL,
  `Q2` float NOT NULL,
  `Q3` float NOT NULL,
  `Q4` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `pracexternal`
--

CREATE TABLE `pracexternal` (
  `Serial no.` int(5) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Marks` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `pracinternal`
--

CREATE TABLE `pracinternal` (
  `Serial no.` int(5) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Marks` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `theoryuni`
--

CREATE TABLE `theoryuni` (
  `Serial no.` int(5) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Marks` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tut`
--

CREATE TABLE `tut` (
  `Serial no.` int(5) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `T1` char(1) NOT NULL,
  `T2` char(1) NOT NULL,
  `T3` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
