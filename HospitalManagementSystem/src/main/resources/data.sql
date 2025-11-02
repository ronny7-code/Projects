INSERT INTO patient(name, gender, birth_date, email, blood_group)
VALUES
    ('Bijay Tamang', 'MALE', '2006-02-04', 'bijay98813@gmail.com', 'O_POSITIVE'),
    ('Aarush Tamang', 'MALE', '2010-06-09', 'aarush@gmail.com', 'A_POSITIVE'),
    ('Abinash Tamang', 'MALE', '2008-12-24', 'abinash@gmail.com', 'AB_POSITIVE'),
    ('Cristiano Ronaldo', 'MALE', '1985-02-05', 'ronaldo@gmail.com', 'O_NEGATIVE');

INSERT INTO doctor(name, specialization, email)
VALUES
    ('Dr. Ramesh Shah', 'Cardiology', 'ramesh@gmail.com'),
    ('Dr. Joseph Lennon', 'Dermatology', 'joseph@gmail.com'),
    ('Dr. Eric Cantonna', 'Orthopedics', 'eric@gmail.com');

INSERT INTO appointment (appointment_time, reason, doctor_id, patient_id)
VALUES
    ('2025-07-01 10:30:00', 'General Checkup', 1, 2),
    ('2025-07-02 11:30:00', 'Skin Rash', 2, 2),
    ('2025-07-03 9:45:00', 'Knee Pain', 3, 3),
    ('2025-07-04 14:00:00', 'Follow-up', 1, 1),
    ('2025-07-05 16:15:00', 'Consultation', 1, 4);