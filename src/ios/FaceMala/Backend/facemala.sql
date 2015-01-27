-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 27-Jan-2015 às 17:41
-- Versão do servidor: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `facemala`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_category`
--

CREATE TABLE IF NOT EXISTS `tbl_category` (
`id` int(11) NOT NULL,
  `name` varchar(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tbl_category`
--

INSERT INTO `tbl_category` (`id`, `name`) VALUES
(1, 'categoria1'),
(2, 'categoria2'),
(3, 'categoria3');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_posts`
--

CREATE TABLE IF NOT EXISTS `tbl_posts` (
`id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `caption` varchar(50) NOT NULL,
  `description` varchar(180) NOT NULL,
  `link` varchar(200) NOT NULL,
  `picture` varchar(200) NOT NULL,
  `category_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tbl_posts`
--

INSERT INTO `tbl_posts` (`id`, `name`, `caption`, `description`, `link`, `picture`, `category_id`) VALUES
(1, 'Auto post configurado', 'campo titulo', 'descricao de conteudo', 'http://mobile.dc.ufscar.br', 'http://2.bp.blogspot.com/-tZrIhMnuQU4/Upif94mFotI/AAAAAAAAAA0/EqjpDFje-QQ/s1600/Memcomputa%C3%A7%C3%A3o+f%C3%ADsica+ca%C3%B3tica+abre+caminho+para+computa%C3%A7%C3%A3o+cerebral.jpg', 1),
(2, 'post2', 'caption2', 'desc2', 'mobile.ufscar.br', 'http://mobile.dc.ufscar.br/img/intel.jpg', 2),
(3, 'post3', 'caption3', 'desc3', 'mobile.ufscar.br', 'http://mobile.dc.ufscar.br/img/intel.jpg', 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_user_posts`
--

CREATE TABLE IF NOT EXISTS `tbl_user_posts` (
`id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tbl_user_posts`
--

INSERT INTO `tbl_user_posts` (`id`, `post_id`, `user_id`) VALUES
(1, 2, 332518126920668);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_user_preference`
--

CREATE TABLE IF NOT EXISTS `tbl_user_preference` (
`id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `fb_id` bigint(20) NOT NULL,
  `status` int(11) NOT NULL,
  `gender` int(11) NOT NULL,
  `age` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tbl_user_preference`
--

INSERT INTO `tbl_user_preference` (`id`, `category_id`, `fb_id`, `status`, `gender`, `age`) VALUES
(1, 3, 111, 1, 1, 21),
(2, 1, 111, 1, 1, 1),
(3, 2, 111, 1, 1, 21),
(4, 1, 222, 1, 22, 21),
(6, 1, 332518126920668, 1, 1, 21),
(7, 2, 332518126920668, 1, 1, 21);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_category`
--
ALTER TABLE `tbl_category`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_posts`
--
ALTER TABLE `tbl_posts`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `category` (`category_id`);

--
-- Indexes for table `tbl_user_posts`
--
ALTER TABLE `tbl_user_posts`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_user_preference`
--
ALTER TABLE `tbl_user_preference`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_category`
--
ALTER TABLE `tbl_category`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `tbl_posts`
--
ALTER TABLE `tbl_posts`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `tbl_user_posts`
--
ALTER TABLE `tbl_user_posts`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `tbl_user_preference`
--
ALTER TABLE `tbl_user_preference`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
