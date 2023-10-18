INSERT INTO usuario (nome, email, senha) VALUES ('Nina Brown', 'nina@gmail.com', '$2a$10$NYFZ/8WaQ3Qb6FCs.00jce4nxX9w7AkgWVsQCG6oUwTAcZqP9Flqu');
INSERT INTO usuario (nome, email, senha) VALUES ('Leia Red', 'leia@gmail.com', '$2a$10$NYFZ/8WaQ3Qb6FCs.00jce4nxX9w7AkgWVsQCG6oUwTAcZqP9Flqu');

INSERT INTO role (role_name) VALUES ('ROLE_OPERATOR');
INSERT INTO role (role_name) VALUES ('ROLE_ADMIN');

INSERT INTO usuario_role (usuario_Id, role_Id) VALUES (1, 1);
INSERT INTO usuario_role (usuario_Id, role_Id) VALUES (2, 1);
INSERT INTO usuario_role (usuario_Id, role_Id) VALUES (2, 2);