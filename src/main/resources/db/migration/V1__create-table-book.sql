CREATE TABLE book (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,
      name VARCHAR(255),
      author VARCHAR(255),
      description VARCHAR(255),
      author_id BIGINT,
      number_of_pages INT
);
