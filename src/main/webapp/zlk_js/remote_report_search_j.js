$("#remote_report_search_j").click(function(){
    BJUI.navtab({
        id:'remote_report_search',
        url:'zlk_html/remote_search_list_j.jsp?modality=' + $("#modality").val()
        + '&pat_type=' + $("#pat_type").val()
        + '&dateBegin=' + $("#dateBegin").val()
        + '&dateEnd=' + $("#dateEnd").val()
        + '&name=' + $("#name").val()
        + '&ID=' + $("#ID").val()
        + '&number=' + $("#number").val()
        + '&clinic_id=' + $("#clinic_id").val(),
        title:'报告列表'
    });
});
function checkReport_Operation(value,data) {
    if(data.check_number == "没有数据！"){
        var html = null;
    }else{
        var html = '<button type="button" class="btn-blue" onclick="dialog_report(\''+data.check_number+'\');">查看报告</button>';
    }
    return html;
}
function dialog_report(check_number) {
    BJUI.dialog({
        id:'reportImageToday_check_number-'+check_number,
        url:'zlk_html/remote_report_image_j.jsp?check_number='+check_number,
        height: 890,
        width: 830,
        title:'检查报告单'
    });
}