var editdata =localStorage.getItem('editdata');
var editMode = false;
var filedata;
let form = new FormData();

if (editdata){
    editdata = JSON.parse(editdata);
    editMode = true;
    $("#title").replaceWith("<div id=\"title\">編輯公布事項</div>");

    $("#_title").val(editdata.title);
    $("#_publisher").val(editdata.publisher);
    $("#_publishstartdate").val(editdata.publishstartdate);
    $("#_publishenddate").val(editdata.publishenddate);
    $("#_context").val(editdata.context);
    if (editdata?.file != null){
        $("#imgblob").append('<p><img width="200" src="data:image/jpeg;base64,' + editdata.file + '" /></p>');
    }
}
function editrow(id){
    $.ajax({
        type:"put",
        url:URL+"/"+id,
        headers: { "Accept": "application/json"},
        data:{
            title:$("#_title").val(),
            publisher:$("#_publisher").val(),
            publishstartdate:$("#_publishstartdate").val(),
            publishenddate:$("#_publishenddate").val(),
            context:$("#_context").val()
        },
    }).done(function(){
        window.open(baseURL+"", "_self");
    })
}


// 新增修改按鈕功能
$( "form" ).submit(function( event ) {
    form.append("title", $("#_title").val())
    form.append("publisher", $("#_publisher").val())
    form.append("publishstartdate", $("#_publishstartdate").val())
    form.append("publishenddate", $("#_publishenddate").val())

    form.append("context",$("#_context").val())

    $.ajax({
        type:editMode?"post":"post",
        url:editMode?URL+"/"+editdata.id:URL,
        headers: { "Accept": "application/json"},
        processData : false,
        contentType : false,
        data: form
    // {
    //         file:filedata,
    //         title:$("#_title").val(),
    //         publisher:$("#_publisher").val(),
    //         publishstartdate:$("#_publishstartdate").val(),
    //         publishenddate:$("#_publishenddate").val(),
    //         context:$("#_context").val()
    //     }
        ,
        success:function () {
            console.log("成功")
            window.open(baseURL+"", "_self");
        }
    })
    event.preventDefault();
    return false;
});


//
$("#_file").change( e => {
    form.append("file", e.target.files[0])
})
