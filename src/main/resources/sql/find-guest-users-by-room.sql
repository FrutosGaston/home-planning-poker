SELECT id,
       room_id,
       name
FROM guest_user
WHERE room_id = :room_id
