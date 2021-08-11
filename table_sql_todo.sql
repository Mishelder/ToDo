DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS task;


CREATE TABLE client
(
    id       SERIAL PRIMARY KEY,
    login    VARCHAR(128) NOT NULL UNIQUE,
    email    VARCHAR(128) NOT NULL UNIQUE,
    password VARCHAR(32)  NOT NULL
);

CREATE TABLE task
(
    id        SERIAL PRIMARY KEY,
    client_id INTEGER REFERENCES client (id),
    task_name VARCHAR(512) NOT NULL,
    done      BOOLEAN      NOT NULL,
    date      DATE         NOT NULL
);


INSERT INTO client (login, email, password)
VALUES ('killer888', 'example1@mail.com', 'qwerty'),
       ('goblin','example2@mail.com','123'),
       ('HellenGeller','example3@mail.com','4321');

INSERT INTO task (client_id, task_name, done, date) VALUES
(2,'buy the milk',true,'2021-07-12'),
(3,'buy the meal',false,'2021-07-10'),
(1,'go to the cinema',true,'2021-07-09'),
(3,'do stretching',false,'2021-07-10'),
(2,'buy the boat',true,'2021-07-12'),
(1,'hang out wth friends',false,'2021-07-11'),
(1,'buy the buckwheat',true,'2021-07-11'),
(3,'buy the sugar',false,'2021-07-10'),
(2,'buy the milk',false,'2021-07-12'),
(2,'buy the egg',true,'2021-07-12'),
(2,'buy the dog',false,'2021-07-12'),
(2,'buy the salt',true,'2021-07-12'),
(2,'buy the milk',false,'2021-07-12');

