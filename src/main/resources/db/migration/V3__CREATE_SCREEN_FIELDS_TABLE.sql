-- This table defines different fields
CREATE TABLE IF NOT EXISTS SCREEN_FIELDS
(
    SF_ID   INT PRIMARY KEY,
    SF_NAME VARCHAR(255) NOT NULL,
    SF_LABEL VARCHAR(255) NOT NULL,
    SF_TYPE VARCHAR(100) NOT NULL DEFAULT 'text',
    SF_DEFAULT_VALUE VARCHAR(255),
    SF_IS_MANDATORY VARCHAR(1) DEFAULT 'N',
    SF_PLACEHOLDER VARCHAR(255),
    SF_TOOL_TIP VARCHAR(255),
    SF_MAX INT,
    SF_MIN INT
)