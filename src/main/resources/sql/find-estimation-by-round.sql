SELECT id,
       name,
       round_id,
       guest_user_id,
       created_at
FROM estimation
WHERE round_id = :round_id
