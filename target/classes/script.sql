SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE `accountTable` (
    `Id` int(11) NOT NULL AUTO_INCREMENT,
    `Account_Number` varchar(15) NOT NULL,
    `Account_Type` varchar(15) NOT NULL,
    `BCode` varchar(15) NOT NULL,
    `Name` varchar(50) NOT NULL,
    `Gender` varchar(10) NOT NULL,
    `DOB` date DEFAULT NULL,
    `Address` varchar(50) NOT NULL,
    `Aadhar` varchar(12) NOT NULL,
    `Balance` double NOT NULL,
PRIMARY KEY (`Account_Number`),
UNIQUE KEY `Id` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



INSERT INTO `accountTable` (`Id`, `Account_Number`, `Account_Type`, `BCode`, `Name`, `Gender`, `DOB`, `Address`, `Aadhar`, `Balance`) VALUES
(1, 'SBI23432310001', 'Savings', 'SBI234323', 'chandan', 'M', '2018-09-06', 'xyz xyz', '234432234', 20500);



CREATE TABLE `branchtable` (
  `Id` int(11) NOT NULL,
  `Name` varchar(120) NOT NULL,
  `BCode` varchar(15) NOT NULL,
  `Address` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `branchtable` (`Id`, `Name`, `BCode`, `Address`) VALUES
(1, 'newjersy', 'SBI234323', 'xyz');



CREATE TABLE `employeetable` (
  `Id` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Branch` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `employeetable` (`Id`, `Name`, `Branch`) VALUES
(1, 'arun', 'SBI234323');

CREATE TABLE `servicetable` (
  `Date` date NOT NULL,
  `Account_Num` varchar(15) DEFAULT NULL,
  `ServiceName` varchar(100) DEFAULT NULL,
  `Description` varchar(200) DEFAULT NULL,
  `Amount` double DEFAULT NULL,
  `TransactionId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `servicetable` (`Date`, `Account_Num`, `ServiceName`, `Description`, `Amount`, `TransactionId`) VALUES
('2018-09-06', 'SBI23432310001', 'online banking', 'done', 500, 2);



CREATE TABLE `transactiontable` (
  `Id` int(11) NOT NULL,
  `Date` date NOT NULL,
  `Account_Num` varchar(15) DEFAULT NULL,
  `Transaction_Type` varchar(15) DEFAULT NULL,
  `Amount` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



INSERT INTO `transactiontable` (`Id`, `Date`, `Account_Num`, `Transaction_Type`, `Amount`) VALUES
(1, '2018-09-06', 'SBI23432310001', 'Credit', 21000),
(2, '2018-09-06', 'SBI23432310001', 'Debit', 500);


ALTER TABLE `accounttable`
  ADD PRIMARY KEY (`Id`);


ALTER TABLE `branchtable`
  ADD PRIMARY KEY (`Id`);


ALTER TABLE `employeetable`
  ADD PRIMARY KEY (`Id`);


ALTER TABLE `servicetable`
  ADD KEY `par_ind` (`TransactionId`);


ALTER TABLE `transactiontable`
  ADD PRIMARY KEY (`Id`);


ALTER TABLE `accounttable`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

ALTER TABLE `branchtable`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;


ALTER TABLE `employeetable`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;


ALTER TABLE `transactiontable`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;


ALTER TABLE `servicetable`
  ADD CONSTRAINT `fk_tranTable` FOREIGN KEY (`TransactionId`) REFERENCES `transactiontable` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

