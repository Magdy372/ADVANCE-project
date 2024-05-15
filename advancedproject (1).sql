-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 15, 2024 at 11:57 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `advancedproject`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `sub_total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`id`, `user_id`, `product_id`, `quantity`, `sub_total`) VALUES
(5, NULL, NULL, 2, 0),
(6, NULL, NULL, 5, 0),
(11, 108, 19, 1, 59000),
(12, 108, 10, 2, 24000),
(13, 108, 10, 2, 24000);

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'Rings'),
(2, 'Necklaces'),
(3, 'Earrings'),
(4, 'Bracelets'),
(5, 'Anklets'),
(6, 'Brooches'),
(7, 'Pendants'),
(8, 'Chains'),
(9, 'Charms'),
(10, 'Bangles'),
(13, 'zyada'),
(14, ''),
(15, 'abc'),
(16, 'a'),
(17, 'xx'),
(18, 'metals'),
(19, 'r r r rr ');

-- --------------------------------------------------------

--
-- Table structure for table `checkout`
--

CREATE TABLE `checkout` (
  `id` bigint(20) NOT NULL,
  `product_id` int(11) DEFAULT NULL,
  `cart_id` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `metal`
--

CREATE TABLE `metal` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `metal`
--

INSERT INTO `metal` (`id`, `name`) VALUES
(1, 'Gold'),
(2, 'Silver'),
(3, 'Platinum'),
(4, 'Titanium'),
(5, 'Stainless Steel'),
(6, 'White Gold'),
(7, 'Rose Gold'),
(8, 'Yellow Gold');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `available` bit(1) NOT NULL,
  `created_at` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `photos` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `rating` int(11) NOT NULL,
  `weight` double NOT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `metal_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `available`, `created_at`, `description`, `name`, `photos`, `price`, `rating`, `weight`, `category_id`, `metal_id`) VALUES
(9, b'1', '2024-03-20 12:59:41.291084', '1', 'abc', 'Blank diagram (1).png', 1, 1, 1, 2, 2),
(10, b'1', '2024-03-17 19:33:02.744824', '', 'Love Band For Her', 'frsg.23-003.jpeg', 12000, 5, 10, 1, 1),
(11, b'1', '2024-03-17 19:38:17.379789', '', 'Three-Quarter Love Ring', 'frsg.22-003.jpeg', 28000, 5, 6, 1, 1),
(12, b'1', '2024-03-17 19:37:24.608993', '', 'Calligraphy Floral Bangle', 'lbg.22-003.jpeg', 19000, 5, 6, 4, 1),
(13, b'1', '2024-03-17 19:39:54.815240', '', 'Gold Blessings Chain Ring', 'crg.22-001.jpeg', 40000, 5, 8, 1, 1),
(14, b'1', '2024-03-17 19:41:10.543513', '', 'Ottoman Bangle', 'cbg.20-003_2.jpeg', 40000, 4, 6, 4, 3),
(16, b'1', '2024-03-17 19:46:06.285411', '', 'Ghalia Earrings', 'leg.23-001.jpeg', 39000, 5, 7, 3, 1),
(17, b'1', '2024-03-17 19:47:17.778493', '', 'Gold Endearment Necklace', 'lng.23-006.jpeg', 35000, 5, 4, 2, 1),
(18, b'1', '2024-03-17 20:29:14.061315', '', 'Fallahy Crescent Earrings', 'ces.21-001.jpeg', 32000, 5, 5, 3, 1),
(19, b'1', '2024-03-17 20:30:08.082087', '', 'Haneya Necklace', 'lng.23-002.jpeg', 59000, 5, 4, 1, 1),
(20, b'1', '2024-05-10 19:46:14.507411', 'rna', 'rana', 'reg.PNG', 900, 5, 586, 8, 1);

-- --------------------------------------------------------

--
-- Table structure for table `spring_session`
--

CREATE TABLE `spring_session` (
  `PRIMARY_ID` char(36) NOT NULL,
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint(20) NOT NULL,
  `LAST_ACCESS_TIME` bigint(20) NOT NULL,
  `MAX_INACTIVE_INTERVAL` int(11) NOT NULL,
  `EXPIRY_TIME` bigint(20) NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `spring_session`
--

INSERT INTO `spring_session` (`PRIMARY_ID`, `SESSION_ID`, `CREATION_TIME`, `LAST_ACCESS_TIME`, `MAX_INACTIVE_INTERVAL`, `EXPIRY_TIME`, `PRINCIPAL_NAME`) VALUES
('59cee929-bde3-4c44-9ea9-bdc7c94c5aef', '5c6af598-1dcf-454d-8fb2-addd6bef13b8', 1715766841048, 1715766933696, 1800, 1715768733696, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `spring_session_attributes`
--

CREATE TABLE `spring_session_attributes` (
  `SESSION_PRIMARY_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `spring_session_attributes`
--

INSERT INTO `spring_session_attributes` (`SESSION_PRIMARY_ID`, `ATTRIBUTE_NAME`, `ATTRIBUTE_BYTES`) VALUES
('59cee929-bde3-4c44-9ea9-bdc7c94c5aef', 'id', 0xaced00057372000e6a6176612e6c616e672e4c6f6e673b8be490cc8f23df0200014a000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b0200007870000000000000006c),
('59cee929-bde3-4c44-9ea9-bdc7c94c5aef', 'username', 0xaced000574000b52616e614d6f68616d6564),
('59cee929-bde3-4c44-9ea9-bdc7c94c5aef', 'userType', 0xaced00057e72001f636f6d2e6164762e6164762e6d6f64656c2e5573657224557365725479706500000000000000001200007872000e6a6176612e6c616e672e456e756d0000000000000000120000787074000455534552);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_type` enum('ADMIN','USER') DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `password`, `user_type`, `username`) VALUES
(7, 'rana@email.com', '$2a$12$j3GtnKdx6YmMhgY0VGTx9.CuaQ/2owqb2syn4SVDH/YzH1nbmBDaq', 'USER', 'mahmoud'),
(9, 'mahmoud1@gmail.com', '$2a$12$wS7HaJPvR8gKJULnbsvXu.y2zkSMpcHVA05epB/RyFhaspKN9awRa', 'USER', 'mahmoud'),
(101, 'admin@gmail.com', '$2a$12$SddaHSPiLRSTySNteWcCT.9hpbkYv/CUnlP0pYwbL4IGNB0gNtEc.', 'ADMIN', 'admin'),
(106, 'r@email.com', '$2a$12$8erHtg9Lw5/EdsJMkFxntuiFaiHItzk5cPUo8Jw80/eROfofHvSHi', 'USER', 'rana'),
(107, 'ranoon@email.com', '$2a$12$kLusQWRw.tk.RproYcAwquG4WqDfWI65xT/Udl2gysceDpKh/EN4e', 'USER', 'ranoon'),
(108, 'rm@email.com', '$2a$12$noCq15PF31rIwEscHB/y7eeoLNzzCBCdkmhuGArOlk7JOKu0FMFcW', 'USER', 'RanaMohamed');

-- --------------------------------------------------------

--
-- Table structure for table `wishlist`
--

CREATE TABLE `wishlist` (
  `id` bigint(20) NOT NULL,
  `product_id` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `wishlist`
--

INSERT INTO `wishlist` (`id`, `product_id`, `user_id`) VALUES
(8, 9, 9),
(9, 20, 106),
(10, 19, 108);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3d704slv66tw6x5hmbm6p2x3u` (`product_id`),
  ADD KEY `FKl70asp4l4w0jmbm1tqyofho4o` (`user_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `checkout`
--
ALTER TABLE `checkout`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqnhui1ehqjc5m5p6slxjthw4e` (`product_id`),
  ADD KEY `FKiw839lhgcn33nwk20rl9bqnon` (`cart_id`);

--
-- Indexes for table `metal`
--
ALTER TABLE `metal`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`),
  ADD KEY `FKt5d17obefqjd1eh82jpxyvh8t` (`metal_id`);

--
-- Indexes for table `spring_session`
--
ALTER TABLE `spring_session`
  ADD PRIMARY KEY (`PRIMARY_ID`),
  ADD UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  ADD KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
  ADD KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`);

--
-- Indexes for table `spring_session_attributes`
--
ALTER TABLE `spring_session_attributes`
  ADD PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `wishlist`
--
ALTER TABLE `wishlist`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqchevbfw5wq0f4uqacns02rp7` (`product_id`),
  ADD KEY `FKd4r80jm8s41fgoa0xv9yy5lo8` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `checkout`
--
ALTER TABLE `checkout`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `metal`
--
ALTER TABLE `metal`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=109;

--
-- AUTO_INCREMENT for table `wishlist`
--
ALTER TABLE `wishlist`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `FK3d704slv66tw6x5hmbm6p2x3u` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FKl70asp4l4w0jmbm1tqyofho4o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `checkout`
--
ALTER TABLE `checkout`
  ADD CONSTRAINT `FKiw839lhgcn33nwk20rl9bqnon` FOREIGN KEY (`cart_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FKqnhui1ehqjc5m5p6slxjthw4e` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  ADD CONSTRAINT `FKt5d17obefqjd1eh82jpxyvh8t` FOREIGN KEY (`metal_id`) REFERENCES `metal` (`id`);

--
-- Constraints for table `spring_session_attributes`
--
ALTER TABLE `spring_session_attributes`
  ADD CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `spring_session` (`PRIMARY_ID`) ON DELETE CASCADE;

--
-- Constraints for table `wishlist`
--
ALTER TABLE `wishlist`
  ADD CONSTRAINT `FKd4r80jm8s41fgoa0xv9yy5lo8` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKqchevbfw5wq0f4uqacns02rp7` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
