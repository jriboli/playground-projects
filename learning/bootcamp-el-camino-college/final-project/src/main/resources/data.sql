USE clinical_study

INSERT INTO owner (company_name, owner_first_name, owner_last_name) VALUES ("Tests R Us", "Rocket", "Raccoon")

INSERT INTO site (owner_id, site_address, site_city, site_state, site_zip, site_phone, site_name) VALUES (1, "123 Main St.", "Burbank", "CA", "91601", "888.555.6666", "SiteOne")
INSERT INTO site (owner_id, site_address, site_city, site_state, site_zip, site_phone, site_name) VALUES (1, "564 Main St.", "Burbank", "CA", "91601", "888.555.6667", "SiteTwo")
INSERT INTO site (owner_id, site_address, site_city, site_state, site_zip, site_phone, site_name) VALUES (1, "789 Main St.", "Burbank", "CA", "91601", "888.555.6668", "SiteThree")

INSERT INTO specialty (specialty_name) VALUES ("pulmonology")
INSERT INTO specialty (specialty_name) VALUES ("anethetics")
INSERT INTO specialty (specialty_name) VALUES ("immunology")
INSERT INTO specialty (specialty_name) VALUES ("cardiology")

INSERT INTO site_specialty (site_id, specialty_id) VALUES (1, 1)
INSERT INTO site_specialty (site_id, specialty_id) VALUES (2, 2)
INSERT INTO site_specialty (site_id, specialty_id) VALUES (3, 3)

INSERT INTO doctor (owner_id, site_id, doctor_first_name, doctor_last_name) VALUES (1, 1, "Rocket", "Raccoon")
INSERT INTO doctor (owner_id, site_id, doctor_first_name, doctor_last_name) VALUES (1, NULL, "Peter", "Quill")

INSERT INTO clinical_study (specialty_id, study_name, study_description, study_status) VALUES (1, "Pulmonology Study", "This is a study", 1)
INSERT INTO clinical_study (specialty_id, study_name, study_description, study_status) VALUES (2, "Anethetic Study", "This is a study", 2)
INSERT INTO clinical_study (specialty_id, study_name, study_description, study_status) VALUES (3, "Immunology Study", "This is a study", 3)
INSERT INTO clinical_study (specialty_id, study_name, study_description, study_status) VALUES (4, "Cardiology Study", "This is a study", 4)

INSERT INTO site_study (clinical_study_id, site_id) VALUES (1, 1)

INSERT INTO patient (patient_first_name, patient_last_name, patient_age, patient_sex) VALUES ("Wade", "Wilson", 37, 1)
INSERT INTO patient (patient_first_name, patient_last_name, patient_age, patient_sex) VALUES ("Twilight", "Sparkle", 5, 1)
INSERT INTO patient (patient_first_name, patient_last_name, patient_age, patient_sex) VALUES ("Apple", "Jack", 10, 0)
INSERT INTO patient (patient_first_name, patient_last_name, patient_age, patient_sex) VALUES ("Rainbox", "Dash", 9, 0)
INSERT INTO patient (patient_first_name, patient_last_name, patient_age, patient_sex) VALUES ("Pinkie", "Pie", 7, 1)

INSERT INTO study_patient (patient_id, study_id) VALUES (1, 1) 
INSERT INTO study_patient (patient_id, study_id) VALUES (2, 1)