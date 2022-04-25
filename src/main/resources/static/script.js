$(document).ready(function () {
    $("#bookForm").submit(function (e) {
        e.preventDefault();
        let resultData = {
            title:$("#title").val(),
            isbn:$("#isbn").val()
        };
        $.ajax({
            type: 'POST',
            url: '/books/book-list',
            data: JSON.stringify(resultData),
            contentType: 'application/json',
            success: function (book) {
                appendBook(book);
            },
            error: function (msg) {
                console.log("Send was unsuccessful " + JSON.stringify(msg));
                $("#alert")
                    .text("Error")
                    .addClass("alert-danger").removeClass("alert-success")
                    .removeAttr("hidden");
            }
        });
    })

    $("#isbn-form").submit(function (e) {
        e.preventDefault();
        $("#found-isbn").find(".hidden").css("display","none");
        $("#found-isbn").find("#"+$("#isbn-search").val()).css("display","block")
    });

    $("#title-form").submit(function (e) {
        e.preventDefault();
        $("#found-title").find(".hidden").css("display","none");
        $("#found-title").find("."+$("#title-search").val()).css("display","block")
    });
})

$(document).ready(function () {
    $.ajax({
        type: 'GET',
        url: '/books/book-list/get',
        success: function (books) {
           books.forEach(book => {
               appendBook(book);
           })
        },
        error: function (msg) {
            console.log("Send was unsuccessful " + JSON.stringify(msg));
            $("#alert")
                .text("Error")
                .addClass("alert-danger").removeClass("alert-success")
                .removeAttr("hidden");
        }
    });
})

function appendBook(book){
    console.log("here");
    $("#books-table").find('tbody')
        .append(
            "<tr><td>"+book.title+"</td><td>"+book.isbn+"</td></tr>"
        );

    $("#found-title").find('tbody')
        .append(
            "<tr class='hidden "+book.title+"' id='"+book.isbn+"'><td>"+book.title+"</td><td>"+book.isbn+"</td></tr>"
        );

    $("#found-isbn").find('tbody')
        .append(
            "<tr class='hidden "+book.title+"' id='"+book.isbn+"'><td>"+book.title+"</td><td>"+book.isbn+"</td></tr>"
        );
}