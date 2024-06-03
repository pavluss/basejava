CREATE TABLE resume (
    uuid      VARCHAR(36) PRIMARY KEY NOT NULL,
    full_name TEXT                 NOT NULL
);

CREATE TABLE contact (
    id          SERIAL,
    resume_uuid VARCHAR(36) NOT NULL REFERENCES resume (uuid) ON DELETE CASCADE,
    type        TEXT     NOT NULL,
    value       TEXT     NOT NULL
);

CREATE TABLE section (
    id          SERIAL PRIMARY KEY,
    resume_uuid VARCHAR(36) NOT NULL REFERENCES resume (uuid) ON DELETE CASCADE,
    type        TEXT     NOT NULL,
    content     TEXT     NOT NULL
);

CREATE UNIQUE INDEX section_idx
    ON section (resume_uuid, type);

CREATE UNIQUE INDEX contact_uuid_type_index
    ON contact (resume_uuid, type);