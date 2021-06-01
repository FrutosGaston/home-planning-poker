SELECT id,
       title,
       description,
       deck_id,
       selected_task_id,
       created_at
FROM room
WHERE id = :id
