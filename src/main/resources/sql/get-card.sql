SELECT id,
       value,
       deck_id,
       created_at
FROM card
WHERE id = :id
