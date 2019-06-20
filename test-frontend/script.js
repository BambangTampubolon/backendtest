$(document).ready(function(){
	$('#mobile_number').focusout(function(){
		isExistMobileNumber($('#mobile_number').val());
	});
});

$(document).ready(function(){
	$('#email').focusout(function(){
		isExistEmail($('#email').val());
	});
});

$(document).ready(function(){
	$( "#user_form" ).submit(function(event) {
		event.preventDefault();
		saveNewUser();
	});
});


function isExistMobileNumber(mobileNumber){
	console.dir(mobileNumber);
	$.ajax({
		type: 'GET',
		url: 'http://localhost:8081/api.test/v1/User/' + mobileNumber,
		dataType: "json",
		crossOrigin: true
	}).then(function(data){
		if(data.content.status == 200){
			alert('Mobile number already exist');
		}
	});
}

function isExistEmail(email){
	$.ajax({
		type: 'GET',
		url:'http://localhost:8081/api.test/v2/User/?email='+ email,
		dataType: "json",
		crossOrigin: true
	}).then(function(data){
		if(data.content.status == 200){
			alert('Email already exist');
		}
		console.dir(data.content);
	});
}
function disableForm(){
	document.getElementById("user_form").reset();
	var form = document.getElementById("user_form");
	var elements = form.elements;
	var len = elements.length;
	for(var index = 0; index < len; index++){
		elements[index].disabled = true;
	}
	document.getElementById("button-left").style.visibility = "visible";
	document.getElementById("submit_button").style.opacity = "0.4";
	document.getElementById("footer_main").style.backgroundColor="#e7e7e7";

}

function saveNewUser(){	
	$.ajax({
		type: 'POST',
		url: 'http://localhost:8081/api.test/v1/User',
		dataType: 'json',
		contentType: 'application/json; charset=utf-8',
		data: formToJSON(),
		success: function(data){
			console.dir(data)
            alert("Sucess insert data with id:" + data.content.detailmessage);
			disableForm();
        },
		error: function(data){
			console.dir(data)
			alert(data.content.detailmessage);
		}
	});
}

function formToJSON() {
	var gender = null;
	if (document.getElementById('gender_male').checked) {
		gender = true;
	}else if (document.getElementById('gender_female').checked) {
		gender = false;
	}else{
		gender = null;
	}
    return JSON.stringify({
        "gender": gender,
        "mobile_number": $('#mobile_number').val().toString(),
        "first_name": $('#first_name').val(),
        "last_name": $('#last_name').val(),
        "email": $('#email').val(),
		"date_of_birth" : $('#birth-date').val().toString(),
        });
}

function validateForm(){
	valid = true;
	if(document.user_form.mobileNumber.value = ""){
		alert("Please ");
	}
}

function invalidMobileNumber(textbox){
	console.log(textbox.value.toString());
	if(textbox.value == ""){
		textbox.setCustomValidity('Required mobile phone number');
	}else if(!/^(?=\d{12,13}$)(62)\d+/.test(textbox.value.toString()) ){
		textbox.setCustomValidity('Please enter valid indonesia phone number');
	}else{
		textbox.setCustomValidity('');
	}
}

function invalidEmail(textbox){
	if(textbox.value == ""){
		textbox.setCustomValidity('Required email');
	}else if(!/^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$/.test(textbox.value.toString()) ){
		textbox.setCustomValidity('Please enter valid email address');
	}else{
		textbox.setCustomValidity('');
	}
}
	
