update estimation
set active = :active
where task_id = :task_id and guest_user_id = :guest_user_id;