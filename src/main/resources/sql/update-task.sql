update task
set estimation_id = coalesce(:estimation_id, estimation_id),
    title = coalesce(NULLIF(:title, ''), title)
where id = :id;