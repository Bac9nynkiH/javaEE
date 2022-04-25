create table if not exists Book
(
    isbn                text primary key,
    title               text,
    author              text
    );

INSERT INTO Book
VALUES
       ('1', 'author1', 'title1'),
       ('2', 'author2', 'title1'),
       ('3', 'author2', 'title2'),
       ('4', 'author3', 'title3');