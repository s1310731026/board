var editdata =localStorage.getItem('editdata');
var editMode = false;

if (editdata){
    editdata = JSON.parse(editdata);
    editMode = true;
    $("#title").replaceWith("<div id=\"title\">編輯公布事項</div>");

    $("#_title").val(editdata.title);
    $("#_publisher").val(editdata.publisher);
    $("#_publishstartdate").val(editdata.publishstartdate);
    $("#_publishenddate").val(editdata.publishenddate);
    $("#_context").val(editdata.context);
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


// 新增按鈕功能
$( "form" ).submit(function( event ) {
    $.ajax({
        type:editMode?"post":"post",
        url:editMode?URL+"/"+editdata.id:URL,
        headers: { "Accept": "application/json"},
        data:{
            title:$("#_title").val(),
            publisher:$("#_publisher").val(),
            publishstartdate:$("#_publishstartdate").val(),
            publishenddate:$("#_publishenddate").val(),
            context:$("#_context").val()
        },
        success:function () {
            console.log("成功")
            window.open(baseURL+"", "_self");
        }
    })
    event.preventDefault();
});
