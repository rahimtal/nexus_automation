-- Script to create 'sa' user directly in Keycloak PostgreSQL database
-- This bypasses the UI/API 3-character username validation

-- First, get the realm ID for 'nexus'
SELECT id INTO @realm_id FROM realm WHERE name = 'nexus' LIMIT 1;

-- Create the user 'sa'
INSERT INTO user_entity (
    id,
    username,
    created_timestamp,
    email_verified,
    enabled,
    federation_link,
    first_name,
    last_name,
    realm_id,
    email,
    service_account_client_link
) VALUES (
    gen_random_uuid(),
    'sa',
    EXTRACT(EPOCH FROM now()) * 1000,
    false,
    true,
    NULL,
    NULL,
    NULL,
    (SELECT id FROM realm WHERE name = 'nexus'),
    NULL,
    NULL
) RETURNING id INTO @user_id;

-- Get the user ID we just created
SELECT id INTO @user_id FROM user_entity WHERE username = 'sa' AND realm_id = (SELECT id FROM realm WHERE name = 'nexus') LIMIT 1;

-- Add the password credential
-- First, we need to hash the password using PBKDF2 (Keycloak default)
INSERT INTO credential (
    id,
    user_id,
    type,
    created_date,
    user_label,
    secret_data,
    credential_data,
    priority
) VALUES (
    gen_random_uuid(),
    @user_id,
    'password',
    EXTRACT(EPOCH FROM now()) * 1000,
    NULL,
    '{"value":"password"}',
    '{"hashIterations":27500,"algorithm":"pbkdf2-sha256"}',
    10
);

-- Add custom attributes
INSERT INTO user_attribute (
    id,
    user_id,
    name,
    value
) VALUES 
    (gen_random_uuid(), @user_id, 'UserName', 'sa'),
    (gen_random_uuid(), @user_id, 'UserId', 'sa');

SELECT 'User sa created successfully' as result;
