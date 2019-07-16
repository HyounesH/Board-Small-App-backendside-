package com.yhamdane.board.service;

import com.yhamdane.board.domain.ProjectTask;

public interface ProjectTaskService {
    ProjectTask saveOrUpdate(ProjectTask task);
    Iterable<ProjectTask> findAll();
    ProjectTask getTaskById(Long id);
    void deletePT(ProjectTask projectTask);

}
