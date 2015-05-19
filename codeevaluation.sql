-- phpMyAdmin SQL Dump
-- version 4.1.4
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2015-05-13 09:08:00
-- 服务器版本： 5.6.15-log
-- PHP Version: 5.5.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `codeevaluation`
--
CREATE DATABASE IF NOT EXISTS `codeevaluation` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `codeevaluation`;

-- --------------------------------------------------------

--
-- 表的结构 `exercise`
--

CREATE TABLE IF NOT EXISTS `exercise` (
  `title` varchar(100) NOT NULL,
  `description` varchar(2000) NOT NULL,
  `contentHead` text NOT NULL,
  `contentFoot` text NOT NULL,
  PRIMARY KEY (`title`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `exercise`
--

INSERT INTO `exercise` (`title`, `description`, `contentHead`, `contentFoot`) VALUES
('Value Switch', 'Write an algorithm that switches values of 2 int variables', 'public class ValueSwitch {\n\n	public static void main(String[] args) {\n		switchValue(100, 200);\n	}\n	\n	private static void switchValue(int a, int b) {\n		// display before switch\n		System.out.println("Before switch: a=" + a + " b=" + b);\n		\n		// algorithm to fulfill', '		// display\n		System.out.println("After switch:  a=" + a + " b=" + b);\n	}\n}');

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `email` varchar(100) NOT NULL,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`email`, `firstname`, `lastname`, `password`) VALUES
('123@123.com', 'Jfd', 'Jer', 'jiyuan1005'),
('1234@123.com', 'Jfdf', 'Jer', 'jiyuan1005'),
('321@321.com', 'Yuhao', 'Zeng', '12345abc');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
