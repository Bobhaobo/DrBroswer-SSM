function detail(value,data) {
    // alert(data.patName);
    if(data.checknumber == "没有数据！"){
        var html = null;
    }else{
        var html = '<button type="button" class="btn-blue" onclick="flow_detail(\''+data.checknum+'\');">查看详情</button>';
    }
    return html;
}



function flow_detail(checknum) {
    BJUI.dialog({
        id:'flow_detail-'+checknum,
        url:'zlk_html/flow_detail.jsp?checknum='+checknum,
        height: 890,
        width: 830,
        title:'流程详情'
    });
}