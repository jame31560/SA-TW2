-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- 主機: localhost:3306
-- 產生時間： 2019 年 06 月 01 日 23:08
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
-- 資料表結構 `transaction_detail`
--

CREATE TABLE `transaction_detail` (
  `id` int(11) NOT NULL,
  `transaction_id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `role` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 資料表的匯出資料 `transaction_detail`
--

INSERT INTO `transaction_detail` (`id`, `transaction_id`, `username`, `role`) VALUES
(9, 8, 'B10623009', 0),
(10, 8, '1234', 1),
(11, 9, '1234', 0),
(12, 9, 'B10623020', 1),
(13, 10, '1234', 0),
(14, 10, 'B10623022', 1),
(15, 11, '1234', 0),
(16, 11, '5678', 1),
(17, 12, '1234', 0),
(18, 12, 'B10623020', 1),
(19, 13, '1234', 0),
(20, 13, 'B10623020', 1);

-- --------------------------------------------------------

--
-- 資料表結構 `transaction_list`
--

CREATE TABLE `transaction_list` (
  `id` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `datetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 資料表的匯出資料 `transaction_list`
--

INSERT INTO `transaction_list` (`id`, `amount`, `datetime`, `status`) VALUES
(8, 500, '2019-05-29 01:39:35', 2),
(9, 672, '2019-05-29 01:40:14', 1),
(10, 200, '2019-05-29 01:41:04', 3),
(11, 100, '2019-06-01 14:37:15', 2),
(12, 328, '2019-06-01 14:37:56', 3),
(13, 328, '2019-06-01 23:03:57', 1);

-- --------------------------------------------------------

--
-- 資料表結構 `transaction_status`
--

CREATE TABLE `transaction_status` (
  `id` int(11) NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 資料表的匯出資料 `transaction_status`
--

INSERT INTO `transaction_status` (`id`, `description`) VALUES
(1, 'Success.'),
(2, 'Payer\'s balance is not enough.'),
(3, 'Payer reject the amount.'),
(4, 'Transaction timeout.');

-- --------------------------------------------------------

--
-- 資料表結構 `userinfo`
--

CREATE TABLE `userinfo` (
  `username` varchar(20) NOT NULL,
  `name` text NOT NULL,
  `password` text NOT NULL,
  `balance` int(11) NOT NULL DEFAULT '0',
  `phone` text NOT NULL,
  `QRcodeID` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 資料表的匯出資料 `userinfo`
--

INSERT INTO `userinfo` (`username`, `name`, `password`, `balance`, `phone`, `QRcodeID`) VALUES
('1234', '1234', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 1000, '1234', '12341234'),
('5678', '567', '97a6d21df7c51e8289ac1a8c026aaac143e15aa1957f54f42e30d8f8a85c3a55', 0, '567', '5678567'),
('B10623009', '5678', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 0, '7894561230', 'B106230097894561230'),
('B10623020', 'Amy', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 0, '09123456781', 'B1062302009123456781'),
('B10623022', 'Inky', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 250, '09123456783', 'B1062302209123456783');

--
-- 已匯出資料表的索引
--

--
-- 資料表索引 `transaction_detail`
--
ALTER TABLE `transaction_detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `transaction_id` (`transaction_id`),
  ADD KEY `username` (`username`);

--
-- 資料表索引 `transaction_list`
--
ALTER TABLE `transaction_list`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `transaction_id` (`id`),
  ADD KEY `status` (`status`);

--
-- 資料表索引 `transaction_status`
--
ALTER TABLE `transaction_status`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- 資料表索引 `userinfo`
--
ALTER TABLE `userinfo`
  ADD PRIMARY KEY (`username`),
  ADD UNIQUE KEY `username` (`username`(15));

--
-- 在匯出的資料表使用 AUTO_INCREMENT
--

--
-- 使用資料表 AUTO_INCREMENT `transaction_detail`
--
ALTER TABLE `transaction_detail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- 使用資料表 AUTO_INCREMENT `transaction_list`
--
ALTER TABLE `transaction_list`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- 使用資料表 AUTO_INCREMENT `transaction_status`
--
ALTER TABLE `transaction_status`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- 已匯出資料表的限制(Constraint)
--

--
-- 資料表的 Constraints `transaction_detail`
--
ALTER TABLE `transaction_detail`
  ADD CONSTRAINT `transaction_detail_ibfk_1` FOREIGN KEY (`transaction_id`) REFERENCES `transaction_list` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `transaction_detail_ibfk_2` FOREIGN KEY (`username`) REFERENCES `userinfo` (`username`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- 資料表的 Constraints `transaction_list`
--
ALTER TABLE `transaction_list`
  ADD CONSTRAINT `transaction_list_ibfk_1` FOREIGN KEY (`status`) REFERENCES `transaction_status` (`id`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
