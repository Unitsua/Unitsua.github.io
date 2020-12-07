function form_submit(){
	document.getElementById("login").submit();
}
function form_reset(){
	document.getElementById("login").reset();
}
function reloadcode(){
    var verify=document.getElementById('safecode');
    verify.setAttribute('src','images/checkcodes.png?'+Math.random());
}
//多行删除函数
function deleteSelected() {
    var url = "/backend/article?";
    //因为所有频道复选框的name属性是一样的name=cid，通过name属性选取到所有的复选框
    //这里面取到的是所有name=cid的复选框，这里面既有用户选择的也有未选的
    var as = document.getElementsByName("aid");
    //所以要过滤出所有被选中的复选框，取出值，绑定到查询字符串
    for (var i=0; i<as.length; i++){
        if (as[i].checked){
            var aid = as[i].value;
            url += "aid=" + aid + "&"
        }
    }
    //大体会生成/backend/channelDelete?cid=1&cid=2&cid=3
    location = url + "method=delete";
}