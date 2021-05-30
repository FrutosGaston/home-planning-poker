SELECT id,
       card_id,
       task_id,
       guest_user_id,
       created_at,
       active
FROM estimation
WHERE id = :id
