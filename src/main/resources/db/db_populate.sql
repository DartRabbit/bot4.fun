DELETE FROM user_roles;
DELETE FROM quotes;
DELETE FROM bot_types;
DELETE FROM bots;
DELETE FROM users;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('Admin', 'admin@gmail.com', 'adminpass'),
  ('User', 'user@yandex.ru', 'userpass');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_ADMIN', 100000),
  ('ROLE_USER', 100000),
  ('ROLE_USER', 100001);

INSERT INTO bots (user_id, name, token, enabled, type) VALUES
  (100000, 'Шрэк', '347037495:AAGtX8QqLKNIdi8hqPTDj40nCdHDNZ_Le7w', false, 0),
  (100001, 'Шрэк II', '569944003:AAFjdvqOX11T4TfB47cO4jU6Mr4My32uZos', false, 0);