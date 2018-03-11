DROP TABLE IF EXISTS quotes;
DROP TABLE IF EXISTS bot_types;
DROP TABLE IF EXISTS bots;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq
  START 100000;

CREATE TABLE users
(
  id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name       VARCHAR                 NOT NULL,
  email      VARCHAR                 NOT NULL,
  password   VARCHAR                 NOT NULL,
  registered TIMESTAMP DEFAULT now() NOT NULL,
  enabled    BOOL DEFAULT TRUE       NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx
  ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE bots
(
  id      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  user_id INTEGER                 NOT NULL,
  name    VARCHAR                 NOT NULL,
  token   VARCHAR                 NOT NULL,
  created TIMESTAMP DEFAULT now() NOT NULL,
  enabled BOOLEAN DEFAULT TRUE    NOT NULL,
  type    SMALLINT                NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX bots_token_idx
  ON bots (token);

CREATE TABLE quotes
(
  text    VARCHAR                 NOT NULL,
  bot_id  INTEGER                 NOT NULL,
  author  VARCHAR,
  created TIMESTAMP DEFAULT now() NOT NULL,
  PRIMARY KEY (bot_id, created),
  FOREIGN KEY (bot_id) REFERENCES bots (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX quotes_unique_idx
  ON quotes (bot_id, created);

CREATE TABLE bot_types
(
  bot_id INTEGER NOT NULL,
  type   VARCHAR,
  CONSTRAINT bot_types_idx UNIQUE (bot_id, type),
  FOREIGN KEY (bot_id) REFERENCES bots (id) ON DELETE CASCADE
);
