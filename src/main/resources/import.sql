INSERT INTO roles (name) values ("ROLE_ADMIN");
INSERT INTO roles (name) values ("ROLE_USUARIO");

INSERT INTO usuarios (username, password,enabled) VALUES ("usuario","$2a$10$uljO2MjDHGgJzb5qYWHnJepWVqWhW8kZSWtYPK1Yoz5GshoUa2imW",true);
INSERT INTO usuarios (username, password,enabled) VALUES ("admin","$2a$10$uljO2MjDHGgJzb5qYWHnJepWVqWhW8kZSWtYPK1Yoz5GshoUa2imW",true);

INSERT INTO usuario_roles (usuario_id,role_id) VALUES (1,1)
INSERT INTO usuario_roles (usuario_id,role_id) VALUES (2,1)
INSERT INTO usuario_roles (usuario_id,role_id) VALUES (2,2)

