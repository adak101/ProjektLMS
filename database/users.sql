CREATE USER 'bsurma'@'%' IDENTIFIED BY 'bsurma';
CREATE USER 'mnoga'@'%' IDENTIFIED BY 'mnoga';
CREATE USER 'sswiercz'@'%' IDENTIFIED BY 'sswiercz';
CREATE USER 'awasko'@'%' IDENTIFIED BY 'awasko';
CREATE USER 'samba'@'%' IDENTIFIED BY 'samba';

GRANT ALL PRIVILEGES ON lms.* TO 'bsurma'@'%';
GRANT ALL PRIVILEGES ON lms.* TO 'mnoga'@'%';
GRANT ALL PRIVILEGES ON lms.* TO 'sswiercz'@'%';
GRANT ALL PRIVILEGES ON lms.* TO 'awasko'@'%';
GRANT ALL PRIVILEGES ON lms.* TO 'samba'@'%';