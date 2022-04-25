create table if not exists Book
(
    isbn                text primary key,
    title               text,
    author              text,
    );

INSERT INTO Book
VALUES
       ('1', 'title1', 'author1'),
       ('2', 'title1', 'author2'),
       ('3', 'title2', 'author2'),
       ('4', 'title3', 'author3');