ALTER TABLE company
ADD CONSTRAINT name_is_unique UNIQUE (company_name),
ADD CONSTRAINT unique_mail UNIQUE (company_mail);