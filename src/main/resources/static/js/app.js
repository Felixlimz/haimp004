function check_pass() {
    if (document.getElementById('password').value ==
            document.getElementById('confirm_password').value) {
        document.getElementById('submit').disabled = false;
    } else {
        document.getElementById('submit').disabled = true;
    }
}

function func() {
  if (document.getElementById("stock").value == 0) {
        document.getElementById('submit').style.display = "none";
        document.getElementById('message').style.display = "";
  } else {
        document.getElementById('submit').style.display = "";
        document.getElementById('message').style.display = "none";
  }
}

//$(document).ready(function () {
//    if($('#stock').val() == 0)
//    {
//       document.getElementById('submit').disabled = true;
//    } else {
//        document.getElementById('submit').disabled = false;
//    }
//});