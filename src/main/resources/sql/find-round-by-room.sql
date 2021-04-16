SELECT id,
       room_id,
       title,
       created_at
FROM round
WHERE room_id = :room_id
