update room
set selected_task_id = coalesce(:selected_task_id, selected_task_id)
where id = :id;