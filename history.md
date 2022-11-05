# Set Up a MySQL Database

## docker-compose up
```sh
% ls | grep  docker-compose.yml 
docker-compose.yml
% docker-compose up -d
[+] Running 3/3
 ⠿ Network docker_default    Created                                            0.0s
 ⠿ Container docker-redis-1  Started                                            0.3s
 ⠿ Container mysql_host      Started                                            0.3s
admin@gw-mac docker % docker ps -a
CONTAINER ID   IMAGE          COMMAND                  CREATED         STATUS         PORTS                               NAMES
529a1c54113e   mysql:8.0.22   "docker-entrypoint.s…"   8 seconds ago   Up 7 seconds   0.0.0.0:3306->3306/tcp, 33060/tcp   mysql_host
ee8c3ee9b20e   redis:latest   "docker-entrypoint.s…"   8 seconds ago   Up 7 seconds   0.0.0.0:6379->6379/tcp              docker-redis-1
% 
```
## create db&table and insert data
```sh
% htpasswd -n -B pass
New password: 
Re-type new password: 
pass:$2y$05$dKLxqxq8cBj3wYGciicTdOR6zhfHiG3IEYreqyxkfFPU.Qq5w4KKS
% 
% mysql -h 127.0.0.1 --port 3306 -u root -p mysql
Enter password: 
Reading table information for completion of table and column names
You can turn off this feature to get a quicker startup with -A

Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 8
Server version: 8.0.30 Homebrew

Copyright (c) 2000, 2022, Oracle and/or its affiliates.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> CREATE DATABASE book_manager;
Query OK, 1 row affected (0.01 sec)

mysql> 
mysql> use book_manager
Database changed
mysql> 
mysql> CREATE TABLE user (
    ->     id bigint NOT NULL,
    ->     email varchar(256) UNIQUE NOT NULL,
    ->     password varchar(128) NOT NULL,
    ->     name varchar(32) NOT NULL,
    ->     role_type enum('ADMIN', 'USER'),
    ->     PRIMARY KEY (id)
    -> ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
Query OK, 0 rows affected, 1 warning (0.03 sec)

mysql> CREATE TABLE book (
    ->     id bigint NOT NULL,
    ->     title varchar(128) NOT NULL,
    ->     author varchar(32) NOT NULL,
    ->     release_date date NOT NULL,
    ->     PRIMARY KEY (id)
    -> ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
Query OK, 0 rows affected, 1 warning (0.01 sec)

mysql> CREATE TABLE rental (
    ->     book_id bigint NOT NULL,
    ->     user_id bigint NOT NULL,
    ->     rental_datetime datetime NOT NULL,
    ->     return_deadline datetime NOT NULL,
    ->     PRIMARY KEY (book_id)
    -> ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
Query OK, 0 rows affected, 1 warning (0.01 sec)

mysql> 
mysql> INSERT INTO book values(100, 'kotlin入門', 'コトリン太郎', '1950-10-01'), (200, 'java入門', 'ジャバ太郎', '2005-08-29');
Query OK, 2 rows affected (0.01 sec)
Records: 2  Duplicates: 0  Warnings: 0

mysql> 
mysql> INSERT INTO user values(1, 'admin@test.com', ':$2y$05$dKLxqxq8cBj3wYGciicTdOR6zhfHiG3IEYreqyxkfFPU.Qq5w4KKS', '管理者', 'ADMIN'), (2, 'user@test.com', ':$2y$05$dKLxqxq8cBj3wYGciicTdOR6zhfHiG3IEYreqyxkfFPU.Qq5w4KKS', 'ユーザー', 'USER');
Query OK, 2 rows affected (0.00 sec)
Records: 2  Duplicates: 0  Warnings: 0

mysql> 
mysql> exit
Bye
% 
```
