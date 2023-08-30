function loesche(id) {
    $.ajax({
        type: "DELETE",
        url: "/banken/" + id,
        accept: "application/json",
        dataType: "json",
        success: function (data, status) {
            $.each(data, function(index, element) {
                console.log(data);
                console.log(element.text);
                $("body").append($("<div>", {
                    text: element.text
                }));
            })
        },
        error: function (jqXHR, status) {
        }
    })
}
