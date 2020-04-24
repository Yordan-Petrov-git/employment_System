-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 24, 2020 at 04:30 PM
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

-- --------------------------------------------------------

--
-- Table structure for table `companies`
--

CREATE TABLE `companies` (
  `company_id` int(32) UNSIGNED NOT NULL,
  `username_company` varchar(32) NOT NULL,
  `password_company` varchar(512) NOT NULL,
  `password_salt_company` varchar(684) NOT NULL,
  `passphrase_company` varchar(32) NOT NULL,
  `name_company` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `company_logos`
--

CREATE TABLE `company_logos` (
  `logo_id` int(32) UNSIGNED NOT NULL,
  `company_id` int(32) UNSIGNED NOT NULL,
  `logo_path` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
(4, 3, 'test@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `history_log_job_offers`
--

CREATE TABLE `history_log_job_offers` (
  `history_log_id` int(32) UNSIGNED NOT NULL,
  `job_offer_id` int(32) UNSIGNED NOT NULL,
  `user_id` int(32) UNSIGNED NOT NULL,
  `views_offer` int(128) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
  `date_added_offer` varchar(32) NOT NULL,
  `net_salary_for_offer` varchar(32) NOT NULL,
  `is_offer_active` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `logo_job_offer`
--

CREATE TABLE `logo_job_offer` (
  `logo_id` int(32) UNSIGNED NOT NULL,
  `job_offer_id` int(32) UNSIGNED NOT NULL,
  `logo_path` varchar(32) NOT NULL
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

-- --------------------------------------------------------

--
-- Table structure for table `phone_numbers_company`
--

CREATE TABLE `phone_numbers_company` (
  `phone_numers_id` int(32) UNSIGNED NOT NULL,
  `company_id` int(32) UNSIGNED NOT NULL,
  `phone_numer_company` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `type_job_offer`
--

CREATE TABLE `type_job_offer` (
  `offer_type_id` int(32) UNSIGNED NOT NULL,
  `job_offer_id` int(32) UNSIGNED NOT NULL,
  `full_time` tinyint(1) NOT NULL,
  `temporrary_position` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(32) UNSIGNED NOT NULL,
  `username_user` varchar(32) NOT NULL,
  `password_user` varchar(512) NOT NULL,
  `password_salt` varchar(684) NOT NULL,
  `passphrase_user` varchar(32) NOT NULL,
  `first_name_user` varchar(32) NOT NULL,
  `family_name_user` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username_user`, `password_user`, `password_salt`, `passphrase_user`, `first_name_user`, `family_name_user`) VALUES
(3, 'ahaead', '1234', '1234', 'bau', 'y', 'p');

-- --------------------------------------------------------

--
-- Table structure for table `user_acc_type`
--

CREATE TABLE `user_acc_type` (
  `acc_type_id` int(32) UNSIGNED NOT NULL,
  `user_id` int(32) UNSIGNED NOT NULL,
  `admin` tinyint(1) UNSIGNED DEFAULT NULL,
  `client` tinyint(1) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user_picture`
--

CREATE TABLE `user_picture` (
  `user_picture_id` int(32) UNSIGNED NOT NULL,
  `user_id` int(32) UNSIGNED NOT NULL,
  `user_picture_path` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

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
-- Indexes for table `history_log_job_offers`
--
ALTER TABLE `history_log_job_offers`
  ADD PRIMARY KEY (`history_log_id`),
  ADD KEY `fk_job_offers_history` (`job_offer_id`),
  ADD KEY `fk_users_ids` (`user_id`);

--
-- Indexes for table `job_offers`
--
ALTER TABLE `job_offers`
  ADD PRIMARY KEY (`job_offer_id`),
  ADD KEY `fk_offers_company` (`company_id`);

--
-- Indexes for table `logo_job_offer`
--
ALTER TABLE `logo_job_offer`
  ADD PRIMARY KEY (`logo_id`),
  ADD KEY `fk_offer_company_picture` (`job_offer_id`);

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
-- AUTO_INCREMENT for table `companies`
--
ALTER TABLE `companies`
  MODIFY `company_id` int(32) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `company_logos`
--
ALTER TABLE `company_logos`
  MODIFY `logo_id` int(32) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `contact_info_company`
--
ALTER TABLE `contact_info_company`
  MODIFY `company_contact_info_id` int(32) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `description_company`
--
ALTER TABLE `description_company`
  MODIFY `description_company_id` int(32) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `emails_user`
--
ALTER TABLE `emails_user`
  MODIFY `emails_id` int(32) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `history_log_job_offers`
--
ALTER TABLE `history_log_job_offers`
  MODIFY `history_log_id` int(32) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `job_offers`
--
ALTER TABLE `job_offers`
  MODIFY `job_offer_id` int(32) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `logo_job_offer`
--
ALTER TABLE `logo_job_offer`
  MODIFY `logo_id` int(32) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `phones_user`
--
ALTER TABLE `phones_user`
  MODIFY `phone_numbers_user_id` int(32) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `phone_numbers_company`
--
ALTER TABLE `phone_numbers_company`
  MODIFY `phone_numers_id` int(32) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `type_job_offer`
--
ALTER TABLE `type_job_offer`
  MODIFY `offer_type_id` int(32) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(32) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `user_acc_type`
--
ALTER TABLE `user_acc_type`
  MODIFY `acc_type_id` int(32) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user_picture`
--
ALTER TABLE `user_picture`
  MODIFY `user_picture_id` int(32) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

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
-- Constraints for table `history_log_job_offers`
--
ALTER TABLE `history_log_job_offers`
  ADD CONSTRAINT `fk_job_offers_history` FOREIGN KEY (`job_offer_id`) REFERENCES `job_offers` (`job_offer_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_users_ids` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `job_offers`
--
ALTER TABLE `job_offers`
  ADD CONSTRAINT `fk_offers_company` FOREIGN KEY (`company_id`) REFERENCES `companies` (`company_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `logo_job_offer`
--
ALTER TABLE `logo_job_offer`
  ADD CONSTRAINT `fk_offer_company_picture` FOREIGN KEY (`job_offer_id`) REFERENCES `job_offers` (`job_offer_id`) ON DELETE CASCADE ON UPDATE CASCADE;

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
