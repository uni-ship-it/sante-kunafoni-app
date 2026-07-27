CREATE DATABASE IF NOT EXISTS santekunafoni;
USE santekunafoni;

CREATE TABLE IF NOT EXISTS utilisateur (
                                           idUtilisateur INT AUTO_INCREMENT PRIMARY KEY,
                                           nom VARCHAR(255) NOT NULL,
                                           prenom VARCHAR(255) NOT NULL,
                                           tel VARCHAR(50),
                                           motpass VARCHAR(255) NOT NULL,
                                           role VARCHAR(50),
                                           CHECK (role IN ('agent_sante', 'patient', 'admin'))
);

CREATE TABLE IF NOT EXISTS maladie (
                                       id_maladie INT AUTO_INCREMENT PRIMARY KEY,
                                       nom VARCHAR(255) NOT NULL UNIQUE,
                                       description TEXT NOT NULL,
                                       date_declaration DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS administrateur (
                                              idUtilisateur INT PRIMARY KEY,
                                              email VARCHAR(255) NOT NULL,
                                              FOREIGN KEY (idUtilisateur) REFERENCES utilisateur(idUtilisateur)
);

-- Table notification corrigée (avec lue et utilisateur_id)
CREATE TABLE IF NOT EXISTS notification (
                                            idNotif INT AUTO_INCREMENT PRIMARY KEY,
                                            titre VARCHAR(255) NOT NULL,
                                            message TEXT NOT NULL,
                                            datePublication TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                            lue BOOLEAN DEFAULT FALSE,
                                            utilisateur_id INT NULL,
                                            FOREIGN KEY (utilisateur_id) REFERENCES utilisateur(idUtilisateur)
);

CREATE TABLE IF NOT EXISTS agent_sante (
                                           idUtilisateur INT PRIMARY KEY,
                                           specialite VARCHAR(255),
                                           centre VARCHAR(255),
                                           email VARCHAR(255) UNIQUE,
                                           FOREIGN KEY (idUtilisateur) REFERENCES utilisateur(idUtilisateur)
);

CREATE TABLE IF NOT EXISTS patient (
                                       idUtilisateur INT PRIMARY KEY,
                                       adresse VARCHAR(255),
                                       age INT,
                                       etat VARCHAR(50),
                                       sexe VARCHAR(20) NOT NULL,
                                       CHECK (etat IN ('Stable', 'Instable', 'Critique')),
                                       FOREIGN KEY (idUtilisateur) REFERENCES utilisateur(idUtilisateur)
);

CREATE TABLE IF NOT EXISTS traitement (
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


CREATE TABLE IF NOT EXISTS maladie_patient (
                                               id_utilisateur BIGINT NOT NULL,
                                               id_maladie BIGINT NOT NULL,
                                               localite VARCHAR(255),
                                               periode DATE,
                                               id_notif BIGINT,
                                               PRIMARY KEY (id_utilisateur, id_maladie),
                                               CONSTRAINT fk_maladie_patient_patient FOREIGN KEY (id_utilisateur) REFERENCES patient(id_utilisateur),
                                               CONSTRAINT fk_maladie_patient_maladie FOREIGN KEY (id_maladie) REFERENCES maladie(id_maladie),
                                               CONSTRAINT fk_maladie_patient_notif FOREIGN KEY (id_notif) REFERENCES notification(id)
);

CREATE TABLE IF NOT EXISTS symptome (
                                        idSymptome BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        nomsymptome VARCHAR(255) NOT NULL UNIQUE,
                                        description VARCHAR(255) NOT NULL,
                                        id_maladie INT NOT NULL,
                                        idUtilisateur INT NOT NULL,
                                        FOREIGN KEY (id_maladie) REFERENCES maladie(id_maladie),
                                        FOREIGN KEY (idUtilisateur) REFERENCES patient(idUtilisateur)
);

CREATE TABLE IF NOT EXISTS symptome_patient (
                                                idSymptome INT NOT NULL,
                                                idUtilisateur INT NOT NULL,
                                                PRIMARY KEY (idSymptome, idUtilisateur),
                                                FOREIGN KEY (idUtilisateur) REFERENCES patient(idUtilisateur)
);

CREATE TABLE IF NOT EXISTS symptome_maladie (
                                                idSymptome INT NOT NULL,
                                                id_maladie INT NOT NULL,
                                                PRIMARY KEY (idSymptome, id_maladie),
                                                FOREIGN KEY (id_maladie) REFERENCES maladie(id_maladie)
);