CREATE DATABASE santekunafoni;
use santekunafoni;


CREATE TABLE utilisateur (
                             idUtilisateur INT AUTO_INCREMENT PRIMARY KEY,
                             nom VARCHAR(255) NOT NULL,
                             prenom VARCHAR(255) NOT NULL,
                             tel VARCHAR(50),
                             motpass VARCHAR(255) NOT NULL,
                             role VARCHAR(50),
                             CHECK (role IN ('agent_sante', 'patient', 'admin'))
);

CREATE TABLE maladie (
                         id_maladie INT AUTO_INCREMENT PRIMARY KEY,
                         nom VARCHAR(255) NOT NULL UNIQUE,
                         description TEXT NOT NULL,
                         date_declaration DATE NOT NULL
);

CREATE TABLE administrateur (
                                idUtilisateur INT PRIMARY KEY,
                                email VARCHAR(255) NOT NULL,
                                FOREIGN KEY (idUtilisateur) REFERENCES utilisateur(idUtilisateur)
);

CREATE TABLE notification (
                              idNotif INT AUTO_INCREMENT PRIMARY KEY,
                              titre VARCHAR(255) NOT NULL,
                              datePublication TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              message TEXT NOT NULL
);

CREATE TABLE agent_sante (
                             idUtilisateur INT PRIMARY KEY,
                             specialite VARCHAR(255),
                             centre VARCHAR(255),
                             email VARCHAR(255) UNIQUE,
                             FOREIGN KEY (idUtilisateur) REFERENCES utilisateur(idUtilisateur)
);

CREATE TABLE patient (
                         idUtilisateur INT PRIMARY KEY,
                         adresse VARCHAR(255),
                         age INT,
                         etat VARCHAR(50),
                         sexe VARCHAR(20) NOT NULL,
                         CHECK (etat IN ('Stable', 'Instable', 'Critique')),
                         FOREIGN KEY (idUtilisateur) REFERENCES utilisateur(idUtilisateur)
);

CREATE TABLE traitement (
                            idTraitement BIGINT AUTO_INCREMENT PRIMARY KEY,
                            nomTraitement VARCHAR(255) NOT NULL,
                            datedebut DATE NOT NULL,
                            datefin DATE NOT NULL,
                            description TEXT NOT NULL,
                            id_maladie INT NOT NULL,
                            idAgentSante INT NOT NULL,
                            idPatient INT NOT NULL,
                            FOREIGN KEY (id_maladie) REFERENCES maladie(id_maladie),
                            FOREIGN KEY (idAgentSante) REFERENCES agent_sante(idUtilisateur),
                            FOREIGN KEY (idPatient) REFERENCES patient(idUtilisateur)
);

CREATE TABLE maladie_patient (
                                 idUtilisateur INT NOT NULL,
                                 id_maladie INT NOT NULL,
                                 localite VARCHAR(255),
                                 periode DATE,
                                 idNotif INT,
                                 PRIMARY KEY (idUtilisateur, id_maladie),
                                 FOREIGN KEY (idUtilisateur) REFERENCES patient(idUtilisateur),
                                 FOREIGN KEY (id_maladie) REFERENCES maladie(id_maladie),
                                 FOREIGN KEY (idNotif) REFERENCES notification(idNotif)
);

CREATE TABLE symptome (
                          idSymptome BIGINT AUTO_INCREMENT PRIMARY KEY,
                          nomsymptome VARCHAR(255) NOT NULL UNIQUE,
                          description VARCHAR(255) NOT NULL,
                          id_maladie INT NOT NULL,
                          idUtilisateur INT NOT NULL,
                          FOREIGN KEY (id_maladie) REFERENCES maladie(id_maladie),
                          FOREIGN KEY (idUtilisateur) REFERENCES patient(idUtilisateur)
);

CREATE TABLE symptome_patient (
                                  idSymptome BIGINT NOT NULL,
                                  idUtilisateur INT NOT NULL,

                                  PRIMARY KEY(idSymptome,idUtilisateur),

                                  FOREIGN KEY(idSymptome)
                                      REFERENCES symptome(idSymptome),

                                  FOREIGN KEY(idUtilisateur)
                                      REFERENCES patient(idUtilisateur)
);

CREATE TABLE symptome_maladie (
                                  idSymptome BIGINT NOT NULL,
                                  id_maladie INT NOT NULL,

                                  PRIMARY KEY(idSymptome,id_maladie),

                                  FOREIGN KEY(idSymptome)
                                      REFERENCES symptome(idSymptome),

                                  FOREIGN KEY(id_maladie)
                                      REFERENCES maladie(id_maladie)
);

ALTER TABLE patient ADD COLUMN periode Date;
ALTER TABLE patient RENAME COLUMN adresse To localite;
ALTER TABLE maladie_patient DROP COLUMN periode;
ALTER TABLE maladie_patient DROP COLUMN localite;