SELECT id,
       room_id,
       title,
       estimation_id,
       created_at
FROM task
WHERE room_id = :room_id
