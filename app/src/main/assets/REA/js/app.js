$(document).ready(function(){

	// GAME WORDS
	var wordList = [
		{
			originalWord: "WALES",
			jumbleOne: "SBWLE",
			jumbleTwo: "WESEL",
			jumbleThree: "SWEAL",
			jumbleFour: "SWSLA",
			correctAnswer: "SWEAL"
		},
		{
			originalWord: "AMERICA",
			jumbleOne: "RCIAEMA",
			jumbleTwo: "COMARE",
			jumbleThree: "RICEAEM",
			jumbleFour: "EEMCIAR",
			correctAnswer: "RCIAEMA"
		},
		{
			originalWord: "RUSSIA",
			jumbleOne: "ISIRAS",
			jumbleTwo: "RUIASS",
			jumbleThree: "SUSIRU",
			jumbleFour: "TISSAU",
			correctAnswer: "RUIASS"
		},
		{
			originalWord: "ZIMBABWE",
			jumbleOne: "EBOZMBWI",
			jumbleTwo: "WEBAZIBM",
			jumbleThree: "WBZMIEBB",
			jumbleFour: "BZIAIBWM",
			correctAnswer: "WEBAZIBM"
		},
		{
			originalWord: "WELCOME",
			jumbleOne: "EMOLEWC",
			jumbleTwo: "LMOEEWL",
			jumbleThree: "WCEMOEE",
			jumbleFour: "COEELME",
			correctAnswer: "EMOLEWC"
		},
		{
			originalWord: "APTITUDE",
			jumbleOne: "TTPEDUAE",
			jumbleTwo: "TTEDPUAI",
			jumbleThree: "APETIUDI",
			jumbleFour: "PEATIUDI",
			correctAnswer: "TTEDPUAI"
		}
	];

	var currentWord = 0; // start with word at array position 0

	// get words from word list starting at position 0
	var mainWord = wordList[currentWord].originalWord;
	var jumbleOne = wordList[currentWord].jumbleOne;
	var jumbleTwo = wordList[currentWord].jumbleTwo;
	var jumbleThree = wordList[currentWord].jumbleThree;
	var jumbleFour = wordList[currentWord].jumbleFour;
	var correctAnswer = wordList[currentWord].correctAnswer;

	// GAME MECHANICS 
	// set time modes
	var easyTime = 10;
	var mediumTime = 80;
	var hardTime = 60;
	var extremeHardTime = 30;
	var score=0;

	var gameTimeMode = mediumTime;

	var tries;

	function initTries () { // create function to encapsulate tries code
		tries = 2;
		$('#triesText').text(tries); // show initial tries count on page
	}

	initTries(); // use function to set up tries

	// set initial time countdown
	var currentTime = gameTimeMode;
	$("#timerText").text(currentTime);
	currentTime--;	

	// setup variables
	/*$("#timerText")  
	$("#jumbles")
	$("#result")

	$("#tryAgain")
	$("#continue") */

	function decreaseTime(){
		if (currentTime > 0) {
			$("#timerText").text(currentTime);
			currentTime--;	
		} else {
			$("#timerText").text(currentTime);
			clearInterval(intervalHandleTime);
			$("#mainWord").text('');
			$("#jumbles").hide();
			$("#badResult").text("You ran out of time");
			$('#result').show();
			$("#tryAgain").show();
			$("#continue").hide();
			tries--;
			$('#triesText').text(tries);
		}
	}

	// decreaseTime function runs every x number of seconds
	intervalHandleTime = setInterval(decreaseTime, 100);

	// function to pause and unpause time
	$('#milliseconds').on('click', function (){
		if (intervalHandleTime) {
			clearInterval(intervalHandleTime);
			intervalHandleTime = false;
		} else {
			intervalHandleTime = setInterval(decreaseTime, 100);
		}
	});

	// function to increase number of tries
	$('#tries').on('click', function () {
		if (tries >= 10) {
			// do nothing
		} else { // else increase tries
			tries++;
			$('#triesText').text(tries);
		}
		
	});

	// display words on screen
	$("#mainWord").text(mainWord); // this is the main word
	$("#jumbleOne").text(jumbleOne);
	$("#jumbleTwo").text(jumbleTwo);
	$("#jumbleThree").text(jumbleThree);
	$("#jumbleFour").text(jumbleFour);

	// check if any tries are left
	function checkTries (num) {
		if (num <= 0) {
			return false;
		} else {
			return true;
		}
	}

	// func to determine if selected word is correct
	function determineIfCorrect (){
		clearInterval(intervalHandleTime);
		$("#mainWord").text('');
		if ($(this).text() == correctAnswer) {
			$("#jumbles").hide();
			$("#result").show();
			$("#goodResult").text(correctAnswer + " is correct");
			$("#continue").show();	
			score++;
			//alert(correctAnswer + "  is correct!");
			return; // prevents further execution of function
		} else {
			tries--; // if answer if wrong, reduce tries
			$('#triesText').text(tries); // update triesText on view
			if(tries==0)
			{
				window.location="https://www.google.com"+score;
			}
		} 

		if (!(checkTries(tries))) { // check if there are any tries left
			$("#jumbles").hide();
			$("#result").show();
			$("#badResult").html($(this).text() + " is incorrect <br> You've run out of tries");
			$("#startAgain").show();
		} else {
			//alert('hi');
			$("#jumbles").hide();
			$("#result").show();
			$("#badResult").text($(this).text() + " is incorrect");
			$("#tryAgain").show();
			$('#triesText').text(tries);
			//alert($(this).text() + " is incorrect.");
		}
	}

	// start from beginning
	function startAgain() {
		currentWord = 0; // start from first word again
		$("#startAgain").hide(); // hide the start again button 
		initTries(); // reset tries

		mainWord = wordList[currentWord].originalWord;
		jumbleOne = wordList[currentWord].jumbleOne;
		jumbleTwo = wordList[currentWord].jumbleTwo;
		jumbleThree = wordList[currentWord].jumbleThree;
		jumbleFour = wordList[currentWord].jumbleFour;
		correctAnswer = wordList[currentWord].correctAnswer;

		// display words on screen
		$("#mainWord").text(mainWord); // this is the main word
		$("#jumbleOne").text(jumbleOne);
		$("#jumbleTwo").text(jumbleTwo);
		$("#jumbleThree").text(jumbleThree);
		$("#jumbleFour").text(jumbleFour);

		$("#jumbles").show();
		$("#result").hide();
		$("#tryAgain").hide();
		$("#continue").hide();
		$("#badResult").text('');
		$("#goodResult").text('');

		currentTime = gameTimeMode;
		intervalHandleTime = setInterval(decreaseTime, 100);
	}

	// try current word again
	function tryAgain() {	
		$("#mainWord").text(mainWord); 	
		$("#jumbles").show();
		$("#result").hide();
		$("#tryAgain").hide();
		$("#continue").hide();
		$("#badResult").text('');
		$("#goodResult").text('');

		mainWord = wordList[currentWord].originalWord;
		jumbleOne = wordList[currentWord].jumbleOne;
		jumbleTwo = wordList[currentWord].jumbleTwo;
		jumbleThree = wordList[currentWord].jumbleThree;
		jumbleFour = wordList[currentWord].jumbleFour;
		correctAnswer = wordList[currentWord].correctAnswer;

		currentTime = gameTimeMode;
		intervalHandleTime = setInterval(decreaseTime, 100);
	}

	// continue to next word
	function nextWord() {
		// increase position in array
			currentWord++;

		// checks if we've completed all the words
		if (currentWord <= (wordList.length - 1)) {
			//alert((wordList.length - 1));

			// show and hide stuff
			$("#jumbles").show();
			$("#result").hide();
			$("#tryAgain").hide();
			$("#continue").hide();
			$("#badResult").text('');
			$("#goodResult").text('');

			// set timings
			currentTime = gameTimeMode;
			intervalHandleTime = setInterval(decreaseTime, 100);

			// assign variables
			mainWord = wordList[currentWord].originalWord;
			jumbleOne = wordList[currentWord].jumbleOne;
			jumbleTwo = wordList[currentWord].jumbleTwo;
			jumbleThree = wordList[currentWord].jumbleThree;
			jumbleFour = wordList[currentWord].jumbleFour;
			correctAnswer = wordList[currentWord].correctAnswer;

			// display new words on screen
			$("#mainWord").text(mainWord);
			$("#jumbleOne").text(jumbleOne);
			$("#jumbleTwo").text(jumbleTwo);
			$("#jumbleThree").text(jumbleThree);
			$("#jumbleFour").text(jumbleFour);
		} else {
		    window.location="https://www.google.com"+score;
			$("#mainWord").text("");
			$("#timer").hide();
			$("#badResult").text('');
			$("#goodResult").html("You win! <br> With " + tries + " tries remaining.");
			$("#continue").hide();
			$('#selectWord').text('');
			//$("#startAgain").show();
		}
	}

	// click on word
	$(".jumbleButton").click(determineIfCorrect);

	// try again and continue buttons
	$("#tryAgain").click(tryAgain);
	$("#continue").click(nextWord);
	$("#startAgain").click(startAgain);

	$('#tryAgain').keypress(function(e){
      if(e.keyCode==13)
      $('#tryAgain').click();
    });
});
