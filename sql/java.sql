-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- 主機: localhost:3306
-- 產生時間： 2019 年 05 月 18 日 22:29
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
-- 資料表結構 `userinfo`
--
-- 建立時間: 2019 年 05 月 18 日 13:20
--

CREATE TABLE `userinfo` (
  `u_id` int(11) NOT NULL,
  `u_name` text NOT NULL,
  `u_username` text NOT NULL,
  `u_password` text NOT NULL,
  `u_balance` int(11) NOT NULL DEFAULT '0',
  `u_phone` text NOT NULL,
  `u_QRcodeID` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 資料表的匯出資料 `userinfo`
--

INSERT INTO `userinfo` (`u_id`, `u_name`, `u_username`, `u_password`, `u_balance`, `u_phone`, `u_QRcodeID`) VALUES
(1, 'Amy', 'B10623020', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 1000, '09123456781', 'B1062302009123456781'),
(2, 'James', 'B10623021', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 1000, '09123456782', 'B1062302109123456782'),
(3, 'Inky', 'B10623022', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 1000, '09123456783', 'B1062302209123456783'),
(9, '1234', '1234', 'db2e7f1bd5ab9968ae76199b7cc74795ca7404d5a08d78567715ce532f9d2669', 0, '1234', '12341234');

--
-- 已匯出資料表的索引
--

--
-- 資料表索引 `userinfo`
--
ALTER TABLE `userinfo`
  ADD PRIMARY KEY (`u_id`),
  ADD UNIQUE KEY `u_id` (`u_id`),
  ADD UNIQUE KEY `username` (`u_username`(15));

--
-- 在匯出的資料表使用 AUTO_INCREMENT
--

--
-- 使用資料表 AUTO_INCREMENT `userinfo`
--
ALTER TABLE `userinfo`
  MODIFY `u_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
