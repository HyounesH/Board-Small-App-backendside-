package com.yhamdane.board.service.impl;

import com.yhamdane.board.domain.ProjectTask;
import com.yhamdane.board.repository.ProjectTaskRepository;
import com.yhamdane.board.service.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.yhamdane.board.util.Constant.DEFAULT_PROJECT_TASK_STATUS;

@Service
public class DefaultProjectTaskService implements ProjectTaskService {
    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Override
    public ProjectTask saveOrUpdate(ProjectTask task) {
        if (task.getStatus() == null || task.getStatus() == "")
            task.setStatus(DEFAULT_PROJECT_TASK_STATUS);
        return projectTaskRepository.save(task);
    }

    @Override
    public Iterable<ProjectTask> findAll() {
        return projectTaskRepository.findAll();
    }

    @Override
    public ProjectTask getTaskById(Long id) {
        return projectTaskRepository.getProjectTaskById(id);
    }

    @Override
    public void deletePT(ProjectTask projectTask) {
        projectTaskRepository.delete(projectTask);
    }

}
