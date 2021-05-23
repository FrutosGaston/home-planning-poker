SELECT id,
       value,
       deck_id,
       created_at
FROM card
WHERE deck_id = :deck_id
