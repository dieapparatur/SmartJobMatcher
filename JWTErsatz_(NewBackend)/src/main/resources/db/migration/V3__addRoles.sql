--added roles for JWTs

ALTER TABLE candidate
ADD role TEXT NOT NULL DEFAULT 'candidate';

ALTER TABLE company
ADD role TEXT NOT NULL DEFAULT 'company';