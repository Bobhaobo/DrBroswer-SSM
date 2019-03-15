
cornerstoneWADOImageLoader.external.cornerstone = cornerstone;

var imageIdlist;
//接收后台传过来的图像集合
$.ajax({
    async:false,
    cache:true,
    traditional:true,//
    type: 'post',
    dataType: 'json',
    url: '/get/sendDcm1',
    /*data: {xy:JSON.stringify(xy)},*/
    data : {
    },
    success: function (data) {
        imageIdlist = data;

        /*imageIdlist = data[0].map(function (item) {
            return 'wadouri:'+item;
        })*/
    },
    error: function(){

    }
});
//判断是否加载
let loaded = false;
//获取canvas的画布的区域
/*var element = document.getElementById('dicomImage');*/
//执行
/*var series = imageIdlist[0].map(function (item) {
    return 'wadouri:'+item;
})*/
/*statckImage(series);

function statckImage(imageIdslist) {
    var stack = {
        currentImageIdIndex: 0,
        imageIds: imageIdslist
    };
    cornerstone.enable(element);
    cornerstoneTools.mouseInput.enable(element);
    cornerstoneTools.mouseWheelInput.enable(element);
    cornerstone.loadAndCacheImage(series[stack.currentImageIdIndex]).then(function (image) {
        // display this image
        var viewport = cornerstone.getDefaultViewportForImage(element, image);
        cornerstone.displayImage(element, image, viewport);
        if(loaded === false) {
            cornerstoneTools.mouseInput.enable(element);
            cornerstoneTools.mouseWheelInput.enable(element);
            // set the stack as tool state
            cornerstoneTools.addStackStateManager(element, ['stack']);
            cornerstoneTools.addToolState(element, 'stack', stack);

            // Enable all tools we want to use with this element
            //cornerstoneTools.stackScroll.activate(element, 2);
            cornerstoneTools.stackScrollWheel.activate(element);
            cornerstoneTools.pan.activate(element, 4); // 2是滚轮
            cornerstoneTools.zoom.activate(element, 2); // 通过右键放大缩小
            // Uncomment below to enable stack prefetching
            // With the example images the loading will be extremely quick, though
            //暂时不知道该功能用法
            cornerstoneTools.stackPrefetch.enable(element, 3);
            loaded = true;
        }
        document.getElementById("topleft").innerText = "Study Date:" + image.data.string('x00080020') + '\n' +
            "StudyID:" + image.data.string('x00200010') + '\n' +
            "Series Number:" + image.data.string('x00200011');
        document.getElementById("topright").innerText = "Name:" + image.data.string('x00100010') + '\n' +
            "PID:" + image.data.string('x00100020') + '\n' +
            "Sex:" + image.data.string('x00100040');
        function onImageRendered(e) {
            var viewport1 = cornerstone.getViewport(e.target);

            document.getElementById('bottomleft').innerText = "WW/WC:" + Math.round(viewport1.voi.windowWidth) + '/' + Math.round(viewport1.voi.windowCenter);
            document.getElementById('bottomright').innerText = "Zoom:" + viewport1.scale.toFixed(2);
        }
        element.addEventListener('cornerstoneimagerendered', onImageRendered);

    });
    function onNewImage(e) {
        $(function() {
            $( "#slider-vertical" ).slider({
                orientation: "vertical",
                range: "max",
                min: 0,
                max: imageIdlist.length-1,
                value: imageIdlist.length-1,
                slide: function( event, ui ) {
                    //$( "#amount" ).val( ui.value );
                    stack.currentImageIdIndex = imageIdlist.length-1-ui.value;
                        cornerstone.loadAndCacheImage(imageIdlist[stack.currentImageIdIndex]).then(function(image) {
                            var viewport = cornerstone.getViewport(element);
                            cornerstone.displayImage(element, image, viewport);
                        });
                }
            });
            //$( "#amount" ).val( $( "#slider-vertical" ).slider( "value" ) );
        });
        var newImageIdIndex = stack.currentImageIdIndex;
        //更新滑块的值
        $( "#slider-vertical" ).slider("value", imageIdlist.length-1-newImageIdIndex);

    }
    element.addEventListener('cornerstonenewimage',onNewImage);
}*/
// this function gets called once the user drops the file onto the div


cornerstoneWADOImageLoader.configure({
    beforeSend: function(xhr) {
        // Add custom headers here (e.g. auth tokens)
        //xhr.setRequestHeader('x-auth-token', 'my auth token');
    },
    useWebWorkers: true,
});

//加载几个序列的函数
function  loadSeriesImage(stacks,divArray) {
    for (let s = 0; s < stacks.length; s++) {
        const element = stacks[s].element;
        const stack = stacks[s].stack;

        cornerstone.enable(element);
        cornerstone.loadImage(stack.imageIds[stack.currentImageIdIndex]).then(function(image) {
            // display this image
            cornerstone.displayImage(element, image);

            cornerstoneTools.mouseInput.enable(element);
            cornerstoneTools.mouseWheelInput.enable(element);

            // set the stack as tool state
            cornerstoneTools.addStackStateManager(element, ['stack']);
            cornerstoneTools.addToolState(element, 'stack', stack);

            // Enable all tools we want to use with this element
            //cornerstoneTools.stackScroll.activate(element, 1);
            cornerstoneTools.stackScrollWheel.activate(element);
            divArray[s].children[1].innerText = "Study Date:" + image.data.string('x00080020') + '\n' +
                "StudyID:" + image.data.string('x00200010') + '\n' +
                "Series Number:" + image.data.string('x00200011');
            divArray[s].children[2].innerText = "Name:" + image.data.string('x00100010') + '\n' +
                "PID:" + image.data.string('x00100020') + '\n' +
                "Sex:" + image.data.string('x00100040');
            function onImageRendered(e) {
                var viewport = cornerstone.getViewport(e.target);

                divArray[s].children[4].innerText = "WW/WC:" + Math.round(viewport.voi.windowWidth) + '/' + Math.round(viewport.voi.windowCenter);
                divArray[s].children[3].innerText = "Zoom:" + viewport.scale.toFixed(2);
            }
            element.addEventListener('cornerstoneimagerendered', onImageRendered);


        });
        function onNewImage(e) {
            $(function() {
                $(divArray[s].children[5]).slider({
                    orientation: "vertical",
                    range: "max",
                    min: 0,
                    max: stack.imageIds.length-1,
                    value: stack.imageIds.length-1,
                    slide: function( event, ui ) {
                        //$( "#amount" ).val( ui.value );
                        stack.currentImageIdIndex = stack.imageIds.length-1-ui.value;
                        cornerstone.loadAndCacheImage(stack.imageIds[stack.currentImageIdIndex]).then(function(image) {
                            var viewport = cornerstone.getViewport(element);
                            cornerstone.displayImage(element, image, viewport);
                        });
                    }
                });
                //$( "#amount" ).val( $( "#slider-vertical" ).slider( "value" ) );
            });
            var newImageIdIndex = stack.currentImageIdIndex;
            //更新滑块的值
            $(divArray[s].children[5]).slider("value", stack.imageIds.length-1-newImageIdIndex);

        }
        element.addEventListener('cornerstonenewimage',onNewImage);

    }
}

//放大倍数设置
var config = {
    // invert: true,
    minScale: 0.25,
    maxScale: 10.0,
    preventZoomOutsideImage: true
};

cornerstoneTools.zoom.setConfiguration(config);


function activate(id) {
    document.querySelectorAll('button').forEach(function(elem) {
        elem.classList.remove('active');
    });

    document.getElementById(id).classList.add('active');
}
//使其他按钮失效的函数
function disableAllTools() {
    for (let s = 0; s < webStacks.length; s++) {
        const element = webStacks[s].element;

        cornerstoneTools.rectangleRoi.deactivate(element,1);
        cornerstoneTools.probe.deactivate(element, 1);
        cornerstoneTools.pan.activate(element, 4); // 2是滚轮
        cornerstoneTools.zoom.activate(element, 2); // 通过右键放大缩小
        cornerstoneTools.eraser.deactivate(element,1);
        cornerstoneTools.length.deactivate(element,1);
        cornerstoneTools.angle.deactivate(element,1);
    }
}


function loadAndViewImage(imageId) {
    const element = document.getElementById('dicomImage');
    //const start = new Date().getTime();
    cornerstone.loadImage(imageId).then(function(image) {
        //console.log(image);
        const viewport = cornerstone.getDefaultViewportForImage(element, image);
       /* document.getElementById('toggleModalityLUT').checked = (viewport.modalityLUT !== undefined);
        document.getElementById('toggleVOILUT').checked = (viewport.voiLUT !== undefined);*/
        cornerstone.displayImage(element, image, viewport);
        if(loaded === false) {
            cornerstoneTools.mouseInput.enable(element);
            cornerstoneTools.mouseWheelInput.enable(element);
            //1是左键，2是滚轮，3是左键和滚轮，4是右键，5是左键和右键
            //cornerstoneTools.wwwc.activate(element, 1); // ww/wc is the default tool for left mouse button
            cornerstoneTools.pan.activate(element, 4); // 2是滚轮
            cornerstoneTools.zoom.activate(element, 2); // 通过右键放大缩小
           // cornerstoneTools.zoomWheel.activate(element); // 通过滚轮放大缩小
            //这个是图像渲染的信息
            //cornerstoneTools.imageStats.enable(element);
            loaded = true;
        }



        document.getElementById("topleft").innerText = "Study Date:" + image.data.string('x00080020') + '\n' +
            "StudyID:" + image.data.string('x00200010') + '\n' +
            "Series Number:" + image.data.string('x00200011');
        document.getElementById("topright").innerText = "Name:" + image.data.string('x00100010') + '\n' +
            "PID:" + image.data.string('x00100020') + '\n' +
            "Sex:" + image.data.string('x00100040');
        function onImageRendered(e) {
            var viewport1 = cornerstone.getViewport(e.target);
            document.getElementById('bottomleft').innerText = "WW/WC:" + Math.round(viewport1.voi.windowWidth) + '/' + Math.round(viewport1.voi.windowCenter);
            document.getElementById('bottomright').innerText = "Zoom:" + viewport1.scale.toFixed(2);
        }
        element.addEventListener('cornerstoneimagerendered', onImageRendered);

    }, function(err) {
        alert(err);
    });
}

//开始添加各个按钮功能
//获取打开文件按钮
var openFile = document.getElementById("openFile");
//保存文件的按钮
var saveAnnotaitons = document.getElementById("saveAnnotations");
//获取恢复原图大小按钮
var actualSize = document.getElementById("actualSize");
//获取放大按钮
var zoomInImage = document.getElementById("zoomInImage");
//获取缩小按钮
var zoomOutImage = document.getElementById("zoomOutImage");
//获取左旋转按钮
var rotateCounterClockwise = document.getElementById("rotateCounterClockwise");
//获取右旋转按钮
var rotateClockwise = document.getElementById("rotateClockwise");
//获取矩形框按钮
var rectbtn = document.getElementById("rectbtn");
//获取删除标记框的按钮
var deleteAnnotation = document.getElementById("deleteAnnotation");
//获取长度测量的按钮
var length = document.getElementById("length");
//获取角度测量的按钮
var angle = document.getElementById("angle");
//获取序列布局的按钮
var layout = document.getElementById("layout");
//测试
var loadTools = document.getElementById("loadTools");
/*
//对调窗宽窗位的按钮的操作
document.getElementById("windowChange").onclick = function () {
    disableAllTools();
    let viewport = cornerstone.getViewport(element);
    viewport.voi.windowWidth = document.getElementById("windowWidth").value;
    viewport.voi.windowCenter = document.getElementById("windowCenter").value;
    cornerstone.setViewport(element, viewport);
};
*/

//对保存标注按钮的操作
saveAnnotaitons.onclick = function(e){
    disableAllTools();
    var toolStateManager = cornerstoneTools.getElementToolStateManager(element);
    var toolData = toolStateManager.get(element,"rectangleRoi");
    var imageSize = cornerstone.getImage(element);
    if(!toolData){
        alert("没有标注信息");
        return;
    }else{
        //console.log(toolData.data[1].handles.start.x);
        var xy = new Array();
        var picWidth = imageSize.data.uint16('x00280011');
        var picHeight = imageSize.data.uint16('x00280010');
        for(var i = 0;i<toolData.data.length;i++){
            var x1 =  Math.min(toolData.data[i].handles.start.x,toolData.data[i].handles.end.x);
            var y1 =  Math.min(toolData.data[i].handles.start.y,toolData.data[i].handles.end.y);
            var x2 =  Math.max(toolData.data[i].handles.start.x,toolData.data[i].handles.end.x);
            var y2 =  Math.max(toolData.data[i].handles.start.y,toolData.data[i].handles.end.y);
            //xy[i-1]=new Array(x1,y1,x2,y2);
            xy.push(x1,y1,x2,y2);

        }
        $.ajax({
            async:false,
            cache:false,
            traditional:true,//
            type: 'post',
            dataType: 'json',
            url: '/get/receiveList',
            /*data: {xy:JSON.stringify(xy)},*/
            data : {
                fileSrc:fileSrc,
                xy:xy,
                width:picWidth,
                height:picHeight
            },
            success: function (data) {
                if(data == "1") {
                    alert("保存成功");
                }
                if (data == "0"){
                    alert("保存失败")
                }
            },
            error: function(){
                alert("保存失败");
            }
        });

    }

}


//对实际尺寸按钮的操作
actualSize.onclick = function(){
    disableAllTools();
    for (let s = 0; s < webStacks.length; s++) {
        const element = webStacks[s].element;

        cornerstone.reset(element);
    }

}

//放大按钮的操作
zoomInImage.onclick = function(){
    disableAllTools();
    for (let s = 0; s < webStacks.length; s++) {
        const element = webStacks[s].element;
        let viewport = cornerstone.getViewport(element);
        viewport.scale += 0.3;
        cornerstone.setViewport(element, viewport);
    }
}

//缩小按钮的操作
zoomOutImage.onclick = function(){
    disableAllTools();
    for (let s = 0; s < webStacks.length; s++) {
        const element = webStacks[s].element;
        let viewport = cornerstone.getViewport(element);
        viewport.scale -= 0.3;
        cornerstone.setViewport(element, viewport);
    }

}

//左旋转按钮的操作
rotateCounterClockwise.onclick = function(){
    disableAllTools();
    for (let s = 0; s < webStacks.length; s++) {
        const element = webStacks[s].element;
        const viewport = cornerstone.getViewport(element);
        viewport.rotation -= 90;
        cornerstone.setViewport(element, viewport);
    }

}

//右旋转按钮的操作
rotateClockwise.onclick = function(){
    disableAllTools();
    for (let s = 0; s < webStacks.length; s++) {
        const element = webStacks[s].element;
        const viewport = cornerstone.getViewport(element);
        viewport.rotation += 90;
        cornerstone.setViewport(element, viewport);
    }

}

//对删除按钮的操作
deleteAnnotation.onclick = function(){
    activate("deleteAnnotation");
    disableAllTools();
    for (let s = 0; s < webStacks.length; s++) {
        const element = webStacks[s].element;
        cornerstoneTools.rectangleRoi.enable(element,1);
        cornerstoneTools.probe.enable(element, 1);
        cornerstoneTools.length.enable(element,1);
        cornerstoneTools.angle.enable(element,1);
        cornerstoneTools.eraser.enable(element, 1);
        cornerstoneTools.eraser.activate(element, 1);
    }

}

//对画标注框按钮的操作
rectbtn.onclick = function(e){
    activate("rectbtn");
    disableAllTools();
    for (let s = 0; s < webStacks.length; s++) {
        const element = webStacks[s].element;
        cornerstoneTools.rectangleRoi.activate(element, 1);
    }


}

//对长度测量按钮的操作
length.onclick = function () {
    activate("length");
    disableAllTools();
    for (let s = 0; s < webStacks.length; s++) {
        const element = webStacks[s].element;
        cornerstoneTools.length.activate(element,1);
    }

}

//对角度测量按钮的操作
angle.onclick = function () {
    activate("angle");
    disableAllTools();
    for (let s = 0; s < webStacks.length; s++) {
        const element = webStacks[s].element;
        cornerstoneTools.angle.activate(element,1);
    }

}

//对序列布局按钮的操作
layout.onclick = function () {
        $('#myModal').modal();
}

loadTools.onclick = function () {
    //activate("angle");
    disableAllTools();
    /*for (let s = 0; s < webStacks.length; s++) {
        const element = webStacks[s].element;
        cornerstoneTools.angle.activate(element,1);
    }*/
}
//获取所有图像的图像元素保存


//对弹框确定按钮的添加函数
var btn_submit = document.getElementById('btn_submit');
btn_submit.onclick = function(){
    var h =  $("#txt_rows").val();
    var l =	 $("#txt_columns").val();
    layoutSeries(h,l);

    webStacks = getStacks(imageIdlist);

    loadSeriesImage(webStacks,document.getElementById('imageViewerDiv').children);
   imageAreaSize();
    window.onload = function(){

    }
    loadTools.click();
}
//对序列分框的函数
function layoutSeries(rows,columns) {
    var viewerContent = document.getElementById("viewerContent");
    var parEle = document.getElementById('imageViewerDiv');
    //viewerContent.style.width = document.documentElement.clientWidth*0.80+'px';
    //viewerContent.style.height = document.documentElement.clientHeight*0.83+'px';
   // parEle.style.width = document.documentElement.clientWidth*0.80+'px';
    //parEle.style.height = document.documentElement.clientHeight*0.83+'px';
    parEle.innerHTML = "";
    for (var n=0;n<rows;n++){
        for (var m=0;m<columns;m++){
            var oDiv = document.createElement('div');
         /*   oDiv.style.height=(parEle.clientHeight/rows - 15)+"px";
            oDiv.style.width=(parEle.clientWidth/columns - 15)+"px";*/
            oDiv.style.height=100/rows+"%";
            oDiv.style.width=100/columns+"%";
            oDiv.className="viewportContainer";
            oDiv.style.float="left";
            oDiv.style.borderStyle="solid";
            oDiv.style.borderWidth="1px";
            oDiv.style.boxSizing = "border-box";
            oDiv.style.position="relative";
            oDiv.style.color="white";
            oDiv.innerHTML=/*'<div  style="position:relative;color: white;"' +
                ' oncontextmenu="return false"\n' +
                ' class=\'disable-selection noIbar\'\n' +
                ' unselectable=\'on\'\n' +
                ' onselectstart=\'return false;\'\n' +
                ' onmousedown=\'return false;\'>\n' +*/
                '<div  class="dicomImage" style="">\n' +
                '</div>' +
                '<div class="overlay topleft" style="position:absolute;top:0px;left:0px">' +
                '</div>' +
                '<div  class="overlay topright" style="position:absolute;top:0px;right:0px">' +
                '</div>' +
                '<div  class="overlay bottomright" style="position:absolute;bottom:0px;right:0px">' +
                '</div>' + '<div  class="overlay bottomleft" style="position:absolute;bottom:0px;left:0px">' +
                '</div>' + '<div  class="overlay " style="position:absolute;top:25%;height:50%;right:0px"></div>' /*+
                '</div>'*/;

            parEle.appendChild(oDiv);
            //oDiv.getElementsByTagName('div')[0].style.height='100%';
        }
    }

}
//获取stacks集合的函数

layoutSeries(1,1);
var webStacks = getStacks(imageIdlist);

loadSeriesImage(webStacks,document.getElementById('imageViewerDiv').children);
/**
 *
 * @param imageList 从后台传过来的影像路径的集合
 */

function getStacks(imageList) {
    //序列的长度
    var seriesL = imageList.length;
    //分屏div的个数
    var parEleL= document.getElementById('imageViewerDiv').children.length;
    //alert(parEleL);
    var stacks = new Array();
    for (var i=0; i<Math.min(seriesL,parEleL);i++){
        //变形过之后的图片路径数组
        var imageIdlistChange= imageList[i].map(function (item) {
            return 'wadouri:'+item;
        });
        var childDiv = document.getElementById('imageViewerDiv').children[i];
        stacks.push({element:childDiv.children[0],stack:{currentImageIdIndex:0,imageIds:imageIdlistChange}});
    }
    return stacks;


}


/*cornerstone.events.addEventListener('cornerstoneimageloadprogress', function(event) {
    const eventData = event.detail;
    const loadProgress = document.getElementById('loadProgress');
    loadProgress.textContent = `Image Load Progress: ${eventData.percentComplete}%`;
});*/

//适应窗口大小
/*function imageSize() {
    var imgArea = document.getElementById("imgArea");
    imgArea.style.height = (document.documentElement.clientHeight)*0.65+"px";
    imgArea.style.width = (document.documentElement.clientWidth)*0.75+"px";
    element.style.height = (document.documentElement.clientHe  ight)*0.65+"px";
    element.style.width = (document.documentElement.clientWidth)*0.75+"px";
    element.getElementsByTagName("canvas")[0].height = (document.documentElement.clientHeight)*0.65;
    element.getElementsByTagName("canvas")[0].width = (document.documentElement.clientWidth)*0.75;
    element.getElementsByTagName("canvas")[0].style.height = (document.documentElement.clientHeight)*0.65+"px";
    element.getElementsByTagName("canvas")[0].style.width = (document.documentElement.clientWidth)*0.75+"px";
    //$( "#slider-vertical" ).height = (document.documentElement.clientHeight)*0.50+"px";
}*/
//总体自适应大小
function imageAreaSize() {

    var imageViewerDiv = document.getElementById('imageViewerDiv');
    var Cweight = document.documentElement.clientWidth*1;
    var Cheight = document.documentElement.clientHeight*0.88;
    //imageViewerDiv.style.width = Cweight+'px';
    //imageViewerDiv.style.height = Cheight+'px';
    var h =  $("#txt_rows").val();
    var l =	 $("#txt_columns").val();

    //获取显示影像div的个数
    var imageDivNum = imageViewerDiv.children.length;
    for (var i=0;i<imageDivNum;i++){
        //alert( imageViewerDiv.getElementsByTagName('div')[i].getElementsByTagName('div')[0].clientWidth);
        imageViewerDiv.children[i].style.height = Cheight/h+'px';
        imageViewerDiv.children[i].style.weight = Cweight/l+'px';
        imageViewerDiv.children[i].children[0].style.height = Cheight/h+'px';
        imageViewerDiv.children[i].children[0].style.weight = Cweight/l+'px';
        if( imageViewerDiv.children[i].children[0].children[0]){

            imageViewerDiv.children[i].children[0].children[0].height = (Cheight/h);
            imageViewerDiv.children[i].children[0].children[0].weight = (Cweight/l-10);
            imageViewerDiv.children[i].children[0].children[0].style.height = (Cheight/h)+'px';
            imageViewerDiv.children[i].children[0].children[0].style.weight = (Cweight/l-10)+'px';
        }
    }
}
imageAreaSize();
$(window).resize(function(){
    //imageSize();
    imageAreaSize();
    //cornerstone.resize(element);
    for (let s = 0; s < webStacks.length; s++) {
        const element = webStacks[s].element;
        cornerstone.resize(element);
    }
});
