function saveThisJobToCart(obj) {
	$.ajax({
		type : "post",
		url : "savejobtocart.action",
		data : {
			jobid : obj
		},
		success : function() {
			alert("Successfully add it to the cart!");
		}
	});
}
function DeleteJobInCart(obj) {
	$.ajax({
		type : "post",
		url : "deletejobincart.action",
		data : {
			jobid : obj
		},
		success : function() {
			location.reload();
		}
	});
}
function DeleteFeed(obj) {
	$.ajax({
		type : "post",
		url : "deletefeed.action",
		data : {
			feedid : obj
		},
		success : function() {
			location.reload();
		}
	});
}
function CreateFeed() {
	var title = $("[name='createtitle']");
	var location = $("[name='createlocation']");
	$.ajax({
		type : "post",
		url : "createfeed.action",
		data : {
			title : title,
			location : location
		},
		success : function() {
		}
	});
}
function mouseOver(obj) {
	obj.style.backgroundColor = '#fff';
}
function mouseOut(obj) {
	if (obj != document.activeElement)
		obj.style.backgroundColor = '#F7F8F8';
}
function blurinput(obj) {
	obj.style.backgroundColor = '#F7F8F8';
}