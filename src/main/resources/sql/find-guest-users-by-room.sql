SELECT id,
       room_id,
       name,
       spectator
FROM guest_user
WHERE room_id = :room_id
