package controllers;

import models.Habit;
import models.Daily;
import models.ToDo;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class TaskController extends HttpServlet {

    private static final String LIST_PAGE = "tasks?action=list";
    private static final String ADD_HABIT_PAGE = "add_habit.jsp";
    private static final String ADD_DAILY_PAGE = "add_daily.jsp";
    private static final String ADD_TODO_PAGE = "add_todo.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action parameter is missing.");
            return;
        }

        switch (action) {
            case "addHabit":
                forwardToPage(request, response, ADD_HABIT_PAGE);
                break;
            case "addDaily":
                forwardToPage(request, response, ADD_DAILY_PAGE);
                break;
            case "addToDo":
                forwardToPage(request, response, ADD_TODO_PAGE);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action parameter is missing.");
            return;
        }

        try {
            switch (action) {
                case "addHabit":
                    processHabit(request, response);
                    break;
                case "addDaily":
                    processDaily(request, response);
                    break;
                case "addToDo":
                    processToDo(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action.");
            }
        } catch (IllegalArgumentException e) {
            handleValidationError(request, response, action, e.getMessage());
        } catch (Exception e) {
            handleServerError(request, response, action, e);
        }
    }

    private void processHabit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String type = request.getParameter("type");

        validateRequiredField("Title", title);

        Habit habit = new Habit();
        habit.setTitle(title);
        habit.setDescription(description);
        habit.setHabitType(type);

        if (!habit.save()) {
            throw new Exception("Failed to save habit.");
        }

        redirectWithMessage(response, "Habit added successfully!");
    }

    private void processDaily(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String frequency = request.getParameter("frequency");

        validateRequiredField("Title", title);
        validateRequiredField("Frequency", frequency);

        Daily daily = new Daily();
        daily.setTitle(title);
        daily.setDescription(description);
        daily.setFrequency(frequency);

        if (!daily.save()) {
            throw new Exception("Failed to save daily task.");
        }

        redirectWithMessage(response, "Daily task added successfully!");
    }

    private void processToDo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String dueDate = request.getParameter("dueDate");

        validateRequiredField("Title", title);
        validateRequiredField("Due date", dueDate);

        ToDo todo = new ToDo();
        todo.setTitle(title);
        todo.setDescription(description);
        todo.setDueDate(dueDate);

        if (!todo.save()) {
            throw new Exception("Failed to save to-do item.");
        }

        redirectWithMessage(response, "To-Do added successfully!");
    }

    private void validateRequiredField(String fieldName, String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " is required.");
        }
    }

    private void redirectWithMessage(HttpServletResponse response, String message) throws IOException {
        response.sendRedirect(LIST_PAGE + "&message=" + message);
    }

    private void forwardToPage(HttpServletRequest request, HttpServletResponse response, String page)
            throws ServletException, IOException {
        request.getRequestDispatcher(page).forward(request, response);
    }

    private void handleValidationError(HttpServletRequest request, HttpServletResponse response, String action, String errorMessage)
            throws ServletException, IOException {
        request.setAttribute("error", errorMessage);
        request.getRequestDispatcher(getErrorPage(action)).forward(request, response);
    }

    private void handleServerError(HttpServletRequest request, HttpServletResponse response, String action, Exception e)
            throws ServletException, IOException {
        e.printStackTrace(); // Log the error for debugging purposes
        request.setAttribute("error", "An unexpected error occurred. Please try again.");
        request.getRequestDispatcher(getErrorPage(action)).forward(request, response);
    }

    private String getErrorPage(String action) {
        switch (action) {
            case "addHabit":
                return ADD_HABIT_PAGE;
            case "addDaily":
                return ADD_DAILY_PAGE;
            case "addToDo":
                return ADD_TODO_PAGE;
            default:
                return LIST_PAGE;
        }
    }
}
