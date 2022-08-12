$( document ).ready(() =>{
    console.log( "ready!" );
    query();

});

// let baseURL = "http://127.0.0.1:5501/";
let baseURL = "http://localhost:8080/board/";
let URL = "http://localhost:8080/board/api/boards";
var boardlist;
var page=1;

//按鈕
$("#querybtn").click(()=>query())
$("#delbtn").click(()=>delrows())
$("#insertbtn").click(function(){
    localStorage.removeItem('editdata');
    window.open(baseURL+"boardedit", "_self");
})
$("#returnbtn").click(function(){
    window.open(baseURL+"", "_self");
})

//查詢按鈕功能
function query(){
    $.ajax({
        type:"get",
        url:URL,
        headers: { "Accept": "application/json"},
        data:{},
        success:function(data){
            // console.log("成功");
            // console.log(data);
            boardlist = data;
            querypages(page);
            
            $(".pagination>").remove();
            $(".pagination").append(
                "<li class='page-item'>"+
                "<a class='page-link' href='#' aria-label='Previous'>"+
                "<span aria-hidden='true' onclick='querypages("+1000+")'>&laquo;</span></a></li>"

                )
            
            for(i=1; i< Math.floor(Object.keys(data).length/5+2);i++){
                $(".pagination").append(
                "<li class='page-item'><a class='page-link' href='#' onclick='querypages("+i+")'>"+i+"</a></li>"
                )
            }
            $(".pagination").append( 
            "<li class='page-item'>"+
            "<a class='page-link' href='#' aria-label='Next'>"+
            "<span aria-hidden='true' onclick='querypages("+1001+")'>&raquo;</span></a></li>"
            )

            if(!data.length>0){
                $("#qempty").show();
            }else{
                $("#qempty").hide();
            }
  

        },
        error:function(x,e) {
            if (x.status==0) {
                alert('You are offline!!\n Please Check Your Network.');
            } else if(x.status==404) {
                alert('Requested URL not found.');
            } else if(x.status==500) {
                alert('Internel Server Error.');
            } else if(e=='parsererror') {
                alert('Error.\nParsing JSON Request failed.');
            } else if(e=='timeout'){
                alert('Request Time out.');
            } else {
                alert('Unknow Error.\n'+x.responseText);
            }
        }
    })
}


function querypages(pages){
    if(pages==1000){
        if(page===1){
            return;
        }
        page--;
        pages = page;
    }
    if(pages==1001){
        page++;
        pages = page;
    }
    page = pages;
    console.log(page);
    data = boardlist;
    $("table>tbody>tr").remove();
    for (const key in data) {
        if (Object.hasOwnProperty.call(data, key)) {
            const e = data[key];
            //排除第一頁  每頁五筆資料
            if( (pages-1)*5 <= Number(key) && Number(key) < pages*5){
                $("table tbody").append(
                    "<tr id="+e?.id+"><td>"+ e?.title +"</td>"+
                    // "<td>"+ e?.context +"</td>"+
                    "<td>"+ e?.publishstartdate +"</td>"+
                    "<td>"+ e?.publishenddate +"</td>"+
                    // "<td>"+ e?.startdate +"</td>"+
                    // "<td>"+ e?.enddate +"</td>"+
                    "<td>"+ "<button type='button' onclick='geteditrow("+e?.id+")'>修改</button>" +"</td>"+
                    "<td>"+ "<button type='button' onclick='delrow("+e?.id+")'>刪除</button>" +"</td>"+
                    +"</tr>");
            }
        }
    }
}


function delrows(){
    // $("table>tbody>tr").remove();
    $.ajax({
        type:"delete",
        url:URL,
        headers: { "Accept": "application/json"},
        data:{},
    }).done(function(){
        query();
    })
}
function delrow(id){
    console.log(id);
    // $("#"+id).remove();
    $.ajax({
        type:"delete",
        url:URL+"/"+id,
        headers: { "Accept": "application/json"},
        data:{},
    }).done(function(){
        query();
    })
}

function geteditrow(id){
    $.ajax({
        type:"get",
        url:URL+"/"+id,
        headers: { "Accept": "application/json"},
        data:{},
        success:function(data){
            localStorage.setItem('editdata',JSON.stringify(data));
            window.open(baseURL+"boardedit", "_self");
        }
    })
}

