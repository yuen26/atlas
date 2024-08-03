-- Plain password: 123456
INSERT INTO users (username, email, password, role, credit)
VALUES ('admin', 'admin@atlas.org', '$2a$12$.sw34Jx7f/I9aln/s9b2.eMcs6Ji9HpcudPAl2BOkLPmupGhsU8yO', 'ADMIN', 0),
       ('customer', 'customer@atlas.org', '$2a$12$.sw34Jx7f/I9aln/s9b2.eMcs6Ji9HpcudPAl2BOkLPmupGhsU8yO', 'CUSTOMER', 100000);
