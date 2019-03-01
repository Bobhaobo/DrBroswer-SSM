function openOrder(){
    BJUI.navtab({
        id:'arrangeOrder',
        url:'zlk_html/view_reservation_m.html',
        title:'查看预约'
    })
}
function local_waitforReport(){
    BJUI.navtab({
        id:'hadChecked',
        url:'zlk_html/report_hadchecked_m.html',
        title:'本地'
    })
}
function remote_waitforReport(){
    BJUI.navtab({
        id:'hadWritted',
        url:'zlk_html/remote_wait_report.html',
        title:'远程'
    })
}
function local_hadwritedReport(){
    BJUI.navtab({
        id:'ultrasound_hadChecked',
        url:'zlk_html/report_hadWritted_m.html',
        title:'本地'
    })
}
function remote_hadwritedReport(){
    BJUI.navtab({
        id:'ultrasound_hadWritted',
        url:'zlk_html/remote_writted_report.html',
        title:'远程'
    })
}