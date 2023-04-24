-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 24, 2023 at 02:03 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `shopping_assistant`
--

-- --------------------------------------------------------

--
-- Table structure for table `favourites`
--

CREATE TABLE `favourites` (
  `user_id` int(11) NOT NULL,
  `meal_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `favourites`
--

INSERT INTO `favourites` (`user_id`, `meal_id`) VALUES
(12, 9),
(17, 1),
(17, 2),
(17, 3);

-- --------------------------------------------------------

--
-- Table structure for table `mealproducts`
--

CREATE TABLE `mealproducts` (
  `meal_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mealproducts`
--

INSERT INTO `mealproducts` (`meal_id`, `product_id`) VALUES
(1, 5),
(1, 6),
(1, 9),
(2, 4),
(2, 11),
(2, 12),
(2, 15),
(3, 3),
(3, 6),
(3, 7),
(3, 9),
(3, 11),
(3, 13),
(3, 16),
(4, 4),
(4, 5),
(4, 6),
(4, 7),
(4, 9),
(4, 10),
(4, 11),
(5, 1),
(5, 2),
(5, 8),
(5, 14),
(6, 1),
(6, 19),
(6, 20),
(6, 21),
(6, 25),
(6, 27),
(6, 29),
(6, 33),
(6, 36),
(6, 37),
(7, 6),
(7, 17),
(7, 18),
(7, 30),
(8, 9),
(8, 19),
(8, 20),
(8, 23),
(8, 25),
(8, 33),
(8, 38),
(9, 19),
(9, 24),
(9, 28),
(9, 29),
(9, 31),
(9, 34),
(9, 35),
(10, 19),
(10, 20),
(10, 22),
(10, 24),
(10, 25),
(10, 26),
(10, 32),
(10, 33),
(10, 35),
(10, 36),
(11, 18),
(11, 19),
(11, 20),
(11, 24),
(11, 25),
(11, 28),
(11, 33),
(11, 49),
(11, 50),
(11, 53),
(12, 42),
(12, 44),
(12, 51),
(13, 24),
(13, 25),
(13, 28),
(13, 33),
(13, 36),
(13, 39),
(13, 40),
(13, 41),
(13, 46),
(13, 48),
(13, 52),
(13, 53),
(14, 21),
(14, 24),
(14, 33),
(14, 43),
(14, 45),
(14, 46),
(14, 47),
(14, 53),
(14, 54);

-- --------------------------------------------------------

--
-- Table structure for table `meals`
--

CREATE TABLE `meals` (
  `meal_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `mealImage` varchar(50) NOT NULL,
  `mealCategory` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `meals`
--

INSERT INTO `meals` (`meal_id`, `name`, `mealImage`, `mealCategory`) VALUES
(1, 'Soft Boiled Eggs & Toast', 'eggsandtoastimage', 'Breakfast'),
(2, 'Porridge Oats with mixed Berries', 'porridgeimage', 'Breakfast'),
(3, 'Cinnamon & Raspberry Pancakes', 'pancakesimage', 'Breakfast'),
(4, 'French Toast with fresh Berries', 'frenchtoastimage', 'Breakfast'),
(5, 'Salmon Breakfast Bagel', 'salmonbagelimage', 'Breakfast'),
(6, 'Spicy Chicken Wraps', 'wrapsimage', 'Lunch'),
(7, 'Jacket Potato', 'jacketpotatoimage', 'Lunch'),
(8, 'Chicken and Rice', 'chickenandricedishimage', 'Lunch'),
(9, 'Chicken Pad Thai', 'padthaiimage', 'Lunch'),
(10, 'Chicken Salad', 'chickensaladimage', 'Lunch'),
(11, 'Chicken Pasta Bake', 'pastabakeimage', 'Dinner'),
(12, 'Fish, Chips & Mushy Peas', 'fishandchipsimage', 'Dinner'),
(13, 'Spaghetti Bolognese', 'spagbolimage', 'Dinner'),
(14, 'Lamb Curry', 'lambcurryimage', 'Dinner');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `product_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `productImage` varchar(50) NOT NULL,
  `aisle` int(11) NOT NULL,
  `row` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`product_id`, `name`, `productImage`, `aisle`, `row`) VALUES
(1, 'Avocado', 'avocadoimage', 0, 0),
(2, 'Bagel', 'bagelimage', 1, 2),
(3, 'Baking Powder', 'bakingpowderimage', 1, 1),
(4, 'Blueberries', 'blueberriesimage', 0, 2),
(5, 'Bread', 'breadimage', 1, 2),
(6, 'Butter', 'butterimage', 5, 2),
(7, 'Cinnamon Powder', 'cinnamonpowderimage', 1, 0),
(8, 'Cream Cheese', 'creamcheeseimage', 5, 1),
(9, 'Eggs', 'eggsimage', 1, 0),
(10, 'Icing Sugar', 'icingsugarimage', 1, 1),
(11, 'Milk', 'milkimage', 5, 0),
(12, 'Oats', 'oatsimage', 1, 0),
(13, 'Raspberries', 'raspberriesimage', 0, 2),
(14, 'Salmon', 'salmonimage', 6, 2),
(15, 'Strawberries', 'strawberriesimage', 0, 2),
(16, 'Sugar', 'sugarimage', 1, 1),
(17, 'Beans', 'beansimage', 2, 2),
(18, 'Cheese', 'cheeseimage', 5, 1),
(19, 'Chicken Breast', 'chickenbreastimage', 6, 1),
(20, 'Chilli Powder', 'chillipowderimage', 4, 2),
(21, 'Coriander', 'corianderimage', 0, 0),
(22, 'Cucumber', 'cucumberimage', 0, 1),
(23, 'Frozen Veg', 'frozenvegimage', 7, 0),
(24, 'Garlic', 'garlicimage', 0, 0),
(25, 'Ground Pepper', 'groundpepperimage', 4, 1),
(26, 'Lettuce', 'lettuceimage', 0, 1),
(27, 'Lime', 'limeimage', 0, 2),
(28, 'Onions', 'onionsimage', 0, 0),
(29, 'Peppers', 'peppersimage', 0, 1),
(30, 'Potato', 'potatoimage', 0, 0),
(31, 'Rice Noodles', 'ricenoodlesimage', 3, 2),
(32, 'Rocket', 'rocketimage', 0, 1),
(33, 'Salt', 'saltimage', 4, 1),
(34, 'Soy Sauce', 'soysauceimage', 4, 0),
(35, 'Sweet Chilli Sauce', 'sweetchillisauceimage', 4, 0),
(36, 'Tomatoes', 'tomatoesimage', 0, 2),
(37, 'Tortilla Wraps', 'tortillawrapsimage', 1, 2),
(38, 'White Rice', 'whitericeimage', 3, 0),
(39, 'Basil', 'basilimage', 0, 0),
(40, 'Bay Leaves', 'bayleavesimage', 4, 0),
(41, 'Carrots', 'carrotsimage', 0, 1),
(42, 'Chips', 'chipsimage', 7, 2),
(43, 'Cinnamon Stick', 'cinnamonstickimage', 4, 1),
(44, 'Cod Fish', 'codimage', 7, 1),
(45, 'Cumin Powder', 'cuminpowderimage', 4, 2),
(46, 'Green Chillies', 'greenchilliesimage', 0, 0),
(47, 'Lamb Meat', 'lambmeatimage', 6, 0),
(48, 'Lamb Mince', 'mincemeatimage', 6, 0),
(49, 'Parseley', 'parseleyimage', 0, 0),
(50, 'Pasta', 'pastaimage', 3, 1),
(51, 'Peas', 'peasimage', 7, 0),
(52, 'Spaghetti', 'spaghettiimage', 3, 1),
(53, 'Tinned Tomatoes', 'tinnedtomatoesimage', 2, 1),
(54, 'Turmeric Powder', 'turmericpowder', 4, 2);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `email`, `password`, `firstName`, `lastName`) VALUES
(1, 'test', 'test', '$2y$10$VNF0aK4cXJpbu9oemdeK7OkA3S7dHZeETQkR6L5YwjMqMySl8bvfy', 'test', 'test'),
(4, 'Ukhan123', 'ukhan@outlook.com', '$2y$10$sUUGe9kKz74D9WL1c7uOX.ZKgSR1KE9..5BiuiJdCI74/VZt912E2', 'Umar', 'Khan'),
(5, 'Taz12', 'Taz@hotmail.com', '$2y$10$aKRu48tGLTCnTqaK9Iwn3.QXUyrx18IB/6.H2tliLiHdPTUhazHRO', 'Taz', 'Singh'),
(8, 'Tony28', 'tony28@outlook.com', '$2y$10$JeC3wIAIAi/lKDimtP81wONmFbtzHb61.Wo07CigN8oMfO0YhjxTO', 'Tony', 'Williams'),
(9, 'Taran22', 'Taran11@gmail.com', '$2y$10$sXhs/7oLEmtM4C2PKRJf4OWccWyJLW0kjTR3jhdyQNnjWklxcdIWW', 'Taran', 'Singh'),
(10, 'Bob21', 'Bob@outlook.com', '$2y$10$DR6EpXt4aWpnk8WlDm5cGeTLF0JjI3pQZ56ugm6N.sQ1Fcp3eTqY6', 'Bob', 'Davies'),
(11, 'Haymaker11', 'dhaye@outlook.com', '$2y$10$0MTIjfr.yHwgadRyJrUwXudi15pQClGPx3vVIFgj5Vs7jLTK50PNy', 'David', 'Haye'),
(12, 'Sanj09', 'sanj123@outlook.com', '$2y$10$TXz8grg7NkoehHDOU4degeiUrNoB4yOz0Qc9bc8hRV5k5U8L7lH0i', 'Sanj', 'Singh'),
(13, 'Taran111', 'tarans11@gmail.com', '$2y$10$t.gOCujYEcmfWgGhnmdNguH3xuoDCoGoZ7V5pcCGDlaHXEeNrY1aa', 'Taran', 'Singh'),
(14, 'Dev08', 'devrajsingh08@outlook.com', '$2y$10$6RsQgpuFa3IqOZKlt6tLWul0TWUeJEWDMVL4i2dnyezcYt8.zjKgO', 'Devraj', 'Singh'),
(15, 'Tony1', 'tony1@outlook.com', '$2y$10$Jn/G4YK4laObxW2//jPZC.0errYUW/w.Hz8ozqcJ1VOEjOKlRjHX2', 'Tony', 'David'),
(16, 'Qilla11', 'qillah@outlook.com', '$2y$10$07SmfdNKIx6Z50WTBmHO6eIdTISimhYSB4vi9TSH5dKIeEXrwU4T.', 'Qasim', 'Hussain'),
(17, 'taz11', 'taz11@outlook.com', '$2y$10$4vs2EV6l8.2q8CkTw/iAeuLBXXiwUMRDHyeutr6jW6EB9nQKnhURi', 'Taz', 'Singh'),
(18, 'newUser1', 'user1@outlook.com', '$2y$10$YTl3x1GHAYdUwppaUXkuEuChA24NLy61CX/VfOjC/8kDcjB.fsLJy', 'newUser', 'User'),
(19, 'Qhuss02', 'qas.123@outlook.com', '$2y$10$5JYHhLX4TjH9DdNO9IABmOzyYC7RnJzDRgqWSXC24bnpcAtiVXT/G', 'Qas', 'Hussain');

-- --------------------------------------------------------

--
-- Table structure for table `weeklyplanner`
--

CREATE TABLE `weeklyplanner` (
  `user_id` int(11) NOT NULL,
  `meal_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `mealType` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `weeklyplanner`
--

INSERT INTO `weeklyplanner` (`user_id`, `meal_id`, `date`, `mealType`) VALUES
(12, 1, '2023-03-09', 'Breakfast'),
(12, 6, '2023-03-09', 'Lunch'),
(12, 11, '2023-03-09', 'Dinner'),
(12, 1, '2023-03-10', 'Breakfast'),
(12, 6, '2023-03-10', 'Lunch'),
(12, 11, '2023-03-10', 'Dinner'),
(12, 1, '2023-03-11', 'Breakfast'),
(12, 6, '2023-03-11', 'Lunch'),
(12, 14, '2023-03-11', 'Dinner'),
(12, 1, '2023-03-12', 'Breakfast'),
(12, 7, '2023-03-12', 'Lunch'),
(12, 13, '2023-03-12', 'Dinner'),
(12, 1, '2023-03-13', 'Breakfast'),
(12, 10, '2023-03-13', 'Lunch'),
(12, 12, '2023-03-13', 'Dinner'),
(12, 2, '2023-03-14', 'Breakfast'),
(12, 9, '2023-03-14', 'Lunch'),
(12, 11, '2023-03-14', 'Dinner'),
(12, 5, '2023-03-15', 'Breakfast'),
(12, 10, '2023-03-15', 'Lunch'),
(12, 11, '2023-03-15', 'Dinner'),
(12, 3, '2023-03-16', 'Breakfast'),
(12, 6, '2023-03-16', 'Lunch'),
(12, 14, '2023-03-16', 'Dinner'),
(12, 2, '2023-03-17', 'Breakfast'),
(12, 9, '2023-03-17', 'Lunch'),
(12, 11, '2023-03-17', 'Dinner'),
(14, 1, '2023-03-11', 'Breakfast'),
(14, 6, '2023-03-11', 'Lunch'),
(14, 13, '2023-03-11', 'Dinner'),
(14, 1, '2023-03-12', 'Breakfast'),
(14, 6, '2023-03-12', 'Lunch'),
(14, 14, '2023-03-12', 'Dinner'),
(14, 2, '2023-03-13', 'Breakfast'),
(14, 7, '2023-03-13', 'Lunch'),
(14, 13, '2023-03-13', 'Dinner'),
(14, 1, '2023-03-14', 'Breakfast'),
(14, 9, '2023-03-14', 'Lunch'),
(14, 14, '2023-03-14', 'Dinner'),
(14, 1, '2023-03-15', 'Breakfast'),
(14, 8, '2023-03-15', 'Lunch'),
(14, 14, '2023-03-15', 'Dinner'),
(14, 1, '2023-03-16', 'Breakfast'),
(14, 7, '2023-03-16', 'Lunch'),
(14, 12, '2023-03-16', 'Dinner'),
(14, 1, '2023-03-17', 'Breakfast'),
(14, 7, '2023-03-17', 'Lunch'),
(14, 13, '2023-03-17', 'Dinner'),
(15, 1, '2023-03-11', 'Breakfast'),
(15, 6, '2023-03-11', 'Lunch'),
(15, 11, '2023-03-11', 'Dinner'),
(15, 1, '2023-03-12', 'Breakfast'),
(15, 6, '2023-03-12', 'Lunch'),
(15, 11, '2023-03-12', 'Dinner'),
(15, 1, '2023-03-13', 'Breakfast'),
(15, 6, '2023-03-13', 'Lunch'),
(15, 11, '2023-03-13', 'Dinner'),
(15, 1, '2023-03-14', 'Breakfast'),
(15, 6, '2023-03-14', 'Lunch'),
(15, 11, '2023-03-14', 'Dinner'),
(15, 1, '2023-03-15', 'Breakfast'),
(15, 6, '2023-03-15', 'Lunch'),
(15, 11, '2023-03-15', 'Dinner'),
(15, 1, '2023-03-16', 'Breakfast'),
(15, 6, '2023-03-16', 'Lunch'),
(15, 11, '2023-03-16', 'Dinner'),
(15, 1, '2023-03-17', 'Breakfast'),
(15, 6, '2023-03-17', 'Lunch'),
(15, 11, '2023-03-17', 'Dinner'),
(17, 1, '2023-03-12', 'Breakfast'),
(17, 8, '2023-03-12', 'Lunch'),
(17, 14, '2023-03-12', 'Dinner'),
(17, 2, '2023-03-13', 'Breakfast'),
(17, 7, '2023-03-13', 'Lunch'),
(17, 11, '2023-03-13', 'Dinner'),
(17, 4, '2023-03-14', 'Breakfast'),
(17, 9, '2023-03-14', 'Lunch'),
(17, 14, '2023-03-14', 'Dinner'),
(17, 2, '2023-03-15', 'Breakfast'),
(17, 8, '2023-03-15', 'Lunch'),
(17, 11, '2023-03-15', 'Dinner'),
(17, 2, '2023-03-16', 'Breakfast'),
(17, 9, '2023-03-16', 'Lunch'),
(17, 11, '2023-03-16', 'Dinner'),
(17, 3, '2023-03-17', 'Breakfast'),
(17, 8, '2023-03-17', 'Lunch'),
(17, 13, '2023-03-17', 'Dinner'),
(17, 4, '2023-03-18', 'Breakfast'),
(17, 6, '2023-03-18', 'Lunch'),
(17, 12, '2023-03-18', 'Dinner'),
(18, 4, '2023-03-22', 'Breakfast'),
(18, 9, '2023-03-22', 'Lunch'),
(18, 13, '2023-03-22', 'Dinner'),
(18, 4, '2023-03-23', 'Breakfast'),
(18, 7, '2023-03-23', 'Lunch'),
(18, 11, '2023-03-23', 'Dinner'),
(18, 2, '2023-03-24', 'Breakfast'),
(18, 7, '2023-03-24', 'Lunch'),
(18, 13, '2023-03-24', 'Dinner'),
(18, 1, '2023-03-25', 'Breakfast'),
(18, 7, '2023-03-25', 'Lunch'),
(18, 13, '2023-03-25', 'Dinner'),
(18, 2, '2023-03-26', 'Breakfast'),
(18, 6, '2023-03-26', 'Lunch'),
(18, 13, '2023-03-26', 'Dinner'),
(18, 2, '2023-03-27', 'Breakfast'),
(18, 9, '2023-03-27', 'Lunch'),
(18, 13, '2023-03-27', 'Dinner'),
(18, 1, '2023-03-28', 'Breakfast'),
(18, 7, '2023-03-28', 'Lunch'),
(18, 14, '2023-03-28', 'Dinner'),
(19, 1, '2023-03-23', 'Breakfast'),
(19, 8, '2023-03-23', 'Lunch'),
(19, 14, '2023-03-23', 'Dinner'),
(19, 4, '2023-03-24', 'Breakfast'),
(19, 6, '2023-03-24', 'Lunch'),
(19, 14, '2023-03-24', 'Dinner'),
(19, 5, '2023-03-25', 'Breakfast'),
(19, 10, '2023-03-25', 'Lunch'),
(19, 14, '2023-03-25', 'Dinner'),
(19, 5, '2023-03-26', 'Breakfast'),
(19, 10, '2023-03-26', 'Lunch'),
(19, 13, '2023-03-26', 'Dinner'),
(19, 3, '2023-03-27', 'Breakfast'),
(19, 10, '2023-03-27', 'Lunch'),
(19, 14, '2023-03-27', 'Dinner'),
(19, 1, '2023-03-28', 'Breakfast'),
(19, 6, '2023-03-28', 'Lunch'),
(19, 11, '2023-03-28', 'Dinner'),
(19, 3, '2023-03-29', 'Breakfast'),
(19, 10, '2023-03-29', 'Lunch'),
(19, 14, '2023-03-29', 'Dinner');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `favourites`
--
ALTER TABLE `favourites`
  ADD KEY `user_id` (`user_id`,`meal_id`),
  ADD KEY `meal_id` (`meal_id`);

--
-- Indexes for table `mealproducts`
--
ALTER TABLE `mealproducts`
  ADD KEY `meal_id` (`meal_id`,`product_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `meals`
--
ALTER TABLE `meals`
  ADD PRIMARY KEY (`meal_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `weeklyplanner`
--
ALTER TABLE `weeklyplanner`
  ADD KEY `user_id` (`user_id`,`meal_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `meals`
--
ALTER TABLE `meals`
  MODIFY `meal_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `favourites`
--
ALTER TABLE `favourites`
  ADD CONSTRAINT `favourites_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `favourites_ibfk_2` FOREIGN KEY (`meal_id`) REFERENCES `meals` (`meal_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `mealproducts`
--
ALTER TABLE `mealproducts`
  ADD CONSTRAINT `mealproducts_ibfk_1` FOREIGN KEY (`meal_id`) REFERENCES `meals` (`meal_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `mealproducts_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `weeklyplanner`
--
ALTER TABLE `weeklyplanner`
  ADD CONSTRAINT `weeklyplanner_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `weeklyplanner_ibfk_2` FOREIGN KEY (`meal_id`) REFERENCES `meals` (`meal_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
