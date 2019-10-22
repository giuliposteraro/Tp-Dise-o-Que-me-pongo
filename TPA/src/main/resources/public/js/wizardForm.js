function actualizarTelas() {
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
}
