SELECT id,
       room_id,
       title,
       created_at
FROM task
WHERE room_id = :room_id
