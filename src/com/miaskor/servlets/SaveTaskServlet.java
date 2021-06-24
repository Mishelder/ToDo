package com.miaskor.servlets;

import com.miaskor.dto.FetchTaskDto;
import com.miaskor.dto.SaveTaskDto;
import com.miaskor.entity.Client;
import com.miaskor.exception.ValidationException;
import com.miaskor.service.DeleteTaskService;
import com.miaskor.service.SaveTaskService;
import com.miaskor.util.ControllersURIKeys;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(ControllersURIKeys.SAVE_TASK)
public class SaveTaskServlet extends HttpServlet {

    private final SaveTaskService saveTaskService = SaveTaskService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var day = Integer.parseInt(req.getParameter("day"));
        var pointer_time =(ZonedDateTime)req.getSession().getAttribute("pointer_time");
        var actualDate = pointer_time.plusDays(day);
        var keyActualDate = actualDate.toLocalDate().toString();
        var client = (Client)req.getSession().getAttribute("client");
        var clientId = client.getId();
        List<SaveTaskDto> saveTaskDtos = new ArrayList<>();
        for(int index = 0;index<15;index++){
            var taskParameter = req.getParameter("task_%d".formatted(index));
            if(!taskParameter.isEmpty()) {
                saveTaskDtos.add(SaveTaskDto.builder()
                        .clientId(clientId)
                        .task(taskParameter)
                        .date(actualDate.toLocalDate())
                        .doneTask(req.getParameter("task_box_%d".formatted(index)))
                        .indexInForm("%d".formatted(index)).build());
            }
        }
        try {
            var tasks = saveTaskService.saveTask(saveTaskDtos,clientId,actualDate.toLocalDate());
            var userTasks = (Map<String, List<FetchTaskDto>>)req.getSession().getAttribute("tasks");
            if(tasks == null && userTasks.containsKey(keyActualDate)){
                userTasks.remove(keyActualDate);
            }else if(tasks != null){
                userTasks.put(keyActualDate, tasks);
            }
            req.getSession().setAttribute("tasks",userTasks);
            resp.sendRedirect(ControllersURIKeys.TODO);
        }catch (ValidationException e){
            req.setAttribute("error",e.getErrorMessages());
            req.getRequestDispatcher(ControllersURIKeys.TODO).forward(req,resp);
        }
    }
}
