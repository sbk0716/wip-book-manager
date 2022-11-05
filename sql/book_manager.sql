CREATE TABLE user (
    id bigint NOT NULL,
    email varchar(256) UNIQUE NOT NULL,
    password varchar(128) NOT NULL,
    name varchar(32) NOT NULL,
    role_type enum('ADMIN', 'USER'),
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE book (
    id bigint NOT NULL,
    title varchar(128) NOT NULL,
    author varchar(32) NOT NULL,
    release_date date NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE rental (
    book_id bigint NOT NULL,
    user_id bigint NOT NULL,
    rental_datetime datetime NOT NULL,
    return_deadline datetime NOT NULL,
    PRIMARY KEY (book_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO book values(
    100,
    'kotlin入門',
    'コトリン太郎',
    '1950-10-01'
), (
    200,
    'java入門',
    'ジャバ太郎',
    '2005-08-29'
);

INSERT INTO user values(
    1,
    'admin@test.com',
    '$2y$05$dKLxqxq8cBj3wYGciicTdOR6zhfHiG3IEYreqyxkfFPU.Qq5w4KKS',
    '管理者',
    'ADMIN'
), (
    2,
    'user@test.com',
    '$2y$05$dKLxqxq8cBj3wYGciicTdOR6zhfHiG3IEYreqyxkfFPU.Qq5w4KKS',
    'ユーザー',
    'USER'
);
