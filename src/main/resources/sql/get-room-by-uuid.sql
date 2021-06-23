SELECT id,
       uuid,
       title,
       description,
       deck_id,
       selected_task_id,
       created_at
FROM room
WHERE uuid = :uuid
