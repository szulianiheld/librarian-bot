-- INSERT: seed data on book table
MERGE INTO book (id,name, author, description, editorial, imprint, date, isbn13, url, is_digital) VALUES (1,'Continuous Delivery', 'Jez Humble, David Farley', 'Reliable Software Releases through Build, Test, and Deployment Automation', 'Pearson Education', 'Addison Wesley', parsedatetime('21-12-2010', 'dd-MM-yyyy'), '9780321601919', '', false);

-- INSERT: seed data on user table
MERGE INTO user (id, name, surname, user_name, is_admin) VALUES (1, 'Santiago', 'Zuliani Held', '@szulianiheld', true);