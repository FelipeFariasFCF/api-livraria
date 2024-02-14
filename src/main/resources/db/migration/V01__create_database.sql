CREATE TABLE tag
(
    id   BIGSERIAL           NOT NULL,
    name VARCHAR(255) UNIQUE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE author
(
    id            BIGSERIAL           NOT NULL,
    name          VARCHAR(255) UNIQUE NOT NULL,
    date_of_birth DATE                NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE publisher
(
    id              BIGSERIAL           NOT NULL,
    name            VARCHAR(255) UNIQUE NOT NULL,
    foundation_year INTEGER             NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE library_user
(
    id            BIGSERIAL           NOT NULL,
    name          VARCHAR(255)        NOT NULL,
    email         VARCHAR(255) UNIQUE NOT NULL,
    date_of_birth DATE                NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE admin_user
(
    id            BIGSERIAL           NOT NULL,
    login          VARCHAR(255)        NOT NULL,
    password         VARCHAR(255) UNIQUE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE book
(
    id                 BIGSERIAL           NOT NULL,
    title              VARCHAR(255) UNIQUE NOT NULL,
    isbn               VARCHAR(255) UNIQUE NOT NULL,
    publication_year   INTEGER             NOT NULL,
    available_quantity INTEGER,
    total_quantity     INTEGER,
    author_id          int8                NOT NULL,
    publisher_id       int8                NOT NULL,
    tag_id             int8                NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_author_id FOREIGN KEY (author_id) REFERENCES author,
    CONSTRAINT fk_publisher_id FOREIGN KEY (publisher_id) REFERENCES publisher,
    CONSTRAINT fk_tag_id FOREIGN KEY (tag_id) REFERENCES tag
);

CREATE TABLE loan
(
    id                   BIGSERIAL NOT NULL,
    loan_date            DATE      NOT NULL,
    expected_return_date DATE      NOT NULL,
    actual_return_date   DATE,
    book_id              int8      NOT NULL,
    library_user_id      int8      NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_book_id FOREIGN KEY (book_id) REFERENCES book,
    CONSTRAINT fk_library_user_id FOREIGN KEY (library_user_id) REFERENCES library_user
);