function submitForm(id) {
  event.preventDefault();
  var form = document.getElementById(id);
  let c = form.children;
  let request = {};
  for (i = 0; i < c.length; i++) {
    if (c[i].tagName === "INPUT") request[c[i].name] = c[i].value;
  }
  //console.log( request)
  fetch(form.getAttribute("action"), {
    method: form.getAttribute("method"),
    body: JSON.stringify(request),
    headers: {
      "Content-Type": "application/json",
      "Access-Control-Allow-Origin": "*"
    }
  }).then(setTimeout(() => location.reload(), 300));
}
