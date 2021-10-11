
$(document).ready(function(){

    // -----------------regist modal1-------------------------
    var modal = document.getElementById("myModal");
    
    // Get the button that opens the modal
    var btn = document.getElementById("myBtn");
    
    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];
    
    // When the user clicks on the button, open the modal
    btn.onclick = function() {
    	let date = document.getElementById("date").value;
    	let today = new Date();
    	today.setHours(0);
    	today.setSeconds(0);
    	today.setMilliseconds(0);
    	let visitDay = new Date(date);
    	
    	if (visitDay < today) {
    		alert("지난 날짜는 등록할 수 없습니다.");
    	} else {
    		modal.style.display = "block";
    	}
    }
    
    // When the user clicks on <span> (x), close the modal
    span.onclick = function() {
        modal.style.display = "none";
    }
    
    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal) {
            console.log(event.target);
            modal.style.display = "none";
        } 
    }
    // -----------------regist modal2-------------------------
    var modal2 = document.getElementById("myModal2");

    // Get the button that opens the modal
    var btn2 = document.getElementById("cancle_btn");

    // Get the <span> element that closes the modal
    var back = document.getElementById("back");

    // When the user clicks on the button, open the modal
    btn2.onclick = function() {
    modal2.style.display = "block";
    }

    // When the user clicks on <span> (x), close the modal
    back.onclick = function() {
        modal2.style.display = "none";
    }

// When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal) {
            console.log(event.target);
            modal.style.display = "none";
        } 

    if (event.target == modal2) {
        modal2.style.display = "none";
        } 
    }

    

});
