CREATE TABLE motivos (
                         id INT NOT NULL,
                         descricao VARCHAR(75) DEFAULT NULL
);

CREATE TABLE cor (
                     id INT NOT NULL,
                     nomeclatura VARCHAR(75) DEFAULT NULL
);

CREATE TABLE sexo (
                      id INT NOT NULL,
                      nomeclatura VARCHAR(75) DEFAULT NULL
);

CREATE TABLE estados (
                         id int NOT NULL,
                         nome varchar(75) DEFAULT NULL,
                         uf varchar(5) DEFAULT NULL
);

CREATE TABLE cidades (
                         id int NOT NULL,
                         nome varchar(120) DEFAULT NULL,
                         id_estado int DEFAULT NULL
);

create table cidadao_motivo (
                                id int not null,
                                cidadao_id int not null,
                                motivo_id int not null
);

create table cidadao (
                         id int not null,
                         nome varchar(255) not null,
                         data_nascimento date not null,
                         id_sexo int not null,
                         id_cor int not null,
                         id_cidade int not null
);

ALTER TABLE cidades
    ADD PRIMARY KEY (id);

ALTER TABLE estados
    ADD PRIMARY KEY (id);

ALTER TABLE motivos
    ADD PRIMARY KEY (id);

ALTER TABLE cidadao_motivo
    ADD PRIMARY KEY (id);

ALTER TABLE sexo
    ADD PRIMARY KEY (id);

ALTER TABLE cor
    ADD PRIMARY KEY (id);

ALTER TABLE cidadao
    ADD PRIMARY KEY (id);

ALTER TABLE cidades
    MODIFY COLUMN id int NOT NULL AUTO_INCREMENT;

ALTER TABLE estados
    MODIFY COLUMN id int NOT NULL AUTO_INCREMENT;

ALTER TABLE cidades
    ADD FOREIGN KEY (id_estado)
        REFERENCES estados (id);

ALTER TABLE cidadao_motivo
    ADD FOREIGN KEY (motivo_id)
        REFERENCES motivos (id);

ALTER TABLE cidadao
    ADD FOREIGN KEY (id_sexo)
        REFERENCES sexo (id);

ALTER TABLE cidadao
    ADD FOREIGN KEY (id_cor)
        REFERENCES cor (id);

ALTER TABLE cidadao
    ADD FOREIGN KEY (id_cidade)
        REFERENCES cidades (id);