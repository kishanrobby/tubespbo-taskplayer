<%-- 
    Document   : index
    Created on : Dec 25, 2024, 7:16:20 PM
    Author     : mfath
--%>

<%--<%@page import="models.Dashboard"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - TaskPlayer</title>
    <!--<link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">-->
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap');

        body {
            /*margin: 0;*/
            font-family: 'Press Start 2P', monospace;
            background: #000;
            /*background: linear-gradient(to bottom, #a0c1f1, #8566c7);*/
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            overflow: hidden;
        }
        .game-container {
            width: 100%;
            height: 100vh;
            position: relative;
            overflow: hidden;
        }
        .background {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 1440px;
            height: 1024px;
        }
        .background_all {
            background: url('img_dsb/bg.png') no-repeat center center;
            background-size: cover;
            z-index: -3;
        }
        .background1 {
            background: url('img_dsb/bg1.png') no-repeat center center;
            background-size: cover;
            z-index: -3;
        }
        .background2 {
            background: url('img_dsb/bg2.png') no-repeat center center;
            background-size: cover;
            z-index: -2;
        }
        .background3 {
            background: url('img_dsb/bg3.png') no-repeat center center;
            background-size: cover;
            z-index: -1;
        }        
        .header {
            position: absolute;
            top: 20px;
            left: 30px;
            right: 90px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            z-index: 10;
        }
        .logo-container {
            display: flex;
            align-items: center;
        }
        .logo {
            width: 90px;
            height: 90px;
        }
        .taskplayer-text {
            position: relative;
            top: -10px;
            z-index: 1;
        }
        .taskplayer-text img {
            position: absolute;
            top: 0;
            left: 0;
            width: 240px;
            height: 30px;
        }
        .taskplayer-text img:nth-child(1) {
            top: 5px;
        }
        .profile {
            width: 72px;
            height: 72px;
        }
        .menu {
            position: absolute;
            top: 40px;
            left: 52%;
            transform: translateX(-50%);
            display: flex;
            justify-content: center;
            gap: 60px;
            z-index: 2;
        }
        .menu-item {
            font-size: 15px;
            color: #ffffff;
            text-decoration: none;
            cursor: pointer;
        }
        .menu:hover{
            color: black;
/*            text-decoration: underline;*/
        }
        .menu-item:hover {
            color: whitesmoke;
            text-decoration: underline;
        }
        .menu-item:nth-child(1) { /* Dashboard */
            animation: glow 1.5s infinite alternate;
        }
        @keyframes glow {
            0% {
                text-shadow: 0px 0px 5px #ffffff, 0px 0px 10px #ffffff, 0px 0px 20px #ffffff;
            }
            100% {
                text-shadow: 0px 0px 10px #ffffff, 0px 0px 30px #ffffff, 0px 0px 45px #ffffff;
            }
        }

        .dashboard-container {
            position: absolute;
            top: 115px;
            left: 50%;
            transform: translateX(-50%);
            z-index: 2;
            width: 400px;
            height: 100px;
        }
        .dashboard-banner {
            position: absolute;
            width: 100%;
            height: 120%;
        }
        .dashboard-text {
            position: absolute;
            top: 58%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 60%;
            height: auto;
        }
        .dashboard-card {
            background: #F2DDDC;
            border-radius: 15px;
            padding: 20px;
            width: 800px;
            justify-self: center;
            align-content: center;
            position: relative;
            top: 220px;
            color: #333;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
        }

        .title {
            font-size: 1.5em;
            font-weight: bold;
            margin-bottom: 20px;
            color: #000;
        }

        .stat {
            font-size: 15px;
            margin: 10px;
            border: #E2A09F;
            border-spacing: 70px;
            /*padding: 5px 5px;*/
            padding: 8px 10px 8px 20px; /*top, right, bottom, and left,*/
            border-radius: 6px;
            background-color: #E2A09F;
            background-size: 10px;
        }
        .avatar-container {
            float: start;
        }
        .avatar {
            width: 200px;
            height: 200px;
            margin: 0 auto 20px auto;
/*            background-color: pink;*/
            border-radius: 50%;
            position: relative;
            overflow: hidden;
            display: flex;
            align-items: center;
            justify-content: center;
        }
/*        .avatar-circle {
            width: 80px;
            height: 80px;
            border: 4px solid #ff0077;
            border-radius: 50%;
        }*/

        .taskplayer-title {
            font-size: 1.5em;
            color: #f88;
            margin-top: 0;
            margin-left: auto;
        }
    </style>
</head>
<body>
    <div class="game-container">
<!--        <div class="background background1"></div>
        <div class="background background3"></div>
        <div class="background background2"></div>-->
        <div class="background background_all"></div>

        <header class="header">
            <div class="logo-container">
                <img src="img_dsb/Logo.png" alt="Logo" class="logo">
                <div class="taskplayer-text">
                    <img src="img_dsb/TaskPlayer-1.png" alt="TaskPlayer">
                    <img src="img_dsb/TaskPlayer.png" alt="TaskPlayer">
                </div>
            </div>
            <div class="menu">
                <a class="menu-item" href="#">DASHBOARD</a>     <!-- betulin -->
                <a class="menu-item" href="#">TASK</a>          <!-- betulin -->
                <a class="menu-item" href="#">SHOP</a>         <!-- betulin -->
            </div>
            <a class="menu-item" href="#">LOGOUT</a>     <!-- betulin -->
            <!--<img src="img/Profile picture.png" alt="Profile" class="profile">-->
        </header>
<!--        
        <div class="menu">
        </div>-->
        
        <div class="dashboard-container">
            <img src="img_dsb/dashboardBanner.png" alt="Dashboard" class="dashboard-banner">
            <img src="img_dsb/dashboard text.png" alt="DASHBOARD" class="dashboard-text">
        </div>
        
        <div class="dashboard-card">
            <div class="avatar-container">
                <img src="img_dsb/Profile picture.png" alt="Profile" class="avatar">
            </div>
            <%
//                Dashboard d = (Dashboard)request.getAttribute("list");
                
              
            %>
            
            <div class="stat">NAME: <%  %></div>      <!-- betulin -->
            <div class="stat">POINTS: <%  %></div>          <!-- betulin -->
            <div class="stat">TASK FINISHED: <%  %></div>    <!-- betulin -->
            <div class="stat">TASK PENDING: <%  %></div>     <!-- betulin -->
            <!--<div class="stat">NUMBER OF GROUP Own: 2</div>-->
            <!--<div class="stat">Number of Group Join: 1</div>-->
        </div>
        </div>
    </div>
</body>

</html>

