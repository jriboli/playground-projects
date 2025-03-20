-- Insert into the project table
INSERT INTO project (project_name, estimated_hours, actual_hours, difficulty, notes) VALUES ("Mow the lawn", 1, 1, 1, "Make sure to pick up poo first");

-- Insert into the category table
INSERT INTO category (category_name) VALUES ("Personal Chore");
INSERT INTO category (category_name) VALUES ("Wife ToDo List");
INSERT INTO category (category_name) VALUES ("School Project");

-- Insert into the material table
INSERT INTO material (project_id, material_name, num_required, cost) VALUES (1, "Lawn Mower", 1, 10.00);

-- Insert into the step table
INSERT INTO step (project_id, step_text, step_order) VALUES (1, "Get Lawn Mower from garage", 1);
INSERT INTO step (project_id, step_text, step_order) VALUES (1, "Turn On and run over all of backyard", 2);
INSERT INTO step (project_id, step_text, step_order) VALUES (1, "Clean and stow in garage", 3);

-- Insert into the project category table
INSERT INTO project_category (project_id, category_id) VALUES (1, 2);