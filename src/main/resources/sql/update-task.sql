update task
set estimation_id = :estimation_id,
    title = coalesce(NULLIF(:title, ''), title)
where id = :id;