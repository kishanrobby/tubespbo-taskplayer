<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shop Page</title>
    <link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Press Start 2P', monospace;
            background-color: #000000;
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
            width: 2560px;
            height: 1440px;
        }

        .background1 {
            background: url('img_shop/Background1.png') no-repeat center center;
            background-size: cover;
            z-index: -3;
        }
        .background2 {
            background: url('img_shop/Background2.png') no-repeat center center;
            background-size: cover;
            z-index: -2;
        }
        .background3 {
            background: url('img_shop/Background3.png') no-repeat center center;
            background-size: cover;
            z-index: -1;
        }

        .header {
            position: absolute;
            top: 20px;
            left: 20px;
            right: 30px;
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
            height: 80px;
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
            top: 50px;
            left: 54%;
            transform: translateX(-50%);
            display: flex;
            justify-content: center;
            gap: 50px;
            z-index: 2;
        }

        .menu-item {
            font-size: 15px;
            color: #ffffff;
            text-decoration: none;
            cursor: pointer;
        }

        .menu-item:hover {
            text-decoration: underline;
        }
		
	.menu-item:nth-child(3) { /* SHOP menu */
            animation: glow 1.5s infinite alternate;
        }

        @keyframes glow {
            0% {
                text-shadow: 0px 0px 5px #ffffff, 0px 0px 10px #ffffff, 0px 0px 20px #ffffff;
            }
            100% {
                text-shadow: 0px 0px 15px #ffffff, 0px 0px 30px #ffffff, 0px 0px 45px #ffffff;
            }
        }
		
        .welcome-container {
            position: absolute;
            top: 100px;
            left: 52%;
            transform: translateX(-50%);
            z-index: 2;
            width: 400px;
            height: 80px;
        }

        .welcome-banner {
            position: absolute;
            width: 120%;
            height: 120%;
        }

        .welcome-text {
            position: absolute;
            top: 58%;
            left: 59%;
            transform: translate(-50%, -50%);
            width: 80%;
            height: auto;
        }

        .book-container {
            position: absolute;
            top: 140px;
            left: 55%;
            transform: translateX(-50%);
            width: 950px;
            height: 650px;
            background: url('img_shop/book.png') no-repeat center;
            background-size: contain;
        }

        .character-grid {
            position: absolute;
            top: 120px;
            left: 75px;
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            grid-template-rows: repeat(2, 1fr);
            gap: 40px;
            width: 300px;
            align-items: center;
            justify-items: center;
        }

        /* Character container */
        .character {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            cursor: pointer;
            position: relative;
            gap: 5px;
        }

        .character img {
            width: 90px;
            height: 90px;
            object-fit: contain;
        }

        /* Character name styling */
        .character-name {
            margin-top: 5px;
            font-size: 14px;
            color: #000000;
            text-align: center;
            font-family: 'Press Start 2P', monospace;
        }

        .character.selected::before {
            content: '';
            position: absolute;
            top: -40px;
            left: -20px;
            right: -20px;
            bottom: -40px;
            background: url('img/Char Select.png') no-repeat center center;
            background-size: contain;
            z-index: -1;
        }

        .merchant {
            position: absolute;
            bottom: 130px;
            left: 100px;
            width: 280px;
            height: 450px;
        }

        .points-display {
            position: absolute;
            bottom: 40px;
            left: 115px;
            background: rgba(0, 0, 0, 0.5);
            color: white;
            padding: 15px 30px;
            border-radius: 10px;
            font-size: 16px;
        }

        .merchant-dialog {
            position: absolute;
            bottom: 20px;
            left: 350px;
            width: 600px;
            height: 120px;
            background: url('img_shop/MerchantTextBanner.png') no-repeat center;
            background-size: contain;
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 14px;
            padding: 20px;
            text-align: center;
            font-family: 'Press Start 2P', monospace;
            line-height: 1.8;
        }

        .dialogue-text {
            width: 50%;
            height: auto;
            max-height: 80px;
            overflow: hidden;
            white-space: normal;
            word-wrap: break-word;
            color: #000;
            text-align: center;
            padding-bottom: 12px;
        }

        .button-container {
            position: absolute;
            bottom: 40px;
            right: 390px;
            display: flex;
            gap: 20px;
        }

        .button {
            width: 190px;
            height: 80px;
            border: none;
            cursor: pointer;
            background-size: contain;
            background-repeat: no-repeat;
            background-position: center;
            background-color: transparent;
        }

        .back-button {
            background-image: url('img/Back Button.png');
        }

        .buy-button {
            background-image: url('img/Buy Button.png');
        }

        .garuda-color {
            color: #40E0D0;
        }

        .frog-color {
            color: #90EE90;
        }

        .pig-color {
            color: #F08080;
        }

        .goat-color {
            color: #A99999;
        }
    </style>
</head>
<body>
    <div class="game-container">
        <div class="background background1"></div>
        <div class="background background2"></div>
        <div class="background background3"></div>

        <header class="header">
            <div class="logo-container">
                <img src="img_shop/Logo.png" alt="Logo" class="logo">
                <div class="taskplayer-text">
                    <img src="img_shop/TaskPlayer.png" alt="TaskPlayer">
                    <img src="img_shop/TaskPlayer-1.png" alt="TaskPlayer">
                </div>
            </div>
            <div class="menu">
                <a class="menu-item" href="#">DASHBOARD</a>
                <a class="menu-item" href="#">TASK</a>
                <a class="menu-item" href="#">SHOP</a>
            </div>
            <img src="img_shop/Profile.png" alt="Profile" class="profile">
        </header>

        <div class="welcome-container">
            <img src="img_shop/WelcomeBanner.png" alt="Welcome Banner" class="welcome-banner">
            <img src="img/Welcome Text.png" alt="Welcome Adventurer!" class="welcome-text">
        </div>

        <div class="book-container">
            <div class="character-grid">
                <div class="character" data-character="GARUDA" onclick="selectCharacter('GARUDA')">
                    <img src="img_shop/Bird.png" alt="GARUDA">
                    <span class="character-name">GARUDA</span>
                </div>
                <div class="character pig" data-character="PIGLET" onclick="selectCharacter('PIGLET')">
                    <img src="img_shop/Pig.png" alt="PIGLET">
                    <span class="character-name">PIGLET</span>
                </div>
                <div class="character frog" data-character="FROGY" onclick="selectCharacter('FROGY')">
                    <img src="img_shop/Frog.png" alt="FROGY">
                    <span class="character-name">FROGY</span>
                </div>
                <div class="character goat" data-character="THE GOAT" onclick="selectCharacter('THE GOAT')">
                    <img src="img_shop/Goat.png" alt="THE GOAT">
                    <span class="character-name">THE GOAT</span>
                </div>
            </div>
        </div>

        <img src="img_shop/Merchant.png" alt="Merchant" class="merchant">

        <div class="points-display">POINTS : <span id="points-value"></span></div>

        <div class="merchant-dialog">
            <div class="dialogue-text">
                Click a character to see the price...
            </div>
        </div>

        <div class="button-container">
            <button class="button back-button" onclick="window.location.href='about:blank'"></button>
            <button class="button buy-button" id="buyButton" onclick="buyCharacter()"></button>
        </div>
    </div>

    <script>
        let points = 500; // Updated initial points, this can be dynamically set

        // Function to update the points display in HTML
        function updatePointsDisplay() {
            document.getElementById('points-value').innerText = points;
        }

        // Initial points display call
        updatePointsDisplay();

        let selectedCharacter = null;

        function selectCharacter(characterName) {
            const characters = document.querySelectorAll('.character');
            const dialogue = document.querySelector('.dialogue-text');
            let dialogueText = "";

            // Reset the color classes
            dialogue.classList.remove('garuda-color', 'frog-color', 'pig-color', 'goat-color');

            switch (characterName) {
                case 'GARUDA':
                    dialogueText = "<span class='garuda-color'>GARUDA</span> will be 100 points.";
                    break;
                case 'PIGLET':
                    dialogueText = "<span class='pig-color'>PIGLET</span> will be 70 points.";
                    break;
                case 'FROGY':
                    dialogueText = "<span class='frog-color'>FROGY</span> will be 150 points.";
                    break;
                case 'THE GOAT':
                    dialogueText = "<span class='goat-color'>THE GOAT</span> will be 200 points.";
                    break;
                default:
                    dialogueText = "Click a character to see the price...";
            }

            dialogue.innerHTML = dialogueText;

            // Highlight selected character
            characters.forEach((character) => {
                if (character.dataset.character === characterName) {
                    character.classList.add('selected');
                    selectedCharacter = characterName;
                } else {
                    character.classList.remove('selected');
                }
            });

            // Enable buy button
            document.getElementById('buyButton').disabled = false;
        }

        function redirect() {
            window.location.href = 'about:blank';
        }

        function buyCharacter() {
            if (selectedCharacter) {
                let price = 0;

                // Determine the price of the selected character
                switch (selectedCharacter) {
                    case 'GARUDA':
                        price = 100;
                        break;
                    case 'PIGLET':
                        price = 70;
                        break;
                    case 'FROGY':
                        price = 150;
                        break;
                    case 'THE GOAT':
                        price = 200;
                        break;
                    default:
                        alert('Please select a character first!');
                        return;
                }

                // Check if the user has enough points
                if (points >= price) {
                    // Deduct points for the purchase
                    points -= price;

                    // Update points display
                    updatePointsDisplay();

                    // Directly display the success message
                    alert("Purchased successfully!");

                    // After a successful purchase, reset dialogue
                    const dialogue = document.querySelector('.dialogue-text');
                    dialogue.innerHTML = "Click a character to see the price...";

                    // Deselect the selected character
                    const characters = document.querySelectorAll('.character');
                    characters.forEach((character) => {
                        character.classList.remove('selected');
                    });

                    // Reset selectedCharacter to null
                    selectedCharacter = null;

                    // Disable the buy button until a new character is selected
                    document.getElementById('buyButton').disabled = true;
                } else {
                    alert('You do not have enough points to make this purchase!');
                }
            } else {
                alert('Please select a character first!');
            }
        }
    </script>
</body>
</html>