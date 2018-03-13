function validate() {
	var result = true;
	var errorMessage = "";
	var name = document.getElementById("name").value;
	var creds = document.getElementById("credits").value;

	 
	
	if (creds.trim() == "") {
		errorMessage = "Enter a valid credit value\n";
		result = false;
	}
	else if (isNaN(creds) || creds < 0) {
		errorMessage = "Not a valid number. Try again!";
		result = false;
	}

	

	if (name.trim() == "") {
		errorMessage = "Enter a valid name";
		result = false;
	}
	
	if (!result) {
		alert(errorMessage);
	}
	
	return result;
}

function runIt(address){
	if(validate()){
		getInformation(address);
	}
}

function getInformation(address){
	
}