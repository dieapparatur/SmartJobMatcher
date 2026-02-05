ALTER TABLE candidate
ADD candidate_mail TEXT UNIQUE NOT NULL,
ADD candidate_password_hash TEXT NOT NULL;

ALTER TABLE company
ADD company_mail TEXT UNIQUE NOT NULL,
ADD company_password_hash TEXT NOT NULL;