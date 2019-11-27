$(document).ready(() => {
  var userInput = document.getElementById("usr");
  var myInput = document.getElementById("psw");
  var myInput2 = document.getElementById("psw2");
  var sumbitButton = document.getElementById("submitBtn");
  // When the user starts to type something inside the password field
  myInput.onkeyup = function() {
    // Validate lowercase letters
    var lowerCaseLetters = /[a-z]/g;
    if (myInput.value.match(lowerCaseLetters)) {
      myInput.classList.remove("is-invalid");
    } else {
      myInput.classList.add("is-invalid");
    }

    //psw matchs psw2
    if (myInput.value == myInput2.value) {
      myInput2.classList.remove("is-invalid");
    } else {
      myInput2.classList.add("is-invalid");
    }

    // Validate capital letters
    var upperCaseLetters = /[A-Z]/g;
    if (myInput.value.match(upperCaseLetters)) {
      myInput.classList.remove("is-invalid");
    } else {
      myInput.classList.add("is-invalid");
    }

    // Validate numbers
    var numbers = /[0-9]/g;
    if (myInput.value.match(numbers)) {
      myInput.classList.remove("is-invalid");
    } else {
      myInput.classList.add("is-invalid");
    }

    // Validate length
    if (myInput.value.length >= 8) {
      myInput.classList.remove("is-invalid");
    } else {
      myInput.classList.add("is-invalid");
    }
  };

  myInput2.onkeyup = function() {
    //psw matchs psw2
    if (myInput.value == myInput2.value) {
      myInput2.classList.remove("is-invalid");
    } else {
      myInput2.classList.add("is-invalid");
    }
  };

  sumbitButton.onclick = function() {
    // REVISAR
    var pswOK = !$("#psw").hasClass("is-invalid") && $("#psw").val() != "";
    var psw2OK = !$("#psw2").hasClass("is-invalid") && $("#psw2").val() != "";
    var userOK = !$("#usr").hasClass("is-invalid") && $("#usr").val() != "";

    if ($("#psw").val() != $("#psw2").val()) event.preventDefault();
  };

  userInput.onkeyup = () => {
    if (userInput.value != "")
      $.get(
        {
          url: `/users/${userInput.value}`,
          data: { masterToken: "bokita" }
        },
        res => {
          res = JSON.parse(res);
          if (res.exists) {
            userInput.classList.remove("is-valid");
            userInput.classList.add("is-invalid");
          } else {
            userInput.classList.remove("is-invalid");
            userInput.classList.add("is-valid");
          }
        }
      );
    else {
      userInput.classList.remove("is-valid");
      userInput.classList.remove("is-invalid");
    }
  };
});
