SELECT id,
       room_id,
       title,
       estimation_id,
       created_at
FROM task
WHERE id = :id
