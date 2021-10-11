$(document).ready(function() {
	var modal = document.getElementById("myModal");
	var weekInsertBtn = document.getElementById("weekInsertBtn");
	var cancelWeekInsert = document.getElementById("cancelWeekInsert");

	if(weekInsertBtn != null){
		weekInsertBtn.onclick = function() {
			modal.style.display = "block";
		}
	}
	
	cancelWeekInsert.onclick = function() {
		modal.style.display = "none";
	}
	
	var modal2 = document.getElementById("myModal2");
	 var weekUpdateBtn = document.getElementById("weekUpdateBtn");
	var cancelWeekChange = document.getElementById("cancelWeekChange");
	
	if(weekUpdateBtn != null){
		weekUpdateBtn.onclick = function() {
			if (endDate != '') {
				modalChangeCheck.checked = true;
				modalP2.style.display = "block";
			  modalChangeDate2.style.display = "block";
			  modalChangeDate2.value = endDate;
			}
			modal2.style.display = "block";
		}
	}
	
	cancelWeekChange.onclick = function() {
		modal2.style.display = "none";
	}

	window.onclick = function(event) {
		if (event.target == modal) {
			modal.style.display = "none";
		}
		
		if (event.target == modal2) {
			modal2.style.display = "none";
		}
	}
});