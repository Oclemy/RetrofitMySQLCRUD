-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Feb 25, 2020 at 03:50 AM
-- Server version: 5.6.24
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `scientistsdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `scientiststb`
--

CREATE TABLE IF NOT EXISTS `scientiststb` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `died` date NOT NULL DEFAULT '0000-00-00',
  `description` text NOT NULL,
  `galaxy` varchar(50) NOT NULL,
  `star` varchar(100) NOT NULL,
  `dob` date NOT NULL DEFAULT '0000-00-00'
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `scientiststb`
--

INSERT INTO `scientiststb` (`id`, `name`, `died`, `description`, `galaxy`, `star`, `dob`) VALUES
(1, 'Albert Einstein', '1973-04-06', 'Albert Einstein was a German-born theoretical physicist who developed the theory of relativity, one of the two pillars of modern physics. His work is also known for its influence on the philosophy of science.', 'Cartwheel', 'VY Canis Majoris', '1905-05-13'),
(2, 'Niels David Bohr', '1992-11-10', 'Niels Henrik David Bohr was a Danish physicist who made foundational contributions to understanding atomic structure and quantum theory, for which he received the Nobel Prize in Physics in 1922. Bohr was also a philosopher and a promoter of scientific research.', 'Cosmos Redshift', 'Bellatrix', '1918-05-05'),
(3, 'Galileo Galilei', '1972-07-05', 'Galileo Galilei was an Italian astronomer, physicist and engineer, sometimes described as a polymath. Galileo has been called the "father of observational astronomy", the "father of modern physics", the "father of the scientific method", and the "father of modern science".', 'Centarus A', 'VY Canis Majoris', '1901-05-07'),
(4, 'Stephen Hawking ', '2018-07-09', 'Stephen William Hawking CH CBE FRS FRSA was an English theoretical physicist, cosmologist, and author who was director of research at the Centre for Theoretical Cosmology at the University of Cambridge at the time of his death.', 'Wolf Lundmark Melotte', 'KY Cygni', '1942-01-08'),
(5, 'Rene Descartes', '1965-10-05', 'Rene Descartes was a French philosopher, mathematician, and scientist. A native of the Kingdom of France, he spent about 20 years of his life in the Dutch Republic after serving for a while in the Dutch States Army of Maurice of Nassau, Prince of Orange and the Stadtholder of the United Provinces.', 'Virgo Stella Stream', 'KY Cygni', '1905-06-11'),
(6, 'Nikola Tesla', '1969-06-06', 'Nikola Tesla was a Serbian American inventor, electrical engineer, mechanical engineer, and futurist who is best known for his contributions to the design of the modern alternating current electricity supply system.', 'Circinus Galaxy', 'Antares', '1913-11-27'),
(8, 'dffff hhhh', '0000-00-00', 'yyyyy', 'uuuuuuu', '', '0000-00-00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `scientiststb`
--
ALTER TABLE `scientiststb`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `scientiststb`
--
ALTER TABLE `scientiststb`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
