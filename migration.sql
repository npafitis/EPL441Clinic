CREATE TABLE Role (
    roleId INTEGER not NULL AUTO_INCREMENT,
    description VARCHAR(255) not NULL,
    PRIMARY KEY ( roleId )
)

CREATE TABLE User (
    userId INTEGER not NULL AUTO_INCREMENT,
    name VARCHAR(255) not NULL,
    email VARCHAR(255) not NULL unique,
    phone VARCHAR(15) not NULL,
    roleId INTEGER not NULL,
    PRIMARY KEY (userId),
    FOREIGN KEY (roleId) REFERENCES Role(roleId)
)

CREATE TABLE Warning (
    warningId INTEGER not NULL AUTO_INCREMENT,
    userId INTEGER not NULL,
    warningDate DATE not NULL,
    description VARCHAR(255),
    PRIMARY KEY (warningId),
    FOREIGN KEY (userId) REFERENCES User(userId)
)

CREATE TABLE Patient (
    patientId INTEGER not NULL AUTO_INCREMENT,
    name VARCHAR(255) not NULL,
    email VARCHAR(255) not NULL,
    phone VARCHAR(15) not NULL,
    selfHarm BOOLEAN DEFAULT false,
    PRIMARY KEY (patientId)
)

CREATE TABLE Consultation (
    consultationId INTEGER not NULL AUTO_INCREMENT,
    userId INTEGER not NULL,
    patientId INTEGER not NULL,
    consultationDate DATE not NULL,
    attended BOOLEAN DEFAULT false,
    PRIMARY KEY (consultationId),
    FOREIGN KEY (userId) REFERENCES User(userId),
    FOREIGN KEY (patientId) REFERENCES Patient(patientId)
)

CREATE TABLE Incident (
    incidentId INTEGER not NULL AUTO_INCREMENT,
    patientId INTEGER not NULL,
    incidentDate DATE not NULL,
    description VARCHAR(255) not NULL,
    PRIMARY KEY (incidentId),
    FOREIGN KEY (patientId) REFERENCES Patient(patientId)
)

CREATE TABLE InformationChange (
    changeId INTEGER not NULL AUTO_INCREMENT,
    patientId INTEGER not NULL,
    changeDate DATE not NULL,
    newName VARCHAR(255),
    newEmail VARCHAR(255),
    newPhone VARCHAR(15),
    oldName VARCHAR(255),
    oldEmail VARCHAR(255),
    oldPhone VARCHAR(15),
    accepted BOOLEAN DEFAULT false,
    PRIMARY KEY (changeId),
    FOREIGN KEY (patientId) REFERENCES Patient(patientId)
)

CREATE TABLE Relative (
    relativeId INTEGER not NULL AUTO_INCREMENT,
    patientId INTEGER not NULL,
    name VARCHAR(255) not NULL,
    email VARCHAR(255) not NULL,
    phone VARCHAR(15) not NULL,
    PRIMARY KEY (relativeId),
    FOREIGN KEY (patientId) REFERENCES Patient(patientId)
)

CREATE TABLE Allergy (
    allergyId INTEGER not NULL AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (allergyId)
)

CREATE TABLE Treatment (
    treatmentId INTEGER not NULL AUTO_INCREMENT,
    patientId INTEGER not NULL,
    description VARCHAR(255),
    treatmentDate DATE not NULL,
    PRIMARY KEY (treatmentId),
    FOREIGN KEY (patientId) REFERENCES Patient(patientId)
)

CREATE TABLE TreatmentAllergy (
    treatmentId INTEGER not NULL,
    allergyId INTEGER not NULL,
    PRIMARY KEY (treatmentId,allergyId),
    FOREIGN KEY (treatmentId) REFERENCES Treatment(treatmentId),
    FOREIGN KEY (allergyId) REFERENCES Allergy(allergyId)
)

CREATE TABLE PatientAllergy (
    patientId INTEGER not NULL,
    allergyId INTEGER not NULL,
    PRIMARY KEY (patientId, allergyId),
    FOREIGN KEY (allergyId) REFERENCES Allergy(allergyId),
    FOREIGN KEY (patientId) REFERENCES Patient(patientId)
)

CREATE TABLE Diagnosis (
    diagnosisId INTEGER not NULL AUTO_INCREMENT,
    patientId INTEGER not NULL,
    details VARCHAR(255),
    comments VARCHAR(255),
    PRIMARY KEY (diagnosis),
    FOREIGN KEY (patientId) REFERENCES Patient(patientId)
)

