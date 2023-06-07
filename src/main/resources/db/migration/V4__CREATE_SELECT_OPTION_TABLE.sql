CREATE TABLE IF NOT EXISTS SELECT_OPTIONS
(
    SO_ID NUMERIC PRIMARY KEY,
    SO_VALUE VARCHAR(255) NOT NULL,
    SO_TEXT VARCHAR(255) NOT NULL,
    SO_SELECTED VARCHAR(1) DEFAULT 'N',
    SO_HIDDEN VARCHAR(1) DEFAULT 'N',
    SO_ENABLED VARCHAR(1) DEFAULT 'Y'
)