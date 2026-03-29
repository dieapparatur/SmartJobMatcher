ALTER TABLE job_posting
ADD field TEXT NOT NULL,
ADD salary INT,
ADD interested_count INT NOT NULL DEFAULT 0,
ADD picture TEXT;