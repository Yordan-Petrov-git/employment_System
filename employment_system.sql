-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 30, 2020 at 09:55 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `employment_system`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `asd` (IN `a` INT(32), IN `b` INT(32))  SELECT e.`job_offer_id`,e.`company_job_offer_title`,e.`city_offer`
,e.`position_job_offer`
,e.`description_job_offer`
,e.`date_added_offer`
,e.`net_salary_for_offer`
,d.`name_company`
,c.`type`
FROM `job_offers` AS e 
INNER JOIN `companies` AS d
ON e.`company_id` = d.`company_id`
INNER JOIN `type_job_offer`AS c
ON e.`job_offer_id` = c.`job_offer_id`
LIMIT a,b$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `count_job_offers` ()  SELECT count(job_offer_id) from job_offers$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `count_job_offers_by_company_id` (IN `comp_id` INT(32) UNSIGNED)  SELECT count(job_offer_id)
from job_offers
WHERE `company_id` = comp_id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `count_job_offers_by_offer_id` (IN `offer_id` INT(32))  SELECT COUNT(`job_offer_id`)
FROM `applications_log_job_offers`
WHERE `job_offer_id` = offer_id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_job_offer` (IN `offer_id` INT(32) UNSIGNED)  DELETE FROM `job_offers` WHERE `job_offers`.`job_offer_id` = offer_id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `get_all_companies_usernames` ()  SELECT e.`company_id`,d.`username_company`
FROM `companies` AS e
INNER JOIN `login_credentials_company` AS d
ON e.company_id = d.company_id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `get_all_usernames_users` ()  SELECT e.`user_id`,d.`username_user`
FROM `users` AS e
INNER JOIN `login_credentials_users` AS d
ON e.user_id = d.user_id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_application_for_job_offer` (IN `offer_id` INT(32) UNSIGNED, IN `user_id` INT(32) UNSIGNED, IN `m_letter` VARCHAR(256), IN `y_exp` VARCHAR(8))  INSERT INTO `applications_log_job_offers`
( `job_offer_id`, 
 `user_id`, 
 `motivational_letter_from_user`,
 `years_of_experience`)
 VALUES (
    offer_id, 
    user_id,
    m_letter,
     y_exp)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_new_company` (IN `name_comp` VARCHAR(32), IN `usrname` VARCHAR(32), IN `passwrd` VARCHAR(512), IN `salt` VARCHAR(684), IN `phrase` VARCHAR(32))  BEGIN 
DECLARE lastId INT;
INSERT INTO `companies` 
(`name_company`) 
VALUES (name_comp);
SET lastId=LAST_INSERT_ID();
INSERT INTO `login_credentials_company`
(`company_id`,`username_company`
 ,`password_company`,`password_salt_company`,`passphrase_company`)
VALUES (lastId,usrname,passwrd,salt,phrase);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_new_job_offer` (IN `comp_id` INT(32), IN `title` VARCHAR(32), IN `city` VARCHAR(32), IN `position` VARCHAR(32), IN `description` VARCHAR(32), IN `salary` VARCHAR(32), IN `type_offer` VARCHAR(32))  BEGIN 
DECLARE lastId INT;
INSERT INTO `job_offers`(`company_id`, `company_job_offer_title`, `city_offer`, `position_job_offer`, `description_job_offer`,`net_salary_for_offer`) 
VALUES (comp_id,title,city,position,description,salary);
SET lastId=LAST_INSERT_ID();
INSERT INTO`type_job_offer` (`job_offer_id`,`type`)
VALUES (lastId,type_offer);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_new_user` (IN `fname` VARCHAR(32), IN `lname` VARCHAR(32), IN `email` VARCHAR(128), IN `phone` VARCHAR(32), IN `usrname` VARCHAR(32), IN `passwrd` VARCHAR(512), IN `salt` VARCHAR(684), IN `phrase` VARCHAR(32))  BEGIN 
DECLARE lastId INT;
INSERT INTO `users` (`first_name_user`, `family_name_user`) 
VALUES (fname,lname);
SET lastId=LAST_INSERT_ID();
INSERT INTO`emails_user` (`user_id`,`email`)
VALUES (lastId,email);
INSERT INTO`phones_user` (`user_id`,`phone_number`)
VALUES (lastId, phone);
INSERT INTO`login_credentials_users`(`user_id`,`username_user`,`password_user`,`password_salt_user`,`passphrase_user`)
VALUES (lastId,usrname,passwrd,salt,phrase);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `select_all_company_info_by_company_username` (IN `username` VARCHAR(32))  NO SQL
SELECT e.`company_id`,e.`name_company`,d.`username_company`
FROM `companies` AS e 
INNER JOIN `login_credentials_company` AS d
ON e.`company_id` = d.`company_id`
WHERE d.`username_company`= username
LIMIT 1$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `select_all_ingfo_job_offers_by_company_id` (IN `comp_id` INT(32) UNSIGNED)  SELECT * FROM `job_offers` WHERE `company_id` = comp_id ORDER BY `company_id` ASC$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `select_all_job_offers_for_paging_by_company_id` (IN `a` INT(32) UNSIGNED, IN `b` INT(32) UNSIGNED, IN `comp_id` INT(32) UNSIGNED)  SELECT e.`job_offer_id`,e.`company_job_offer_title`,e.`city_offer`
,e.`position_job_offer`
,e.`description_job_offer`
,e.`date_added_offer`
,e.`net_salary_for_offer`
,d.`name_company`
,c.`type`
FROM `job_offers` AS e 
INNER JOIN `companies` AS d
ON e.`company_id` = d.`company_id`
INNER JOIN `type_job_offer`AS c
ON e.`job_offer_id` = c.`job_offer_id`
WHERE e.`company_id` = comp_id
LIMIT a,b$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `select_all_offers_offer_id` (IN `comp_id` INT(32))  SELECT e.`job_offer_id`,e.`company_job_offer_title`,e.`city_offer`
,e.`position_job_offer`
,e.`description_job_offer`
,e.`date_added_offer`
,e.`net_salary_for_offer`
,d.`name_company`
,c.`type`
FROM `job_offers` AS e 
INNER JOIN `companies` AS d
ON e.`company_id` = d.`company_id`
INNER JOIN `type_job_offer`AS c
ON e.`job_offer_id` = c.`job_offer_id`
WHERE e.`company_id` = comp_id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `select_all_user_info_by_username` (IN `username` VARCHAR(32))  SELECT e.`user_id`,e.`first_name_user`,e.`family_name_user`,d.`username_user`,c.`phone_number`,f.`email` 
FROM `users` AS e 
INNER JOIN `login_credentials_users` AS d
ON e.`user_id` = d.`user_id`
INNER JOIN `phones_user` AS c
ON e.user_id = c.user_id
INNER JOIN `emails_user` AS f
ON e.user_id = f.user_id
WHERE d.`username_user`= username
LIMIT 1$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `select_company_by_username` (IN `company` VARCHAR(32))  NO SQL
SELECT e.`company_id`,d.`username_company`,d.`password_company`,d.`password_salt_company`
FROM `companies` AS e 
INNER JOIN `login_credentials_company` AS d
ON e.`company_id` = d.`company_id`
WHERE d.`username_company`= company
LIMIT 1$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `select_offers_by_title` (IN `job_title` VARCHAR(32))  SELECT e.`job_offer_id`,e.`company_job_offer_title`,e.`city_offer` ,e.`position_job_offer` ,e.`description_job_offer` ,e.`date_added_offer` ,e.`net_salary_for_offer` ,d.`name_company` ,c.`type` FROM `job_offers` AS e INNER JOIN `companies` AS d ON e.`company_id` = d.`company_id` INNER JOIN `type_job_offer`AS c ON e.`job_offer_id` = c.`job_offer_id` WHERE e.`company_job_offer_title` = job_title$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `select_serach_company_username_for_existing` (IN `company` VARCHAR(32))  SELECT e.`company_id`,d.`username_company`
FROM `companies` AS e 
INNER JOIN `login_credentials_company` AS d
ON e.`company_id` = d.`company_id`
WHERE d.`username_company`= company
LIMIT 1$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `select_username_if_exists` (IN `usrname` VARCHAR(32))  SELECT e.`user_id`,d.`username_user`
FROM `users` AS e 
INNER JOIN `login_credentials_users` AS d
ON e.`user_id` = d.`user_id`
WHERE d.`username_user`= usrname
LIMIT 1$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `select_user_all_info_by_user_id` (IN `usr_id` VARCHAR(32))  SELECT e.`user_id`,e.`first_name_user`,e.`family_name_user`,d.`username_user`,c.`phone_number`,f.`email` 
FROM `users` AS e 
INNER JOIN `login_credentials_users` AS d
ON e.`user_id` = d.`user_id`
INNER JOIN `phones_user` AS c
ON e.user_id = c.user_id
INNER JOIN `emails_user` AS f
ON e.user_id = f.user_id
WHERE d.`user_id`= usr_id
LIMIT 1$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `select_user_by_username` (IN `usrname` VARCHAR(32))  NO SQL
SELECT e.`user_id`,d.`username_user`,d.`password_user`,d.`password_salt_user` 
FROM `users` AS e 
INNER JOIN `login_credentials_users` AS d
ON e.`user_id` = d.`user_id`
WHERE d.`username_user`= usrname
LIMIT 1$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `select_user_info_by_user_id_for_job_offers_applications` (IN `job_offer_id` INT(32) UNSIGNED, IN `p` INT(32) UNSIGNED, IN `s` INT(32) UNSIGNED)  SELECT e.`user_id`,e.`first_name_user`,e.`family_name_user`,c.`phone_number`,f.`email`,g.`motivational_letter_from_user` ,g.`years_of_experience` ,g.`time_stamp_application` FROM `users` AS e INNER JOIN `login_credentials_users` AS d ON e.`user_id` = d.`user_id` INNER JOIN `phones_user` AS c ON e.user_id = c.user_id INNER JOIN `emails_user` AS f ON e.user_id = f.user_id INNER JOIN `applications_log_job_offers` AS g ON e.user_id = g.user_id
WHERE g.`job_offer_id`= job_offer_id
LIMIT p,s$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `serach_specific_job_offer` (IN `jtitle` VARCHAR(32))  SELECT * FROM `job_offers` WHERE `company_job_offer_title` = jtitle$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `show_all_job_offers` (IN `a` INT(32) UNSIGNED, IN `b` INT(32) UNSIGNED)  SELECT e.`job_offer_id`,e.`company_job_offer_title`,e.`city_offer`
,e.`position_job_offer`
,e.`description_job_offer`
,e.`date_added_offer`
,e.`net_salary_for_offer`
,d.`name_company`
,c.`type`
FROM `job_offers` AS e 
INNER JOIN `companies` AS d
ON e.`company_id` = d.`company_id`
INNER JOIN `type_job_offer`AS c
ON e.`job_offer_id` = c.`job_offer_id`
LIMIT a,b$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `applications_log_job_offers`
--

CREATE TABLE `applications_log_job_offers` (
  `history_log_id` int(32) UNSIGNED NOT NULL,
  `job_offer_id` int(32) UNSIGNED NOT NULL,
  `user_id` int(32) UNSIGNED NOT NULL,
  `motivational_letter_from_user` varchar(256) DEFAULT NULL,
  `years_of_experience` varchar(8) DEFAULT NULL,
  `time_stamp_application` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `companies`
--

CREATE TABLE `companies` (
  `company_id` int(32) UNSIGNED NOT NULL,
  `name_company` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `companies`
--

INSERT INTO `companies` (`company_id`, `name_company`) VALUES
(1, 'zranch0'),
(38, 'ecorp'),
(42, 'Company Name'),
(43, 'Company admin');

-- --------------------------------------------------------

--
-- Table structure for table `company_logos`
--

CREATE TABLE `company_logos` (
  `logo_id` int(32) UNSIGNED NOT NULL,
  `company_id` int(32) UNSIGNED NOT NULL,
  `logo_path` mediumblob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `company_logos`
--

INSERT INTO `company_logos` (`logo_id`, `company_id`, `logo_path`) VALUES
(1, 1, 0x3a636f6d705c6c6f666f);

-- --------------------------------------------------------

--
-- Table structure for table `contact_info_company`
--

CREATE TABLE `contact_info_company` (
  `company_contact_info_id` int(32) UNSIGNED NOT NULL,
  `company_id` int(32) UNSIGNED NOT NULL,
  `address_company` varchar(32) NOT NULL,
  `city_company` varchar(32) NOT NULL,
  `eik_company` varchar(32) NOT NULL,
  `webpage_company` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `description_company`
--

CREATE TABLE `description_company` (
  `description_company_id` int(32) UNSIGNED NOT NULL,
  `company_id` int(32) UNSIGNED NOT NULL,
  `about_company` varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `emails_user`
--

CREATE TABLE `emails_user` (
  `emails_id` int(32) UNSIGNED NOT NULL,
  `user_id` int(32) UNSIGNED NOT NULL,
  `email` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `emails_user`
--

INSERT INTO `emails_user` (`emails_id`, `user_id`, `email`) VALUES
(67, 90, 'ad@gmai.com');

-- --------------------------------------------------------

--
-- Table structure for table `job_offers`
--

CREATE TABLE `job_offers` (
  `job_offer_id` int(32) UNSIGNED NOT NULL,
  `company_id` int(32) UNSIGNED NOT NULL,
  `company_job_offer_title` varchar(32) NOT NULL,
  `city_offer` varchar(32) NOT NULL,
  `position_job_offer` varchar(32) NOT NULL,
  `description_job_offer` varchar(256) NOT NULL,
  `date_added_offer` timestamp NOT NULL DEFAULT current_timestamp(),
  `net_salary_for_offer` varchar(32) NOT NULL,
  `is_offer_active` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `job_offers`
--

INSERT INTO `job_offers` (`job_offer_id`, `company_id`, `company_job_offer_title`, `city_offer`, `position_job_offer`, `description_job_offer`, `date_added_offer`, `net_salary_for_offer`, `is_offer_active`) VALUES
(11, 38, 'comp', 'varna', 'java intern', 'asdasd', '2020-04-28 22:46:39', '2050', 1),
(52, 42, 'Job Title', 'City', 'Position', 'Description', '2020-04-30 15:48:30', 'Salary', 1),
(53, 42, 'Job Title', 'City', 'Position', 'Description', '2020-04-30 15:48:31', 'Salary', 1),
(54, 42, 'Job Title', 'City', 'Position', 'Description', '2020-04-30 15:48:31', 'Salary', 1),
(55, 42, 'Job Title', 'City', 'Position', 'Description', '2020-04-30 15:48:31', 'Salary', 1),
(56, 42, 'Job Title', 'City', 'Position', 'Description', '2020-04-30 15:48:31', 'Salary', 1),
(57, 42, 'Job Title', 'City', 'Position', 'Description', '2020-04-30 19:43:57', 'Salary', 1),
(58, 42, 'Job Title', 'City', 'Position', 'Description', '2020-04-30 19:44:46', 'Salary', 1);

-- --------------------------------------------------------

--
-- Table structure for table `login_credentials_company`
--

CREATE TABLE `login_credentials_company` (
  `login_company_creds_id` int(32) UNSIGNED NOT NULL,
  `company_id` int(32) UNSIGNED NOT NULL,
  `username_company` varchar(32) NOT NULL,
  `password_company` varchar(88) NOT NULL,
  `password_salt_company` varchar(684) NOT NULL,
  `passphrase_company` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `login_credentials_company`
--

INSERT INTO `login_credentials_company` (`login_company_creds_id`, `company_id`, `username_company`, `password_company`, `password_salt_company`, `passphrase_company`) VALUES
(1, 1, 'zranchousername', '0000', '000', '000'),
(5, 38, 'ecorp', 'MPBKnBx4Ax1UYs3ZnF7vAoyN5E9aSRuB7f+xowNQnHdsntZA+DJVpEB5LUs9vkipKhN33BpTiNxQBT+5/dRDVw==', 'V5LvncbT8v8vFK1eruvhsrdNM+CRra5Gg9IlqcVppnngCBWAFcnGmfibpqK2wqD/0gKG33/L5nMb8BmM1tjad78Y6WT5bAWqsb4Q9LT28oH2QezjnLHZsWAcX5CBjZLiYNVkPMEC6UYC8uku/uyOB+CZXs/0bEE0wTbMFuEwloxyo6XLUcdXLvLH84tdEedSxu6wyMihJPlK/pxf59eWiGDlTmJUZn5XzPLw/lHaugXEkxcd19Uo5/F08z80yMXNgu0tuz6ZZkprNOh6Tk/VLD10ROga4C0+ttOO5TaK4fggaiW41QiGEhZzDrQXzXwztAWZ8BP/39l0dGWN97SYMKQbswbohtMRGRKZDWe+TUB0n8kVUhewJzWHMjspTSZnebVrt6e9IOvD8bAnGXhuuKgdabbIi+1LIFf9y+y5YKEJLyW8xw+1HaoAifuWvZzWYMsurd2f27h2DyN9cb5MSAGzF8Z2Wb2VEvKTmfrLTIvy4gOBoFpCRZrZ1dApn1kXq5NiB5As395bBpwsehNV77QgElvoTjWH3k8ZQDqtI2fRWgeZ+yBBmmlVFrXEj6pHm0oaoLVAMJS8XZ7HXWxSBtwMT8CP5OD9jB5VzneDPD8STSBxN2ULD5Jcw7MjHE20MIkloJ77AFRkHEGJgi4GmnxCQrjoKh8c19Sz3zD+UK4=', 'ecorp'),
(9, 42, 'Username', '88+dZ6VuuAfuQiMLRjtieJA1E3Uxrtn9xbHRvIiLahpv28QWBRkae6LoRK0pmk50o+y5nc9ktPL1erQUkSxYUQ==', '5RMLdkX3xntiMT767NHGL+uIsMSxprvfUP05DzDbZPSFoUB04RSEh5veSMycncTD6E11368uXZ3j6mNfm91Va/fGP5gvHTlVMiymTJSdZ/TVzOtOqCUgKEUjTE4yw3koiweep++T/dUhBuAkcM7sxDjpP/UQk5Io4aD+eIXQyaLCPvOabgICxRMOB4+7iEG0wg6U4zo5Xux1ChCatrDNC3gudWSBjmNzQwb4MpAjyXOONLhXq4bRkRvMRrOVPNbneDNKWRYfk0AWnHOUsZVk5o4GDRkJkdcshvjxrpI/VNmaT4ToL5qc7cAlrRSfst9hOi+XYiRI3qxKLbKPaw/jMFIyhJuBR9K47TKP9amp/GYNWB3KpYPzwnqTy0Re6RADKw8/zTHObLvsrwYqAuxvWenPKIccGIorjzDIR8DyVVvaKvt4J7UdOVI4m92S6oBu1CogGFOAzCn2eP8nhzZQMpqsBYsE7q5R2Qu0fSvd4rrVZ7BZxlFQPt6Ex0mwHYitRvDMqW78X8uyq/61Jaqe+sAZcUxhMV4SpL3Ou+5UkfBdSCAwtWe0dwSfNZ3ujARYgVFB0gSLYtHFu0CqUq8y/DUAWVPweyz0IzHrQvdnRpxgqOHQScJa/JYYQri4VQ/orpxGiaz0dZBYNWsBRvlQFV9xRgy6Qquu0ZVvnTImS8c=', 'Passphrase'),
(10, 43, 'admin', 'Xzkbmwdkua1Ej5vf+3Lg2d2eMoIwYTb79e7mpqqVcZMhO8v+yVlGAEW/k9ZLd071i2APvODz02etMa/8jyKtcQ==', 'JjT1lHjembH7FtGXpmAD4LRTSRbvfb5wVQ86Od2xhoGZBV3Vd1GCK/xKMyVc7oNB79yo1WRZW8rKWu+VtGPBEYogVBKAoVsW5z906gUfIpTkRmuV91pQgl31ZrVNklVzcsHOKmEHx9jM196GH6YELIyq9TuRKI3Biyg0byhby5QPwSZVK7ulB7JtbvC1T5zn0qtpJBNKaO24uvkvEAqOfZRKngosgxEqQ6meAmj/KPtZ4UnxxFv8vrCab+CADh1UJkVx0s4+Xi6iWOHmC+6k1wiJJKhQyMgsba5t64RoI+4KwxYBTJnTrfALFZL1MsJDNrvn+lOUIXYktNIKxzZ3Z8RhZOS+WGyo5l43PYH+wfcbyErBTlBoqzAZaVo/ItlE1i+ulOhh93bUtGgMY3ToaQuev9NDQS6gPZW0aSfj/NZYO8rWxGmv+c8ki/6cu2nLryDX58E9Quboha5oc2ga+uOIDa0mpsIOLHqr3jEYaqCpHC23WMMlGTOFZ0Yn+xXz08TE6EZoA7h8Mn2kM+kKh17dKbrUopIRI1RfgihG89D+xYgMTU6ABfYC65dnD/AzIvVtR52T0QwfmMzYNBUxrb7tKjmqqUOClLhXyBInVv65WC0kVvwHyHorvj1bC9lq7CS4cv7gZF4/FM1N+sq+SEPtH4PO/qYICSxQE/xbFp8=', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `login_credentials_users`
--

CREATE TABLE `login_credentials_users` (
  `login_user_creds_id` int(32) UNSIGNED NOT NULL,
  `user_id` int(32) UNSIGNED NOT NULL,
  `username_user` varchar(32) NOT NULL,
  `password_user` varchar(88) NOT NULL,
  `password_salt_user` varchar(684) NOT NULL,
  `passphrase_user` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `login_credentials_users`
--

INSERT INTO `login_credentials_users` (`login_user_creds_id`, `user_id`, `username_user`, `password_user`, `password_salt_user`, `passphrase_user`) VALUES
(51, 90, 'Username', 'YpaXWEf3HkSjAoQMpadOpCPHDmI/LY+Teh0sKY0jduEvbEbuJsLbgTdf3g4PpdEqwZRXQCH+T04t4pliN60QWA==', 'fTmYoy26xmNGI8dII6X3w2ZJBeBi3jplZqwe6Caz8JIsWC+C2vopNZAxpFHLTVlvhiMeyp8Skg9YPFrK0uvMRDYqLAjcxsPsdhR10EHfc/420JygOGrwv1fzbaM+mO6O/sEZhx/n36c0g+sIGmUb+ovQyAaw7pOvWr3xCX/bj1saYCEDYseji2GT5CqqUgQz5YYmacQG08xbkSrM6PPoyUQvIGuzB1UOZdQux9yZP5IRUdcDSppphZcEAgPNWdmQOAvWbGTCRQIyxcP2xnoV0NyKJBxlfbamVC8hzRcIuz7jFtuIa8rlGvkanYGN0iV4yvz9IjHcuoe0q9NTtrAYBXaxwFdAC057WZIRBX7qKPbnwMeOsqBCcn6EEe/3D7Qe3200KcxbfA9VCYFWuse/G73XycOrlFZM3FTYmjh7tdbilLjO+nG/YHS82SPATivu0lpJt+zgWDC6+ixK3RXiHs54HF5zI2Xdg/hUbm2h7aTdQk7rwtjULS0uJDsP4K2tAlfPM4xMIGF7q0LwON2T6Vec+09wY7y53+dyNibOcgrq/jtkwU0q9XBEWgQJ/Ad6q12OQOW5+rH7zH7w/5nYXs7AEWGhxQ63YMaCcXYeJDN/FaiPUfDDMwR6t9OdIopNNyx3cyHRN3u59WjpYNj+nhOf5URyqU7AM9/8Xg+c85w=', 'Passphrase');

-- --------------------------------------------------------

--
-- Table structure for table `logo_job_offer`
--

CREATE TABLE `logo_job_offer` (
  `logo_id` int(32) UNSIGNED NOT NULL,
  `job_offer_id` int(32) UNSIGNED NOT NULL,
  `logo_path` mediumblob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `log_info_job_offers`
--

CREATE TABLE `log_info_job_offers` (
  `log_info_id` int(32) UNSIGNED NOT NULL,
  `job_offer_id` int(32) UNSIGNED NOT NULL,
  `user_id` int(32) UNSIGNED NOT NULL,
  `timestamp_log` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `phones_user`
--

CREATE TABLE `phones_user` (
  `phone_numbers_user_id` int(32) UNSIGNED NOT NULL,
  `user_id` int(32) UNSIGNED NOT NULL,
  `phone_number` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `phones_user`
--

INSERT INTO `phones_user` (`phone_numbers_user_id`, `user_id`, `phone_number`) VALUES
(66, 90, '0000000000');

-- --------------------------------------------------------

--
-- Table structure for table `phone_numbers_company`
--

CREATE TABLE `phone_numbers_company` (
  `phone_numers_id` int(32) UNSIGNED NOT NULL,
  `company_id` int(32) UNSIGNED NOT NULL,
  `phone_company` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `phone_numbers_company`
--

INSERT INTO `phone_numbers_company` (`phone_numers_id`, `company_id`, `phone_company`) VALUES
(1, 1, '555555555555');

-- --------------------------------------------------------

--
-- Table structure for table `type_job_offer`
--

CREATE TABLE `type_job_offer` (
  `offer_type_id` int(32) UNSIGNED NOT NULL,
  `job_offer_id` int(32) UNSIGNED NOT NULL,
  `type` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `type_job_offer`
--

INSERT INTO `type_job_offer` (`offer_type_id`, `job_offer_id`, `type`) VALUES
(3, 11, 'filltime'),
(44, 52, 'Temporary position'),
(45, 53, 'Temporary position'),
(46, 54, 'Temporary position'),
(47, 55, 'Temporary position'),
(48, 56, 'Temporary position'),
(49, 57, 'Full-time job'),
(50, 58, 'Full-time job');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(32) UNSIGNED NOT NULL,
  `first_name_user` varchar(32) NOT NULL,
  `family_name_user` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `first_name_user`, `family_name_user`) VALUES
(90, 'Name', 'Family name');

-- --------------------------------------------------------

--
-- Table structure for table `user_acc_type`
--

CREATE TABLE `user_acc_type` (
  `acc_type_id` int(32) UNSIGNED NOT NULL,
  `user_id` int(32) UNSIGNED NOT NULL,
  `acc_type` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user_picture`
--

CREATE TABLE `user_picture` (
  `user_picture_id` int(32) UNSIGNED NOT NULL,
  `user_id` int(32) UNSIGNED NOT NULL,
  `user_picture_path` mediumblob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `applications_log_job_offers`
--
ALTER TABLE `applications_log_job_offers`
  ADD PRIMARY KEY (`history_log_id`),
  ADD KEY `fk_job_offer_application` (`job_offer_id`),
  ADD KEY `fk_users_applied` (`user_id`);

--
-- Indexes for table `companies`
--
ALTER TABLE `companies`
  ADD PRIMARY KEY (`company_id`);

--
-- Indexes for table `company_logos`
--
ALTER TABLE `company_logos`
  ADD PRIMARY KEY (`logo_id`),
  ADD KEY `fk_company_logo` (`company_id`);

--
-- Indexes for table `contact_info_company`
--
ALTER TABLE `contact_info_company`
  ADD PRIMARY KEY (`company_contact_info_id`),
  ADD KEY `fk_company_info` (`company_id`);

--
-- Indexes for table `description_company`
--
ALTER TABLE `description_company`
  ADD PRIMARY KEY (`description_company_id`),
  ADD KEY `fk_description_company` (`company_id`);

--
-- Indexes for table `emails_user`
--
ALTER TABLE `emails_user`
  ADD PRIMARY KEY (`emails_id`),
  ADD KEY `FOREIGN_email_usr` (`user_id`) USING BTREE;

--
-- Indexes for table `job_offers`
--
ALTER TABLE `job_offers`
  ADD PRIMARY KEY (`job_offer_id`),
  ADD KEY `fk_offers_company` (`company_id`);

--
-- Indexes for table `login_credentials_company`
--
ALTER TABLE `login_credentials_company`
  ADD PRIMARY KEY (`login_company_creds_id`),
  ADD UNIQUE KEY `username_company` (`username_company`),
  ADD KEY `fk_company_login_credentials` (`company_id`);

--
-- Indexes for table `login_credentials_users`
--
ALTER TABLE `login_credentials_users`
  ADD PRIMARY KEY (`login_user_creds_id`),
  ADD KEY `fk_user_login_credentials` (`user_id`);

--
-- Indexes for table `logo_job_offer`
--
ALTER TABLE `logo_job_offer`
  ADD PRIMARY KEY (`logo_id`),
  ADD KEY `fk_offer_company_picture` (`job_offer_id`);

--
-- Indexes for table `log_info_job_offers`
--
ALTER TABLE `log_info_job_offers`
  ADD PRIMARY KEY (`log_info_id`),
  ADD KEY `fk_job_offers` (`job_offer_id`),
  ADD KEY `fk_users_viewed_the_offer` (`user_id`);

--
-- Indexes for table `phones_user`
--
ALTER TABLE `phones_user`
  ADD PRIMARY KEY (`phone_numbers_user_id`),
  ADD KEY `fk_phone_number_user` (`user_id`);

--
-- Indexes for table `phone_numbers_company`
--
ALTER TABLE `phone_numbers_company`
  ADD PRIMARY KEY (`phone_numers_id`),
  ADD KEY `fk_phone_company` (`company_id`);

--
-- Indexes for table `type_job_offer`
--
ALTER TABLE `type_job_offer`
  ADD PRIMARY KEY (`offer_type_id`),
  ADD KEY `fk_offer_position_type` (`job_offer_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `user_acc_type`
--
ALTER TABLE `user_acc_type`
  ADD PRIMARY KEY (`acc_type_id`),
  ADD KEY `fk_acc_type` (`user_id`);

--
-- Indexes for table `user_picture`
--
ALTER TABLE `user_picture`
  ADD PRIMARY KEY (`user_picture_id`),
  ADD KEY `fk_user_picture` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `applications_log_job_offers`
--
ALTER TABLE `applications_log_job_offers`
  MODIFY `history_log_id` int(32) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `companies`
--
ALTER TABLE `companies`
  MODIFY `company_id` int(32) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT for table `company_logos`
--
ALTER TABLE `company_logos`
  MODIFY `logo_id` int(32) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `contact_info_company`
--
ALTER TABLE `contact_info_company`
  MODIFY `company_contact_info_id` int(32) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `description_company`
--
ALTER TABLE `description_company`
  MODIFY `description_company_id` int(32) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `emails_user`
--
ALTER TABLE `emails_user`
  MODIFY `emails_id` int(32) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=68;

--
-- AUTO_INCREMENT for table `job_offers`
--
ALTER TABLE `job_offers`
  MODIFY `job_offer_id` int(32) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;

--
-- AUTO_INCREMENT for table `login_credentials_company`
--
ALTER TABLE `login_credentials_company`
  MODIFY `login_company_creds_id` int(32) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `login_credentials_users`
--
ALTER TABLE `login_credentials_users`
  MODIFY `login_user_creds_id` int(32) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT for table `logo_job_offer`
--
ALTER TABLE `logo_job_offer`
  MODIFY `logo_id` int(32) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `log_info_job_offers`
--
ALTER TABLE `log_info_job_offers`
  MODIFY `log_info_id` int(32) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `phones_user`
--
ALTER TABLE `phones_user`
  MODIFY `phone_numbers_user_id` int(32) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=67;

--
-- AUTO_INCREMENT for table `phone_numbers_company`
--
ALTER TABLE `phone_numbers_company`
  MODIFY `phone_numers_id` int(32) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `type_job_offer`
--
ALTER TABLE `type_job_offer`
  MODIFY `offer_type_id` int(32) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(32) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=91;

--
-- AUTO_INCREMENT for table `user_acc_type`
--
ALTER TABLE `user_acc_type`
  MODIFY `acc_type_id` int(32) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `user_picture`
--
ALTER TABLE `user_picture`
  MODIFY `user_picture_id` int(32) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `applications_log_job_offers`
--
ALTER TABLE `applications_log_job_offers`
  ADD CONSTRAINT `fk_job_offer_application` FOREIGN KEY (`job_offer_id`) REFERENCES `job_offers` (`job_offer_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_users_applied` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `company_logos`
--
ALTER TABLE `company_logos`
  ADD CONSTRAINT `fk_company_logo` FOREIGN KEY (`company_id`) REFERENCES `companies` (`company_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `contact_info_company`
--
ALTER TABLE `contact_info_company`
  ADD CONSTRAINT `fk_company_info` FOREIGN KEY (`company_id`) REFERENCES `companies` (`company_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `description_company`
--
ALTER TABLE `description_company`
  ADD CONSTRAINT `fk_description_company` FOREIGN KEY (`company_id`) REFERENCES `companies` (`company_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `emails_user`
--
ALTER TABLE `emails_user`
  ADD CONSTRAINT `fk_email_usr` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `job_offers`
--
ALTER TABLE `job_offers`
  ADD CONSTRAINT `fk_offers_company` FOREIGN KEY (`company_id`) REFERENCES `companies` (`company_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `login_credentials_company`
--
ALTER TABLE `login_credentials_company`
  ADD CONSTRAINT `fk_company_login_credentials` FOREIGN KEY (`company_id`) REFERENCES `companies` (`company_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `login_credentials_users`
--
ALTER TABLE `login_credentials_users`
  ADD CONSTRAINT `fk_user_login_credentials` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `logo_job_offer`
--
ALTER TABLE `logo_job_offer`
  ADD CONSTRAINT `fk_offer_company_picture` FOREIGN KEY (`job_offer_id`) REFERENCES `job_offers` (`job_offer_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `log_info_job_offers`
--
ALTER TABLE `log_info_job_offers`
  ADD CONSTRAINT `fk_job_offers` FOREIGN KEY (`job_offer_id`) REFERENCES `job_offers` (`job_offer_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_users_viewed_the_offer` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `phones_user`
--
ALTER TABLE `phones_user`
  ADD CONSTRAINT `fk_phone_number_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `phone_numbers_company`
--
ALTER TABLE `phone_numbers_company`
  ADD CONSTRAINT `fk_phone_company` FOREIGN KEY (`company_id`) REFERENCES `companies` (`company_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `type_job_offer`
--
ALTER TABLE `type_job_offer`
  ADD CONSTRAINT `fk_offer_position_type` FOREIGN KEY (`job_offer_id`) REFERENCES `job_offers` (`job_offer_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user_acc_type`
--
ALTER TABLE `user_acc_type`
  ADD CONSTRAINT `fk_acc_type` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user_picture`
--
ALTER TABLE `user_picture`
  ADD CONSTRAINT `fk_user_picture` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
