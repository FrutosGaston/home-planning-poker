SELECT id,
       room_id,
       title,
       final_estimation,
       created_at
FROM task
WHERE room_id = :room_id
