-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- 主機: localhost:3306
-- 產生時間： 2019 年 06 月 03 日 00:15
-- 伺服器版本: 5.7.26-0ubuntu0.18.04.1
-- PHP 版本： 7.2.17-0ubuntu0.18.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `java`
--

-- --------------------------------------------------------

--
-- 資料表結構 `Role`
--

CREATE TABLE `Role` (
  `role` int(11) NOT NULL,
  `roleDescription` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 資料表的匯出資料 `Role`
--

INSERT INTO `Role` (`role`, `roleDescription`) VALUES
(1, 'Payee'),
(2, 'Payer');

-- --------------------------------------------------------

--
-- 資料表結構 `Transaction`
--

CREATE TABLE `Transaction` (
  `TransactionId` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `datetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 資料表的匯出資料 `Transaction`
--

INSERT INTO `Transaction` (`TransactionId`, `amount`, `datetime`, `status`) VALUES
(8, 500, '2019-05-29 01:39:35', 2),
(9, 672, '2019-05-29 01:40:14', 1),
(10, 200, '2019-05-29 01:41:04', 3),
(11, 100, '2019-06-01 14:37:15', 2),
(12, 328, '2019-06-01 14:37:56', 3),
(13, 328, '2019-06-01 23:03:57', 1);

-- --------------------------------------------------------

--
-- 資料表結構 `TransactionDetail`
--

CREATE TABLE `TransactionDetail` (
  `TransactionDetailId` int(11) NOT NULL,
  `TransactionId` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `role` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 資料表的匯出資料 `TransactionDetail`
--

INSERT INTO `TransactionDetail` (`TransactionDetailId`, `TransactionId`, `username`, `role`) VALUES
(9, 8, 'B10623009', 1),
(10, 8, '1234', 2),
(11, 9, '1234', 1),
(12, 9, 'B10623020', 2),
(13, 10, '1234', 1),
(14, 10, 'B10623022', 2),
(15, 11, '1234', 1),
(16, 11, '5678', 2),
(17, 12, '1234', 1),
(18, 12, 'B10623020', 2),
(19, 13, '1234', 1),
(20, 13, 'B10623020', 2);

-- --------------------------------------------------------

--
-- 資料表結構 `TransactionStatus`
--

CREATE TABLE `TransactionStatus` (
  `status` int(11) NOT NULL,
  `statusDescription` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 資料表的匯出資料 `TransactionStatus`
--

INSERT INTO `TransactionStatus` (`status`, `statusDescription`) VALUES
(1, 'Success.'),
(2, 'Payer\'s balance is not enough.'),
(3, 'Payer reject the amount.'),
(4, 'Transaction timeout.');

-- --------------------------------------------------------

--
-- 資料表結構 `User`
--

CREATE TABLE `User` (
  `username` varchar(20) NOT NULL,
  `name` text NOT NULL,
  `password` text NOT NULL,
  `balance` int(11) NOT NULL DEFAULT '0',
  `phone` text NOT NULL,
  `QRcodeID` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 資料表的匯出資料 `User`
--

INSERT INTO `User` (`username`, `name`, `password`, `balance`, `phone`, `QRcodeID`) VALUES
('12333', '1222', '9b871512327c09ce91dd649b3f96a63b7408ef267c8cc5710114e629730cb61f', 0, '1222', '123331222'),
('1234', '1234', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 1000, '1234', '12341234'),
('5678', '567', '97a6d21df7c51e8289ac1a8c026aaac143e15aa1957f54f42e30d8f8a85c3a55', 0, '567', '5678567'),
('B10623009', '5678', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 0, '7894561230', 'B106230097894561230'),
('B10623020', 'Amy', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 0, '09123456781', 'B1062302009123456781'),
('B10623022', 'Inky', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 250, '09123456783', 'B1062302209123456783');

--
-- 已匯出資料表的索引
--

--
-- 資料表索引 `Role`
--
ALTER TABLE `Role`
  ADD PRIMARY KEY (`role`),
  ADD UNIQUE KEY `rool` (`role`);

--
-- 資料表索引 `Transaction`
--
ALTER TABLE `Transaction`
  ADD PRIMARY KEY (`TransactionId`),
  ADD UNIQUE KEY `transaction_id` (`TransactionId`),
  ADD KEY `status` (`status`);

--
-- 資料表索引 `TransactionDetail`
--
ALTER TABLE `TransactionDetail`
  ADD PRIMARY KEY (`TransactionDetailId`),
  ADD KEY `transaction_id` (`TransactionId`),
  ADD KEY `username` (`username`),
  ADD KEY `role` (`role`);

--
-- 資料表索引 `TransactionStatus`
--
ALTER TABLE `TransactionStatus`
  ADD PRIMARY KEY (`status`),
  ADD UNIQUE KEY `id` (`status`);

--
-- 資料表索引 `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`username`),
  ADD UNIQUE KEY `username` (`username`(15));

--
-- 在匯出的資料表使用 AUTO_INCREMENT
--

--
-- 使用資料表 AUTO_INCREMENT `Role`
--
ALTER TABLE `Role`
  MODIFY `role` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- 使用資料表 AUTO_INCREMENT `Transaction`
--
ALTER TABLE `Transaction`
  MODIFY `TransactionId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- 使用資料表 AUTO_INCREMENT `TransactionDetail`
--
ALTER TABLE `TransactionDetail`
  MODIFY `TransactionDetailId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- 使用資料表 AUTO_INCREMENT `TransactionStatus`
--
ALTER TABLE `TransactionStatus`
  MODIFY `status` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- 已匯出資料表的限制(Constraint)
--

--
-- 資料表的 Constraints `Transaction`
--
ALTER TABLE `Transaction`
  ADD CONSTRAINT `Transaction_ibfk_1` FOREIGN KEY (`status`) REFERENCES `TransactionStatus` (`status`) ON UPDATE CASCADE;

--
-- 資料表的 Constraints `TransactionDetail`
--
ALTER TABLE `TransactionDetail`
  ADD CONSTRAINT `TransactionDetail_ibfk_1` FOREIGN KEY (`TransactionId`) REFERENCES `Transaction` (`TransactionId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `TransactionDetail_ibfk_2` FOREIGN KEY (`username`) REFERENCES `User` (`username`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `TransactionDetail_ibfk_3` FOREIGN KEY (`role`) REFERENCES `Role` (`role`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
