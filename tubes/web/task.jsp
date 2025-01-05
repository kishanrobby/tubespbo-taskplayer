<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TaskPlayer - Task Management</title>
    <link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">
    <style>
        body {
            margin: 0;
            font-family: 'Press Start 2P', cursive;
            background-image: url('<%= request.getContextPath() %>/img/bg.png');
            background-size: cover;
            background-position: center;
            color: white;
            text-align: center;
        }
        .navbar {
            display: flex;
            justify-content: flex-start;
            align-items: center;
            padding: 0px 10px;
            color: #FFF;
        }
        .navbar a {
            color: white;
            text-decoration: none;
            margin: 0 15px;
        }
        .profile {
            margin-left: 850px;
        }
        .navbar a:hover {
            text-decoration: underline;
        }
        .logo {
            display: flex;
            align-items: center;
        }
        .logo img {
            width: 300px;
            margin-right: 10px;
        }
        .container {
            display: flex;
            justify-content: center;
            align-items: flex-start;
            gap: 20px;
        }
        .column {
            flex: 1;
            height: 550px;
        }
        .form-container {
            width: 85%;
            height: 100%;
            padding: 15px;
        }
        .titleBox {
            display: flex;
            justify-content: flex-start;
            margin-left: 80px;
            margin-bottom: 10px;
        }
        .add-task {
            background: #642F94;
            color: white;
            padding: 5px;
            width: 67%;
            height: 40px;
            margin: 10px 0;
            border-radius: 5px;
            border-color: black;
            font-family: 'Press Start 2P', cursive;
            cursor: pointer;
        }
        .add-task:hover {
            background: #5A79BC;
        }
        .titleBox_popUp{
            display: flex;
            justify-content: flex-start;
            margin-left: 10px;
            margin-top: 10px;
        }
        .popup {
            display: none;
            background-image: url('<%= request.getContextPath() %>/img/popUP_bg.png'); /* Replace with your uploaded image path */
            background-size: cover;
            background-position: center;
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            z-index: 1001;
            padding: 50px;
            border-radius: 10px;
            width: 500px;
            height: 500px; /* Adjust based on the design dimensions */
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.5);
        }
        .popup .habit-list {
            padding: 10px;
            height: 250px; /* Control the height of the scrollable area */
            overflow-y: auto;
            margin-bottom: 20px;
        }

        .popup .habit-options {
            display: flex;
            flex-direction: column;
            gap: 8px;
        }

        .popup .habit-option {
            background-color: #3A3A3A;
            color: white;
            padding: 8px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-family: 'Press Start 2P', cursive;
        }

        .popup .habit-option:hover {
            background-color: #642F94;
        }

        .popup .input-box input {
            width: 100%;
            padding: 8px;
            background: #3A3A3A;
            border: none;
            border-radius: 5px;
            color: white;
            margin-bottom: 10px;
            font-family: 'Press Start 2P', cursive;
        }

        .popup-buttons {
            display: flex;
            justify-content: space-between;
        }


        .popup-form label {
            display: block;
            font-size: 12px;
            margin-top: 15px;
            color: #FFF;
        }

        .input-field{
            width: 95%;
            display: flex;
            justify-content: flex-start;
            padding: 8px;
            background: #3A3A3A;
            border: none;
            margin-top: 5px;
            border-radius: 5px;
            color: #FFF;
            font-family: 'Press Start 2P', cursive;
        }
        .time-field {
            width: 50%;
            display: flex;
            justify-content: flex-start;
            padding: 8px;
            background: #3A3A3A;
            border: none;
            margin-top: 5px;
            border-radius: 5px;
            color: #FFF;
            font-family: 'Press Start 2P', cursive;
        }

        .time-input {
            width: 55%;
            display: flex;
            justify-content: flex-start;
            gap: 2px;
            align-items: center;
            justify-content: center;
            margin-top: 10px;
        }

        .popup-buttons {
            margin-top: 200px;
            display: flex;
            justify-content: space-between;
        }

        .cancel-button, .add-button {
            padding: 8px 15px;
            font-family: 'Press Start 2P', cursive;
            border: none;
            cursor: pointer;
        }
        
        .popup-close {
            background: #d9534f;
            color: white;
        }
        .footer {
            position: fixed;
            background-image: url('<%= request.getContextPath() %>/img/pointBox.png');
            background-size: contain;
            background-position: center;
            background-repeat: no-repeat;
            bottom: 0;
            font-size: 10px;
            width: 300px;
            height: 50px;
            color: white;
        }
        /* Overlay styling */
        #overlay {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.7);
            z-index: 1000;
        }
    </style>
</head>
<body>
    <!-- Overlay -->
    <div id="overlay"></div>

    <!-- Navbar -->
    <div class="navbar">
        <div class="logo">
            <img src="<%= request.getContextPath() %>/img/logo.png" alt="TaskPlayer Logo">
        </div>
        <div class="links">
            <a href="dashboard.jsp">DASHBOARD</a>
            <a href="task.jsp">TASK</a>
            <a href="shop.jsp">SHOP</a>
        </div>
        <div class="profile">
            <img src="<%= request.getContextPath() %>/img/Profile.png" alt="TaskPlayer Profile">
        </div>
    </div>

    <!-- Content -->
    <div class="container">
        <!-- Habits Column -->
        <div class="column">
            <div class="titleBox">
                <img src="<%= request.getContextPath() %>/img/titleBox_habit.png" alt="TitleBox">
            </div>
            <div class="form-container" style="background-image: url('<%= request.getContextPath() %>/img/habit-bg.png'); background-size: contain; background-position: center; background-repeat: no-repeat; border-radius: 5px;">
                <button type="button" class="add-task" onclick="openPopup('popupHabits')">Add a Habit</button>
                <ul id="habitsList"></ul>
            </div>
                
        </div>

        <!-- Dailies Column -->
        <div class="column">
            <div class="titleBox">
                <img src="<%= request.getContextPath() %>/img/titleBox_daily.png" alt="TitleBox">
            </div>
            <div class="form-container" style="background-image: url('<%= request.getContextPath() %>/img/daily-bg.png'); background-size: contain; background-position: center; background-repeat: no-repeat; border-radius: 5px;">
                <button type="button" class="add-task" onclick="openPopup('popupDailies')">Add a Daily</button>
                <ul id="dailiesList"></ul>
            </div>
        </div>

        <!-- To-Do's Column -->
        <div class="column">
            <div class="titleBox">
                <img src="<%= request.getContextPath() %>/img/titleBox_toDo.png" alt="TitleBox">
            </div>
            <div class="form-container" style="background-image: url('<%= request.getContextPath() %>/img/todo-bg.png'); background-size: contain; background-position: center; background-repeat: no-repeat; border-radius: 5px;">
                <button type="button" class="add-task" onclick="openPopup('popupTodos')">Add a To-Do</button>
                <ul id="todosList"></ul>
            </div>
        </div>
    </div>

    <!-- Popup Modals -->
    <div id="popupHabits" class="popup">
        <div class="titleBox_popUp">
            <img src="<%= request.getContextPath() %>/img/titleBox_habit.png" alt="TitleBox">
        </div>
        <div class="habit-list">
            <h4>Choose a Habit</h4>
            <div class="habit-options">
                <!-- Loop through available habits. For now, let's add example ones -->
                <button class="habit-option">Exercise</button>
                <button class="habit-option">Read a Book</button>
                <button class="habit-option">Meditate</button>
                <button class="habit-option">Drink Water</button>
                <!-- More buttons can be added dynamically using server data -->
            </div>
        </div>
        <div class="input-box">
            <input type="text" placeholder="Enter your habit..." />
        </div>
        <div class="popup-buttons">
            <button onclick="closePopup('popupHabits')" class="cancel-button">Close</button>
            <button class="add-button">Add Habit</button>
        </div>
    </div>
        
    <div id="popupDailies" class="popup">
        <div class="titleBox_popUp">
            <img src="<%= request.getContextPath() %>/img/titleBox_daily.png" alt="TitleBox">
        </div>
        <form class="popup-form">
            <label for="taskName">Task Name</label>
            <input type="text" id="taskName" class="input-field" name="taskName" placeholder="Enter to-do...">
            <label for="dueDate">Due Date</label>
            <input type="date" id="dueDate" class="time-field" name="dueDate">
            <label for="dueTime">Due Time</label>
            <div class="time-input">
                <input type="number" id="hours" class="time-field" placeholder="HH" min="0" max="23">
                <span>:</span>
                <input type="number" id="minutes" class="time-field" placeholder="MM" min="0" max="59">
            </div>
            <div class="popup-buttons">
                <button type="button" class="cancel-button" onclick="closePopup('popupDailies')">Cancel</button>
                <button type="submit" class="add-button">Add</button>
            </div>
        </form>
    </div>

    <div id="popupTodos" class="popup">
        <div class="titleBox_popUp">
            <img src="<%= request.getContextPath() %>/img/titleBox_toDo.png" alt="TitleBox">
        </div>
        <form class="popup-form">
            <label for="taskName">Task Name</label>
            <input type="text" id="taskName" class="input-field" name="taskName" placeholder="Enter to-do...">
            <label for="dueDate">Due Date</label>
            <input type="date" id="dueDate" class="time-field" name="dueDate">
            <label for="dueTime">Due Time</label>
            <div class="time-input">
                <input type="number" id="hours" class="time-field" placeholder="HH" min="0" max="23">
                <span>:</span>
                <input type="number" id="minutes" class="time-field" placeholder="MM" min="0" max="59">
            </div>
            <div class="popup-buttons">
                <button type="button" class="cancel-button" onclick="closePopup('popupTodos')">Cancel</button>
                <button type="submit" class="add-button">Add</button>
            </div>
        </form>
    </div>

    <!-- Footer -->
    <div class="footer">
        <h2>POINTS: 70</h2>
    </div>

    <script>
        function openPopup(popupId) {
            document.getElementById('overlay').style.display = 'block';
            document.getElementById(popupId).style.display = 'block';
        }

        function closePopup(popupId) {
            document.getElementById('overlay').style.display = 'none';
            document.getElementById(popupId).style.display = 'none';
        }
    </script>
</body>
</html>
