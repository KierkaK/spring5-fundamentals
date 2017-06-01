SET MODE POSTGRESQL;

CREATE TABLE country (
  id        INT PRIMARY KEY AUTO_INCREMENT,
  name      VARCHAR(255),
  code_name VARCHAR(255)
);

-- CREATE TABLE person (
--   id         INT PRIMARY KEY AUTO_INCREMENT,
--   name       VARCHAR(100),
--   country_id INTEGER,
--
--   FOREIGN KEY (country_id) REFERENCES country (id)
-- );