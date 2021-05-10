SELECT id,
       title,
       description,
       created_at
FROM room
WHERE id = :id
