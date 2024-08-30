-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 23, 2023 at 02:59 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `nhom5`
--

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
    `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
    (12);

-- --------------------------------------------------------

--
-- Table structure for table `table_about`
--

CREATE TABLE `table_about` (
                               `about_id` tinyint(4) NOT NULL,
                               `about_title` text NOT NULL,
                               `about_sub` text NOT NULL,
                               `about_topic` text NOT NULL,
                               `about_img` varchar(64) NOT NULL,
                               `name` text NOT NULL,
                               `status` tinyint(4) NOT NULL,
                               `Created` timestamp NOT NULL DEFAULT current_timestamp(),
                               `Updated` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `table_about`
--

INSERT INTO `table_about` (`about_id`, `about_title`, `about_sub`, `about_topic`, `about_img`, `name`, `status`, `Created`, `Updated`) VALUES
    (1, 'Our Mission', 'Mauris non lacinia magna. Sed nec lobortis dolor. Vestibulum rhoncus dignissim risus, sed consectetur erat. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nullam maximus mauris sit amet odio convallis, in pharetra magna gravida. Praesent sed nunc fermentum mi molestie tempor. Morbi vitae viverra odio. Pellentesque ac velit egestas, luctus arcu non, laoreet mauris. Sed in ipsum tempor, consequat odio in, porttitor ante. Ut mauris ligula, volutpat in sodales in, porta non odio. Pellentesque tempor urna vitae mi vestibulum, nec venenatis nulla lobortis. Proin at gravida ante. Mauris auctor purus at lacus maximus euismod. Pellentesque vulputate massa ut nisl hendrerit, eget elementum libero iaculis.', 'Creativity is just connecting things. When you ask creative people how they did something, they feel a little guilty because they didn\'t really do it, they just saw something. It seemed obvious to them after a while.', '/images/about-02.jpg', 'Steve Job’s', 1, '2022-11-22 10:16:37', '2022-11-22 17:19:17'),
(2, 'Our Mission 2', 'Mauris non lacinia magna. Sed nec lobortis dolor. Vestibulum rhoncus dignissim risus, sed consectetur erat. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nullam maximus mauris sit amet odio convallis, in pharetra magna gravida. Praesent sed nunc fermentum mi molestie tempor. Morbi vitae viverra odio. Pellentesque ac velit egestas, luctus arcu non, laoreet mauris. Sed in ipsum tempor, consequat odio in, porttitor ante. Ut mauris ligula, volutpat in sodales in, porta non odio. Pellentesque tempor urna vitae mi vestibulum, nec venenatis nulla lobortis. Proin at gravida ante. Mauris auctor purus at lacus maximus euismod. Pellentesque vulputate massa ut nisl hendrerit, eget elementum libero iaculis.', 'Creativity is just connecting things. When you ask creative people how they did something, they feel a little guilty because they didn\'t really do it, they just saw something. It seemed obvious to them after a while.', '/images/about-02.jpg', 'Steve Job’s', 1, '2022-11-22 10:16:37', '2022-11-22 18:30:10');

-- --------------------------------------------------------

--
-- Table structure for table `table_accounts`
--

CREATE TABLE `table_accounts` (
                                  `Account_id` int(11) NOT NULL,
                                  `Username` varchar(255) NOT NULL,
                                  `Password` varchar(255) NOT NULL,
                                  `Role_id` int(11) NOT NULL,
                                  `Status` tinyint(4) NOT NULL,
                                  `Verification_code` varchar(255) DEFAULT NULL,
                                  `reset_pass_code` varchar(255) DEFAULT NULL,
                                  `Created` timestamp NOT NULL DEFAULT current_timestamp(),
                                  `Updated` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `table_accounts`
--

INSERT INTO `table_accounts` (`Account_id`, `Username`, `Password`, `Role_id`, `Status`, `Verification_code`, `reset_pass_code`, `Created`, `Updated`) VALUES
                                                                                                                                                           (1, 'admin_npt', '$2a$10$T4gMFcvbC3z71daUG280neusl9e9fxMrysf3fItLEKQU0L/3nMzVu', 2, 1, '', NULL, '2022-11-05 15:25:38', '2023-11-22 01:47:43'),
                                                                                                                                                           (2, 'user_npt', '$2a$10$.xv3//v6j8oY1GmCbpebLu/vrXNJQ82bRG5ux4psUiBCm.884Rrf.', 1, 1, '', NULL, '2022-11-05 15:25:38', '2023-11-23 13:19:59'),
                                                                                                                                                           (3, 'user_nhttm', '$2a$10$GixiwoIjaryBJJhTzKy64eOYZvTha3PxXSNeXMmfjVIfPfFcNx/rC', 1, 1, '', NULL, '2022-11-05 15:25:38', '2022-11-07 11:28:02'),
                                                                                                                                                           (4, 'user_nntt', '$2a$10$GixiwoIjaryBJJhTzKy64eOYZvTha3PxXSNeXMmfjVIfPfFcNx/rC', 1, 1, '', NULL, '2022-11-05 15:25:38', '2022-11-07 11:28:04'),
                                                                                                                                                           (5, 'user_lvhn', '$2a$10$GixiwoIjaryBJJhTzKy64eOYZvTha3PxXSNeXMmfjVIfPfFcNx/rC', 1, 1, '', NULL, '2022-11-05 15:25:38', '2022-11-07 11:28:06'),
                                                                                                                                                           (6, 'admin', '$2a$10$.xv3//v6j8oY1GmCbpebLu/vrXNJQ82bRG5ux4psUiBCm.884Rrf.', 2, 1, '', NULL, '2022-11-05 15:25:38', '2023-11-23 12:43:54'),
                                                                                                                                                           (7, 'test', '$2a$10$GixiwoIjaryBJJhTzKy64eOYZvTha3PxXSNeXMmfjVIfPfFcNx/rC', 1, 1, '', NULL, '2022-11-05 15:25:38', '2022-11-07 11:28:09'),
                                                                                                                                                           (14, 'testmail', '$2a$10$GixiwoIjaryBJJhTzKy64eOYZvTha3PxXSNeXMmfjVIfPfFcNx/rC', 1, 1, NULL, NULL, '2022-11-05 15:25:38', '2022-11-07 11:28:11'),
                                                                                                                                                           (15, 'adad', '$2a$10$GixiwoIjaryBJJhTzKy64eOYZvTha3PxXSNeXMmfjVIfPfFcNx/rC', 1, 1, NULL, NULL, '2022-11-05 15:25:38', '2022-11-07 11:28:12'),
                                                                                                                                                           (17, 'thinhken106', '$2a$10$GixiwoIjaryBJJhTzKy64eOYZvTha3PxXSNeXMmfjVIfPfFcNx/rC', 1, 1, NULL, NULL, '2022-11-05 15:25:38', '2022-11-07 11:28:16');

-- --------------------------------------------------------

--
-- Table structure for table `table_blog`
--

CREATE TABLE `table_blog` (
                              `blog_id` int(11) NOT NULL,
                              `author` varchar(255) DEFAULT NULL,
                              `content` varchar(255) DEFAULT NULL,
                              `descript` varchar(255) DEFAULT NULL,
                              `title` varchar(255) DEFAULT NULL,
                              `created_at` datetime(6) DEFAULT NULL,
                              `img` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `table_blog_cmt`
--

CREATE TABLE `table_blog_cmt` (
                                  `cmt_id` int(11) NOT NULL,
                                  `cmt` varchar(255) DEFAULT NULL,
                                  `created_at` datetime(6) DEFAULT NULL,
                                  `blog_id` int(11) DEFAULT NULL,
                                  `author_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `table_category`
--

CREATE TABLE `table_category` (
                                  `Category_id` int(11) NOT NULL,
                                  `Category_name` varchar(255) NOT NULL,
                                  `status` tinyint(4) NOT NULL,
                                  `Created` datetime NOT NULL DEFAULT current_timestamp(),
                                  `Updated` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `table_category`
--

INSERT INTO `table_category` (`Category_id`, `Category_name`, `status`, `Created`, `Updated`) VALUES
                                                                                                  (13, 'a', 1, '2023-11-23 19:54:52', '2023-11-23 12:54:52'),
                                                                                                  (14, 'trai cai', 1, '2023-11-23 20:17:14', '2023-11-23 13:17:14');

-- --------------------------------------------------------

--
-- Table structure for table `table_comment`
--

CREATE TABLE `table_comment` (
                                 `comment_id` int(11) NOT NULL,
                                 `comment_value` varchar(255) DEFAULT NULL,
                                 `created` datetime(6) DEFAULT NULL,
                                 `rating` int(11) DEFAULT NULL,
                                 `status` int(11) DEFAULT NULL,
                                 `updated` datetime(6) DEFAULT NULL,
                                 `customer_id` int(11) DEFAULT NULL,
                                 `product_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_ci;

--
-- Dumping data for table `table_comment`
--

INSERT INTO `table_comment` (`comment_id`, `comment_value`, `created`, `rating`, `status`, `updated`, `customer_id`, `product_id`) VALUES
    (1, 'alo', NULL, 5, 1, NULL, 1, 19);

-- --------------------------------------------------------

--
-- Table structure for table `table_contact`
--

CREATE TABLE `table_contact` (
                                 `contact_id` int(11) NOT NULL,
                                 `address` varchar(255) NOT NULL,
                                 `phone_number` varchar(20) NOT NULL,
                                 `email_sup` varchar(255) NOT NULL,
                                 `status` tinyint(4) NOT NULL,
                                 `Created` timestamp NOT NULL DEFAULT current_timestamp(),
                                 `Updated` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `table_contact`
--

INSERT INTO `table_contact` (`contact_id`, `address`, `phone_number`, `email_sup`, `status`, `Created`, `Updated`) VALUES
    (1, '280, An Duong Vuong, Ward 4, District 5, Ho Chi Minh City', '+84 99 111 1114', 'lifeleopard.contact@gmail.com', 1, '2022-11-22 09:52:25', '2022-11-22 10:04:58');

-- --------------------------------------------------------

--
-- Table structure for table `table_customer`
--

CREATE TABLE `table_customer` (
                                  `Customer_id` int(11) NOT NULL,
                                  `Account_id` int(11) NOT NULL,
                                  `First_name` varchar(255) NOT NULL,
                                  `Last_name` varchar(255) NOT NULL,
                                  `Birthday` date DEFAULT NULL,
                                  `Email` varchar(255) NOT NULL,
                                  `Phone_number` varchar(11) NOT NULL,
                                  `Address` varchar(255) NOT NULL,
                                  `Created` timestamp NOT NULL DEFAULT current_timestamp(),
                                  `Updated` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `table_customer`
--

INSERT INTO `table_customer` (`Customer_id`, `Account_id`, `First_name`, `Last_name`, `Birthday`, `Email`, `Phone_number`, `Address`, `Created`, `Updated`) VALUES
                                                                                                                                                                (1, 2, 'Nguyễn Phước', 'Thịnh', '2002-06-10', 'thinhit1.nguyen@gmail.com', '09056*****', 'Việt Nam', '2022-11-05 15:18:33', '2022-11-22 19:23:14'),
                                                                                                                                                                (2, 3, 'Nguyễn Huỳnh Thị Tuyết', 'My', NULL, 'myit.nguyen@gmail.com', '1234567890', 'Việt Nam', '2022-11-05 15:18:33', '2022-11-05 15:18:33'),
                                                                                                                                                                (3, 4, 'Ngô Nguyễn Thanh', 'Tâm', NULL, 'tamit.nguyen@gmail.com', '1234567890', 'Việt Nam', '2022-11-05 15:18:33', '2022-11-05 15:18:33'),
                                                                                                                                                                (4, 5, 'Lê Võ Huỳnh', 'Nga', '2002-01-01', 'nga.it@gmail.com', '01232312312', 'Việt Nam', '2022-11-05 15:18:33', '2022-11-05 15:18:33'),
                                                                                                                                                                (5, 7, 'Nguyễn Phước', 'Thịnh', '2022-11-02', 'thinhit2.nguyen@gmail.com', '0123456789', 'aa', '2022-11-05 15:18:33', '2022-11-05 15:18:33'),
                                                                                                                                                                (6, 14, 'Nguyen', 'Thinh', '2022-11-08', 'thinhit3.nguyen@gmail.com', '12345678', 'adadad', '2022-11-05 15:18:33', '2022-11-05 15:18:33'),
                                                                                                                                                                (7, 15, 'th', 'dsa', '2022-11-08', 'thinhit4.nguyen@gmail.com', '123123', '213213', '2022-11-05 15:18:33', '2022-11-05 15:18:33'),
                                                                                                                                                                (9, 17, 'Nguyen', 'Thinh', '2022-11-14', 'thinhken106@gmail.com', '1234567890', 'Quang Nam', '2022-11-05 15:18:33', '2022-11-05 15:18:33');

-- --------------------------------------------------------

--
-- Table structure for table `table_departments`
--

CREATE TABLE `table_departments` (
                                     `Department_id` int(11) NOT NULL,
                                     `Department_name` varchar(255) NOT NULL,
                                     `Created` timestamp NOT NULL DEFAULT current_timestamp(),
                                     `Updated` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `table_departments`
--

INSERT INTO `table_departments` (`Department_id`, `Department_name`, `Created`, `Updated`) VALUES
    (1, '280 An Dương Vương', '2022-11-05 15:19:08', '2022-11-05 15:19:08');

-- --------------------------------------------------------

--
-- Table structure for table `table_employee`
--

CREATE TABLE `table_employee` (
                                  `Employee_id` int(11) NOT NULL,
                                  `Account_id` int(11) NOT NULL,
                                  `First_name` varchar(255) NOT NULL,
                                  `Last_name` varchar(255) NOT NULL,
                                  `Birthday` date DEFAULT NULL,
                                  `Email` varchar(255) NOT NULL,
                                  `Phone_number` varchar(11) NOT NULL,
                                  `Address` varchar(255) NOT NULL,
                                  `Department_id` int(11) NOT NULL,
                                  `Position_id` int(11) NOT NULL,
                                  `Status` int(11) NOT NULL,
                                  `Created` timestamp NOT NULL DEFAULT current_timestamp(),
                                  `Updated` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `table_employee`
--

INSERT INTO `table_employee` (`Employee_id`, `Account_id`, `First_name`, `Last_name`, `Birthday`, `Email`, `Phone_number`, `Address`, `Department_id`, `Position_id`, `Status`, `Created`, `Updated`) VALUES
                                                                                                                                                                                                          (1, 1, 'Nguyễn Phước', 'Thịnh', NULL, 'thinhadmin@gmail.com', '0905633***', 'Điên Dương - Điện Bàn - Quảng Nam', 1, 2, 1, '2022-11-05 15:26:00', '2022-11-05 15:26:00'),
                                                                                                                                                                                                          (2, 6, 'admin', 'web', NULL, 'admin@gmail.com', '123456789', 'Viet Nam', 1, 2, 1, '2022-11-05 15:26:00', '2022-11-05 15:26:00');

-- --------------------------------------------------------

--
-- Table structure for table `table_event`
--

CREATE TABLE `table_event` (
                               `event_id` tinyint(4) NOT NULL,
                               `event_name` varchar(255) NOT NULL,
                               `event_topic` varchar(255) NOT NULL,
                               `event_img` varchar(64) NOT NULL,
                               `list_product_id` varchar(255) DEFAULT NULL,
                               `status` int(11) NOT NULL,
                               `Created` datetime NOT NULL DEFAULT current_timestamp(),
                               `Updated` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `table_order`
--

CREATE TABLE `table_order` (
                               `Order_id` int(11) NOT NULL,
                               `Customer_id` int(11) NOT NULL,
                               `First_name` varchar(255) NOT NULL,
                               `Last_name` varchar(255) NOT NULL,
                               `Phone_numer` varchar(11) NOT NULL,
                               `Address` varchar(255) NOT NULL,
                               `Total` double NOT NULL,
                               `Payment` varchar(255) DEFAULT NULL,
                               `Payment_status` tinyint(4) NOT NULL DEFAULT 0,
                               `Status` int(11) NOT NULL,
                               `Created` timestamp NOT NULL DEFAULT current_timestamp(),
                               `Updated` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `table_order`
--

INSERT INTO `table_order` (`Order_id`, `Customer_id`, `First_name`, `Last_name`, `Phone_numer`, `Address`, `Total`, `Payment`, `Payment_status`, `Status`, `Created`, `Updated`) VALUES
                                                                                                                                                                                     (1, 2, 'Nguyễn', 'My', '0123455612', 'Lạc Long Quân', 165000, '', 1, 2, '2022-11-09 16:36:08', '2022-11-09 16:36:08'),
                                                                                                                                                                                     (2, 4, 'Không', 'Biết', '0123455612', 'không biết luôn', 75000, '', 1, 1, '2022-11-09 16:37:55', '2022-11-09 16:37:55'),
                                                                                                                                                                                     (3, 1, 'Nguyễn Phước', 'Thịnh', '09056*****', 'Việt Nam', 3333, NULL, 0, 0, '2023-11-23 13:35:26', '2023-11-23 13:35:26');

-- --------------------------------------------------------

--
-- Table structure for table `table_position`
--

CREATE TABLE `table_position` (
                                  `Position_id` int(11) NOT NULL,
                                  `Position_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `table_position`
--

INSERT INTO `table_position` (`Position_id`, `Position_name`) VALUES
                                                                  (1, 'Bảo Vệ'),
                                                                  (2, 'Bán Hàng');

-- --------------------------------------------------------

--
-- Table structure for table `table_products`
--

CREATE TABLE `table_products` (
                                  `Product_id` int(11) NOT NULL,
                                  `Category_id` int(11) NOT NULL,
                                  `Product_type` int(11) DEFAULT NULL,
                                  `Product_name` varchar(255) NOT NULL,
                                  `Product_images_preview` varchar(64) DEFAULT NULL,
                                  `Short_description` text NOT NULL,
                                  `description` text NOT NULL,
                                  `Quantity` int(11) NOT NULL,
                                  `Status` tinyint(4) NOT NULL,
                                  `pirce_preview` double NOT NULL,
                                  `Weight` double DEFAULT NULL,
                                  `Dimensions` varchar(64) DEFAULT NULL,
                                  `Materials` varchar(64) DEFAULT NULL,
                                  `Created` timestamp NOT NULL DEFAULT current_timestamp(),
                                  `Updated` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `table_products`
--

INSERT INTO `table_products` (`Product_id`, `Category_id`, `Product_type`, `Product_name`, `Product_images_preview`, `Short_description`, `description`, `Quantity`, `Status`, `pirce_preview`, `Weight`, `Dimensions`, `Materials`, `Created`, `Updated`) VALUES
                                                                                                                                                                                                                                                               (18, 13, NULL, 'a', '/images/product/18/qbcdouvnT120231123204902.png', 'a', 'a', 10000, 1, 1111111, 0, NULL, NULL, '2023-11-23 12:56:48', '2023-11-23 13:49:02'),
                                                                                                                                                                                                                                                               (19, 14, NULL, 'adaada', '/images/product/19/wFk6ClTMCR20231123201751.jpg', 'đa', 'ad', 1108, 1, 1111110, 0, NULL, NULL, '2023-11-23 13:17:51', '2023-11-23 13:35:26');

-- --------------------------------------------------------

--
-- Table structure for table `table_product_images`
--

CREATE TABLE `table_product_images` (
                                        `Product_images_id` int(11) NOT NULL,
                                        `Product_id` int(11) NOT NULL,
                                        `Product_imgaes_url` varchar(64) NOT NULL,
                                        `Created` timestamp NOT NULL DEFAULT current_timestamp(),
                                        `Updated` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `table_product_images`
--

INSERT INTO `table_product_images` (`Product_images_id`, `Product_id`, `Product_imgaes_url`, `Created`, `Updated`) VALUES
                                                                                                                       (18, 18, '/images/product/18/qbcdouvnT120231123204902.png', '2023-11-23 12:56:48', '2023-11-23 13:49:02'),
                                                                                                                       (19, 19, '/images/product/19/wFk6ClTMCR20231123201751.jpg', '2023-11-23 13:17:51', '2023-11-23 13:17:51');

-- --------------------------------------------------------

--
-- Table structure for table `table_product_order`
--

CREATE TABLE `table_product_order` (
                                       `Product_Order_id` int(11) NOT NULL,
                                       `Order_id` int(11) NOT NULL,
                                       `Product_id` int(11) NOT NULL,
                                       `Quantity` int(11) NOT NULL,
                                       `Price` int(11) NOT NULL,
                                       `Created` timestamp NOT NULL DEFAULT current_timestamp(),
                                       `Updated` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `table_product_order`
--

INSERT INTO `table_product_order` (`Product_Order_id`, `Order_id`, `Product_id`, `Quantity`, `Price`, `Created`, `Updated`) VALUES
    (3, 3, 123, 3, 3333, '2023-11-23 13:35:26', '2023-11-23 13:35:26');

-- --------------------------------------------------------

--
-- Table structure for table `table_product_size`
--

CREATE TABLE `table_product_size` (
                                      `Product_Size_id` int(11) NOT NULL,
                                      `Product_id` int(11) NOT NULL,
                                      `Size_id` int(11) NOT NULL,
                                      `Quantity` int(11) NOT NULL,
                                      `Price` int(11) NOT NULL,
                                      `Status` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `table_product_size`
--

INSERT INTO `table_product_size` (`Product_Size_id`, `Product_id`, `Size_id`, `Quantity`, `Price`, `Status`) VALUES
                                                                                                                 (120, 18, 8, 100, 10000, 1),
                                                                                                                 (121, 18, 9, 1000, 1221312312, 1),
                                                                                                                 (122, 19, 8, 1111111, 11111, 1),
                                                                                                                 (123, 19, 9, 11108, 1111, 1);

-- --------------------------------------------------------

--
-- Table structure for table `table_product_type`
--

CREATE TABLE `table_product_type` (
                                      `Type_id` int(11) NOT NULL,
                                      `Type_name` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `table_roles`
--

CREATE TABLE `table_roles` (
                               `Role_id` int(11) NOT NULL,
                               `role_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `table_roles`
--

INSERT INTO `table_roles` (`Role_id`, `role_name`) VALUES
                                                       (1, 'ROLE_USER'),
                                                       (2, 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Table structure for table `table_size`
--

CREATE TABLE `table_size` (
                              `Size_id` int(11) NOT NULL,
                              `Size_name` varchar(255) NOT NULL,
                              `Created` timestamp NOT NULL DEFAULT current_timestamp(),
                              `Updated` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `table_size`
--

INSERT INTO `table_size` (`Size_id`, `Size_name`, `Created`, `Updated`) VALUES
                                                                            (8, 'Túi', '2023-11-23 12:55:41', '2023-11-23 12:55:41'),
                                                                            (9, 'Kg', '2023-11-23 12:55:41', '2023-11-23 12:55:41');

-- --------------------------------------------------------

--
-- Table structure for table `table_slide_home`
--

CREATE TABLE `table_slide_home` (
                                    `slide_id` tinyint(4) NOT NULL,
                                    `slide_img` varchar(64) NOT NULL,
                                    `slide_sub` text NOT NULL,
                                    `slide_title` text NOT NULL,
                                    `status` tinyint(4) NOT NULL,
                                    `Created` timestamp NOT NULL DEFAULT current_timestamp(),
                                    `Updated` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `table_slide_home`
--

INSERT INTO `table_slide_home` (`slide_id`, `slide_img`, `slide_sub`, `slide_title`, `status`, `Created`, `Updated`) VALUES
                                                                                                                         (1, '/images/slide-01.jpg', 'Women Collection 2018', 'NEW SEASON', 0, '2022-11-22 11:03:24', '2023-11-23 12:51:28'),
                                                                                                                         (2, '/images/slide-02.jpg', 'Men New-Season', 'Jackets & Coats', 0, '2022-11-22 11:03:24', '2023-11-23 12:51:30'),
                                                                                                                         (3, '/images/slide-03.jpg', 'Men Collection 2018', 'New arrivals', 0, '2022-11-22 11:03:24', '2023-11-23 12:51:33');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `table_about`
--
ALTER TABLE `table_about`
    ADD PRIMARY KEY (`about_id`);

--
-- Indexes for table `table_accounts`
--
ALTER TABLE `table_accounts`
    ADD PRIMARY KEY (`Account_id`),
  ADD UNIQUE KEY `User_name` (`Username`),
  ADD KEY `Role_id` (`Role_id`);

--
-- Indexes for table `table_blog`
--
ALTER TABLE `table_blog`
    ADD PRIMARY KEY (`blog_id`);

--
-- Indexes for table `table_blog_cmt`
--
ALTER TABLE `table_blog_cmt`
    ADD PRIMARY KEY (`cmt_id`),
  ADD KEY `FKm7p2e6sqp566s9t6us8dc3lkh` (`blog_id`),
  ADD KEY `FKivq4deixxtp6vblx55klfkpgg` (`author_id`);

--
-- Indexes for table `table_category`
--
ALTER TABLE `table_category`
    ADD PRIMARY KEY (`Category_id`);

--
-- Indexes for table `table_comment`
--
ALTER TABLE `table_comment`
    ADD PRIMARY KEY (`comment_id`),
  ADD KEY `FKb9pr3i50ub9s4t5c0nrcwnmnf` (`customer_id`),
  ADD KEY `FKgayfqtr4r4avtqswe6mhq1dd2` (`product_id`);

--
-- Indexes for table `table_contact`
--
ALTER TABLE `table_contact`
    ADD PRIMARY KEY (`contact_id`);

--
-- Indexes for table `table_customer`
--
ALTER TABLE `table_customer`
    ADD PRIMARY KEY (`Customer_id`),
  ADD UNIQUE KEY `Username` (`Account_id`);

--
-- Indexes for table `table_departments`
--
ALTER TABLE `table_departments`
    ADD PRIMARY KEY (`Department_id`);

--
-- Indexes for table `table_employee`
--
ALTER TABLE `table_employee`
    ADD PRIMARY KEY (`Employee_id`),
  ADD KEY `Department_id` (`Department_id`),
  ADD KEY `Position_id` (`Position_id`),
  ADD KEY `Account_id` (`Account_id`);

--
-- Indexes for table `table_event`
--
ALTER TABLE `table_event`
    ADD PRIMARY KEY (`event_id`);

--
-- Indexes for table `table_order`
--
ALTER TABLE `table_order`
    ADD PRIMARY KEY (`Order_id`),
  ADD KEY `FKrv5by75uwwfxjn8mvu5hpxf6h` (`Customer_id`);

--
-- Indexes for table `table_position`
--
ALTER TABLE `table_position`
    ADD PRIMARY KEY (`Position_id`);

--
-- Indexes for table `table_products`
--
ALTER TABLE `table_products`
    ADD PRIMARY KEY (`Product_id`),
  ADD KEY `FKqurjuqo4fo8qbk0x7ygtmxvby` (`Category_id`);

--
-- Indexes for table `table_product_images`
--
ALTER TABLE `table_product_images`
    ADD PRIMARY KEY (`Product_images_id`),
  ADD KEY `FKner637g99t8cybonraf640soc` (`Product_id`);

--
-- Indexes for table `table_product_order`
--
ALTER TABLE `table_product_order`
    ADD PRIMARY KEY (`Product_Order_id`),
  ADD KEY `FK3rxpo4povqcftkcgsodtlnoxq` (`Order_id`),
  ADD KEY `FKnbe9j30yemq7ogsq967miqwbg` (`Product_id`);

--
-- Indexes for table `table_product_size`
--
ALTER TABLE `table_product_size`
    ADD PRIMARY KEY (`Product_Size_id`),
  ADD KEY `Size_id` (`Size_id`),
  ADD KEY `Product_id` (`Product_id`);

--
-- Indexes for table `table_product_type`
--
ALTER TABLE `table_product_type`
    ADD PRIMARY KEY (`Type_id`);

--
-- Indexes for table `table_roles`
--
ALTER TABLE `table_roles`
    ADD PRIMARY KEY (`Role_id`);

--
-- Indexes for table `table_size`
--
ALTER TABLE `table_size`
    ADD PRIMARY KEY (`Size_id`);

--
-- Indexes for table `table_slide_home`
--
ALTER TABLE `table_slide_home`
    ADD PRIMARY KEY (`slide_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `table_about`
--
ALTER TABLE `table_about`
    MODIFY `about_id` tinyint(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `table_accounts`
--
ALTER TABLE `table_accounts`
    MODIFY `Account_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `table_blog`
--
ALTER TABLE `table_blog`
    MODIFY `blog_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `table_blog_cmt`
--
ALTER TABLE `table_blog_cmt`
    MODIFY `cmt_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `table_category`
--
ALTER TABLE `table_category`
    MODIFY `Category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `table_comment`
--
ALTER TABLE `table_comment`
    MODIFY `comment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `table_contact`
--
ALTER TABLE `table_contact`
    MODIFY `contact_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `table_customer`
--
ALTER TABLE `table_customer`
    MODIFY `Customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `table_departments`
--
ALTER TABLE `table_departments`
    MODIFY `Department_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `table_employee`
--
ALTER TABLE `table_employee`
    MODIFY `Employee_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `table_event`
--
ALTER TABLE `table_event`
    MODIFY `event_id` tinyint(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `table_order`
--
ALTER TABLE `table_order`
    MODIFY `Order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `table_position`
--
ALTER TABLE `table_position`
    MODIFY `Position_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `table_products`
--
ALTER TABLE `table_products`
    MODIFY `Product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `table_product_images`
--
ALTER TABLE `table_product_images`
    MODIFY `Product_images_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `table_product_order`
--
ALTER TABLE `table_product_order`
    MODIFY `Product_Order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `table_product_size`
--
ALTER TABLE `table_product_size`
    MODIFY `Product_Size_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=124;

--
-- AUTO_INCREMENT for table `table_product_type`
--
ALTER TABLE `table_product_type`
    MODIFY `Type_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `table_roles`
--
ALTER TABLE `table_roles`
    MODIFY `Role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `table_size`
--
ALTER TABLE `table_size`
    MODIFY `Size_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `table_slide_home`
--
ALTER TABLE `table_slide_home`
    MODIFY `slide_id` tinyint(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `table_accounts`
--
ALTER TABLE `table_accounts`
    ADD CONSTRAINT `table_accounts_ibfk_1` FOREIGN KEY (`Role_id`) REFERENCES `table_roles` (`Role_id`);

--
-- Constraints for table `table_blog_cmt`
--
ALTER TABLE `table_blog_cmt`
    ADD CONSTRAINT `FKivq4deixxtp6vblx55klfkpgg` FOREIGN KEY (`author_id`) REFERENCES `table_customer` (`Customer_id`),
  ADD CONSTRAINT `FKm7p2e6sqp566s9t6us8dc3lkh` FOREIGN KEY (`blog_id`) REFERENCES `table_blog` (`blog_id`);

--
-- Constraints for table `table_comment`
--
ALTER TABLE `table_comment`
    ADD CONSTRAINT `FKb9pr3i50ub9s4t5c0nrcwnmnf` FOREIGN KEY (`customer_id`) REFERENCES `table_customer` (`Customer_id`),
  ADD CONSTRAINT `FKgayfqtr4r4avtqswe6mhq1dd2` FOREIGN KEY (`product_id`) REFERENCES `table_products` (`Product_id`);

--
-- Constraints for table `table_customer`
--
ALTER TABLE `table_customer`
    ADD CONSTRAINT `table_customer_ibfk_1` FOREIGN KEY (`Account_id`) REFERENCES `table_accounts` (`Account_id`);

--
-- Constraints for table `table_employee`
--
ALTER TABLE `table_employee`
    ADD CONSTRAINT `table_employee_ibfk_1` FOREIGN KEY (`Department_id`) REFERENCES `table_departments` (`Department_id`),
  ADD CONSTRAINT `table_employee_ibfk_2` FOREIGN KEY (`Position_id`) REFERENCES `table_position` (`Position_id`),
  ADD CONSTRAINT `table_employee_ibfk_3` FOREIGN KEY (`Account_id`) REFERENCES `table_accounts` (`Account_id`);

--
-- Constraints for table `table_order`
--
ALTER TABLE `table_order`
    ADD CONSTRAINT `FKrv5by75uwwfxjn8mvu5hpxf6h` FOREIGN KEY (`Customer_id`) REFERENCES `table_customer` (`Customer_id`);

--
-- Constraints for table `table_products`
--
ALTER TABLE `table_products`
    ADD CONSTRAINT `FKqurjuqo4fo8qbk0x7ygtmxvby` FOREIGN KEY (`Category_id`) REFERENCES `table_category` (`Category_id`);

--
-- Constraints for table `table_product_images`
--
ALTER TABLE `table_product_images`
    ADD CONSTRAINT `FKner637g99t8cybonraf640soc` FOREIGN KEY (`Product_id`) REFERENCES `table_products` (`Product_id`);

--
-- Constraints for table `table_product_order`
--
ALTER TABLE `table_product_order`
    ADD CONSTRAINT `FK3rxpo4povqcftkcgsodtlnoxq` FOREIGN KEY (`Order_id`) REFERENCES `table_order` (`Order_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FKnbe9j30yemq7ogsq967miqwbg` FOREIGN KEY (`Product_id`) REFERENCES `table_product_size` (`Product_Size_id`);

--
-- Constraints for table `table_product_size`
--
ALTER TABLE `table_product_size`
    ADD CONSTRAINT `table_product_size_ibfk_2` FOREIGN KEY (`Size_id`) REFERENCES `table_size` (`Size_id`),
  ADD CONSTRAINT `table_product_size_ibfk_3` FOREIGN KEY (`Product_id`) REFERENCES `table_products` (`Product_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
