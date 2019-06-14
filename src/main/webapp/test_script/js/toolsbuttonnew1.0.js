
cornerstoneWADOImageLoader.external.cornerstone = cornerstone;

var getParam = function () {
    try{
        var url = window.location.href;
        var result = url.split("?")[1];
        var keyValue = result.split("&");
        var obj = {};
        for (var i = 0; i < keyValue.length; i++) {
            var item = keyValue[i].split("=");
            obj[item[0]] = item[1];
        }
        return obj;
    }catch(e){
        console.warn("There has no param value!");
    }
};
//图像的URL
var imageUrl = getParam()['url'].replace(/@/g,"\\");

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
        url:imageUrl
    },
    success: function (data) {
        imageIdlist = data;
        /*imageIdlist = data[0].map(function (item) {
            return 'wadouri:'+item;
        })*/
    },
    error: function(){
            alert("加载图像失败");
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
//类似于加载tool的函数
function loadMeasureTools(element) {
   /* cornerstoneTools.rectangleRoi.deactivate(element,1);
    cornerstoneTools.probe.deactivate(element, 1);
    // 2是滚轮
    cornerstoneTools.zoom.activate(element, 2); // 通过右键放大缩小
   cornerstoneTools.eraser.deactivate(element,1);
    cornerstoneTools.length.deactivate(element,1);
    cornerstoneTools.angle.deactivate(element,1);*/
    $("#playSpan").switchClass("stopIcon","playIcon");
    document.getElementById("playSpeed").style.display = "none";
    cornerstoneTools.stopClip(element);
    cornerstoneTools.pan.activate(element, 4);
}
/*//方向的配置
var config = {
    drawAllMarkers: true
}

// Comment this out to draw only the top and left markers
cornerstoneTools.orientationMarkers.setConfiguration(config);*/
//加载几个序列的函数
function  loadSeriesImage(stacks,divArray) {


        const element = stacks.element;
        const stack = stacks.stack;

        cornerstone.enable(element);
        cornerstone.loadAndCacheImage(stack.imageIds[stack.currentImageIdIndex]).then(function(image) {
            loadMeasureTools(element);
            // display this image
            cornerstone.displayImage(element, image);

            cornerstoneTools.mouseInput.enable(element);
            cornerstoneTools.mouseWheelInput.enable(element);

            // set the stack as tool state

            cornerstoneTools.addStackStateManager(element, ['stack', 'playClip']);
            cornerstoneTools.addToolState(element, 'stack', stack);
            //前后左右的标示
            //cornerstoneTools.orientationMarkers.enable(element);
            //这个是加测量线
            //cornerstoneTools.scaleOverlayTool.enable(element);
            //cornerstoneTools.pan.activate(element, 4); // 2是滚轮
            //cornerstoneTools.zoom.activate(element, 2); // 通过右键放大缩小

            cornerstone.reset(element);
            cornerstone.resize(element);
            // Enable all tools we want to use with this element
            //cornerstoneTools.stackScroll.activate(element, 1);
            cornerstoneTools.stackScrollWheel.activate(element);
            divArray.children[1].innerText = "Study Date:" + image.data.string('x00080020') + '\n' +
                "StudyID:" + image.data.string('x00200010') + '\n' +
                "Series Number:" + image.data.string('x00200011');
            divArray.children[2].innerText = "Name:" + image.data.string('x00100010') + '\n' +
                "PID:" + image.data.string('x00100020') + '\n' +
                "Sex:" + image.data.string('x00100040');
            function onImageRendered(e) {
                var viewport = cornerstone.getViewport(e.target);

                divArray.children[4].innerText = "Img:"+(stack.currentImageIdIndex+1)+'/'+stack.imageIds.length+'\n'+"WW/WC:" + Math.round(viewport.voi.windowWidth) + '/' + Math.round(viewport.voi.windowCenter);
                divArray.children[3].innerText = "Zoom:" + viewport.scale.toFixed(2);
            }
            element.addEventListener('cornerstoneimagerendered', onImageRendered);


        });
        function onNewImage(e) {
            $(function() {
                $(divArray.children[5]).slider({
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
            $(divArray.children[5]).slider("value", stack.imageIds.length-1-newImageIdIndex);

        }
        element.addEventListener('cornerstonenewimage',onNewImage);


}

//是同步滚动
var synchronizer = new cornerstoneTools.Synchronizer("cornerstonenewimage",  cornerstoneTools.stackScrollSynchronizer);
//加载一个序列的图像布局的函数

/**
 *
 * @param stacks
 * @param divArray
 * @param n  是第几个框呈现
 * @param m  一次呈现的间隔数
 */
function  loadOneSeriesImage(imgId,divArray,currentIndex,allNums) {

    const element =   divArray.children[0];
    cornerstone.enable(element);
    cornerstone.loadAndCacheImage(imgId).then(function(image) {
        loadMeasureTools(element);
        // display this image
        cornerstone.displayImage(element, image);

       cornerstoneTools.mouseInput.enable(element);
       cornerstoneTools.mouseWheelInput.enable(element);

        //添加同步滚动
        synchronizer.add(element);
        // set the stack as tool state


        //前后左右的标示
        //cornerstoneTools.orientationMarkers.enable(element);
        //这个是加测量线
        //cornerstoneTools.scaleOverlayTool.enable(element);
        //cornerstoneTools.pan.activate(element, 4); // 2是滚轮
        //cornerstoneTools.zoom.activate(element, 2); // 通过右键放大缩小

        //cornerstone.reset(element);
        cornerstone.resize(element);
        // Enable all tools we want to use with this element
        //cornerstoneTools.stackScroll.activate(element, 1);
        divArray.children[1].innerText = "Study Date:" + image.data.string('x00080020') + '\n' +
            "StudyID:" + image.data.string('x00200010') + '\n' +
            "Series Number:" + image.data.string('x00200011');
        divArray.children[2].innerText = "Name:" + image.data.string('x00100010') + '\n' +
            "PID:" + image.data.string('x00100020') + '\n' +
            "Sex:" + image.data.string('x00100040');
        function onImageRendered(e) {
            var viewport = cornerstone.getViewport(e.target);

            divArray.children[4].innerText = "Img:"+(currentIndex+1)+'/'+allNums+'\n'+"WW/WC:" + Math.round(viewport.voi.windowWidth) + '/' + Math.round(viewport.voi.windowCenter);
            divArray.children[3].innerText = "Zoom:" + viewport.scale.toFixed(2);
        }
        element.addEventListener('cornerstoneimagerendered', onImageRendered);


    });



}
/*function  loadSeriesImage(stacks,divArray) {
    loaded = false;
    for (let s = 0; s < stacks.length; s++) {
        const element = stacks[s].element;
        const stack = stacks[s].stack;

        cornerstone.enable(element);
        cornerstone.loadAndCacheImage(stack.imageIds[stack.currentImageIdIndex]).then(function(image) {
            // display this image
            cornerstone.displayImage(element, image);

            cornerstoneTools.mouseInput.enable(element);
            cornerstoneTools.mouseWheelInput.enable(element);

            // set the stack as tool state

            cornerstoneTools.addStackStateManager(element, ['stack']);
            cornerstoneTools.addToolState(element, 'stack', stack);
            //前后左右的标示
            //cornerstoneTools.orientationMarkers.enable(element);
            //这个是加测量线
            //cornerstoneTools.scaleOverlayTool.enable(element);
            //cornerstoneTools.pan.activate(element, 4); // 2是滚轮
            //cornerstoneTools.zoom.activate(element, 2); // 通过右键放大缩小
            loadMeasureTools(element)
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
    loaded = true;
}*/

//显示序列缩略图




//放大倍数设置
var config = {
    // invert: true,
    minScale: 0.3,
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
        cornerstoneTools.wwwc.disable(element);
        cornerstoneTools.rectangleRoi.deactivate(element,1);
        cornerstoneTools.probe.deactivate(element, 1);
        cornerstoneTools.pan.activate(element, 4); // 通过右键放大缩小,2是滚轮
        cornerstoneTools.zoom.deactivate(element, 1); //
        cornerstoneTools.eraser.deactivate(element,1);
        cornerstoneTools.length.deactivate(element,1);
        cornerstoneTools.simpleAngle.deactivate(element,1);
        cornerstoneTools.arrowAnnotate.deactivate(element,1);
        cornerstoneTools.ctr.deactivate(element, 1);
    }
}
//以下是箭头标注弹窗所用

// Register the dialogs using the HTML5 Dialog Polyfill
var annotationDialog = document.querySelector('.annotationDialog');
var relabelDialog = document.querySelector('.relabelDialog');
dialogPolyfill.registerDialog(annotationDialog);
dialogPolyfill.registerDialog(relabelDialog);

// Define a callback to get your text annotation
// This could be used, e.g. to open a modal
function getTextCallback(doneChangingTextCallback) {
    var annotationDialog  = document.querySelector('.annotationDialog');
    var getTextInput = annotationDialog.querySelector('.annotationTextInput');
    var confirm = annotationDialog.querySelector('.annotationDialogConfirm');

    annotationDialog.showModal();
    // Add dialog listeners
    confirm.addEventListener('click', closeHandler);
    annotationDialog.addEventListener('keydown', keyPressHandler);

    function closeDialog() {
        // Remove dialog listeners
        annotationDialog.removeEventListener("keydown", keyPressHandler);
        confirm.removeEventListener('click', closeHandler);

        annotationDialog.close();
    }

    function keyPressHandler(e) {
        // If Enter is pressed, close the dialog
        if (e.which === 13) {
            closeHandler();
        }
    }

    function closeHandler() {
        closeDialog();
        doneChangingTextCallback(getTextInput.value);
        // Reset the text value
        getTextInput.value = "";
    }
}

// Define a callback to edit your text annotation
// This could be used, e.g. to open a modal
function changeTextCallback(data, eventData, doneChangingTextCallback) {
    var relabelDialog = document.querySelector('.relabelDialog');
    var getTextInput = relabelDialog.querySelector('.annotationTextInput');
    var confirm = relabelDialog.querySelector('.relabelConfirm');
    var remove = relabelDialog.querySelector('.relabelRemove');

    getTextInput.value = data.text;
    relabelDialog.showModal();
    // Add dialog listeners
    confirm.addEventListener('click', confirmHandler);
    remove.addEventListener('click', removeHandler);
    relabelDialog.addEventListener('keydown', keyPressHandler);


    function closeDialog () {
        // Remove dialog listeners
        confirm.removeEventListener('click', confirmHandler);
        remove.removeEventListener('click', removeHandler);
        relabelDialog.removeEventListener("keydown", keyPressHandler);

        relabelDialog.close();
    }

    function confirmHandler() {
        closeDialog();
        doneChangingTextCallback(data, getTextInput.value);
    }

    // If the remove button is clicked, delete this marker
    function removeHandler() {
        closeDialog();
        doneChangingTextCallback(data, undefined, true);
    }

    function keyPressHandler(e) {
        // If Enter is pressed, close the dialog
        if (e.which === 13) {
            closeDialog();
            doneChangingTextCallback(data, getTextInput.value);
            // Reset the text value
            getTextInput.value = "";
        }
    }
}

var config = {
    getTextCallback : getTextCallback,
    changeTextCallback : changeTextCallback,
    drawHandles : false,
    drawHandlesOnHover : true,
    arrowFirst : true
}

//以上是箭头弹窗所用

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


//全局变量
//图像布局的标识
var oneSeriesFlag = false;
//序列布局的标识
var seriesFlag = true;
//当前图像布局显示框得个数
var displayDiv = 0;

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
//var zoomOutImage = document.getElementById("zoomOutImage");
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
//加载标注信息的按钮
var loadAnno = document.getElementById("loadAnnotations");
//调节窗宽窗位的按钮
var levels = document.getElementById("levels");
//播放一个序列的按钮
var play = document.getElementById("play");
//停止的播放序列的按钮
// var stop = document.getElementById("stop");
//播放速率的按钮
var playSpeed = document.getElementById("playSpeed");
//负像的按钮
var invert = document.getElementById("invert");
//左右镜像的按钮
var hflip = document.getElementById("hflip");
//上下镜像的按钮
var vflip = document.getElementById("vflip");
//箭头标注的按钮
var arrowAnno = document.getElementById("arrowAnno");
//心胸比的按钮
var ctr = document.getElementById("ctr");
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
    const element = webStacks[currentDiv].element;
    var fileSrc = webStacks[currentDiv].stack.imageIds[webStacks[currentDiv].stack.currentImageIdIndex];
    var toolStateManager = cornerstoneTools.getElementToolStateManager(element);
    var toolData = toolStateManager.get(element,"rectangleRoi");
    var imageSize = cornerstone.getImage(element);
    var appState = cornerstoneTools.appState.save([element]);
    var serializedState = JSON.stringify(appState);
    if(!toolData){
        alert("没有标注信息");
        return;
    }else{
        //console.log(toolData.data[1].handles.start.x);
        var xy = new Array();
        //toolData.data[i].handles.start.x 这个获取的是距离
        //toolData.data[i].handles.end.x 这个获取的是像素
        for(var i = 0;i<toolData.data.length;i++){
            var x1 =  Math.round( Math.min(toolData.data[i].handles.start.x,toolData.data[i].handles.end.x));
            var y1 =  Math.round( Math.min(toolData.data[i].handles.start.y,toolData.data[i].handles.end.y));
            var x2 =  Math.round( Math.max(toolData.data[i].handles.start.x,toolData.data[i].handles.end.x));
            var y2 =  Math.round( Math.max(toolData.data[i].handles.start.y,toolData.data[i].handles.end.y));
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
                annoInfo:serializedState
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
//定义一个全局变量，用来接收当先正在操作的div
var currentDiv = 0;
//定义一个全局变量，来存放当前div显示序列的序列书；
var currentSeriesDisplaty;

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
    activate("zoomInImage")
    disableAllTools();
    for (let s = 0; s < webStacks.length; s++) {
        const element = webStacks[s].element;
        cornerstoneTools.zoom.activate(element, 1);
       /* let viewport = cornerstone.getViewport(element);
        viewport.scale += 0.3;
        cornerstone.setViewport(element, viewport);*/
    }
    /*const element = webStacks[currentDiv].element;
   et viewport = cornerstone.getViewport(element);
   viewport.scale += 0.3;
   cornerstone.setViewport(element, viewport);*/
}

//缩小按钮的操作
/*zoomOutImage.onclick = function(){
    disableAllTools();
    for (let s = 0; s < webStacks.length; s++) {
        const element = webStacks[s].element;
        let viewport = cornerstone.getViewport(element);
        viewport.scale -= 0.3;
        cornerstone.setViewport(element, viewport);
    }

}*/

//左旋转按钮的操作
rotateCounterClockwise.onclick = function(){
    disableAllTools();
   /* for (let s = 0; s < webStacks.length; s++) {
        const element = webStacks[s].element;
        const viewport = cornerstone.getViewport(element);
        viewport.rotation -= 90;
        cornerstone.setViewport(element, viewport);
    }*/
    const element = webStacks[currentDiv].element;
    const viewport = cornerstone.getViewport(element);
    viewport.rotation -= 90;
    cornerstone.setViewport(element, viewport);

}

//右旋转按钮的操作
rotateClockwise.onclick = function(){
    disableAllTools();
 /*   for (let s = 0; s < webStacks.length; s++) {
        const element = webStacks[s].element;
        const viewport = cornerstone.getViewport(element);
        viewport.rotation += 90;
        cornerstone.setViewport(element, viewport);
    }*/
    const element = webStacks[currentDiv].element;
    const viewport = cornerstone.getViewport(element);
    viewport.rotation += 90;
    cornerstone.setViewport(element, viewport);

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
        cornerstoneTools.simpleAngle.enable(element,1);
        cornerstoneTools.eraser.enable(element, 1);
        cornerstoneTools.arrowAnnotate.enable(element, 1);
        cornerstoneTools.eraser.activate(element, 1);
        cornerstoneTools.ctr.enable(element, 1);
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

//对窗宽窗位按钮的操作
levels.onclick = function(){
    activate("levels")
    disableAllTools();
    for (let s = 0; s < webStacks.length; s++) {
        const element = webStacks[s].element;
        cornerstoneTools.wwwc.activate(element, 1);
    }
}

//对角度测量按钮的操作
angle.onclick = function () {
    activate("angle");
    disableAllTools();
    for (let s = 0; s < webStacks.length; s++) {
        const element = webStacks[s].element;
        cornerstoneTools.simpleAngle.activate(element,1);
    }

}
//箭头标注按钮的操作
arrowAnno.onclick = function(){
    activate("arrowAnno");
    disableAllTools();
    cornerstoneTools.arrowAnnotate.setConfiguration(config);
    for (let s = 0; s < webStacks.length; s++) {
        const element = webStacks[s].element;
        cornerstoneTools.arrowAnnotate.activate(element, 1);
    }
}
//对心胸比按钮的操作
ctr.onclick = function(){
    activate("arrowAnno");
    disableAllTools();
    for (let s = 0; s < webStacks.length; s++) {
        const element = webStacks[s].element;
        cornerstoneTools.ctr.activate(element, 1);
    }
}

//对播放按钮的操作
play.onclick = function(){
    var playSpan = document.getElementById("playSpan");
    activate("play");
    disableAllTools();
    const element = webStacks[currentDiv].element;
    $("#playSpan").toggleClass("stopIcon");
    $("#playSpan").toggleClass("playIcon");
    if (playSpan.getAttribute('class')) {  // 存在class属性
        if (playSpan.className.indexOf('stopIcon') > -1) {
            playSpeed.style.display = "inline";
            $("#playDiv").text("暂停");
            cornerstoneTools.playClip(element,15);
            return false;
        }

        // 方式2
        if (playSpan.className.indexOf('playIcon') > -1) {
            playSpeed.style.display = "none";
            $("#playDiv").text("播放");
            cornerstoneTools.stopClip(element);
            return false;
        }
    }
}
//对Playspeed增加监听事件
var speedInput = document.getElementById("speedInput");
$(speedInput).bind('input propertychange',function () {
    const element = webStacks[currentDiv].element;
    var playClipToolData = cornerstoneTools.getToolState(element, 'playClip');

    if (!playClipToolData.data.length) {
        playClipToolData.data.push({
            intervalId: undefined,
            framesPerSecond: 1,
            lastFrameTimeStamp: undefined,
            frameRate: 0,
            frameTimeVector: undefined,
            ignoreFrameTimeVector: false,
            usingFrameTimeVector: false,
            speed: 1,
            reverse: false,
            loop: true
        })
    } else {
        playClipToolData.data[0].framesPerSecond = speedInput.value;
        cornerstoneTools.stopClip(element);
        cornerstoneTools.playClip(element);
    }
});

//对停止按钮的操作
/*stop.onclick =function(){
    activate("stop");
    disableAllTools();
    const element = webStacks[currentDiv].element;
    cornerstoneTools.stopClip(element);
    return false;
}*/


//对序列布局按钮的操作
layout.onclick = function () {
        //$('#myModal').modal();
}

//对负像按钮的操作
invert.onclick = function(){
    disableAllTools();
    const element = webStacks[currentDiv].element;
    const viewport = cornerstone.getViewport(element);
    viewport.invert = !viewport.invert;
    cornerstone.setViewport(element, viewport);
}
//对左右镜像按钮的操作
hflip.onclick = function(){
    disableAllTools();
    const element = webStacks[currentDiv].element;
    const viewport = cornerstone.getViewport(element);
    viewport.hflip = !viewport.hflip;
    cornerstone.setViewport(element, viewport);
}
//对上下镜像按钮的操作
vflip.onclick = function(){
    disableAllTools();
    const element = webStacks[currentDiv].element;
    const viewport = cornerstone.getViewport(element);
    viewport.vflip = !viewport.vflip;
    cornerstone.setViewport(element, viewport);
}

//对加载标注信息按钮的操作
loadAnno.onclick = function () {
    disableAllTools();
    const element = webStacks[currentDiv].element;
    var fileSrc = webStacks[currentDiv].stack.imageIds[webStacks[currentDiv].stack.currentImageIdIndex];
    $.ajax({
        async:false,
        cache:false,
        traditional:true,//
        type: 'post',
        dataType: 'json',
        url: '/get/loadAnno',
        /*data: {xy:JSON.stringify(xy)},*/
        data : {
            fileSrc:fileSrc
        },
        success: function (data) {
            if (data == "0"){
                alert("该序列没有标注信息")
            }else {
                var appState = JSON.parse(data);
                cornerstoneTools.appState.restore(appState)
            }

        },
        error: function(){
            alert("加载失败");
        }
    });
}

/*document.getElementById("test").onclick = function () {
    console.log("Saving state");
    const element = webStacks[currentDiv].element;
    var appState = cornerstoneTools.appState.save([element]);
    var serializedState = JSON.stringify(appState);
    console.log(serializedState);


}*/
//测试
/*document.getElementById("test").onclick = function () {
   /!* var thumbnail = document.getElementById("studyThumbnail");
    $("div",$(thumbnail)).draggable({
        appendTo: "body",
        helper:function () {
            var dom = document.getElementById("studyThumbnail").children[0].cloneNode(true);

            return $(dom);
        }
      /!* helper:"clone"*!/
    });*!/
}*/

//获取所有图像的图像元素保存


//对弹框确定按钮的添加函数
var btn_submit = document.getElementById('btn_submit');
btn_submit.onclick = function(){
    var h =  $("#txt_rows").val();
    var l =	 $("#txt_columns").val();
    layoutSeries(h,l);
    $("#temp_rows").val(h);

    $("#temp_columns").val(l);
    webStacks = getStacks(imageIdlist);

    for (var i=0;i<webStacks.length;i++){
        loadSeriesImage(webStacks[i],document.getElementById('imageViewerDiv').children[i]);
    }
    imageAreaSize(h,l);
    currentDiv = 0;
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
    var imgSonDiv = document.createElement('div');
    imgSonDiv.className = "col-lg-12 col-md-12 col-sm-12 col-xs-12";
    parEle.appendChild(imgSonDiv);
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
            oDiv.onselectstart= function(){return false;};
            oDiv.oncontextmenu = function(){return false;};
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
            var isDbClick = false;
            imgSonDiv.appendChild(oDiv);
            $(oDiv).on('click mousewheel DOMMouseScroll',function () {
                $(this).siblings('div').removeClass('active');
               $(this).addClass('active');
               var sd = document.getElementById("imageViewerDiv").children[0];
                var divIndex = $(sd).children("div").index(this);
                currentDiv =divIndex;
            });
            $(oDiv).dblclick(function () {
                if(!isDbClick){
                    $(this).siblings('div').hide();
                    //$(this).css('height','100%');
                    //$(this).css('width','100%');
                    var vieDiv = $(this).get(0);
                    vieDiv.style.height = '100%';
                    vieDiv.style.width = '100%';
                    $("#temp_rows").val(1);
                    $("#temp_columns").val(1);
                    imageAreaSize(1,1);
                    for (let s = 0; s < webStacks.length; s++) {
                        const element = webStacks[s].element;
                        cornerstone.resize(element);
                        cornerstone.reset(element);
                    }

                    isDbClick = true;
                }
                else {
                    $(this).siblings('div').show();
                    var h =  $("#txt_rows").val();
                    var l =	 $("#txt_columns").val();
                    $("#temp_rows").val(h);
                    $("#temp_columns").val(l);
                    // $(this).css('height',100/h+'%');
                    // $(this).css('weight',100/l+'%');
                    var vieDiv = $(this).get(0);
                    vieDiv.style.height = 100/h+'%';
                    vieDiv.style.width = 100/l+'%';


                    imageAreaSize(h,l);

                    for (let s = 0; s < webStacks.length; s++) {
                        const element = webStacks[s].element;
                        cornerstone.resize(element);
                        cornerstone.reset(element);
                    }

                    isDbClick = false;
                }
            });

            $(oDiv).droppable({
               /* activeClass: "ui-state-default",
                hoverClass: "ui-state-hover",
                accept: ":not(.ui-sortable-helper)",*/
                drop: function( event, ui ) {
                    var sd = document.getElementById("imageViewerDiv").children[0];
                    var imageIndex =  $("#studyThumbnail").children("div").index(ui.draggable);
                    var divIndex = $(sd).children("div").index(this);
                    var imageIdlistChange= imageIdlist[imageIndex].map(function (item) {
                        return 'wadouri:'+item;
                    });
                    webStacks[divIndex].stack = {currentImageIdIndex:0,imageIds:imageIdlistChange};
                    loadSeriesImage(webStacks[divIndex],document.getElementById('imageViewerDiv').children[0].children[divIndex]);
                   // alert( $("#studyThumbnail div").index(ui.draggable))
                   //  alert( $("#imageViewerDiv div").index(this))
                   //  alert(imageIndex+""+divIndex);
                }
            })
            //oDiv.getElementsByTagName('div')[0].style.height='100%';
        }
    }

}

//对一个序列图像分框的函数
function layoutOneSeriesImg(rows,columns) {
    var parEle = document.getElementById('imageViewerDiv');
    parEle.innerHTML = "";
    var imgSonDiv = document.createElement('div');
    imgSonDiv.className = "col-lg-12 col-md-12 col-sm-12 col-xs-12";
    parEle.appendChild(imgSonDiv);
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
            oDiv.onselectstart= function(){return false;};
            oDiv.oncontextmenu = function(){return false;};
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
            var isDbClick = false;
            imgSonDiv.appendChild(oDiv);
            $(oDiv).on('click',function () {
                $(this).siblings('div').removeClass('active');
                $(this).addClass('active');
            });
            $(oDiv).dblclick(function () {
                if(!isDbClick){
                    $(this).siblings('div').hide();
                    //$(this).css('height','100%');
                    //$(this).css('width','100%');
                    var vieDiv = $(this).get(0);
                    vieDiv.style.height = '100%';
                    vieDiv.style.width = '100%';
                    imageAreaSize(1,1);
                    for (let s = 0; s < document.getElementById('imageViewerDiv').children[0].children.length; s++) {
                        const element = document.getElementById('imageViewerDiv').children[0].children[s].children[0];
                        cornerstone.resize(element);
                        cornerstone.reset(element);
                    }

                    isDbClick = true;
                }
                else {
                    $(this).siblings('div').show();
                    var h =  $("#imgtemp_rows").val();
                    var l =	 $("#imgtemp_columns").val();
                    // $(this).css('height',100/h+'%');
                    // $(this).css('weight',100/l+'%');
                    var vieDiv = $(this).get(0);
                    vieDiv.style.height = 100/h+'%';
                    vieDiv.style.width = 100/l+'%';

                    imageAreaSize(h,l);

                    for (let s = 0; s < document.getElementById('imageViewerDiv').children[0].children.length; s++) {
                        const element = document.getElementById('imageViewerDiv').children[0].children[s].children[0];
                        cornerstone.resize(element);
                        cornerstone.reset(element);
                    }

                    isDbClick = false;
                }
            });

            $(oDiv).droppable({
                /* activeClass: "ui-state-default",
                 hoverClass: "ui-state-hover",
                 accept: ":not(.ui-sortable-helper)",*/
                drop: function( event, ui ) {

                    var imageIndex =  $("#studyThumbnail").children("div").index(ui.draggable);
                    var divIndex = $("#imageViewerDiv").children("div").index(this);
                    var imageIdlistChange= imageIdlist[imageIndex].map(function (item) {
                        return 'wadouri:'+item;
                    });
                    var rows =  $("#imgtemp_rows").val();
                    var cols =	 $("#imgtemp_columns").val();
                    displayDiv = rows*cols;
                    loadOneSeriesAllImg(imageIdlistChange);
                    // alert( $("#studyThumbnail div").index(ui.draggable))
                    //  alert( $("#imageViewerDiv div").index(this))
                    //  alert(imageIndex+""+divIndex);
                }
            })
            //oDiv.getElementsByTagName('div')[0].style.height='100%';
        }
    }

}


//重新加载一个序列的图像分布布局的函数
function loadOneSeriesAllImg(imgList){
    var rows =  $("#imgtemp_rows").val();
    var cols =	 $("#imgtemp_columns").val();
    layoutOneSeriesImg(rows,cols);
    //一共有几页
    var pageNum = Math.ceil(imgList.length/(rows*cols));
    //增加监听事件
    const wheelEvents = ['mousewheel', 'DOMMouseScroll'];
    var childDiv = document.getElementById('imageViewerDiv').children[0];
    /*wheelEvents.forEach((eventType) => {
        childDiv.removeEventListener(eventType,scrollListener(e,currentPage,pageNum,imgList));
    });*/
    //当前页码
    var currentPage=0;
    loadImg(currentPage,imgList.length);
    function loadImg(currentP,imgNums) {
        for (var i = 0; i < document.getElementById('imageViewerDiv').children[0].children.length; i++) {
            var childDiv = document.getElementById('imageViewerDiv').children[0];
            var index = currentP*rows*cols+i;
            if(index<imgNums){
                loadOneSeriesImage(imgList[index], childDiv.children[i],index,imgList.length);
            }else {
                    displayDiv = index -currentP*rows*cols;
                    childDiv.children[i].innerHTML="";

                    var oDiv = document.createElement('div');
                    oDiv.innerHTML =  '<div  class="dicomImage" style="">\n' +
                        '</div>' +
                        '<div class="overlay topleft" style="position:absolute;top:0px;left:0px">' +
                        '</div>' +
                        '<div  class="overlay topright" style="position:absolute;top:0px;right:0px">' +
                        '</div>' +
                        '<div  class="overlay bottomright" style="position:absolute;bottom:0px;right:0px">' +
                        '</div>' + '<div  class="overlay bottomleft" style="position:absolute;bottom:0px;left:0px">' +
                        '</div>' + '<div  class="overlay " style="position:absolute;top:25%;height:50%;right:0px"></div>';
                    childDiv.children[i].appendChild(oDiv);

                    imageAreaSize(rows,cols);
            }

        }
    }


    wheelEvents.forEach((eventType) => {
        childDiv.addEventListener(eventType, function (e) {
            // Firefox e.detail > 0 scroll back, < 0 scroll forward
            // chrome/safari e.wheelDelta < 0 scroll back, > 0 scroll forward
            if (e.wheelDelta < 0 || e.detail > 0) {
                if(currentPage<pageNum-1&&currentPage>=0){
                    currentPage++;
                    loadImg(currentPage,imgList.length);
                }


            } else if(e.wheelDelta > 0 || e.detail < 0){
                //向上
                if(currentPage<pageNum&&currentPage>0) {
                    currentPage--;
                    loadImg(currentPage, imgList.length);
                }
            }
            // Prevent page fom scrolling
            return false;
        }/*scrollListener(event,currentPage,pageNum,imgList)*/);
    });

    imageAreaSize(rows,cols);


}



//获取stacks集合的函数

layoutSeries(1,1);
var webStacks = getStacks(imageIdlist);
for (var i=0;i<webStacks.length;i++){
    loadSeriesImage(webStacks[i],document.getElementById('imageViewerDiv').children[0].children[i]);
}
/**
 *
 * @param imageList 从后台传过来的影像路径的集合
 */

function getStacks(imageList) {
    //序列的长度
    var seriesL = imageList.length;
    //分屏div的个数
    var parEleL= document.getElementById('imageViewerDiv').children[0].children.length;
    //alert(parEleL);
    var stacks = new Array();
    for (var i=0; i<Math.min(seriesL,parEleL);i++){
        //变形过之后的图片路径数组
        var imageIdlistChange= imageList[i].map(function (item) {
            return 'wadouri:'+item;
        });
        var childDiv = document.getElementById('imageViewerDiv').children[0].children[i];
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
imageSeriesThumbnail(imageIdlist);
//缩略图的加载
function imageSeriesThumbnail(imageList) {

    layoutSeriesThumbnail(imageList);
    var stacks = getSeriesStacks(imageList);
    loadSeriesThumbnail(stacks);

}
//获取缩略图的

function getSeriesStacks(imageList) {
    //序列的长度
    var seriesL = imageList.length;
    //alert(parEleL);
    var stacks = new Array();
    for (var i=0; i<seriesL;i++){
        //变形过之后的图片路径数组
        var imageIdlistChange= imageList[i].map(function (item) {
            return 'wadouri:'+item;
        });
        var childDiv = document.getElementById('studyThumbnail');
        stacks.push({element:childDiv.children[i],stack:{currentImageIdIndex:0,imageIds:imageIdlistChange[0]}});
    }
    return stacks;

}

//显示缩略图

function  loadSeriesThumbnail(stacks) {
    loaded = false;
        for (let s = 0; s < stacks.length; s++) {
            const element = stacks[s].element;
            const stack = stacks[s].stack;

            cornerstone.enable(element);
            cornerstone.loadAndCacheImage(stack.imageIds).then(function(image) {
                // display this image
                cornerstone.displayImage(element, image);

            });
    }
}

//生成缩略图个数
function layoutSeriesThumbnail(imageList) {
    var thumbnail = document.getElementById("studyThumbnail");
    //序列的长度
    var seriesL = imageList.length;
    for(var i=0;i<seriesL;i++){
        var sonDiv = document.createElement('div');
        sonDiv.className="viewportContainer";
        sonDiv.style.borderStyle="solid";
        sonDiv.style.borderWidth="1px";
        sonDiv.style.boxSizing = "border-box";
        sonDiv.style.position="relative";
        thumbnail.appendChild(sonDiv);
        $(sonDiv).dblclick(function () {
            var index = $("#studyThumbnail").children("div").index(this);
            var imageIdlistChange= imageList[index].map(function (item) {
                return 'wadouri:'+item;
            });
            if(seriesFlag){
                webStacks[0].stack = {currentImageIdIndex:0,imageIds:imageIdlistChange};
                loadSeriesImage(webStacks[0],document.getElementById('imageViewerDiv').children[0].children[0]);
            }else if(oneSeriesFlag){
                var rows =  $("#imgtemp_rows").val();
                var cols =	 $("#imgtemp_columns").val();
                displayDiv = rows*cols;
                loadOneSeriesAllImg(imageIdlistChange);
            }


        });
        $(sonDiv).draggable({
            appendTo: "body",
            helper:function () {
                var index = $("#studyThumbnail").children("div").index(this);
                var oldDom = document.getElementById("studyThumbnail").children[index];
                var oldCanvas = oldDom.children[0];
                var dom =oldDom.cloneNode(true);
                var newCanvas = dom.children[0];
                var context = newCanvas.getContext('2d');
                context.drawImage(oldCanvas,0,0);
                return $(dom);
            }
        });

    }
}

//总体自适应大小
function imageAreaSize(row,colume) {
    var imageViewerDiv = document.getElementById('imageViewerDiv');
    var Cweight = document.documentElement.clientWidth*1;
    var Cheight = document.documentElement.clientHeight*0.91;
    //imageViewerDiv.style.width = Cweight+'px';
    //imageViewerDiv.style.height = Cheight+'px';
    // var h =  $("#txt_rows").val();
    // var l =	 $("#txt_columns").val();

    //获取显示影像div的个数
    var imageDivNum = imageViewerDiv.children[0].children.length;
    for (var i=0;i<imageDivNum;i++){
        //alert( imageViewerDiv.getElementsByTagName('div')[i].getElementsByTagName('div')[0].clientWidth);
      /*  imageViewerDiv.children[i].style.height = Cheight/row+'px';
        imageViewerDiv.children[i].style.weight = Cweight/colume+'px';*/
        imageViewerDiv.children[0].children[i].style.height = 100/row+'%';
        imageViewerDiv.children[0].children[i].style.weight = 100/colume+'%';
        imageViewerDiv.children[0].children[i].children[0].style.height = Cheight/row+'px';
        imageViewerDiv.children[0].children[i].children[0].style.weight = Cweight/colume+'px';
        if( imageViewerDiv.children[0].children[i].children[0].children[0]){
            imageViewerDiv.children[0].children[i].children[0].children[0].height = (Cheight/row-10);
            imageViewerDiv.children[0].children[i].children[0].children[0].weight = (Cweight/colume);
            imageViewerDiv.children[0].children[i].children[0].children[0].style.height = (Cheight/row-10)+'px';
            imageViewerDiv.children[0].children[i].children[0].children[0].style.weight = (Cweight/colume)+'px';
        }
    }
}

imageAreaSize(1,1);
$(window).resize(function(){
    //imageSize();
    if(seriesFlag){
        var h =  $("#temp_rows").val();
        var l =	 $("#temp_columns").val();
        imageAreaSize(h,l);
        //cornerstone.resize(element);
        for (let s = 0; s < webStacks.length; s++) {
            const element = webStacks[s].element;
            cornerstone.resize(element);
            //cornerstone.reset(element);
        }
    }else if(oneSeriesFlag){
        var h =  $("#imgtemp_rows").val();
        var l =	 $("#imgtemp_columns").val();
        imageAreaSize(h,l);
        for (let s = 0; s < displayDiv; s++) {
            const element = document.getElementById('imageViewerDiv').children[0].children[s].children[0];
            cornerstone.resize(element);
            //cornerstone.reset(element);
        }
    }

});
/*
* 插入表格
* @btn:选择触发器,jquery对象
* @opt:表格选项,{min:[最小列数,最小行数],max:[最大列数,最大行数],insert:确认选择后回调事件}
*/
var insertTable = function(btn,opt){
    if(!btn){return;}
    this.btn = btn;
    opt = opt || {};
    this.box = null;//弹框
    this.inBox = null;
    this.pickUnLight = null;
    this.pickLight = null;
    this.status = null;
    this.minSize = opt.min || [5,5];//最小列数和行数
    this.maxSize = opt.max || [15,15];//最大列数和行数
    this.insert = opt.insert;//回调
    this.nowSize = [];//当前选择尺寸
    this.isInit = {create:false,bind:false};
    this.bind();
}
insertTable.prototype = {
    init : function(){
        if(this.isInit.create){return;}
        this.isInit.create = true;
        var id = 'in_tab_box_'+String(Math.ceil(Math.random() * 100000) + String(new Date().getTime())),
            html = '<div class="in_tab_box" id="'+id+'">';
        html += 	'<div class="itb_con">';
        html += 		'<div class="itb_picker_unlight"></div>';
        html += 		'<div class="itb_picker_light"></div>';
        html += 	'</div>';
        html += 	'<div class="itb_picker_status"></div>';
        html += '</div>';
        $("body").append(html);
        this.box = $("#"+id);
        this.inBox = this.box.find(".itb_con");
        this.pickAll = this.box.find(".itb_picker_all");
        this.pickUnLight = this.box.find(".itb_picker_unlight");
        this.pickLight = this.box.find(".itb_picker_light");
        this.status = this.box.find(".itb_picker_status");
        this.setBg(this.minSize[0],0);
        this.setBg(this.minSize[1],1);
        //this.status.text(0+'列 x '+0+'行');
    },
    bind : function(){
        var T = this,
            pos,//弹框显示位置
            m,
            bPos,//弹框可选区域位置
            mPos;//鼠标位置
        this.btn.click(function(){
            if(!T.isInit.create){T.init();}//初始化弹框
            if(!T.isInit.bind){B();}//初始化事件
            m = $(this);
            if(T.box.is(":hidden")){
                pos = {
                    top:m.offset().top+60,
                    left:m.offset().left+2
                }
                T.box.css({
                    "top":pos.top,
                    "left":pos.left
                }).fadeIn(100);
                bPos = {
                    top : T.inBox.offset().top,
                    left : T.inBox.offset().left
                }
                $(document).bind("click",function(){T.hide();});
            }else{
                T.hide();
            }
            return false;
        })
        function B(){
            T.isInit.bind = true;
            T.inBox.mousemove(function(e){
                mPos = {
                    x : e.clientX,
                    y : e.clientY
                }
                if(mPos.x < bPos.left || mPos.y < bPos.top){return;}
                T.nowSize[0] = Math.ceil((mPos.x - bPos.left)/18);//列数
                T.nowSize[1] = Math.ceil((mPos.y - bPos.top)/18);//行数
                if(T.nowSize[0]>=T.minSize[0]&&T.nowSize[0]<T.maxSize[0]){
                    T.setBg(T.nowSize[0]+1,0);
                }else if(T.nowSize[0]<T.minSize[0]){
                    T.setBg(T.minSize[0],0);
                }else{
                    T.nowSize[0] = T.maxSize[0];
                }
                if(T.nowSize[1]>=T.minSize[1]&&T.nowSize[1]<T.maxSize[1]){
                    T.setBg(T.nowSize[1]+1,1);
                }else if(T.nowSize[1]<T.minSize[1]){
                    T.setBg(T.minSize[1],1);
                }else{
                    T.nowSize[1] = T.maxSize[1];
                }
                T.pickLight.css({
                    "width":T.nowSize[0]+'em',
                    "height":T.nowSize[1]+'em'
                })
                //T.status.text(T.nowSize[0]+'列 x '+T.nowSize[1]+'行');
            })
//单击确认插入表格
            T.box.click(function(){
                if(T.nowSize[0]>0&&T.nowSize[0]<=T.maxSize[0]&&T.nowSize[1]>0&&T.nowSize[1]<=T.maxSize[1]){
                    var rows = T.nowSize[1],
                        cols = T.nowSize[0];
                    try{T.insert(rows,cols);}catch(e){}
                }
            })
        }
    },
//调整背景区域
    setBg : function(size,t){
        if(t==0){
            this.inBox.width(size+'em');
            this.pickUnLight.width(size+'em');
        }else{
            this.inBox.height(size+'em');
            this.pickUnLight.height(size+'em');
        }
    },
//隐藏弹框
    hide : function(){
        var T = this;
        this.box.fadeOut(100,function(){
//重置
            T.setBg(T.minSize[0],0);
            T.setBg(T.minSize[1],1);
            T.pickLight.css({
                "width":'0',
                "height":'0'
            })
        });
    }
}
//序列布局的按钮的拉选
new insertTable($("#layout"),{
    min : [4,4],
    max : [4,4],
    insert : function(rows,cols){
        seriesFlag = true;
        oneSeriesFlag = false;
//这里只返回所选行数rows和列数cols,插入后的效果和样式需自定义,以下只是做简单的示例
       $("#txt_rows").val(rows);
       $("#txt_columns").val(cols);
        layoutSeries(rows,cols);
        $("#temp_rows").val(rows);

        $("#temp_columns").val(cols);
        webStacks = getStacks(imageIdlist);

        for (var i=0;i<webStacks.length;i++){
            loadSeriesImage(webStacks[i],document.getElementById('imageViewerDiv').children[0].children[i]);
        }
        imageAreaSize(rows,cols);
        currentDiv = 0;
       /* var html = '<table>';
        for(var i=0;i<rows;i++){
            html += '<tr>';
            for(var j=0;j<cols;j++){
                html += '<td></td>';
            }
            html += '</tr>';
        }
        html += '</table>';
        this.btn.siblings("table").remove();
        this.btn.after(html);*/
    }
});
new insertTable($("#layoutImg"),{
    min : [4,4],
    max : [4,4],
    insert : function(rows,cols){
        seriesFlag = false;
        oneSeriesFlag = true;
//这里只返回所选行数rows和列数cols,插入后的效果和样式需自定义,以下只是做简单的示例
        displayDiv = rows*cols;
        $("#imgtemp_rows").val(rows);
        $("#imgtemp_columns").val(cols);


        var imageIdlistChange= imageIdlist[currentDiv].map(function (item) {
            return 'wadouri:'+item;
        });
        loadOneSeriesAllImg(imageIdlistChange);

    }
});

