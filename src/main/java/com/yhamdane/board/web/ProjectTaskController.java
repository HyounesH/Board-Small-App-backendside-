package com.yhamdane.board.web;

import com.yhamdane.board.domain.ProjectTask;
import com.yhamdane.board.service.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.yhamdane.board.util.Constant.DEFAULT_FRONTEND_URL;

@RestController
@RequestMapping("/api/board")
@CrossOrigin(DEFAULT_FRONTEND_URL)
public class ProjectTaskController {

    @Autowired
    private ProjectTaskService projectTaskService;
    @PostMapping("/add")
    public ResponseEntity<?> addPTToBoard(@Valid @RequestBody ProjectTask task, BindingResult result){
        if(result.hasErrors()){
            Map<String,String> errorMap=new HashMap<>();
            for(FieldError error:result.getFieldErrors()){
                errorMap.put(error.getField(),error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String,String>>(errorMap,HttpStatus.BAD_REQUEST);
        }
        ProjectTask newPT=projectTaskService.saveOrUpdate(task);
        return new ResponseEntity<ProjectTask>(newPT,HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updatePTToBoard(@Valid @RequestBody ProjectTask task,BindingResult result){
        return addPTToBoard(task,result);
    }
    @GetMapping("/all")
    public ResponseEntity<Iterable<ProjectTask>> findAll(){
        return ResponseEntity.ok(projectTaskService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProjectTask> getPTById(@PathVariable("id") Long id){
        try {
         return ResponseEntity.ok(projectTaskService.getTaskById(id));
        }catch (ResourceNotFoundException ex){
           return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePT(@PathVariable("id") Long id){

        Optional<ProjectTask> projectTask= Optional.ofNullable(projectTaskService.getTaskById(id));
        if(!projectTask.isPresent()){
            return new ResponseEntity<String>("task with id : "+id+" not found",HttpStatus.BAD_REQUEST);
        }
        projectTaskService.deletePT(projectTask.get());
        return new ResponseEntity<String>("task with id : "+id+" has been deleted successfully",HttpStatus.OK);
    }




}
