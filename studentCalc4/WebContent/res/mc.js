/**
 * 
 */function validate(){
	 var ok = true;
	 
	 var p = document.getElementById("principal").value;
	 if (isNaN(p) || p < 0){
		 alert("Principal invalid! Must be non-negative numnber");
		ok = false;
	 }
	 
	 if (!ok) return false;
	 
	 p = document.getElementById("interest").value;
	 if (isNaN(p) || p <= 0 || p >= 100){
		 alert("Interest invalid! Must be in the range (0,100].");
		 ok = false;
	 }
	 
	 if (!ok) return false;
	 
	 p = document.getElementById("period").value;
	 if (isNaN(p) || p <= 0){
		 alert("Period invalid. Must be greater than 0.");
		 ok = false;
}
return ok;
}