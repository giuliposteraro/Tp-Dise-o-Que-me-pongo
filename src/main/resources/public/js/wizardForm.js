$(document).ready(() => {
  $("#tipo").change(() => {
    if ($("#tipo").val() == "") $("#btnTipo").attr("disabled", true);
    else $("#btnTipo").removeAttr("disabled");
  });

  $("#color2").attr("disabled", true);

  $("#color1, #color2").change(() => {
    if (
      $("#color1").val() == "" ||
      $("#color2").val() == "" ||
      $("#color1").val() == $("#color2").val()
    ) {
      $("#btnColor").attr("disabled", true);
      if ($("#color1").val() == $("#color2").val())
        $("#color2").addClass("is-invalid");
    } else {
      $("#btnColor").removeAttr("disabled");
      if ($("#color1").val() != $("#color2").val())
        $("#color2").removeClass("is-invalid");
    }
    if($("#color1").val() == ""){
      $("#color2").attr("disabled", true);
      $("#color2").val("Ninguno");
    }else{
      $("#color2").attr("disabled", false);
    }
  });

  $("#tela").change(() => {
    if ($("#tela").val() == "") $("#btnTela").attr("disabled", true);
    else $("#btnTela").removeAttr("disabled");
  });
});

actualizarTelas = () => {
  var tipo = $("#tipo")
    .val()
    .toLowerCase();
  $.get(`/clothes/${tipo}/fabrics`, res => {
    $("#tela")
      .empty()
      .append('<option value="">Seleccione una tela...</option>');
    JSON.parse(res).forEach(tela => {
      $("#tela").append(`<option value="${tela}">${tela}</option>`);
    });
  });
};
