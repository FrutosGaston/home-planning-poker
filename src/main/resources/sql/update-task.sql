update task
set final_estimation = coalesce(NULLIF(:final_estimation, ''), final_estimation),
    title = coalesce(NULLIF(:title, ''), title)
where id = :id;